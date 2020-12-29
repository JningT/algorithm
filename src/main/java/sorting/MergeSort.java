import java.util.stream.IntStream;

/**
 * @author nathan
 */
public class MergeSort {

    public void mergeSort(int[] arr){
        mergeSort(arr,0, arr.length - 1, new int[arr.length] );
    }

    private void mergeSort (int[] arr, int start, int end, int[] temp){
        if (start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid, temp);
        mergeSort(arr, mid + 1,end, temp);
        merge(arr, start, end, temp);
    }

    private void merge(int[] arr, int start, int end, int[] temp) {
        int mid = start + (end - start) / 2;
        int leftIndex  = start;
        int rightIndex = mid + 1;
        int idx = start;
        while (leftIndex <= mid && rightIndex <= end){
           if (arr[leftIndex] < arr[rightIndex]){
               temp[idx++] = arr[leftIndex++];
           }else {
               temp[idx++] = arr[rightIndex++];
           }
        }
        while (leftIndex <= mid){
            temp[idx++] = arr[leftIndex++];
        }
        while (rightIndex <= end){
            temp[idx++] = arr[rightIndex++];
        }
        IntStream.rangeClosed(start, end).forEach(i -> arr[i] = temp[i]);
    }


}
