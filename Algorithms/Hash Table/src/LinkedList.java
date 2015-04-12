/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #5 (Hash table)
Last Modifyed 4/20
LinkedList.java
this file contains the LinkedList class that has all the functionality of the
list.
Working/tested
compiles, no errors

Functions:
isEmpty(): checks if list is empty, first element null.
insertFront(Record): inserts the given record to the front of the list.
insertRear(Record): inserts given record to the rear of the list
removeFront(): removes the first element from the list.
removeRear(); removes the last element from the list.
removeNode(int): removes record with the specified key value.
printList(): prints the entire list.
printFront(): prints just the first element of the list.
saveList(String): appends contents of list to given filename
find(int): returns copy of record with given key value.
*/



import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.io.*;
public class LinkedList {
    private Link first;
    private Link last;
    public LinkedList() {
        last = null;
        first = null;
    }
    public int getFirst() {
        return first.getKey();
    }
    public int getLast() {
        return last.getKey();
    }
    public boolean isEmpty() {
        return first == null;
    }
    public void insertFront (Record myRecord) {
        if (isEmpty()) {
            last = first = new Link(myRecord);
        }
        else {
            Link link = new Link(myRecord);
            link.next = first;
	    link.next.prev = link;
	    link.prev = null;
            first = link;
        }
    }
    public void insertRear (Record myRecord) {
        if (isEmpty()) {
            last = first = new Link(myRecord);
        }
        else {
            Link newLast = new Link(myRecord);
	    
            last.next = newLast;
	    newLast.prev = last;
            last = newLast;
        }
    }
    public void removeFront() {
        if (isEmpty()) {
            return;
        }
        if (first.next == null) {
            first = null;
            return;
        }
        first = first.next;
	first.prev = null;
    }
    public void removeRear() {
	if(isEmpty()) {
	    return;
	}
	if(last.prev == null) {
	    first = last.next;
	    return;
	}
	last = last.prev;
	last.next = null;
    }
    public boolean removeNode(int key) {
	Link currentLink = first;
	while (currentLink != null) {
	    if (currentLink.getKey() == key) {
		if (currentLink == first) {
		    removeFront();
		    return true;
		}
		if(currentLink == last) {
		    removeRear();
		    return true;
		}
		currentLink.prev.next = currentLink.next;
		currentLink.next.prev = currentLink.prev;
		return true;
	    }
	    currentLink = currentLink.next;
	}
	return false;
	
    }
    public String toString () {
        String output;
        if (isEmpty()) {
            output = "{ }";
            return output;
        }
        Link currentLink = first;
        output = "{ ";
        while (currentLink != null) {
            output = output + currentLink.getKey() +" ";
            currentLink = currentLink.next;
        }
        output = output + "}";
        return output;
    }
    public void printList() {
        if (isEmpty()) {
            System.out.print("{ }");
            return;
        }
        Link currentLink = first;
        System.out.print("{");
        while (currentLink != null) {
	    currentLink.printNode();
            currentLink = currentLink.next;
        }
	System.out.print("}");
        return;

    }
    public void printFront() {
        NumberFormat df = new DecimalFormat("#.000");
        System.out.println(df.format(first.getKey()));
    }
    public void saveList(String filename) {
	
	try{
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
	    Link currentLink = first;
	    while(currentLink != null){
		out.println(currentLink.getKey()+" "+currentLink.getData());
		currentLink = currentLink.next;
	    }
	    out.close();
        }
        catch(IOException e) {
            System.out.println("Failed to save file");
        }

    }
    public Record find(int key) {
	Link currentLink = first;
	Record copy = null;
	while (currentLink != null) {
	    if(currentLink.getKey() == key) {
		copy = currentLink.getRecord().clone();
		return copy;
	    }
	    currentLink = currentLink.next;
	}
	return null;
    }
}
