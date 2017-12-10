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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.adapter.ReportedProblemAdapter;
import com.c0711561.mad3125.finalproject.listener.OnItemClickListener;
import com.c0711561.mad3125.finalproject.listener.RecyclerTouchListener;
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

        createProblemList();

        updateProblemList();
    }

    private void createProblemList() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProblems);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent viewProblemIntent = new Intent(ProblemListActivity.this, ViewProblemActivity.class);
                viewProblemIntent.putExtra("problemId", problems.get(position).getId());
                startActivity(viewProblemIntent);
            }
        }));
    }

    private void updateProblemList() {
        problems = problemRepository.getAll();
        reportedProblemAdapter = new ReportedProblemAdapter(problems);
        recyclerView.setAdapter(reportedProblemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DENIS", "size : " + problems.size() + " | before onActivityResult");
        if(requestCode == ReportNewProblemActivity.REPORTED_NEW_PROBLEM && resultCode == RESULT_OK) {
            Snackbar.make(findViewById(R.id.problemListCoordinatorLayout), "New Problem Success Reported! ", Snackbar.LENGTH_LONG).show();
        }
        updateProblemList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateProblemList();
    }
}