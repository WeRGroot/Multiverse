package com.shivam.snl;

public class Board {

    private int size;
    private int start;
    private int end;

    public Board(int size) {
        this.size = size;
        start = 1;
        end = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
