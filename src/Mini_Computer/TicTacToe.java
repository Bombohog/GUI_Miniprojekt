/********************************************
 * Project description
 *
 * Created by: Lasse J. Kongsdal
 * Date: 30-11-2020
 *
 * Description of program
 ********************************************/

package Mini_Computer;

import java.util.Random;

public class TicTacToe {

    // Every field in the board has one of three states
    enum field {
        EMPTY, CIRCLE, CROSS
    }

    // Instantiating board
    field[][] board = {
            {field.EMPTY, field.EMPTY, field.EMPTY},
            {field.EMPTY, field.EMPTY, field.EMPTY},
            {field.EMPTY, field.EMPTY, field.EMPTY}
    };

    field player;
    field computer;
    byte difficulty;
    boolean gameActiveStatus = false;

    // Player turn
    public boolean placeField(byte[] placement) {

        if (board[placement[0]][placement[1]] == field.EMPTY && gameActiveStatus) {

            board[placement[0]][placement[1]] = player;
            return true;
        }

        return false;
    }

    // Computer turn
    public byte[] computerAI() {

        /* Dumb AI */ if (difficulty == 1) { return dumbMove(); }
        /* Somewhat smart AI */ else if (difficulty == 2) {

            Random intelligent = new Random();

            if (intelligent.nextBoolean()) { return smartMove(); }
            /* Difficulty 1 - Dumb AI */ else { return dumbMove(); }

        }
        /* Smart AI */ else if (difficulty == 3) { return smartMove(); }

        return null;
    }


    public byte[] dumbMove() {

        boolean illegalMove = false;
        do {

            Random x = new Random();
            Random y = new Random();

            byte xPoint = (byte) x.nextInt(3);
            byte yPoint = (byte) y.nextInt(3);

            if (board[xPoint][yPoint] == field.EMPTY) {

                board[xPoint][yPoint] = computer;
                return new byte[]{xPoint, yPoint};

            } else { illegalMove = true; }

        } while (illegalMove);

        return null;
    }

    public byte[] smartMove() {

        // First computer checks if it can win
        if (board[0][0] == computer && board[0][1] == computer  && board[0][2] == field.EMPTY) { board[0][2] = computer; return new byte[]{0, 2}; } else
        if (board[0][0] == computer && board[0][1] == field.EMPTY  && board[0][2] == computer) { board[0][1] = computer; return new byte[]{0, 1}; } else
        if (board[0][0] == field.EMPTY && board[0][1] == computer  && board[0][2] == computer) { board[0][0] = computer; return new byte[]{0, 0}; } else
        if (board[1][0] == computer && board[1][1] == computer  && board[1][2] == field.EMPTY) { board[1][2] = computer; return new byte[]{1, 2}; } else
        if (board[1][0] == computer && board[1][1] == field.EMPTY  && board[1][2] == computer) { board[1][1] = computer; return new byte[]{1, 1}; } else
        if (board[1][0] == field.EMPTY && board[1][1] == computer  && board[1][2] == computer) { board[1][0] = computer; return new byte[]{1, 0}; } else
        if (board[2][0] == computer && board[2][1] == computer  && board[2][2] == field.EMPTY) { board[2][2] = computer; return new byte[]{2, 2}; } else
        if (board[2][0] == computer && board[2][1] == field.EMPTY  && board[2][2] == computer) { board[2][1] = computer; return new byte[]{2, 1}; } else
        if (board[2][0] == field.EMPTY && board[2][1] == computer  && board[2][2] == computer) { board[2][0] = computer; return new byte[]{2, 0}; } else /* End of Vertical */
            if (board[0][0] == computer && board[1][0] == computer  && board[2][0] == field.EMPTY) { board[2][0] = computer; return new byte[]{2, 0}; } else
            if (board[0][0] == computer && board[1][0] == field.EMPTY  && board[2][0] == computer) { board[1][0] = computer; return new byte[]{1, 0}; } else
            if (board[0][0] == field.EMPTY && board[1][0] == computer  && board[2][0] == computer) { board[0][0] = computer; return new byte[]{0, 0}; } else
            if (board[0][1] == computer && board[1][1] == computer  && board[2][1] == field.EMPTY) { board[2][1] = computer; return new byte[]{2, 1}; } else
            if (board[0][1] == computer && board[1][1] == field.EMPTY  && board[2][1] == computer) { board[1][1] = computer; return new byte[]{1, 1}; } else
            if (board[0][1] == field.EMPTY && board[1][1] == computer  && board[2][1] == computer) { board[0][1] = computer; return new byte[]{0, 1}; } else
            if (board[0][2] == computer && board[1][2] == computer  && board[2][2] == field.EMPTY) { board[2][2] = computer; return new byte[]{2, 2}; } else
            if (board[0][2] == computer && board[1][2] == field.EMPTY  && board[2][2] == computer) { board[1][2] = computer; return new byte[]{1, 2}; } else
            if (board[0][2] == field.EMPTY && board[1][2] == computer  && board[2][2] == computer) { board[0][2] = computer; return new byte[]{0, 2}; } else /* End of Horizontal */
                if (board[0][0] == computer && board[1][1] == computer  && board[2][2] == field.EMPTY) { board[2][2] = computer; return new byte[]{2, 2}; } else
                if (board[0][0] == computer && board[1][1] == field.EMPTY  && board[2][2] == computer) { board[1][1] = computer; return new byte[]{1, 1}; } else
                if (board[0][0] == field.EMPTY && board[1][1] == computer  && board[2][2] == computer) { board[0][0] = computer; return new byte[]{0, 0}; } else /* End of leftTop to rightBottom */
                    if (board[0][2] == computer && board[1][1] == computer  && board[2][0] == field.EMPTY) { board[2][0] = computer; return new byte[]{2, 0}; } else
                    if (board[0][2] == computer && board[1][1] == field.EMPTY  && board[2][0] == computer) { board[1][1] = computer; return new byte[]{1, 1}; } else
                    if (board[0][2] == field.EMPTY && board[1][1] == computer  && board[2][0] == computer) { board[0][2] = computer; return new byte[]{0, 2}; } else /* End of leftBottom to rightTop *//* If the computer cant win, then it checks if the player can win */
                        if (board[0][0] == player && board[0][1] == player  && board[0][2] == field.EMPTY) { board[0][2] = computer; return new byte[]{0, 2}; } else
                        if (board[0][0] == player && board[0][1] == field.EMPTY  && board[0][2] == player) { board[0][1] = computer; return new byte[]{0, 1}; } else
                        if (board[0][0] == field.EMPTY && board[0][1] == player  && board[0][2] == player) { board[0][0] = computer; return new byte[]{0, 0}; } else
                        if (board[1][0] == player && board[1][1] == player  && board[1][2] == field.EMPTY) { board[1][2] = computer; return new byte[]{1, 2}; } else
                        if (board[1][0] == player && board[1][1] == field.EMPTY  && board[1][2] == player) { board[1][1] = computer; return new byte[]{1, 1}; } else
                        if (board[1][0] == field.EMPTY && board[1][1] == player  && board[1][2] == player) { board[1][0] = computer; return new byte[]{1, 0}; } else
                        if (board[2][0] == player && board[2][1] == player  && board[2][2] == field.EMPTY) { board[2][2] = computer; return new byte[]{2, 2}; } else
                        if (board[2][0] == player && board[2][1] == field.EMPTY  && board[2][2] == player) { board[2][1] = computer; return new byte[]{2, 1}; } else
                        if (board[2][0] == field.EMPTY && board[2][1] == player  && board[2][2] == player) { board[2][0] = computer; return new byte[]{2, 0}; } else /* End of Vertical */
                            if (board[0][0] == player && board[1][0] == player  && board[2][0] == field.EMPTY) { board[2][0] = computer; return new byte[]{2, 0}; } else
                            if (board[0][0] == player && board[1][0] == field.EMPTY  && board[2][0] == player) { board[1][0] = computer; return new byte[]{1, 0}; } else
                            if (board[0][0] == field.EMPTY && board[1][0] == player  && board[2][0] == player) { board[0][0] = computer; return new byte[]{0, 0}; } else
                            if (board[0][1] == player && board[1][1] == player  && board[2][1] == field.EMPTY) { board[2][1] = computer; return new byte[]{2, 1}; } else
                            if (board[0][1] == player && board[1][1] == field.EMPTY  && board[2][1] == player) { board[1][1] = computer; return new byte[]{1, 1}; } else
                            if (board[0][1] == field.EMPTY && board[1][1] == player  && board[2][1] == player) { board[0][1] = computer; return new byte[]{0, 1}; } else
                            if (board[0][2] == player && board[1][2] == player  && board[2][2] == field.EMPTY) { board[2][2] = computer; return new byte[]{2, 2}; } else
                            if (board[0][2] == player && board[1][2] == field.EMPTY  && board[2][2] == player) { board[1][2] = computer; return new byte[]{1, 2}; } else
                            if (board[0][2] == field.EMPTY && board[1][2] == player  && board[2][2] == player) { board[0][2] = computer; return new byte[]{0, 2}; } else /* End of Horizontal */
                                if (board[0][0] == player && board[1][1] == player  && board[2][2] == field.EMPTY) { board[2][2] = computer; return new byte[]{2, 2}; } else
                                if (board[0][0] == player && board[1][1] == field.EMPTY  && board[2][2] == player) { board[1][1] = computer; return new byte[]{1, 1}; } else
                                if (board[0][0] == field.EMPTY && board[1][1] == player  && board[2][2] == player) { board[0][0] = computer; return new byte[]{0, 0}; } else /* End of leftTop to rightBottom */
                                    if (board[0][2] == player && board[1][1] == player  && board[2][0] == field.EMPTY) { board[2][0] = computer; return new byte[]{2, 0}; } else
                                    if (board[0][2] == player && board[1][1] == field.EMPTY  && board[2][0] == player) { board[1][1] = computer; return new byte[]{1, 1}; } else
                                    if (board[0][2] == field.EMPTY && board[1][1] == player  && board[2][0] == player) { board[0][2] = computer; return new byte[]{0, 2}; } else /* End of leftBottom to rightTop *//* At last if the player cant win, then it makes a random/dumbMove() move */
                                    { return dumbMove(); }

    }




    // Runs after every turn, to chekc if someone has won
    public String checkWinCondition() {

        if (board[0][0] == field.CIRCLE && board[1][1] == field.CIRCLE && board[2][2] == field.CIRCLE ||
                board[0][2] == field.CIRCLE && board[1][1] == field.CIRCLE && board[2][0] == field.CIRCLE ||
                board[0][0] == field.CIRCLE && board[0][1] == field.CIRCLE && board[0][2] == field.CIRCLE ||
                board[1][0] == field.CIRCLE && board[1][1] == field.CIRCLE && board[1][2] == field.CIRCLE ||
                board[2][0] == field.CIRCLE && board[2][1] == field.CIRCLE && board[2][2] == field.CIRCLE ||
                board[0][0] == field.CIRCLE && board[1][0] == field.CIRCLE && board[2][0] == field.CIRCLE ||
                board[0][1] == field.CIRCLE && board[1][1] == field.CIRCLE && board[2][1] == field.CIRCLE ||
                board[0][2] == field.CIRCLE && board[1][2] == field.CIRCLE && board[2][2] == field.CIRCLE) { return "CIRCLE"; }
        else if (board[0][0] == field.CROSS && board[1][1] == field.CROSS && board[2][2] == field.CROSS ||
                board[0][2] == field.CROSS && board[1][1] == field.CROSS && board[2][0] == field.CROSS ||
                board[0][0] == field.CROSS && board[0][1] == field.CROSS && board[0][2] == field.CROSS ||
                board[1][0] == field.CROSS && board[1][1] == field.CROSS && board[1][2] == field.CROSS ||
                board[2][0] == field.CROSS && board[2][1] == field.CROSS && board[2][2] == field.CROSS ||
                board[0][0] == field.CROSS && board[1][0] == field.CROSS && board[2][0] == field.CROSS ||
                board[0][1] == field.CROSS && board[1][1] == field.CROSS && board[2][1] == field.CROSS ||
                board[0][2] == field.CROSS && board[1][2] == field.CROSS && board[2][2] == field.CROSS) { return "CROSS"; }
        else { return "NON"; }

    }

    public boolean boardFilled() {
        if (board[0][0] != field.EMPTY && board[0][1] != field.EMPTY && board[0][2] != field.EMPTY &&
                board[1][0] != field.EMPTY && board[1][1] != field.EMPTY && board[1][2] != field.EMPTY &&
                board[2][0] != field.EMPTY && board[2][1] != field.EMPTY && board[2][2] != field.EMPTY) {
            return true;
        } else { return false; }
    }

    public String[] newGame(byte difficulty) {

        // Empty board
        board = new field[][] {
                {field.EMPTY, field.EMPTY, field.EMPTY},
                {field.EMPTY, field.EMPTY, field.EMPTY},
                {field.EMPTY, field.EMPTY, field.EMPTY}
        };

        // Give players new 'Characters'
        Random rand = new Random();

        this.difficulty = difficulty;

        if (rand.nextBoolean()) {
            player = field.CROSS;
            computer = field.CIRCLE;
            return new String[]{"Cross", "Circle"};
        } else {
            player = field.CIRCLE;
            computer = field.CROSS;
            return new String[]{"Circle", "Cross"};
        }

    }

}
