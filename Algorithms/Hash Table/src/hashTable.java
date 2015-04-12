/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #5 (Hash Table)
Last Modifyed 4/20
hashTable.java
contains hash table of linkedLists that store Records
Working/tested
compiles, no errors

Functions:
hashTable(): default construcor makes hashTable of size 100
hashTable(int):makes hashTable of specified size
insert(Record);insert Record into the hashTable
delete(int):delete Record corosponding to first found record with that key
deleteTable():delete all elements from the table
print():prints hashtable
search(int);returns a copy of the first found record w/ input key
save(String);save hash table to input filename
hash(Record):hashes index for given Record
*/



import java.util.ArrayList;
import java.io.*;
class hashTable {
    public hashTable() {
	m = 100;
	table = new LinkedList[m];
        for (int i=0;i<m;i++) {
            table[i] = new LinkedList();
        }
    }
    public hashTable(int size) {
	m = size;
	table = new LinkedList[m];
        for (int i=0;i<m;i++) {
            table[i] = new LinkedList();
        }
    }

    public void insert(Record input) {
	int hashIndex = hash(input);
	table[hashIndex].insertRear(input);
    }
    
    public void delete(int key) {
	boolean deleted = false;
	for(int i=0;(i<m)&&(deleted==false);i++) {
	    deleted = table[i].removeNode(key);
	}
	if (deleted == false) {
	    System.out.println("Could not find key to delete");
	}
    }
    
    public void deleteTable() {
	for(int i=0;i<m;i++) {
	    while(!table[i].isEmpty()) {
		table[i].removeFront();
	    }
	}
    }

    public void print() {
	for(int i=0;i<m;i++) {
	    System.out.println("");
	    table[i].printList();
	}

    }
    public Record search(int key) {
	Record copy = null;
	
	for (int i=0;(i<m)&&(copy==null);i++) {
	    copy = table[i].find(key);
	}
	return copy;
    }

    public void save(String filename) {
	
	File clear = new File(filename);
	if(clear.exists()) {
	    try{
		clear.delete();
		clear.createNewFile();
	    }
	    catch(IOException e) {
	    }
	}
	for(int i=0;i<m;i++) {
	    //save individual lists
	    table[i].saveList(filename);
	}
    }

    private int hash(Record record) {
	double CxKey = C*record.getKey();
	return (int)Math.floor((CxKey-Math.floor(CxKey))*m);
    }
    private LinkedList table[];
    private int m;
    final double C = .6180033887;
}

