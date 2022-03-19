package be.ucll.domain.model;

import be.ucll.DomainException;

public class Dier {
    private String naam;
    private String soort;
    private int voedsel;

    public Dier(){}

    public Dier(String naam, String soort, int voedsel) {
        setSoort(soort);
        setNaam(naam);
        setVoedsel(voedsel);
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        if (soort == null || soort.isEmpty())
            throw new DomainException("Ongeldige soort");
        this.soort = soort;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty())
            throw new DomainException("Ongeldige naam");
        this.naam = naam;
    }

    public int getVoedsel() {
        return voedsel;
    }

    public void setVoedsel(int voedsel) {
        if (voedsel < 0 || voedsel > 10)
            throw new DomainException("Voedsel moet tussen 0 and 10 liggen");
        this.voedsel = voedsel;
    }

    @Override
    public String toString(){
        return getNaam()+" (voedsel: "+ getVoedsel()+")";
    }
}