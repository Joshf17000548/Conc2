package com.example.joshf.conc;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.nfc.Tag;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh_PC on 2016/07/25.
 */
public class PlayerAdapter extends ArrayAdapter<Player> implements Filterable {
    private ArrayList<Player> playerList;
    private Activity context;
    private Filter playerFilter;
    private ArrayList<Player> origPlayerList;
    String TAG = "PlayerAdapter";

    DisplayImageOptions options;
    ImageLoader imageLoader;

    public SwipeMenuListView list;
    public TextView noResult;
    //public String photoPath;
    public TextView txtTitle;
    private ImageView imageView;


    public PlayerAdapter(Activity context, ArrayList<Player> players) {
        super(context, R.layout.playerlist_item, players);
        this.context = context;
        this.playerList = players;
        this.origPlayerList = players;

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "http://104.198.254.110/ConcApp/Player_Image/");
        try {
            // Get singletone instance of ImageLoader
            imageLoader = ImageLoader.getInstance();
            // Create configuration for ImageLoader (all options are optional)
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
// Initialize ImageLoader with created configuration. Do it once.
            imageLoader.init(config);
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .showImageOnLoading(R.mipmap.ic_launcher)//display stub image until image is loaded
                    .displayer(new RoundedBitmapDisplayer(50))
                    .build();
            //---------------/IMG----
        }catch (Exception e){
            e.printStackTrace();
        }

        list = (SwipeMenuListView) context.findViewById(R.id.list_view_swipe);
        noResult = (TextView) context.findViewById(R.id.no_result);

    }
    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public long getItemId(int i) {
        return playerList.get(i).hashCode();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        PlayerHolder holder = new PlayerHolder();

        if(view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.playerlist_item, null);

            txtTitle = (TextView) view.findViewById(R.id.playerName);
            imageView = (ImageView) view.findViewById(R.id.playerPhoto);
           // playerStatus = (TextView) context.findViewById(R.id.no_result);

            holder.playerNameView = txtTitle;
            holder.playerPhoto = imageView;
           // holder.noResult = (TextView) context.findViewById(R.id.no_result);

            view.setTag(holder);
        }
        else{
            holder = (PlayerHolder) view.getTag();
        }

        String photoPath = String.valueOf(playerList.get(position).getCode_Player());
        try {
            imageLoader.displayImage("http://104.198.254.110/ConcApp/Player_Image/" + photoPath +"IMG.png", holder.playerPhoto, options);
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.playerNameView.setText(playerList.get(position).getPlayer_Name());

        return view;
    }

    private static class PlayerHolder {
        public TextView playerNameView;
        public ImageView playerPhoto;
    }

    @Override
    public Filter getFilter() {
        if (playerFilter == null) {
            playerFilter = new PlayerFilter();
        }
        return playerFilter;
    }
    private class PlayerFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint==null || constraint.length() ==0) {
                results.count = origPlayerList.size();
                results.values = origPlayerList;
            }else{
                ArrayList<Player> tempList = new ArrayList<Player>();
                // search content in friend list
                for (Player player : origPlayerList) {
                    if (player.getPlayer_Name().contains(constraint.toString())) {
                        tempList.add(player);
                    }
                }
                results.count = tempList.size();
                results.values = tempList;
            }

            return results;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            playerList = (ArrayList<Player>) results.values;
            if (results.count == 0) {
                list.setVisibility(View.GONE);
                noResult.setVisibility(View.VISIBLE);
                notifyDataSetInvalidated();

            }else {
                list.setVisibility(View.VISIBLE);
                noResult.setVisibility(View.GONE);
                notifyDataSetChanged();
            }
        }
    }
}
