package com.arsalan.covid19statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.arsalan.covid19statistics.Adapter.OpenBetAdapter;
import com.arsalan.covid19statistics.Model.CountryListModel;
import com.arsalan.covid19statistics.VolleyNetwork.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    ImageView imindia, imworld;
    TextView tvclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imindia = (ImageView) findViewById(R.id.ImIndia);
        imworld = (ImageView) findViewById(R.id.ImWorld);
        tvclick = (TextView) findViewById(R.id.tvclick);
        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        mtoolbar.findViewById(R.id.Imquestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WebiewActivity.class);
                i.putExtra("url","who");
                startActivity(i);
            }
        });
        tvclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,WebiewActivity.class);
                i.putExtra("url","google");
                startActivity(i);
            }
        });

        imworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WorldStatisticActivity.class);
                startActivity(i);
            }
        });
        imindia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, IndiaStatisticsActivity.class);
                startActivity(i);

            }
        });


    }
}
