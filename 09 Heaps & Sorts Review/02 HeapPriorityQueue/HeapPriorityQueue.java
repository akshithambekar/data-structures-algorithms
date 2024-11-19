//Name: Akshith Ambekar
//Date: 3/24/2022

import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> {
   private ArrayList<E> myHeap;

   public HeapPriorityQueue() {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }

   public ArrayList<E> getHeap() // for Codepost
   {
      return myHeap;
   }

   public int lastIndex() {
      return myHeap.size() - 1;
   }

   public boolean isEmpty() {
      return myHeap.size() == 1;
   }

   public boolean add(E obj) {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }

   public E remove() {
      int i = lastIndex();
      swap(1, i);
      E obj = myHeap.remove(i);
      heapDown(1, i - 1);
      return obj;
   }

   public E peek() {
      if (isEmpty()) {
         return null;
      }
      return myHeap.get(1);
   }

   // it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k) {
      int parent = k / 2;
      if (parent == 0) {
         return;
      }
      if (myHeap.get(parent).compareTo(myHeap.get(k)) > 0) {
         swap(parent, k);
         heapUp(parent);
      }
   }

   private void swap(int a, int b) {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }

   // it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex) {
      int left = 2 * k;
      int right = 2 * k + 1;
      if (left > lastIndex) {
         return;
      }
      if (left == lastIndex) {
         if (myHeap.get(k).compareTo(myHeap.get(left)) > 0) {
            swap(k, left);
         }
      } else {
         int maxChild;
         if (myHeap.get(left).compareTo(myHeap.get(right)) < 0) {
            maxChild = left;
         } else {
            maxChild = right;
         }
         if (myHeap.get(k).compareTo(myHeap.get(maxChild)) > 0) {
            swap(k, maxChild);
            heapDown(maxChild, lastIndex);
         }
      }
   }

   public String toString() {
      return myHeap.toString();
   }
}
