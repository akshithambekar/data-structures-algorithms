// Name:   
// Date:

import java.util.*;

import javax.print.attribute.HashAttributeSet;

import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface {
   public String getName();

   public HashSet<Vertex> getAdjacencies();

   /*
    * postcondition: if the set already contains a vertex with the same name, the
    * vertex v is not added
    * this method should be O(1)
    */
   public void addAdjacent(Vertex v);

   /*
    * postcondition: returns as a string one vertex with its adjacencies, without
    * commas.
    * for example, D [C A]
    */
   public String toString();

}

/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> // 2 vertexes are equal if and only if they have the same
                                                            // name
{
   private final String name;
   private HashSet<Vertex> adjacencies;

   /* enter your code here */
   public Vertex(String n) {
      name = n;
      adjacencies = new HashSet<Vertex>();
   }

   public String getName() {
      return name;
   }

   public HashSet<Vertex> getAdjacencies() {
      return adjacencies;
   }

   /*
    * postcondition: if the set already contains a vertex with the same name, the
    * vertex v is not added
    * this method should be O(1)
    */
   public void addAdjacent(Vertex v) {
      if (!adjacencies.contains(v))
         adjacencies.add(v);

   }

   /*
    * postcondition: returns as a string one vertex with its adjacencies, without
    * commas.
    * for example, D [C A]
    */
   public String toString() {
      String toRet = name + " [";
      if (adjacencies.isEmpty())
         return toRet + "]";
      for (Vertex v : (new TreeSet<Vertex>(adjacencies)))
         toRet += v.getName() + " ";

      return toRet.substring(0, toRet.length() - 1) + "]";
   }

   public int compareTo(Vertex other) {
      return name.compareTo(other.getName());
   }

   public int hashCode() {
      return name.hashCode();
   }
}

/*************************************************************/
interface AdjListInterface {
   public Set<Vertex> getVertices();

   public Vertex getVertex(String vName);

   public Map<String, Vertex> getVertexMap(); // this is just for codepost testing

   /*
    * postcondition: if a Vertex with the name v exists, then the map is unchanged.
    * addVertex should work in O(log n)
    */
   public void addVertex(String vName);

   /*
    * precondition: both Vertexes, source and target, are already stored in the
    * graph.
    * postcondition: addEdge should work in O(1)
    */
   public void addEdge(String source, String target);

   /*
    * returns the whole graph as one string, e.g.:
    * A [C]
    * B [A]
    * C [C D]
    * D [C A]
    */
   public String toString();

}

/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS {
   public List<Vertex> depthFirstSearch(String name);

   public List<Vertex> breadthFirstSearch(String name);

   /* extra credit methods */
   public List<Vertex> depthFirstRecur(String name);

   public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities {
   public void readData(String cities, String edges) throws FileNotFoundException;

   public int edgeCount();

   public int vertexCount();

   public boolean isReachable(String source, String target);

   public boolean isStronglyConnected(); // return true if every vertex is reachable from every
                                         // other vertex, otherwise false
}

/************* start the Adjacency-List graph *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   // we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();

   /* constructor is not needed because of the instantiation above */

   /* enter your code here */

   public Set<Vertex> getVertices() {
      Set<Vertex> vertices = new HashSet<Vertex>();
      for (String s : vertexMap.keySet())
         vertices.add(vertexMap.get(s));
      return vertices;

   }

   public Vertex getVertex(String vName) {
      return vertexMap.get(vName);
   }

   public Map<String, Vertex> getVertexMap() {
      return vertexMap;
   } // this is just for codepost testing

   /*
    * postcondition: if a Vertex with the name v exists, then the map is unchanged.
    * addVertex should work in O(log n)
    */
   public void addVertex(String vName) {

      if (!vertexMap.containsKey(vName))
         vertexMap.put(vName, new Vertex(vName));

   }

   /*
    * precondition: both Vertexes, source and target, are already stored in the
    * graph.
    * postcondition: addEdge should work in O(1)
    */
   public void addEdge(String source, String target) {
      Vertex t = vertexMap.get(target);
      getVertex(source).addAdjacent(t);

   }

   /*
    * returns the whole graph as one string, e.g.:
    * A [C]
    * B [A]
    * C [C D]
    * D [A C]
    */
   public String toString() {
      String toRet = "";
      for (String s : vertexMap.keySet())
         toRet += vertexMap.get(s).toString() + "\n";
      return toRet;
   }

   public int hashCode() {
      return toString().hashCode();
   }

   public List<Vertex> depthFirstSearch(String name) {
      List<Vertex> reachableVertices = new ArrayList<Vertex>();
      Stack<Vertex> stk = new Stack<Vertex>();
      stk.push(getVertex(name));
      while (!stk.isEmpty()) {
         Vertex pop = stk.pop();
         if (!reachableVertices.contains(pop)) {
            reachableVertices.add(pop);
         }

         for (Vertex x : pop.getAdjacencies()) {
            if (!reachableVertices.contains(x)) {
               stk.push(x);
            }
         }
      }
      return reachableVertices;
      
   }

   public List<Vertex> breadthFirstSearch(String name) {
      List<Vertex> reachableVertices = new ArrayList<Vertex>();
      Queue<Vertex> stk = new LinkedList<Vertex>();
      stk.add(getVertex(name));
      while (!stk.isEmpty()) {
         Vertex pop = stk.remove();
         if (!reachableVertices.contains(pop)) {
            reachableVertices.add(pop);
         }

         for (Vertex x : pop.getAdjacencies()) {
            if (!reachableVertices.contains(x)) {
               stk.add(x);
            }
         }
      }
      return reachableVertices;
   }

   public List<Vertex> depthFirstRecur(String name) {
      return null;
   }

   public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable) {
      return null;
   }

   public void readData(String cities, String edges) throws FileNotFoundException {
      Scanner cityScanner = new Scanner(new File(cities));
      while (cityScanner.hasNext()) {
         String city = cityScanner.next();
         vertexMap.put(city, new Vertex(city));
      }

      Scanner edgeScanner = new Scanner(new File(edges));
      while (edgeScanner.hasNext()) {
         String from = edgeScanner.next();
         String to = edgeScanner.next();
         addVertex(from);
         addVertex(to);
         addEdge(from, to);
      }
   }

   public int edgeCount() {
      int count = 0; 
      for (String name : getVertexMap().keySet()) {
         count += getVertex(name).getAdjacencies().size();
      }
      return count;
   }

   public int vertexCount() {
      return vertexMap.size();
   }

   public boolean isReachable(String source, String target) {
      List<Vertex> reachable = depthFirstSearch(source);
      for (Vertex v : reachable) {
         if (v.getName().equals(target)) {
            return true;
         }
      }
      return false;
   }

   public boolean isStronglyConnected() { // return true if every vertex is reachable from every other vertex, otherwise false
      for (String v1 : getVertexMap().keySet()) {
         for (String v2 : getVertexMap().keySet()) {
            if (!isReachable(v1, v2)) {
               return false;
            }
         }
      }
      return true;
   }                

}