package com.example.alex.assign_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class game extends AppCompatActivity {
    private String stdid, stdpw;

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

        ImageView imageButton;
        for (int i = 0; i < 7; i++) {
//            linearLayout[i]= (LinearLayout)findViewById(R.id.ll);
            for (int j = 0; j < 6; j++) {
                imageButton = new ImageView(this);
                imageButton.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                imageButton.setImageResource(R.drawable.empty_t);
                linearLayout[i].addView(imageButton);
//        TextView tv = new TextView(this);
            }
        }
    }
//        setContentView(R.layout.activity_game);
}
