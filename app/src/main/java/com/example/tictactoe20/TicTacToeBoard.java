package com.example.tictactoe20;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {
    private final int colorBoard;
    private final int oColor;
    private final int xColor;
    private final int winningColor;
    private boolean winner = false;
    private final Paint paint = new Paint();
    private int sellSize = getWidth()/3;
    private final gameLogic game;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new gameLogic();

        TypedArray array = context.getTheme().obtainStyledAttributes
                (attrs, R.styleable.TicTacToeBoard, 0, 0);
        try {
            colorBoard = array.getInteger(R.styleable.TicTacToeBoard_colorBoard, 0);
            oColor = array.getInteger(R.styleable.TicTacToeBoard_oColor, 0);
            xColor = array.getInteger(R.styleable.TicTacToeBoard_xColor, 0);
            winningColor = array.getInteger(R.styleable.TicTacToeBoard_winningColor, 0);
        }
        finally {
            array.recycle();
        }

    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        sellSize = dimension/3;

        setMeasuredDimension(dimension, dimension);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawBoard(canvas);
        drawMarkers(canvas);

        if (winner){
            paint.setColor(winningColor);
            DrawWinLine(canvas);
        }
    }

    private void drawBoard(Canvas canvas){
        paint.setColor(colorBoard);
        paint.setStrokeWidth(16);
        for(int c=1; c<3; c++){
            canvas.drawLine(sellSize*c, 0, sellSize*c, canvas.getWidth(), paint);
        }
        for(int r=1; r<3; r++){
            canvas.drawLine(0, sellSize*r, canvas.getWidth(), sellSize*r, paint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/sellSize);
            int col = (int) Math.ceil(x/sellSize);

            if (!winner){
                if(game.updateGameBoard(row,col)){
                    invalidate();
                    if (game.winCheck()){
                        winner = true;
                        invalidate();
                    }
                    if(game.getPlayer()%2 == 0){
                        game.setPlayer(game.getPlayer()-1);
                    }
                    else{
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }

            invalidate();
            return true;
        }
        return false;
    }

    public void setUpGame(Button playAgain, Button homeBTN, TextView playerTurn, String[] names){
        game.setPlayAgain(playAgain);
        game.setHomeBTN(homeBTN);
        game.setPlayerTurn(playerTurn);
        game.setNames(names);
    }

    public void drawMarkers(Canvas canvas){
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                if(game.getGameBoard()[r][c] != 0){
                    if (game.getGameBoard()[r][c] == 1){
                        DrawX(canvas, r, c);
                    }
                    else{
                        DrawO(canvas, r,c);
                    }
                }
            }
        }
    }

    private void DrawO(Canvas canvas, int row, int col){
        paint.setColor(oColor);

        canvas.drawLine((col+1)*sellSize - sellSize*0.2f,
                row*sellSize + sellSize*0.2f, col*sellSize + sellSize*0.2f,
                (row+1)*sellSize - sellSize*0.2f, paint);
        canvas.drawLine((col)*sellSize + sellSize*0.2f,
                row*sellSize + sellSize*0.2f, (col+1)*sellSize - sellSize*0.2f, (row+1)*sellSize - sellSize*0.2f, paint);

    }
    private void DrawX(Canvas canvas, int row, int col){
        paint.setColor(xColor);
        canvas.drawOval(col*sellSize + sellSize*0.2f, row*sellSize + sellSize*0.2f,
                (col*sellSize+sellSize) - sellSize*0.2f, (row*sellSize+ sellSize)
                        - sellSize*0.2f, paint);
    }

    private void DrawHorizLine(Canvas canvas, int row, int col){
        canvas.drawLine(0, row*sellSize + sellSize/2f,
                sellSize*3, row*sellSize + sellSize/2f, paint);
    }
    private void DrawVertLine(Canvas canvas, int row, int col){
        canvas.drawLine(col*sellSize + sellSize/2f, 0,
                col*sellSize + sellSize/2f, sellSize*3, paint);
    }
    private void DrawPosDiagonal(Canvas canvas){
        canvas.drawLine(0, 0,
                sellSize*3, sellSize*3, paint);
    }
    private void DrawNegDiagonal(Canvas canvas){
        canvas.drawLine(0, sellSize*3,
                sellSize*3, 0, paint);
    }
    private void DrawWinLine(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];
        switch (game.getWinType()[2]){
            case 1:
                DrawHorizLine(canvas, row, col);
                break;
            case 2:
                DrawVertLine(canvas, row, col);
                break;
            case 3:
                DrawPosDiagonal(canvas);
                break;
            case 4:
                DrawNegDiagonal(canvas);
                break;
        }
    }

    public void updateGame(){
        game.resetGame();
        winner = false;
    }

}
