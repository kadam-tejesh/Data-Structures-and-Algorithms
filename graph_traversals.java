import java.util.*;
import java.util.Queue;
import java.util.Stack;

class adj_matrix{
    ArrayList<ArrayList<Integer>> matrix;
    HashMap<String,Integer> m;
    int vertices;
    boolean isDirected;
    String vertex_name[];
    adj_matrix(int vertices,boolean isDirected,String vertex_names[]){
        this.vertices=vertices;
        this.isDirected=isDirected;
        m= new HashMap<>();
        matrix=new ArrayList<>();
        vertex_name=new String[vertices];
        for(int i=0;i<vertices;i++){
            m.put(vertex_names[i],i);
            vertex_name[i]=vertex_names[i];
        }
        for(int i=0;i<vertices;i++){
            ArrayList<Integer> row=new ArrayList<>();
            for(int j=0;j<vertices;j++){
                row.add(0);
            }
            matrix.add(row);
        }
    }
    public void addedge(String source,String destination){
        int i=m.get(source);
        int j=m.get(destination);
        matrix.get(i).set(j,1);
        if(!isDirected){
            matrix.get(j).set(i,1);
        }
    }
    public void removeedge(String source,String destination){
        int i=m.get(source);
        int j=m.get(destination);
        matrix.get(i).set(j,0);
        if(!isDirected){
            matrix.get(j).set(i,0);
        }
    }
    public void bfs(String start){
        boolean visited[]=new boolean[vertices];
        Queue<String> q=new LinkedList<>();
        visited[m.get(start)]=true;
        q.add(start);
        while(!q.isEmpty()){
            String current=q.poll();
            System.out.print(current+" ");
            int current_index=m.get(current);
            for(int i=0;i<vertices;i++){
                if(matrix.get(current_index).get(i)==1 && !visited[i]){
                    visited[i]=true;
                    q.add(vertex_name[i]);
                }
            }
        }
    }
    public void bfs_sssp(String start){
        Queue<String> q=new LinkedList<>();
        boolean visited[]=new boolean[vertices];
        Map<String,Integer> m1=new HashMap<>();
        q.add(start);
        m1.put(start,0);
        while(!q.isEmpty()){
            String current=q.poll();
            System.out.println(start+"->"+current+"="+m1.get(current));
            int current_index=m.get(current);
            for (int i = 0; i < vertices; i++) {
                if(matrix.get(current_index).get(i)==1 && !visited[i]){
                    visited[i]=true;
                    q.add(vertex_name[i]);
                    m1.put(vertex_name[i],m1.get(current)+1);
                }
            }
        }
    }
    public void dfs(String start){
        boolean visited[]=new boolean[vertices];
        Stack<String> s=new Stack<>();
        s.push(start);
        System.out.println();
        while(!s.isEmpty()) {
            String current = s.pop();
            int current_index = m.get(current);
            if (!visited[current_index]) {
                visited[current_index] = true;
                System.out.print(current + " ");
                for (int i = vertices - 1; i >= 0; i--) {
                    if (matrix.get(current_index).get(i) == 1 && !visited[i]) {
                        s.push(vertex_name[i]);
                    }
                }
            }
        }
    }
    public void topological(){
        boolean visited[]=new boolean[vertices];
        Stack<String> s=new Stack<>();
        for(int i=0;i<vertices;i++) {
            if (!visited[i]) {
                topologicalhelp(i, visited, s);
            }
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }
    public void topologicalhelp(int index,boolean[] visited,Stack<String> s){
        visited[index]=true;
        for(int i=0;i<vertices;i++) {
            if (matrix.get(index).get(i)==1 && !visited[i]){
                topologicalhelp(i,visited,s);
            }
        }
        s.push(vertex_name[index]);
    }
}
public class graph_traversals {
    public static void main(String[] args) {
        String vertex_names[]={"A","B","C","D","E","F","G","H"};
        adj_matrix y=new adj_matrix(8,true,vertex_names);
        y.addedge("B","C");
        y.addedge("A","C");
        y.addedge("C","E");
        y.addedge("E","F");
        y.addedge("E","H");
        y.addedge("B","D");
        y.addedge("D","F");
        y.addedge("F","G");
//        y.bfs("A");
//        y.dfs("A");
        y.topological();
        System.out.println();
        y.bfs_sssp("A");
    }
}
