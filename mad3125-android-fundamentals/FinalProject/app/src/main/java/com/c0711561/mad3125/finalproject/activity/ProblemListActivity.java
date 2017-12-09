package com.c0711561.mad3125.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.adapter.ReportedProblemAdapter;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;

import java.util.ArrayList;
import java.util.List;

public class ProblemListActivity extends AppCompatActivity {

    private List<Problem> problems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReportedProblemAdapter reportedProblemAdapter;
    private String loggedUserEmail;
    private ProblemRepository problemRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loggedUserEmail = getIntent().getStringExtra("loggedUserEmail");
        problemRepository = new ProblemRepository(getApplication());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportNewProblemIntent = new Intent(ProblemListActivity.this, ReportNewProblemActivity.class);
                reportNewProblemIntent.putExtra("loggedUserEmail", loggedUserEmail);
                startActivityForResult(reportNewProblemIntent, ReportNewProblemActivity.REPORTED_NEW_PROBLEM);
            }
        });

        problems = problemRepository.getAll();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProblems);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        reportedProblemAdapter = new ReportedProblemAdapter(problems);
        recyclerView.setAdapter(reportedProblemAdapter);
        reportedProblemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ReportNewProblemActivity.REPORTED_NEW_PROBLEM && resultCode == RESULT_OK) {
            Snackbar.make(findViewById(R.id.problemListCoordinatorLayout), "New Problem Success Reported! ", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        problems = problemRepository.getAll();
        reportedProblemAdapter.notifyDataSetChanged();
    }
}
