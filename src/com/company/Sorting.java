package com.company;

public class Sorting {

    public void quickSort() {
        int[] a = { 5, 6, 1, 4, 7, 7, 9, 2, 3};
        quicksort(a);

        for(int i : a){
            System.out.print(i + " ");
        }
    }

    public void mergeSort() {
        int[] a = { 5, -6, 1, 4, 7, 7, 9, 2, 3};
        int[] helper = new int[a.length];
        mergeSort(a, helper, 0, a.length - 1);

        for(int i : a){
            System.out.print(i + " ");
        }
    }

    private void quicksort(int[] arr){
        quicksort(arr, 0, arr.length - 1);
    }

    private void quicksort(int[] arr, int low, int high){
        int pivot = arr[(low+high)/2];
        int i = low, j = high;

        while( i<= j){
            while(arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if(i <= j){
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        if(i < high)
                quicksort(arr, i, high);
        if(low < j)
            quicksort(arr, low, j);
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void mergeSort(int[] arr, int[] helper, int low, int high){
        if(low < high){
            int middle = (low + high)/2;
            mergeSort(arr, helper, low, middle);
            mergeSort(arr, helper, middle + 1, high);
            merge(arr, helper, low, middle, high);
        }
    }

    private void merge(int[] arr, int[] helper, int low, int middle, int high){
        for(int i = low; i <= high; i ++){
            helper[i] = arr[i];
        }

        int i = low, j = middle + 1, k = low;

        while(i <= middle && j <= high){
            if(helper[i] <= helper[j]){
                arr[k] = helper[i];
                i++;
            } else {
                arr[k] = helper[j];
                j++;
            }
            k++;
        }
        while( i<= middle){
            arr[k] = helper[i];
            i++;
            k++;
        }
    }

}
