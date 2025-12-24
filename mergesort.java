import java.util.Arrays;

public class mergesort {
    public static void merge(int a[],int left,int mid,int right){
        int leftarray[]=new int[mid-left+2];
        int rightarray[]=new int[right-mid+1];
        for(int i=0;i<=mid-left;i++){
            leftarray[i]=a[left+i];
        }
        for(int i=0;i<right-mid;i++){
            rightarray[i]=a[mid+i+1];
        }
        int i=0,j=0;
        leftarray[mid-left+1]=Integer.MAX_VALUE;
        rightarray[right-mid]=Integer.MAX_VALUE;
        for(int k=left;k<=right;k++){
            if(leftarray[i]<rightarray[j]){
                a[k]=leftarray[i];
                i++;
            }
            else{
                a[k]=rightarray[j];
                j++;
            }
        }
    }
    public static void mergesort(int a[],int left,int right){
        if(right>left){
            int mid=(left+right)/2;
            mergesort(a,left,mid);
            mergesort(a,mid+1,right);
            merge(a,left,mid,right);
        }
    }
    public static void print(int a[]){
        System.out.println(Arrays.toString(a));
        mergesort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        int a[]={2,1,4,3};
        print(a);
    }
}
