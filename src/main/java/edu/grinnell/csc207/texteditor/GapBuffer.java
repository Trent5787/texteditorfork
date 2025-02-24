package edu.grinnell.csc207.texteditor;

import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private char[] Str;
    private int start;
    private int end;
    
    public GapBuffer(int initSize) {
        this.Str = new char[initSize];
        this.start = 0;
        this.end = initSize - 1;
    }
    
    public void doubleSize() {
        char[] newStr = new char[getSize() * 2];

        // Copy left part
        for (int i = 0; i < start; i++) {
            newStr[i] = Str[i];
        }

        // Copy right part (after gap)
        for (int j = end; j < Str.length; j++) {
            newStr[j + newStr.length / 2] = Str[j];
        }

        end += getSize() / 2;
        Str = newStr;
    }

    public void insert(char ch) {
        if (start == end) {
            doubleSize();
        }
        Str[start++] = ch;
    }

    public void delete() {
        if (start > 0) {
            start--;
        }
    }

    public int getCursorPosition() {
        return start;
    }

    public void moveLeft() {
        if (start > 0) {
            start--;
            end--;
        }
    }

     public void moveRight() {
        if(start >= Str.length-1) {
            //Cursor at end of buffer
            return;
        }   
        Str[start] = Str[end];
        start++;
        end++;
    }

    
    public int getSize() {
        return Str.length;
    }

    public char getChar(int i) {
        if (i >= Str.length || (i >= start && i < end)) {
            throw new IllegalArgumentException("Index out of bounds or in the gap");
        }
        return Str[i];
    }

    public String toString() {
        return Arrays.toString(Str);
    }
}
