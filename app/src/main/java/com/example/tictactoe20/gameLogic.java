package com.example.tictactoe20;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameLogic {
    int[][] gameBoard;
    private int player = 1;
    String[] names = {"PLayer 1", "Player 2"};
    private Button playAgain;
    private Button homeBTN;
    private TextView playerTurn;
    private int[] winType;

    gameLogic(){
        gameBoard = new int[3][3];
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }
    }
    public boolean winCheck(){
        boolean isWinner = false;
        winType = new int[] {-1,-1,-1};
        for(int r=0; r<3; r++){
            if(gameBoard[r][0]== gameBoard[r][1] && gameBoard[r][0]== gameBoard[r][2]
                    && gameBoard[r][0]!= 0){
                winType = new int[] {r,0,1};
                isWinner = true;
            }
        }
        for(int c=0; c<3; c++){
            if(gameBoard[0][c]== gameBoard[1][c] && gameBoard[0][c]== gameBoard[2][c]
                    && gameBoard[0][c]!= 0){
                winType = new int[] {0,c,2};
                isWinner = true;
            }
        }
        if(gameBoard[0][0]== gameBoard[1][1] && gameBoard[0][0]== gameBoard[2][2]
                && gameBoard[0][0]!= 0){
            winType = new int[] {0, 2,3};
            isWinner = true;
        }
        if(gameBoard[0][2]== gameBoard[1][1] && gameBoard[0][2]== gameBoard[2][0]
                && gameBoard[0][2]!= 0){
            winType = new int[] {2, 2,4};
            isWinner = true;
        }

        int isFilled = 0;
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                if (gameBoard[r][c]!=0){
                    isFilled+=1;
                }
            }
        }

        if(isWinner){
            playAgain.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText(names[player-1] + " Won!");
            playerTurn.setTextColor(Color.rgb(255,255,0));
            return true;
        }
        else if(isFilled == 9){
            playAgain.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText("Game Drawn!");
            playerTurn.setTextColor(Color.rgb(255,255,255));
            return true;
        }
        else {
            return false;
        }
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public void setHomeBTN(Button homeBTN) {
        this.homeBTN = homeBTN;
    }

    public void setPlayAgain(Button playAgain) {
        this.playAgain = playAgain;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean updateGameBoard(int r, int c){
        if (gameBoard[r-1][c-1]==0){
            gameBoard[r-1][c-1] = player;

            if (player == 1){
                playerTurn.setText(names[1] + "'s Turn");
                playerTurn.setTextColor(Color.rgb(255,0,0));
            }
            else {
                playerTurn.setText(names[0] + "'s Turn");
                playerTurn.setTextColor(Color.rgb(0,0,255));
            }
            return true;
        }
        else{
            return false;
        }
    }
    public void resetGame(){
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                gameBoard[r][c] = 0;
            }
        }
        playAgain.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);
        player = 1;
        playerTurn.setText(names[0]+ "'s Turn");
        playerTurn.setTextColor(Color.rgb(0,0,255));
    }


    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int[] getWinType() { return winType; }
}
