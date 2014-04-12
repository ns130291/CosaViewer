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
