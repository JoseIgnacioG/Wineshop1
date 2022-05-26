package com.example.wine.Wine;


import com.example.wine.Region.Region;
import com.example.wine.Type.Type;
import com.example.wine.Winery.Winery;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wine {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int wineYear;
    private float rating;
    private int num_reviews;
    private int price;
    private int body;
    private int acidity;

    @ManyToOne
    @JoinColumn(name = "region")
    private Region region;
    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "winery")
    private Winery winery;

    public Wine() {
    }

    public Wine(String name, int year, float rating, int num_reviews, int price, int body, int acidity, Region region, Type type, Winery winery) {
        this.name = name;
        this.wineYear = year;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
        this.region = region;
        this.type = type;
        this.winery = winery;
    }

    public Wine(String name, int wineYear, float rating, int num_reviews, int price, int body, int acidity) {
        this.name = name;
        this.wineYear = wineYear;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
        this.region = new Region();
        this.type = new Type();
        this.winery = new Winery();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Wine)) return false;
        Wine wine = (Wine) o;
        return Objects.equals(this.getId(), wine.getId()) && Objects.equals(this.winery, wine.winery) && Objects.equals(this.wineYear, wine.wineYear) && Objects.equals(this.region, wine.region) && Objects.equals(this.price, wine.price) && Objects.equals(this.name, wine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.winery, this.wineYear, this.region, this.price, this.name);
    }

    @Override
    public String toString() {
        return "Wine{" + "id=" + id + ", name='" + name + '\'' + ", year='" + wineYear + '\'' + ", rating=" + rating + ", num_reviews=" + num_reviews + ", price=" + price + ", body=" + body + ", acidity=" + acidity + ", region=" + region + ", type=" + type + ", winery=" + winery + '}';
    }
}

