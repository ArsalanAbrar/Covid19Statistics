package com.arsalan.covid19statistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arsalan.covid19statistics.Model.CountryListModel;
import com.arsalan.covid19statistics.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class OpenBetAdapter extends RecyclerView.Adapter<OpenBetAdapter.OpenBetAdapterHolder> {

    private Context context;
    private List<CountryListModel> openmodellist;
    ArrayAdapter<String> spinnerAdapter;
    //    OpenBetChildAdapter openBetChildAdapter;
    List<String> list = new ArrayList<>();
    String isclick = "0";

    public OpenBetAdapter(Context context, List<CountryListModel> openmodellist) {
        this.context = context;
        this.openmodellist = openmodellist;
    }

    @NonNull
    @Override
    public OpenBetAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.openbet_raw, parent, false);
        return new OpenBetAdapter.OpenBetAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OpenBetAdapterHolder holder, int position) {
        CountryListModel openBetModel = openmodellist.get(position);
        holder.tvcountry.setText(openBetModel.getCountryname());
        holder.tvdate.setText(openBetModel.getDate());
        holder.tvtime.setVisibility(View.GONE);
        holder.tvlivebets.setText(String.valueOf(openBetModel.getTotalcase()) + "  Total Cases");

        holder.tvactive.setText(String.valueOf(openBetModel.getActive()));
        holder.tvrecover.setText(String.valueOf(openBetModel.getRecovered()));
        holder.tvcritical.setText(String.valueOf(openBetModel.getCritical()));
        holder.tvnew.setText(openBetModel.getNewcase());
        holder.tvnewdeath.setText(openBetModel.getNewdeath());
        holder.tvtotaldeath.setText(String.valueOf(openBetModel.getTotaldeath()));

        holder.cvopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isclick.equalsIgnoreCase("0")) {
                    holder.cvdeatil.setVisibility(View.VISIBLE);
                    isclick = "1";
                } else {
                    holder.cvdeatil.setVisibility(View.GONE);
                    isclick = "0";
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return openmodellist.size();
    }

    public class OpenBetAdapterHolder extends RecyclerView.ViewHolder {

        TextView tvdate, tvcountry, tvlivebets, tvtime, tvnew, tvactive, tvrecover, tvcritical, tvnewdeath, tvtotaldeath;
        RecyclerView rv;
        LinearLayout llopen;
        CardView cvopen, cvdeatil;

        public OpenBetAdapterHolder(@NonNull View itemView) {
            super(itemView);
            tvdate = (TextView) itemView.findViewById(R.id.tvdate);
            tvtime = (TextView) itemView.findViewById(R.id.tvtime);
            tvcountry = (TextView) itemView.findViewById(R.id.tvcountryname);
            tvlivebets = (TextView) itemView.findViewById(R.id.tvtotalcase);
            llopen = (LinearLayout) itemView.findViewById(R.id.llopenbets);
            cvopen = (CardView) itemView.findViewById(R.id.cvopen);
            cvdeatil = (CardView) itemView.findViewById(R.id.cvdetail);
            tvnew = (TextView) itemView.findViewById(R.id.tvnewcases);
            tvnewdeath = (TextView) itemView.findViewById(R.id.tvnewdeath);
            tvcritical = (TextView) itemView.findViewById(R.id.tvcritical);
            tvtotaldeath = (TextView) itemView.findViewById(R.id.tvtotaldeath);
            tvrecover = (TextView) itemView.findViewById(R.id.tvrecovered);
            tvactive = (TextView) itemView.findViewById(R.id.tvactive);
        }
    }
}
