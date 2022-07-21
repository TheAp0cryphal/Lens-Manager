package com.example.lens_manager.textUI;

import com.example.lens_manager.Model.LensManager;
import com.example.lens_manager.Model.*;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Sample (incomplete) text UI to interact with the user.
 * You may change any part of this!
 */
public class CameraTextUi {
    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard


    public CameraTextUi(LensManager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.add(new com.example.lens_manager.Model.Lens("Canon", 50, 1.8));
        manager.add(new com.example.lens_manager.Model.Lens("Tamron", 90, 2.8));
        manager.add(new com.example.lens_manager.Model.Lens("Sigma", 200, 2.8));
        manager.add(new com.example.lens_manager.Model.Lens("Nikon", 200, 4));
    }

    public void show() {
        // BEGIN SAMPLE USING SCREEN AND KEYBOARD:
        // (remove this: it's just to show you how to access the screen and keyboard!)
        System.out.println("WELCOME TO LENS MANAGER");
        System.out.println("_____________________________LIST____________________________");
        System.out.println("");

        for (int i = 0; i < manager.size(); i++) {

            System.out.println(i + ")" + " " + manager.get(i));
        }

        System.out.println("\n(-1 to Exit)");
        System.out.println("\nPlease Enter the Index of the Lens: ");

        int input = in.nextInt();

        while (input >= manager.size() || input < -1) {
            System.out.println("Invalid Input!!!, Please Enter a valid Index of the desired Product: ");
            input = in.nextInt();
        }
        if (input == -1) {
            System.out.println("Thanks For Visiting Us, Please Visit us Again!!!");
            return;
        }

        com.example.lens_manager.Model.Lens l;
        l = manager.get(input);
        System.out.println("Aperture [the F number]: ");

        double aperture = in.nextDouble();

        while (aperture < l.getMaximum_aperture()) {
            System.out.println("Please Enter a valid Aperture");
            aperture = in.nextDouble();
        }
        System.out.println("Distance to subject [m]: ");
        double distance = in.nextDouble();

        com.example.lens_manager.Model.DepthOfFieldCalculator dof;
        dof = new com.example.lens_manager.Model.DepthOfFieldCalculator(l, distance, aperture, COC);

        System.out.println("\tIn Focus: " + formatM(dof.Near_Focal_distance()) + "m to " + formatM(dof.Far_focal_Point()) + "m\t[DoF]= " + formatM(dof.Depth_of_field()) + "m");
        System.out.println("\tHyperfocal Point: " + formatM(dof.HyperFocal_distance()) + "m");
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}
