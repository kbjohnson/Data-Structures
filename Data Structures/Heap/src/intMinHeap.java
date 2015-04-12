/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #2 (Integer Minimum Heap and Priority Queue)
Last Modifyed 3/9
intMinHeap.java
this file contains the heap class
Working/tested
compiles, no errors

intMinHeap(): Default constructor, makes empty heap of capaciy 100.
intMinHeap(int): Overloaded constuctor, makes empty heap of given capacity.
parent(int):return index of parent to given index.
left(int):returns index of left of given index.
right(int):returns index of right of given index.
minimum():return A[1] if object is a heap
extractmin():removes and returns min value of the heap.
isempty(): checks if heap is empty
heapify():maintains heap property.
buildheap():builds a heap from an array
heapsort(): makes a sorted(decending) array from the heap.
heapinsert(int):inserts data into the heap
decreasekey():used for newly inserted data to maintain heap property.
indexOfSmallest(int,int,int):returns index of smallest value of the 3 indexes given.
toString(): prints heap/array with size and capacity
swap(int,int):swap data in the two given indexes.
*/



public class intMinHeap {
    public intMinHeap() {
	heap = true;
	size = 0;
	capacity = 100;
	A = new int[capacity+1];//+1 since element zero is ignored.
    }
    public intMinHeap(int heapCapacity) {
	heap = true;
	size = 0;
	capacity = heapCapacity;
	A = new int[capacity+1];//+1 since element zero is ignored.
    }
    public int parent(int i) {
	return i/2;
    }
    public int left(int i) {
	return 2*i;
    }
    public int right(int i) {
	return (2*i)+1;
    }
    public int minimum() {
	if (isempty()) {
	    return 0;
	}
	if (heap == false) {
	    System.err.println("Object is not a heap");
	    return 0;
	}
	return A[1];
    }
    public int extractmin() {
	if (isempty()) {
	    System.err.println("No data in heap");
	    return 0;
	}
	if (heap == false) {
            System.err.println("Object is not a heap");
	    return 0;
        }
	int min = A[1];
	A[1] = A[size];
	size--;
	heapify(1);
	return min;
    }
    public boolean isempty() {
	return size == 0;
    }
    public void heapify(int i) {
	int n = indexOfSmallest(i,left(i),right(i));

	if (n != i) {
	    swap(i,n);
	    heapify(n);
	}
    }
    public void buildheap() {
	for (int i = size/2;i > 0;i--) {
	    heapify(i);
	}
	heap = true;
    }
    public void heapsort() {
	int savedSize = size;
	if (heap == false) {
	    buildheap();
	}
	while (size > 0) {
	    swap(1,size);
	    size--;
	    heapify(1);
	}
	size = savedSize;
	heap = false;
    }
    public void heapinsert(int data) {
	if (size == capacity) {
	    System.err.println("Heap at maximum capacity");
	    return;
	}
	size++;
	A[size] = data;
	decreaseKey(size,data);
    }
    private void decreaseKey(int i,int key) {
	if (key > A[i]) {
	    return;
	}
	A[i] = key;
	while (i > 1 && A[parent(i)] > A[i]) {
	    swap(i,parent(i));
	    i = parent(i);
	}
    }
    private int indexOfSmallest(int i,int i2,int i3) {
	int min = i;
	if (i2 <= size && A[i2] < A[min]) {
	    min = i2;
	}
	if (i3 <= size && A[i3] < A[min]) {
	    min = i3;
	}
	return min;
    }
    
    public String toString() {
	String contents;
	if (heap == true) {
	    contents = "Heap [s" + size + ",c" + capacity + "] "; 
	}
	else {
            contents = "Array [s" + size + ",c" + capacity + "] ";
        }

	contents = contents + "{";
	for(int i=1;i<size;i++) {
	    contents = contents + A[i] + ", ";
	}
	contents = contents + A[size] + "}";
	return contents;
    }
    public void swap(int i,int n) {
	int temp = A[i];
	A[i] = A[n];
	A[n]= temp;
    }
    
    private int A[];
    private int capacity;
    private int size;
    private boolean heap;

}