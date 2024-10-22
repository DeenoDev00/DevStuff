package org.example.tictactoe;

import javafx.fxml.FXML;
import static org.example.tictactoe.Players.*;

public class TicTacModel {
    private Players[][] board = new Players[3][3];
    private Players currentPlayer = PLAYER_X;
    private boolean isGameOver;
    private String statusMessage;
    private int computerWins;
    private int playerWins;

    public TicTacModel() {
        resetBoard();
    }

    public void resetGame() {
        currentPlayer = PLAYER_X;
        playerWins = 0;
        computerWins = 0;
    }

    public void resetBoard(){
        board = new Players[3][3];
        currentPlayer = PLAYER_X;
        isGameOver = false;
        statusMessage = "Player X's turn";
    }

    public void updateScore(Players winner){
        if (winner == PLAYER_X){
            playerWins++;
        }
        else if (winner ==PLAYER_O){
            computerWins++;
        }
    }

    public int getPlayerWins(){
        return playerWins;
    }

    public int getComputerWins(){
        return computerWins;
    }

    public Players getCurrentPlayer() {
        return currentPlayer;
    }

    public Players checkGameStatus(){
        Players winner = checkWinner();
        if(winner != null){
            return winner.PLAYER_X;
        }
        return isBoardFull() ? "draw" : "";
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void updateBoard(int position) {
        if (isGameOver){
            return;
        }
        int row = position / 3;
        int col = position % 3;
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;

            Players winner = checkWinner();
            if (winner != null) {
                isGameOver = true;
                statusMessage = winner.name() + " won!";
            } else if (isBoardFull()) {
                isGameOver = true;
                statusMessage = "Draw";

            } else {
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                statusMessage = currentPlayer.name() + "'s turn";
            }

        }
    }

    public Players checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                isGameOver = true;
                statusMessage = board[i][0] + " won!";
                return board[i][0];
            }
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                isGameOver = true;
                statusMessage = board[0][i] + " won!";
                return board[0][i];
            }
        }
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            isGameOver = true;
            statusMessage = board[0][0] + " won!";
            return board[0][0];
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            isGameOver = true;
            statusMessage = board[0][2] + " won!";
            return board[0][2];
        }
        if (isBoardFull()) {
            isGameOver = true;
            statusMessage = "It's a draw!";
            return null;
        }
        return null;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}

