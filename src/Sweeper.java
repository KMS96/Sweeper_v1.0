import javax.swing.*;
import java.awt.*;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

/**
 * Created by Mikhail on 21.03.2018.
 */
public class Sweeper extends JFrame {
    private Game game;
    private final int IMAGE_SIZE = 50;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;

    private JPanel panel;

    public static void main(String[] args) {
        new Sweeper();
    }

    private Sweeper(){
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel(){ // анонимный класс для функции рисования

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                            g.drawImage((Image) game.getBox(coord).image,
                                    coord.x * IMAGE_SIZE, // ordinal - текущий номер очередного элемента в списке
                                    coord.y * IMAGE_SIZE, this); // рисуем картинку
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE)); // размер панели
        add(panel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // закрытие программы на крестик
        setTitle("My Sweeper");
        setResizable(false); // размер нельзя менять

        pack(); // автоматическое изменение размера формы(JPanel)
        setVisible(true);
        setLocationRelativeTo(null); // расположение относительно пустого пространства
    }

    private void setImages(){ // устанавливает нужные картинки в каждый экземпляр перечисления
        for(Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
        setIconImage(getImage("icon")); // иконка программы
    }

    private Image getImage(String name){
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // ищет в ресурсах
        return icon.getImage();
    }

}
