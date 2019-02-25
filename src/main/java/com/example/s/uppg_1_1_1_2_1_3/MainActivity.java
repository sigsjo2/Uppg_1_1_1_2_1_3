package com.example.s.uppg_1_1_1_2_1_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        MyRecyclerViewAdapter.ItemClickListener {

    String TAG = "MainActivity";
    public static final int TEXT_REQUEST = 1;
    MyRecyclerViewAdapter adapter;
    List<String> movieList = new ArrayList<>();
    RecyclerView recyclerView;
    int posDelete = -1;

    /** initialize variables etc at startup */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // prepare the list
        addMovies();

        // set up the RecyclerView
        recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyRecyclerViewAdapter(this, movieList);
        adapter.setClickListener(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // set the adapter
        recyclerView.setAdapter(adapter);

    }



    /** triggered when the user click on an item in the list */
    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(this, adapter.getItem(position) + " is marked for deletion",
                Toast.LENGTH_LONG).show();

        posDelete = position;
    }

    /** Called when the user taps the Del Movie button */
    public void delMovie(View view) {

        if ((posDelete >= 0) && (posDelete < movieList.size())) {

            Toast.makeText(this, adapter.getItem(posDelete) + " is deleted",
                Toast.LENGTH_LONG).show();

            movieList.remove(posDelete);
            adapter.updateMovieList(movieList);

            adapter.notifyItemRemoved(posDelete);
            adapter.notifyItemRangeChanged(posDelete, movieList.size());

            posDelete = -1;

            Log.d(TAG, " adapter " + adapter.getItemCount() + " list " + movieList.size());
        }
    }

    /** Called when the user taps the Add Movie button */
    public void addMovie(View view) {

        posDelete = -1;
        openAddMovieActivity();
    }
    
    /** data to populate the RecyclerView with */
    public void addMovies() {
    
        movieList.add("Fargo");
        movieList.add("Heat");
        movieList.add("French Connection");
        movieList.add("The Meg");
        movieList.add("Deliverance");

        movieList.add("Gone Girl");
        movieList.add("Godzilla");
        movieList.add("Birdman");
        movieList.add("Cast Away");
        movieList.add("Snatch");

        movieList.add("Nightcrawler");
        movieList.add("Iron Man 3");
        movieList.add("12 Years a slave");
        movieList.add("Skyfall");
        movieList.add("Looper");
        
    }

    /** call the other activity to ask for movie name */
    public void openAddMovieActivity() {

        Intent intent = new Intent(this, Main2Activity.class);

        startActivityForResult(intent, TEXT_REQUEST);
    }

    /** Activated when other activity is finished */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        String movieName;
        int size;

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {

                movieName = data.getStringExtra(Main2Activity.REPLY);
                movieName.trim();

                if (movieName.length() > 0) {

                    size = movieList.size();
                    movieList.add(movieName);
                    adapter.updateMovieList(movieList);

                    adapter.notifyItemInserted(size);
                    adapter.notifyItemRangeChanged(size, movieList.size());

                    Toast.makeText(this, movieName + " added",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
