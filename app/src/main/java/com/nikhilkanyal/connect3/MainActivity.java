package com.nikhilkanyal.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0; int count=0;
    //0=red ,1= yellow
    int[] gameState={2,2,2,2,2,2,2,2,2};//2=unplayed
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && count!=1)
        { gameState[tappedCounter]=activePlayer;
        counter.setTranslationY(-1000f);


        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.red);
            activePlayer=1;
        }else{ counter.setImageResource(R.drawable.yellow);
        activePlayer=0;
        }
        counter.animate().translationYBy(1000f).rotation(300).setDuration(300);
            for(int[] winningPos:winningPositions)//checks all winning postion conditons
            {
                if(gameState[winningPos[0]]==gameState[winningPos[1]] &&
                        gameState[winningPos[1]]==gameState[winningPos[2]] &&gameState[winningPos[0]]!=2)
                { count++;

                    String winner="Red";
                    if(gameState[winningPos[0]]==1)
                    {
                        winner="Yellow";
                    }

                    TextView winnerMessage=(TextView)findViewById(R.id.textView);
                    winnerMessage.setText(winner+"  has won ");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.winnerId);
                    layout.setVisibility(View.VISIBLE);

                }else{ int check=0;
                    for(int i=0;i<gameState.length;i++)
                    {
                        if(gameState[i]==2){
                            check=1;
                        }
                    }
                    if(check==0) {
                        TextView winnerMessage = (TextView) findViewById(R.id.textView);
                        winnerMessage.setText("  It's a draw ");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.winnerId);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
    }
    }
    public void playAgain(View view)
    {
        LinearLayout layout = (LinearLayout)findViewById(R.id.winnerId);
        layout.setVisibility(View.INVISIBLE);//turing layout invivible

        activePlayer=0;
        //0=red ,1= yellow
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }//setting played values to default value again

        GridLayout grid=(GridLayout)findViewById(R.id.gridLayoutId);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
        count=0;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
