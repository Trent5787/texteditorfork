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
    public GapBuffer() { 
        this.initSize = 32; 
        this.str = new char[initSize];
        this.start = 0; 
        this.end = initSize - 1;
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
        for (int j = end + 1; j < str.length; j++) {
            newStr[j + newStr.length / 2] = str[j];
        }

        end += str.length;
        str = newStr;
    }

    /**
     * Inserts the specified character into the buffer
     * @param ch the character to insert
     */
    public void insert(char ch) {
        if (start == end) {
            doubleSize();
        }
        str[start++] = ch;

    }

    /**
     * Deletes a character from the buffer.
     */
    public void delete() {
        if (start > 0) {
            start--;
            str[start] = 0;
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
            str[start] = 0;
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
    
    public int getSize2() {
        return str.length;
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
        
        if (i >= start && i <= end) {
            //if(i < str.length - 1) {
            //i++;
            throw new IllegalArgumentException("Index in the gap");
          //  }
        }
        System.out.println("The character we are getting:" + str[i]);
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
