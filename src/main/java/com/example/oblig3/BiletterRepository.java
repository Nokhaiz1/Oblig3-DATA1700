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

    public void lagreBiletter (Biletter nyBilett){
        String sql = "INSERT INTO BILETTER (antall, fornavn, etternavn, mobilnummer, epost, navn) VALUES(?,?,?,?,?,?)";

        db.update(
                sql,
                nyBilett.getFilm(),
                nyBilett.getAntall(),
                nyBilett.getFornavn(),
                nyBilett.getEtternavn(),
                nyBilett.getTelefonnummer(),
                nyBilett.getEpost()
        );
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
