package com.example.kadh.ui.main.bean;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/4
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class WeatherBean {
    private String weather;
    private String temperature;
    private String wind;
    private String ptype;

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", wind='" + wind + '\'' +
                ", ptype='" + ptype + '\'' +
                '}';
    }
}
