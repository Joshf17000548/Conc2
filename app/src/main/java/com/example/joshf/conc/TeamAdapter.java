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
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh_PC on 2016/07/25.
 */
public class TeamAdapter extends ArrayAdapter<Team> implements Filterable {

    private ArrayList<Team> teamList;
    private Activity context;
    private Filter teamFilter;
    private ArrayList<Team> origTeamList;
    String TAG = "TeamAdapter";

    DisplayImageOptions options;
    ImageLoader imageLoader;

    public SwipeMenuListView list;
    public TextView noResult;
    //public String photoPath;
    public TextView nameTxt;
    private ImageView imageView;
    private ImageView brainStatus;


    public TeamAdapter(Activity context, ArrayList<Team> teams) {
        super(context, R.layout.playerlist_item, teams);
        this.context = context;
        this.teamList = teams;
        this.origTeamList = teams;

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "http://104.198.254.110/ConcApp/Team_Logo/");
        try {
            // Get singletone instance of ImageLoader
            imageLoader = ImageLoader.getInstance();
            // Create configuration for ImageLoader (all options are optional)
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
// Initialize ImageLoader with created configuration. Do it once.
            imageLoader.init(config);
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(false)
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
        return teamList.size();
    }

    @Override
    public long getItemId(int i) {
        return teamList.get(i).hashCode();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        TeamHolder holder = new TeamHolder();

        if(view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.teamlist_item, null);

            nameTxt = (TextView) view.findViewById(R.id.name);
            imageView = (ImageView) view.findViewById(R.id.playerPhoto);
            brainStatus = (ImageView) view.findViewById(R.id.playerHealth);
            // playerStatus = (TextView) context.findViewById(R.id.no_result);

            holder.teamNameView = nameTxt;
            holder.teamPhoto = imageView;
            holder.teamBrainStatus = brainStatus;
            // holder.noResult = (TextView) context.findViewById(R.id.no_result);

            view.setTag(holder);
        }
        else{
            holder = (TeamHolder) view.getTag();
        }

/*        String photoPath = String.valueOf(teamList.get(position).getCode_Team());
        String path = "http://104.198.254.110/ConcApp/Team_Logo/" + photoPath +"IMG.png";

        try {
            imageLoader.displayImage(path, holder.teamPhoto, options);

        }catch (Exception e){
            e.printStackTrace();
        }*/
        holder.teamNameView.setText(teamList.get(position).getTeam_Name());
        Log.e(TAG, teamList.get(position).getTeam_Name());
        //teamList.get(position).Player_Status= position % 3;
      //  Log.e("mod", String.valueOf(teamList.get(position).Player_Status));

     //   int status = teamList.get(position).getPlayer_Status();

        return view;
    }

    private static class TeamHolder {
        TextView teamNameView;
        ImageView teamPhoto;
        ImageView teamBrainStatus;
    }

    @Override
    public Filter getFilter() {
        if (teamFilter == null) {
            teamFilter = new TeamFilter();
        }
        return teamFilter;
    }
    private class TeamFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint==null || constraint.length() ==0) {
                results.count = origTeamList.size();
                results.values = origTeamList;
            }else{
                ArrayList<Team> tempList = new ArrayList<Team>();
                // search content in friend list
                for (Team team : origTeamList) {
                    if (team.getTeam_Name().contains(constraint.toString())) {
                        tempList.add(team);
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
            teamList = (ArrayList<Team>) results.values;
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