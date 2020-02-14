package com.siddharth.moviedetailapp.Model;

import java.io.Serializable;

public class Movie implements Serializable {

    public static final long ID = 1l;


    public String tvrated;
    public String movietype;
    public String production;
    public String country;
    public String awards;
    public String runtime;
    public String imdbid;
    public String poster;
    public String genre;
    public String writer;
    public String title;
    public String rating;
    public String released;
    public String director;
    public String year;
    public String actors;
    public String plot;

    /*public Movie(String tvrated, String movietype, String production, String country, String awards, String runtime, String imdbid, String poster, String genre, String writer, String title, String rating, String released, String director, String year, String actors, String plot) {
        this.tvrated = tvrated;
        this.movietype = movietype;
        this.production = production;
        this.country = country;
        this.awards = awards;
        this.runtime = runtime;
        this.imdbid = imdbid;
        this.poster = poster;
        this.genre = genre;
        this.writer = writer;
        this.title = title;
        this.rating = rating;
        this.released = released;
        this.director = director;
        this.year = year;
        this.actors = actors;
        this.plot = plot;
    }*/

    public static long getID() {
        return ID;
    }

    public String getTvrated() {
        return tvrated;
    }

    public void setTvrated(String tvrated) {
        this.tvrated = tvrated;
    }

    public String getMovietype() {
        return movietype;
    }

    public void setMovietype(String movietype) {
        this.movietype = movietype;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
