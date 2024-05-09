package model;

import java.util.Objects;

public class Position {

    /**
     * the first position of the piece
     */
    private int x;

    /**
     * the second position of the piece
     */
    private int y;

    /**
     * constructor of the class
     * 
     * @param x the first position of the piece
     * @param y the second position of the piece
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method that returns the first position of the piece
     */
    public int getX() {
        return x;
    }

    /**
     * Method that returns the second position of the piece
     */
    public int getY() {
        return y;
    }

    /**
     * Method that sets the first position of the piece
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Method that sets the second position of the piece
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }
}
