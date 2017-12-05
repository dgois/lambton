package com.example.macstudent.cricketplayerrank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.macstudent.cricketplayerrank.R;
import com.example.macstudent.cricketplayerrank.model.Player;

import java.util.List;

/**
 * Created by macstudent on 2017-12-01.
 */

public class PlayerAdapter extends ArrayAdapter<Player> {

    public PlayerAdapter(@NonNull Context context, @NonNull List<Player> players) {
        super(context, 0, players);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Player player = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_item, parent, false);
        }

        TextView txtId = (TextView) convertView.findViewById(R.id.playerId);
        TextView txtName = (TextView) convertView.findViewById(R.id.playerName);
        TextView txtTotalPoints = (TextView) convertView.findViewById(R.id.totalPoints);

        txtId.setText(String.valueOf(player.getId()));
        txtName.setText(player.getName());
        txtTotalPoints.setText(String.valueOf(player.getTotalPoints()));

        return convertView;
    }
}