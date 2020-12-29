package main.java.searching;

import java.util.concurrent.*;

/**
 * @author nathan
 */
public class BinarySearch {

    /**
    * @Description: Binary search in sorted array, using two pointers method.
     * Set the left and right pointers to find the middle position,
     * and judge whether the middle value is equal to target nums[mid] == target,
     * then return the position index
     * nums[mid] > target, then the right pointer moves to the middle
     * nums[mid] < target then the left pointer moves to the middle
    * @Param: [arr, target]
    * @return: int
    * @Date: 2020/9/29
     * time complexity O(log2n)
    * space complexity O(1)
    */

    boolean found = false;
    public int binarySearchNum(int[] arr, int target)  {
        if (arr == null || arr.length == 0){
            return Integer.MIN_VALUE;
        }
        int left = 0, right = arr.length - 1;
        while (left <= right ){
            int mid = left + (right - left) / 2;
            if (arr[mid] == target){
                return mid;
            }
            else if (arr[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return Integer.MIN_VALUE;
    }



    /**
    * @Description:  binary search number in sorted array,using recursion method.
    * @Param: [arr, target]
    * @return: int
    * @Date: 2020/9/29
     * time complexity O(log2n)
     * space complexity O(log2n)
    */
    public int binarySearchNumRecursion(int[] arr, int target)
    {
        if (arr == null || arr.length == 0){
            return Integer.MIN_VALUE;
        }
        return binarySearchNumRecursion(arr,0, arr.length - 1, target);
    }

    /**
    * @Description: Set the left and right pointers to find the middle position,
     * if target greater than middle value, the target should at the left interval, then traverse left interval,
     * otherwise the target at the right interval
    * @Param: [arr, left, right, target]
    * @return: int
    * @Date: 2020/9/29

    */
    public int binarySearchNumRecursion(int[] arr, int left, int right, int target)   {
        if (left > right){
           return Integer.MIN_VALUE;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target && !found){
            found = true;
            return mid;
        }
        return arr[mid] > target ? binarySearchNumRecursion(arr, left,mid - 1, target) : binarySearchNumRecursion(arr,mid + 1, right, target);
    }

    public static class BinarySearchMutiThreading extends RecursiveTask<Integer> {
        int target;
        int[] arr;
        int left, right;
        boolean isFound;

        public BinarySearchMutiThreading(int target, int[] arr){
            this.target = target;
            this.arr =  arr;
            this.left = 0;
            this.right = arr.length - 1;
        }

        public BinarySearchMutiThreading(int target, int[] arr, int left, int right){
            this.target = target;
            this.arr =  arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected Integer compute() {
            if (left > right){
                return Integer.MIN_VALUE;
            }
            int mid = left + (right - left) / 2;
            if (arr[mid] == target){
                isFound = true;
                return mid;
            }

                BinarySearchMutiThreading leftPart = new BinarySearchMutiThreading(target,arr, left, mid - 1);
                BinarySearchMutiThreading rightPart = new BinarySearchMutiThreading(target,arr, mid + 1, right);
                leftPart.fork();
                rightPart.compute();
                leftPart.join();
            return mid;
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[] {-435, -300, -240, -200, -100, -42, -5, 0, 1, 2, 5, 10, 123, 234};

        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println(pool.invoke(new BinarySearchMutiThreading(0, arr)));
    }
}

