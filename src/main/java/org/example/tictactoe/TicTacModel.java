package org.example.tictactoe;


public class TicTacModel {
    private String[][] board;
    private Players currentPlayer;
    private GameStatus gameStatus;
    private int playerXScore; // Score for Player X
    private int playerOScore; // Score for Player O

    public TicTacModel() {
        resetGame(); // Ensure the game is initialized correctly
    }

    public void resetGame() {
        resetBoard();
        resetScores(); // Reset the scores as well
        gameStatus = GameStatus.ONGOING;
    }

    public void resetBoard() {
        board = new String[3][3]; // Reset the board
        currentPlayer = Players.PLAYER_X; // Reset the current player to X
        gameStatus = GameStatus.ONGOING; // Ensure the game status is ongoing
    }

    public void updateBoard(int position) {
        if (gameStatus != GameStatus.ONGOING) {
            return; // Prevent actions if the game is not ongoing
        }
        int row = position / 3;
        int col = position % 3;

        if (board[row][col] == null) {
            board[row][col] = currentPlayer.getSymbol();

            String winner = checkWinner();
            if (!winner.isEmpty()) {
                if (winner.equals("X")) {
                    gameStatus = GameStatus.X_WINS;
                    playerXScore++; // Increment Player X's score
                } else {
                    gameStatus = GameStatus.O_WINS;
                    playerOScore++; // Increment Player O's score
                }
            } else if (isBoardFull()) {
                gameStatus = GameStatus.DRAW;
            } else {
                currentPlayer = (currentPlayer == Players.PLAYER_X) ? Players.PLAYER_O : Players.PLAYER_X;
            }
        }
    }

    public int getPlayerXScore() {
        return playerXScore;
    }
    public int getPlayerOScore() {
        return playerOScore;
    }


    public GameStatus checkGameStatus() {
        String winner = checkWinner();
        if (!winner.isEmpty()) {
            return winner.equals("X") ? GameStatus.X_WINS : GameStatus.O_WINS;
        } else if (isBoardFull()) {
            return GameStatus.DRAW;
        }
        return GameStatus.ONGOING;
    }

    public String checkWinner() {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }
        // Check diagonals
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }
        return ""; // No winner
    }

    public boolean isBoardFull() {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void computerMove() {
        if (currentPlayer == Players.PLAYER_O && gameStatus == GameStatus.ONGOING) {
            // Basic logic: find the first available spot
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == null) {
                        board[i][j] = currentPlayer.getSymbol();
                        checkGameStatus(); // Check if this move wins or draws
                        currentPlayer = Players.PLAYER_X; // Switch back to player
                        return; // Exit after making a move
                    }
                }
            }
        }
    }



    public Players getCurrentPlayer() {
        return currentPlayer;
    }

    public String[][] getBoard() {
        return board; // Return the 2D array
    }

    public GameStatus getGameStatus() {
        return gameStatus; // Expose the current game status
    }

    public void resetScores() {
        playerXScore = 0;
        playerOScore = 0;
    }
}

