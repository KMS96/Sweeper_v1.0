package sweeper;

public class Game {
    private Bomb bomb;

    public Game(int cols, int rows, int bombs){
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
    }

    public void start(){ // создаем карту с бомбами(расставляем элементы)
        bomb.start(); // вызываем инициализацию бомб
    }

    public Box getBox(Coord coord){
        return bomb.get(coord);
    }
}
