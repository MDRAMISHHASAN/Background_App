package com.example.backgroundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

   /*
    public void buttonClicked(View view)
    {
        //textView.setText("Bye Ramish");

        Toast.makeText(this, "the button has pressed", Toast.LENGTH_SHORT).show();

       }

   */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BG myTask = new BG();
        myTask.execute("https://www.codewithharry.com/");


        final TextView textView = findViewById(R.id.textViewId);
        Button  button = findViewById(R.id.buttonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Bye Ramish");
            }
        });
    }


}



class BG extends AsyncTask<String,Void,String>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("ramish", "onPreExecute: run");
    }

    @Override
    protected String doInBackground(String... urls) {
        Log.d("ramish", "doInBackground: run");
        String result = "";

        URL url;
        HttpURLConnection httpURLConnection;
        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data != -1)
            {
                char current = (char) data;
                result += current;
                data = reader.read();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Something went wrong";
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("ramish", "onPostExecute: run");
        Log.d("ramish",s);
    }
}
