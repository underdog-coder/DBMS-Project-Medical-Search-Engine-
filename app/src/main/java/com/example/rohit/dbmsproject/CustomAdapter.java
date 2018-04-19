package com.example.rohit.dbmsproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private String data="";
    private List<MedicineData> data_list;

    public CustomAdapter(Context context, List <MedicineData> data_list) {
        this.context = context;
        this.data_list = data_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.med_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.med_name.setText(data_list.get(position).getMed_name());
        holder.unit_num.setText(data_list.get(position).getUnit());
        holder.manufacturer_num.setText(data_list.get(position).getManufacturer());
        holder.category_num.setText(data_list.get(position).getCategory());
        holder.price_num.setText(data_list.get(position).getPrice());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonParser1 jsonParser = new JsonParser1();
                jsonParser.execute(data_list.get(position).getMed_name());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView med_name;
        public TextView unit_num;
        public TextView price;
        public TextView category;
        public TextView manufacturer;
        public TextView medicine;
        public TextView unit;
        public TextView price_num;
        public TextView category_num;
        public TextView manufacturer_num;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            med_name = itemView.findViewById(R.id.medicine_name);
            unit_num = itemView.findViewById(R.id.unit_num);
            price_num = itemView.findViewById(R.id.price_num);
            category_num = itemView.findViewById(R.id.category_num);
            manufacturer_num = itemView.findViewById(R.id.manufacturer_num);
            medicine = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.textView2);
            unit = itemView.findViewById(R.id.unit);
            manufacturer = itemView.findViewById(R.id.manufacturer);
            cardView = itemView.findViewById(R.id.med_card);
        }
    }
    public class JsonParser1 extends AsyncTask<String,Void,Void> {
        private ProgressDialog progressDialog;
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
                data="";
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
                Intent intent = new Intent(context,MedicalStore.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        }

    }


}
