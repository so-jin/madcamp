package com.example.madcamp_week_2.UI.Address;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madcamp_week_2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    public ArrayList<AddressItem> address_items;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_shop);

        jsonParsing(getJsonString());

        GridView gridView = findViewById(R.id.gridview_shop);
        CustomArrayAdapter gridViewAdapter = new CustomArrayAdapter(getApplicationContext(), R.layout.list_shop, address_items);
        gridView.setAdapter(gridViewAdapter);
    }

    private String getJsonString(){
        String json = "";
        try {
            InputStream is = getApplicationContext().getAssets().open("shop.json");
            int fileSize = is.available();
            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return json;
    }

    private void jsonParsing(String json)
    {
        address_items = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("Data");

            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject item = jsonArray.getJSONObject(i);
                AddressItem addressItem = new AddressItem(item.getString("wrkp_nm"), item.getString("site_tel"));
                address_items.add(addressItem);
            }
        }catch (JSONException e) { e.printStackTrace(); }
    }
}
