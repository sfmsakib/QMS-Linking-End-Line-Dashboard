package com.sqgc.qms_cut_bank_dashboard;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends FragmentActivity {
    RecyclerView lineRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.main_browse_fragment, new MainFragment())
//                    .commitNow();
//        }


        ArrayList<CutBankModel> cutBankModelArrayList = new ArrayList<>();
        cutBankModelArrayList.add(new CutBankModel("Unit1", "Line1","4817-Hileg", "12313124124-TBA-9", 455,535,1534523));
        cutBankModelArrayList.add(new CutBankModel("Unit1", "Line2","4817-Hileg", "12313124124-TBA-9", 5435,452,12533));
        cutBankModelArrayList.add(new CutBankModel("Unit2", "Line3","4817-Hileg", "12313124124-TBA-9", 245,3443,3535));
        cutBankModelArrayList.add(new CutBankModel("Unit2", "Line4","4817-Hileg", "12313124124-TBA-9", 24545,4543,63645));

        lineRecyclerView = findViewById(R.id.rvLine);
        lineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LineRecyclerViewAdapter lineRecyclerViewAdapter = new LineRecyclerViewAdapter(cutBankModelArrayList, this);
        lineRecyclerView.setAdapter(lineRecyclerViewAdapter);
    }
}