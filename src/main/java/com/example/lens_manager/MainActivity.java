package com.example.lens_manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.lens_manager.R;
import com.example.lens_manager.Model.Lens;
import com.example.lens_manager.Model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        AddLens();
        setSupportActionBar(toolbar);
        populateListView();
        registerClickCallback();
    }

    private void AddLens() {
        fab = findViewById(R.id.add);
        fab.setOnClickListener(view -> {
            Intent i = AddLens.makeIntent(MainActivity.this);
            startActivityForResult(i,101);

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            populateListView();
        }
    }

    private void populateListView() {
        LensManager manager = LensManager.getInstance();
        ListView list = findViewById(R.id.LensList);
        ArrayAdapter<Lens> lensArrayAdapter = new ArrayAdapter<>(this, R.layout.layout, manager.getList());
        list.setAdapter(lensArrayAdapter);

    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.LensList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView100 = (TextView) viewClicked;
                Intent intent = dof.makeIntent(MainActivity.this);
                intent.putExtra("Selected Lens", position);
                startActivity(intent);
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

}