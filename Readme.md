Priority Queue for Weighted Graphs

For this project you will create a Java class for a Priority Queue tailored for Dijkstra’s Algorithm. The
elements stored in the queue will contain vertices (encoded as integers between 0 and |V | − 1) and a priority
value. The Priority Queue ADT supports the usual insert and deleteMin operations, but we add the
decreaseKey method:

insert(v, p) : insert vertex v with priority p.
deleteMin() : delete and return the vertex with the minimum priority.
decreaseKey(v, p) : lower the priority for vertex v to p.

We use a Binary Heap for the queue, but we also need some way to search for elements in the heap to support
the decreaseKey operation; To do this, we simply store the “heap index” for each vertex in an array as
shown in Figure 1.

Figure 1: Heap and heap index array for priority queue that can hold 12 vertices. We store −1 to indicate
that the associated vertex is not in the queue.
The insert and deleteMin operations are implemented just as they would be for a classic priority queue,
except that extra book keeping is needed to keep the heap-index array up to date (see notes on heaps on class
web site). The decreaseKey operation must first find the vertex in the heap; once found it can “bubble”
the vertex up into its proper location (since it now has a lower priority) as described in Figure 2.

2 DijkstraPriorityQueue<P>
Figure 3 shows the class definition with public method definitions you need to support. The generic typeparameter
P allows the client to create queues using different priority-value types (e.g., Float, Integer).
1
decreaseKey(v, p) {
i = heapIndex[v];
node = heap[i];
while (i > 1 and p < heap[i/2].priority) {
n = heap[i/2];
heap[i] = n;
heapIndex[n.v] = i;
i = i/2;
}
node.priority = p;
heap[i] = node;
heapIndex[v] = i;
}
Figure 2: decreaseKey operation for vertex v with new lower priority p. We assume that each heap node
stores a vertex v and associated priority. This simply “bubbles” the node up to maintain proper heap
order while adjusting each heap index accordingly.
public class DijkstraPriorityQueue<P> {
public DijkstraPriorityQueue(Comparator<P> priorityComparator, int numVerts) {...}
public boolean isEmpty() {...}
public boolean contains(int v) {...}
public void insert(int v, P priority) {...}
public int deleteMin() {...}
public void decreaseKey(int v, P priority) {...}
}
Figure 3: Java class showing the public methods supported by our Queue ADT. The generic type P specifies
the type used to encode the priority (e.g., could use Float’s). The client provides a priorityComparator
object to order vertices in the queue.
The client also provides a Comparator object that is used to control the priority order (thus we can create
a Min or Max-Priority Queue). The test harness I will provide you with use Float’s:
private static class FloatComparator implements Comparator<Float> {
public int compare(Float a, Float b) {
return (int) Math.signum(a - b);
}
}
...
DijkstraPriorityQueue<Float> Q =
new DijkstraPriorityQueue<Float>(new FloatComparator(), numVerts);
3 Dijkstra Implementation and Test Case
I have provided a zip file containing an importable Eclipse project and four source files. DijkstraPriorityQueue.java
contains a skeleton for the priority queue (which you must complete) and a test of the queue implementation.

Dijkstra.java and WeightedGraph.java provide an implementation of Dijkstra’s Algorithm. DijkstraTest.java
uses the graph in Figure 4 and finds the shortest (weighted) path from vertex 20 to 21. The correct output
is:
20, 15, 10, 11, 6, 1, 2, 7, 8, 3, 4, 21,

9
10
8
7
6
11
12
5
4
3
13
2
14
1
0
15
16
17
18
19
Figure 5: Output from main() in DijkstraPriorityQueue.java
