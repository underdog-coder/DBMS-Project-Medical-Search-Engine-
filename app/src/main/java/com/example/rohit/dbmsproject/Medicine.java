package com.example.rohit.dbmsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Medicine extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private List<MedicineData> data_list;
    private GridLayoutManager gridLayoutManager;
    private String data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_layout);
        recyclerView = findViewById(R.id.recyclerView);
        data_list = new ArrayList<>();
        data = getIntent().getExtras().getString("data");
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject[] jsonObjects = new JSONObject[jsonArray.length()+1];
            for( int i = 0; i < jsonArray.length(); i++){
                jsonObjects[i] = (JSONObject)jsonArray.get(i);
                MedicineData medicineData = new MedicineData(jsonObjects[i].getString("medicine_name"),jsonObjects[i].getString("unit"),jsonObjects[i].getString("medicine_price"),jsonObjects[i].getString("medicine_category"),jsonObjects[i].getString("medicine_manufacturer"));
                data_list.add(medicineData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        customAdapter = new CustomAdapter(this,data_list);
        recyclerView.setAdapter(customAdapter);

    }

}
