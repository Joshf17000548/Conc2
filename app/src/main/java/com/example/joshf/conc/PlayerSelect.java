package com.example.joshf.conc;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayerSelect extends AppCompatActivity {

    String TAG = "Conc";

    private ListView listView;
    private SearchView searchView;
    private SearchManager searchManager;

    Player p;

    //GetData data = new GetData("Player");

    public ArrayList<Player> playerList;
    public PlayerAdapter adapter;

/*    String[] playerNames ={"Joshua Fischer", "Christoff Heunis", "Gareth Holmes", "Gerhard Smith",
            "Tys van der Merwe", "Liam Swanepoel"};
    Integer[] imageId = {R.drawable.joshua_fischer,R.drawable.joshua_fischer,
            R.drawable.joshua_fischer,R.drawable.joshua_fischer,R.drawable.joshua_fischer,
            R.drawable.joshua_fischer
    };*/


    //ArrayList<String> players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // Log.e(TAG, "Start");

        getPlayers();


        listView = (ListView)findViewById(R.id.list_view);

        adapter = new
                PlayerAdapter(PlayerSelect.this, playerList, getPhotoIDs());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Player selectedPlayer = adapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("player_select", "existing");
                        bundle.putBinder("player_info", new ObjectWrapperForBinder(selectedPlayer));
                        startActivity(new Intent(PlayerSelect.this, PlayerProfile.class).putExtras(bundle));
                    }
                }
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("player_select", "new");
                startActivity(new Intent(PlayerSelect.this, PlayerProfile.class).putExtras(bundle));

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playerselect_menu, menu);

        searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return true;
            }
        });

        //searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()) );

        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getNumberOfPlayers() {
        int numberOfPlayers;
        File file = new File(this.getExternalFilesDir(null).toString());
        File[] list = file.listFiles();
        numberOfPlayers = list.length;

/*        for (File f : list) {
            String name = f.getName();
            if (!name.endsWith(".jpg"))
                numberOfPlayers++;
        }*/
        //Log.e("Files",String.valueOf(numberOfPlayers));
        return numberOfPlayers;
    }

    public boolean getPlayers() {
        FileInputStream inStream = null;
        playerList = new ArrayList<Player>();
        for (int n=1 ; n<=(getNumberOfPlayers()); n++) {
            try{

                String folder_main = this.getExternalFilesDir(null)+"/"+String.valueOf(n);
                File fileTxt = new File(folder_main, "/"+String.valueOf(n)+".ply");
                inStream = new FileInputStream(fileTxt);
                ObjectInputStream objectInStream = new ObjectInputStream(inStream);
                Player simpleClass = (Player) objectInStream.readObject();
                objectInStream.close();
                playerList.add(simpleClass);

            }catch(IOException e){
                Log.e(TAG, "Could not write object to file");
            }catch(ClassNotFoundException e){
                Log.e(TAG, "Could not find class");
            }
        }

        return true;

    }
    public Integer[] getPhotoIDs(){
        int num = getNumberOfPlayers();
        Log.e("imageidnum", String.valueOf(num));
        Integer[] imageId= new Integer[num];
        for (int n=1 ; n<=num; n++) {
            Log.e("imageidnum", "3");
            imageId[n-1] = n;
            Log.e("imageid", String.valueOf(imageId[n-1]));
        }
        return imageId;
    }

}

//TODO long click on list item to delete, figure some way to deal with missing code numbers then
//TODO swipe down to refresh PlayerSelect List
//TODO Prepare list for when there is no entries
//TODO Clean up code for dramatic performance increase.Consider Asynch upload
//TODO find a way to compress picture when saving it, so that upload will be much quicker
//TODO Fix search with individual photos

