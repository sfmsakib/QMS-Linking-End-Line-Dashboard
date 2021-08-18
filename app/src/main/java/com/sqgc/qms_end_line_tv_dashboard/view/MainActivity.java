package com.sqgc.qms_end_line_tv_dashboard.view;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.sqgc.qms_end_line_tv_dashboard.R;
import com.sqgc.qms_end_line_tv_dashboard.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends FragmentActivity {
    MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initialize();

        setChartData1();
        setChartData2();


        mainViewModel.getDateTime().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
               // dateTimeTextView.setText(s);
            }
        });

    }

    private void setChartData2() {
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(1,300, "9-10"));
        barEntryList.add(new BarEntry(2,200, "10-11"));
        barEntryList.add(new BarEntry(3,100, "11-12"));
        barEntryList.add(new BarEntry(4,300, "9-10"));
        barEntryList.add(new BarEntry(5,0, "9-10"));
        barEntryList.add(new BarEntry(6,400, "9-10"));
        barEntryList.add(new BarEntry(7,200, "9-10"));
        barEntryList.add(new BarEntry(8,100, "9-10"));
        barEntryList.add(new BarEntry(9,300, "9-10"));
        barEntryList.add(new BarEntry(10,200, "9-10"));
        barEntryList.add(new BarEntry(11,300, "9-10"));

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.blue,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        dataSet.setLabel(null);
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","9-10", "10-11", "11-12", "12-1", "1-2", "2-3","3-4", "4-5", "5-6", "6-7", "7-8"));


        BarData barData = new BarData(dataSet);
        chart2.setData(barData);
        chart2.getDescription().setEnabled(false);
        chart2.setDrawGridBackground(false);
        chart2.setDrawBarShadow(false);


        YAxis leftAxis = chart2.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);

        chart2.getAxisRight().setEnabled(false);

        XAxis xAxis = chart2.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);


        chart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart2.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart2.getLegend().setEnabled(false);
        chart2.invalidate();

    }


    private void setChartData1() {
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(1,300, "9-10"));
        barEntryList.add(new BarEntry(2,200, "10-11"));
        barEntryList.add(new BarEntry(3,100, "11-12"));
        barEntryList.add(new BarEntry(4,300, "9-10"));
        barEntryList.add(new BarEntry(5,0, "9-10"));
        barEntryList.add(new BarEntry(6,400, "9-10"));
        barEntryList.add(new BarEntry(7,200, "9-10"));
        barEntryList.add(new BarEntry(8,100, "9-10"));
        barEntryList.add(new BarEntry(9,300, "9-10"));
        barEntryList.add(new BarEntry(10,200, "9-10"));
        barEntryList.add(new BarEntry(11,300, "9-10"));

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.blue,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        dataSet.setLabel(null);
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","9-10", "10-11", "11-12", "12-1", "1-2", "2-3","3-4", "4-5", "5-6", "6-7", "7-8"));


        BarData barData = new BarData(dataSet);
        chart1.setData(barData);
        chart1.getDescription().setEnabled(false);
        chart1.setDrawGridBackground(false);
        chart1.setDrawBarShadow(false);


        YAxis leftAxis = chart1.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);

        chart1.getAxisRight().setEnabled(false);

        XAxis xAxis = chart1.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);


        chart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart1.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart1.getLegend().setEnabled(false);

        chart1.invalidate();

    }
    BarChart chart1;
    BarChart chart2;

    private void initialize() {
        chart1 = findViewById(R.id.chart_1);
        chart2 = findViewById(R.id.chart_2);

    }
}