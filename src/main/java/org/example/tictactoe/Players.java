package org.example.tictactoe;

public enum Players {
    PLAYER_X("X"),
    PLAYER_O("O");

    private final String symbol;

    Players(String symbol) {
        this.symbol = symbol;
    }


    public String getSymbol() {
        return symbol;
    }
}
