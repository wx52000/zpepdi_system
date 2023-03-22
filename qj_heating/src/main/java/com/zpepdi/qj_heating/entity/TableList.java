package com.zpepdi.qj_heating.entity;

import java.util.List;
import java.util.Map;

public class TableList {

    private String tName;
    private List<String> btList;
    private List<List<String>> bodyList;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public List<String> getBtList() {
        return btList;
    }

    public void setBtList(List<String> btList) {
        this.btList = btList;
    }

    public List<List<String>> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<List<String>> bodyList) {
        this.bodyList = bodyList;
    }

    @Override
    public String toString() {
        return "TableList{" +
                "tName='" + tName + '\'' +
                ", btList=" + btList +
                '}';
    }
}
