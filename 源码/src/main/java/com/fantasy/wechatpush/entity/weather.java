package com.fantasy.wechatpush.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class weather {
    private String area;
    private String date;
    private String vis;
    private String sunrise;
    private String week;
    private String pcpn;
    private String weatherimg;
    private String real;
    private String moonrise;
    private String lowest;
    private String tips;
    private String pop;
    private String winddeg;
    private String windsc;
    @JsonProperty("uv_index")
    private String uvIndex;
    private String highest;
    private String sunset;
    private String weather;
    private String windspeed;
    private String moondown;
    private String humidity;
    private String wind;

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getVis() {
        return vis;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setWeatherimg(String weatherimg) {
        this.weatherimg = weatherimg;
    }

    public String getWeatherimg() {
        return weatherimg;
    }

    public void setReal(String real) {
        this.real = real;
    }

    public String getReal() {
        return real;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public String getLowest() {
        return lowest;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTips() {
        return tips;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPop() {
        return pop;
    }

    public void setWinddeg(String winddeg) {
        this.winddeg = winddeg;
    }

    public String getWinddeg() {
        return winddeg;
    }

    public void setWindsc(String windsc) {
        this.windsc = windsc;
    }

    public String getWindsc() {
        return windsc;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public String getHighest() {
        return highest;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunset() {
        return sunset;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setMoondown(String moondown) {
        this.moondown = moondown;
    }

    public String getMoondown() {
        return moondown;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWind() {
        return wind;
    }
}
