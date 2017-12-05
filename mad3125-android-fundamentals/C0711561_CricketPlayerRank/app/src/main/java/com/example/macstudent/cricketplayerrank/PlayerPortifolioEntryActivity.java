package com.example.macstudent.cricketplayerrank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.macstudent.cricketplayerrank.dao.PlayerDAO;
import com.example.macstudent.cricketplayerrank.model.Player;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PlayerPortifolioEntryActivity extends AppCompatActivity {

    private final static String[] GENDER = new String[]{"Select Gender", "Male", "Female"};
    private final static String[] CATEGORIES = new String[]{"Select Category", "Batsman", "Bowler", "Wicket keeper"};
    private final static String[] COUNTRIES = new String[]{"Select Country", "India", "Brazil", "Canada", "England"};

    private PlayerDAO playerDAO;

    @InjectView(R.id.edtName)
    EditText edtName;
    @InjectView(R.id.edtGender)
    Spinner edtGender;
    @InjectView(R.id.edtBirthdate)
    EditText edtBirthdate;
    @InjectView(R.id.category)
    Spinner category;
    @InjectView(R.id.country)
    Spinner country;
    @InjectView(R.id.nOfTestMatch)
    EditText nOfTestMatch;
    @InjectView(R.id.nOfOneDayMatch)
    EditText nOfOneDayMatch;
    @InjectView(R.id.nOfCatch)
    EditText nOfCatch;
    @InjectView(R.id.nOfRuns)
    EditText nOfRuns;
    @InjectView(R.id.nOfStumping)
    EditText nOfStumping;
    @InjectView(R.id.savePlayerStatistics)
    Button btnSavePlayerStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_portifolio_entry);
        ButterKnife.inject(this);

        playerDAO = new PlayerDAO(this);

        laodSpinner(GENDER, edtGender);
        laodSpinner(CATEGORIES, category);
        laodSpinner(COUNTRIES, country);

        btnSavePlayerStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String gender = GENDER[edtGender.getSelectedItemPosition()];
                String birthdate = edtBirthdate.getText().toString();
                String selectedcategory = CATEGORIES[category.getSelectedItemPosition()];
                String selectedCountry = COUNTRIES[country.getSelectedItemPosition()];
                String numberOfTestMatch = nOfTestMatch.getText().toString();
                String numberOfOneDayMatch = nOfOneDayMatch.getText().toString();
                String numberOfCatch = nOfCatch.getText().toString();
                String numberOfRuns = nOfRuns.getText().toString();
                String numberOfStumping = nOfStumping.getText().toString();

                Player player = new Player(name, gender, new Date(), selectedcategory, selectedCountry);
                player.setNumberOfTestMatch(Integer.parseInt(numberOfTestMatch));
                player.setNumberOf1DayMatch(Integer.parseInt(numberOfOneDayMatch));
                player.setNumberOfCatch(Integer.parseInt(numberOfCatch));
                player.setNumberOfRuns(Integer.parseInt(numberOfRuns));
                player.setNumberOfStuping(Integer.parseInt(numberOfTestMatch));
                player.calculateTotalPoints();

                Log.i("DENIS", player.toString());
                playerDAO.add(player);

                new AlertDialog.Builder(PlayerPortifolioEntryActivity.this)
                        .setTitle("New Player")
                        .setMessage("Success saved!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                edtName.setText("");
                                edtGender.setSelection(0);
                                category.setSelection(0);
                                country.setSelection(0);
                                edtBirthdate.setText("");
                                nOfTestMatch.setText("");
                                nOfOneDayMatch.setText("");
                                nOfCatch.setText("");
                                nOfRuns.setText("");
                                nOfStumping.setText("");
                            }
                        })
                        .setIcon(android.R.drawable.btn_star)
                        .show();
            }
        });

    }

    private void laodSpinner(String[] values, Spinner spinner) {
        List<String> spinnerArray = Arrays.asList(values);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_players_rank, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.listPlayersRank == item.getItemId()) {
            startActivity(new Intent(this, PlayerRankListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isValidInput() {
        if (!isValidSpinner(edtGender)) {
            return false;
        }
        return true;
    }

    private boolean isValidSpinner(Spinner spinner) {
        return spinner.getSelectedItemPosition() != 0 && spinner.getSelectedItemPosition() != -1;
    }
}
