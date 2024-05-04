package com.example.oblig3;


import java.util.*;

public class NavnSortering implements Comparator<Biletter> {

    public int compare(Biletter bestilling1, Biletter bestilling2)
    {
        return bestilling1.getEtternavn().compareTo(bestilling2.getEtternavn());
    }

}
