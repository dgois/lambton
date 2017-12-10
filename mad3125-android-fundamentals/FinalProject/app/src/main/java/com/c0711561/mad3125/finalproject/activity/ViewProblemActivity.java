package com.c0711561.mad3125.finalproject.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;

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
    @InjectView(R.id.viewSolverComments)
    TextView viewSolverComments;

    private ProblemRepository problemRepository;
    private Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_problem);
        ButterKnife.inject(this);
        problemRepository = new ProblemRepository(getApplication());

        int problemId = getIntent().getIntExtra("problemId", 0);
        problem = problemRepository.findById(problemId);

        imageView.setImageBitmap(BitmapFactory.decodeByteArray(problem.getImage(), 0, problem.getImage().length));
        viewTitle.setText(problem.getTitle());
        viewCategory.setText(problem.getCategory());
        viewDescription.setText(problem.getDescription());
        viewLocation.setText(problem.getLocation());
        viewSolverComments.setText(problem.getSolverComments());
    }

    @OnClick(R.id.viewLocationOnMaps)
    public void onViewClicked() {
        Intent mapsIntent = new Intent(this, ProblemsMapsActivity.class);
        mapsIntent.putExtra("problemId", problem.getId());
        startActivity(mapsIntent);
    }
}
