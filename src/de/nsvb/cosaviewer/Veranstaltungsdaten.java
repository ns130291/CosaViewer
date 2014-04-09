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
            RandomAccessFile vFile = new RandomAccessFile(file, "r");

            name = FileReader.readAttribute(vFile, 0x120, 90);
            kurzName = FileReader.readAttribute(vFile, 0x17a, 40);
            veranstalter = FileReader.readAttribute(vFile, 0x1a2, 50);
            ausrichter = FileReader.readAttribute(vFile, 0x1d4, 50);
            ort = FileReader.readAttribute(vFile, 0x200, 50);
            wettkampfstätte = FileReader.readAttribute(vFile, 0x249, 50);
            veranstaltungsNummer = FileReader.readAttribute(vFile, 0x238, 13);

            //TODO: aktiverte datumseinträge 0x2c1+4
            datum1 = FileReader.readAttribute(vFile, 0x27b, 10);
            datum2 = FileReader.readAttribute(vFile, 0x285, 10);
            datum3 = FileReader.readAttribute(vFile, 0x28f, 10);
            datum4 = FileReader.readAttribute(vFile, 0x299, 10);

            veranstaltungsSaison = FileReader.readAttribute(vFile, 0xce, 4);
            stellplatzzeit = FileReader.readAttribute(vFile, 0x9, 3);
            gebührZusendungErgebnisliste = FileReader.readAttribute(vFile, 0xc, 5);
            aufschlagNachmeldegebühren = FileReader.readAttribute(vFile, 0x11, 6);
            aufschlagProzent = FileReader.readAttribute(vFile, 0x17, 1).equals("1");
            beginnNachmeldung = FileReader.readAttribute(vFile, 0x18, 1).equals("1");

            orgGebührErwEinzel = FileReader.readAttribute(vFile, 0x19, 5);
            orgGebührErwStaffel = FileReader.readAttribute(vFile, 0x28, 5);
            orgGebührErwMehr = FileReader.readAttribute(vFile, 0x37, 5);
            orgGebührErwLauf = FileReader.readAttribute(vFile, 0x46, 5);
            orgGebührErwDMM = FileReader.readAttribute(vFile, 0x55, 6);

            orgGebührU20U18Einzel = FileReader.readAttribute(vFile, 0x1e, 5);
            orgGebührU20U18Staffel = FileReader.readAttribute(vFile, 0x2d, 5);
            orgGebührU20U18Mehr = FileReader.readAttribute(vFile, 0x3c, 5);
            orgGebührU20U18Lauf = FileReader.readAttribute(vFile, 0x4b, 5);
            orgGebührU20U18DMM = FileReader.readAttribute(vFile, 0x5b, 6);

            orgGebührU16U8Einzel = FileReader.readAttribute(vFile, 0x23, 5);
            orgGebührU16U8Staffel = FileReader.readAttribute(vFile, 0x32, 5);
            orgGebührU16U8Mehr = FileReader.readAttribute(vFile, 0x41, 5);
            orgGebührU16U8Lauf = FileReader.readAttribute(vFile, 0x50, 5);
            orgGebührU16U8DMM = FileReader.readAttribute(vFile, 0x61, 6);

            orgGebühr3_4KampfU16U8 = FileReader.readAttribute(vFile, 0x1606, 5);
            orgGebühr4_9KampfMJU16 = FileReader.readAttribute(vFile, 0x160b, 5);
            orgGebühr4_7KampfWJU16 = FileReader.readAttribute(vFile, 0x1610, 5);
            orgGebühr5_10KampfMJU20U18 = FileReader.readAttribute(vFile, 0x1615, 5);
            orgGebühr4_7KampfWJU20U18 = FileReader.readAttribute(vFile, 0x161a, 5);

            veranstaltungsTyp = Typ.values()[FileReader.readByte(vFile, 0x2c8)];

            anzahlBahnenFlachGerade = FileReader.readIntString(vFile, 0x6a, 2);
            anzahlBahnenFlachRund = FileReader.readIntString(vFile, 0x6c, 2);
            anzahlBahnenHürdenGerade = FileReader.readIntString(vFile, 0x6e, 2);
            anzahlBahnenHürdenRund = FileReader.readIntString(vFile, 0x70, 2);
            anzahlUrkundenJeWettbewerb = FileReader.readIntString(vFile, 0x6, 3);
            anzahlAusdruckeJeWettkampflisteLauf = FileReader.readIntString(vFile, 0x72, 2);
            anzahlAusdruckeJeWettkampflisteStaffel = FileReader.readIntString(vFile, 0x74, 2);
            anzahlAusdruckeJeWettkampflisteHochStab = FileReader.readIntString(vFile, 0x76, 2);
            anzahlAusdruckeJeWettkampflisteTechnik = FileReader.readIntString(vFile, 0x78, 2);
            anzahlFreieStartpositionenLauf = FileReader.readIntString(vFile, 0x7a, 1);
            anzahlFreieStartpositionenTechnik = FileReader.readIntString(vFile, 0x7c, 1);
            anzahlAusdruckeErgebnisprotokollErfassung = FileReader.readIntString(vFile, 0x82, 2);

            zeitnahmeElektronisch = FileReader.readAttribute(vFile, 0x5, 1).equals("1");
            altersklassenPrüfung = FileReader.readAttribute(vFile, 0x89, 1).equals("1");
            pokalwertung = FileReader.readAttribute(vFile, 0x88, 1).equals("1");
            auswertungNachWertungsgruppen = FileReader.readAttribute(vFile, 0x8d, 1).equals("1");
            hallenveranstaltung = FileReader.readAttribute(vFile, 0x90, 1).equals("1");
            automQualiKennzSetzen = FileReader.readAttribute(vFile, 0x8c, 1).equals("1");
            andruckKennzBSGbeiBewertNachDLVMehrkampfabz = FileReader.readAttribute(vFile, 0x8e, 1).equals("1");
            bewertSenBereichAltersklassenfaktorenMehrkampf = FileReader.readAttribute(vFile, 0x8f, 1).equals("1");
            mitWettbewerbenDMMDAMM = FileReader.readAttribute(vFile, 0xc6, 1).equals("1");
            winderfassungKleinerU16 = FileReader.readAttribute(vFile, 0x92, 1).equals("1");
            altersklassenKleinerU10 = FileReader.readAttribute(vFile, 0x93, 1).equals("1");
            gemischteWettbewerbe = FileReader.readAttribute(vFile, 0xc1, 1).equals("1");
            //bahnenSperren = FileReader.readAttribute(vFile, ??????, 1).equals("1");

            gesperrtFlachGerade = FileReader.readBooleans(vFile, 0x94, 10);
            gesperrtFlachRund = FileReader.readBooleans(vFile, 0x9e, 10);
            gesperrtHürdenGerade = FileReader.readBooleans(vFile, 0xa8, 10);
            gesperrtHürdenRund = FileReader.readBooleans(vFile, 0xb2, 10);

            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(this);
                System.out.printf("%s = %s%n", name, value);
            }
            
            /*for(boolean t:gesperrtHürdenRund){
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

        Typ(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
