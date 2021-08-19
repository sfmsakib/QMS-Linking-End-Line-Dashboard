package com.sqgc.qms_tv_dashboard_sewing.view;

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
import com.sqgc.qms_tv_dashboard_sewing.R;
import com.sqgc.qms_tv_dashboard_sewing.viewmodel.MainViewModel;

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
        setChartData3();
        setChartData4();


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
    private void setChartData3() {
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(1,53));
        barEntryList.add(new BarEntry(2,43));
        barEntryList.add(new BarEntry(3,36));

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.blue,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        dataSet.setLabel(null);
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","1", "2", "3", "4", "5"));


        BarData barData = new BarData(dataSet);
        chart3.setData(barData);
        chart3.getDescription().setEnabled(false);
        chart3.setDrawGridBackground(false);
        chart3.setDrawBarShadow(false);


        YAxis leftAxis = chart3.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);

        chart3.getAxisRight().setEnabled(false);

        XAxis xAxis = chart3.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);


        chart3.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart3.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart3.getLegend().setEnabled(false);

        chart3.invalidate();

    }
    private void setChartData4() {
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(1,362));
        barEntryList.add(new BarEntry(2,43));
        barEntryList.add(new BarEntry(3,362));

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.blue,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        dataSet.setLabel(null);
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","1", "2", "3", "4", "5"));


        BarData barData = new BarData(dataSet);
        chart4.setData(barData);
        chart4.getDescription().setEnabled(false);
        chart4.setDrawGridBackground(false);
        chart4.setDrawBarShadow(false);


        YAxis leftAxis = chart4.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);

        chart4.getAxisRight().setEnabled(false);

        XAxis xAxis = chart4.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);


        chart4.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart4.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart4.getLegend().setEnabled(false);

        chart4.invalidate();

    }

    BarChart chart1;
    BarChart chart2,chart3,chart4;

    private void initialize() {
        chart1 = findViewById(R.id.chart_1);
        chart2 = findViewById(R.id.chart_2);
        chart3 = findViewById(R.id.chart_3);
        chart4 = findViewById(R.id.chart_4);

    }
}