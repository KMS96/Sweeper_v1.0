package sweeper;

/**
 * Created by Mikhail on 25.03.2018.
 */
class Bomb {
    private Matrix bombMap; // матрица для хранения бомб
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    void start(){ // инициализация матрицы
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBombs; j++)
            placeBomb();

    }

    Box get(Coord coord){
        return bombMap.get(coord);
    }

    void placeBomb(){
        bombMap.set(Ranges.getRandomCoord(), Box.BOMB);
    }
}
