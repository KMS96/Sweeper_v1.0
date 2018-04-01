package sweeper;

class Bomb {
    private Matrix bombMap; // матрица для хранения бомб
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start(){ // инициализация матрицы
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBombs; j++)
            placeBomb();

    }

    Box get(Coord coord){
        return bombMap.get(coord);
    }

    int getTotalBombs(){
        return totalBombs;
    }

    private void fixBombsCount(){ // если бомб задано больше чем площадь поля
        int maxBombs = Ranges.getSquare() / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void placeBomb(){ // размещение бомб
        while (true) { // если бомб больше чем клеток поля
            // чтобы бомбы не расставлялись несколько раз в одну клетку
                Coord coord = Ranges.getRandomCoord();
                if (Box.BOMB == bombMap.get(coord))
                    continue;
                bombMap.set(coord, Box.BOMB);
                incNumbersAroundBomb(coord);
                break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for(Coord around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).nextNumberBox());
    }
}