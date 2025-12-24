import java.util.Arrays;

public class quicksort {
    public static void quick(int a[],int first,int last){
        int pivot,i,j;
        if(first<last) {
            pivot = first;
            i = first;
            j = last;
            while (i < j) {
                while (a[i] <= a[pivot] && j<last) {
                    i++;
                }
                while (a[j] > a[pivot]) {
                    j--;
                }
                if (i < j) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
            int t = a[pivot];
            a[pivot] = a[j];
            a[j] = t;

            quick(a, first, j - 1);
            quick(a, j + 1, last);
        }
    }
    public static void print(int a[]){
        System.out.println(Arrays.toString(a));
        quick(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        int a[]={9,4,2,7,1,3};
        print(a);
    }
}
