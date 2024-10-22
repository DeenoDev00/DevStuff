package org.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class TicTacController {

    @FXML
    private Label ComputerWins, PlayerWins, statusMessage;

    @FXML
    GridPane board;

    private List<Button> buttons;

    private TicTacModel model;

    @FXML
    void initialize() {
        model = new TicTacModel();
        buttons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Button button = (Button) board.getChildren().get(i);
            buttons.add(button);
        }

        resetBoard();
    }

    @FXML
    void handleButtonClick(javafx.event.ActionEvent event) {
        if (model.isGameOver()){
            return;
        }

        Button clickedButton = (Button) event.getSource();
        Players currentPlayer = model.getCurrentPlayer();
        if (clickedButton.getText().isEmpty()) {
            clickedButton.setText(currentPlayer.name());
            model.updateBoard(buttons.indexOf(clickedButton));
            updateStatus();
        }

    }

    @FXML
    public void resetGame() {
        // Rensa alla knappar och återställ modellen
        for (Button button : buttons) {
            button.setText("");
        }
        model.resetGame();
        model.setGameOver(false);
        statusMessage.setText(model.getStatusMessage());
        updateScoreDisplay();
    }

    @FXML
    public void resetBoard(){
    model.resetBoard();
    model.setGameOver(false);
    for (Button button : buttons) {
        button.setText("");
    }
        statusMessage.setText(model.getCurrentPlayer() + "'s turn");
    }

    @FXML
    void updateScoreDisplay() {
        ComputerWins.setText(String.valueOf(model.getComputerWins()));
        PlayerWins.setText(String.valueOf(model.getPlayerWins()));
    }

    private void updateStatus() {
        Players result = model.checkGameStatus();
        if (result) {
            if (result.equals("draw")){
                statusMessage.setText("It's a draw!");
            }else {
                statusMessage.setText(result +" won!");
                model.updateScore(result);
                updateScoreDisplay();
                model.setGameOver(true);
            }
            }else {
            statusMessage.setText(model.getCurrentPlayer() + "'s turn!");
        }
    }
}





