package com.example.alex.assign_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class index extends AppCompatActivity {
    TextView stdid,stdpw;
    Button btn_start,btn_quit,btn_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
//        ImageView imageView =new ImageView(this);
//        imageView.setX(20);
//        imageView.setY(20);
//        imageView.setImageResource(R.drawable.ingress_logo_bw);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_quit = (Button) findViewById(R.id.btn_quit);
        btn_about = (Button) findViewById(R.id.btn_about);
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
        btn_about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("https://www.ingress.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
