/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #1 (Linked List)
Last Modifyed 2/22
LinkedList.java
this file contains the LinkedList class that has all the functionality of the 
list.
Working/tested
compiles, no errors

Functions:
isEmpty(): checks if list is empty, first element null.
insert(data): inserts the given data to the front of the stack.
removeFront(): removes the first element from the list.
printList(): prints the entire list.
printFront(): prints just the first element of the list.
*/



import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LinkedList {
    private Link first;
    
    public LinkedList() {
	first = null;
    }
    public boolean isEmpty() {
	return first == null;
    }
    public void insert (double data) {
	Link link = new Link(data);
	link.next = first;
	first = link;
    }
    public void removeFront() {
	if (first == null) {
	    return;
	}
	first = first.next;
    }
    public void printList () {
	Link currentLink = first;
	System.out.print("List: ");
	while (currentLink != null) {
	    currentLink.printNode();
	    currentLink = currentLink.next;
	}
	System.out.println("");
    }
    public void printFront() {
	NumberFormat df = new DecimalFormat("#.000"); 
	System.out.println(df.format(first.getData()));
    }
}