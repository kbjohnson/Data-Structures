/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #1 (Linked List)
Last Modifyed 2/22
Main.java
this file contains the main function of the assignment
where we get input and output it from the stack.
Working/tested
compiles, no errors

*/


import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

	Scanner scan = new Scanner (System.in);
	Stack myStack = new Stack(); 
	double inputData = 0;
	while (scan.hasNext()) {
	    inputData = scan.nextDouble();
	    myStack.push(inputData);
	}
	while (!myStack.isEmptyStack()) {
	    myStack.printTop();
	    myStack.pop();
	    
	}
    }
}