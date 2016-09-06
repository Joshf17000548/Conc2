package com.example.joshf.conc;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by joshf on 2016/07/25.
 */
public class Player implements java.io.Serializable{
    public int Code_Player;
    public int Code_Team;
    public String Player_Name;
    public String Player_Photo;
    public boolean lenses;
    public double weight;
    public double height;
    public Calendar DOB;

    public String getPlayer_Photo(){
        return this.Player_Photo;
    }
    public String getPlayer_Name(){
        return this.Player_Name;
    }
    public boolean getLenses(){
        return this.lenses;
    }
    public double getPlayer_Height(){
        return this.height;
    }
    public double getPlayer_Weight(){
        return this.weight;
    }
    public int getCode_Player(){
        return this.Code_Player;
    }
    public Calendar getDOB_player(){
        return DOB;
    }

    public int[] getDOB() {
        int[] DOBString = {DOB.get(Calendar.YEAR),DOB.get(Calendar.MONTH),
                DOB.get(Calendar.DAY_OF_MONTH)};
        return DOBString;
    }

    /*    public Player(JSONObject object) {
        try {
            this.Player_Name = object.getString("Team_Name");
            this.Code_Player = Integer.valueOf(object.getString("Code_Team"));
            this.Player_Photo = object.getString("Team_Logo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    public Player(){
    }




}
