package com.example.tpbda;

public class Query {

    private int number;
    private String pays;
    private String wilaya;
    private String language;
        public Query(int number, String pays, String wilaya, String language) {
        this.number = number;
        this.pays = pays;
        this.wilaya = wilaya;
        this.language = language;
    }

    public int getNumber() {
        return number;
    }

    public String getPays() {
        return pays;
    }

    public String getWilaya() {
        return wilaya;
    }

    public String getLanguage() {
        return language;
    }
}
