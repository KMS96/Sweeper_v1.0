package sweeper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mikhail on 21.03.2018.
 */
public class Ranges {
    static private Coord size;
    static private ArrayList<Coord> allCoords; // список координат
    static private Random random = new Random();

    public static Coord getSize() {
        return size;
    }

    static void setSize(Coord size) {
        Ranges.size = size;

        allCoords = new ArrayList<Coord>();
        for (int x = 0; x < size.x; x++)
            for (int y = 0; y < size.y; y++)
                allCoords.add(new Coord(x, y));
    }

    static public ArrayList<Coord> getAllCoords(){
        return allCoords;
    }

    static public boolean inRange(Coord coord){ // находится ли указанная координата в пределах поля
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord(){
        return new Coord(random.nextInt(size.x), random.nextInt(size.y)); // функция для случайных координат
    }
}
