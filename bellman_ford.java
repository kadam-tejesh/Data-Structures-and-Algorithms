import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
class edge1{
    String source,destination;
    int weight;
    edge1(String source,String destination,int weight){
        this.source=source;
        this.destination=destination;
        this.weight=weight;
    }
}
class bellman{
    Map<String,List<edge1>> adj_list;
    List<edge1> e;
    bellman(){
        adj_list=new HashMap<>();
        e=new ArrayList<>();
    }
    public void addvertex(String vertex){
        adj_list.putIfAbsent(vertex,new ArrayList<>());
    }
    public void addedge(String source,String destination,int weight){
        adj_list.putIfAbsent(source,new ArrayList<>());
        adj_list.putIfAbsent(destination,new ArrayList<>());
        edge1 x=new edge1(source,destination,weight);
        adj_list.get(source).add(x);
        e.add(x);
    }
    public void bellmanford(String start){
        Map<String,Integer> dist=new HashMap<>();
        Map<String,String> parent=new HashMap<>();
        for(String vertex:adj_list.keySet()){
            dist.put(vertex,Integer.MAX_VALUE);
            parent.put(vertex,null);
        }
        dist.put(start,0);
        int v=adj_list.size();
        for(int i=1;i<=v-1;i++){
            for(edge1 edges:e){
                if(dist.get(edges.source)!=Integer.MAX_VALUE && dist.get(edges.source)+edges.weight< dist.get(edges.destination)){
                    int new_dist=dist.get(edges.source)+edges.weight;
                    dist.put(edges.destination,new_dist);
                    parent.put(edges.destination, edges.source);
                }
            }
        }
        for(edge1 edges:e){
            if(dist.get(edges.source)!=Integer.MAX_VALUE && dist.get(edges.source)+edges.weight< dist.get(edges.destination)){
                System.out.println("negative cycle exists as distance decreases");
                return;
            }
        }
        for(String vertex:dist.keySet()){
            System.out.println(vertex+"->"+dist.get(vertex));
        }
    }
}
public class bellman_ford {
    public static void main(String[] args) {
        bellman x=new bellman();
        x.addvertex("A");
        x.addvertex("B");
        x.addvertex("C");
        x.addvertex("D");
        x.addvertex("E");
        x.addedge("A","C",6);
        x.addedge("A","D",6);
        x.addedge("B","A",3);
        x.addedge("C","D",1);
        x.addedge("D","C",2);
        x.addedge("D","B",1);
        x.addedge("E","B",4);
        x.addedge("E","D",2);
        x.bellmanford("E");


    }
}
