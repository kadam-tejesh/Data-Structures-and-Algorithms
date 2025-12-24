import java.util.*;
import java.util.Queue;

class Node{
    String vertex;
    int distance;
    Node(String vertex,int distance){
        this.vertex=vertex;
        this.distance=distance;
    }
}
class Edge{
    String destination;
    int weight;
    Edge(String destination,int weight){
        this.destination=destination;
        this.weight=weight;
    }
}
class weighted_graph{
    Map<String,List<Edge>> adjacency_list;
    weighted_graph(){
        adjacency_list=new HashMap<>();
    }
    public void adddvertex(String vertex){
        adjacency_list.putIfAbsent(vertex,new ArrayList<>());
    }
    public void addedge(String source,String destination,int weight){
        adjacency_list.putIfAbsent(source,new ArrayList<>());
        adjacency_list.putIfAbsent(destination,new ArrayList<>());
        adjacency_list.get(source).add(new Edge(destination,weight));
    }
    public void dijkstra(String start){
        Map<String,Integer> dist=new HashMap<>();
        Map<String,String> parent=new HashMap<>();
        for(String vertex:adjacency_list.keySet()){
            dist.put(vertex,Integer.MAX_VALUE);
            parent.put(vertex,null);
        }
        dist.put(start,0);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        pq.add(new Node(start,0));
        while(!pq.isEmpty()){
            Node current=pq.poll();
            String u=current.vertex;
            for(Edge neighbour:adjacency_list.getOrDefault(u,new ArrayList<>())){
                int new_dist=dist.get(u)+neighbour.weight;
                if(new_dist<dist.get(neighbour.destination)){
                    dist.put(neighbour.destination,new_dist);
                    parent.put(neighbour.destination,u);
                    pq.add(new Node(neighbour.destination, new_dist));
                }
            }
        }
        for(String vetex:dist.keySet()){
            System.out.println(start+"->"+vetex+"="+dist.get(vetex));
        }
        for(String vertex:parent.keySet()){
            System.out.println(vertex+"->"+parent.get(vertex));
        }
    }
}
public class dijkstra_alg {
    public static void main(String[] args) {
        weighted_graph x=new weighted_graph();
        x.adddvertex("A");
        x.adddvertex("B");
        x.adddvertex("C");
        x.adddvertex("D");
        x.adddvertex("E");
        x.addedge("A","B",10);
        x.addedge("A","C",3);
        x.addedge("B","C",3);
        x.addedge("C","B",4);
        x.addedge("B","D",2);
        x.addedge("C","E",2);
        x.addedge("C","D",8);
        x.addedge("D","E",7);
        x.addedge("E","D",9);
        x.dijkstra("A");
    }
}
