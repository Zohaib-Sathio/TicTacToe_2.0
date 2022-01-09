package com.example.tictactoe20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    private TicTacToeBoard ticTacToeBoard;
    String[] player_Names;
    int names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button playAgain = findViewById(R.id.playAgain);
        Button homeBTN = findViewById(R.id.homeBTN);
        TextView playerTurn = findViewById(R.id.playerTurn);
        playAgain.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);
        playerTurn.setText("Player 1's Turn");
        playerTurn.setTextColor(Color.rgb(0,255,0));

        player_Names = getIntent().getStringArrayExtra("player_names");
        names = getIntent().getIntExtra("ID", 2);

        if(player_Names != null){
            playerTurn.setText(player_Names[0] + "'s Turn");
            playerTurn.setTextColor(Color.rgb(0,0,255));
        }

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        ticTacToeBoard.setUpGame(playAgain, homeBTN, playerTurn, player_Names);

    }
    public void home(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
//    public void swap(View view){
//        this.finishActivity(0);
//        Intent intent = new Intent(this, DisplayActivity.class);
//
//        if (names == 1) {
//            intent.putExtra("player_names", new String[] {player_Names[1], player_Names[0]});
//            empty = true;
//        }
//        if(!empty){
//            intent.putExtra("player_names", new String[]{"Player 2", "Player 1"});
//        }
//        startActivity(intent);
//    }
    public void restart(View view){
        ticTacToeBoard.updateGame();
        ticTacToeBoard.invalidate();

    }

}