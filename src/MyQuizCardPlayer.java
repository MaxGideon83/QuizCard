import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MyQuizCardPlayer {
    private JTextArea question;
    private ArrayList<QuizCard> cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton nextButton;
    private JButton showAnswer;
    private JButton previousButton;


    public static void main(String[] args) {
        MyQuizCardPlayer reader = new MyQuizCardPlayer();
        reader.go();
    }

    public void go() {
        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        question = new JTextArea(10, 20);
        question.setFont(bigFont);

        question.setLineWrap(true);
        question.setEditable(false);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        nextButton = new JButton("Next Question");
        previousButton = new JButton("Prev Question");
        showAnswer = new JButton("Show Answer");

        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        previousButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        showAnswer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(qScroller);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(previousButton);
        buttonPanel.add(showAnswer);
        buttonPanel.add(nextButton);
        nextButton.addActionListener(new NextButtonListener());
        previousButton.addActionListener(new PreviousButtonListener());
        showAnswer.addActionListener(new ShowAnswerListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.WEST, mainPanel);
        frame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        frame.setSize(640, 500);
        frame.setVisible(true);
    }

    public class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cardList != null) {
                if (currentCardIndex < cardList.size() - 1) {
                    currentCardIndex++;
                    currentCard = cardList.get(currentCardIndex);
                    question.setText(currentCard.getQuestion());
                } else {
                    currentCardIndex++;
                    question.setText("That was last card");
                    nextButton.setEnabled(false);
                }
                if (!previousButton.isEnabled()) {
                    previousButton.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please load file with questions");
            }
        }
    }

    public class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentCardIndex > 0) {
                currentCardIndex--;
                currentCard = cardList.get(currentCardIndex);
                question.setText(currentCard.getQuestion());
            } else {

                previousButton.setEnabled(false);
            }
            if (!nextButton.isEnabled()) {
                nextButton.setEnabled(true);
            }
        }
    }

    public class ShowAnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cardList != null) {
                if (currentCardIndex != cardList.size()) {
                    JOptionPane.showMessageDialog(null, currentCard.getAnswer());
                } else {
                    JOptionPane.showMessageDialog(null, "No question for the answer");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please load file with questions");
            }
        }
    }

    public class OpenMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    private void loadFile(File file) {
        cardList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                makeCard(line);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("Couldn`t read the card file");
            ex.printStackTrace();
        }
        showFirstCard();
        previousButton.setEnabled(false);
    }

    private void makeCard(String lineToParse) {
        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }

    private void showFirstCard() {
        currentCardIndex = 0;
        currentCard = cardList.get(currentCardIndex);
        //currentCardIndex++;
        question.setText(currentCard.getQuestion());

    }
}
