package at.meroff.bac;

import at.meroff.bac.models.*;
import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main extends JComponent {

    private final Field field = new Field();

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final Main comp = new Main();
        comp.setPreferredSize(new Dimension(3264, 2448));
        frame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        JButton newLineButton = new JButton("draw");
        JButton clearButton = new JButton("Clear");
        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);

        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        newLineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                comp.addLine();
            }

        });

        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comp.clearLines();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<Vec2d> allVectors = field.getAllVectors();

        double xmin = allVectors.stream().mapToDouble(value -> value.x).min().getAsDouble();
        double xmax = allVectors.stream().mapToDouble(value -> value.x).max().getAsDouble();
        double ymin = allVectors.stream().mapToDouble(value -> value.x).min().getAsDouble();
        double ymax = allVectors.stream().mapToDouble(value -> value.x).max().getAsDouble();

        int xoffset = 0;
        if (xmin < 0) {
            xoffset = (int)Math.abs(xmin);
        }

        int yoffset = 0;

        if (ymin < 0) {
            yoffset = (int)Math.abs(ymin);
        }

        int finalXoffset = xoffset;
        int finalYoffset = yoffset;
        allVectors.forEach(vec2d ->{
            g.setColor(Color.RED);
            g.drawLine((int)(finalXoffset * 0.5), (int)(finalYoffset * 0.5),(int)((((int)vec2d.x) + finalXoffset)*0.5),(int)((((int)vec2d.y)+finalYoffset)*0.5));
        } );

        g.drawRect((int)field.getRectangle(CardType.SUBJECT).getX(), (int)field.getRectangle(CardType.SUBJECT).getY(), (int)field.getRectangle(CardType.SUBJECT).getWidth(), (int)field.getRectangle(CardType.SUBJECT).getHeight());
        g.drawRect((int)field.getRectangle(CardType.TASK).getX(), (int)field.getRectangle(CardType.TASK).getY(), (int)field.getRectangle(CardType.TASK).getWidth(), (int)field.getRectangle(CardType.TASK).getHeight());
    }

    public void clearLines() {
        repaint();
    }

    public void addLine() {
        field.addCard(new Subject(8, 371, 246, 379, 363, 169, 378, 160, 261));
        field.addCard(new Subject(17, 504, 895, 492, 1049, 243, 1030, 255, 876));
        field.addCard(new Subject(44, 1232, 255, 1245, 140, 1452, 163, 1438, 278));
        field.addCard(new Subject(38, 1162, 755, 1295, 655, 1465, 878, 1332, 979));
        field.addCard(new Subject(50, 907, 612, 777, 612, 778, 393, 907, 393));

        field.addCard(new Task(33, 366, 116, 369, 237, 156, 243, 152, 122));
        field.addCard(new Task(42, 419, 685, 434, 839, 197, 862, 182, 708));
        field.addCard(new Task(51, 130, 697, 123, 562, 351, 552, 357, 687));
        field.addCard(new Task(27, 1339, 1000, 1213, 1134, 1000, 935, 1126, 800));
        field.addCard(new Task(60, 808, 1107, 946, 965, 1169, 1183, 1031, 1325));
        field.addCard(new Task(30, 997, 381, 1122, 379, 1126, 600, 1001, 603));
        field.addCard(new Task(9, 1170, 376, 1300, 374, 1304, 607, 1174, 609));
        field.addCard(new Task(39, 1375, 372, 1505, 367, 1514, 614, 1384, 619));
        field.addCard(new Task(15, 1169, 302, 1047, 274, 1097, 52, 1218, 80));
        field.addCard(new Task(3, 1005, 261, 881, 234, 928, 25, 1051, 53));
        field.addCard(new Task(18, 807, 213, 690, 182, 750, -35, 866, -3));
        repaint();
    }
}