package Ordenamientos;

import ModeloArreglo.HistorialClinico;
import java.util.Comparator;

public class MergeSort {

    public static void mergeSort(HistorialClinico[] array, int left, int right, Comparator<HistorialClinico> comparator) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle, comparator);
            mergeSort(array, middle + 1, right, comparator);
            merge(array, left, middle, right, comparator);
        }
    }

    private static void merge(HistorialClinico[] array, int left, int middle, int right, Comparator<HistorialClinico> comparator) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        HistorialClinico[] leftArray = new HistorialClinico[n1];
        HistorialClinico[] rightArray = new HistorialClinico[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
