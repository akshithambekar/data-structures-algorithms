// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public String toString();  //returns the grid as a String
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability();   // Warshall's Algorithm
   public List<String> getReachables(String from);  //Warshall extension
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  write constructor, accessor method, and instance methods here  */  
   public AdjMat(int numVertices) {
      grid = new int[numVertices][numVertices];
      vertices = new TreeMap<>();
      nameList = new ArrayList<>(); 
   }

   public int[][] getGrid() {
      return grid;
   }

   public void addEdge(int source, int target) {
      grid[source][target] = 1;
   }

   public void removeEdge(int source, int target) {
      grid[source][target] = 0;
   }
   
   public boolean isEdge(int from, int to) {
      if (grid[from][to] != 9999) {
         return true;
      }
      return false;
   }

   public String toString() {
      String str = "";
      for (int r = 0; r < grid.length; r++) {
         for (int c = 0; c < grid[0].length; c++) {
            str += grid[r][c] + " ";
         }
         str += "\n";
      }
      return str;
   }

   public int edgeCount() {
      int count = 0;
      for (int r = 0; r < grid.length; r++) {
         for (int c = 0; c < grid[0].length; c++) {
            if (grid[r][c] != 9999 && grid[r][c] != 0) {
               count++;
            }
         }
      }
      return count;
   }

   public List<Integer> getNeighbors(int source) {
      List<Integer> neighbors = new ArrayList<Integer>();
      for (int i = 0; i < grid.length; i++) {
         if (grid[source][i] == 1) {
            neighbors.add(i);
         }
      }
      return neighbors;
   }

   public boolean isEdge(String from, String to) { // overloaded method for Warshall interface
      return isEdge(vertices.get(from), vertices.get(to));
   }

   public Map<String, Integer> getVertices() {
      return vertices;
   }

   public void readNames(String fileName) throws FileNotFoundException {
      Scanner infile = new Scanner(new File(fileName));
      int max = infile.nextInt();
      for (int i = 0; i < max; i++) {
         nameList.add(infile.next());
         vertices.put(nameList.get(i), i);
      }
   }

   public void readGrid(String fileName) throws FileNotFoundException {
      Scanner infile = new Scanner(new File(fileName));
      int size = infile.nextInt();
      for (int r = 0; r < size; r++) {
         for (int c = 0; c < size; c++) {
            grid[r][c] = infile.nextInt();
         }
      }
   }

   public void displayVertices() {
      for (int i = 0; i < nameList.size(); i++) {
         System.out.println(i + "-" + nameList.get(i));
      }
   }

   public void allPairsReachability() {
      for (int b = 0; b < grid.length; b++) {
         for (int f = 0; f < grid.length; f++) {
            for (int t = 0; t < grid.length; t++) {
               if (isEdge(f, b) && isEdge(b, t)) {
                  addEdge(f, t);
               }
            }
         }
      }
   }

   public List<String> getReachables(String from) {
      return null;
   }

   public int getCost(int from, int to) {
      return grid[from][to];
   }

   public int getCost(String from, String to) {
      return getCost(vertices.get(from), vertices.get(to));
   }

   public void allPairsWeighted() { // use getCost
      for (int b = 0; b < grid.length; b++) {
         for (int f = 0; f < grid.length; f++) {
            for (int t = 0; t < grid.length; t++) {
               int cost = getCost(f, b) + getCost(b, t);
               if (cost < getCost(f, t)) {
                  grid[f][t] = cost;
               }
            }
         }
      }
   }

}
