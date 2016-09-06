package com.example.joshf.conc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.nfc.Tag;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh_PC on 2016/07/25.
 */
public class PlayerAdapter extends ArrayAdapter<Player> implements Filterable {
    ArrayList<Player> playerList;
    ArrayList<Player> mList;
    private Activity context;
    private Filter playerFilter;
    private ArrayList<Player> origPlayerName;
    String TAG = "Conc";

    public ListView list;
    public TextView noResult;
    public String photoPath;

    private final Integer[] imageId;
    public TextView txtTitle;


    public PlayerAdapter(Activity context, ArrayList<Player> playerName, Integer[] imageId) {
        super(context, R.layout.playerlist_item, playerName);
       // Log.e("image", "1");
        this.context = context;
        this.playerList = playerName;
        this.origPlayerName = playerName;
        this.imageId = imageId;
        for (Integer n:imageId){
            Log.e("image", String.valueOf(n));
        }
        Log.e("image", "2");

        list = (ListView) context.findViewById(R.id.list_view);
        noResult = (TextView) context.findViewById(R.id.no_result);
        photoPath = context.getExternalFilesDir(null).toString();
        Log.e("image", "3");
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
        Log.e("image", "2");

        PlayerHolder holder = new PlayerHolder();

        if(view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.playerlist_item, null);

            txtTitle = (TextView) view.findViewById(R.id.playerName);
            ImageView imageView = (ImageView) view.findViewById(R.id.playerPhoto);


            holder.playerNameView = txtTitle;
            holder.playerPhoto = imageView;

            view.setTag(holder);
/*            txtTitle.setText(playerName.get(position));
            imageView.setImageResource(imageId[position]);*/

            //return rowView;
        }
        else{
            Log.e("image", "4");
            holder = (PlayerHolder) view.getTag();
        }
        Log.e("image", "5");
        holder.playerNameView.setText(playerList.get(position).getPlayer_Name());

        String photoPath2 = photoPath+"/"+imageId[position]+"/"+ imageId[position]+"P.jpg";

        File imgFile = new  File(photoPath2);
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
        holder.playerPhoto.setImageBitmap(rotatedBitmap);

        //holder.playerPhoto.setImageResource(imageId[position]);

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
                results.count = origPlayerName.size();
                results.values = origPlayerName;
            }else{
                ArrayList<Player> tempList = new ArrayList<Player>();
                // search content in friend list
                for (Player player : origPlayerName) {
                    if (player.getPlayer_Name().contains(constraint.toString())) {
                        tempList.add(player);
                    }
                }
                results.count = tempList.size();
                results.values = tempList;
                for (Player p: tempList)
                    Log.e("Conc",p.getPlayer_Name());
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
