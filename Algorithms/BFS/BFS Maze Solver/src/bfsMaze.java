/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #8 (Breadth-First Search)
Last Modifyed 5/25
bfsMaze.java
class with bfs functionality of maze
Working/some testing
compiles, no errors

Functions:
bfs(): main functionality of bfs, finds path thrugh invoked maze.
printPath(): prints path through parent array once bfs is done.
topOpen(i,j): helper function for bfs, detects if given "square" has an open top
bottomOpen(i,j): helper function for bfs, detects if given "square" has an open bottom
leftOpen(i,j): helper function for bfs, detects if given "square" has an open left
rightOpen(i,j): helper function for bfs, detects if given "square" has an open right
toOneD(i,j): maps given 2d array coordinates to a 1d array coordinate
printMaze(): prints stored maze.


*/




import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class bfsMaze {
    bfsMaze(char graph [][],int mazeSize) {
	maze = graph;
	size = mazeSize;
	color = new String[size*size];
	parent = new Coordinates[size*size];
	coords = new Coordinates(0,0);
	for(int i=0;i<(size*size);i++) {
	    color[i] = "white";
	    parent[i] = null;
	}

    }
    public void bfs() {
	int i = 0;
	int j = 0;
	color[0] = "grey";
	Q.add(coords);
	while(Q.peek() != null) {
	    coords = Q.remove();
	    i = coords.getX();
	    j = coords.getY();
	    if(topOpen(i,j) && bottomOpen(i-1,j)) {
		if(color[toOneD(i-1,j)] == "white") {
		    color[toOneD(i-1,j)] = "grey";
		    parent[toOneD(i-1,j)] =  new Coordinates(coords.getX(),coords.getY());
		    Coordinates temp = new Coordinates(coords.getX()-1,coords.getY());
		    Q.add(temp);
		}
	    }
	    if(bottomOpen(i,j) && topOpen(i+1,j)) {
                if(color[toOneD(i+1,j)] == "white") {
                    color[toOneD(i+1,j)] = "grey";
                    parent[toOneD(i+1,j)] =  new Coordinates(coords.getX(),coords.getY());
		    Coordinates temp = new Coordinates(coords.getX()+1,coords.getY());
                    Q.add(temp);
                }
            }
            if(leftOpen(i,j) && rightOpen(i,j-1)) {
                if(color[toOneD(i,j-1)] == "white") {
                    color[toOneD(i,j-1)] = "grey";
                    parent[toOneD(i,j-1)] =  new Coordinates(coords.getX(),coords.getY());
		    Coordinates temp = new Coordinates(coords.getX(),coords.getY()-1);
                    Q.add(temp);
                }
            }
            if(rightOpen(i,j) && leftOpen(i,j+1)) {
                if(color[toOneD(i,j+1)] == "white") {
                    color[toOneD(i,j+1)] = "grey";
                    parent[toOneD(i,j+1)] = new Coordinates(coords.getX(),coords.getY());
		    Coordinates temp = new Coordinates(coords.getX(),coords.getY()+1);
                    Q.add(temp);
                }
            }
	    color[toOneD(i,j)] = "black";
	}
	printPath();
    }

    private void printPath() {
	Stack<Coordinates> stk = new Stack<Coordinates>();
	int n = (size*size)-1;
	while(parent[n] != null) {
	    stk.push(parent[n]);
	    n = toOneD(parent[n].getX(),parent[n].getY());
	}
	while(!stk.empty()) {
	    stk.pop().print();
	}
	n = size-1;
	System.out.println("("+n+" ,"+ n+")");

    }



    private boolean topOpen(int i,int j) {
	if ((i < 0 || i >= size) || (j < 0 || j >= size)) {
	  return false;
	}
	char current = maze[i][j];
	if (current <=57 && current >= 48) {
            if(((current-48)&8) != 8) {
                return true;
            }
	}
	if (current <=122 && current >= 97) {
            if(((current-87)&8) != 8) {
                return true;
            }
	}
	if (current<=90 && current >= 65) {
	    if(((current-55)&8) != 8) {
		return true;
	    }
	}
	return false;

    }

    private boolean bottomOpen(int i,int j) {
        if ((i < 0 || i >= size) || (j < 0 || j >= size)) {
	    return false;
        }
        char current = maze[i][j];
        if (current <=57 && current >= 48) {
            if(((current-48)&2) != 2) {
                return true;
            }
        }
        if (current <=122 && current >= 97) {
            if(((current-87)&2) != 2) {
                return true;
            }
        }
        if (current<=90 && current >= 65) {
            if(((current-55)&2) != 2) {
                return true;
            }
        }

        return false;

    }
    private boolean leftOpen(int i,int j) {
        if ((i < 0 || i >= size) || (j < 0 || j >= size)) {
            return false;
        }
        char current = maze[i][j];
        if (current <=57 && current >= 48) {
            if(((current-48)&4) != 4) {
                return true;
            }
        }
        if (current <=122 && current >= 97) {
            if(((current-87)&4) != 4) {
                return true;
            }
        }
        if (current<=90 && current >= 65) {
            if(((current-55)&4) != 4) {
                return true;
            }
        }


        return false;

    }
    private boolean rightOpen(int i,int j) {
        if ((i < 0 || i >= size) || (j < 0 || j >= size)) {
            return false;
        }
        char current = maze[i][j];
        if (current <=57 && current >= 48) {
            if(((current-48)&1) != 1) {
                return true;
            }
        }
        if (current <=122 && current >= 97) {
            if(((current-87)&1) != 1) {
                return true;
            }
        }
        if (current<=90 && current >= 65) {
            if(((current-55)&1) != 1) {
                return true;
            }
        }


        return false;

    }



    
    private int toOneD(int i,int j) {
	return (i*size)+j;

    }
    public void printMaze() {
	for (int i=0;i<size;i++) {
	    for (int j=0;j<size;j++) {
		System.out.print(maze[i][j]);
	    }
	    System.out.println("");
	}
    }
    private int size;
    private char maze [][];
    private String color[];
    private Coordinates parent[];
    private Coordinates coords;
    private Queue<Coordinates> Q = new LinkedList<Coordinates>();
	

}