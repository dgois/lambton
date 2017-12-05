package com.example.macstudent.cricketplayerrank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.macstudent.cricketplayerrank.dao.PlayerDAO;
import com.example.macstudent.cricketplayerrank.model.Player;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PlayerPortifolioViewActivity extends AppCompatActivity {

    @InjectView(R.id.txtName)
    TextView txtName;
    @InjectView(R.id.txtTotalPoints)
    TextView txtTotalPoints;
    private PlayerDAO playerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_portifolio_view);
        ButterKnife.inject(this);
        playerDAO = new PlayerDAO(this);

        int playerId = getIntent().getExtras().getInt("selectedPlayerId");

        Player player = playerDAO.get(playerId);
        txtName.setText(player.getName());
        txtTotalPoints.setText(String.valueOf(player.getTotalPoints()));

    }
}
