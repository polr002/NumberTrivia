package com.example.roy.numbertrivia;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService service;
    private List<NumberQuote> numberQuotes;
    private NumberQuoteAdapter mAdapter;
    private RecyclerView recyclerView;
    private int newNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = ApiService.retrofit.create(ApiService.class);
        recyclerView = findViewById(R.id.recyclerView);
        numberQuotes = new ArrayList<>();



        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NumberQuoteAdapter(this, numberQuotes);

        recyclerView.setAdapter(mAdapter);


        //Toolbar stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //floating button stuff
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                newNumber = rand.nextInt(300);

                requestData(newNumber);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestData(int number) {

        Call<NumberQuote> call = service.getNumber(number);
        call.enqueue(new Callback<NumberQuote>() {
            @Override
            public void onResponse(Call<NumberQuote> call, Response<NumberQuote> response) {
                Context context = getApplicationContext();
                String toastText = "New number added!";

                numberQuotes.add(response.body());

                updateView();
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<NumberQuote> call, Throwable t) {
                Log.d("error",t.toString());
            }
        });
    }
    public void updateView() {
        mAdapter.notifyDataSetChanged();

    }
}