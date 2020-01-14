package gol.log;

import gol.vis.Position;

import java.util.Arrays;
import java.util.Random;

public class Map {
    public static int width;
    public static int height;
    public boolean[][] map;
    private int fields;
    public static Random generator = new Random();
    private Rules rules;

    public Map(int width, int height){
        Map.width=width;
        Map.height=height;
        this.map=new boolean[width][height];
        for(boolean[] row: this.map)
            Arrays.fill(row, false);
        this.fields=0;
        this.rules=new Rules("23/3");
    }

    public int getFields() {
        return fields;
    }

    public void nextStep(Position[][] playground){
        boolean[][] newMap = new boolean[width][height];

        int neighbours;
        Vector2d position;
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                position=new Vector2d(i, j);
                neighbours=numberOfNeighbours(position);
                if(rules.rules[neighbours]==States.DIES)
                    newMap[i][j]=false;
                else if(rules.rules[neighbours]==States.BOTH)
                    newMap[i][j]=true;
                else if(isActive(position) && rules.rules[neighbours]==States.SURVIVES)
                    newMap[i][j]=true;
                else if(!isActive(position) && rules.rules[neighbours]==States.APPEARS)
                    newMap[i][j]=true;
                else
                    newMap[i][j]=false;
                if(newMap[i][j] ^ map[i][j]) {
                    playground[i][j].toggle();
                    map[i][j]=!map[i][j];
                }
            }
        }
        this.map=newMap;
    }

    public void clear(Position[][] playground){
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++) {
                if (map[i][j]) {
//                    map[i][j] = false;
                    playground[i][j].toggle();
                }
            }
        }
    }

    private boolean isActive(Vector2d position){
        return this.map[position.x][position.y];
    }

    private int numberOfNeighbours(Vector2d position){
        int counter=0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                if(i==0 && j==0)
                    continue;
                if(isActive(position.add(new Vector2d(i, j))))
                    counter++;
            }
        }
        return counter;
    }

    public String getRules() {
        return rules.toString();
    }

    public void setRules(String rules){
        this.rules.setRules(rules);
    }
}
