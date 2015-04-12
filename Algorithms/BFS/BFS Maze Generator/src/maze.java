/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #6 (Maze Generation with Disjoint Sets)
Last Modifyed 4/4
maze.java
maze class, generates random maze
Working/tested
compiles, no errors

Functions:
maze(n): constructor makes maze of size n*n, in starting configuration
generate(): randomly generates the maze object
breakRand(int,int,int,int):'breaks' random wall, first two paramaters take 
numbers 1-4 those wall given wont be broken.
breakTop(i,j): 'breaks' top of given index
breakBottom(i,j): 'breaks bottom of given index
breakLeft(i,j): breaks left of given index
breakRight(i,j): breaks right of given index
print(): prints the maze
*/

import java.util.Random;

class maze {
    private char maze [][];
    private dSet squareSet;
    private final int mazeSize;
    maze(int n) {
	mazeSize = n;
	maze = new char [mazeSize][mazeSize];
	for(int i=0;i<mazeSize;i++) {
	    for (int j=0;j<mazeSize;j++) {
		if (i == 0 && j ==0) {
		    maze[i][j] = 'b';
		}
		else if (i == n-1 && j == n-1) {
		    maze[i][j] = 'e';
		}
		else {
		    maze [i][j] = 'f';
		}
	    }
	}
	squareSet = new dSet(mazeSize);
	for (int i=0;i<(mazeSize*mazeSize);i++) {
	    squareSet.makeSet(i);
	}
    }
    public void generate() {
	for(int i=0;i<mazeSize;i++) {
            for (int j=0;j<mazeSize;j++) {
		if (i == 0 && j == 0) {
		    //top-left corner
		    breakRand(1,3,i,j);
		}
		else if (i == 0 && j == mazeSize-1) {
                    //top-right corner
                    breakRand(1,4,i,j);
                }
		else if (i == 0) {
		    //on top
		    breakRand(1,1,i,j);
		}
		else if (i == mazeSize-1 && j == 0) {
		    //bottom left corner
		    breakRand(2,3,i,j);
		}
		else if (i == mazeSize-1 && j == mazeSize-1) {
		    //bottom right corner
		    int Index1 = (i*mazeSize)+j;
		    if(!squareSet.sameSet(Index1,(i*mazeSize)+(j-1))) {
			breakLeft(i,j);
			breakRight(i,j-1);
			squareSet.union(Index1,mazeSize+(j-1));
		    }
		    if(!squareSet.sameSet(Index1,((i-1)*mazeSize)+j)) {
			breakTop(i,j);
			breakBottom(i-1,j);
			squareSet.union(Index1,((i-1)*mazeSize)+j);
		    }

		}
		else if (i == mazeSize-1) {
		    //on bottom
		    breakRand(2,2,i,j);
		}
		else if(j == 0) {
		    //on left
		    breakRand(3,3,i,j);
		}
		else if(j == mazeSize-1) {
		    //on right
		    breakRand(4,4,i,j);
		}
		else {
		    breakRand(0,0,i,j);
		}
	    }
	}
    }

    private void breakRand(int dontBreak1,int dontBreak2,int i,int j) {
	Random rand = new Random();
	int Index1 = (i*mazeSize)+j;
	int random = rand.nextInt(4)+1;
	while (random == dontBreak1 || random == dontBreak2) {
	    random = rand.nextInt(4)+1;
	}
	//[1 = top, 2 = bottom, 3 = left, 4 = right]
	if (random == 1) {
	    //break top
	    if(squareSet.sameSet(Index1,((i-1)*mazeSize)+j)) {
		breakRand(dontBreak1,dontBreak2,i,j);
	    }
	    else {
		breakTop(i,j);
		breakBottom(i-1,j);
		squareSet.union(Index1,((i-1)*mazeSize)+j);
	    }
	}
	if (random == 2) {
	    //break bottom
	    if(squareSet.sameSet(Index1,((i+1)*mazeSize)+j)) {
		breakRand(dontBreak1,dontBreak2,i,j);
            }
            else {
		breakBottom(i,j);
		breakTop(i+1,j);
		squareSet.union(Index1,((i+1)*mazeSize)+j);
            }
	}
	if (random == 3) {
	    //break left
	    if(squareSet.sameSet(Index1,(i*mazeSize)+(j-1))) {
                breakRand(dontBreak1,dontBreak2,i,j);
            }
            else {
		breakLeft(i,j);
		breakRight(i,j-1);
		squareSet.union(Index1,(i*mazeSize)+(j-1));
            }
	}
	if (random == 4) {
	    //break right
	    if(squareSet.sameSet(Index1,(i*mazeSize)+(j+1))) {
                breakRand(dontBreak1,dontBreak2,i,j);
            }
            else {
		breakRight(i,j);
		breakLeft(i,j+1);
		squareSet.union(Index1,(i*mazeSize)+(j+1));
            }   
	}
    }

    private void breakTop(int i,int j) {
	char current = maze[i][j];
	int number = 0;
        if (current <=57 && current >= 48) {
	    if(((current-48)&8) != 8) {
		return;
	    }
	    number = current-48;
	    number = number-8;
	    if (number <= 9) {
		maze[i][j] = (char)(number+48);
	    }
	    if (number >= 10) {
		maze[i][j] = (char)(number+87);

	    }
        }
        if (current <=122 && current >= 97) {
	    if(((current-87)&8) != 8) {
		return;
	    }
	    number = current-87;
            number = number-8;
            if (number <= 9) {
                maze[i][j] = (char)(number+48);
            }
            if (number >= 10) {
                maze[i][j] = (char)(number+87);
            }
        }
    }

    private void breakBottom(int i,int j) {
	char current = maze[i][j];
        int number = 0;
        if (current <=57 && current >= 48) {
            if(((current-48)&2) != 2) {
                return;
            }
            number = current-48;
            number = number-2;
            if (number <= 9) {
                maze[i][j] = (char)(number+48);
            }
            if (number >= 10) {
                maze[i][j] = (char)(number+87);
            }
        }
        if (current <=122 && current >= 97) {
            if(((current-87)&2) != 2) {
                return;
            }
            number = current-87;
            number = number-2;
            if (number <= 9) {
                maze[i][j] = (char)(number+48);
            }
            if (number >= 10) {
                maze[i][j] = (char)(number+87);
            }
        }
    }

    private void breakRight(int i,int j) {
	char current = maze[i][j];
        int number = 0;
        if (current <=57 && current >= 48) {
            if(((current-48)&1) != 1) {
                return;
            }
            number = current-48;
            number = number-1;
            if (number <= 9) {
                maze[i][j] = (char)(number+48);
            }
            if (number >= 10) {
                maze[i][j] = (char)(number+87);

            }
        }
        if (current <=122 && current >= 97) {
            if(((current-87)&1) != 1) {
                return;
            }
            number = current-87;
            number = number-1;
            if (number <= 9) {
                maze[i][j] = (char)(number+48);
            }
            if (number >= 10) {
                maze[i][j] = (char)(number+87);
            }
        }
    }

    private void breakLeft(int i,int j) {
	char current = maze[i][j];
        int number = 0;
        if (current <=57 && current >= 48) {
            if(((current-48)&4) != 4) {
                return;
            }
            number = current-48;
            number = number-4;
            if (number <= 9) {
                maze[i][j] = (char)(number+48);
            }
            if (number >= 10) {
                maze[i][j] = (char)(number+87);
            }
        }
        if (current <=122 && current >= 97) {
            if(((current-87)&4) != 4) {
                return;
            }
            number = current-87;
            number = number-4;
            if (number <= 9) {
                maze[i][j] = (char)(number+48);
            }
            if (number >= 10) {
                maze[i][j] = (char)(number+87);
            }
        }
    }

    public void print() {
	for(int i=0;i<mazeSize;i++) {
            for (int j=0;j<mazeSize;j++) {
		System.out.print(maze[i][j]);
	    }
	    System.out.println("");
	}	
    }
}