import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
class bucket{
    public static void sort(int a[],int n){
        if(n<=0)
            return ;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(a[i]>max){
                max=a[i];
            }
        }
        long no_of_buckets=Math.round(Math.sqrt(n));
        ArrayList<Integer> buckets[]=new ArrayList[(int)no_of_buckets];
        for(int i=0;i<no_of_buckets;i++){
            buckets[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<n;i++){
            int bucket_index=(int)((a[i]*no_of_buckets)/(max+1));
            buckets[bucket_index].add(a[i]);
        }
        for(int i=0;i<no_of_buckets;i++){
            Collections.sort(buckets[i]);
        }
        int index=0;
        for(int i=0;i<no_of_buckets;i++){
            for(int value:buckets[i]){
                a[index++]=value;
            }
        }
    }
    public void print(int a[]){
        System.out.println(Arrays.toString(a));
        sort(a,a.length);
        System.out.println(Arrays.toString(a));
    }
}
public class bucketsort{
    public static void main(String[] args) {
        bucket x=new bucket();
        int a[]={2,1,4,3};
        x.print(a);
    }
}