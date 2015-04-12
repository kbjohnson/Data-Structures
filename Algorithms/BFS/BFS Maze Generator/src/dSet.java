/*
Kevin Johnson
kbjohnson831@yahoo.com
kjohnson
Assignment #6 (Maze Generation with Disjoint Sets)
Last Modifyed 4/4
dSet.java
disjoint set class
Working/tested
compiles, no errors

Functions:
makeSet(x):makes the index of paramater its own set
union(x,y): makes the two indexes the same set
sameSet(x,y); checks if paramaters are in the same set
find(x): finds given paramater
getNumbSets(): retuns number of sets in the disjoint set
print(): prints the set, rank & parent
*/



class dSet {
    private int [] rank;
    private int [] parent;
    private int size;
    private int numSets;
    dSet(int n) {
	numSets = n*n;
	size = (n*n);
	rank = new int[size];
	parent = new int[size];
	
    }
    public void makeSet(int x) {
	if(x >= size || x < 0) {
	    return;
	}
	parent[x] = x;
	rank[x] = 0;
    }
    public void union(int x,int y) {
	if(x >= size || y >= size || x < 0 || y < 0) {
	    return;
	}
	link(find(x),find(y));
	numSets--;
    }
    public boolean sameSet(int x,int y) {
	if (x >= size || y >= size || x < 0 || y < 0) {
	    return false;
	}

	return (find(x) == find(y));
    }
    public int find(int x) {
	if (x >= size || x < 0) {
	    return 0;
	    }
	if (x != parent[x]){
	    parent[x] = find(parent[x]);
	}
	return parent[x];
    }
    private void link(int x,int y) {
	if (x >= size || y >= size || x < 0 || y < 0) {
	    return;
	}
	if (x == y) {
	    return;
	}
	if (rank[x] > rank[y]) {
	    parent[y] = x;
	}
	else {
	    parent[x] = y;
	    if (rank[x] == rank[y]) {
		rank[y]++;
	    }
	}
    }
    public int getNumSets() {
	return numSets;
    }
    public void print() {
	System.out.print("Parent: [");
	for(int i=0;i<size;i++) {
	    System.out.print(parent[i] + " ");
	}
	System.out.println("]");
	System.out.print("Rank:   [");
        for(int i=0;i<size;i++) {
            System.out.print(rank[i] + " ");
        }
        System.out.println("]");

    }


}