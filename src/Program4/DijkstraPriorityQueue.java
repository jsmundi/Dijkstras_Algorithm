/*
 * Class: DijkstraPriorityQueue.java
 * Author: Jaiteg Mundi
 * Course: Computer Science 223
 * Dr. Paul Bonamy
 * Washington State University
 *
 * Description:	This class implements the Binary Minimum Heap Priority Queue
 * to hold the vertices and priority for the Dijkstra's Algorithm.
 *
 * The program returns valid outputs.
 */

package Program4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DijkstraPriorityQueue<P extends Comparable<P>> {

    private ArrayList<Entry<P>> heap;
    private ArrayList<Integer> hIndex;

    public DijkstraPriorityQueue(Comparator<P> priorityComparator, int numVerts) {
        heap = new ArrayList<Entry<P>>(numVerts);
        hIndex = new ArrayList<Integer>(Collections.nCopies(numVerts, -1));
    }

    /**
     * Add new entry to the Heap
     * @param v Entry
     * @param priority Key Priority
     */
    public void insert(int v, P priority) {

        heap.add(new Entry<P>(v, priority));
        hIndex.set(v, new Integer(heap.size()-1));
        swimUp(v);
    }

    /**
     *  Removes and returns a smallest key on this heap priority queue.
     * @return
     */
    public int deleteMin() {

        Entry<P> root = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);

        hIndex.set(root.vert, -1);
        if (heap.size() > 0)
        {
            hIndex.set(heap.get(0).vert, 0);
            swimDown(0);
        }


        return root.vert;
    }

    /**
     * New key has lower value; hence update the value and decrease key priority
     * @param v Vertex
     * @param priority Priority of the Key
     */
    public void decreaseKey(int v, P priority) {

        heap.set(hIndex.get(v), new Entry<P>(v, priority));
        while( (heap.get(0).vert != v) && heap.get(hIndex.get(v)).compareTo(heap.get( (hIndex.get(v)-1)/2 )) < 0 )
        {
            swimUp(v);
        }
    }

    /**
     *  Returns true if the heap is empty.
     * @return
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     *  Return the Vertex
     * @param v Vertex
     * @return The Vertex in the heap at current index
     */
    public boolean contains(int v) {

        return !(hIndex.get(v).equals(new Integer(-1)) );
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    /**
     * Swapping the newly added vertex with its parent,
     * until the parent is at least as large or root is reached.
     * @param v Vertex Index
     */
    public void swimUp(int v) {
        while( (heap.get(0).vert != v) && heap.get(hIndex.get(v)).compareTo(heap.get( (hIndex.get(v)-1)/2 )) < 0 ) {

            Entry<P> tempV = heap.get(hIndex.get(v));
            int parentIndex = (hIndex.get(v) - 1) / 2;
            int vIndex = hIndex.get(v);

            Entry<P> vParent = heap.get(parentIndex);
            heap.set(parentIndex, tempV);
            heap.set(vIndex, vParent);
            hIndex.set(tempV.vert, new Integer(parentIndex));
            hIndex.set(vParent.vert, new Integer(vIndex));
        }
    }

    /**
     * Swim down the vertex at root to its correct position in the min .
     * Find the index of the smaller child amongst the left child and right child if it exists.
     * Check if the parent is greater than the child and swap.
     * Update the parent index to its latest position
     * @param v Location in of the key in the heap
     */
    public void swimDown(int v)
    {
        Entry<P> node = heap.get(v);
        while( heap.size()-1 >= (hIndex.get(node.vert)*2+1) && ( node.compareTo(heap.get(hIndex.get(node.vert)*2+1)) > 0  ||
                heap.size()-1 >= ((hIndex.get(node.vert)+1)*2) && node.compareTo(heap.get((hIndex.get(node.vert)+1)*2)) > 0 ) )
        {
            int c = 0;
            if (heap.size()-1 >= ((hIndex.get(node.vert)+1)*2))
            {
                c = heap.get((hIndex.get(node.vert)+1)*2).compareTo(heap.get((hIndex.get(node.vert)*2+1)));
                if (c < 0)
                {
                    int x = node.compareTo(heap.get((hIndex.get(node.vert)+1)*2));
                    if (x > 0)
                    {
                        int nodeIndex = hIndex.get(node.vert);
                        int secondChildIndex = (nodeIndex+1)*2;

                        Entry<P> child = heap.get(secondChildIndex);
                        heap.set(nodeIndex, child);
                        heap.set(secondChildIndex, node);
                        hIndex.set(node.vert, secondChildIndex);
                        hIndex.set(child.vert, nodeIndex);
                        v = secondChildIndex;
                    }
                }
            }
            if (c >= 0 )
            {
                int x = node.compareTo(heap.get((hIndex.get(node.vert)*2+1)));
                if (x > 0 )
                {
                    int nodeIndex = hIndex.get(node.vert);
                    int firstChildIndex = (nodeIndex*2)+1;

                    Entry<P> child = heap.get(firstChildIndex);
                    heap.set(nodeIndex, child);
                    heap.set(firstChildIndex, node);
                    hIndex.set(node.vert, firstChildIndex);
                    hIndex.set(child.vert, nodeIndex);
                    v = firstChildIndex;
                }
            }
            node = heap.get(v);
        }
    }

    /****************************************************************************/


    /*
     * Unit test.
     */
    private static class IntComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        final int n = 20;
        DijkstraPriorityQueue<Integer> Q =
                new DijkstraPriorityQueue<Integer>(new IntComparator(), n);
        for (int i = 0; i < 20; i++)
            Q.insert(i, (i - 10) * (i - 10) + i);
        for (int i = 10; i < 20; i++)
            Q.decreaseKey(i, (i - 5) * (i - 5));
        while (!Q.isEmpty()) {
            int v = Q.deleteMin();
            System.out.println(v);
        }
    }
}
