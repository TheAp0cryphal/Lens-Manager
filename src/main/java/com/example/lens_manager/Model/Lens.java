package com.example.lens_manager.Model;

public class Lens {
    private String make;
    private double maximum_aperture;
    private double focal_length; /** in mm */

    public Lens(String make, double focal_length, double maximum_aperture) {
        this.make = make;
        this.focal_length = focal_length;
        this.maximum_aperture = maximum_aperture;
    }



    @Override
    public String toString() {
        return
                make +
                        " F " + maximum_aperture +" "+ focal_length+" mm";
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getMaximum_aperture() {
        return maximum_aperture;
    }

    public void setMaximum_aperture(double maximum_aperture) {
        this.maximum_aperture = maximum_aperture;
    }

    public double getFocal_length() {
        return focal_length;
    }

    public Lens getLens(){
        Lens l=new Lens(this.make,this.maximum_aperture,this.focal_length);
        return l;
    }

    public void setFocal_length(int focal_length) {
        this.focal_length = focal_length;
    }
}