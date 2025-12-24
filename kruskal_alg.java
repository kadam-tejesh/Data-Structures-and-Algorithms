import java.util.*;
class Edge2{
    String source,destination;
    int weight;
    Edge2(String source,String destination,int weight){
        this.source=source;
        this.destination=destination;
        this.weight=weight;
    }
    public String toString(){
        return source + " - " + destination + " : " + weight;
    }
}
class Disjointset{
    Map<String,String> parent=new HashMap<>();
    Map<String,Integer> rank=new HashMap<>();
    public void makeset(String item){
        parent.put(item,item);
        rank.put(item,0);
    }
    public String find(String item){
        if(!parent.get(item).equals(item)){
            parent.put(item,find(parent.get(item)));
        }
        return parent.get(item);
    }
    public void union(String x,String y){
        String rootx=find(x);
        String rooty=find(y);
        if(rootx.equals(rooty)){
            return;
        }
        int rankx=rank.get(rootx);
        int ranky=rank.get(rooty);
        if(rankx<ranky){
            parent.put(rootx,rooty);
        }
        else if(rankx>ranky){
            parent.put(rooty,rootx);
        }
        else{
            parent.put(rooty,rootx);
            rank.put(rootx,rankx+1);
        }
    }
}
public class kruskal_alg {
    Map<String,List<Edge2>> adjacency_list;
    List<Edge2> edge_list;
    kruskal_alg(){
        adjacency_list=new HashMap<>();
        edge_list=new ArrayList<>();
    }
    public void addvertex(String vertex){
        adjacency_list.putIfAbsent(vertex,new ArrayList<>());
    }
    public void addedge(String source,String destination,int weight){
        adjacency_list.putIfAbsent(source,new ArrayList<>());
        adjacency_list.putIfAbsent(destination,new ArrayList<>());
        Edge2 x=new Edge2(source,destination,weight);
        adjacency_list.get(source).add(x);
        adjacency_list.get(destination).add(new Edge2(destination, source, weight));
        edge_list.add(x);
    }
    public void kruskal(){
        edge_list.sort(Comparator.comparingInt(e -> e.weight));
        Disjointset ds=new Disjointset();
        for(String v:adjacency_list.keySet()){
            ds.makeset(v);
        }
        List<Edge2> mst=new ArrayList<>();
        int total_weight=0;
        for(Edge2 e:edge_list){
            String root1=ds.find(e.source);
            String root2=ds.find(e.destination);
            if(!root1.equals(root2)){
                mst.add(e);
                total_weight+=e.weight;
                ds.union(root1,root2);
            }
        }
        System.out.println("Minimum spanning tree:");
        for(Edge2 e:mst){
            System.out.println(e);
        }
        System.out.println("total weight:"+total_weight);
    }

    public static void main(String[] args) {
    kruskal_alg g=new kruskal_alg();
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
    g.kruskal();
    }

}
