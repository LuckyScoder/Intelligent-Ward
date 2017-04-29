package com.example.lenovo.controller.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.controller.R;
import com.example.lenovo.controller.db.MultiRaspData;
import com.example.lenovo.controller.util.HttpUtil;
import com.example.lenovo.controller.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ContentNowFragment extends android.support.v4.app.Fragment{
    private IntentFilter filter;
    private BroadcastReceiver receiver;
    private LocalBroadcastManager manager;
    private LineChartView lineChart1;
    private LineChartView lineChart2;
    private List<String> list1;
    private List<String> list2;
/*    String[] date={"8-00","9-00","10-00","11-00","12-00",
            "13-00","14-00","15-00","16-00","17-00"}; //x轴的标注
    int[] temperature={10,22,30,33,10,24,22,18,19,20}; //图表的数据点
    int[] humidity={10,20,30,25,34,12,45,23,12,23}; //第二个图标的数据点*/
    private List<PointValue> mPointValues1;
    private List<PointValue> mPointValues2;
    private List<AxisValue> mAxisValues;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.second_ui_now,container,false);
        lineChart1=(LineChartView)view.findViewById(R.id.line_chart_temp_now);
        lineChart2=(LineChartView)view.findViewById(R.id.line_chart_hum_now);
        mPointValues1=new ArrayList<>();
        mPointValues2=new ArrayList<>();
        mAxisValues=new ArrayList<>();
        list1=new ArrayList<>();list1.add("时间");list1.add("温度/℃");
        list2=new ArrayList<>();list2.add("时间");list2.add("湿度/%rh");
        Log.d("todayFragment","onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getAxisXLabels(); //获取x轴的标注
 /*       getAxisPoints(); //获取坐标点
        initLineChart(mPointValues1,lineChart1,list1); //初始化
        initLineChart(mPointValues2,lineChart2,list2); //初始化*/
        manager = LocalBroadcastManager.getInstance(getActivity());
        filter = new IntentFilter();
        filter.addAction("com.example.lenovo.controller.BROADCAST2");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("ContentNowFragment", "onReceive: ");
                requestMultiRaspData();
            }
        };
        manager.registerReceiver(receiver,filter);
        requestMultiRaspData();
        Log.d("todayFragment","onActivityCreated");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }

    /*   //设置x轴的显示
        private void getAxisXLabels(){
            for(int i=0;i<date.length;i++){
                mAxisValues.add(new AxisValue(i).setLabel(date[i]));
                //Log.d("x",date[i]);
            }
        }

        //图表的每个点的显示
        private void getAxisPoints(){
            for(int i=0;i<temperature.length;i++){
                mPointValues1.add(new PointValue(i,temperature[i]).setLabel(temperature[i]+""));
                mPointValues2.add(new PointValue(i,humidity[i]).setLabel(humidity[i]+""));
            }
        }
    */
    private void initLineChart(List<PointValue> pointValues,LineChartView lineChart,List<String> list){
        List<Line> lines=new ArrayList<>();

        Line line=new Line(pointValues).setColor(R.color.khaki); //折线的颜色
        line.setShape(ValueShape.CIRCLE); //折线图上每个数据点的形状（圆形）
        line.setCubic(false); //曲线是否平滑（曲线还是折线）
        line.setStrokeWidth(2); //线条的粗细，默认是3
        line.setFilled(false); //是否填充曲线的面积
        line.setHasLabels(true); //曲线的数据坐标是否加上备注
        //line.setHasLabelsOnlyForSelected(true); //点击数据坐标提示数据，设置了这个上面那个就无效
        line.setHasLines(true); //是否用线显示。false则没有曲线只有点
        line.setHasPoints(true); //是否显示圆点。false没有圆点只有点
        lines.add(line);

        LineChartData data=new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX=new Axis(); //x轴
        //axisX.setHasTiltedLabels(true); //x坐标轴字体是斜的还是直的，true是斜的
        axisX.setTextColor(Color.BLACK); //设置字体颜色
        axisX.setName(list.get(0)); //表格名称
        axisX.setTextSize(8); //设置字体大小
        axisX.setMaxLabelChars(7); //最多几个x轴坐标
        axisX.setValues(mAxisValues); //填充x轴的坐标名称
        data.setAxisXBottom(axisX); //x轴在底部
        //axisX.setHasLines(true); //x轴分割线

        //y轴是根据数据的大小自动设置y轴上限（也可固定y轴的数据个数）
        Axis axisY1=new Axis(); //y轴
        axisY1.setName(list.get(1)); //y轴标注
        axisY1.setTextSize(8); //设置字体大小
        axisY1.setTextColor(Color.BLACK);
        //axisY1.setTextColor(R.color.yellow);
        data.setAxisYLeft(axisY1); //y轴设置在左边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setLineChartData(data);
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL); //缩放类型，水平
        lineChart.setMaxZoom((float)3); //缩放比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);


        Viewport v=new Viewport(lineChart.getMaximumViewport());
        v.left=0;
        v.right=10;
        lineChart.setCurrentViewport(v);
    }

    public void requestMultiRaspData(){
        String raspdataurl = "http://www.raspfwwb.com";
        HttpUtil.sendOkHttpRequest(raspdataurl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"获取温湿度信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final MultiRaspData multiRaspData = Utility.handleMultiRaspDataResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(multiRaspData != null && "ok".equals(multiRaspData.getStatus())){
                            showLineChart(multiRaspData);
                        }
                    }
                });
            }
        });
    }

    private void showLineChart(MultiRaspData multiRaspData){
        List<Double> temperature;
        List<Double> humidity;
        temperature = multiRaspData.getTemperature();
        humidity = multiRaspData.getHumidity();
        //给每个折线点赋值
        for(int i=0;i<temperature.size();i++){
            mPointValues1.add(new PointValue(i,temperature.get(i).floatValue()).setLabel(temperature.get(i).toString()));
            mPointValues2.add(new PointValue(i,humidity.get(i).floatValue()).setLabel(humidity.get(i).toString()));
        }
        initLineChart(mPointValues1,lineChart1,list1);
        initLineChart(mPointValues2,lineChart2,list2);
    }
}
