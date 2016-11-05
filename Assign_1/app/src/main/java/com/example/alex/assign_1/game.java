package com.example.alex.assign_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayDeque;

public class game extends AppCompatActivity {
    private String stdid, stdpw;
    private boolean isRedTurn;
    private int[][] chess = new int[7][6];
    private ArrayDeque<Integer> sequence = new ArrayDeque<>();
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
        TextView gameTurn = new TextView(this);
        gameTurn = (TextView) findViewById(R.id.gameTurn);
        if (isRedTurn) {
            gameTurn.setText("Red Turn");
        } else {
            gameTurn.setText("Green Turn");
        }
        final TextView localgameTurn = gameTurn;
        Button retreat;
        retreat = (Button) findViewById(R.id.retreat);
        retreat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!gameSet){
                    if(sequence.isEmpty()){
                        Toast.makeText(game.this, "Please put at least one chess", Toast.LENGTH_SHORT).show();
                    }else {
                        isRedTurn = !isRedTurn;
                        int retreatj = sequence.pop();
                        int retreati = sequence.pop();
                        chess[retreati][retreatj] = 0;
                        imageButton[retreati][retreatj].setImageResource(R.drawable.empty_t);

                        if (isRedTurn) {
                            localgameTurn.setText("Red Turn");
                        } else {
                            localgameTurn.setText("Green Turn");
                        }
                    }
                }else{
                    Toast.makeText(game.this, "The game is over", Toast.LENGTH_SHORT).show();
                }

            }
        });
        linearLayout[0] = (LinearLayout) findViewById(R.id.ll1);
        linearLayout[1] = (LinearLayout) findViewById(R.id.ll2);
        linearLayout[2] = (LinearLayout) findViewById(R.id.ll3);
        linearLayout[3] = (LinearLayout) findViewById(R.id.ll4);
        linearLayout[4] = (LinearLayout) findViewById(R.id.ll5);
        linearLayout[5] = (LinearLayout) findViewById(R.id.ll6);
        linearLayout[6] = (LinearLayout) findViewById(R.id.ll7);


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
//                final TextView localgameTurn = gameTurn;
                imageButton[i][j] = new ImageView(this);
                imageButton[i][j].setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                imageButton[i][j].setImageResource(R.drawable.empty_t);
                //给每一个button添加监听事件
                imageButton[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isRedTurn) {
                            localgameTurn.setText("Red Turn");
                        } else {
                            localgameTurn.setText("Green Turn");
                        }
                        if (chess[locali][localj] == 0) {
                            for (int count = 5; count >= 0; count--) {
                                if (chess[locali][count] == 0 && !gameSet) {
                                    if (isRedTurn) {
                                        localimageButton[locali][count].setImageResource(R.drawable.red_t);
                                        isRedTurn = false;
                                        sequence.push(locali);
                                        sequence.push(count);
                                        chess[locali][count] = 1;
                                        ifWin(locali, count, 1);
                                        break;
                                    } else {
                                        localimageButton[locali][count].setImageResource(R.drawable.green_t);
                                        isRedTurn = true;
                                        sequence.push(locali);
                                        sequence.push(count);
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
        winEffect(i, j, color);
    }

    protected void horWin(int i, int j, int color) {
        //确保落下子的值还是原来的color
        chess[i][j] = color;
        int lcount = 0;
        int rcount = 0;
        //下面两个for判断横向4子
        for (int x = i; x >= 0; x--) {
            //判左边
            if (chess[x][j] == color) {
                lcount++;
            } else {
                break;
            }
        }
        for (int x = i; x < 7; x++) {
            //判右边
            if (chess[x][j] == color) {
                rcount++;
            } else {
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
            gameSet = true;
        }
    }

    protected void verWin(int i, int j, int color) {
        int dcount = 0;
        //确保落下子的值还是原来的color
        chess[i][j] = color;
        //下面一个for判断上下4子
        for (int x = j; x < 6; x++) {
            if (chess[i][x] == color) {
                dcount++;
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
            gameSet = true;
        }
    }

    protected void lcrossWin(int i, int j, int color) {
        //确保落下子的值还是原来的color
        chess[i][j] = color;
        int drcount = 0;
        int ulcount = 0;
        //下面2个for判断左对角线4子
        int tmpj = j;
        for (int x = i; x >= 0 && tmpj >= 0; x--, tmpj--) {
            //判左上
            if (chess[x][tmpj] == color) {
                ulcount++;
            } else {
                break;
            }
        }
        tmpj = j;
        for (int x = i; x < 6 && tmpj < 5; x++, tmpj++) {
            //判右下
            if (chess[x + 1][tmpj + 1] == color) {
                drcount++;
            } else {
                break;
            }
        }
        if (ulcount + drcount >= 4) {
            //全部放到一个数组里
            for (int hix = i - ulcount + 1, hij = j - ulcount + 1; hix < i + drcount + 1; hix++, hij++) {
                if (color == 1) {
                    chess[hix][hij] = 4;
                } else {
                    chess[hix][hij] = 5;
                }
            }
            gameSet = true;
        }
    }

    protected void rcrossWin(int i, int j, int color) {
        //确保落下子的值还是原来的color
        chess[i][j] = color;
        int urcount = 0;
        int dlcount = 0;
        //下面一个for判断右对角线4子
        int tmpj = j;
        for (int x = i; x < 7 && tmpj >= 0; x++, tmpj--) {
            //判右上
            if (chess[x][tmpj] == color) {
                urcount++;
            } else {
                break;
            }
        }
        tmpj = j;
        for (int x = i; x > 0 && tmpj < 5; x--, tmpj++) {
            //判左下
            if (chess[x - 1][tmpj + 1] == color) {
                dlcount++;
            } else {
                break;
            }
        }

        if (urcount + dlcount >= 4) {

            //全部放到一个数组里
            for (int hix = i - dlcount, hij = j + dlcount; hix < i + urcount; hix++, hij--) {
                if (color == 1) {
                    chess[hix][hij] = 4;
                } else {
                    chess[hix][hij] = 5;
                }
            }
            gameSet = true;
        }
    }

    protected void winEffect(int i, int j, int color) {
        if (gameSet) {
            if (color == 1) {
                chess[i][j] = 4;
            } else {
                chess[i][j] = 5;
            }
            for (int hii = 0; hii < 7; hii++) {
                for (int hij = 0; hij < 6; hij++) {
                    if (chess[hii][hij] == 4) {
                        imageButton[hii][hij].setImageResource(R.drawable.red_wint);
                    } else if (chess[hii][hij] == 5) {
                        imageButton[hii][hij].setImageResource(R.drawable.green_wint);
                    }
                }
            }

            if (color == 1) {
                Toast.makeText(game.this, "Red win", Toast.LENGTH_SHORT).show();
//            gameTurn.setText("Red Win");
            } else {
                Toast.makeText(game.this, "Green win", Toast.LENGTH_SHORT).show();
//            gameTurn.setText("Green Win");
            }
        }
    }
}