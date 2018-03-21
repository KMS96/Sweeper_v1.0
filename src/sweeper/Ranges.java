package sweeper;

/**
 * Created by Mikhail on 21.03.2018.
 */
public class Ranges {
    static private Coord size;

    public static Coord getSize() {
        return size;
    }

    public static void setSize(int cols, int rows){
        Coord size = new Coord(cols, rows);
        setSize(size);
    }

    static void setSize(Coord size) {
        Ranges.size = size;
    }

}
