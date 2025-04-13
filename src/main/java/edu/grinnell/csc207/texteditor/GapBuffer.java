package edu.grinnell.csc207.texteditor;
/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private char[] Str;
    private int start; //start and end represent the start and end of the entire gapbuffer, including the text and the actual gap
    private int end;
    private int initSize;
    
    public GapBuffer() {
        this.initSize = 32; 
        this.Str = new char[initSize];
        this.start = 0; //if the gap buffer is everything, including text not in the gap, how do we know where the gap starts and ends?
        this.end = initSize - 1;
    }
    
    /**
     * Gets the start position
     * @return start
     */
    public int getStart() {
        return(start);
    }
    
    /**
     * Gets the end position
     * @return end
     */
    public int getEnd() {
        return(end);
    }
    
    /**
     * Doubles the size of the buffer
     */
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

    /**
     * Inserts the specified character into the buffer
     * @param ch the character to insert
     */
    public void insert(char ch) {
        if (start == end) {
            doubleSize();
            return;
        }
        Str[start++] = ch;
    }

    /**
     * Deletes a character from the buffer.
     */
    public void delete() {
        if (start > 0) {
            start--;
        }
    }

    /**
     * Gets the position where the cursor is at
     * @return the start position (which is where the cursor is)
     */
    public int getCursorPosition() {
        return start;
    }

    /**
     * Moves the cursor left
     */
    public void moveLeft() { //
        if (start > 0) {
            Str[end]=Str[start-1];
            start--;
            end--;
        }
    }

    /**
     * Moves the cursor right
     */
    public void moveRight() {
        if(end >= Str.length-1) {
            //Cursor at end of buffer
            return;
        }   
        Str[start] = Str[end+1];
        start++;
        end++;
    }

    /**
     * Gets the size of the buffer
     * @return the size of the buffer
     */
    public int getSize() {
        return Str.length;
    }

    /**
     * Grabs the char at the specified index
     * @param i the index to get the char at
     * @return the char at that index
     */
    public char getChar(int i) {
        if (i >= Str.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (i >= start && i < end) {
            throw new IllegalArgumentException("Index inside the gap");
        }
        return Str[i];
    }

    /**
     * Converts the buffer to a string
     * @return the string version of the buffer
     */
    public String toString() {
        String string = "";
        String strChar;
        char ch;

        for (int i = 0; i < Str.length; i++) {
            if (Str[i] != (char) 0) {
                ch = Str[i];
                strChar = Character.toString(ch);
                string = string.concat(strChar);
            }
        }
        return string;
    } 
}
