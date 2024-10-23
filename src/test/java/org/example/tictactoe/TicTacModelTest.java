package org.example.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacModelTest {
    TicTacModel model = new TicTacModel();

    @Test
    @DisplayName("If button occupied button cannot be clickable ")
    void ifButtonOccupiedButtonCannotBeClickable() {
        model.updateBoard(0);
        model.updateBoard(0);
        model.updateBoard(1);
        model.updateBoard(1);


        assertEquals("O", model.getBoard()[0][1]);
        assertEquals("X", model.getBoard()[0][0]);

    }

    @Test
    @DisplayName("X is winnable and gets point for win")
    void xIsWinnableAndGetsPointForWin() {
        model.updateBoard(0);
        model.updateBoard(1);
        model.updateBoard( 3);
        model.updateBoard(4);
        model.updateBoard(6);

        assertEquals(GameStatus.X_WINS, model.checkGameStatus());
        assertEquals(1 , model.getPlayerXScore());
        assertEquals(0 , model.getPlayerOScore());
        
    }

    @Test
    @DisplayName("O is winnable and gets point for win")
    void oIsWinnableAndGetsPointForWin() {
        model.updateBoard(0);
        model.updateBoard(1);
        model.updateBoard(3);
        model.updateBoard(4);
        model.updateBoard(8);
        model.updateBoard(7);

        assertEquals(GameStatus.O_WINS, model.checkGameStatus());
        assertEquals(0 , model.getPlayerXScore());
        assertEquals(1 , model.getPlayerOScore());

    }

    @Test
    @DisplayName("Is it draw when board full and no points given")
    void isItDrawWhenBoardFullAndNoPointsGiven() {
        model.updateBoard(0);
        model.updateBoard(1);
        model.updateBoard(2);
        model.updateBoard(4);
        model.updateBoard(3);
        model.updateBoard(5);
        model.updateBoard(7);
        model.updateBoard(6);
        model.updateBoard(8);

        assertEquals(GameStatus.DRAW, model.checkGameStatus());
        assertEquals(0 , model.getPlayerXScore());
        assertEquals(0 , model.getPlayerOScore());

    }

    @Test
    @DisplayName("When game is not finished has to be ONGOING")
    void whenGameIsNotFinishedHasToBeOngoing() {
        model.updateBoard(0);
        model.updateBoard(1);
        model.updateBoard(2);

        assertEquals(GameStatus.ONGOING, model.checkGameStatus());

    }



        
}

