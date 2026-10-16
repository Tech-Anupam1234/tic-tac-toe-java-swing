import javax.swing.*;
import java.awt.*;

public class TicTacToeProV2 extends JFrame {

    private JButton[] buttons = new JButton[9];

    private String turn = "X";

    private int xScore = 0;
    private int oScore = 0;
    private int drawScore = 0;

    private JLabel scoreLabel;

    public TicTacToeProV2() {

        setTitle("Tic Tac Toe");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Score Board
        scoreLabel = new JLabel(
                "X: 0   O: 0   Draw: 0",
                SwingConstants.CENTER);

        scoreLabel.setFont(
                new Font("Arial", Font.BOLD, 22));

        add(scoreLabel, BorderLayout.NORTH);

        // Board Panel
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton("");

            buttons[i].setFont(
                    new Font("Arial",
                            Font.BOLD,
                            60));

            int index = i;

            buttons[i].addActionListener(e -> {

                if (buttons[index]
                        .getText()
                        .equals("")) {

                    buttons[index]
                            .setText(turn);

                    if (checkWinner()) {
                        return;
                    }

                    turn = turn.equals("X")
                            ? "O"
                            : "X";
                }
            });

            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);

        // Restart Button
        JButton restartButton = new JButton("Restart Game");

        restartButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18));

        // Background Color
        restartButton.setBackground(new Color(52, 152, 219)); // Blue

        // Text Color
        restartButton.setForeground(Color.WHITE);

        // Remove focus border
        restartButton.setFocusPainted(false);

        restartButton.addActionListener(e -> {

            resetBoard();

            xScore = 0;
            oScore = 0;
            drawScore = 0;

            updateScoreBoard();
        });

        add(restartButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // check winner
    private boolean checkWinner() {

        int[][] winPositions = {

                { 0, 1, 2 },
                { 3, 4, 5 },
                { 6, 7, 8 },

                { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 },

                { 0, 4, 8 },
                { 2, 4, 6 }
        };

        for (int[] pos : winPositions) {

            String first = buttons[pos[0]].getText();

            String second = buttons[pos[1]].getText();

            String third = buttons[pos[2]].getText();

            if (!first.equals("") &&
                    first.equals(second) &&
                    second.equals(third)) {

                buttons[pos[0]]
                        .setBackground(Color.GREEN);

                buttons[pos[1]]
                        .setBackground(Color.GREEN);

                buttons[pos[2]]
                        .setBackground(Color.GREEN);

                if (first.equals("X")) {
                    xScore++;
                } else {
                    oScore++;
                }

                updateScoreBoard();

                JOptionPane.showMessageDialog(
                        this,
                        "<html><h2 style='color:green'>" +
                                first + " Wins!</h2></html>",
                        "Winner", JOptionPane.INFORMATION_MESSAGE);

                resetBoard();

                return true;
            }
        }

        if (checkDraw()) {
            return true;
        }

        return false;
    }

    // check draw
    private boolean checkDraw() {

        for (JButton button : buttons) {

            if (button.getText().equals("")) {
                return false;
            }
        }

        drawScore++;

        updateScoreBoard();

        JOptionPane.showMessageDialog(
                this,
                "Match Draw!",
                "Game Result",
                JOptionPane.WARNING_MESSAGE);

        resetBoard();

        return true;
    }

    // button reset

    private void resetBoard() {

        for (JButton button : buttons) {

            button.setText("");

            button.setBackground(null);
        }

        turn = "X";
    }

    // update scoreboard
    private void updateScoreBoard() {

        scoreLabel.setText(
                "X: " + xScore
                        + "   O: " + oScore
                        + "   Draw: " + drawScore);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new TicTacToeProV2();
        });
    }
}
