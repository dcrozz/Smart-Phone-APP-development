package com.example.alex.assign_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class game extends AppCompatActivity {
    private String stdid, stdpw;
    private boolean isWhiteTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
//        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);//获取root RL
//        LinearLayout linearLayout1 = new LinearLayout(this);
//        Display display = getWindowManager().getDefaultDisplay(); 之后全部用代码画


//        Intent intent = getIntent();
//        stdid = intent.getStringExtra("ID");
//        stdpw = intent.getStringExtra("PW");
//        TextView tv = new TextView(this);
//        tv.setText(stdid + '\n' + stdpw);
//        setContentView(tv);

        LinearLayout linearLayout[]= new LinearLayout[7];
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout[0] = (LinearLayout)findViewById(R.id.ll1);
        linearLayout[1] = (LinearLayout)findViewById(R.id.ll2);
        linearLayout[2] = (LinearLayout)findViewById(R.id.ll3);
        linearLayout[3] = (LinearLayout)findViewById(R.id.ll4);
        linearLayout[4] = (LinearLayout)findViewById(R.id.ll5);
        linearLayout[5] = (LinearLayout)findViewById(R.id.ll6);
        linearLayout[6] = (LinearLayout)findViewById(R.id.ll7);

        ImageView imageButton[][] = new ImageView[7][6];
        final boolean[][] chess = new boolean[7][6];
        for (int i = 0; i < 7; i++){
            for(int j=0;j<6; j++){
                chess[i][j] = false;
            }
        }
//        chess[0][0] = false;
        for (int i = 6; i >=0 ; i--) {
            for (int j = 5; j >= 0; j--) {
                final int locali = i;
                final int localj = j;
                final ImageView localimageButton[][] = imageButton;
                imageButton[i][j] = new ImageView(this);
                imageButton[i][j].setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                imageButton[i][j].setImageResource(R.drawable.empty_t);
                imageButton[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!chess[locali][localj]) {
                            for (int count = 0; count < 6 ; count++) {
                                if (!chess[locali][count]) {
                                    if (isWhiteTurn) {
                                        localimageButton[locali][count].setImageResource(R.drawable.red_t);
                                        isWhiteTurn = false;
                                        chess[locali][count] = true;
                                        break;
                                    } else {
                                        localimageButton[locali][count].setImageResource(R.drawable.green_t);
                                        isWhiteTurn = true;
                                        chess[locali][count] = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
                linearLayout[i].addView(imageButton[i][j]);
//        TextView tv = new TextView(this);
            }
        }

    }
//        setContentView(R.layout.activity_game);
}
