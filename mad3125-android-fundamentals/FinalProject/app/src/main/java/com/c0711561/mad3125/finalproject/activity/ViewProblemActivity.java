package com.c0711561.mad3125.finalproject.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        String userEmail = getIntent().getStringExtra("loggedUserEmail");
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

        spiStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                problem.setStatus(Problem.statusOptions.values()[position].name());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void changeLayoutAccordingUserRole() {
        if (user.isReporter()) {
            edtSolverComments.setVisibility(View.INVISIBLE);
            viewSolverComments.setVisibility(View.VISIBLE);
            viewStatus.setText(problem.getStatus());
        } else {
            edtSolverComments.setVisibility(View.VISIBLE);
            viewSolverComments.setVisibility(View.INVISIBLE);

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Problem.statusOptions.values());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spiStatus.setAdapter(adapter);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        problem.setSolverComments(edtSolverComments.getText().toString());

        if (item.getItemId() == R.id.action_save_problem) {
            problemRepository.update(problem);
        }
        return true;
    }

    @OnClick(R.id.viewLocationOnMaps)
    public void onViewClicked() {
        Intent mapsIntent = new Intent(this, ProblemsMapsActivity.class);
        mapsIntent.putExtra("problemId", problem.getId());
        startActivity(mapsIntent);
    }
}
