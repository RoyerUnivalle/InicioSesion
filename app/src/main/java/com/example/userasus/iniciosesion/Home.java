package com.example.userasus.iniciosesion;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class Home extends AppCompatActivity {


    BackGroud task=null;
    Boolean taskOn=false;
    TextView datosUser;
    TextView backData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle recibo = getIntent().getExtras();

        datosUser = (TextView) findViewById(R.id.etUser);
        String nombre =recibo.getString("name").toString();
        String id =recibo.getString("id").toString();
        datosUser.setText(""+nombre+" - "+id);

    }

    public void volver(View v){
        Intent volver = new Intent(Home.this,MainActivity.class);
        volver.addFlags(volver.FLAG_ACTIVITY_CLEAR_TOP | volver.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(volver);
    }

    public void backgroud(View v){
        task = new BackGroud();
        task.execute();
        taskOn=true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(taskOn){
            task.cancel(true);
        }
    }

    public class BackGroud extends AsyncTask<Void,String,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            backData = (TextView) findViewById(R.id.tvBack);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            int x=0;
            String letras[] = new String[]{"A","B","C"};
            while (x<20){
                try {
                    Thread.sleep(3000);
                    int key= (int)Math.floor(Math.random()*3);
                    publishProgress("*"+letras[key]+"*");
                    x++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            backData.append(" "+values[0]+"\n");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(taskOn){
                task.cancel(true);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            task=null;
        }
    }
}
