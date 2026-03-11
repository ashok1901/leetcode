package easy;

public class TicTacToe {

    public String tictactoe(int[][] moves) {
        char[][] board = transformToGrid(moves);
        boolean isMovePending = false;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char seedChar = board[row][col];
                if (seedChar != 'X' && seedChar != 'O') {
                    isMovePending = true;
                    continue;
                }
                String winner = "";
                if(isWinnerByDiagonal(row, col, board)) {
                    return seedChar == 'X' ? "A" : "B";
                }
                if (isWinnerByRow(row, col, board)) {
                    return seedChar == 'X' ? "A" : "B";
                }
                if (isWinnerByCol(row, col, board)) {
                    return seedChar == 'X' ? "A" : "B";
                }
            }
        }

        return isMovePending ? "Pending" : "Draw";
    }

    private boolean isWinnerByRow(int row, int col, char[][] board) {
        char charToLookFor = board[row][col];
        return board[row][(col + 1)%3] == charToLookFor && board[row][(col + 2)%3] == charToLookFor;
    }

    private boolean isWinnerByCol(int row, int col, char[][] board) {
        char charToLookFor = board[row][col];
        return board[(row+1)%3][col] == charToLookFor && board[(row+2)%3][col] == charToLookFor;
    }

    private boolean isWinnerByDiagonal(int row, int col, char[][] board) {
        // Only 5 elements can make a diagonal.
        if (!((row == 0 && col == 0) ||    // Top left Corner
                (row == 1 && col == 1) || // Middle element
                (row == 2 && col == 2) || // Bottom right element
                (row == 0 && col == 2) || // Top right element
                (row == 2 && col == 0))) {   // Bottom left element
            return false;
        }

        char charToLookFor = board[row][col];
        if (row == 0 && col == 0) {
            return board[1][1] == charToLookFor && board[2][2] == charToLookFor;
        }
        if (row == 1 && col == 1) {
            return board[0][0] == charToLookFor && board[2][2] == charToLookFor;
        }
        if (row == 2 && col == 2) {
            return board[0][0] == charToLookFor && board[1][1] == charToLookFor;
        }

        if (row == 2 && col == 0) {
            return board[1][1] == charToLookFor && board[0][2] == charToLookFor;
        }
        if (row == 0 && col == 2) {
            return board[1][1] == charToLookFor && board[2][0] == charToLookFor;
        }

        return false;
    }

    private char[][] transformToGrid(int[][] moves) {
        char[][] board = {{'=', '=', '='}, {'=','=', '='}, {'=','=', '='}};
        boolean isATurn = true;
        for (int[] move : moves) {
            board[move[0]][move[1]] = isATurn ? 'X' : 'O';
            isATurn = !isATurn;
        }
        return board;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
//        int[][] moves = {{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}};
//        int[][] moves = {{0,0},{1,1},{2,0},{1,0},{1,2},{2,1},{0,1},{0,2},{2,2}};
        int[][] moves = {{0,0},{2,0},{1,1},{2,1},{2,2}};
        System.out.println("The result is " + ticTacToe.tictactoe(moves));

    }
}


