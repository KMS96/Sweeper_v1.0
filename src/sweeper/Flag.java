package sweeper;

class Flag {
    private Matrix flagMap;
    private int totalFlaged;
    private int totalClosed;

    void start(){ // запуск игры, проставление клеток
        flagMap = new Matrix(Box.CLOSED);
        totalFlaged = 0;
        totalClosed = Ranges.getSquare();
    }

    Box get(Coord coord){
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        totalClosed --;
    }

    int getTotalFlaged() {
        return totalFlaged;
    }

    int getTotalClosed() {
        return totalClosed;
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
        totalFlaged ++;
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
        totalFlaged --;
    }

    void toggleFlagedToBox(Coord coord) { // переключить значение флажка
        switch (flagMap.get(coord)){
            case FLAGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    void setFlagedToLastClosedBoxes() {
        for (Coord coord : Ranges.getAllCoords())
            if (Box.CLOSED == flagMap.get(coord))
                setFlagedToBox(coord);
    }

    void setBombedToBox(Coord coord) { // устанавливаем картинку взрыва бомбы
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosedBox(Coord coord) { // показать все бомбы когда проигрываем
        if (Box.CLOSED == flagMap.get(coord))
            flagMap.set(coord, Box.OPENED);
    }

    void setNobombToFlagedBox(Coord coord) {
        if (Box.FLAGED == flagMap.get(coord))
            flagMap.set(coord, Box.NOBOMB);
    }

    int getCountOfFlagedBoxesAround(Coord coord) { // получить кол-во флагов вокруг клетки
        int count = 0;
        for(Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}