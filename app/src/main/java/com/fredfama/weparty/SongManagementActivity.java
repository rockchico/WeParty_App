package com.fredfama.weparty;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SongManagementActivity extends WePartyActivity {


    //final String MEDIA_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    final String MEDIA_PATH = "/storage/sdcard1/";
    private ArrayList<Song> songsList = new ArrayList<Song>();
    private String mp3Pattern = ".mp3";

    ListView listView_songs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_management);

        getPlayList();

        Log.i("SongManagementActivity SONG LIST SIZE: ", String.valueOf(songsList.size()));




        listView_songs = (ListView) findViewById(R.id.listVIew_songs);

        //ArrayList<Song> opcoes = new ArrayList<String>();

        for(int i = 0; i < songsList.size(); i++) {
            //opcoes.add(songsList.get(i).getName());
            //Log.i("SongManagementActivity SONG: ", songsList.get(i).get("songTitle") +" : "+songsList.get(i).get("songPath"));

        }

        //opcoes.add("Navegar na Internet");
        //opcoes.add("Fazer uma ligação");
        //opcoes.add("Sobre");
        //opcoes.add("Sair");

        SongAdapter adaptador = new SongAdapter(SongManagementActivity.this, android.R.layout.simple_list_item_1, songsList);
        listView_songs.setAdapter(adaptador);
        listView_songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_LONG;

                Song s = (Song) parent.getItemAtPosition(position);

                text = "Musica = "+s.getName();
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


            }
        });






    }


    /**
     * Function to read all mp3 files and store the details in
     * ArrayList
     * */
    public ArrayList<Song> getPlayList() {
        //System.out.println(MEDIA_PATH);
        Log.i("SongManagementActivity MEDIA_PATH: ", MEDIA_PATH);
        if (MEDIA_PATH != null) {
            File home = new File(MEDIA_PATH);
            File[] listFiles = home.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    //System.out.println(file.getAbsolutePath());
                    Log.i("SongManagementActivity FILE_PATH: ", file.getAbsolutePath());
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }
                }
            }
        }
        // return songs list array
        return songsList;
    }


    private void scanDirectory(File directory) {
        if (directory != null) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }

                }
            }
        }
    }


    private void addSongToList(File song) {



        //Log.i("SongManagementActivity addSongToList: ", song.getName().toString());

        if (song.getName().toString().endsWith(mp3Pattern)) {



            String uniqueID = UUID.randomUUID().toString();
            String name = song.getName().substring(0, (song.getName().length() - 4));
            String path = song.getPath();

            songsList.add(new Song(uniqueID, name, path, false));



        }
    }



}