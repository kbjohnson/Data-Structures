/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #1 (Linked List)
Last Modifyed 2/22
Link.java
this file contains the Link class that makes each node of the Linked List.
Working/tested
compiles, no errors

Functions:
printNode(): prints the data of the invoked node
getData: returns the value of the data in the invoked node.
*/



public class Link {
    public Link next;
    private  double data;

    public Link(double givenData) {
	data = givenData;
    }
    public void printNode() {
	System.out.print(data + " , ");
    }
    public double getData() {
	return data;
    }




}