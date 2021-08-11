import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class QuizCardGame {
    private JFrame frame;
    private JButton builder;
    private JButton player;
    private Box buttonBox;

    public static void main(String[] args) {
        QuizCardGame game = new QuizCardGame();
        game.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        builder = new JButton("Start Card Builder");
        player = new JButton("Start Card Player");
        builder.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        player.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonBox = new Box(BoxLayout.Y_AXIS);
        buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 10));
        buttonBox.add(builder);
        buttonBox.add(player);
        frame.getContentPane().add(BorderLayout.CENTER, buttonBox);
        frame.setBounds(650, 450, 200, 140);
        frame.setResizable(false);
        frame.setVisible(true);
        builder.addActionListener(new BuilderActionListener());
        player.addActionListener(new PlayerActionListener());
    }
    public class BuilderActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCardBuilder builder = new QuizCardBuilder();
            builder.go();
            frame.dispose();
        }
    }
    public class PlayerActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MyQuizCardPlayer cardPlayer = new MyQuizCardPlayer();
            cardPlayer.go();
            frame.dispose();
        }
    }

}
