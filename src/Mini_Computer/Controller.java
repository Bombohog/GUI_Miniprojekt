package Mini_Computer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Controller {




    // Tic Tac Toe game under here
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

    TicTacToe gameTicTacToe;

    public void startTicTacToe() {

        gameTicTacToe = new TicTacToe();
        if (choiceBoxTicTacToe.getValue() != null) {

            labelWinner.setText("");
            initialize();
            String[] symbols = gameTicTacToe.newGame((Byte) choiceBoxTicTacToe.getValue());
            labelPlayer1.setText(symbols[0]);
            labelPlayer2.setText(symbols[1]);
            choiceBoxTicTacToe.setDisable(true);
            gameTicTacToe.gameActiveStatus = true;

            button00.setText(" ");
            button01.setText(" ");
            button02.setText(" ");
            button10.setText(" ");
            button11.setText(" ");
            button12.setText(" ");
            button20.setText(" ");
            button21.setText(" ");
            button22.setText(" ");

        } else { labelWinner.setText("Choose difficulty"); }

    }

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


    public void placement(Button button, byte[] points) {

        // If the player can place the field and the game is still going
        if (gameTicTacToe.placeField(new byte[]{points[0], points[1]}) && gameTicTacToe.gameActiveStatus) {
            // Place player symbol on board
            if (gameTicTacToe.player == TicTacToe.field.CIRCLE) {
                button.setText("o");
            } else {
                button.setText("x");
            }

            // Check if the player wins
            String winner = gameTicTacToe.checkWinCondition();
            if (!winner.equals("NON")) {
                choiceBoxTicTacToe.setDisable(false);
                labelWinner.setText(winner);
                gameTicTacToe.gameActiveStatus = false;
                logWin(true);
                return; // Stops the computer from placing anything
            }

            // Is the board filled
            if (gameTicTacToe.boardFilled()) {
                gameTicTacToe.gameActiveStatus = false;
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
            winner = gameTicTacToe.checkWinCondition();
            if (!winner.equals("NON")) {
                choiceBoxTicTacToe.setDisable(false);
                labelWinner.setText(winner);
                gameTicTacToe.gameActiveStatus = false;
                logWin(false);
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
    private void logWin(boolean winner) {

        try {

            File file = new File("GameplayLog.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {

                String placeholder = input.nextLine();
                // TODO
            }

            String winnerString = "";
            if (winner) {
                winnerString = "Player Won!";
            } else {
                winnerString = "Computer Win!";
            }

            // TODO make save feature work, problem is that it overwrites the saved text
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(button00.getText() + button10.getText() + button20.getText() + "\n"
                    + button01.getText() + button11.getText() + button21.getText() + "\n"
                    + button02.getText() + button12.getText() + button22.getText() + "\n" + winnerString);

            input.close();

        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }

    }

}
