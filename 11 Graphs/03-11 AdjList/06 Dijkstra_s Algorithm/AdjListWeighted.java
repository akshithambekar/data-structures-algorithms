// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor>
{
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d) {
      target = t;
      edgeDistance = d;
   }
   
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   //.........
   public int compareTo(Neighbor other) {
      return target.compareTo(other.target);
   }

   public double getEdgeDistance() {
      return edgeDistance;
   }

   public wVertex getTarget() {
      return target;
   }

   public boolean equals(Object obj) {
      if (obj instanceof wVertex) {
         return equals((wVertex) obj);
      }
      return false;
   }

   public int hashCode() {
      return target.hashCode();
   }
   public String toString()
   {
      return target.getName() + " " + edgeDistance;  
   }
}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijkstra 7
//compareTo is using the distanceToVertex to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToVertex; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToVertex = d;
   }
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToVertex;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToVertex = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToVertex - other.distanceToVertex);
   }
   
   public wVertex getPrevious()  //Dijkstra 7
   {
      return this.previous;
   }
   public void setPrevious(wVertex v) //Dijkstra 7
   {
      this.previous = v;
   } 
   
   //implement toString to match the sample output   
   public String toString()
   { 
      String toReturn = "";
      toReturn += vertex.getName() + " ";
      return toReturn;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface 
{
   public String getName();
   
   public Set<Neighbor> getNeighbors();
   
   /*  adds to the neighbors set  
       called at the beginning of the lab*/
   public void addAdjacent(wVertex v, double d); 
     
    /*returns an arraylist of PQelements that store distanceToVertex to another wVertex  */
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
   
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double d); 
 
   public String toString();  
 
}

class wVertex implements Comparable<wVertex>, wVertexInterface 
{ 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */ 
   public wVertex(String n) {
      name = n;
      neighbors = new TreeSet<Neighbor>();
      alDistanceToVertex = new ArrayList<PQelement>();
   }

   public String getName() {
      return name;
   }

   public Set<Neighbor> getNeighbors() {
      return neighbors;
   }

   public void addAdjacent(wVertex v, double d) {
      neighbors.add(new Neighbor(v, d));
   }

   public ArrayList<PQelement> getAlDistanceToVertex() {
      return alDistanceToVertex;
   }

   public PQelement getPQelement(wVertex v) {
      for (PQelement p : alDistanceToVertex) {
         if (p.getVertex().equals(v)) {
            return p;
         }
      }
      return null;
   }

   public Double getDistanceToVertex(wVertex v) {
      if (getPQelement(v) == null) {
         return null;
      }
      return getPQelement(v).getDistanceToVertex();
   }

   public void setDistanceToVertex(wVertex v, double d) {
      if (getPQelement(v) == null) {
         alDistanceToVertex.add(new PQelement(v, d));
      } else {
         getPQelement(v).setDistanceToVertex(d);
      }
   }
   
   
   /* 2 vertexes are equal if and only if they have the same name
      add all methods needed for a HashSet and TreeSet to function with Neighbor objects
      use only target, not distances, since a vertex can't have 2 neighbors that have the same target   
   */
   
   public int compareTo(wVertex other) {
      return name.compareTo(other.name);
   }

   public boolean equals(wVertex v) {
      return name.equals(v.name);
   }

   public boolean equals(Object obj) {
      if (obj instanceof wVertex) {
         return equals((wVertex) obj);
      }
      return false;
   }

   public int hashCode() {
      return name.hashCode();
   }
   
   
   public String toString()
   { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " List: " + alDistanceToVertex; 
      return toReturn;
   }
}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface 
{
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities 
{       
   public List<String> getShortestPathTo(wVertex vSource, wVertex target);
   public void readData(String vertexNames, String edgeListData) ;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface//,AdjListWeightedInterfaceWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   
   /* default constructor -- not needed!  */
  
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   
   
   public String toString()
   {
      String strResult = "";
      for(String vName: vertexMap.keySet())
      {
         strResult += vertexMap.get(vName) + "\n"; 
      }
      return strResult;
   }
   
   /*  Graphs 7 has two more methods */
   public List<String> getShortestPathTo(wVertex target) 
   {
      return null;
   }  
     
   public void readData(String vertexNames, String edgeListData) 
   {
     /* use a try-catch  */
   
   }

   public Set<wVertex> getVertices() {
      Set<wVertex> vertices = new TreeSet<wVertex>();
      for (String s : vertexMap.keySet()) {
         vertices.add(vertexMap.get(s));
      }
      return vertices;
   } 
   public Map<String, wVertex> getVertexMap() {
      return vertexMap;
   }  //this is just for codepost testing

   public wVertex getVertex(String vName) {
      return vertexMap.get(vName);
   }
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName) {
      if (!vertexMap.containsKey(vName)) {
         vertexMap.put(vName, new wVertex(vName));
      }
   }
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d) {
      getVertex(source).addAdjacent(getVertex(target), d);
   }
   public void minimumWeightPath(String vertexName) {
      PriorityQueue<PQelement> pq = new PriorityQueue<PQelement>();
      PQelement source = new PQelement(getVertex(vertexName), 0.0);
      for (String vName : vertexMap.keySet()) {
         if (!vName.equals(vertexName)) {
            source.getVertex().getAlDistanceToVertex().add(new PQelement(getVertex(vName), wVertex.NODISTANCE));
         } else {
            source.getVertex().getAlDistanceToVertex().add(source);
         }
      }
      pq.add(source);
      while (!pq.isEmpty()) {
         PQelement dq = pq.remove();
         for (Neighbor n : dq.getVertex().getNeighbors()) {
            double newCost = dq.getDistanceToVertex() + n.getEdgeDistance();
            double oldCost = source.getVertex().getDistanceToVertex(n.getTarget());
            if (newCost < oldCost) {
               pq.add(new PQelement(n.getTarget(), newCost));
               source.getVertex().setDistanceToVertex(n.getTarget(), newCost);
            }
         }
      }
   }  

}