/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #8 (Breadth-First Search)
Last Modifyed 5/25
Coordinates.java
Class to hold coordinates
Working/some testing
compiles, no errors

Functions:
setX(x2): sets x to x2
sety(y2): sets y to y2
getX(): returns x
getY(): returns y
print(): prints coordinates on a line
*/




class Coordinates {
    private int x;
    private int y;

    Coordinates(int x2,int y2) {
	x = x2;
	y = y2;
	
    }

    public void setX(int x2) {
	x = x2;
    }
    public void setY(int y2) {
	y = y2;
    }
    public int getX() {
	return x;
    }
    public int getY() {
	return y;
    }
    public void print() {
	System.out.println("("+x+ " ,"+y+")");
    }
    


}