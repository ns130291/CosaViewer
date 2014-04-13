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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ns130291
 */
public class Veranstaltungsdaten {

    private String name;
    private String kurzName;
    private String veranstalter;
    private String ausrichter;
    private String veranstaltungsNummer;
    private String ort;
    private String wettkampfstätte;
    private String datum1;
    private String datum2;
    private String datum3;
    private String datum4;
    private String veranstaltungsSaison;
    private String stellplatzzeit;
    private String gebührZusendungErgebnisliste;
    private String aufschlagNachmeldegebühren;

    private boolean aufschlagProzent;
    private boolean beginnNachmeldung;
    private boolean zeitnahmeElektronisch;
    private boolean altersklassenPrüfung;
    private boolean pokalwertung;
    private boolean auswertungNachWertungsgruppen;
    private boolean hallenveranstaltung;
    private boolean automQualiKennzSetzen;
    private boolean andruckKennzBSGbeiBewertNachDLVMehrkampfabz;
    private boolean bewertSenBereichAltersklassenfaktorenMehrkampf;
    private boolean mitWettbewerbenDMMDAMM;
    private boolean winderfassungKleinerU16;
    private boolean altersklassenKleinerU10;
    private boolean gemischteWettbewerbe;
    //private boolean bahnenSperren;

    private boolean[] gesperrtFlachGerade;
    private boolean[] gesperrtFlachRund;
    private boolean[] gesperrtHürdenGerade;
    private boolean[] gesperrtHürdenRund;
    private boolean[] tage;

    private Typ veranstaltungsTyp;

    private int anzahlUrkundenJeWettbewerb;
    private int anzahlBahnenFlachGerade;
    private int anzahlBahnenFlachRund;
    private int anzahlBahnenHürdenGerade;
    private int anzahlBahnenHürdenRund;
    private int anzahlAusdruckeJeWettkampflisteLauf;
    private int anzahlAusdruckeJeWettkampflisteStaffel;
    private int anzahlAusdruckeJeWettkampflisteHochStab;
    private int anzahlAusdruckeJeWettkampflisteTechnik;
    private int anzahlFreieStartpositionenLauf;
    private int anzahlFreieStartpositionenTechnik;
    private int anzahlAusdruckeErgebnisprotokollErfassung;

    //Organisationsgebühren
    private String orgGebührErwEinzel;
    private String orgGebührErwStaffel;
    private String orgGebührErwMehr;
    private String orgGebührErwLauf;
    private String orgGebührErwDMM;
    private String orgGebührU20U18Einzel;
    private String orgGebührU20U18Staffel;
    private String orgGebührU20U18Mehr;
    private String orgGebührU20U18Lauf;
    private String orgGebührU20U18DMM;
    private String orgGebührU16U8Einzel;
    private String orgGebührU16U8Staffel;
    private String orgGebührU16U8Mehr;
    private String orgGebührU16U8Lauf;
    private String orgGebührU16U8DMM;
    private String orgGebühr3_4KampfU16U8;
    private String orgGebühr4_9KampfMJU16;
    private String orgGebühr4_7KampfWJU16;
    private String orgGebühr5_10KampfMJU20U18;
    private String orgGebühr4_7KampfWJU20U18;

    public void read(File file) {            
        try {
            read(new RandomFileReader(new RandomAccessFile(file, "r")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Veranstaltungsdaten.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void read(byte[] file){
        read(new ByteArrayReader(file));
    }
    
    private void read(Reader reader) {
        try {

            name = reader.readAttribute(0x120, 90);
            kurzName = reader.readAttribute(0x17a, 40);
            veranstalter = reader.readAttribute(0x1a2, 50);
            ausrichter = reader.readAttribute(0x1d4, 50);
            ort = reader.readAttribute(0x200, 50);
            wettkampfstätte = reader.readAttribute(0x249, 50);
            veranstaltungsNummer = reader.readAttribute(0x238, 13);

            //TODO: aktiverte datumseinträge 0x2c1+4
            tage = reader.readBooleans(0x2c1, 4);
            datum1 = reader.readAttribute(0x27b, 10);
            datum2 = reader.readAttribute(0x285, 10);
            datum3 = reader.readAttribute(0x28f, 10);
            datum4 = reader.readAttribute(0x299, 10);

            veranstaltungsSaison = reader.readAttribute(0xce, 4);
            stellplatzzeit = reader.readAttribute(0x9, 3);
            gebührZusendungErgebnisliste = reader.readAttribute(0xc, 5);
            aufschlagNachmeldegebühren = reader.readAttribute(0x11, 6);
            aufschlagProzent = reader.readAttribute(0x17, 1).equals("1");
            beginnNachmeldung = reader.readAttribute(0x18, 1).equals("1");

            orgGebührErwEinzel = reader.readAttribute(0x19, 5);
            orgGebührErwStaffel = reader.readAttribute(0x28, 5);
            orgGebührErwMehr = reader.readAttribute(0x37, 5);
            orgGebührErwLauf = reader.readAttribute(0x46, 5);
            orgGebührErwDMM = reader.readAttribute(0x55, 6);

            orgGebührU20U18Einzel = reader.readAttribute(0x1e, 5);
            orgGebührU20U18Staffel = reader.readAttribute(0x2d, 5);
            orgGebührU20U18Mehr = reader.readAttribute(0x3c, 5);
            orgGebührU20U18Lauf = reader.readAttribute(0x4b, 5);
            orgGebührU20U18DMM = reader.readAttribute(0x5b, 6);

            orgGebührU16U8Einzel = reader.readAttribute(0x23, 5);
            orgGebührU16U8Staffel = reader.readAttribute(0x32, 5);
            orgGebührU16U8Mehr = reader.readAttribute(0x41, 5);
            orgGebührU16U8Lauf = reader.readAttribute(0x50, 5);
            orgGebührU16U8DMM = reader.readAttribute(0x61, 6);

            orgGebühr3_4KampfU16U8 = reader.readAttribute(0x1606, 5);
            orgGebühr4_9KampfMJU16 = reader.readAttribute(0x160b, 5);
            orgGebühr4_7KampfWJU16 = reader.readAttribute(0x1610, 5);
            orgGebühr5_10KampfMJU20U18 = reader.readAttribute(0x1615, 5);
            orgGebühr4_7KampfWJU20U18 = reader.readAttribute(0x161a, 5);

            veranstaltungsTyp = Typ.values()[reader.readByte(0x2c8)];

            anzahlBahnenFlachGerade = reader.readIntString(0x6a, 2);
            anzahlBahnenFlachRund = reader.readIntString(0x6c, 2);
            anzahlBahnenHürdenGerade = reader.readIntString(0x6e, 2);
            anzahlBahnenHürdenRund = reader.readIntString(0x70, 2);
            anzahlUrkundenJeWettbewerb = reader.readIntString(0x6, 3);
            anzahlAusdruckeJeWettkampflisteLauf = reader.readIntString(0x72, 2);
            anzahlAusdruckeJeWettkampflisteStaffel = reader.readIntString(0x74, 2);
            anzahlAusdruckeJeWettkampflisteHochStab = reader.readIntString(0x76, 2);
            anzahlAusdruckeJeWettkampflisteTechnik = reader.readIntString(0x78, 2);
            anzahlFreieStartpositionenLauf = reader.readIntString(0x7a, 1);
            anzahlFreieStartpositionenTechnik = reader.readIntString(0x7c, 1);
            anzahlAusdruckeErgebnisprotokollErfassung = reader.readIntString(0x82, 2);

            zeitnahmeElektronisch = reader.readAttribute(0x5, 1).equals("1");
            altersklassenPrüfung = reader.readAttribute(0x89, 1).equals("1");
            pokalwertung = reader.readAttribute(0x88, 1).equals("1");
            auswertungNachWertungsgruppen = reader.readAttribute(0x8d, 1).equals("1");
            hallenveranstaltung = reader.readAttribute(0x90, 1).equals("1");
            automQualiKennzSetzen = reader.readAttribute(0x8c, 1).equals("1");
            andruckKennzBSGbeiBewertNachDLVMehrkampfabz = reader.readAttribute(0x8e, 1).equals("1");
            bewertSenBereichAltersklassenfaktorenMehrkampf = reader.readAttribute(0x8f, 1).equals("1");
            mitWettbewerbenDMMDAMM = reader.readAttribute(0xc6, 1).equals("1");
            winderfassungKleinerU16 = reader.readAttribute(0x92, 1).equals("1");
            altersklassenKleinerU10 = reader.readAttribute(0x93, 1).equals("1");
            gemischteWettbewerbe = reader.readAttribute(0xc1, 1).equals("1");
            //bahnenSperren = Filereader.readAttribute(??????, 1).equals("1");

            gesperrtFlachGerade = reader.readBooleans(0x94, 10);
            gesperrtFlachRund = reader.readBooleans(0x9e, 10);
            gesperrtHürdenGerade = reader.readBooleans(0xa8, 10);
            gesperrtHürdenRund = reader.readBooleans(0xb2, 10);

            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(this);
                System.out.printf("%s = %s%n", name, value);
            }
            
            /*for(boolean t:tage){
                System.out.print((t)?"1":"0");
            }*/

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Veranstaltungsdaten.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Veranstaltungsdaten.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Veranstaltungsdaten.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Veranstaltungsdaten.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public String getVeranstalter() {
        return veranstalter;
    }

    public void setVeranstalter(String veranstalter) {
        this.veranstalter = veranstalter;
    }

    public String getAusrichter() {
        return ausrichter;
    }

    public void setAusrichter(String ausrichter) {
        this.ausrichter = ausrichter;
    }

    public String getVeranstaltungsNummer() {
        return veranstaltungsNummer;
    }

    public void setVeranstaltungsNummer(String veranstaltungsNummer) {
        this.veranstaltungsNummer = veranstaltungsNummer;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getWettkampfstätte() {
        return wettkampfstätte;
    }

    public void setWettkampfstätte(String wettkampfstätte) {
        this.wettkampfstätte = wettkampfstätte;
    }

    public String getDatum1() {
        return datum1;
    }

    public void setDatum1(String datum1) {
        this.datum1 = datum1;
    }

    public String getDatum2() {
        return datum2;
    }

    public void setDatum2(String datum2) {
        this.datum2 = datum2;
    }

    public String getDatum3() {
        return datum3;
    }

    public void setDatum3(String datum3) {
        this.datum3 = datum3;
    }

    public String getDatum4() {
        return datum4;
    }

    public void setDatum4(String datum4) {
        this.datum4 = datum4;
    }

    public String getVeranstaltungsSaison() {
        return veranstaltungsSaison;
    }

    public void setVeranstaltungsSaison(String veranstaltungsSaison) {
        this.veranstaltungsSaison = veranstaltungsSaison;
    }

    public String getStellplatzzeit() {
        return stellplatzzeit;
    }

    public void setStellplatzzeit(String stellplatzzeit) {
        this.stellplatzzeit = stellplatzzeit;
    }

    public String getGebührZusendungErgebnisliste() {
        return gebührZusendungErgebnisliste;
    }

    public void setGebührZusendungErgebnisliste(String gebührZusendungErgebnisliste) {
        this.gebührZusendungErgebnisliste = gebührZusendungErgebnisliste;
    }

    public String getAufschlagNachmeldegebühren() {
        return aufschlagNachmeldegebühren;
    }

    public void setAufschlagNachmeldegebühren(String aufschlagNachmeldegebühren) {
        this.aufschlagNachmeldegebühren = aufschlagNachmeldegebühren;
    }

    public boolean isAufschlagProzent() {
        return aufschlagProzent;
    }

    public void setAufschlagProzent(boolean aufschlagProzent) {
        this.aufschlagProzent = aufschlagProzent;
    }

    public boolean isBeginnNachmeldung() {
        return beginnNachmeldung;
    }

    public void setBeginnNachmeldung(boolean beginnNachmeldung) {
        this.beginnNachmeldung = beginnNachmeldung;
    }

    public boolean isZeitnahmeElektronisch() {
        return zeitnahmeElektronisch;
    }

    public void setZeitnahmeElektronisch(boolean zeitnahmeElektronisch) {
        this.zeitnahmeElektronisch = zeitnahmeElektronisch;
    }

    public boolean isAltersklassenPrüfung() {
        return altersklassenPrüfung;
    }

    public void setAltersklassenPrüfung(boolean altersklassenPrüfung) {
        this.altersklassenPrüfung = altersklassenPrüfung;
    }

    public boolean isPokalwertung() {
        return pokalwertung;
    }

    public void setPokalwertung(boolean pokalwertung) {
        this.pokalwertung = pokalwertung;
    }

    public boolean isAuswertungNachWertungsgruppen() {
        return auswertungNachWertungsgruppen;
    }

    public void setAuswertungNachWertungsgruppen(boolean auswertungNachWertungsgruppen) {
        this.auswertungNachWertungsgruppen = auswertungNachWertungsgruppen;
    }

    public boolean isHallenveranstaltung() {
        return hallenveranstaltung;
    }

    public void setHallenveranstaltung(boolean hallenveranstaltung) {
        this.hallenveranstaltung = hallenveranstaltung;
    }

    public boolean isAutomQualiKennzSetzen() {
        return automQualiKennzSetzen;
    }

    public void setAutomQualiKennzSetzen(boolean automQualiKennzSetzen) {
        this.automQualiKennzSetzen = automQualiKennzSetzen;
    }

    public boolean isAndruckKennzBSGbeiBewertNachDLVMehrkampfabz() {
        return andruckKennzBSGbeiBewertNachDLVMehrkampfabz;
    }

    public void setAndruckKennzBSGbeiBewertNachDLVMehrkampfabz(boolean andruckKennzBSGbeiBewertNachDLVMehrkampfabz) {
        this.andruckKennzBSGbeiBewertNachDLVMehrkampfabz = andruckKennzBSGbeiBewertNachDLVMehrkampfabz;
    }

    public boolean isBewertSenBereichAltersklassenfaktorenMehrkampf() {
        return bewertSenBereichAltersklassenfaktorenMehrkampf;
    }

    public void setBewertSenBereichAltersklassenfaktorenMehrkampf(boolean bewertSenBereichAltersklassenfaktorenMehrkampf) {
        this.bewertSenBereichAltersklassenfaktorenMehrkampf = bewertSenBereichAltersklassenfaktorenMehrkampf;
    }

    public boolean isMitWettbewerbenDMMDAMM() {
        return mitWettbewerbenDMMDAMM;
    }

    public void setMitWettbewerbenDMMDAMM(boolean mitWettbewerbenDMMDAMM) {
        this.mitWettbewerbenDMMDAMM = mitWettbewerbenDMMDAMM;
    }

    public boolean isWinderfassungKleinerU16() {
        return winderfassungKleinerU16;
    }

    public void setWinderfassungKleinerU16(boolean winderfassungKleinerU16) {
        this.winderfassungKleinerU16 = winderfassungKleinerU16;
    }

    public boolean isAltersklassenKleinerU10() {
        return altersklassenKleinerU10;
    }

    public void setAltersklassenKleinerU10(boolean altersklassenKleinerU10) {
        this.altersklassenKleinerU10 = altersklassenKleinerU10;
    }

    public boolean isGemischteWettbewerbe() {
        return gemischteWettbewerbe;
    }

    public void setGemischteWettbewerbe(boolean gemischteWettbewerbe) {
        this.gemischteWettbewerbe = gemischteWettbewerbe;
    }

    public boolean[] getGesperrtFlachGerade() {
        return gesperrtFlachGerade;
    }

    public void setGesperrtFlachGerade(boolean[] gesperrtFlachGerade) {
        this.gesperrtFlachGerade = gesperrtFlachGerade;
    }

    public boolean[] getGesperrtFlachRund() {
        return gesperrtFlachRund;
    }

    public void setGesperrtFlachRund(boolean[] gesperrtFlachRund) {
        this.gesperrtFlachRund = gesperrtFlachRund;
    }

    public boolean[] getGesperrtHürdenGerade() {
        return gesperrtHürdenGerade;
    }

    public void setGesperrtHürdenGerade(boolean[] gesperrtHürdenGerade) {
        this.gesperrtHürdenGerade = gesperrtHürdenGerade;
    }

    public boolean[] getGesperrtHürdenRund() {
        return gesperrtHürdenRund;
    }

    public void setGesperrtHürdenRund(boolean[] gesperrtHürdenRund) {
        this.gesperrtHürdenRund = gesperrtHürdenRund;
    }

    public boolean[] getTage() {
        return tage;
    }

    public void setTage(boolean[] tage) {
        this.tage = tage;
    }

    public Typ getVeranstaltungsTyp() {
        return veranstaltungsTyp;
    }

    public void setVeranstaltungsTyp(Typ veranstaltungsTyp) {
        this.veranstaltungsTyp = veranstaltungsTyp;
    }

    public int getAnzahlUrkundenJeWettbewerb() {
        return anzahlUrkundenJeWettbewerb;
    }

    public void setAnzahlUrkundenJeWettbewerb(int anzahlUrkundenJeWettbewerb) {
        this.anzahlUrkundenJeWettbewerb = anzahlUrkundenJeWettbewerb;
    }

    public int getAnzahlBahnenFlachGerade() {
        return anzahlBahnenFlachGerade;
    }

    public void setAnzahlBahnenFlachGerade(int anzahlBahnenFlachGerade) {
        this.anzahlBahnenFlachGerade = anzahlBahnenFlachGerade;
    }

    public int getAnzahlBahnenFlachRund() {
        return anzahlBahnenFlachRund;
    }

    public void setAnzahlBahnenFlachRund(int anzahlBahnenFlachRund) {
        this.anzahlBahnenFlachRund = anzahlBahnenFlachRund;
    }

    public int getAnzahlBahnenHürdenGerade() {
        return anzahlBahnenHürdenGerade;
    }

    public void setAnzahlBahnenHürdenGerade(int anzahlBahnenHürdenGerade) {
        this.anzahlBahnenHürdenGerade = anzahlBahnenHürdenGerade;
    }

    public int getAnzahlBahnenHürdenRund() {
        return anzahlBahnenHürdenRund;
    }

    public void setAnzahlBahnenHürdenRund(int anzahlBahnenHürdenRund) {
        this.anzahlBahnenHürdenRund = anzahlBahnenHürdenRund;
    }

    public int getAnzahlAusdruckeJeWettkampflisteLauf() {
        return anzahlAusdruckeJeWettkampflisteLauf;
    }

    public void setAnzahlAusdruckeJeWettkampflisteLauf(int anzahlAusdruckeJeWettkampflisteLauf) {
        this.anzahlAusdruckeJeWettkampflisteLauf = anzahlAusdruckeJeWettkampflisteLauf;
    }

    public int getAnzahlAusdruckeJeWettkampflisteStaffel() {
        return anzahlAusdruckeJeWettkampflisteStaffel;
    }

    public void setAnzahlAusdruckeJeWettkampflisteStaffel(int anzahlAusdruckeJeWettkampflisteStaffel) {
        this.anzahlAusdruckeJeWettkampflisteStaffel = anzahlAusdruckeJeWettkampflisteStaffel;
    }

    public int getAnzahlAusdruckeJeWettkampflisteHochStab() {
        return anzahlAusdruckeJeWettkampflisteHochStab;
    }

    public void setAnzahlAusdruckeJeWettkampflisteHochStab(int anzahlAusdruckeJeWettkampflisteHochStab) {
        this.anzahlAusdruckeJeWettkampflisteHochStab = anzahlAusdruckeJeWettkampflisteHochStab;
    }

    public int getAnzahlAusdruckeJeWettkampflisteTechnik() {
        return anzahlAusdruckeJeWettkampflisteTechnik;
    }

    public void setAnzahlAusdruckeJeWettkampflisteTechnik(int anzahlAusdruckeJeWettkampflisteTechnik) {
        this.anzahlAusdruckeJeWettkampflisteTechnik = anzahlAusdruckeJeWettkampflisteTechnik;
    }

    public int getAnzahlFreieStartpositionenLauf() {
        return anzahlFreieStartpositionenLauf;
    }

    public void setAnzahlFreieStartpositionenLauf(int anzahlFreieStartpositionenLauf) {
        this.anzahlFreieStartpositionenLauf = anzahlFreieStartpositionenLauf;
    }

    public int getAnzahlFreieStartpositionenTechnik() {
        return anzahlFreieStartpositionenTechnik;
    }

    public void setAnzahlFreieStartpositionenTechnik(int anzahlFreieStartpositionenTechnik) {
        this.anzahlFreieStartpositionenTechnik = anzahlFreieStartpositionenTechnik;
    }

    public int getAnzahlAusdruckeErgebnisprotokollErfassung() {
        return anzahlAusdruckeErgebnisprotokollErfassung;
    }

    public void setAnzahlAusdruckeErgebnisprotokollErfassung(int anzahlAusdruckeErgebnisprotokollErfassung) {
        this.anzahlAusdruckeErgebnisprotokollErfassung = anzahlAusdruckeErgebnisprotokollErfassung;
    }

    public String getOrgGebührErwEinzel() {
        return orgGebührErwEinzel;
    }

    public void setOrgGebührErwEinzel(String orgGebührErwEinzel) {
        this.orgGebührErwEinzel = orgGebührErwEinzel;
    }

    public String getOrgGebührErwStaffel() {
        return orgGebührErwStaffel;
    }

    public void setOrgGebührErwStaffel(String orgGebührErwStaffel) {
        this.orgGebührErwStaffel = orgGebührErwStaffel;
    }

    public String getOrgGebührErwMehr() {
        return orgGebührErwMehr;
    }

    public void setOrgGebührErwMehr(String orgGebührErwMehr) {
        this.orgGebührErwMehr = orgGebührErwMehr;
    }

    public String getOrgGebührErwLauf() {
        return orgGebührErwLauf;
    }

    public void setOrgGebührErwLauf(String orgGebührErwLauf) {
        this.orgGebührErwLauf = orgGebührErwLauf;
    }

    public String getOrgGebührErwDMM() {
        return orgGebührErwDMM;
    }

    public void setOrgGebührErwDMM(String orgGebührErwDMM) {
        this.orgGebührErwDMM = orgGebührErwDMM;
    }

    public String getOrgGebührU20U18Einzel() {
        return orgGebührU20U18Einzel;
    }

    public void setOrgGebührU20U18Einzel(String orgGebührU20U18Einzel) {
        this.orgGebührU20U18Einzel = orgGebührU20U18Einzel;
    }

    public String getOrgGebührU20U18Staffel() {
        return orgGebührU20U18Staffel;
    }

    public void setOrgGebührU20U18Staffel(String orgGebührU20U18Staffel) {
        this.orgGebührU20U18Staffel = orgGebührU20U18Staffel;
    }

    public String getOrgGebührU20U18Mehr() {
        return orgGebührU20U18Mehr;
    }

    public void setOrgGebührU20U18Mehr(String orgGebührU20U18Mehr) {
        this.orgGebührU20U18Mehr = orgGebührU20U18Mehr;
    }

    public String getOrgGebührU20U18Lauf() {
        return orgGebührU20U18Lauf;
    }

    public void setOrgGebührU20U18Lauf(String orgGebührU20U18Lauf) {
        this.orgGebührU20U18Lauf = orgGebührU20U18Lauf;
    }

    public String getOrgGebührU20U18DMM() {
        return orgGebührU20U18DMM;
    }

    public void setOrgGebührU20U18DMM(String orgGebührU20U18DMM) {
        this.orgGebührU20U18DMM = orgGebührU20U18DMM;
    }

    public String getOrgGebührU16U8Einzel() {
        return orgGebührU16U8Einzel;
    }

    public void setOrgGebührU16U8Einzel(String orgGebührU16U8Einzel) {
        this.orgGebührU16U8Einzel = orgGebührU16U8Einzel;
    }

    public String getOrgGebührU16U8Staffel() {
        return orgGebührU16U8Staffel;
    }

    public void setOrgGebührU16U8Staffel(String orgGebührU16U8Staffel) {
        this.orgGebührU16U8Staffel = orgGebührU16U8Staffel;
    }

    public String getOrgGebührU16U8Mehr() {
        return orgGebührU16U8Mehr;
    }

    public void setOrgGebührU16U8Mehr(String orgGebührU16U8Mehr) {
        this.orgGebührU16U8Mehr = orgGebührU16U8Mehr;
    }

    public String getOrgGebührU16U8Lauf() {
        return orgGebührU16U8Lauf;
    }

    public void setOrgGebührU16U8Lauf(String orgGebührU16U8Lauf) {
        this.orgGebührU16U8Lauf = orgGebührU16U8Lauf;
    }

    public String getOrgGebührU16U8DMM() {
        return orgGebührU16U8DMM;
    }

    public void setOrgGebührU16U8DMM(String orgGebührU16U8DMM) {
        this.orgGebührU16U8DMM = orgGebührU16U8DMM;
    }

    public String getOrgGebühr3_4KampfU16U8() {
        return orgGebühr3_4KampfU16U8;
    }

    public void setOrgGebühr3_4KampfU16U8(String orgGebühr3_4KampfU16U8) {
        this.orgGebühr3_4KampfU16U8 = orgGebühr3_4KampfU16U8;
    }

    public String getOrgGebühr4_9KampfMJU16() {
        return orgGebühr4_9KampfMJU16;
    }

    public void setOrgGebühr4_9KampfMJU16(String orgGebühr4_9KampfMJU16) {
        this.orgGebühr4_9KampfMJU16 = orgGebühr4_9KampfMJU16;
    }

    public String getOrgGebühr4_7KampfWJU16() {
        return orgGebühr4_7KampfWJU16;
    }

    public void setOrgGebühr4_7KampfWJU16(String orgGebühr4_7KampfWJU16) {
        this.orgGebühr4_7KampfWJU16 = orgGebühr4_7KampfWJU16;
    }

    public String getOrgGebühr5_10KampfMJU20U18() {
        return orgGebühr5_10KampfMJU20U18;
    }

    public void setOrgGebühr5_10KampfMJU20U18(String orgGebühr5_10KampfMJU20U18) {
        this.orgGebühr5_10KampfMJU20U18 = orgGebühr5_10KampfMJU20U18;
    }

    public String getOrgGebühr4_7KampfWJU20U18() {
        return orgGebühr4_7KampfWJU20U18;
    }

    public void setOrgGebühr4_7KampfWJU20U18(String orgGebühr4_7KampfWJU20U18) {
        this.orgGebühr4_7KampfWJU20U18 = orgGebühr4_7KampfWJU20U18;
    }
    
    

    public enum Typ {

        VEREINSOFFEN("Vereinsoffen"),
        KREISOFFEN("Kreisoffen"),
        BEZIRKSOFFEN("Bezirksoffen"),
        LANDESOFFEN("Landesoffen"),
        NATIONAL("National"),
        INTERN_SPORTFEST("Intern. Sportfest"),
        INTERN_EINLADUNGSSPORTFEST("Intern. Einladungssportfest"),
        EAA("EAA"),
        IAAF("IAAF");

        private final String name;

        private Typ(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
