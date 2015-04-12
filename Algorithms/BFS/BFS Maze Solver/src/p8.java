/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #8 (Breadth-First Search)
Last Modifyed 5/25
p8.java
main for bfs maze path finding
Working/some testing
compiles, no errors

Functions:

*/



import java.util.Scanner;

class p8 {
    public static void main(String [] args) {
	Scanner scan = new Scanner(System.in);
	int size = 0;
	String line = null;
	line = scan.nextLine();
	size = line.length();
	char maze [][] = new char [size][size];
	for (int i=0;i<size;i++) {
	    maze[0][i] = line.charAt(i);
	}
	for (int i=1;i<size;i++) {
	    line = scan.nextLine();
	    for (int j=0;j<size;j++) {
		maze[i][j] = line.charAt(j);
	    }
	}
	bfsMaze myBFS = new bfsMaze(maze,size);
	myBFS.bfs();
    }
}