package edu.grinnell.csc207.texteditor;
/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private char[] str;
    private int start; 
    private int end;
    private int initSize;
    
    /**
     * GapBuffer constructor
     */
    public GapBuffer() { //NOTE: Changes made here
        this.initSize = 0; 
        this.str = new char[initSize];
        this.start = 0; 
        this.end = 0;
    }
    
    /**
     * Gets the start position
     * @return start
     */
    public int getStart() {
        return (start);
    }
    
    /**
     * Gets the end position
     * @return end
     */
    public int getEnd() {
        return (end);
    }
    
    public void incrementSize() { //NOTE: new method
        char[] newStr = new char[1];
        end++;
        str = newStr;
    }
    
    /**
     * Doubles the size of the buffer
     */
    public void doubleSize() {
        char[] newStr = new char[str.length * 2];

        // Copy left part
        for (int i = 0; i < start; i++) {
            newStr[i] = str[i];
        }

        // Copy right part (after gap)
        for (int j = end; j < str.length; j++) {
            newStr[j + newStr.length / 2] = str[j];
        }

        end += str.length / 2;
        str = newStr;
    }

    /**
     * Inserts the specified character into the buffer
     * @param ch the character to insert
     */
    public void insert(char ch) { //NOTE: made changes here
        if (start == 0 && end == 0){
            incrementSize(); //because we can't double zero
            System.out.println("Incrementing size");
            str[start++] = ch;
            System.out.println(ch);
            return;
        } else if (start == end) {
            doubleSize();
            System.out.println("Doubling size");
            str[start] = ch; //somehow this isn't working
            //System.out.println(ch);
            return;
        }
        str[start++] = ch;
        System.out.println(ch);
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
    public void moveLeft() { 
        if (start > 0) {
            str[end] = str[start - 1];
            start--;
            end--;
        }
    }

    /**
     * Moves the cursor right
     */
    public void moveRight() {
        if (end >= str.length - 1) {
            //Cursor at end of buffer
            return;
        }   
        str[start] = str[end + 1];
        start++;
        end++;
    }

    /**
     * Gets the size of the buffer
     * @return the size of the buffer
     */
    public int getSize() {
        return (str.length - (end - start + 1));
    }

    /**
     * Grabs the char at the specified index
     * @param i the index to get the char at
     * @return the char at that index
     */
    public char getChar(int i) {
        if (i >= str.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (i >= start && i < end) {
            throw new IllegalArgumentException("Index inside the gap");
        }
        return str[i];
    }

    /**
     * Converts the buffer to a string
     * @return the string version of the buffer
     */
    public String toString() {
        String string = "";
        String strChar;
        char ch;

        for (int i = 0; i < str.length; i++) {
            if (str[i] != (char) 0) {
                ch = str[i];
                strChar = Character.toString(ch);
                string = string.concat(strChar);
            }
        }
        return string;
    } 
}
