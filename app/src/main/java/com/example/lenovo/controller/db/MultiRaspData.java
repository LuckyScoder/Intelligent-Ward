package com.example.lenovo.controller.db;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 */

public class MultiRaspData extends DataSupport {
    private String status;          //接收状态

    private List<Double> temperature;     //温度

    private List<Double> humidity;        //湿度

    private List<String> time;           //时间

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Double> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Double> temperature) {
        this.temperature = temperature;
    }

    public List<Double> getHumidity() {
        return humidity;
    }

    public void setHumidity(List<Double> humidity) {
        this.humidity = humidity;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
