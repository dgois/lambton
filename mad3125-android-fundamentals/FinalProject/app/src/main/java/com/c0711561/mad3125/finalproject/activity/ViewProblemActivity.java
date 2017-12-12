package com.c0711561.mad3125.finalproject.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.model.User;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;
import com.c0711561.mad3125.finalproject.repository.UserRepository;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ViewProblemActivity extends AppCompatActivity {

    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.viewTitle)
    TextView viewTitle;
    @InjectView(R.id.viewCategory)
    TextView viewCategory;
    @InjectView(R.id.viewDescription)
    TextView viewDescription;
    @InjectView(R.id.viewLocation)
    TextView viewLocation;
    @InjectView(R.id.viewLocationOnMaps)
    TextView viewLocationOnMaps;
    @InjectView(R.id.viewStatus)
    TextView viewStatus;
    @InjectView(R.id.spiStatus)
    Spinner spiStatus;
    @InjectView(R.id.viewSolverComments)
    TextView viewSolverComments;
    @InjectView(R.id.edtSolverComments)
    TextInputEditText edtSolverComments;
    @InjectView(R.id.viewProblemLayourt)
    LinearLayout viewProblemLayourt;

    private ProblemRepository problemRepository;
    private UserRepository userRepository;
    private Problem problem;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_problem);
        ButterKnife.inject(this);
        problemRepository = new ProblemRepository(getApplication());
        userRepository = new UserRepository(getApplication());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String userEmail = getSharedPreferences(getPackageName(), MODE_PRIVATE).getString("loggedUserEmail", "empty");
        user = userRepository.findByEmail(userEmail);

        int problemId = getIntent().getIntExtra("problemId", 0);
        problem = problemRepository.findById(problemId);

        imageView.setImageBitmap(BitmapFactory.decodeByteArray(problem.getImage(), 0, problem.getImage().length));
        viewTitle.setText(problem.getTitle());
        viewCategory.setText(problem.getCategory());
        viewDescription.setText(problem.getDescription());
        viewLocation.setText(problem.getLocation());
        viewSolverComments.setText(problem.getSolverComments());
        edtSolverComments.setText(problem.getSolverComments());

        changeLayoutAccordingUserRole();
    }

    private void changeLayoutAccordingUserRole() {
        if (user.isReporter()) {
            getSupportActionBar().setTitle("View Reported Problem");
            edtSolverComments.setVisibility(View.INVISIBLE);
            viewSolverComments.setVisibility(View.VISIBLE);
            spiStatus.setVisibility(View.INVISIBLE);
            viewStatus.setVisibility(View.VISIBLE);
            viewStatus.setText(problem.getStatus());
        } else {
            getSupportActionBar().setTitle("Resolve Reported Problem");
            edtSolverComments.setVisibility(View.VISIBLE);
            viewSolverComments.setVisibility(View.INVISIBLE);
            spiStatus.setVisibility(View.VISIBLE);
            viewStatus.setVisibility(View.INVISIBLE);

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Problem.statusOptions.values());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiStatus.setAdapter(adapter);
            spiStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    problem.setStatus(Problem.statusOptions.values()[position].name());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spiStatus.setSelection(Problem.statusOptions.valueOf(problem.getStatus()).ordinal());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!user.isReporter()) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.solver_problem_context, menu);
        }
        return true;
    }

    @OnClick(R.id.viewLocationOnMaps)
    public void onViewClicked() {
        Intent mapsIntent = new Intent(this, ProblemsMapsActivity.class);
        mapsIntent.putExtra("problemId", problem.getId());
        startActivity(mapsIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.getIntent().putExtra("problemId", problem.getId());
                this.getIntent().putExtra("loggedUserEmail", user.getEmail());

                if (!user.isReporter()) {
                    showAlert();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            case R.id.action_save_problem:
                problem.setSolverComments(edtSolverComments.getText().toString());
                int update = problemRepository.update(problem);
                if (update > 0 ) {
                    notifyAfterUpdateProblem();
                    finish();
                }
                return true;
            default: return true;
        }
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You will loose edited information!")
                .setTitle("Cancel Problem update?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ViewProblemActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
        builder.create().show();
    }

    private void notifyAfterUpdateProblem() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Problem was updated")
                .setContentText("Problem title: " + problem.getTitle() + " was change to : " + problem.getStatus());

        Intent resultIntent = new Intent(this, LoginActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(001, builder.build());
    }
}
