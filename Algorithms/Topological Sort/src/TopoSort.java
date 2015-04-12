
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;



/**
 *
 * @author Kevin
 * 
 * Class for Topological sort function.
 * Contains a vertex class for storing data
 */
public class TopoSort
{
   /**
    * map of topological graph, impliminted as a hashMap.
    */
   Map graph;
   /**
    * Constructor for the graph, takes a file
    * @param filename must be input.txt, is adjacency
    * list for the graph to be based upon
    */
   TopoSort(String filename) {
      this.graph = new HashMap();
      File file = new File(filename);
      try {
         Scanner scanner = new Scanner(file);
         while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //System.out.println(line);
            parseLine(line);
         }
         scanner.close();
      }
      catch(FileNotFoundException e) {
         e.printStackTrace();
      }

   }
   /**
    * public topological sort function that 
    * starts the sort
    */
   public void topologicalSort() {
      computeIndegree();
      Queue<Vertex> q = EnqueueZeroIndegree();
      topologicalSort(q);
   }
   /**
    * main sort fucntion that prints each id in order
    * @param q Queue after indegrees of zero have been
    * enqueued
    */
   private void topologicalSort(Queue<Vertex> q) {
      int counter = 0;
      while(!q.isEmpty()) {
         Vertex v = q.poll();
         
         v.topoNo = ++counter;
         for(int i=0;i<v.edgeList.size();i++) {
            int adjVertexNo = v.edgeList.get(i);
            Vertex adjVertex = (Vertex)graph.get(adjVertexNo);
            adjVertex.indegree--;
            if(adjVertex.indegree == 0) {
               q.add(adjVertex);
            }
            graph.put(adjVertex.id, adjVertex);

         }
         System.out.print(v.id + " ");
      }
      System.out.println("");

      if(counter != graph.size()) {
         System.err.println("Cycle detected");
         System.exit(0);
         
      }
   }
   /**
    * sets indegree of all vertecies in the graph to 
    * zero, it then computes indegree of each vertex from
    * the adjacency list
    */
   private void computeIndegree() {
      for(int v=1;v<graph.size();v++) {
         Vertex temp = (Vertex)graph.get(v);
         temp.indegree = 0;
         graph.put(temp.id, temp);
         
      }
      for(int v=1;v<graph.size();v++) {
         Vertex temp = (Vertex)graph.get(v);
         for(int i=0;i<temp.edgeList.size();i++) {
            int vertex = temp.edgeList.get(i);
            Vertex temp2 = (Vertex)graph.get(vertex);
            temp2.indegree++;
            graph.put(temp2.id, temp2);

         }
         
      }

   }
   /**
    * puts Vertecies with an indegree of zero into a queue
    * @return a Queue with all the indegree vertecies of zero
    */
   private Queue EnqueueZeroIndegree() {
      Queue<Vertex> q = new LinkedList<>();
      for(int v=1;v<graph.size();v++) {
        Vertex temp = (Vertex)graph.get(v);
        if(temp.indegree == 0) {
           q.add(temp);
        }
      }
      return q;
      
   }
   /**
    * parses a single line of numbers that represent and
    * adjacency list, fire number is Vertex id, following 
    * are adjacent to the first id.
    * @param line the line of integers seperated by spaces to 
    * be parsed
    */
   private void parseLine(String line) {
      Scanner scanner = new Scanner(line);
      //ignore empty line
      if(!scanner.hasNext() ) {
         return;
      }
      int vertex = scanner.nextInt();
      Vertex newVertex = new Vertex(vertex);
      
      while(scanner.hasNext()) {
         //add vertex to adjacency list of current vertex
         int temp = scanner.nextInt();
         newVertex.edgeList.add(temp);
      }
      scanner.close();
      graph.put(newVertex.id, newVertex);
      
      
   }
   /**
    * vertex class that stores the id, topoNo, and indegree of each vertex
    * as well as their adjecency list.
    */
   private static class Vertex {
      private int id;
      private int topoNo;
      private int indegree;
      private List<Integer> edgeList;
      
      Vertex(int id) {
         this.id = id;
         edgeList = new LinkedList<>();
      }
      
            
   }
   
   
      
   /**
    * entry point for testing
    * @param args no args used
    */
   public static void main(String [] args) {
      TopoSort myGraph = new TopoSort("input.txt");
      myGraph.topologicalSort();

   }
}
