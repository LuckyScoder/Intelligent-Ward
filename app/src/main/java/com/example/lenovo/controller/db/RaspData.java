package com.example.lenovo.controller.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/4/17.
 */

public class RaspData extends DataSupport {

    private String status;          //接收状态

    private double temperature;     //温度

    private double humidity;        //湿度

    private String time;           //时间

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
