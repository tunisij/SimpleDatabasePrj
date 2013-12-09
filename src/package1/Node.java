package package1;

/**********************************************************************
 * Creates nodes for a linked list
 * 
 * @author John Tunisi
 * @version 11/12/13
 *********************************************************************/
public class Node<E> {
	
	public E data;
	public Node<E> next;
	public Node<E> prev;
	
	/******************************************************************
	 * A constructor that initializes instance variables
	 * 
	 * @param data the data being contained in a node
	 * @param next the next node in the linked list
	 *****************************************************************/
	public Node(E data, Node<E> next, Node<E> prev) {
		super();
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	/******************************************************************
	 * A constructor that calls the super class
	 *****************************************************************/
	public Node() {
		super();
	}

	/******************************************************************
	 * Returns the data held within the node
	 * 
	 * @return data the data of a node
	 *****************************************************************/
	public E getData() {
		return data;
	}
	
	/******************************************************************
	 * Sets the data held within the node
	 * 
	 * @param data the data being sent to the node
	 *****************************************************************/
	public void setData(E data) {
		this.data = data;
	}
	
	/******************************************************************
	 * Returns the node that follows in the linked list
	 * 
	 * @return next the next node in the linked list
	 *****************************************************************/
	public Node<E> getNext() {
		return next;
	}
	
	/******************************************************************
	 * Sets the next node in the linked list
	 * 
	 * @param next the next node in the linked list
	 *****************************************************************/
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	/******************************************************************
	 * Returns the previous node in the linked list
	 * 
	 * @return prev the prev node in the linked list
	 *****************************************************************/
	public Node<E> getPrev() {
		return prev;
	}
	
	/******************************************************************
	 * Sets the previous node in the linked list
	 * 
	 * @param prev the previous node in the linked list
	 *****************************************************************/
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
}