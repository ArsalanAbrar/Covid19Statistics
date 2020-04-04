package com.arsalan.covid19statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.arsalan.covid19statistics.Adapter.StateModelAdapter;
import com.arsalan.covid19statistics.Model.CountryListModel;
import com.arsalan.covid19statistics.Model.StateModel;
import com.arsalan.covid19statistics.VolleyNetwork.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IndiaStatisticsActivity extends AppCompatActivity {

    TextView tvtime, tvconfirm, tvactive, tvrecovered, tvdeath, tvconfirmincr, tvactiveincr, tvrecoverincr, tvdeathincr;
    TextView tvtotalsample;
    RecyclerView rvstate;
    StateModelAdapter stateModelAdapter;
    private ArrayList<StateModel> dataset = new ArrayList<>();
    Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_statistics);
        tvtime = (TextView) findViewById(R.id.tvtime);
        tvactive = (TextView) findViewById(R.id.tvactivecase);
        tvconfirm = (TextView) findViewById(R.id.tvconfirmedcase);
        tvrecovered = (TextView) findViewById(R.id.tvrecoveredcase);
        tvdeath = (TextView) findViewById(R.id.tvdeathcase);
        tvactiveincr = (TextView) findViewById(R.id.tvactivecaseincr);
        tvconfirmincr = (TextView) findViewById(R.id.tvconfirmedcaseincr);
        tvrecoverincr = (TextView) findViewById(R.id.tvrecoveredcaseincr);
        tvdeathincr = (TextView) findViewById(R.id.tvdeathcaseincr);
        tvtotalsample = (TextView) findViewById(R.id.tvtotalsample);
        rvstate = (RecyclerView) findViewById(R.id.rvstate);
        mtoolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        mtoolbar.findViewById(R.id.Imquestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IndiaStatisticsActivity.this, WebiewActivity.class);
                i.putExtra("url","testing");
                startActivity(i);
            }
        });

        rvstate.setHasFixedSize(true);
        rvstate.setNestedScrollingEnabled(false);
        rvstate.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
//        openBetFragmentViewModel = ViewModelProviders.of(this).get(OpenBetFragmentViewModel.class);
//        openBetFragmentViewMmodel.initOpenModel();
//        openBetFragmentViewModel.getOpenBetModel().observe(getViewLifecycleOwner(), new Observer<List<OpenBetModel>>() {
//            @Override
        stateModelAdapter = new StateModelAdapter(getApplicationContext(), dataset);
        rvstate.setAdapter(stateModelAdapter);


        loadindiastate();
    }

    public void loadindiastate() {
        dataset.clear();
        final String url = "https://api.covid19india.org/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("indiajson", response.toString());
                    if (response.has("statewise")) {
                        JSONArray keydata = response.getJSONArray("key_values");
                        JSONObject key = keydata.getJSONObject(0);
                        if (key.has("lastupdatedtime")) {
                            String time = key.getString("lastupdatedtime");
                            tvtime.setText("Last Updated: " + time);
                        }
                        if (key.has("deceaseddelta")) {
                            String deceaseddata = key.getString("deceaseddelta");
                            tvdeathincr.setText("[+" + deceaseddata + "]");
                        }
                        if (key.has("confirmeddelta")) {
                            String confirmdata = key.getString("confirmeddelta");
                            tvconfirmincr.setText("[+" + confirmdata + "]");
                        }
                        if (key.has("recovereddelta")) {
                            String recoverdata = key.getString("recovereddelta");
                            tvrecoverincr.setText("[+" + recoverdata + "]");
                        }
                        JSONArray statewise = response.getJSONArray("statewise");
                        JSONObject totalstats = statewise.getJSONObject(0);
                        String total_case = totalstats.getString("confirmed");
                        String active_case = totalstats.getString("active");
                        String recovered = totalstats.getString("recovered");
                        String death = totalstats.getString("deaths");
                        tvactive.setText(active_case);
                        tvconfirm.setText(total_case);
                        tvrecovered.setText(recovered);
                        tvdeath.setText(death);
                        JSONArray testing = response.getJSONArray("tested");
                        JSONObject testing_object = testing.getJSONObject(testing.length() - 1);
                        if (testing_object.has("totalsamplestested")) {
                            String totalsample = testing_object.getString("totalsamplestested");
                            tvtotalsample.setText(" " + totalsample);
                        }
                        int j = 0;
                        for (int i = 1; i < statewise.length(); i++) {
                            JSONObject stateobject = statewise.getJSONObject(i);
                            String statename = "", active = "", confirmed = "", recover = "", deacesed = "", deltaconfirm = "", deltadeath = "", deltarecovered = "";
                            statename = stateobject.getString("state");
                            active = stateobject.getString("active");
                            confirmed = stateobject.getString("confirmed");
                            recover = stateobject.getString("recovered");
                            deacesed = stateobject.getString("deaths");
                            deltaconfirm = stateobject.getString("deltaconfirmed");
                            deltarecovered = stateobject.getString("deltarecovered");
                            deltadeath = stateobject.getString("deltadeaths");
                            dataset.add(j, new StateModel(statename, active, confirmed, recover, deacesed, deltaconfirm, deltarecovered, deltadeath));
                            j++;

                        }
                        rvstate.setAdapter(stateModelAdapter);
                        stateModelAdapter.notifyDataSetChanged();
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
