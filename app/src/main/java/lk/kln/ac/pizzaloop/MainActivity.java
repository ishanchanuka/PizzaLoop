package lk.kln.ac.pizzaloop;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.hardware.camera2.TotalCaptureResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();
        recyclerView = /*(RecyclerView)*/  findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);
        loadProducts();
    }

    private void loadProducts() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://192.168.43.247:8080/demo/all",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this,"oK",Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonArray = response.getJSONArray("pizza_details");
                            for (int i=0; i< jsonArray.length();i++){
                                JSONObject pizza = jsonArray.getJSONObject(i);

                                String Name = pizza.getString("name");
                                String Description = pizza.getString("description");
                                double Price = pizza.getDouble("price");
                                String Image = pizza.getString("image_url");

                                productList.add(new Product(Name,Description,Price,Image));
                            }

                            adapter = new ProductAdapter(MainActivity.this,productList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

       requestQueue.add(request);
    }
}