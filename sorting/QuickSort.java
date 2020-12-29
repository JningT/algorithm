/**
 * @author nathan
 */
public class QuickSort {

    public void quickSort(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        int left = start, right = end;
        int pivot = arr[start + (end - start) / 2];
        while (left <= right){
            while (left <= right && arr[left] < pivot){
                left++;
            }

            while (left <= right && arr[right] > pivot){
                right--;
            }
            if (left <= right){
                int temp = arr[left];
                arr[left++] = arr[right];
                arr[right--] = temp;
            }
        }
        quickSort(arr, start, right);
        quickSort(arr, left, end);
    }

    public void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }


}
