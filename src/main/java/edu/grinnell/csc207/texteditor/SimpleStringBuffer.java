package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    StringBuffer SimpleStringBuffer = new StringBuffer();
    int cursor = 0;
    
    /**
     * Inserts the specified character into the string buffer.
     * @param ch The character to insert into the buffer
     */
    public void insert(char ch) {
        SimpleStringBuffer.insert(getCursorPosition(),ch);
        cursor++;
    }

    /**
     * Deletes the value at the current cursor position, then decrements the 
     * cursor by one; Returns null if the string buffer is empty.
     */
    public void delete() {
        if (cursor == 0)
            return;
        SimpleStringBuffer.delete(getCursorPosition()-1,getCursorPosition());
        cursor--;
    }

    /**
     * Returns the current cursor position.
     * @return the current cursor position.
     */
    public int getCursorPosition() {
        return(cursor);
    }

    /**
     * Decrements the cursor by one; unless the cursor is at the start, then
     * nothing happens.
     */
    public void moveLeft() {
        if (cursor == 0)
            return;
        cursor--;
    }

    /**
     * Increments the cursor by one; unless the cursor is at the end, then
     * nothing happens.
     */
    public void moveRight() {
        if (cursor == getSize())
            return;
        cursor++;
    }

    /**
     * Returns the size of the string buffer.
     * @return The length (size) of the string buffer.
     */
    public int getSize() {
        return(SimpleStringBuffer.length());
    }

    /**
     * Gets a char from a string buffer at the index, i.
     * @param i The index to get a char from.
     * @return The character at the index, i.
     */
    public char getChar(int i) {
        return(SimpleStringBuffer.charAt(i));
    }

    /**
     * Converts a stringbuffer to a string and returns it.
     * @return The string version of the stringbuffer.
     */
    @Override
    public String toString() {
       return(SimpleStringBuffer.toString());
    }
}
