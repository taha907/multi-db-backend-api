package com.gamermatch.common;

import java.util.ArrayList;
import java.util.List;

public class PagedList<T> {

    private List<T> items = new ArrayList<>();
    private int total;

    public PagedList() {
    }

    public PagedList(List<T> items) {
        this.items = items;
        this.total = items.size();
    }

    public List<T> getItems() { return items; }
    public int getTotal() { return total; }
}
