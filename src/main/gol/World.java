package gol;

//import gol.vis.T1;
import gol.log.Map;
import gol.log.Vector2d;
import gol.vis.Visualisation;

public class World {
    public static void main(String[] arg){
        System.out.println("działa");
        Map map = new Map(50,50);
        Visualisation vis=new Visualisation(map);
        vis.visualise();
//        T1.run();

    }
}
