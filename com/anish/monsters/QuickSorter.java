package com.anish.monsters;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[][] elements;
    private String plan = "";

    @Override
    public void load(T[][] a) {
        this.elements = a;
    }

    @Override
    public void sort() {
        int n = elements.length * elements[0].length;
        quickSort(0, n - 1);
    }

    private void quickSort(int begin, int end) {
        if (begin >= end) {
            return;
        }

        int p = partition(begin, end);
        quickSort(begin, p - 1);
        quickSort(p + 1, end);
    }

    private int partition(int begin, int end) {
        T pivot = elements[end%16][end/16];
        int i = begin;
        for (int j = begin; j < end; ++j) {
            if (elements[j%16][j/16].compareTo(pivot) < 0) {
                if (i == j) {
                    ++i;
                } else {
                    swap(i, j);
                    ++i;
                }
            }
        }
        swap(i, end);
        return i;
    }

    public void swap(int i, int j) {
        T temp = elements[i%16][i/16];
        elements[i%16][i/16] = elements[j%16][j/16];
        elements[j%16][j/16] = temp;
        plan += "" + elements[i%16][i/16] + "<->" + elements[j%16][j/16] + "\n";
    }

    @Override
    public String getPlan() {
        return this.plan;
    }
}
