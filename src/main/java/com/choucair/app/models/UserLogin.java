package com.choucair.app.models;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserLogin {
    String usuario;
    String contrasena;

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }


    public static List<UserLogin> setData(DataTable dataTable) {
        List<UserLogin> dates = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, UserLogin.class));
        }
        return dates;
    }
}

