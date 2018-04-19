package com.example.rohit.dbmsproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

public class JsonParser1 extends AsyncTask<String,Void,Void> {
    private  String data = "";
    private ProgressDialog progressDialog ;
    private Context context;

    public JsonParser1(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        super.onPreExecute();
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override

    protected Void doInBackground(String... params) {
        String que = params[0];
        String search_address = "https://waterproofed-fellow.000webhostapp.com/medical_store.php";

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
    }

    public String getData() {
        return data;
    }
}