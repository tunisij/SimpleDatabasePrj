package package1;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleDatabaseTest {
	String[] sNames = {"John", "Adam", "Luke", "Sarah", "Brian", "Liz", "Lauren", "Jenna", "Zack", "Carly"};
	String[] sNumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "1"};
	double[] sGPA = {0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5};
	ArrayList<Student> s = new ArrayList<Student>();
	SimpleDatabase<Student> d = new SimpleDatabase<Student>();

	@Before public void initialize() {
		for(int i=0; i < 10; i++){
			Student student = new Student(sNames[i], sNumber[i], sGPA[i]);
			s.add(student);
		}
	}
	
	@Test
	public void testToBlankString(){
		d.insert(s.get(0));
		d.delete("1");
		assertFalse(d.toString().contains("John"));
	}

	@Test
	public void testInsertName() {
		d.insert(s.get(0));
		assertTrue(d.toString().contains("John"));
	}
	
	@Test
	public void testInsertGPA() {
		d.insert(s.get(0));
		assertTrue(d.toString().contains("0.0"));
	}
	
	@Test
	public void testInsertgNum() {
		d.insert(s.get(0));
		assertTrue(d.toString().contains("1"));
	}
	
	@Test
	public void testInsertMult() {
		d.insert(s.get(0));
		d.insert(s.get(1));
		assertTrue(d.toString().contains("John"));
		assertTrue(d.toString().contains("Adam"));
	}
	
	@Test
	public void testCompareTo(){
		assertTrue(s.get(0).getgNumber() == s.get(9).getgNumber());
	}
	
	@Test
	public void testCompareToEqual(){
		assertTrue(s.get(0).compareTo(s.get(9)) == 0);
	}
	
	@Test
	public void testCompareToLess(){
		assertTrue(s.get(0).compareTo(s.get(8)) == -1);
	}
	
	@Test
	public void testCompareToGreat(){
		assertTrue(s.get(8).compareTo(s.get(1)) == 1);
	}
	
	@Test
	public void testDeleteNull() {
		d.insert(s.get(0));
		assertTrue(s.toString().contains("John"));
		assertFalse(d.delete("0"));
	}
	
	@Test
	public void testDeleteOne() {
		d.insert(s.get(0));
		assertTrue(d.toString().contains("John"));
		assertTrue(d.delete("1"));
	}
	
	@Test
	public void testDeleteMult() {
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		d.insert(s.get(4));
		d.delete("0");
		d.delete("1");
		d.delete("3");
		d.delete("4");
		d.delete("5");
		assertTrue(d.toString().contains("Adam"));
		assertFalse(d.toString().contains("John"));
	}
	
	@Test
	public void testReverseTop(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.reverseList();
		assertTrue(d.getTop().getData().getgNumber().equals("3"));
	}
	
	@Test
	public void testReverseTail(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.reverseList();
		assertTrue(d.getTail().getData().getgNumber().equals("1"));
	}
	
	@Test
	public void testReverseTopNext(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		d.reverseList();
		assertTrue(d.getTop().getNext().getData().getgNumber().equals("3"));
	}
	
	@Test
	public void testReverseTopPrev(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		d.reverseList();
		assertTrue(d.getTop().getPrev() == null);

	}
	
	@Test
	public void testReverseTailNext(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		d.reverseList();
		assertTrue(d.getTail().getNext() == null);
	}
	
	@Test
	public void testReverseTailPrev(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		d.reverseList();
		assertTrue(d.getTail().getPrev().getData().getgNumber().equals("2"));
	}
	
	@Test
	public void testFindNullEmpty(){
		assertTrue(d.find("0") == null);
	}
	
	@Test
	public void testFindNullFull(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		d.insert(s.get(4));
		assertTrue(d.find("7") == null);
	}
	
	@Test
    public void testFindOne(){
    	d.insert(s.get(0));
    	assertTrue(d.toString().contains("John"));
    	assertTrue(d.find("1").getName().equals("John"));
	}
	
	@Test
	public void testFindMult(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		d.insert(s.get(4));
		assertTrue(d.find("2").getgNumber().equals("2"));
	}
	
	@Test
	public void testCount(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(3));
		assertTrue(d.getSize() == 4);
	}
	
	@Test
	public void testRemoveDNull(){
		d.removeDuplicates();
		assertTrue(d.getSize() == 0);
	}
	
	@Test
	public void testRemoveDSingle(){
		d.insert(s.get(0));
		d.removeDuplicates();
		assertTrue(d.getSize() == 1);
	}
	
	//doesnt work
	@Test
	public void testRemoveDMultSame(){
		d.insert(s.get(0));
		d.insert(s.get(0));
		d.insert(s.get(0));
		d.insert(s.get(0));
		d.removeDuplicates();
		assertTrue(d.getSize() == 1);
	}
	
	@Test
	public void testRemoveDMultDiff(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(0));
		d.insert(s.get(2));
		d.removeDuplicates();
		assertTrue(d.getSize() == 3);
	}
	
	@Test
	public void testRemoveDMultDiffSet(){
		d.insert(s.get(2));
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(5));
		d.insert(s.get(2));
		d.insert(s.get(7));
		d.insert(s.get(2));
		d.removeDuplicates();
		assertTrue(d.getSize() == 5);
	}
	
	@Test
	public void testRemoveDMultSets(){
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.insert(s.get(2));
		d.insert(s.get(0));
		d.insert(s.get(1));
		d.removeDuplicates();
		assertTrue(d.getSize() == 3);
	}
}
