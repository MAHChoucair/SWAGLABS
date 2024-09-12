package com.choucair.app.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;

public class DatosSolicitudMortgage {
    private String typeofloan;
    private String numberyear;
    private String typeofocupation;
    private String yearlyIncome;

    public String getTypeofloan() {
        return typeofloan;
    }

    public void setTypeofloan(String typeofloan) {
        this.typeofloan = typeofloan;
    }

    public String getNumberyear() {
        return numberyear;
    }

    public void setNumberyear(String numberyear) {
        this.numberyear = numberyear;
    }

    public String getTypeofocupation() {
        return typeofocupation;
    }

    public void setTypeofocupation(String typeofocupation) {
        this.typeofocupation = typeofocupation;
    }

    public String getYearlyIncome() {
        return yearlyIncome;
    }

    public void setYearlyIncome(String yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    public static List<DatosSolicitudMortgage> setData(DataTable dataTable) {
        List<DatosSolicitudMortgage> dates = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, DatosSolicitudMortgage.class));
        }
        return dates;
    }
}
