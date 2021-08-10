package com.sqgc.qms_cut_bank_dashboard.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sqgc.qms_cut_bank_dashboard.model.CutBankModel;
import com.sqgc.qms_cut_bank_dashboard.model.adapters.LineRecyclerViewAdapter;
import com.sqgc.qms_cut_bank_dashboard.R;
import com.sqgc.qms_cut_bank_dashboard.viewmodel.CutBankViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class CutBankActivity extends FragmentActivity {
    RecyclerView lineRecyclerView;
    CutBankViewModel cutBankViewModel;
    LinearLayout linearLayout;
    ArrayList<CutBankModel> cutBankModelArrayList = new ArrayList<>();
    TextView tvMsg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_bank);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_browse_fragment, new MainFragment())
//                    .commitNow();
//        }


        linearLayout = findViewById(R.id.error_layout);
        tvMsg = findViewById(R.id.tvMsg);
        lineRecyclerView = findViewById(R.id.rvLine);
        lineRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cutBankViewModel = new ViewModelProvider(this).get(CutBankViewModel.class);


        cutBankViewModel.getCutBankData().observe(this, new Observer<List<CutBankModel>>() {
            @Override
            public void onChanged(List<CutBankModel> cutBankModels) {
                if (cutBankModels!=null){
                    Log.i("CUT_BANK_DASHBOARD","OnChanged:"+cutBankModels.toString());
                    if (cutBankModelArrayList!=null){
                        if (cutBankModels.size() == 0){
                            linearLayout.setVisibility(View.VISIBLE);
                            lineRecyclerView.setVisibility(View.GONE);
                            tvMsg.setText(R.string.no_data_msg);
                        }else {
                            cutBankModelArrayList.clear();
                            cutBankModelArrayList.addAll(cutBankModels);
                            LineRecyclerViewAdapter lineRecyclerViewAdapter = new LineRecyclerViewAdapter(cutBankModelArrayList, CutBankActivity.this);
                            lineRecyclerView.setAdapter(lineRecyclerViewAdapter);
                            linearLayout.setVisibility(View.GONE);
                            lineRecyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                }else {
                    tvMsg.setText(R.string.failed_msg);
                    lineRecyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        lineRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cutBankViewModel.getCutBankData();
            }
        });

        TextView dateTimeTextView = findViewById(R.id.tvDateTime);
        cutBankViewModel.getDateTime().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dateTimeTextView.setText(s);
            }
        });

    }
}