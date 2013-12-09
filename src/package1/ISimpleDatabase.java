package package1;

/**********************************************************************
 * An interface to create methods for database
 * 
 * @author John Tunisi
 * @version 11/3/13
 *********************************************************************/
interface ISimpleDatabase {
	
	/* used to insert a student into the DB */
	void insert(Student student);

	/* used to delete a student from the DB, use the gNumber to find the student*/
	boolean delete(String gNumber);

	/* returns a string of the entire DB */
	String toString();

	/* finds a Student, otherwise returns null */
	Student find(String gNumber);

	/* reverses the database (see notes below) */
	void reverseList();

	/* removes duplicates from the database) */
	void removeDuplicates();

	/* undo the previous command */
	void undo();

	/* Loads/saves using files */
	void loadDB(String fileName);
	void saveDB(String fileName);
} 
