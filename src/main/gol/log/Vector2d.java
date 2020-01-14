package gol.log;

import static java.lang.Math.abs;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d((other.x + this.x + Map.width)%(Map.width), (other.y + this.y + Map.height)%(Map.height));
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d((this.x - other.x + Map.width)%(Map.width), (this.y - other.y + Map.height)%(Map.height));
    }

    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return ((this.x == that.x) && (this.y == that.y));
    }

    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    public Vector2d randInRange(Vector2d other){
        int x, y;
        if(this.x==other.x)
            x=this.x;
        else
            x=this.x+Map.generator.nextInt(abs(other.x-this.x+1));
        if(this.y==other.y)
            y=this.y;
        else
            y=this.y+Map.generator.nextInt(abs(other.y-this.y+1));
        return new Vector2d(x, y);
    }


    @Override
    public int hashCode(){
        int hash=0;
        hash += this.x * 17 + this.y * 107;
        return hash;
    }
}
