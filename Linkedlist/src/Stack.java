/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #1 (Linked List)
Last Modifyed 2/22
Stack.java
this file contains the Stack class that we use to store the data
using a linked list with stack functionality.
Working/tested
compiles, no errors

Functions:
push(double data): inserts given number to the top of the stack.
pop(): removes the first element from the top of the stack.
printStack(): prints the entire stack
printTop(): prints just the first element of the stack.
isEmptyStack(): checks if the stack is empty.
*/


public class Stack {
    private LinkedList list = new LinkedList();

    public void  push(double data) {
	list.insert(data);
    }
    public void pop() {
	list.removeFront();

    }
    public void printStack() {
	
	list.printList();


    }
    public void printTop() {
	list.printFront();
    }
    public boolean isEmptyStack() {
	return list.isEmpty();

    }



}