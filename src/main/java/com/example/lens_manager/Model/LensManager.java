package com.example.lens_manager.Model;

import androidx.annotation.NonNull;

import com.example.lens_manager.Model.Lens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager implements Iterable<Lens>{
    private List<Lens> list=new ArrayList<>();
    int index;
    private static LensManager instance;

    public LensManager() {
        list.add(new com.example.lens_manager.Model.Lens("Canon", 50, 1.8));
        list.add(new com.example.lens_manager.Model.Lens("Tamron", 90, 2.8));
        list.add(new com.example.lens_manager.Model.Lens("Sigma", 200, 2.8));
        list.add(new com.example.lens_manager.Model.Lens("Nikon", 200, 4));
    }

    public LensManager(List<Lens> list) {
        this.list = list;
        index = 0;
    }

    public static LensManager getInstance() {
        if(instance==null){
            instance=new LensManager();
        }
        return instance;
    }


    public List<Lens> getList() {
        return list;
    }
    public String getYourLens(int index){
        return get(index).toString();
    }

    public void setList(List<Lens> list) {
        this.list = list;
    }

    public void add(Lens l) {
        list.add(l);
    }

    public void add(String make, double focal_length, double maximum_aperture) {
        Lens l = new Lens(make, focal_length, maximum_aperture);
        list.add(l);
    }
    public int size() {
        return list.size();
    }

    public void remove(Lens l) {
        list.remove(l);
    }

    public Lens get(int index) {
        return (Lens) list.get(index);
    }

    @NonNull
    @Override
    public Iterator<Lens> iterator() {
        return list.iterator();
    }
}


