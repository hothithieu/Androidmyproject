package com.example.android_dialy_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowDialyList extends AppCompatActivity implements DialyAdapter.OnItemClicked {
    RecyclerView recyclerviewUser;
    AppDatabase db;
    DialyAdapter dialyAdapter;

    public static List<Dialy> Dialies = new ArrayList<Dialy>();
    public static List<Dialy> listDialies = new ArrayList<Dialy>();
    int index = -1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dialy_list);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        recyclerviewUser = findViewById(R.id.recyclerview_id);
        recyclerviewUser.setLayoutManager(new LinearLayoutManager((this)));

        dialyAdapter = new DialyAdapter();
        dialyAdapter.setOnClick(new DialyAdapter.OnItemClicked() {
            @Override
            public void onClickItemDelete(int position) {
                getIndex(position);
                deleteDialy(position);
            }
        });
        final Button btn_ShowDialiesList = (Button) findViewById(R.id.btnCombakHome);
        btn_ShowDialiesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowDialyList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final TextView showDitail = (TextView) findViewById(R.id.tvTitle);
        showDitail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index >= 0){
                    Intent intent = new Intent(ShowDialyList.this, dialyDetail.class);
                    String Title = listDialies.get(index).Title;
                    String Content = listDialies.get(index).Content;
                    intent.putExtra("Title", Title);
                    intent.putExtra("Content", Content);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getandDisplayTask();
    }

    public int getIndex(int position){
        return index = position;
    }
    public void getandDisplayTask() {
        new AsyncTask<Void, Void, List<Dialy>>() {
            @Override
            protected List<Dialy> doInBackground(Void... voids) {
                listDialies = db.dialyDao().getAll();
                return db.dialyDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Dialy> dialies) {
                dialyAdapter.Dialies = dialies;
                recyclerviewUser.setAdapter(dialyAdapter);
                super.onPostExecute(dialies);
            }
        }.execute();
    }

    @Override
    public void onClickItemDelete(final int position) {
        Log.i("TAG", "clicked at " + position);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.dialyDao().delete(Dialies.get(position));
                Log.i("TAG", "delete success");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dialyAdapter.Dialies.remove(position);
                dialyAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    void deleteDialy(final int position){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.dialyDao().delete(dialyAdapter.Dialies.get(position));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dialyAdapter.Dialies.remove(position);
                dialyAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}


