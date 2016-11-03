package com.example.alex.assign_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class game extends AppCompatActivity {
    private String stdid, stdpw;
    private boolean isRedTurn;
    private int[][] chess = new int[7][6];
    //none = 0, red = 1, green =2，fin = 3
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
        linearLayout[0] = (LinearLayout)findViewById(R.id.ll1);
        linearLayout[1] = (LinearLayout)findViewById(R.id.ll2);
        linearLayout[2] = (LinearLayout)findViewById(R.id.ll3);
        linearLayout[3] = (LinearLayout)findViewById(R.id.ll4);
        linearLayout[4] = (LinearLayout)findViewById(R.id.ll5);
        linearLayout[5] = (LinearLayout)findViewById(R.id.ll6);
        linearLayout[6] = (LinearLayout)findViewById(R.id.ll7);
        ImageView imageButton[][] = new ImageView[7][6];
        for(int i = 0; i < 7; i++){
            for(int j=0; j < 6; j++){
                chess[i][j] = 0;
            }
        }
        for (int i = 0; i <7 ; i++) {
            for (int j = 0; j <6; j++) {
                final int locali = i;
                final int localj = j;
                final ImageView localimageButton[][] = imageButton;
                imageButton[i][j] = new ImageView(this);
                imageButton[i][j].setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                imageButton[i][j].setImageResource(R.drawable.empty_t);
                imageButton[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chess[locali][localj] == 0) {
                            for (int count = 5; count >= 0 ; count--) {
                                if (chess[locali][count] == 0) {
                                    if (isRedTurn) {
                                        localimageButton[locali][count].setImageResource(R.drawable.red_t);
                                        isRedTurn = false;
                                        chess[locali][count] = 1;
                                        ifWin(locali,count,1);
                                        break;
                                    } else {
                                        localimageButton[locali][count].setImageResource(R.drawable.green_t);
                                        isRedTurn = true;
                                        chess[locali][count] = 2;
                                        ifWin(locali,count,2);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
                linearLayout[i].addView(imageButton[i][j]);
            }
        }
    }
    protected void ifWin(int i, int j, int color){
        int lcount = 0;
        int rcount = 0;
        int dcount = 0;
        int ulcount = 0;
        int urcount = 0;
        int dlcount = 0;
        int drcount = 0;
            for(int x=i;x>=0;x--){
                //判左边
                if(chess[x][j]==color){
                    lcount++;
                    System.out.println(Integer.toString(color) + "lcount:" + Integer.toString(lcount));
                }else{
                    continue;
                }
            }
            for(int x=i;x<7;x++){
                //判右边
                if(chess[x][j]==color){
                    rcount++;
                    System.out.println(Integer.toString(color) + "rcount:" + Integer.toString(rcount));
                }else{
                    continue;
                }
            }
            if(lcount+rcount==5){
                if(color==1){
                    System.out.println("Red WIN");
                }else{
                    System.out.println("Green WIN");
                }
            }

        for(int x=j;x<6;x++){
            if(chess[i][x]==color){
                dcount++;
                System.out.println(Integer.toString(color) + "dcount:" + Integer.toString(dcount));
                if(dcount==4){
//                    for(int a = 0; a < 7; i++){
//                        for(int b=0; b < 6; j++){
//                            chess[a][b] = 3;
//                        }
//                    }
                    if(color==1){
                        System.out.println("Red WIN");
                    }else{
                        System.out.println("Green WIN");
                    }
                }
            }else{
                continue;
            }
        }
        int tmpj = j;
        for(int x=i;x>=0&&tmpj>=0;x--,tmpj--){
            //判左上
            if(chess[x][tmpj]==color){
                ulcount++;
                if (color==1) {
                    System.out.println("red:------->" + x + tmpj + "ulcount----->" + ulcount);
                }else{
                    System.out.println("green:------->" + x + tmpj + "ulcount----->" + ulcount);
                }
            }else{
                break;
            }
        }
        tmpj = j;
        for(int x=i;x<6&&tmpj<5;x++,tmpj++){
            //判右下
            if(chess[x+1][tmpj+1]==color){
                drcount++;
                if(color==1){
                    System.out.println("red:------->"+x+tmpj+"drcount----->"+drcount);
                }else{
                    System.out.println("green:------->"+x+tmpj+"drcount----->"+drcount);
                }
            }else{
                break;
            }
        }
                    //for testing
//                    if(color==1){
//                        System.out.println("red:------->"+drcount+ulcount);
//                    }else{
//                        System.out.println("green:------->"+drcount+ulcount);
//                    }
        if(ulcount+drcount>=4){
            if(color==1){
                System.out.println("Red WIN");
            }else{
                System.out.println("Green WIN");
            }
        }
        tmpj = j;
        for(int x=i;x<7&&tmpj>=0;x++,tmpj--){
            //判右上
            if(chess[x][tmpj]==color){
                urcount++;
                if (color==1) {
                    System.out.println("red:------->" + x + tmpj + "urcount----->" + urcount);
                }else{
                    System.out.println("green:------->" + x + tmpj + "urcount----->" + urcount);
                }
            }else{
                break;
            }
        }
        tmpj = j;
        for(int x=i;x>0&&tmpj<5;x--,tmpj++){
            //判左下
            if(chess[x-1][tmpj+1]==color){
                dlcount++;
                if(color==1){
                    System.out.println("red:------->"+x+tmpj+"dlcount----->"+dlcount);
                }else{
                    System.out.println("green:------->"+x+tmpj+"dlcount----->"+dlcount);
                }
            }else{
                break;
            }
        }
        //for testing
//                    if(color==1){
//                        System.out.println("red:------->"+drcount+ulcount);
//                    }else{
//                        System.out.println("green:------->"+drcount+ulcount);
//                    }
        if(urcount+dlcount>=4){
            if(color==1){
                System.out.println("Red WIN");
            }else{
                System.out.println("Green WIN");
            }
        }
    }
}
