import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
class adjacency_matrix{
    ArrayList<ArrayList<Integer>> adj_matrix;
    int vertices;
    boolean isdirected;
    adjacency_matrix(int vertices,boolean isdirected){
        this.vertices=vertices;
        this.isdirected=isdirected;
        adj_matrix=new ArrayList<>();
        for(int i=0;i<vertices;i++){
            ArrayList<Integer> row=new ArrayList<>();
            for(int j=0;j<vertices;j++){
                row.add(0);
            }
            adj_matrix.add(row);
        }
    }
    public void addedge(int source,int destination){
        adj_matrix.get(source).set(destination,1);
        if(!isdirected){
            adj_matrix.get(destination).set(source,1);
        }
    }
    public void removeedge(int source,int destination){
        adj_matrix.get(source).set(destination,0);
        if(!isdirected){
            adj_matrix.get(destination).set(source,0);
        }
    }
    public void print(){
        for(ArrayList<Integer> row:adj_matrix){
            for(int val:row){
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
    public boolean hashedge(int source,int destination){
        if(adj_matrix.get(source).get(destination)==1){
            return true;
        }
        else{
            return false;
        }
    }
}
class adjacency_list{
    Map<String,ArrayList<String>> adj_list;
    boolean isDirected;
    adjacency_list(boolean isDirected){
        this.isDirected=isDirected;
        adj_list=new HashMap<>();
    }
    public void addvertex(String vertex){
        adj_list.putIfAbsent(vertex,new ArrayList<>());
    }
    public void addedge(String source,String destination){
        adj_list.putIfAbsent(source,new ArrayList<>());
        adj_list.putIfAbsent(destination,new ArrayList<>());
        adj_list.get(source).add(destination);
        if(!isDirected){
            adj_list.get(destination).add(source);
        }
    }
    public void removeedge(String source,String destination){
        if(adj_list.containsKey(source)){
            adj_list.get(source).remove(destination);
            if(!isDirected && adj_list.containsKey(destination)){
                adj_list.get(destination).remove(source);
            }
        }
    }
    public void bfs(String start){
        Set<String> visited=new HashSet<>();
        Queue<String> q=new LinkedList<>();
        visited.add(start);
        q.add(start);
        while(!q.isEmpty()){
            String current=q.poll();
            System.out.print(current+" ");
            for(String neighbour:adj_list.getOrDefault(current,new ArrayList<>()))
            {
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    q.add(neighbour);
                }
            }
        }
    }
    public void bfs_sssp(String start){
        Map<String,Integer> m=new HashMap<>();
        Queue<String> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();
        q.add(start);
        visited.add(start);
        m.put(start,0);
        while(!q.isEmpty()){
            String current=q.poll();
            System.out.println(start+"->"+current+"->"+m.get(current));
            for(String neighbour:adj_list.getOrDefault(current,new ArrayList<>())){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    q.add(neighbour);
                    m.put(neighbour,m.get(current)+1);
                }
            }
        }
    }
    public void print(){
        for(String val:adj_list.keySet()){
            System.out.print(val+"->");
            System.out.println(adj_list.get(val));
        }
    }
    public void dfs(String start){
        Set<String> visited=new HashSet<>();
        Stack<String> s=new Stack<>();
        s.push(start);
        while(!s.isEmpty()){
            String current=s.pop();
            if(!visited.contains(current)){
                visited.add(current);
                System.out.print(current+" ");
                List<String> l=adj_list.getOrDefault(current,new ArrayList<>());
                for(int i=l.size()-1;i>=0;i--){
                    String neighbour=l.get(i);
                    if(!visited.contains(neighbour)){
                        s.push(neighbour);
                    }
                }
            }
        }
    }
    public void topological(){
        Set<String> visited=new HashSet<>();
        Stack<String> s=new Stack<>();
        for(String val:adj_list.keySet()){
            if(!visited.contains(val)){
                topologicalhelper(val,visited,s);
            }
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }
    public void topologicalhelper(String vertex,Set<String> visited,Stack<String> s){
        visited.add(vertex);
        for(String neighbour:adj_list.getOrDefault(vertex,new ArrayList<>())){
            if(!visited.contains(neighbour)){
                topologicalhelper(neighbour,visited,s);
            }
        }
        s.push(vertex);
    }
}
public class graph {
    public static void main(String[] args) {
        adjacency_matrix x=new adjacency_matrix(5,false);
        x.addedge(0,1);
        x.addedge(0,2);
        x.addedge(0,3);

        x.addedge(1,4);

        x.addedge(2,3);

        x.addedge(3,4);

        x.print();


        adjacency_list z=new adjacency_list(false);
        z.addvertex("A");
        z.addvertex("B");
        z.addvertex("C");
        z.addvertex("D");
        z.addvertex("E");
        z.addvertex("F");
        z.addvertex("G");
        z.addedge("A", "B");
        z.addedge("A", "C");
        z.addedge("A", "D");
        z.addedge("B", "D");
        z.addedge("B", "G");
        z.addedge("C", "E");
        z.addedge("C", "D");
        z.addedge("D", "F");
        z.addedge("E", "F");
        z.addedge("F", "G");
        System.out.println("graph:");
//        z.print();
//        z.bfs("A");
//        System.out.println();
//        z.dfs("A");
        z.topological();
        System.out.println();
        z.bfs_sssp("A");
    }
}
