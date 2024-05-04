package com.example.oblig3;

public class Biletter {

    private String film;
    private int antall;  // Changed from String to int
    private String fornavn;
    private String etternavn;
    private String telefonnummer;
    private String epost;

    public Biletter(String film, int antall, String fornavn, String etternavn, String telefonnummer, String epost) {
        this.film = film;
        this.antall = antall;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefonnummer = telefonnummer;
        this.epost = epost;
    }

    // Getters and Setters
    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public int getAntall() {  // Return type changed to int
        return antall;
    }

    public void setAntall(int antall) {  // Parameter type changed to int
        this.antall = antall;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }
}
