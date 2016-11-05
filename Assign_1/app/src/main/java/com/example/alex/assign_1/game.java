package com.example.alex.assign_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class game extends AppCompatActivity {
    private String stdid, stdpw;
    private boolean isRedTurn;
    private int[][] chess = new int[7][6];
    private ImageView imageButton[][] = new ImageView[7][6];
    private boolean gameSet = false;

    //none = 0, red = 1, green =2，fin = 3, hired = 4, higreen = 5
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
        LinearLayout linearLayout[] = new LinearLayout[7];
        linearLayout[0] = (LinearLayout) findViewById(R.id.ll1);
        linearLayout[1] = (LinearLayout) findViewById(R.id.ll2);
        linearLayout[2] = (LinearLayout) findViewById(R.id.ll3);
        linearLayout[3] = (LinearLayout) findViewById(R.id.ll4);
        linearLayout[4] = (LinearLayout) findViewById(R.id.ll5);
        linearLayout[5] = (LinearLayout) findViewById(R.id.ll6);
        linearLayout[6] = (LinearLayout) findViewById(R.id.ll7);
//
        //初始化chess数组
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                chess[i][j] = 0;
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                final int locali = i;
                final int localj = j;
                final ImageView localimageButton[][] = imageButton;
                imageButton[i][j] = new ImageView(this);
                imageButton[i][j].setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                imageButton[i][j].setImageResource(R.drawable.empty_t);
                //给每一个button添加监听事件
                imageButton[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chess[locali][localj] == 0) {
                            for (int count = 5; count >= 0; count--) {
                                if (chess[locali][count] == 0 && !gameSet) {
                                    if (isRedTurn) {
                                        localimageButton[locali][count].setImageResource(R.drawable.red_t);
                                        isRedTurn = false;
                                        chess[locali][count] = 1;
                                        ifWin(locali, count, 1);
                                        break;
                                    } else {
                                        localimageButton[locali][count].setImageResource(R.drawable.green_t);
                                        isRedTurn = true;
                                        chess[locali][count] = 2;
                                        ifWin(locali, count, 2);
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

    protected void ifWin(int i, int j, int color) {
        horWin(i, j, color);
        verWin(i, j, color);
        lcrossWin(i, j, color);
        rcrossWin(i, j, color);
    }

    protected void horWin(int i, int j, int color) {
        int lcount = 0;
        int rcount = 0;
        //下面两个for判断横向4子
        for (int x = i; x >= 0; x--) {
            //判左边
            if (chess[x][j] == color) {
                lcount++;
//                    System.out.println(Integer.toString(color) + "lcount:" + Integer.toString(lcount));
            } else {
//                    continue;
                break;
            }
        }
        for (int x = i; x < 7; x++) {
            //判右边
            if (chess[x][j] == color) {
                rcount++;
//                    System.out.println(Integer.toString(color) + "rcount:" + Integer.toString(rcount));
            } else {
//                    continue;
                break;
            }
        }
        if (lcount + rcount >= 5) {
            //全部放到一个数组里
            for (int x = i - lcount + 1; x < i + rcount; x++) {
                if (color == 1) {
                    chess[x][j] = 4;
                } else {
                    chess[x][j] = 5;
                }
            }
            winEffect(color);
        }
    }

    protected void verWin(int i, int j, int color) {
        int dcount = 0;
        //下面一个for判断上下4子
        for (int x = j; x < 6; x++) {
            if (chess[i][x] == color) {
                dcount++;
//                System.out.println(Integer.toString(color) + "dcount:" + Integer.toString(dcount));
            } else {
                break;
            }
        }
        if (dcount >= 4) {
            //放到一个数组里
            for (int x = j; x < j + dcount; x++) {
                if (color == 1) {
                    chess[i][x] = 4;
                } else {
                    chess[i][x] = 5;
                }
            }
            winEffect(color);
        }
    }

    protected void lcrossWin(int i, int j, int color) {
        int drcount = 0;
        int ulcount = 0;
        //下面2个for判断左对角线4子
        int tmpj = j;
        for (int x = i; x >= 0 && tmpj >= 0; x--, tmpj--) {
            //判左上
            if (chess[x][tmpj] == color) {
                ulcount++;
//                if (color==1) {
//                    System.out.println("red:------->" + x + tmpj + "ulcount----->" + ulcount);
//                }else{
//                    System.out.println("green:------->" + x + tmpj + "ulcount----->" + ulcount);
//                }
            } else {
                break;
            }
        }
        tmpj = j;
        for (int x = i; x < 6 && tmpj < 5; x++, tmpj++) {
            //判右下
            if (chess[x + 1][tmpj + 1] == color) {
                drcount++;
//                if(color==1){
//                    System.out.println("red:------->"+x+tmpj+"drcount----->"+drcount);
//                }else{
//                    System.out.println("green:------->"+x+tmpj+"drcount----->"+drcount);
//                }
            } else {
                break;
            }
        }
        //for testing
//                    if(color==1){
//                        System.out.println("red:------->"+drcount+ulcount);
//                    }else{
//                        System.out.println("green:------->"+drcount+ulcount);
//                    }
        if (ulcount + drcount >= 4) {
            //全部放到一个数组里
            for (int hix = i - ulcount +1, hij = j-ulcount + 1; hix<i+drcount; hix++, hij++) {
                if (color == 1) {
                    chess[hix][hij] = 4;
                } else {
                    chess[hix][hij] = 5;
                }
            }
            winEffect(color);
        }
    }

    protected void rcrossWin(int i, int j, int color) {
        int urcount = 0;
        int dlcount = 0;
        //下面一个for判断右对角线4子
        int tmpj = j;
        for (int x = i; x < 7 && tmpj >= 0; x++, tmpj--) {
            //判右上
            if (chess[x][tmpj] == color) {
                urcount++;
//                if (color==1) {
//                    System.out.println("red:------->" + x + tmpj + "urcount----->" + urcount);
//                }else{
//                    System.out.println("green:------->" + x + tmpj + "urcount----->" + urcount);
//                }
            } else {
                break;
            }
        }
        tmpj = j;
        for (int x = i; x > 0 && tmpj < 5; x--, tmpj++) {
            //判左下
            if (chess[x - 1][tmpj + 1] == color) {
                dlcount++;
//                if(color==1){
//                    System.out.println("red:------->"+x+tmpj+"dlcount----->"+dlcount);
//                }else{
//                    System.out.println("green:------->"+x+tmpj+"dlcount----->"+dlcount);
//                }
            } else {
                break;
            }
        }
        //for testing
//                    if(color==1){
//                        System.out.println("red:------->"+drcount+ulcount);
//                    }else{
//                        System.out.println("green:------->"+drcount+ulcount);
//                    }
        if (urcount + dlcount >= 4) {

            //全部放到一个数组里
            for (int hix = i - dlcount +1, hij = j + dlcount + 1; hix<i+urcount; hix++, hij--) {
                if (color == 1) {
                    chess[hix][hij] = 4;
                } else {
                    chess[hix][hij] = 5;
                }
            }
            winEffect(color);
        }
    }

    protected void winEffect(int color) {
        gameSet=true;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (chess[i][j] == 4) {
                    imageButton[i][j].setImageResource(R.drawable.red_wint);
                } else if (chess[i][j] == 5) {
                    imageButton[i][j].setImageResource(R.drawable.green_wint);
                }
            }
        }
        if (color == 1) {
            Toast.makeText(game.this, "Red win", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(game.this, "Green win", Toast.LENGTH_SHORT).show();
        }
    }

}
