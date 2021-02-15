package com.example.air_quality_app.model.station;

public class City{
    public int id;
    public String name;
    public Commune commune;

    public City() {
    }

    public City(int id, String name, Commune commune) {
        this.id = id;
        this.name = name;
        this.commune = commune;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commune=" + commune +
                '}';
    }
}
