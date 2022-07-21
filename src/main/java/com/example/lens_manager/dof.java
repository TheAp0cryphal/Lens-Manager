package com.example.lens_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lens_manager.Model.DepthOfFieldCalculator;
import com.example.lens_manager.Model.LensManager;

import java.text.DecimalFormat;

public class dof extends AppCompatActivity {

    private LensManager manager;
    private double fLength;
    private double maxAperture;
    private double coc = 0.029;
    private DepthOfFieldCalculator c;
    private double dist;
    private double aperture;
    Toolbar toolbar;
    Button b;
    EditText text_coc, text_dist, text_aper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dof);
        Toolbar toolbar = findViewById(R.id.toolbar);
        text_coc = findViewById(R.id.text_coc);
        text_dist = findViewById(R.id.text_dist);
        text_aper = findViewById(R.id.text_aper);
        b = findViewById(R.id.calc_but);


        manager = LensManager.getInstance();
        int Lens = getIntent().getIntExtra("Selected Lens", 0);
        EditText text_coc = findViewById(R.id.text_coc);
        text_coc.setText("0.029");
        maxAperture = manager.getList().get(Lens).getMaximum_aperture();
        fLength = manager.getList().get(Lens).getFocal_length();

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validateForEmptyInput(text_coc.getText().toString()))
                coc = Double.parseDouble(text_coc.getText().toString());
                if(validateForEmptyInput(text_dist.getText().toString()))
                dist = Double.parseDouble(text_dist.getText().toString());

                boolean flag = true;
                if(validateForEmptyInput(text_aper.getText().toString()))
                aperture = Double.parseDouble(text_aper.getText().toString());
                {

                    String error1 = "";
                    String error2 = "";
                    String error3 = "";
                    if (maxAperture > aperture) {
                        error1 = " Invalid Aperture";
                        flag = false;
                    }
                    if (dist < 0) {
                        error2 = " Distance cannot be negative";
                        flag = false;
                    }
                    if (coc < 0) {
                        error3 = " COC cannot be negative";
                        flag = false;
                    }
                    Toast.makeText(dof.this, error1 + error2 + error3, Toast.LENGTH_SHORT).show();
                }
                if (flag) {
                    c = new DepthOfFieldCalculator(manager.get(Lens), dist, aperture, coc);
                    double HFD = c.HyperFocal_distance();
                    double NFD = c.Near_Focal_distance();
                    double FFD = c.Far_focal_Point();
                    double DOF = c.Depth_of_field();
                    TextView _NFD = findViewById(R.id.NFD);
                    _NFD.setText(formatM(NFD));
                    TextView _HFD = findViewById(R.id.HFD);
                    _HFD.setText(formatM(HFD));
                    TextView _FFD = findViewById(R.id.FFD);
                    _FFD.setText(formatM(FFD));
                    TextView _DOF = findViewById(R.id.DOF);
                    _DOF.setText(formatM(DOF));
                } else {
                    TextView _NFD = findViewById(R.id.NFD);
                    _NFD.setText("NaN");
                    TextView _HFD = findViewById(R.id.HFD);
                    _HFD.setText("NaN");
                    ;
                    TextView _FFD = findViewById(R.id.FFD);
                    _FFD.setText("NaN");
                    ;
                    TextView _DOF = findViewById(R.id.DOF);
                    _DOF.setText("NaN");
                    ;
                }
            }

        });


        Button bck = (Button) findViewById(R.id.bck_but);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                setResult(Activity.RESULT_CANCELED, intent);

                finish();
            }
        });
    }


    private boolean validateForEmptyInput(String input) {
        return input != null && !input.isEmpty();
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, dof.class);
    }
}