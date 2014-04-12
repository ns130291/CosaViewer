/*
 * Copyright (C) 2014 ns130291
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.nsvb.cosaviewer;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ns130291
 */
public class Wettbewerb {

    private int nummer;
    private String name;
    private String kurzName;
    private boolean windmessung;
    private int anzahlVersuche;
    private int teilnehmerProStaffelMannschaft;
    private boolean altersklassenWertung;
    private boolean keineBewertungMitAKFaktoren;
    private int pokalwertungsGruppe;
    private int urkundenTyp;
    private int siegertextNr;
    private int stellplatzzeit;
    private String orgGebühr;
    private Altersklasse altersklasse;
    private Disziplin disziplin;

    //evtl weitere Attribute wie Stellplatzvorlaufzeit, Stellplatzzeit, Vor-, Zwischen- & Endlauf
    public int getPokalwertungsGruppe() {
        return pokalwertungsGruppe;
    }

    public void setPokalwertungsGruppe(int pokalwertungsGruppe) {
        this.pokalwertungsGruppe = pokalwertungsGruppe;
    }
    
    
    public int getStellplatzzeit() {
        return stellplatzzeit;
    }

    public void setStellplatzzeit(int stellplatzzeit) {
        this.stellplatzzeit = stellplatzzeit;
    }
    
    
    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKurzName() {
        return kurzName;
    }

    public void setKurzName(String kurzName) {
        this.kurzName = kurzName;
    }

    public boolean isWindmessung() {
        return windmessung;
    }

    public void setWindmessung(boolean windmessung) {
        this.windmessung = windmessung;
    }

    public int getAnzahlVersuche() {
        return anzahlVersuche;
    }

    public void setAnzahlVersuche(int anzahlVersuche) {
        this.anzahlVersuche = anzahlVersuche;
    }

    public int getTeilnehmerProStaffelMannschaft() {
        return teilnehmerProStaffelMannschaft;
    }

    public void setTeilnehmerProStaffelMannschaft(int teilnehmerProStaffelMannschaft) {
        this.teilnehmerProStaffelMannschaft = teilnehmerProStaffelMannschaft;
    }

    public boolean isAltersklassenWertung() {
        return altersklassenWertung;
    }

    public void setAltersklassenWertung(boolean altersklassenWertung) {
        this.altersklassenWertung = altersklassenWertung;
    }

    public boolean isKeineBewertungMitAKFaktoren() {
        return keineBewertungMitAKFaktoren;
    }

    public void setKeineBewertungMitAKFaktoren(boolean keineBewertungMitAKFaktoren) {
        this.keineBewertungMitAKFaktoren = keineBewertungMitAKFaktoren;
    }

    public int getUrkundenTyp() {
        return urkundenTyp;
    }

    public void setUrkundenTyp(int urkundenTyp) {
        this.urkundenTyp = urkundenTyp;
    }

    public int getSiegertextNr() {
        return siegertextNr;
    }

    public void setSiegertextNr(int siegertextNr) {
        this.siegertextNr = siegertextNr;
    }

    public String getOrgGebühr() {
        return orgGebühr;
    }

    public void setOrgGebühr(String orgGebühr) {
        this.orgGebühr = orgGebühr;
    }

    public Altersklasse getAltersklasse() {
        return altersklasse;
    }

    public void setAltersklasse(Altersklasse altersklasse) {
        this.altersklasse = altersklasse;
    }

    public Disziplin getDisziplin() {
        return disziplin;
    }

    public void setDisziplin(Disziplin disziplin) {
        this.disziplin = disziplin;
    }

    @Override
    public String toString() {
        String out = "";
        for (Field field : getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(this);
                out += name + " = " + value + "; ";
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Wettbewerb.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Wettbewerb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return out; //To change body of generated methods, choose Tools | Templates.
    }

}
