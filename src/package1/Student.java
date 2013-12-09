package package1;

/**********************************************************************
 * A student's info
 * 
 * @author John Tunisi
 * @version 11/7/13
 *********************************************************************/
public class Student implements Comparable<Student> {
	private String name;
	private String gNumber;
	private double gpa;
	
	/******************************************************************
	 * A constructor to initialize instance variables
	 * 
	 * @param name the name of the student
	 * @param gNumber the gNumber of the student
	 * @param gpa the grade point average of the student
	 *****************************************************************/
	public Student(String name, String gNumber, double gpa) {
		super();
		this.name = name;
		this.gNumber = gNumber;
		this.gpa = gpa;
	}

	/******************************************************************
	 * Gets the name of the student
	 * 
	 * @return name the name of the student
	 *****************************************************************/
	public String getName() {
		return name;
	}

	/******************************************************************
	 * Sets the name of the student
	 * 
	 * @param name the name of the student
	 *****************************************************************/
	public void setName(String name) {
		this.name = name;
	}

	/******************************************************************
	 * Gets the gNumber of the student
	 * 
	 * @return gNumber the number of the student
	 *****************************************************************/
	public String getgNumber() {
		return gNumber;
	}

	/******************************************************************
	 * Sets the gNumber of the student
	 * 
	 * @param gNumber the number of the student
	 *****************************************************************/
	public void setgNumber(String gNumber) {
		this.gNumber = gNumber;
	}

	/******************************************************************
	 * Gets the gpa of the student
	 * 
	 * @return gpa the student's grade point average
	 *****************************************************************/
	public double getGpa() {
		return gpa;
	}

	/******************************************************************
	 * Sets the gpa of the student
	 * 
	 * @param gpa the student's grade point average
	 *****************************************************************/
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/******************************************************************
	 * Compares one student to another based on gNumber
	 * 
	 * @param student the student to compare to
	 *****************************************************************/
	public int compareTo(Student student) {
		if(student.getgNumber().equals(gNumber))
			return 0;
		
		if(Integer.parseInt(gNumber) < Integer.parseInt(student.getgNumber()) )
			return -1;
		
		return 1;
	}
	
	/******************************************************************
	 * Displays the student as a string
	 *****************************************************************/
	public String toString(){
		String str = "";
		return str = "" + name + "\t" + gpa + "\t" + gNumber;
		
	}
}