package package1;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.*;

/*******************************************************************
 * GUI for Lost game
 * 
 * @author John Tunisi
 * @version 3/27/13
 *******************************************************************/
public class GUISimpleDatabase extends JPanel implements ActionListener{

	/** database */
    private SimpleDatabase<Student> d;

    /** text fields */
    private JTextField nameText;
    private JTextField gNumText;
    private JTextField GPAText;

    /** database functions */
    private JButton insertButton;
    private JButton findButton;
    private JButton duplicateButton;
    private JButton loadButton;
    private JButton deleteButton;
    private JButton reverseButton;
    private JButton displayButton;
    private JButton saveButton;

    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenuItem quitItem;
    private JMenuItem clearItem;

    /******************************************************************
     * Constructor - instantiates and displays all of the GUI commponents
     ******************************************************************/
    public GUISimpleDatabase(){ 
    	d = new SimpleDatabase<Student>();

        theGUI = new JFrame("Simple Database");
        theGUI.setVisible(true);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the Results Area for the Center area
        results = new JTextArea(20,25);
        JScrollPane scrollPane = new JScrollPane(results);
        theGUI.add(BorderLayout.CENTER, scrollPane);

        //allow word wrap
        results.setLineWrap(true);
        results.setWrapStyleWord(true);

        //allows auto scrolling within JTextArea
        DefaultCaret caret = (DefaultCaret) results.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        //creates the east panel for the east area
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        theGUI.add(BorderLayout.EAST, eastPanel);

        //instantiates buttons for south area
        nameText = new JTextField(5);
        gNumText = new JTextField(5);
        GPAText = new JTextField(5);
        nameText.setText("Name");
        gNumText.setText("gNum");
        GPAText.setText("1.0");
        insertButton = new JButton("Insert");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");
        duplicateButton = new JButton("Remove Duplicates");
        loadButton = new JButton("Load");
        reverseButton = new JButton("Reverse");
        displayButton = new JButton("Display");
        saveButton = new JButton("Save");

        //adds buttons to south area
//        eastPanel.setLayout(new GridLayout(2,4));
        eastPanel.add(nameText);
        eastPanel.add(gNumText);
        eastPanel.add(GPAText);
        eastPanel.add(insertButton);
        eastPanel.add(deleteButton);
        eastPanel.add(findButton);
        eastPanel.add(duplicateButton);
        eastPanel.add(loadButton);
        eastPanel.add(reverseButton);
        eastPanel.add(displayButton);
        eastPanel.add(saveButton);

        //adds button listeners
        insertButton.addActionListener(this);
        findButton.addActionListener(this);
        duplicateButton.addActionListener(this);
        loadButton.addActionListener(this);
        reverseButton.addActionListener(this);
        saveButton.addActionListener(this);
        deleteButton.addActionListener(this);
        displayButton.addActionListener(this);


        // set up File menus
        setupMenus();
        theGUI.pack();
    }
    
    /*****************************************************************
     * Main method
     *****************************************************************/
    public static void main(String arg[]){
        new GUISimpleDatabase();
    }

    /******************************************************************
     * Set up the menu items
     ******************************************************************/
    private void setupMenus(){

        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        clearItem = new JMenuItem("Clear");

        // assign action listeners
        quitItem.addActionListener(this);
        clearItem.addActionListener(this);

        // display menu components
        fileMenu.add(quitItem);
        fileMenu.add(clearItem);
        menus = new JMenuBar();

        menus.add(fileMenu);
        theGUI.setJMenuBar(menus);
    }   

    /******************************************************************
    Respond to menu selections and button clicks

    @param e the button or menu item that was selected
     ******************************************************************/
    String name = "";
    String gNumber = "";
    double GPA;
    public void actionPerformed(ActionEvent e){

    	// menu item - quit
    	if (e.getSource() == quitItem){
    		System.exit(1);   
    	}

    	if(e.getSource() == clearItem){
    		results.setText("");
    	}

    	//button to insert student
    	if(e.getSource() == insertButton){
    		name = nameText.getText();
    		gNumber = gNumText.getText();
    		GPA = Double.parseDouble(GPAText.getText());
    		Student s = new Student(name, gNumber, GPA);
    		d.insert(s);
    		results.append(d.toString() + "\n\n");
    	}

    	//button to delete student
    	if(e.getSource() == deleteButton){
    		gNumber = gNumText.getText();
    		d.delete(gNumber);
    		results.append(d.toString() + "\n\n");
    	}

    	//button to find student
    	if(e.getSource() == findButton){
    		gNumber = gNumText.getText();
    		d.find(gNumber);
    		results.append(d.toString() + "\n\n");
    	}

    	//button to remove duplicate students
    	if(e.getSource() == duplicateButton){
    		d.removeDuplicates();
    		results.append(d.toString() + "\n\n");
    	}

    	//button to display description of current room
    	if(e.getSource() == loadButton){
    		JFileChooser chooser = new JFileChooser();
    		int returnVal = chooser.showOpenDialog(chooser);
    		if(returnVal == JFileChooser.APPROVE_OPTION) {
    			String file = chooser.getSelectedFile().getAbsolutePath();
    			SimpleDatabase<Node<Student>> d = new SimpleDatabase<Node<Student>>();
    			d.loadDB(file);
    			results.append(d.toString() + "\n\n");
    		}

    		//button to display inventory and notes
    		if(e.getSource() == reverseButton){
    			d.reverseList();
    			results.append(d.toString() + "\n\n");
    		}

    		//button to take notes
    		if(e.getSource() == displayButton){
    			results.append(d.toString() + "\n\n");
    		}

    		//button to view notes
    		if(e.getSource() == saveButton){
    			d.saveDB("SimpleDatabase.txt");
    		}
    	}
    }
}