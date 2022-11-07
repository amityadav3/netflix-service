package com.reactivespring.Netflixinfoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class MovieInfo {
    @Id
    @NotBlank(message = "MessageInfo.show_id must not be null")
    private String show_id;
    private String type;
    @NotBlank(message = "MessageInfo.title must not be null")
    private String title;
    private String director;
    private String cast;
    @NotBlank(message = "MessageInfo.country must not be null")
    private String country;
    private String date_added;
    @NotBlank(message = "MessageInfo.releaseYear must not be null")
    private String release_year;
    private String rating;
    private String duration;
    private String listed_in;
    private String description;

    public String getShow_id() {
        return show_id;
    }

    public void setShow_id(String show_id) {
        this.show_id = show_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getListed_in() {
        return listed_in;
    }

    public void setListed_in(String listed_in) {
        this.listed_in = listed_in;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static MovieInfo createObject(String[] data) {
        MovieInfo m = new MovieInfo();
        m.setShow_id(data[0]);
        m.setType(data[1]);
        m.setTitle(data[2]);
        m.setDirector(data[3]);
        m.setCast(data[4]);
        m.setCountry(data[5]);
        m.setDate_added(data[6]);
        m.setRelease_year(data[7]);
        m.setRating(data[8]);
        m.setDuration(data[9]);
        m.setListed_in(data[10]);
        m.setDescription(data[11]);

        return m;
    }
}
