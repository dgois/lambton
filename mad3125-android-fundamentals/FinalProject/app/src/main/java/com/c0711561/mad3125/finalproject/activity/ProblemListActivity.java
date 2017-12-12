package com.c0711561.mad3125.finalproject.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.c0711561.mad3125.finalproject.R;
import com.c0711561.mad3125.finalproject.adapter.ReportedProblemAdapter;
import com.c0711561.mad3125.finalproject.helper.RecyclerItemTouchHelper;
import com.c0711561.mad3125.finalproject.listener.OnItemClickListener;
import com.c0711561.mad3125.finalproject.listener.RecyclerTouchListener;
import com.c0711561.mad3125.finalproject.model.Problem;
import com.c0711561.mad3125.finalproject.repository.ProblemRepository;

import java.util.ArrayList;
import java.util.List;

public class ProblemListActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, SearchView.OnQueryTextListener {

    private List<Problem> problems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReportedProblemAdapter reportedProblemAdapter;
    private String loggedUserEmail;
    private ProblemRepository problemRepository;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        configureActionBar();

        loggedUserEmail = getSharedPreferences(getPackageName(), MODE_PRIVATE).getString("loggedUserEmail", "empty");

        problemRepository = new ProblemRepository(getApplication());
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.problemListCoordinatorLayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportNewProblemIntent = new Intent(ProblemListActivity.this, ReportNewProblemActivity.class);
                startActivityForResult(reportNewProblemIntent, ReportNewProblemActivity.REPORTED_NEW_PROBLEM);
            }
        });

        createProblemList();

        updateProblemList();
    }

    private void configureActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Problem List");
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

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    private void updateProblemList() {
        problems = problemRepository.getAll();
        reportedProblemAdapter = new ReportedProblemAdapter(problems);
        recyclerView.setAdapter(reportedProblemAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ReportedProblemAdapter.MyViewHolder) {
            final int deletedIndex = viewHolder.getAdapterPosition();

            String title = problems.get(deletedIndex).getTitle();
            final Problem deletedProblem = problems.get(deletedIndex);

            reportedProblemAdapter.removeProblem(deletedIndex);
            problemRepository.delete(deletedProblem);

            Snackbar snackbar = Snackbar.make(coordinatorLayout, title + " was removed!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    reportedProblemAdapter.restoreProblem(deletedProblem, deletedIndex);
                    problemRepository.insert(deletedProblem);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_problem_context, menu);

        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String title) {
        reportedProblemAdapter.filter(title);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String title) {
        reportedProblemAdapter.filter(title);
        return true;
    }
}