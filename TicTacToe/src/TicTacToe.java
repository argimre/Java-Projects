import java.util.Scanner;

public class TicTacToe {
    static char currentPlayer = 'X';
    static char[][] board = {
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
    };

    public static void printBoard() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

        System.out.println("    1   2   3");
        System.out.println("  -------------");
        char[] rowLabels = { 'A', 'B', 'C' };
        for (int i = 0; i < 3; i++) {
            System.out.print(rowLabels[i] + " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n  -------------");
        }
    }

    public static boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void changeTurn() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static int[] getMoveInput(Scanner input) {
        while (true) {
            System.out.print("Enter your move (e.g., A1, B3): ");
            String move = input.next().toUpperCase();

            if (move.length() == 2) {
                char rowChar = move.charAt(0);
                char colChar = move.charAt(1);

                if (rowChar >= 'A' && rowChar <= 'C' && colChar >= '1' && colChar <= '3') {
                    int row = rowChar - 'A';
                    int col = colChar - '1';
                    return new int[] { row, col };
                }
            }
            System.out.println("Invalid input. Please enter a valid move (e.g., A1, B3).");
        }
    }

    public static boolean makeMove(int row, int col) {
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        System.out.println("Invalid move. The cell is already occupied. Try again.");
        return false;
    }

    public static boolean processTurn(Scanner input) {
        printBoard();
        System.out.println("Player " + currentPlayer + "'s turn.");
        int[] move = getMoveInput(input);

        if (makeMove(move[0], move[1])) {
            if (checkWinner()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                return true;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                return true;
            }
            changeTurn();
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            if (processTurn(input)) {
                break;
            }
        }

        input.close();
    }
}