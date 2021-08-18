package com.sqgc.qms_end_line_tv_dashboard.model.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sqgc.qms_end_line_tv_dashboard.R;
import com.sqgc.qms_end_line_tv_dashboard.model.DataModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LineRecyclerViewAdapter extends RecyclerView.Adapter<LineRecyclerViewAdapter.ViewHolder> {

    List<DataModel> mCutBankModelList;
    LayoutInflater mInflater;

    public LineRecyclerViewAdapter(List<DataModel> cutBankModelList, Context context) {
        this.mCutBankModelList = cutBankModelList;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.row_line_recyclerview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LineRecyclerViewAdapter.ViewHolder viewHolder, int i) {

        viewHolder.mProductionUnit.setText(mCutBankModelList.get(i).getProductionUnit());
        viewHolder.mLine.setText(mCutBankModelList.get(i).getLine());
        viewHolder.mStyle.setText(mCutBankModelList.get(i).getStyle());
        viewHolder.mPO.setText(mCutBankModelList.get(i).getPo());
        viewHolder.mCutBankIn.setText(String.valueOf(mCutBankModelList.get(i).getCutBankIn()));
        viewHolder.mCutBankOut.setText(String.valueOf(mCutBankModelList.get(i).getCutBankOut()));
        viewHolder.mCutBankWIP.setText(String.valueOf(mCutBankModelList.get(i).getCutBankWip()));
    }

    @Override
    public int getItemCount() {
        return mCutBankModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mProductionUnit, mLine, mStyle, mPO, mCutBankIn, mCutBankOut, mCutBankWIP;
        public ViewHolder(View itemView) {
            super(itemView);
            mProductionUnit = itemView.findViewById(R.id.tvProductionUnit);
            mLine = itemView.findViewById(R.id.tvLine);
            mStyle = itemView.findViewById(R.id.tvStyle);
            mPO = itemView.findViewById(R.id.tvPO);
            mCutBankIn = itemView.findViewById(R.id.rowTvCutBankIn);
            mCutBankOut = itemView.findViewById(R.id.rowTvCutBankOut);
            mCutBankWIP = itemView.findViewById(R.id.rowTvCutBankWip);

        }
    }
}
