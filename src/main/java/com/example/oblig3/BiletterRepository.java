package com.example.oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BiletterRepository {

    @Autowired
    private JdbcTemplate db;

    public void lagreBiletter(Biletter biletter) {
        // SQL statement excluding the 'film' column
        String sql = "INSERT INTO Biletter (antall, fornavn, etternavn, telefonnummer, epost) VALUES (?, ?, ?, ?, ?)";
        try {
            db.update(sql,
                    biletter.getAntall(),
                    biletter.getFornavn(),
                    biletter.getEtternavn(),
                    biletter.getTelefonnummer(),
                    biletter.getEpost()
            );
        } catch (Exception e) {
            System.err.println("Error inserting billett into database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Biletter> hentAlle() {
        try {
            String sql = "SELECT * FROM BILETTER";
            List<Biletter> alleBiletter = db.query(sql, new BeanPropertyRowMapper<>(Biletter.class));
            Collections.sort(alleBiletter, new NavnSortering());
            return alleBiletter;
        } catch (Exception e) {
            e.printStackTrace();  // Log exception
            return new ArrayList<>();  // Return an empty list or handle accordingly
        }
    }


    public void slettAlle(){
        String sql = "DELETE FROM BILETTER";
        db.update(sql);
    }


}
