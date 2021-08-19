package com.example.projectuas.model;

public class ModelTeams {
    private String name;
    private String years;
    private String country;
    private String description;
    private String image;

    public ModelTeams(String name, String years, String country, String description, String image){
        this.name = name;
        this.years = years;
        this.country = country;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
