package com.anish.monsters;

public interface Sorter<T extends Comparable<T>> {
    public void load(T[][] a);

    public void sort();

    public String getPlan();
}
