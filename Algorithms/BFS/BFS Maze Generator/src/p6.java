/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #6 (Maze Generation with Disjoint Sets)
Last Modifyed 4/4
p6.java
main, checks command line arugments and makes/generates maze
Working/tested
compiles, no errors

Functions:

*/


class p6 {
    public static void main (String [] args) {
	int firstArg = 0;
	if (args.length > 0 ) {
	    try {
		firstArg = Integer.parseInt(args[0]);
	    } catch (NumberFormatException e) {
		System.err.println("Argument" + " must be an integer");
		System.exit(1);
	    }
	}
	if(args.length < 1) {
	    System.out.println("Need argument for size of maze, input n -> nxn maze");
	    System.exit(1);
	}
	if(args.length > 1) {
	    System.out.println("Only one argument of integer n for nxn maze");
	    System.exit(1);
	}
	if (firstArg < 3) {
	    System.out.println("Smallest Maze is 3x3, you input a " + firstArg + "x" + firstArg +" maze");
	    System.exit(1);
	}
	int size = firstArg;
	maze myMaze = new maze(size);
	myMaze.generate();
	myMaze.print();
    }

}