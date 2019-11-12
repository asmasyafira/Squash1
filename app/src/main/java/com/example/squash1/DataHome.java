package com.example.squash1;

import com.example.squash1.model.HomeModel;

import java.util.ArrayList;

public class DataHome {
    public static String[][] data = new String[][]{

    };

    public static ArrayList<HomeModel> getListData(){
        ArrayList<HomeModel> list = new ArrayList<>();
        for (String[] arrData : data){
            HomeModel home = new HomeModel();
            home.setName(arrData[0]);
            home.setDescription(arrData[1]);
            home.setPhoto(arrData[2]);

            list.add(home);
        }
        return list;
    }
}
