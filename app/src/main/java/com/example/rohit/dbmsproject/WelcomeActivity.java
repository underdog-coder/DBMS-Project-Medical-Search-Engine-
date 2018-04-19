package com.example.rohit.dbmsproject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class WelcomeActivity  extends AppCompatActivity{
    private EditText search;
    public static String query="";
    String[] strings = {};
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static  int flag = 0;
    private static  final String msg = "message";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private  int radioID;
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_layout);
        ImageView symbol = findViewById(R.id.symbol);
        search = findViewById(R.id.search);
        Button searchbttn = findViewById(R.id.searchBttn);
        radioGroup = findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                search.setText("");
            }
        });

        searchbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query =  search.getText().toString();
                if(query.compareTo("") == 0){
                    Context context = getApplicationContext();
                    CharSequence sequence = "Incorrect Input!!";
                    int time = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,sequence,time);
                    toast.show();
                }
                else {
                    radioID = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID);
                    if(radioButton.getText().toString().compareTo("Medicine") == 0){
                        JsonParser1 jsonParser1 = new JsonParser1();
                        jsonParser1.execute(query);
                    }
                    else if (radioButton.getText().toString().compareTo("Disease") == 0){
                        JsonParser jsonParser = new JsonParser();
                        jsonParser.execute(query);
                    }
                }
            }

        });

    }

    @SuppressLint("StaticFieldLeak")
    class JsonParser extends AsyncTask<String,Void,Void> {
        private  String data = "";
        private String parsed ="";
        private String dataParsed ="";
        private String parsed1="";
        private String dataParsed1="";
        private ProgressDialog progressDialog ;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(WelcomeActivity.this);
            super.onPreExecute();
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override

        protected Void doInBackground(String... params) {
            String que = params[0];
            String search_address = "https://waterproofed-fellow.000webhostapp.com/disease.php";

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
                data = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;

                }
                dataParsed = "";
                dataParsed1 = "";
                JSONArray jsonArray = new  JSONArray(data);
                JSONObject[] jsonObjects = new JSONObject[jsonArray.length()+1];
                for( int i = 0; i < jsonArray.length(); i++){
                    jsonObjects[i] = (JSONObject)jsonArray.get(i);
                    if(i == 0){
                        parsed = jsonObjects[i].get(Integer.toString(i+1)) + "";
                        dataParsed = dataParsed + parsed ;
                    }else {
                        parsed1 = jsonObjects[i].get(Integer.toString(i+1)) +"";
                        dataParsed1 = dataParsed1 + "->" + parsed1 + "\n";
                    }
                }
                bufferedReader.close();
                inputStream.close();
            } catch (IOException | JSONException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if(data.compareTo(" []null") == 0 || data.compareTo("") == 0){
                Toast.makeText(WelcomeActivity.this,"Poor Connection or Wrong Disease Name",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(WelcomeActivity.this, Result.class);
                intent.putExtra("id",dataParsed);
                intent.putExtra("id1",dataParsed1);
                intent.putExtra("diseaseName",query);
                startActivity(intent);
                Log.d(msg,dataParsed);
            }
        }

    }


    class JsonParser1 extends AsyncTask<String,Void,Void> {
        private  String data = "";
        private ProgressDialog progressDialog ;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(WelcomeActivity.this);
            super.onPreExecute();
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override

        protected Void doInBackground(String... params) {
            String que = params[0];
            String search_address = "https://waterproofed-fellow.000webhostapp.com/onlymedicine.php";

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
                data = "";
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
            if(data.compareTo(" []null") == 0 || data.compareTo("") == 0){
                Toast.makeText(WelcomeActivity.this,"Poor Connection or Wrong Medicine Name",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(WelcomeActivity.this,Medicine.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        }

    }


}
