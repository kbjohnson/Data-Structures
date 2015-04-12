/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #5 (Hash Table)
Last Modifyed 4/20
Record.java
Record class for storing Records of a key and a string
Working/tested
compiles, no errors

Functions:
Record(): default constructor 
Record(): overloaded constructor
getKey(): returns the value of the key
getData(): returns the value of the string(data)
clone(): retuns a pointer to a copyed Record
printRecord(): prints a records values
*/

class Record {
    Record() {
	key = 0;
	data = null;

    }
    Record(int inKey,String inData) {
	key = inKey;
	data = inData;
    }
    public int getKey() {
	return key;
    }
    public String getData() {
	return data;
    }
    public Record clone() {
	Record clone = new Record(this.getKey(),this.getData());
	return clone;
    }
    public void printRecord() {
	System.out.print("[Key]:"+getKey()+" "+"[String]"+getData()+" ,");
    }
    private int key;
    private String data;

}