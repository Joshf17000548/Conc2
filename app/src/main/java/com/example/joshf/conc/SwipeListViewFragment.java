package com.example.joshf.conc;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by joshf on 2016/09/13.
 */
public class SwipeListViewFragment extends Fragment implements ConfirmationDialog.DeleteDialogListener{

    public PlayerAdapter mAdapter;
    private SwipeMenuListView mListView;
    public ArrayList<Player> playerList;
    String TAG = "SwipeListViewFragment";
    private int playerPositionForDelete;
    int deleteCode;
    Activity activity;


    ListSelected listCallback;


    private SearchView searchView;
    private SearchManager searchManager;

    static public interface ListSelected {
        public void listItemSelected(Player player, View view);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listCallback = (PlayerSelect) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CheckSelectedCallback");
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe_player_list, container, false);
        playerList =(ArrayList<Player>) getArguments().getSerializable("player_list");
        mListView = (SwipeMenuListView) view.findViewById(R.id.list_view_swipe);
        //playerPhoto = (ImageView) view.findViewById(R.id.playerPhoto) ;
        setHasOptionsMenu(true);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mAdapter = new PlayerAdapter(getActivity(), playerList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Player selectedPlayer = mAdapter.getItem(position);
                        listCallback.listItemSelected(selectedPlayer,view);
                    }
                }
        );


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));

                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);

                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                DialogFragment dialog = new ConfirmationDialog();
                dialog.show(getChildFragmentManager(), "ConformationDialog");
                playerPositionForDelete=position;


            return false;
            }
        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }
            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }
            @Override
            public void onMenuClose(int position) {
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
               // Log.e(TAG, query);
                if (mListView != null)
                mAdapter.getFilter().filter(query);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    public void onDialogPositiveClick(DialogFragment dialog) {
        Player selectedPlayer = mAdapter.getItem(playerPositionForDelete);
        deleteCode = selectedPlayer.getCode_Player();
        new deletePlayers().execute();
        mAdapter.remove(selectedPlayer);
        mAdapter.notifyDataSetChanged();
    }


    public void onDialogNegativeClick(DialogFragment dialog) {
    }


    public static SwipeListViewFragment newInstance() {
        return new SwipeListViewFragment();
    }

    private class deletePlayers extends AsyncTask<String, String, JSONArray> {

        private static final String URL = "http://104.198.254.110/ConcApp/deletePlayer.php";
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... params) {

            try {
                HashMap<String, String> args = new HashMap<>();

                args.put("Code_Player",String.valueOf(deleteCode));


                JSONArray json = jsonParser.makeHttpRequest(
                        URL, "POST", args);

                if (json != null) {
                    Log.e("JSON REQUEST", params.toString());
                    Log.e("JSON result", json.toString());
                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray) {


        }
    }


}


