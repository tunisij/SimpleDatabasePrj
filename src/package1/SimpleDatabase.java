package package1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**********************************************************************
 * Performs functions of a database
 * 
 * @author John Tunisi
 * @version 12/3/13
 *********************************************************************/
public class SimpleDatabase<T> implements ISimpleDatabase{

	private Node<Student> top;
	private Node<Student> tail;
	private int size = 0;
//	private ArrayList<String> undoList;
//	private String method;
	private Stack<Student> s = new Stack<Student>();
	
	/******************************************************************
	 * Gets the top of the linked list
	 * 
	 * @return top the top of the list
	 *****************************************************************/
	public Node<Student> getTop() {
		return top;
	}

	/******************************************************************
	 * Gets the bottom of the linked list
	 * 
	 * @return tail the bottom of the list
	 *****************************************************************/
	public Node<Student> getTail() {
		return tail;
	}

	/******************************************************************
	 * Gets the size of the linked list
	 * 
	 * @return size the size of the list
	 *****************************************************************/
	public int getSize() {
		return size;
	}
	
	/******************************************************************
	 * Inserts a student into the database
	 * 
	 * @param student an instance of a student
	 *****************************************************************/
	public void insert(Student student) {
		if(top == null){
			top = new Node<Student>(student, null, null);
			tail = top;
			s.add(new Student(top.getData().getName(), top.getData().getgNumber(), top.getData().getGpa()));
		}else{
			tail.setNext(new Node<Student>(student, null, tail));
			tail = tail.getNext();
			s.add(new Student(tail.getData().getName(), tail.getData().getgNumber(), tail.getData().getGpa()));
		}
		size++;
//		method = "insert";
//		undoList.add(method);
	}

	/******************************************************************
	 * Deletes a student from the database
	 * 
	 * @param gNumber the gNumber of a student
	 *****************************************************************/
	public boolean delete(String gNumber) {
		if(top == null){
			return false;
		}
		
		if(top.getData().getgNumber().equals(gNumber)){
			s.add(new Student(top.getData().getName(), top.getData().getgNumber(), top.getData().getGpa()));
			if(top.getNext() != null)
				top.getNext().setPrev(null);
			
			top = top.getNext();
			size--;
			return true;
		}
		
		Node<Student> temp = top;
		
		while(temp != null){
			if(temp.getData().getgNumber().equals(gNumber)){
				s.add(new Student(temp.getData().getName(), temp.getData().getgNumber(), temp.getData().getGpa()));
				if(temp.getNext() == null){
					temp.getPrev().setNext(null);
					size--;
					return true;
				}else{
					temp.getNext().setPrev(temp.getPrev());
					temp.getPrev().setNext(temp.getNext());
					size--;
					return true;
				}
			}
			temp = temp.getNext();
		}
//		method = "delete";
//		undoList.add(method);
		return false;
	}

	/******************************************************************
	 * Finds a student based on their gNumber
	 * 
	 * @param gNumber the gNumber of a student
	 *****************************************************************/
	public Student find(String gNumber) {
		if(top == null)
			return null;
		
		Node<Student> temp = top;

		while(temp != null){
			if(temp.getData().getgNumber().equals(gNumber))
				return temp.getData();
			
			temp = temp.getNext();
		}
		return null;
	}

	/******************************************************************
	 * Reverses the order of the list
	 *****************************************************************/
	public void reverseList() {		
		if(top == null)
			return;
		
        Node<Student> currNode = top;
        Node<Student> nextNode = top.getNext();
        top.setNext(null);
        Node<Student> loopNode;

        //switches node's next
        while(nextNode != null) {
        	loopNode = nextNode.getNext();
            nextNode.setNext(currNode);
            currNode = nextNode;
            nextNode = loopNode;
        }
        top = currNode;
        nextNode = top.getNext();
        top.setPrev(null);
        
        //switches node's prev
        while(nextNode != null){
        	loopNode = nextNode.getNext();
        	nextNode.setPrev(currNode);
        	currNode = nextNode;
        	nextNode = loopNode;
        }
        tail = currNode;
//        method = "reverseList";
//        undoList.add(method);
    }

	/******************************************************************
	 * Removes duplicates based on gNumber
	 *****************************************************************/
	public void removeDuplicates(){
		Node<Student> curr = top;
		if(curr == null || curr.getNext() == null){
			return;
		}
		Node<Student> curr2;
		Node<Student> prev;
		while(curr != null){
			curr2 = curr.getNext();
			prev = curr;
			while(curr2 != null){
				if(curr.getData().equals(curr2.getData())){
					s.add(new Student(curr.getData().getName(), curr.getData().getgNumber(), curr.getData().getGpa()));
//					method = "removeDuplicates";
//					undoList.add(method);
					
					prev.setNext(curr2.getNext());
					size--;
				}
				prev = curr2;
				curr2 = curr2.getNext();
			}
			curr = curr.getNext();
		}
	}
	
	/******************************************************************
	 * Undos previous function
	 *****************************************************************/
	public void undo() {	
//		if(undoList.get(0).equals("insert")){
//			
//		}
		
	}

	/******************************************************************
	 * Loads database from a text file
	 *****************************************************************/
	public void loadDB(String fileName) {
		SimpleDatabase<Node<Student>> d = new SimpleDatabase<Node<Student>>();
		try {
			Scanner scanner = new Scanner(new File(fileName));
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/******************************************************************
	 * Saves database to a text file
	 *****************************************************************/
	public void saveDB(String fileName) {
		if (fileName.equals("")) {
			return;
		}
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
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
			return;
		} catch (IOException ex) {
			System.out.println ("IO Error!");
			return;
		}
	}
	
	/******************************************************************
	 * Creates a string format of the database
	 *****************************************************************/
	public String toString(){
		String str = "";
		Node<Student> temp = top;

		while(temp != null){
			str += temp.getData().toString() + "\n";
			temp = temp.getNext();
		}
		return str;
	}

	/******************************************************************
	 * Main method for testing
	 * 
	 * @param args
	 *****************************************************************/
	public static void main(String[] args){
		SimpleDatabase<Student> s = new SimpleDatabase<Student>();
		Student s1 = new Student("John", "1", 2);
		Student s2 = new Student("Johadsfn", "asdf1", 21);
		s.insert(s1);
		s.insert(s2);
		s.removeDuplicates();
		s.delete("1");
		//	s.undoList.toString();

	}
}
