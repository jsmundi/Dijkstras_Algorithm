/*
 * Class: DijkstraPriorityQueue.java
 * Author: Jaiteg Mundi
 * Course: Computer Science 223
 * Dr. Paul Bonamy
 * Washington State University
 * Description:	The class defines the vertex parameters.
 */
package Program4;

/**
 * Vertex Constructor and Initialize empty Priority and Int Vertex.
 * @param <P>
 */
public class Entry<P extends Comparable<P>> {
    public P priority;
    public int vert;
    public Entry(int index, P p)
    {
        vert = index;
        priority = p;
    }
    public int compareTo(Entry toTest) {
        return priority.compareTo((P) toTest.priority);
    }
}
