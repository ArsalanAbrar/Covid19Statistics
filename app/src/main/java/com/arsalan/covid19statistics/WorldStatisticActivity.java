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

public class WorldStatisticActivity extends AppCompatActivity {
    public static LottieAnimationView lottie;
    Toolbar mtoolbar;
    private ArrayList<CountryListModel> dataset = new ArrayList<>();
    RecyclerView recyclerView;
    OpenBetAdapter openBetAdapter;
    Handler hander;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_statistic);
        recyclerView = (RecyclerView) findViewById(R.id.rvlist);
        lottie = (LottieAnimationView) findViewById(R.id.lottie_open);
        recyclerView.setVisibility(View.GONE);
        lottie.setVisibility(View.VISIBLE);
        final int delay = 2000;
        hander = new Handler();
        mtoolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        mtoolbar.findViewById(R.id.Imquestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorldStatisticActivity.this, WebiewActivity.class);
                i.putExtra("url", "who");
                startActivity(i);
            }
        });


// Your runnable that will be triggered.
//        runnable = new Runnable() {
//            public void run() {

        // post it again
//                hander.postDelayed(this, delay);
//            }
//        };
//// Start
//        hander.post(runnable);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
//        openBetFragmentViewModel = ViewModelProviders.of(this).get(OpenBetFragmentViewModel.class);
//        openBetFragmentViewMmodel.initOpenModel();
//        openBetFragmentViewModel.getOpenBetModel().observe(getViewLifecycleOwner(), new Observer<List<OpenBetModel>>() {
//            @Override
//            public void onChanged(List<OpenBetModel> openBetModels) {
//             openBetAdapter.notifyDataSetChanged();
//            }
//        });
        openBetAdapter = new OpenBetAdapter(getApplicationContext(), dataset);
        recyclerView.setAdapter(openBetAdapter);
//        loadindiastae();
        loaddata();


    }

    public void loaddata() {
//        showProgressDialog(this);
        dataset.clear();
        final String url = "https://covid-193.p.rapidapi.com/statistics";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("response")) {
                        JSONArray result = response.getJSONArray("response");
                        lottie.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        Log.d("arsalanabrar", response.toString());
                        for (int i = 0; i < result.length(); i++) {
                            String country = "", date = "", time = "", newcases = "", newdeath = "";
                            int totalcases, active, critical, recovered, totaldeath;
                            JSONObject jsonObject = result.getJSONObject(i);
                            country = jsonObject.getString("country");
                            date = jsonObject.getString("day");
                            time = jsonObject.getString("time");
                            JSONObject cases = jsonObject.getJSONObject("cases");
                            newcases = cases.getString("new");
                            active = cases.getInt("active");
                            critical = cases.getInt("critical");
                            recovered = cases.getInt("recovered");
                            totalcases = cases.getInt("total");
                            JSONObject death = jsonObject.getJSONObject("deaths");
                            newdeath = death.getString("new");
                            totaldeath = death.getInt("total");
                            Log.d("aaaa", String.valueOf(totaldeath));
                            dataset.add(i, new CountryListModel(country, totalcases, date, time, newcases, active, critical, recovered, newdeath, totaldeath));
                        }
                        recyclerView.setAdapter(openBetAdapter);
                        openBetAdapter.notifyDataSetChanged();
//                        dataset.add(i, new OpenBetModel(id, name, title, eventlive, eventlivetextcolor, openlist));
//                    }
//                    rvopen.setAdapter(openBetAdapter);
//                    openBetAdapter.notifyDataSetChanged();
//                  } else {
//                            hideProgressDialog();
//                            showToast(ReferEarnActivity.this, "Something is getting wrong. Please try again later.");
                    }

                } catch (JSONException e) {
//                    hideProgressDialog();
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                hideProgressDialog();
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
//                        showToast(ReferEarnActivity.this, errorMessage);
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
//                        showToast(ReferEarnActivity.this, errorMessage);
                    }
                } else {
                    String result = new String(networkResponse.data);
//                    String message = CommonMethod.getErrorData("message", result);
                    if (networkResponse.statusCode == 404) {
                        errorMessage = "Resource not found";
//                        showToast(ReferEarnActivity.this, errorMessage);
                    } else if (networkResponse.statusCode == 401) {
//                        errorMessage = message + " Unauthorized access";
//                        showToast(ReferEarnActivity.this, errorMessage);
                    } else if (networkResponse.statusCode == 400) {
//                        errorMessage = message + " Check your inputs";
//                        showToast(ReferEarnActivity.this, errorMessage);
                    } else if (networkResponse.statusCode == 500) {
//                        errorMessage = message + " Something is getting wrong";
//                        showToast(ReferEarnActivity.this, errorMessage);
                    } else if (networkResponse.statusCode == 502) {
//                        errorMessage = message + " Failed to connect server";
//                        showToast(ReferEarnActivity.this, errorMessage);
                    }
                }
//                showLog("Error " + errorMessage);
                error.printStackTrace();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
                headers.put("x-rapidapi_host", "covid-193.p.rapidapi.com");
                headers.put("x-rapidapi-key", "");
                return headers;
            }


            @Override
            protected com.android.volley.Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
//                statuscode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);

    }
}