/*
 * Class: DijkstraPriorityQueue.java
 * Author: Jaiteg Mundi
 * Course: Computer Science 223
 * Dr. Paul Bonamy
 * Washington State University
 */
package Program4;

import java.util.Iterator;

public interface WeightedGraph {
    public class VertexWeight {
        public final int i;
        public final float weight;
        public VertexWeight(int v, float w) {i=v; weight=w;}
    }
    int numVerts();
    Iterator<VertexWeight> adjacents(int v);
}