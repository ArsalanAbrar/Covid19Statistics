package com.arsalan.covid19statistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arsalan.covid19statistics.Model.CountryListModel;
import com.arsalan.covid19statistics.Model.StateModel;
import com.arsalan.covid19statistics.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StateModelAdapter extends RecyclerView.Adapter<StateModelAdapter.StateModelAdapterHolder> {

    private Context context;
    private List<StateModel> stateModelList;
    ArrayAdapter<String> spinnerAdapter;
    //    OpenBetChildAdapter openBetChildAdapter;
    List<String> list = new ArrayList<>();
    String isclick = "0";

    public StateModelAdapter(Context context, List<StateModel> stateModelList) {
        this.context = context;
        this.stateModelList = stateModelList;
    }

    @NonNull
    @Override
    public StateModelAdapter.StateModelAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_model_raw, parent, false);
        return new StateModelAdapter.StateModelAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StateModelAdapter.StateModelAdapterHolder holder, int position) {
        StateModel stateModel = stateModelList.get(position);

        holder.tvstate.setText(stateModel.getStatename());
        holder.tvconfirm.setText(stateModel.getConfirmed());
        holder.tvactive.setText(stateModel.getActive());
        holder.tvrecover.setText(stateModel.getRecovered());
        holder.tvdeath.setText(stateModel.getDeath());
    }

    @Override
    public int getItemCount() {
        return stateModelList.size();
    }

    public class StateModelAdapterHolder extends RecyclerView.ViewHolder {

        TextView tvstate,tvactive,tvconfirm,tvrecover,tvdeath;

        public StateModelAdapterHolder(@NonNull View itemView) {
            super(itemView);
            tvstate = (TextView) itemView.findViewById(R.id.tvsatename);
            tvactive =(TextView)itemView.findViewById(R.id.tvstateactive);
            tvconfirm = (TextView)itemView.findViewById(R.id.tvstateconfirm);
            tvdeath = (TextView)itemView.findViewById(R.id.tvstatedeath);
            tvrecover = (TextView)itemView.findViewById(R.id.tvstaterecover);

        }
    }
}

