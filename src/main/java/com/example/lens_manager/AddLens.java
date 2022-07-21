package com.example.lens_manager;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.lens_manager.Model.Lens;
import com.example.lens_manager.Model.LensManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddLens extends AppCompatActivity {

    // private static Context c;
    private String Make;
    private double focal_length;
    private double Aperture;
    private LensManager manager;
    Button save;
    EditText InputMake, InputFocalLength, InputAperture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        Toolbar toolbar = findViewById(R.id.toolbar);
        InputMake = findViewById(R.id.Make);
        InputFocalLength = findViewById(R.id.Focal_Length);
        InputAperture = findViewById(R.id.Aperture);
        save = findViewById(R.id.save);


        setSupportActionBar(toolbar);
        saveButton();
        cancelButton();
    }

    private void saveButton() {

        save.setOnClickListener(v -> {

            if (validateForEmptyInput(InputMake.getText().toString()))
                Make = InputMake.getText().toString();
            if (validateForEmptyInput(InputFocalLength.getText().toString()))
                focal_length = Double.parseDouble(InputFocalLength.getText().toString());
            if (validateForEmptyInput(InputAperture.getText().toString()))
                Aperture = Double.parseDouble(InputAperture.getText().toString());


            manager = LensManager.getInstance();
            Intent intent = new Intent();
            manager.add(Make, focal_length, Aperture);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }

    private boolean validateForEmptyInput(String input) {
        return input != null && !input.isEmpty();
    }

    private void cancelButton() {
        Button Cancel = (Button) findViewById(R.id.cancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                setResult(Activity.RESULT_CANCELED, intent);

                finish();
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddLens.class);

    }
}