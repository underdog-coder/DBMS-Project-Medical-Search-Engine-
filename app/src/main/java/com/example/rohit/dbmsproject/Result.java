package com.example.rohit.dbmsproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Result extends AppCompatActivity{
    private String disease_name ="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        String a = getIntent().getStringExtra("id");
        String b = getIntent().getStringExtra("id1");
        TextView diseaseName = findViewById(R.id.disease_name);
        ImageView resultImg = findViewById(R.id.result_image);
        CardView description = findViewById(R.id.descprition_card);
        CardView symptoms = findViewById(R.id.symptom_card);
        TextView disease_description = findViewById(R.id.disease_description);
         disease_description.setText(a);
        TextView symptoms_d = findViewById(R.id.symptoms);
         symptoms_d.setText(b);
         disease_name = getIntent().getStringExtra("diseaseName").toUpperCase();
         diseaseName.setText(disease_name);
        CardView medicine = findViewById(R.id.medicine_card);
         medicine.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 JsonParser jsonParser = new JsonParser();
                 jsonParser.execute(disease_name);
             }
         });
    }

    class JsonParser extends AsyncTask<String,Void,Void> {
        private  String data = "";
        private ProgressDialog progressDialog ;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Result.this);
            super.onPreExecute();
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override

        protected Void doInBackground(String... params) {
            String que = params[0];
            String search_address = "https://waterproofed-fellow.000webhostapp.com/medicine.php";

            try {
                URL search_url = new URL(search_address);
                HttpURLConnection httpURLConnection1 = (HttpURLConnection) search_url.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoOutput(true);
                httpURLConnection1.setDoInput(true);
                OutputStream outputStream = httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("query1","UTF-8") + "="+ URLEncoder.encode(que,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection1.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line ="";

                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                bufferedReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Intent intent = new Intent(Result.this,Medicine.class);
            intent.putExtra("data",data);
            startActivity(intent);
        }

    }

}
