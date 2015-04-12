/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #2 (Integer Minimum Heap and Priority Queue)
Last Modifyed 3/9
p2.java
this file contains the main function where we make a heap
and test it.
Working/tested
compiles, no errors

*/



public class p2 {
    public static void main(String[] args) {
	int heapSize = 50;
	intMinHeap myHeap = new intMinHeap(heapSize);
	myHeap.heapinsert(5);
	myHeap.heapinsert(3);
	myHeap.heapinsert(7);
	myHeap.heapinsert(1);
	myHeap.heapinsert(2);
	myHeap.heapinsert(4);
	myHeap.heapinsert(9);
	myHeap.heapinsert(8);
	myHeap.heapinsert(6);
	myHeap.heapinsert(10);

	System.out.println("Kevin Johnson");
	System.out.println(myHeap.toString());
	myHeap.extractmin();
	System.out.println(myHeap.toString());
	myHeap.extractmin();
	System.out.println(myHeap.toString());
	myHeap.heapinsert(1);
	System.out.println(myHeap.toString());
	myHeap.heapsort();
	System.out.println(myHeap.toString());

    }
}