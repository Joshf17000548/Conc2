package com.example.joshf.conc;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    public boolean Player_Lenses;
    public double Player_Weight;
    public double Player_Height;
    public String Player_DateOfBirth;

    public String getPlayer_Photo(){
        return this.Player_Photo;
    }
    public String getPlayer_Name(){
        return this.Player_Name;
    }
    public boolean getLenses(){
        return this.Player_Lenses;
    }
    public double getPlayer_Height(){
        return this.Player_Height;
    }
    public double getPlayer_Weight(){
        return this.Player_Weight;
    }
    public int getCode_Player(){
        return this.Code_Player;
    }
    public String getDOB_player(){
        return this.Player_DateOfBirth;
    }

    public Integer[] getDOBInt() {

        String yearSubStr = getDOB_player().substring(0, 4);
        String monthSubStr = getDOB_player().substring(5, 7);
        String daySubStr = getDOB_player().substring(8, 10);

        Integer[] DOBInt = {Integer.valueOf(yearSubStr),Integer.valueOf(monthSubStr),
                Integer.valueOf(daySubStr)};

        return DOBInt;
    }

    public Player(JSONObject object){

        if(object!=null) {
            try {
                this.Player_Name = object.getString("Player_Name");
                this.Code_Player = Integer.valueOf(object.getString("Code_Player"));
                this.Player_Weight = Float.valueOf(object.getString("Player_Weight"));
                this.Player_Height = Float.valueOf(object.getString("Player_Height"));
                this.Player_Lenses = Boolean.valueOf(object.getString("Player_Lenses"));
                this.Player_Photo = object.getString("Player_Photo");
                this.Player_DateOfBirth = object.getString("Player_DateOfBirth");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Player", "Error");
            }
        }
    }

    public static ArrayList<Player> fromJson(JSONArray jsonObjects) {
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {

                players.add(new Player(jsonObjects.getJSONObject(i)));
                Log.e("player", String.valueOf(players.get(i).getCode_Player()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return players;
    }





}
