package com.fredfama.weparty;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by root on 29/05/16.
 */
class SongAdapter extends ArrayAdapter<Song> {

    private ArrayList<Song> songList;
    Context context;
    int layoutResourceId;

    public SongAdapter(Context context, int textViewResourceId, ArrayList<Song> songList) {
        super(context, textViewResourceId, songList);
        this.context = context;
        this.layoutResourceId = textViewResourceId;
        this.songList = new ArrayList<Song>();
        this.songList.addAll(songList);
    }

    private class ViewHolder {
        TextView uuid;
        CheckBox name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        View row = convertView;
        Log.v("ConvertView", String.valueOf(position));

        if (row == null) {
            //LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = vi.inflate(R.layout.song_info, null);

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.song_info, parent, false);

            holder = new ViewHolder();
            holder.uuid = (TextView) row.findViewById(R.id.uuid);
            holder.name = (CheckBox) row.findViewById(R.id.checkBox1);
            row.setTag(holder);

            holder.name.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Song song = (Song) cb.getTag();
                    Toast.makeText(context,
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    song.setSelected(cb.isChecked());
                }
            });
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Song song = songList.get(position);
        holder.uuid.setText(" (" +  song.getUuid() + ")");
        holder.name.setText(song.getName());
        holder.name.setChecked(song.isSelected());
        holder.name.setTag(song);

        return row;

    }

}
