import java.util.Arrays;

class Bs1{
    public static void sort(int a[]){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<(a.length-i-1);j++){
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
    }
    public void printing(int a[]){
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
public class bubblesort {
    public static void main(String[] args) {
        Bs1 x=new Bs1();
        int a[]={2,1,2,1};
        x.printing(a);
    }
}
