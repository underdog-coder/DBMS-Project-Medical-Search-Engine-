package com.example.rohit.dbmsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MedicalStore extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomAdapter2 customAdapter;
    private List<MedicalStoreData> data_list;
    private GridLayoutManager gridLayoutManager;
    private String data="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_store_layout);
        recyclerView = findViewById(R.id.medical_sre_layout);

        data_list = new ArrayList<>();
        data="";
        data = getIntent().getExtras().getString("data");
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()+1];
            for( int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = (JSONObject)jsonArray.get(i);
                MedicalStoreData medicalStoreData = new MedicalStoreData(jsonObjects[i].getString("medical_store_name"),jsonObjects[i].getString("medical_store_address"));
                data_list.add(medicalStoreData);
                Log.d("a",medicalStoreData.getMedicalStoreAddress()+medicalStoreData.getMedicalStoreName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("size",data_list.size()+"");
        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        customAdapter = new CustomAdapter2(MedicalStore.this,data_list);
        recyclerView.setAdapter(customAdapter);
    }
}
