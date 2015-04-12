/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment # (Quicksort)
Last Modifyed 3/16
p3.java
this file contains the quicksort and main functions.
Working/tested
compiles, no errors


Functions:
insertionsort(ArrayList,int,int):insertion sort used when array is small enough
quicksort(ArrayList,int,int): main quicksort function
partition(ArrayList,int,int): finds a suitable partition using median of 3
swap(ArrayList,int,int):swaps the two indexes in the given arraylist
medianOf3(ArrayList,int,int): finds the median value of left,right, and center.
printArray(arrayList):prints arraylist with specified format.
*/


import java.util.Scanner;
import java.util.ArrayList;

class p3 {
    public static void main (String [] args) {
	ArrayList<Integer> myArrayList = new ArrayList<Integer>();
	Scanner scan = new Scanner(System.in);
	int data;
	int size;
	int left = 0;
	int right;
	while (scan.hasNext()) {
	    data = scan.nextInt();
	    myArrayList.add(data);
	}
	right = myArrayList.size()-1;
	scan.close();
        if (myArrayList.isEmpty() || myArrayList.size() == 1) {
            System.out.println("List too small to sort");
            return;
        }
	quicksort(myArrayList,left,right);
	printArray(myArrayList);
    }
    public static void insertionsort(ArrayList<Integer> myArrayList,int left,int right) {
	int j;
	int newValue;
	int size = (right-left)+1;
	for (int i = left; i < right;i++) {
	    newValue = myArrayList.get(i);
	    j=i;
	    while (j > 0 && myArrayList.get(j-1) > newValue) {
		myArrayList.set(j,myArrayList.get(j-1));
		j--;
	    }
	    myArrayList.set(j,newValue);
	}
    }

    public static void quicksort(ArrayList<Integer> myArrayList,int left, int right) {
	int size = (right-left)+1;
	if (size <= 15) {
	    insertionsort(myArrayList,left,right);
	}

	if (left < right) {
	    int partition = partition(myArrayList,left,right);

	    quicksort(myArrayList,left,partition-1);
	    quicksort(myArrayList,partition+1,right);
	}
    }

    private static int partition(ArrayList<Integer> myArrayList,int left,int right) {
	int x;
	int i = left-1;

	x = medianOf3(myArrayList,left,right);
	for(int j=left;j<right;j++) {
	    if (myArrayList.get(j) <= x) {
		i++;
		swap(myArrayList,j,i);
	    }
	}
	swap(myArrayList,i+1,right);
	return i+1;
    }


    private static void swap(ArrayList<Integer> myArrayList,int i,int n) {
	int temp = myArrayList.get(i);
	myArrayList.set(i,myArrayList.get(n));
	myArrayList.set(n,temp);

    }

    private static int medianOf3(ArrayList<Integer> myArrayList,int left,int right) {

	
	int center = (left+right)/2;
	if (myArrayList.get(left) > myArrayList.get(right)) {
	    if (myArrayList.get(right) > myArrayList.get(center)) {
		swap(myArrayList,right,right);
		return myArrayList.get(right);
	    } else if (myArrayList.get(left) > myArrayList.get(center)) {
		swap(myArrayList,center,right);
		return myArrayList.get(right);
	    } else {
		swap(myArrayList,left,right);
		return myArrayList.get(right);
	    }
	} else {
	    if (myArrayList.get(left) > myArrayList.get(center)) {
		swap(myArrayList,left,right);
		return myArrayList.get(right);
	    } else if (myArrayList.get(right) > myArrayList.get(center)) {
		swap(myArrayList,center,right);
		return myArrayList.get(right);
	    } else {
		swap(myArrayList,right,right);
		return myArrayList.get(right);
	    }
	}
    }

    public static void printArray(ArrayList<Integer> myArrayList) {
	for (int i=0;i<myArrayList.size()-1;i++) {
	    System.out.format("%09d%n",myArrayList.get(i));
	}
	
    }
}