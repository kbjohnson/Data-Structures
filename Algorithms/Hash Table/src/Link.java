/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #5 (Hash table)
Last Modifyed 4/20
List.java
this file contains the List class
Working/tested
compiles, no errors

Functions:
printNode():prints the record in this node
getKey(): returns the key value of this nodes record
getData(): returns the string data in this nodes record
getRecord(); returns the record in this node
*/

public class Link {
    public Link next;
    public Link prev;
    private Record data;

    public Link(Record myRecord) {
        data = myRecord;
	next = null;
	prev = null;
    }
    public void printNode() {
	data.printRecord();
    }
    public int getKey() {
	return data.getKey();
    }
    public String getData() {
	return data.getData();
    }
    public Record getRecord() {
	return data;
    }



}
