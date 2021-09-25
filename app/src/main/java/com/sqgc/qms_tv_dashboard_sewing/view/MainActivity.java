package com.sqgc.qms_tv_dashboard_sewing.view;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.sqgc.qms_tv_dashboard_sewing.R;
import com.sqgc.qms_tv_dashboard_sewing.model.DataModel;
import com.sqgc.qms_tv_dashboard_sewing.model.HourlyActualPcs;
import com.sqgc.qms_tv_dashboard_sewing.model.TopDefect;
import com.sqgc.qms_tv_dashboard_sewing.model.util.Loader;
import com.sqgc.qms_tv_dashboard_sewing.viewmodel.MainViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends FragmentActivity {
    MainViewModel mainViewModel;
    private Loader loader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = new Loader(this);
        loader.show();


        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initialize();
//        Context context = getApplicationContext();
//        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//        buyerName1.setText(ip);
//        Log.i("IP_LOG", ip);


        mainViewModel.getDateTime().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null && !s.isEmpty()) {
                    time.setText(s);
                }
            }
        });
        mainViewModel.getDashboardData().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                if (dataModels != null && !dataModels.isEmpty()){
                    if (loader != null){
                        loader.hide();
                    }
                    Log.i("DASHBOARD_LOG",dataModels.toString());
                    setValue(dataModels);
                }
            }
        });

    }

    private void setValue(List<DataModel> dataModels) {

        int colorGreen = ResourcesCompat.getColor(getResources(), R.color.green_alert, null);
        int colorYellow = ResourcesCompat.getColor(getResources(), R.color.yellow_alert, null);
        int colorRed = ResourcesCompat.getColor(getResources(), R.color.red_alert, null);
        int colorWhite = ResourcesCompat.getColor(getResources(), R.color.white, null);

        int redLimit = 30;
        int yellowLimit = 70;
        int greenLimit = 120;

        //DecimalFormat decimalFormat = new DecimalFormat("#.##");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        if (dataModels.get(0).getLineName() != null && dataModels.get(0).getBuyerName() != null){

            int pPcs1 = dataModels.get(0).getPlannedPcs();
            int var1 = dataModels.get(0).getVariance();
            int hTarget1 = dataModels.get(0).getHourlyTarget();

            lineNumber1.setText(dataModels.get(0).getLineName()+"");
            buyerName1.setText(dataModels.get(0).getBuyerName()+"");
            hourlyTarget1.setText(hTarget1+"");


            int aPcs1 = dataModels.get(0).getActualPcs();
            actualPcs1.setText(aPcs1+"");

            float actualPercentage1 = getPercentage(aPcs1,hTarget1);

            Log.i("DASHBOARD_LOG","Percentage:"+actualPercentage1+" actual:"+aPcs1+" hTarget:"+hTarget1);
            if (actualPercentage1 > redLimit && actualPercentage1 <= yellowLimit){
                actualPcs1.setTextColor(colorYellow);
            }else if (actualPercentage1 >= greenLimit){
                actualPcs1.setTextColor(colorGreen);
            }else if (actualPercentage1 <= redLimit){
                actualPcs1.setTextColor(colorRed);
            }else {
                actualPcs1.setTextColor(colorWhite);
            }


            variance1.setText(var1+"");
            planPcs1.setText(pPcs1+"");

            int cumPlannedPcsValue1 = dataModels.get(0).getCumPlannedPcs();
            int cumActualPcsValue1 = dataModels.get(0).getCumActualPcs();

            float cumActualPercentage1 = getPercentage(cumActualPcsValue1,cumPlannedPcsValue1);

            cumPlannedPcs1.setText(cumPlannedPcsValue1+"");
            cumActualPcs1.setText(cumActualPcsValue1+"");

            Log.i("DASHBOARD_LOG","Percentage:"+cumActualPercentage1+" cumActualPcsValue1:"+cumActualPcsValue1+" cumPlannedPcsValue1:"+cumPlannedPcsValue1);
            if (cumActualPercentage1 > redLimit && cumActualPercentage1 <= yellowLimit){
                cumActualPcs1.setTextColor(colorYellow);
            }else if (cumActualPercentage1 >= greenLimit){
                cumActualPcs1.setTextColor(colorGreen);
            }else if (cumActualPercentage1 <= redLimit){
                cumActualPcs1.setTextColor(colorRed);
            }else {
                cumActualPcs1.setTextColor(colorWhite);
            }


            balanceToProduce1.setText(dataModels.get(0).getBalanceToProduce()+"");
            cumVariance1.setText(dataModels.get(0).getCumVariance()+"");

            float eff = dataModels.get(0).getEfficiency();
            String ff = df.format(eff);

            efficiency1.setText(ff+"");

            defectivePcs1.setText(dataModels.get(0).getDefectivePcs()+"");

            String dp1 = df.format(dataModels.get(0).getDefectPercentage());
            defectPercentage1.setText(dp1+"");

            totalDefect1.setText(dataModels.get(0).getTotalDefect()+"");

            String dhuV1 = df.format(dataModels.get(0).getDhu());
            dhu1.setText(dhuV1+"");

            if (dataModels.get(0).getTopDefects() != null){
                topDefectName11.setText(dataModels.get(0).getTopDefects().get(0).getDefectName()+"");
                topDefectName12.setText(dataModels.get(0).getTopDefects().get(1).getDefectName()+"");
                topDefectName13.setText(dataModels.get(0).getTopDefects().get(2).getDefectName()+"");
                topDefectName14.setText(dataModels.get(0).getTopDefects().get(3).getDefectName()+"");
                topDefectName15.setText(dataModels.get(0).getTopDefects().get(4).getDefectName()+"");
                topDefectPercentage11.setText(Math.round(dataModels.get(0).getTopDefects().get(0).getDefectPercentage())+"%");
                topDefectPercentage12.setText(Math.round(dataModels.get(0).getTopDefects().get(1).getDefectPercentage())+"%");
                topDefectPercentage13.setText(Math.round(dataModels.get(0).getTopDefects().get(2).getDefectPercentage())+"%");
                topDefectPercentage14.setText(Math.round(dataModels.get(0).getTopDefects().get(3).getDefectPercentage())+"%");
                topDefectPercentage15.setText(Math.round(dataModels.get(0).getTopDefects().get(4).getDefectPercentage())+"%");

                setChartData3(dataModels);
            }
            if (dataModels.get(0).getHourlyActualPcsList() != null){
                setChartData1(dataModels);
            }
        }
        if (dataModels.get(1).getLineName() != null && dataModels.get(1).getBuyerName() != null){
            lineNumber2.setText(dataModels.get(1).getLineName()+"");
            buyerName2.setText(dataModels.get(1).getBuyerName()+"");
            hourlyTarget2.setText(dataModels.get(1).getHourlyTarget()+"");
            actualPcs2.setText(dataModels.get(1).getActualPcs()+"");
            variance2.setText(dataModels.get(1).getVariance()+"");
            planPcs2.setText(dataModels.get(1).getPlannedPcs()+"");
            cumPlannedPcs2.setText(dataModels.get(1).getCumPlannedPcs()+"");
            cumActualPcs2.setText(dataModels.get(1).getCumActualPcs()+"");
            balanceToProduce2.setText(dataModels.get(1).getBalanceToProduce()+"");
            cumVariance2.setText(dataModels.get(1).getCumVariance()+"");

            String eff2 = df.format(dataModels.get(1).getEfficiency());
            efficiency2.setText(eff2+"");

            defectivePcs2.setText(dataModels.get(1).getDefectivePcs()+"");

            String dp2 = df.format(dataModels.get(1).getDefectPercentage());
            defectPercentage2.setText(dp2+"");

            totalDefect2.setText(dataModels.get(1).getTotalDefect()+"");

            String dhuV2 = df.format(dataModels.get(1).getDhu());
            dhu2.setText(dhuV2+"");

            if (dataModels.get(1).getTopDefects() != null) {
                topDefectName21.setText(dataModels.get(1).getTopDefects().get(0).getDefectName()+"");
                topDefectName22.setText(dataModels.get(1).getTopDefects().get(1).getDefectName()+"");
                topDefectName23.setText(dataModels.get(1).getTopDefects().get(2).getDefectName()+"");
                topDefectName24.setText(dataModels.get(1).getTopDefects().get(3).getDefectName()+"");
                topDefectName25.setText(dataModels.get(1).getTopDefects().get(4).getDefectName()+"");
                topDefectPercentage21.setText(Math.round(dataModels.get(1).getTopDefects().get(0).getDefectPercentage())+"%");
                topDefectPercentage22.setText(Math.round(dataModels.get(1).getTopDefects().get(1).getDefectPercentage())+"%");
                topDefectPercentage23.setText(Math.round(dataModels.get(1).getTopDefects().get(2).getDefectPercentage())+"%");
                topDefectPercentage24.setText(Math.round(dataModels.get(1).getTopDefects().get(3).getDefectPercentage())+"%");
                topDefectPercentage25.setText(Math.round(dataModels.get(1).getTopDefects().get(4).getDefectPercentage())+"%");
                setChartData4(dataModels);
            }
            if (dataModels.get(1).getHourlyActualPcsList() != null) {
                setChartData2(dataModels);
            }

        }



    }

    public static float getPercentage(int n, int total) {
        float proportion = ((float) n) / ((float) total);
        return proportion * 100;
    }

    private void setChartData2(List<DataModel> dataModels) {
        chart2.getDescription().setEnabled(false);
        chart2.setDrawGridBackground(false);
        chart2.setDrawBarShadow(false);
//        chart1.setBackgroundColor(Color.WHITE);
        chart2.setHighlightFullBarEnabled(false);

        // draw bars behind lines
        chart2.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.BUBBLE,
                CombinedChart.DrawOrder.CANDLE,
                CombinedChart.DrawOrder.LINE,
                CombinedChart.DrawOrder.SCATTER
        });


        CombinedData data = new CombinedData();

        data.setData(generateLineData2(dataModels));
        data.setData(generateBarData2(dataModels));
//        data.setData(generateBubbleData());
//        data.setData(generateScatterData());
//        data.setData(generateCandleData());
//        data.setValueTypeface(tfLight);



        YAxis rightAxis = chart2.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        rightAxis.setEnabled(false); //old

        YAxis leftAxis = chart2.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false); //old


        XAxis xAxis = chart2.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        ArrayList<String> xAxisValues = new ArrayList<>();
        List<HourlyActualPcs> hourlyActualPcs = dataModels.get(1).getHourlyActualPcsList();

        for(int i = 0; i < hourlyActualPcs.size(); i++){
            xAxisValues.add(hourlyActualPcs.get(i).getHourName());
        }

        //List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","9-10", "10-11", "11-12", "12-1", "1-2", "2-3","3-4", "4-5", "5-6", "6-7", "7-8"));
        chart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart2.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart2.getLegend().setEnabled(false);


        chart2.setData(data);
        chart2.invalidate();


    }

    private BarData generateBarData2(List<DataModel> dataModels) {


        ArrayList<BarEntry> barEntryList = new ArrayList<>();
        List<HourlyActualPcs> hourlyActualPcs = dataModels.get(1).getHourlyActualPcsList();

        for(int i = 0; i < hourlyActualPcs.size(); i++){
            barEntryList.add(new BarEntry(i, hourlyActualPcs.get(i).getHourlyActualPcs()));
        }

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.blue,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        dataSet.setLabel(null);
        dataSet.setValueTextSize(10f);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        //float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData barData = new BarData(dataSet);
        //barData.setBarWidth(barWidth);

        chart2.getXAxis().setSpaceMin(barData.getBarWidth()/2f);
        //chart2.getXAxis().setSpaceMax(barData.getBarWidth() / 1f);

        // make this BarData object grouped
        //barData.groupBars(0, groupSpace, barSpace); // start at x = 0

        return barData;

    }

    private LineData generateLineData2(List<DataModel> dataModels) {


        LineData d = new LineData();

        ArrayList<Entry> lineEntryList = new ArrayList<>();
        List<HourlyActualPcs> hourlyActualPcs = dataModels.get(1).getHourlyActualPcsList();

        for(int i = 0; i < hourlyActualPcs.size(); i++){
            lineEntryList.add(new Entry(i, hourlyActualPcs.get(i).getHourlyActualPcs()));
        }

        LineDataSet set = new LineDataSet(lineEntryList, "Line DataSet");
        set.setColor(ResourcesCompat.getColor(getResources(),R.color.green_line,null));
        set.setLineWidth(2f);
        set.setCircleColor(ResourcesCompat.getColor(getResources(),R.color.yellow,null));
        set.setCircleRadius(3f);
        set.setFillColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(false);
        set.setValueTextSize(10f);
        set.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;



    }


    private void setChartData1(List<DataModel> dataModels) {

        chart1.getDescription().setEnabled(false);
        chart1.setDrawGridBackground(false);
        chart1.setDrawBarShadow(false);
//        chart1.setBackgroundColor(Color.WHITE);
        chart1.setHighlightFullBarEnabled(false);

        // draw bars behind lines
        chart1.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.BUBBLE,
                CombinedChart.DrawOrder.CANDLE,
                CombinedChart.DrawOrder.LINE,
                CombinedChart.DrawOrder.SCATTER
        });


        CombinedData data = new CombinedData();

        data.setData(generateLineData1(dataModels));
        data.setData(generateBarData1(dataModels));
//        data.setData(generateBubbleData());
//        data.setData(generateScatterData());
//        data.setData(generateCandleData());
//        data.setValueTypeface(tfLight);



        YAxis rightAxis = chart1.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        rightAxis.setEnabled(false); //old

        YAxis leftAxis = chart1.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false); //old


        XAxis xAxis = chart1.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);


        ArrayList<String> xAxisValues = new ArrayList<>();
        List<HourlyActualPcs> hourlyActualPcs = dataModels.get(0).getHourlyActualPcsList();

        for(int i = 0; i < hourlyActualPcs.size(); i++){
            xAxisValues.add(hourlyActualPcs.get(i).getHourName());
            Log.i("DASHBOARD_LOG","Hour index:"+i+" Hour name: "+hourlyActualPcs.get(i).getHourName());
        }
        Log.i("DASHBOARD_LOG",xAxisValues.toString());



        //List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","9-10", "10-11", "11-12", "12-1", "1-2", "2-3","3-4", "4-5", "5-6", "6-7", "7-8"));
        chart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart1.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart1.getLegend().setEnabled(false);


        chart1.setData(data);
        chart1.invalidate();

    }

    private BarData generateBarData1(List<DataModel> dataModels) {


        ArrayList<BarEntry> barEntryList = new ArrayList<>();
        List<HourlyActualPcs> hourlyActualPcs = dataModels.get(0).getHourlyActualPcsList();

        for(int i = 0; i < hourlyActualPcs.size(); i++){
            barEntryList.add(new BarEntry(i, hourlyActualPcs.get(i).getHourlyActualPcs()));
        }

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.blue,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        dataSet.setLabel(null);
        dataSet.setValueTextSize(10f);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        //float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData barData = new BarData(dataSet);
        //barData.setBarWidth(barWidth);

        chart1.getXAxis().setSpaceMin(barData.getBarWidth() / 2f);
        chart1.getXAxis().setSpaceMax(barData.getBarWidth() / 2f);

        // make this BarData object grouped
        //barData.groupBars(0, groupSpace, barSpace); // start at x = 0

        return barData;
    }

    private LineData generateLineData1(List<DataModel> dataModels) {

        LineData d = new LineData();

        ArrayList<Entry> lineEntryList = new ArrayList<>();
        List<HourlyActualPcs> hourlyActualPcs = dataModels.get(0).getHourlyActualPcsList();

        for(int i = 0; i < hourlyActualPcs.size(); i++){
            lineEntryList.add(new Entry(i, hourlyActualPcs.get(i).getHourlyActualPcs()));
        }

        LineDataSet set = new LineDataSet(lineEntryList, "Line DataSet");
        set.setColor(ResourcesCompat.getColor(getResources(),R.color.green_line,null));
        set.setLineWidth(2f);
        set.setCircleColor(ResourcesCompat.getColor(getResources(),R.color.yellow,null));
        set.setCircleRadius(3f);
        set.setFillColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(false);
        set.setValueTextSize(10f);
        set.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;

    }

    private void setChartData3(List<DataModel> dataModels) {
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(5,dataModels.get(0).getTopDefects().get(0).getDefectPcs()));
        barEntryList.add(new BarEntry(4,dataModels.get(0).getTopDefects().get(1).getDefectPcs()));
        barEntryList.add(new BarEntry(3,dataModels.get(0).getTopDefects().get(2).getDefectPcs()));
        barEntryList.add(new BarEntry(2,dataModels.get(0).getTopDefects().get(3).getDefectPcs()));
        barEntryList.add(new BarEntry(1,dataModels.get(0).getTopDefects().get(4).getDefectPcs()));

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");
        //dataSet.setBarSpacePercent(50f);

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.pink500,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        dataSet.setLabel(null);
        ArrayList<String> xAxisValues = new ArrayList<>();
        List<TopDefect> topDefectList = dataModels.get(0).getTopDefects();

        for(int i = 0; i < topDefectList.size(); i++){
            xAxisValues.add(String.valueOf(topDefectList.get(i).getSlNo()));
        }

        //List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","1", "2", "3", "4", "5"));


        BarData barData = new BarData(dataSet);
        chart3.setData(barData);
        chart3.getDescription().setEnabled(false);
        chart3.setDrawGridBackground(false);
        chart3.setDrawBarShadow(false);
        chart3.setFitBars(true);

        //barData.setBarWidth(2f);



        YAxis leftAxis = chart3.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);
        leftAxis.setDrawLabels(false);
        //leftAxis.needsOffset();
//        leftAxis.setXOffset(0f);
//        leftAxis.setYOffset(0f);


        //chart3.setViewPortOffsets(0f,0f,0f,0f);
        chart3.setMinOffset(0f);

        chart3.getAxisRight().setEnabled(false);
        chart3.getAxisRight().setDrawLabels(false);





        XAxis xAxisTop = chart3.getXAxis();
        xAxisTop.setEnabled(true);
        xAxisTop.setPosition(XAxis.XAxisPosition.TOP);
        xAxisTop.setDrawGridLines(false);
        xAxisTop.setDrawAxisLine(false);
        xAxisTop.setDrawLabels(false);


        chart3.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart3.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart3.getLegend().setEnabled(false);

        chart3.invalidate();

    }
    private void setChartData4(List<DataModel> dataModels) {
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(5,dataModels.get(1).getTopDefects().get(0).getDefectPcs()));
        barEntryList.add(new BarEntry(4,dataModels.get(1).getTopDefects().get(1).getDefectPcs()));
        barEntryList.add(new BarEntry(3,dataModels.get(1).getTopDefects().get(2).getDefectPcs()));
        barEntryList.add(new BarEntry(2,dataModels.get(1).getTopDefects().get(3).getDefectPcs()));
        barEntryList.add(new BarEntry(1,dataModels.get(1).getTopDefects().get(4).getDefectPcs()));

        BarDataSet dataSet = new BarDataSet(barEntryList, "Defect Pcs");
        //dataSet.setBarSpacePercent(50f);

        dataSet.setColor(ResourcesCompat.getColor(getResources(),R.color.pink500,null));
        dataSet.setValueTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        dataSet.setLabel(null);

        ArrayList<String> xAxisValues = new ArrayList<>();
        List<TopDefect> topDefectList = dataModels.get(1).getTopDefects();

        for(int i = 0; i < topDefectList.size(); i++){
            xAxisValues.add(String.valueOf(topDefectList.get(i).getSlNo()));
        }


        //List<String> xAxisValues = new ArrayList<>(Arrays.asList("0","1", "2", "3", "4", "5"));


        BarData barData = new BarData(dataSet);
        chart4.setData(barData);
        chart4.getDescription().setEnabled(false);
        chart4.setDrawGridBackground(false);
        chart4.setDrawBarShadow(false);
        chart4.setFitBars(true);

        //barData.setBarWidth(2f);



        YAxis leftAxis = chart4.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);
        leftAxis.setDrawLabels(false);
        //leftAxis.needsOffset();
//        leftAxis.setXOffset(0f);
//        leftAxis.setYOffset(0f);


        //chart3.setViewPortOffsets(0f,0f,0f,0f);
        chart4.setMinOffset(0f);

        chart4.getAxisRight().setEnabled(false);
        chart4.getAxisRight().setDrawLabels(false);





        XAxis xAxisTop = chart4.getXAxis();
        xAxisTop.setEnabled(true);
        xAxisTop.setPosition(XAxis.XAxisPosition.TOP);
        xAxisTop.setDrawGridLines(false);
        xAxisTop.setDrawAxisLine(false);
        xAxisTop.setDrawLabels(false);


        chart4.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
        chart4.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
        chart4.getLegend().setEnabled(false);

        chart4.invalidate();

    }

    CombinedChart chart1;
    CombinedChart chart2;
    HorizontalBarChart chart3,chart4;
    TextView lineNumber1, buyerName1, hourlyTarget1, actualPcs1, variance1, planPcs1, balanceToProduce1, cumActualPcs1,
            cumPlannedPcs1, cumVariance1, efficiency1, defectivePcs1, defectPercentage1, totalDefect1, dhu1,
            topDefectName11, topDefectName12, topDefectName13,topDefectName14,topDefectName15, topDefectPercentage11,
            topDefectPercentage12, topDefectPercentage13,topDefectPercentage14,topDefectPercentage15;

    TextView lineNumber2, buyerName2,time, hourlyTarget2, actualPcs2, variance2, planPcs2,cumPlannedPcs2, cumActualPcs2,
            balanceToProduce2, cumVariance2, efficiency2, defectivePcs2, defectPercentage2, totalDefect2, dhu2,
            topDefectName21, topDefectName22, topDefectName23,topDefectName24,topDefectName25, topDefectPercentage21,
            topDefectPercentage22, topDefectPercentage23,topDefectPercentage24,topDefectPercentage25;

    private void initialize() {
        chart1 = findViewById(R.id.chart_1);
        chart2 = findViewById(R.id.chart_2);
        chart3 = findViewById(R.id.chart_3);
        chart4 = findViewById(R.id.chart_4);
        lineNumber1 = findViewById(R.id.lineNumber1);
        buyerName1= findViewById(R.id.buyerName1);
        hourlyTarget1= findViewById(R.id.hourlyTarget1);
        actualPcs1= findViewById(R.id.actualPcs1);
        variance1= findViewById(R.id.variance1);
        planPcs1= findViewById(R.id.planPcs1);
        cumPlannedPcs1 = findViewById(R.id.cumPlannedPcs1);
        cumActualPcs1= findViewById(R.id.cumActualPcs1);
        balanceToProduce1 = findViewById(R.id.balanceToProduce1);
        cumVariance1= findViewById(R.id.cumVariance1);
        efficiency1= findViewById(R.id.efficiency1);
        defectivePcs1= findViewById(R.id.defectivePcs1);
        defectPercentage1= findViewById(R.id.defectPercentage1);
        totalDefect1= findViewById(R.id.totalDefect1);
        dhu1= findViewById(R.id.dhu1);
        topDefectName11= findViewById(R.id.topDefectName11);
        topDefectName12= findViewById(R.id.topDefectName12);
        topDefectName13= findViewById(R.id.topDefectName13);
        topDefectName14= findViewById(R.id.topDefectName14);
        topDefectName15= findViewById(R.id.topDefectName15);
        topDefectPercentage11= findViewById(R.id.topDefectPercentage11);
        topDefectPercentage12= findViewById(R.id.topDefectPercentage12);
        topDefectPercentage13= findViewById(R.id.topDefectPercentage13);
        topDefectPercentage14= findViewById(R.id.topDefectPercentage14);
        topDefectPercentage15= findViewById(R.id.topDefectPercentage15);
        lineNumber2 = findViewById(R.id.lineNumber2);
        buyerName2= findViewById(R.id.buyerName2);
        time = findViewById(R.id.time);
        hourlyTarget2= findViewById(R.id.hourlyTarget2);
        actualPcs2= findViewById(R.id.actualPcs2);
        variance2= findViewById(R.id.variance2);
        planPcs2= findViewById(R.id.planPcs2);
        cumPlannedPcs2 = findViewById(R.id.cumPlannedPcs2);
        cumActualPcs2= findViewById(R.id.cumActualPcs2);
        balanceToProduce2 = findViewById(R.id.balanceToProduce2);
        cumVariance2= findViewById(R.id.cumVariance2);
        efficiency2= findViewById(R.id.efficiency2);
        defectivePcs2= findViewById(R.id.defectivePcs2);
        defectPercentage2= findViewById(R.id.defectPercentage2);
        totalDefect2= findViewById(R.id.totalDefect2);
        dhu2= findViewById(R.id.dhu2);
        topDefectName21= findViewById(R.id.topDefectName21);
        topDefectName22= findViewById(R.id.topDefectName22);
        topDefectName23= findViewById(R.id.topDefectName23);
        topDefectName24= findViewById(R.id.topDefectName24);
        topDefectName25= findViewById(R.id.topDefectName25);
        topDefectPercentage21= findViewById(R.id.topDefectPercentage21);
        topDefectPercentage22= findViewById(R.id.topDefectPercentage22);
        topDefectPercentage23= findViewById(R.id.topDefectPercentage23);
        topDefectPercentage24= findViewById(R.id.topDefectPercentage24);
        topDefectPercentage25= findViewById(R.id.topDefectPercentage25);


    }
}