package com.sqgc.qms_cut_bank_dashboard.view;

import android.os.Bundle;
import android.view.View;
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
    ArrayList<CutBankModel> cutBankModelArrayList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_bank);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_browse_fragment, new MainFragment())
//                    .commitNow();
//        }


        lineRecyclerView = findViewById(R.id.rvLine);
        lineRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cutBankViewModel = new ViewModelProvider(this).get(CutBankViewModel.class);

        cutBankViewModel.getCutBankData().observe(this, new Observer<List<CutBankModel>>() {
            @Override
            public void onChanged(List<CutBankModel> cutBankModels) {
                cutBankModelArrayList.clear();
                cutBankModelArrayList.addAll(cutBankModels);
                LineRecyclerViewAdapter lineRecyclerViewAdapter = new LineRecyclerViewAdapter(cutBankModelArrayList, CutBankActivity.this);
                lineRecyclerView.setAdapter(lineRecyclerViewAdapter);
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