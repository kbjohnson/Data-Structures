/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #5 (Hash Table)
Last Modifyed 4/20
p5.java
main file
Working/tested
compiles, no errors

Functions:

*/



import java.util.Scanner;
import java.io.*;

class p5 {
    public static void main(String [] args) {
	hashTable myHash = new hashTable(178000);
	Scanner scan = new Scanner(System.in);
	int input = 0;
	int key;
	String data = null;

	while (input != 7) {
	    System.out.println("1.) Insert data from file\n2.) Insert record from keyboard\n3.) Delete key\n4.) Search for key\n5.) Clear hash table\n6.) Save table to disk\n7.) Quit");
	    input = scan.nextInt();
	    if (input == 1) {
		//insert from file
		String filename = null;
		System.out.println("Input file name to read from: ");
		filename = scan.next();
		try {
		    Scanner scanf = null;
		    scanf = new Scanner(new BufferedReader(new FileReader(filename)));
		    while (scanf.hasNext()) {
			key = scanf.nextInt();
			data = scanf.nextLine();
			Record newRecord = new Record(key,data);
			myHash.insert(newRecord);
		    }
		    scanf.close();
		}
		catch(FileNotFoundException e) {
		    System.out.println("Could not find file to open");
		}
		catch(NullPointerException ex) {
		    System.out.println("Could not find file to open");
		}
	    }
	    else if (input == 2) {
		//insert record from keyboard, single line
		System.out.println("Enter record on one line,*key* *data*");
		key = scan.nextInt();
		data = scan.nextLine();
		Record newRecord = new Record(key,data);
		myHash.insert(newRecord);
		
	    
		
	    }
	    else if (input == 3) {
		//delete key
		System.out.print("Input key to delete: ");
		key = scan.nextInt();
		myHash.delete(key);
	    }
	    else if (input == 4) {
		//search for input key value
		System.out.print("Input key to search for: ");
		key = scan.nextInt();
		try {
		Record record = myHash.search(key);
		System.out.println(record.getKey()+ " "+ record.getData());
		}
		catch(NullPointerException e) {
		    System.out.println("Could not find matching key");
		}
	    }
	    else if (input == 5) {
		//clear table
		myHash.deleteTable();
	    }
	    else if (input == 6) {
		//save hashtable to disk
		String filename = null;
		System.out.println("Input filename to save to:");
		filename = scan.next();
		myHash.save(filename);
	    }
	    else if (input == 7) {
		//quit;
	    }
	    else {
		System.out.println("Select a valid option(1-7)");
	    }
	}
	scan.close();
    }
}