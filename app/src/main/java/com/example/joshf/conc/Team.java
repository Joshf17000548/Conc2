package com.example.joshf.conc;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by joshf on 2016/10/30.
 */

public class Team implements java.io.Serializable{

    public int Code_Team;
    public String Team_Name;


    public int getCode_Team(){
        return this.Code_Team;
    }
    public String getTeam_Name(){
        return this.Team_Name;
    }


    // Constructor to convert JSON object into a Java class instance
    public Team(JSONObject object){
        try {
            this.Team_Name = object.getString("Team_Name");
            this.Code_Team = Integer.valueOf(object.getString("Code_Team"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Team(){

    }
    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<Team> fromJson(JSONArray jsonObjects) {
        ArrayList<Team> teams = new ArrayList<Team>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                teams.add(new Team(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return teams;
    }
    /*public Team(Parcel source) {
        Team_Name = source.toString();
        Code_Team = source.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Team_Name);
        dest.writeInt(Code_Team);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };*/
}