import java.util.Arrays;

class ss{
    public static  void sort(int a[]){
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i]>a[j]){
                    int temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
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
public class selectionsort {
    public static void main(String[] args) {
        ss x=new ss();
        int a[]={2,1,3,5,4};
        x.printing(a);
    }
}
