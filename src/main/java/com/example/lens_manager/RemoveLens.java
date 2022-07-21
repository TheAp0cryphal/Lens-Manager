package com.example.lens_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lens_manager.Model.LensManager;

public class RemoveLens extends AppCompatActivity {
    private LensManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_lens);
        removeButton();
    }

    private void removeButton() {
        Button delete=findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText pos=findViewById(R.id.pos);
                int PosInput=Integer.parseInt(pos.getText().toString());
                manager= LensManager.getInstance();

                Intent intent=new Intent();
                manager.remove(manager.get(PosInput));
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
    public static Intent makeIntent(Context context) {
        return new Intent(context, RemoveLens.class);
    }


}