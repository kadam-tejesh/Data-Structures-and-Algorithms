import java.util.*;
class warshall{
    int vertices;
    ArrayList<ArrayList<Integer>> matrix;
    HashMap<String,Integer> m;
    String[] vertex_name;
   final int infinite=Integer.MAX_VALUE;
    warshall(int vertices,String[] vertex_names){
        this.vertices=vertices;
        matrix=new ArrayList<>();
        m=new HashMap<>();
        vertex_name=new String[vertices];
        for(int i=0;i<vertices;i++){
            m.put(vertex_names[i],i);
            vertex_name[i]=vertex_names[i];
        }
        for(int i=0;i<vertices;i++){
            ArrayList<Integer> row=new ArrayList<>();
            for(int j=0;j<vertices;j++){
                if(i==j) row.add(0);
                else row.add(infinite);
            }
            matrix.add(row);
        }
    }
    public void addedge(String source,String destination,int weight){
        int i=m.get(source);
        int j=m.get(destination);
        matrix.get(i).set(j,weight);
    }
    public void floyds(){
        int[][] dist=new int[vertices][vertices];
        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){
                dist[i][j]=matrix.get(i).get(j);
            }
        }
        for(int k=0;k<vertices;k++){
            for(int i=0;i<vertices;i++){
                for(int j=0;j<vertices;j++){
                    if(dist[i][k]+dist[k][j]<dist[i][j] && dist[i][k]!=infinite && dist[k][j]!=infinite){
                        dist[i][j]=dist[i][k]+dist[k][j];
                    }
                }
            }
        }
        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }
    }
}public class floyd_warshall {
    public static void main(String[] args) {
        String[] a={"A","B","C","D"};
        warshall x=new warshall(4,a);
        x.addedge("A","B",8);
        x.addedge("A","D",1);
        x.addedge("B","C",1);
        x.addedge("C","A",4);
        x.addedge("D","B",2);
        x.addedge("D","C",9);
        x.floyds();
    }
}
