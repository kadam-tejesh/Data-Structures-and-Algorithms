import java.util.Arrays;
public class insertionsort {
    public static void sort(int a[],int n){
        for(int i=1;i<n;i++){
            int t=a[i];
            int j=i-1;
            while(j>=0 && t>a[j]){ // a[j]>t ascending order
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=t;
        }
    }
    public static void print(int a[]){
        System.out.println(Arrays.toString(a));
        sort(a,a.length);
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        int a[]={2,1,4,3};
        print(a);
    }
}
