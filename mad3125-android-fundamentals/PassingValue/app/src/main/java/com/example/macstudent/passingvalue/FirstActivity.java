package com.example.macstudent.passingvalue;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.widget.Spinner.MODE_DIALOG;

public class FirstActivity extends AppCompatActivity {

    TextView txtTitle;
    Spinner sp;
    Button btnCallApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtTitle = (TextView) findViewById(R.id.txtGreeting);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            String name = b.getString("name", "No Name");

            int id = b.getInt("id");
            txtTitle.setText(id + " - " + name);

        } else {
            txtTitle.setText("No Data");
        }

        sp = (Spinner) findViewById(R.id.spinner2Id);

        String[] options = new String[]{"Easy", "Medium", "Hard"};
        final List<String> difficult = new ArrayList<>(Arrays.asList(options));
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, difficult);
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        sp.setAdapter(dataAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Toast.makeText(FirstActivity.this,
                        "OnItemSelectedListener : " + sp.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_SHORT).show();
                difficult.remove(pos);
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCallApi = (Button) findViewById(R.id.btnCallApi);
        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstActivity.this, "Click", Toast.LENGTH_SHORT);
                new RetrieveApiCall().execute();
            }
        });

        new RetrieveApiCall().execute();

    }


    private class RetrieveApiCall extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(FirstActivity.this, "Start", Toast.LENGTH_SHORT);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://10.0.2.2:8080/greeting/");
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s!=null)
            {
                Log.d("OUTPUT",s);
            }
            else
                Log.d("OUTPUT","NULL");
        }
    }
}
