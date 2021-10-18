package com.anish.monsters;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[] a;
    private String plan = "";

    @Override
    public void load(T[] a) {
        this.a = a;
    }

    @Override
    public void sort() {
        int n = a.length;
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
        T pivot = a[end];
        int i = begin;
        for (int j = begin; j < end; ++j) {
            if (a[j].compareTo(pivot) < 0) {
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
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    @Override
    public String getPlan() {
        return this.plan;
    }
}
