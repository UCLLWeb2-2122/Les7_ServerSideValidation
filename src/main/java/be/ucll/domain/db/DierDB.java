package be.ucll.domain.db;

import be.ucll.DomainException;
import be.ucll.domain.model.Dier;

import java.util.ArrayList;

public class DierDB {
    private ArrayList<Dier> dieren = new ArrayList<>();

    public DierDB() {
        this.voegToe(new Dier("Albert", "Kat", 3));
        this.voegToe(new Dier("Piet", "Kanarie", 1));
        this.voegToe(new Dier("Lex", "Hond", 2));
        this.voegToe(new Dier("Nijn", "Kat", 5));
        this.voegToe(new Dier("Bruintje", "Kip", 1));
    }

    public ArrayList<Dier> getDieren() {
        return dieren;
    }

    public void voegToe(Dier dier) {
        if (dier == null)
            throw new DomainException("Ongeldig dier om toe te voegen");
        if (vind(dier.getNaam()) != null)
            throw new DomainException("Je mag een dier maar één keer toevoegen");
        dieren.add(dier);
    }

    public Dier vind(String naam) {
        if (naam == null || naam.isEmpty())
            throw new DomainException("Naam mag niet leeg zijn");
        for (Dier dier : dieren) {
            if (dier.getNaam().equals(naam))
                return dier;
        }
        return null;
    }

}