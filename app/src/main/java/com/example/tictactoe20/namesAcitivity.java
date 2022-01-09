package com.example.tictactoe20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class namesAcitivity extends AppCompatActivity {
    private EditText player1;
    private EditText player2;

    public void defaultNames(View view){
        Intent intent = new Intent(this, DisplayActivity.class);

        intent.putExtra("player_names", new String[]{"Player 1", "Player 2"});
        intent.putExtra("ID", 0);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.names_acitivity);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
    }

    public void submit(View view){
        Intent intent = new Intent(this, DisplayActivity.class);
        String PlayerOne = player1.getText().toString();
        String Playertwo = player2.getText().toString();

        intent.putExtra("player_names", new String[]{PlayerOne, Playertwo});
        intent.putExtra("ID", 1);
        startActivity(intent);

    }
}