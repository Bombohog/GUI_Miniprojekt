package Mini_Computer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;

public class Controller {

    // Tic Tac Toe game under here
    enum Winner { PLAYER, COMPUTER, NON; }

    @FXML
    ChoiceBox choiceBoxTicTacToe;

    @FXML
    Button button00;
    @FXML
    Button button01;
    @FXML
    Button button02;
    @FXML
    Button button10;
    @FXML
    Button button11;
    @FXML
    Button button12;
    @FXML
    Button button20;
    @FXML
    Button button21;
    @FXML
    Button button22;

    @FXML
    Label labelPlayer1;
    @FXML
    Label labelPlayer2;
    @FXML
    Label labelWinner;

    @FXML
    TextArea gameplayLog;

    TicTacToe gameTicTacToe;

    public void startTicTacToe() {

        gameTicTacToe = new TicTacToe();
        if (choiceBoxTicTacToe.getValue() != null) {

            // Reset and set UI
            labelWinner.setText("");
            initialize();
            byte choiceDifficulty = 1;
            if ((String) choiceBoxTicTacToe.getValue() == "Easy") {
                choiceDifficulty = 1;
            } else if ((String) choiceBoxTicTacToe.getValue() == "Normal") {
                choiceDifficulty = 2;
            } if ((String) choiceBoxTicTacToe.getValue() == "Harder") {
                choiceDifficulty = 3;
            }
            String[] symbols = gameTicTacToe.newGame(choiceDifficulty);
            labelPlayer1.setText(symbols[0]);
            labelPlayer2.setText(symbols[1]);
            choiceBoxTicTacToe.setDisable(true);
            gameTicTacToe.gameActiveStatus = true;

            // Reset board
            button00.setText(" ");
            button01.setText(" ");
            button02.setText(" ");
            button10.setText(" ");
            button11.setText(" ");
            button12.setText(" ");
            button20.setText(" ");
            button21.setText(" ");
            button22.setText(" ");

            // If the player is circle the computer goes first
            if (symbols[0] == "Circle") {
                // Place computer symbol on board
                byte[] computerPoints =  gameTicTacToe.computerAI();
                Button computerButton = whichButton(computerPoints);
                if (gameTicTacToe.computer == TicTacToe.field.CIRCLE) {
                    computerButton.setText("o");
                } else {
                    computerButton.setText("x");
                }
            }

        } else { labelWinner.setText("Choose difficulty"); }

    }

    // Initialize all buttons
    @FXML
    public void initialize() { // https://stackoverflow.com/questions/37902660/javafx-button-sending-arguments-to-actionevent-function
        button00.setOnAction(e -> placement(button00, new byte[]{0, 0}));
        button01.setOnAction(e -> placement(button01, new byte[]{0, 1}));
        button02.setOnAction(e -> placement(button02, new byte[]{0, 2}));
        button10.setOnAction(e -> placement(button10, new byte[]{1, 0}));
        button11.setOnAction(e -> placement(button11, new byte[]{1, 1}));
        button12.setOnAction(e -> placement(button12, new byte[]{1, 2}));
        button20.setOnAction(e -> placement(button20, new byte[]{2, 0}));
        button21.setOnAction(e -> placement(button21, new byte[]{2, 1}));
        button22.setOnAction(e -> placement(button22, new byte[]{2, 2}));
    }

    // Places and checks
    public void placement(Button button, byte[] points) {

        // If the player can place the field and the game is still going
        if (gameTicTacToe.placeField(new byte[]{points[0], points[1]}) && gameTicTacToe.gameActiveStatus) {

            // Used later
            String winnerString = "";

            // Place player symbol on board
            if (gameTicTacToe.player == TicTacToe.field.CIRCLE) {
                button.setText("o");
            } else {
                button.setText("x");
            }

            // Check if the player wins
            winnerString = gameTicTacToe.checkWinCondition();
            if (!winnerString.equals("NON")) {
                choiceBoxTicTacToe.setDisable(false);
                labelWinner.setText(winnerString);
                gameTicTacToe.gameActiveStatus = false;
                logWin(Winner.PLAYER);
                return; // Stops the computer from placing anything
            }

            // Is the board filled
            if (gameTicTacToe.boardFilled()) {
                choiceBoxTicTacToe.setDisable(false);
                labelWinner.setText("Draw");
                gameTicTacToe.gameActiveStatus = false;
                logWin(Winner.NON);
                return; // Stops anyone but mostly the computer from placing
            }

            // Place computer symbol on board
            byte[] computerPoints =  gameTicTacToe.computerAI();
            Button computerButton = whichButton(computerPoints);
            if (gameTicTacToe.computer == TicTacToe.field.CIRCLE) {
                computerButton.setText("o");
            } else {
                computerButton.setText("x");
            }

            // Check if the computer wins
            winnerString = gameTicTacToe.checkWinCondition();
            if (!winnerString.equals("NON")) {
                choiceBoxTicTacToe.setDisable(false);
                labelWinner.setText(winnerString);
                gameTicTacToe.gameActiveStatus = false;
                logWin(Winner.COMPUTER);
            }

        }

    }

    // What button/field did the computer place at
    private Button whichButton(byte[] points) {

        if (points[0] == 0 && points[1] == 0) {
            return button00;
        } else if (points[0] == 0 && points[1] == 1) {
            return button01;
        } else if (points[0] == 0 && points[1] == 2) {
            return button02;
        } else if (points[0] == 1 && points[1] == 0) {
            return button10;
        } else if (points[0] == 1 && points[1] == 1) {
            return button11;
        } else if (points[0] == 1 && points[1] == 2) {
            return button12;
        } else if (points[0] == 2 && points[1] == 0) {
            return button20;
        } else if (points[0] == 2 && points[1] == 1) {
            return button21;
        } else if (points[0] == 2 && points[1] == 2) {
            return button22;
        } else {
            return null;
        }

    }

    // Saves the gameplay/win
    private void logWin(Winner winner) {

        try {

            File file = new File("GameplayLog.txt");

            String winnerString = "";
            if (winner == Winner.PLAYER) {
                winnerString = "Player Won!";
            } else if (winner == Winner.COMPUTER) {
                winnerString = "Computer Win!";
            } else {
                winnerString = "Draw!";
            }

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter outStream = new BufferedWriter(fileWriter);
            outStream.write(button00.getText() + " " + button10.getText() + " "  + button20.getText() + "\n"
                    + button01.getText() + " "  + button11.getText() + " "  + button21.getText() + "\n"
                    + button02.getText() + " "  + button12.getText() + " "  + button22.getText() + "\n"
                    + winnerString + "\nAI Difficulty: " + (String) choiceBoxTicTacToe.getValue() + "\n\n");
            outStream.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }

        loadGameplayLog();

    }

    // Loads saved games into the log tab
    @FXML
    public void loadGameplayLog() {

        ArrayList<String> lines = new ArrayList<String>();

        try {

            File file = new File("GameplayLog.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine()) != null) { lines.add(line); }

            for (int i = 0; i < lines.size(); i++) {
                if (i == 0) {
                    gameplayLog.setText(lines.get(i) + "\n");
                } else {
                    gameplayLog.appendText(lines.get(i) + "\n");
                }
            }

        } catch (IOException e) { e.printStackTrace(); }

    }

}
