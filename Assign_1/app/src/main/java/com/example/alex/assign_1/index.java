package com.example.alex.assign_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class index extends AppCompatActivity {
    TextView stdid,stdpw;
    Button btn_start,btn_quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ImageView imageView =new ImageView(this);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setImageResource(R.drawable.login);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_quit = (Button) findViewById(R.id.btn_quit);
        stdid = (TextView) findViewById(R.id.t_stdid);
        stdpw = (TextView) findViewById(R.id.t_stdpw);
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ //wtf is arg0, Y view here
                Intent myIntent = new Intent(view.getContext(),game.class);
                myIntent.putExtra("ID",stdid.getText().toString());
                myIntent.putExtra("PW",stdpw.getText().toString());
                startActivityForResult(myIntent,0);
            }
        });
        btn_quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
