package com.c0711561.mad3125.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.adapter.ReportedProblemAdapter;
import com.c0711561.mad3125.finalproject.helper.RecyclerItemTouchHelper;
import com.c0711561.mad3125.finalproject.listener.OnItemClickListener;
import com.c0711561.mad3125.finalproject.listener.RecyclerTouchListener;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.model.User;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;
import com.c0711561.mad3125.finalproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SolverProblemListActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerViewProblems)
    RecyclerView recyclerView;

    private ReportedProblemAdapter reportedProblemAdapter;
    private ProblemRepository problemRepository;
    private List<Problem> problems = new ArrayList<>();
    private String loggedUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver_problem_list);
        getSupportActionBar().setTitle("Solver Problem List");
        ButterKnife.inject(this);
        problemRepository = new ProblemRepository(getApplication());
        loggedUserEmail = getSharedPreferences(getPackageName(), MODE_PRIVATE).getString("loggedUserEmail", "empty");

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
                Intent viewProblemIntent = new Intent(SolverProblemListActivity.this, ViewProblemActivity.class);
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
}
