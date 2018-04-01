import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;


public class Sweeper extends JFrame {
    private Game game;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    private JPanel panel;
    private JLabel label;

    public static void main(String[] args) {
        new Sweeper();
    }

    private Sweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel(getMessage());
        Font font = new Font("Tahoma", Font.BOLD, 18);
        label.setFont(font);
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() { // анонимный класс для функции рисования

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords())
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE, // ordinal - текущий номер очередного элемента в списке
                            coord.y * IMAGE_SIZE, this); // рисуем картинку
            }
        };

        // слушатель мыши
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                switch (e.getButton()) { // какая кнопка мыши нажата
                    case MouseEvent.BUTTON1:
                        game.pressLeftButton(coord);
                        break;

                    case MouseEvent.BUTTON3:
                        game.pressRightButton(coord);
                        break;

                    case MouseEvent.BUTTON2:
                        game.start(); // перезапуск игры при нажатии средней кнопки
                        break;
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

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

    private void setImages() { // устанавливает нужные картинки в каждый экземпляр перечисления
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
        setIconImage(getImage("icon")); // иконка программы
    }

    private Image getImage(String name) {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // ищет в ресурсах
        return icon.getImage();
    }

    private String getMessage() {
        switch (game.getState()) {
            case BOMBED:
                return "Boom...You lose :(";
            case WINNER:
                return "Yeah, you win, bitch";
            case PLAYED:
            default:
                if (game.getTotalFlaged() == 0)
                    return "Welcome";
                else
                    return "Think twice! Flagged " + game.getTotalFlaged()
                    + " of " + game.getTotalBombs() + " bombs.";
        }
    }
}