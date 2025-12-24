import java.util.*;
class Node1{
    int distance;
    String vertex;
    Node1(String vertex,int distance){
        this.distance=distance;
        this.vertex=vertex;
    }
}
class Edges{
    String source,destination;
    int weight;
    Edges(String source,String destination, int weight){
        this.source=source;
        this.destination=destination;
        this.weight=weight;
    }
}
class Graphs{
    Map<String,List<Edges>> adj_list;
    Graphs(){
        adj_list=new HashMap<>();
    }
    public void addvertex(String vertex){
        adj_list.putIfAbsent(vertex,new ArrayList<>());
    }
    public void addedge(String source,String destination,int weight){
        adj_list.putIfAbsent(source,new ArrayList<>());
        adj_list.putIfAbsent(destination,new ArrayList<>());
        adj_list.get(source).add(new Edges(source,destination,weight));
        adj_list.get(destination).add(new Edges(destination,source,weight));
    }
    public void prims(String start){
        Map<String,Integer> dist=new HashMap<>();
        Map<String,String> parent=new HashMap<>();
        Set<String> mst=new HashSet<>();
        for(String v:adj_list.keySet()){
            parent.put(v,null);
            dist.put(v,Integer.MAX_VALUE);
        }
        dist.put(start,0);
        PriorityQueue<Node1> pq=new PriorityQueue<>(Comparator.comparingInt(n->n.distance));
        pq.offer(new Node1(start,0));
        while(!pq.isEmpty()){
            Node1 current_node=pq.poll();
            String u=current_node.vertex;
            if(mst.contains(u)) continue;
            mst.add(u);
            for(Edges e:adj_list.get(u)){
                    String v=e.destination;
                    int weight=e.weight;
                    if(!mst.contains(v) && weight<dist.get(v)){
                        parent.put(v,u);
                        dist.put(v,weight);
                        pq.offer(new Node1(v,dist.get(v)));
                    }
            }
        }
        int total_weight=0;
        System.out.println("Mst:"+mst);
        for(String v:adj_list.keySet()){
            if(parent.get(v)!=null){
                total_weight+=dist.get(v);
            }
        }
        System.out.println(total_weight);

    }

}
public class prims_alg {
    public static void main(String[] args) {
        Graphs g=new Graphs();
        g.addvertex("A");
        g.addvertex("B");
        g.addvertex("C");
        g.addvertex("D");
        g.addvertex("E");


        g.addedge("A", "B", 5);
        g.addedge("A", "C", 13);
        g.addedge("A", "E", 15);
        g.addedge("B", "C", 10);
        g.addedge("B", "D", 8);
        g.addedge("C", "E", 20);
        g.addedge("C","D",6);
        g.prims("A");
    }
}
