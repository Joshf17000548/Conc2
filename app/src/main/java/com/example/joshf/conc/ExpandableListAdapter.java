package com.example.joshf.conc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter implements AdapterView.OnItemSelectedListener {
//public class ExpandableListAdapter extends ExpandableListActivity {

    //database
    public HIA3AActivity hia3test;
    public int group_pos;
    // int HIA3_Test3_Question5;
    private Context _context;
    private Activity context;
    ArrayAdapter<String> adapter1,adapter2,adapter3,adapter4;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private static final String TAG = "Video Check";
    int[] posArray = new int[88];

    //spinners for expandable view
    int HIA3_Test3_Question5,HIA3_Test3_Question6,HIA3_Test3_Question7,HIA3_Test3_Question8,
            HIA3_Test3_Question9, HIA3_Test3_Question10, HIA3_Test3_Question11,HIA3_Test3_Question12,
            HIA3_Test3_Question13,HIA3_Test3_Question14,HIA3_Test3_Question15,HIA3_Test3_Question16,
            HIA3_Test3_Question17,HIA3_Test3_Question18,HIA3_Test3_Question19,HIA3_Test3_Question20,
            HIA3_Test3_Question21,HIA3_Test3_Question22,HIA3_Test3_Question23,HIA3_Test3_Question24,
            HIA3_Test3_Question25, HIA3_Test3_Question26, HIA3_Test3_Question27;

    int HIA3_Test3_Question28,HIA3_Test3_Question29,HIA3_Test3_Question30,HIA3_Test3_Question31,
            HIA3_Test3_Question32, HIA3_Test3_Question33, HIA3_Test3_Question34,HIA3_Test3_Question35,
            HIA3_Test3_Question36,HIA3_Test3_Question37,HIA3_Test3_Question38,HIA3_Test3_Question39,
            HIA3_Test3_Question40,HIA3_Test3_Question41,HIA3_Test3_Question42,HIA3_Test3_Question43,
            HIA3_Test3_Question44,HIA3_Test3_Question45,HIA3_Test3_Question46,HIA3_Test3_Question47,
            HIA3_Test3_Question48, HIA3_Test3_Question49, HIA3_Test3_Question50;

    int HIA3_Test3_Question51,HIA3_Test3_Question52,HIA3_Test3_Question53,HIA3_Test3_Question54,
            HIA3_Test3_Question55, HIA3_Test3_Question56, HIA3_Test3_Question57,HIA3_Test3_Question58,
            HIA3_Test3_Question59,HIA3_Test3_Question60,HIA3_Test3_Question61,HIA3_Test3_Question62,
            HIA3_Test3_Question63,HIA3_Test3_Question64,HIA3_Test3_Question65,HIA3_Test3_Question66,
            HIA3_Test3_Question67,HIA3_Test3_Question68,HIA3_Test3_Question69,HIA3_Test3_Question70,
            HIA3_Test3_Question71, HIA3_Test3_Question72, HIA3_Test3_Question73;

    int HIA3_Test3_Question74,HIA3_Test3_Question75,HIA3_Test3_Question76,HIA3_Test3_Question77,
            HIA3_Test3_Question78, HIA3_Test3_Question79, HIA3_Test3_Question80,HIA3_Test3_Question81,
            HIA3_Test3_Question82,HIA3_Test3_Question83,HIA3_Test3_Question84,HIA3_Test3_Question85,
            HIA3_Test3_Question86,HIA3_Test3_Question87,HIA3_Test3_Question88,HIA3_Test3_Question89,
            HIA3_Test3_Question90,HIA3_Test3_Question91,HIA3_Test3_Question92;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        group_pos=groupPosition;
        Log.v(TAG, "Group Pos: " + groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandable_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        String [] req_by =
                {"N/A","1 - Mild","2 - Mild","3 - Moderate","4 - Moderate","5 - Severe","6 - Severe"};

        Spinner spinner1 = (Spinner) convertView.findViewById(R.id.spinner33);
        adapter1 = new ArrayAdapter<String>(this._context,
                android.R.layout.simple_spinner_item, req_by);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);

        String [] when =
                {"N/A","On the pitch","Post match, same-day","Post-match, days after"};

        Spinner spinner2 = (Spinner) convertView.findViewById(R.id.spinner34);
        adapter2 = new ArrayAdapter<String>(this._context,
                android.R.layout.simple_spinner_item, when);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);

        String [] dur =
                {"N/A","0-15 minutes","15 minutes to 1 hour","1 hour to the 1st night", "1st night to 2nd night", "Beyond the 2nd night"};

        Spinner spinner3 = (Spinner) convertView.findViewById(R.id.spinner35);
        adapter3 = new ArrayAdapter<String>(this._context,
                android.R.layout.simple_spinner_item, dur);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter3);

        String [] pres =
                {"N/A","1 - Mild","2 - Mild","3 - Moderate","4 - Moderate","5 - Severe","6 - Severe"};

        Spinner spinner4 = (Spinner) convertView.findViewById(R.id.spinner36);
        adapter4 = new ArrayAdapter<String>(this._context,
                android.R.layout.simple_spinner_item,pres);
        adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner4.setAdapter(adapter4);

        spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner3.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner4.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        switch (group_pos){
            case 0:
                spinner1.setSelection(posArray[0]);
                spinner2.setSelection(posArray[1]);
                spinner3.setSelection(posArray[2]);
                spinner4.setSelection(posArray[3]);
                break;
            case 1:
                spinner1.setSelection(posArray[4]);
                spinner2.setSelection(posArray[5]);
                spinner3.setSelection(posArray[6]);
                spinner4.setSelection(posArray[7]);
                break;
            case 2:
                spinner1.setSelection(posArray[8]);
                spinner2.setSelection(posArray[9]);
                spinner3.setSelection(posArray[10]);
                spinner4.setSelection(posArray[11]);
                break;
            case 3:
                spinner1.setSelection(posArray[12]);
                spinner2.setSelection(posArray[13]);
                spinner3.setSelection(posArray[14]);
                spinner4.setSelection(posArray[15]);
                break;
            case 4:
                spinner1.setSelection(posArray[16]);
                spinner2.setSelection(posArray[17]);
                spinner3.setSelection(posArray[18]);
                spinner4.setSelection(posArray[19]);
                break;
            case 5:
                spinner1.setSelection(posArray[20]);
                spinner2.setSelection(posArray[21]);
                spinner3.setSelection(posArray[22]);
                spinner4.setSelection(posArray[23]);
                break;
            case 6:
                spinner1.setSelection(posArray[24]);
                spinner2.setSelection(posArray[25]);
                spinner3.setSelection(posArray[26]);
                spinner4.setSelection(posArray[27]);
                break;
            case 7:
                spinner1.setSelection(posArray[28]);
                spinner2.setSelection(posArray[29]);
                spinner3.setSelection(posArray[30]);
                spinner4.setSelection(posArray[31]);
                break;
            case 8:
                spinner1.setSelection(posArray[32]);
                spinner2.setSelection(posArray[33]);
                spinner3.setSelection(posArray[34]);
                spinner4.setSelection(posArray[35]);
                break;
            case 9:
                spinner1.setSelection(posArray[36]);
                spinner2.setSelection(posArray[37]);
                spinner3.setSelection(posArray[38]);
                spinner4.setSelection(posArray[39]);
                break;
            case 10:
                spinner1.setSelection(posArray[40]);
                spinner2.setSelection(posArray[41]);
                spinner3.setSelection(posArray[42]);
                spinner4.setSelection(posArray[43]);
                break;
            case 11:
                spinner1.setSelection(posArray[44]);
                spinner2.setSelection(posArray[45]);
                spinner3.setSelection(posArray[46]);
                spinner4.setSelection(posArray[47]);
                break;
            case 12:
                spinner1.setSelection(posArray[48]);
                spinner2.setSelection(posArray[49]);
                spinner3.setSelection(posArray[50]);
                spinner4.setSelection(posArray[51]);
                break;
            case 13:
                spinner1.setSelection(posArray[52]);
                spinner2.setSelection(posArray[53]);
                spinner3.setSelection(posArray[54]);
                spinner4.setSelection(posArray[55]);
                break;
            case 14:
                spinner1.setSelection(posArray[56]);
                spinner2.setSelection(posArray[57]);
                spinner3.setSelection(posArray[58]);
                spinner4.setSelection(posArray[59]);
                break;
            case 15:
                spinner1.setSelection(posArray[60]);
                spinner2.setSelection(posArray[61]);
                spinner3.setSelection(posArray[62]);
                spinner4.setSelection(posArray[63]);
                break;
            case 16:
                spinner1.setSelection(posArray[64]);
                spinner2.setSelection(posArray[65]);
                spinner3.setSelection(posArray[66]);
                spinner4.setSelection(posArray[67]);
                break;
            case 17:
                spinner1.setSelection(posArray[68]);
                spinner2.setSelection(posArray[69]);
                spinner3.setSelection(posArray[70]);
                spinner4.setSelection(posArray[71]);
                break;
            case 18:
                spinner1.setSelection(posArray[72]);
                spinner2.setSelection(posArray[73]);
                spinner3.setSelection(posArray[74]);
                spinner4.setSelection(posArray[75]);
                break;
            case 19:
                spinner1.setSelection(posArray[76]);
                spinner2.setSelection(posArray[77]);
                spinner3.setSelection(posArray[78]);
                spinner4.setSelection(posArray[79]);
                break;
            case 20:
                spinner1.setSelection(posArray[80]);
                spinner2.setSelection(posArray[81]);
                spinner3.setSelection(posArray[82]);
                spinner4.setSelection(posArray[83]);
                break;
            case 21:
                spinner1.setSelection(posArray[84]);
                spinner2.setSelection(posArray[85]);
                spinner3.setSelection(posArray[86]);
                spinner4.setSelection(posArray[87]);
                break;
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true;}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ExpandableListAdapter c = this;
        if(c instanceof ExpandableListAdapter) {
            hia3test = (HIA3AActivity) _context;

            switch (group_pos) {
                case 0:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            HIA3_Test3_Question5 = position;
                            Log.v(TAG, "Headaches spinner1");
                            posArray[0] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question5(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: ");
                            HIA3_Test3_Question6 = position;
                            posArray[1] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question6(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question7 = position;
                            posArray[2] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question7(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question8 = position;
                            posArray[3] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question8(position);
                            return;
                    }
                    return;
                case 1:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Pressure in head spinner1 ");
                            HIA3_Test3_Question9 = position;
                            posArray[4] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question9(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question10 = position;
                            posArray[5] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question10(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question11 = position;
                            posArray[6] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question11(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question12 = position;
                            posArray[7] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question12(position);
                            return;
                    }
                    return;
                case 2:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Neckpain spinner1");
                            HIA3_Test3_Question13 = position;
                            posArray[8] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question13(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question14 = position;
                            posArray[9] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question14(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question15 = position;
                            posArray[10] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question15(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question16 = position;
                            posArray[11] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question16(position);
                            return;
                    }
                    return;
                case 3:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Naus spinner1");
                            HIA3_Test3_Question17 = position;
                            posArray[12] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question17(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question18 = position;
                            posArray[13] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question18(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question19 = position;
                            posArray[14] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question19(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question20 = position;
                            posArray[15] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question20(position);
                            return;
                    }
                    return;
                case 4:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Dizziness spinner1");
                            HIA3_Test3_Question21 = position;
                            posArray[16] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question21(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question22 = position;
                            posArray[17] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question22(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question23 = position;
                            posArray[18] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question23(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question24 = position;
                            posArray[19] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question24(position);
                            return;
                    }
                    return;
                case 5:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Blurred  spinner1");
                            HIA3_Test3_Question25 = position;
                            posArray[20] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question25(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question26 = position;
                            posArray[21] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question26(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question27 = position;
                            posArray[22] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question27(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question28 = position;
                            posArray[23] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question28(position);
                            return;
                    }
                    return;
                case 6:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "balance spinner1");
                            HIA3_Test3_Question29 = position;
                            posArray[24] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question29(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question30 = position;
                            posArray[25] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question30(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question31 = position;
                            posArray[26] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question31(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question32 = position;
                            posArray[27] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question32(position);
                            return;
                    }
                    return;
                case 7:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "sense l spinner1");
                            HIA3_Test3_Question33 = position;
                            posArray[28] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question33(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question34 = position;
                            posArray[29] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question34(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question35 = position;
                            posArray[30] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question35(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question36 = position;
                            posArray[31] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question36(position);
                            return;
                    }
                    return;
                case 8:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "sense n spinner1");
                            HIA3_Test3_Question37 = position;
                            posArray[32] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question37(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question38 = position;
                            posArray[33] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question38(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question39 = position;
                            posArray[34] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question39(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question40 = position;
                            posArray[35] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question40(position);
                            return;
                    }
                    return;
                case 9:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "slow spinner1");
                            HIA3_Test3_Question41 = position;
                            posArray[36] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question41(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question42 = position;
                            posArray[37] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question42(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question43 = position;
                            posArray[38] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question43(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question44 = position;
                            posArray[39] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question44(position);
                            return;
                    }
                    return;
                case 10:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "fog spinner1");
                            HIA3_Test3_Question45 = position;
                            posArray[40] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question45(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question46 = position;
                            posArray[41] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question46(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question47 = position;
                            posArray[42] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question47(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question48 = position;
                            posArray[43] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question48(position);
                            return;
                    }
                    return;
                case 11:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "dfr spinner1");
                            HIA3_Test3_Question49 = position;
                            posArray[44] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question49(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question50 = position;
                            posArray[45] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question50(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question51 = position;
                            posArray[46] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question51(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question52 = position;
                            posArray[47] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question52(position);
                            return;
                    }
                    return;
                case 12:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "dif conc spinner1");
                            HIA3_Test3_Question53 = position;
                            posArray[48] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question53(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question54 = position;
                            posArray[49] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question54(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question55 = position;
                            posArray[50] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question55(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question56 = position;
                            posArray[51] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question56(position);
                            return;
                    }
                    return;
                case 13:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "dif rem spinner1");
                            HIA3_Test3_Question57 = position;
                            posArray[52] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question57(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question58 = position;
                            posArray[53] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question58(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question59 = position;
                            posArray[54] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question59(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question60 = position;
                            posArray[55] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question60(position);
                            return;
                    }
                    return;
                case 14:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "fat spinner1");
                            HIA3_Test3_Question61 = position;
                            posArray[56] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question61(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question62 = position;
                            posArray[57] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question62(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question63 = position;
                            posArray[58] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question63(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question64 = position;
                            posArray[59] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question64(position);
                            return;
                    }
                    return;
                case 15:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "conf spinner1");
                            HIA3_Test3_Question65 = position;
                            posArray[60] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question65(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question66 = position;
                            posArray[61] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question66(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question67 = position;
                            posArray[62] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question67(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question68 = position;
                            posArray[63] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question68(position);
                            return;
                    }
                    return;
                case 16:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Drowsi spinner1");
                            HIA3_Test3_Question69 = position;
                            posArray[64] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question69(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question70 = position;
                            posArray[65] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question70(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question71 = position;
                            posArray[66] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question71(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question72 = position;
                            posArray[67] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question72(position);
                            return;
                    }
                    return;
                case 17:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Troub spinner1");
                            HIA3_Test3_Question73 = position;
                            posArray[68] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question73(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question74 = position;
                            posArray[69] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question74(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question75 = position;
                            posArray[70] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question75(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question76 = position;
                            posArray[71] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question76(position);
                            return;
                    }
                    return;
                case 18:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "More emotion spinner1");
                            HIA3_Test3_Question77 = position;
                            posArray[72] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question77(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question78 = position;
                            posArray[73] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question78(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question79 = position;
                            posArray[74] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question79(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question80 = position;
                            posArray[75] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question80(position);
                            return;
                    }
                    return;
                case 19:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Irrit spinner1");
                            HIA3_Test3_Question81 = position;
                            posArray[76] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question81(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question82 = position;
                            posArray[77] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question82(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question83 = position;
                            posArray[78] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question83(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question84 = position;
                            posArray[79] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question84(position);
                            return;
                    }
                    return;
                case 20:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Sad spinner1 " + position);
                            HIA3_Test3_Question85 = position;
                            posArray[80] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question85(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question86 = position;
                            posArray[81] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question86(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question87 = position;
                            posArray[82] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question87(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question88 = position;
                            posArray[83] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question88(position);
                            return;
                    }
                    return;
                case 21:
                    switch (parent.getId()) {
                        case R.id.spinner33:
                            Log.v(TAG, "Nerv spinner1");
                            HIA3_Test3_Question89 = position;
                            posArray[84] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question89(position);
                            return;
                        case R.id.spinner34:
                            Log.v(TAG, "Video Checkbox1: " + position);
                            HIA3_Test3_Question90 = position;
                            posArray[85] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question90(position);
                            return;
                        case R.id.spinner35:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question91 = position;
                            posArray[86] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question91(position);
                            return;
                        case R.id.spinner36:
                            Log.v(TAG, "Video Checkbox2: " + position);
                            HIA3_Test3_Question92 = position;
                            posArray[87] = position;
                            hia3test.objHIA3.setHIA3_Test3_Question92(position);
                            return;
                    }
                    return;

            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

