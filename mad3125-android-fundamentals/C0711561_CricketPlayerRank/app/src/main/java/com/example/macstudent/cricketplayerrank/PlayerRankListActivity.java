package com.example.macstudent.cricketplayerrank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.macstudent.cricketplayerrank.adapter.PlayerAdapter;
import com.example.macstudent.cricketplayerrank.dao.PlayerDAO;
import com.example.macstudent.cricketplayerrank.model.Player;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PlayerRankListActivity extends AppCompatActivity {

    @InjectView(R.id.players)
    ListView players;
    private PlayerDAO playerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_rank_list);
        ButterKnife.inject(this);
        playerDAO = new PlayerDAO(this);

        final List<Player> playerList = playerDAO.getAllPlayers();
        players.setAdapter(new PlayerAdapter(this, playerList));

        players.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent intent = new Intent(PlayerRankListActivity.this, PlayerPortifolioViewActivity.class);
                intent.putExtra("selectedPlayerId", playerList.get(index).getId());
                startActivity(intent);
            }
        });
    }
}
