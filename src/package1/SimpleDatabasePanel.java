package package1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**********************************************************************
 * @author John Tunisi
 * 
 * @version 12/3/13
 *********************************************************************/
public class SimpleDatabasePanel {
	/******************************************************************
	 * Saves a database as a text file
	 * 
	 * @param filename
	 * @return
	 *****************************************************************/
	public boolean saveAsText(String filename) {
		if (filename.equals("")) {
			return false;
		}
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			SimpleDatabase<Node<Student>> d = new SimpleDatabase<Node<Student>>();
			Node<Student> n = new Node<Student>();
			n = d.getTop();
			out.println(d.getSize());
			for (int i = 0; i < d.getSize(); i++) {
				out.println(n.getData().getName());
				out.println(n.getData().getgNumber());
				out.println(n.getData().getGpa());
				n.getNext();
			}
			out.close();
			return true;
		} catch (IOException ex) {
			System.out.println ("IO Error!");
			return false;
		}
	}
	
	/******************************************************************
	 * Loads database from a text file
	 * 
	 * @param filename
	 *****************************************************************/
	public static SimpleDatabase<Node<Student>> loadFromText(String filename) {

		SimpleDatabase<Node<Student>> d = new SimpleDatabase<Node<Student>>();
		try {
			Scanner scanner = new Scanner(new File(filename));
			int count = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i < count; i++) {
				String name = scanner.nextLine().trim();
				String gNum = scanner.nextLine().trim();
				String g = scanner.nextLine().trim();
				Double GPA = Double.parseDouble(g);
				Student s = new Student(name, gNum, GPA);
				d.insert(s);
			}
			scanner.close();
			return d;
		} catch (Exception ex) {
			ex.printStackTrace();
			return d;
		}
	}
}