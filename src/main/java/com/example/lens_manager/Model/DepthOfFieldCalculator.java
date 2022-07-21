package com.example.lens_manager.Model;

import com.example.lens_manager.Model.Lens;

public class DepthOfFieldCalculator {
    private com.example.lens_manager.Model.Lens l;
    private double distance;
    private double aperture;
    private double Circle_of_confusion;


    public DepthOfFieldCalculator(Lens l2){

        this.l=l2;
    }

    public DepthOfFieldCalculator(com.example.lens_manager.Model.Lens l, double distance, double aperture, double circle_of_confusion) {
        this.l = l;
        this.distance = distance*1000;
        this.aperture = aperture;
        this.Circle_of_confusion=circle_of_confusion;
    }

    public com.example.lens_manager.Model.Lens getL() {
        return l;
    }

    public void setL(com.example.lens_manager.Model.Lens l) {
        this.l = l;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance; /** in mm */
    }

    public double getAperture() {
        return aperture;
    }

    public void setAperture(double aperture) {
        this.aperture = aperture;
    }

    public double getCircle_of_confusion() {
        return Circle_of_confusion;
    }

    public void setCircle_of_confusion(double circle_of_confusion) {
        Circle_of_confusion = circle_of_confusion;
    }

    public double HyperFocal_distance(){ /** in m */
        double num=Math.pow(l.getFocal_length(),2)/1000;
        double  den=aperture*Circle_of_confusion;
        return num/den;
    }

    public double Near_Focal_distance() {
        double num=this.HyperFocal_distance()*distance*1000;
        double den=(this.HyperFocal_distance()*1000) + (distance - l.getFocal_length());
        double finalA = num/den;
        return finalA/1000;
    }
    public double Far_focal_Point(){
        if(distance>HyperFocal_distance()*1000)
            return Double.POSITIVE_INFINITY;
        else
            return (this.HyperFocal_distance()*1000*distance)/(((this.HyperFocal_distance()*1000 )-(getDistance()-l.getFocal_length()))*1000);
    }
    public double Depth_of_field(){
        return Far_focal_Point()-Near_Focal_distance();
    }

}