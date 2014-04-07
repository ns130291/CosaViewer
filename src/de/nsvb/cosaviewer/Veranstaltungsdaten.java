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

    public void read(File file) {
        try {
            RandomAccessFile vFile = new RandomAccessFile(file, "r");

            name = readAttribute(vFile, 0x120, 90);
            kurzName = readAttribute(vFile, 0x17a, 40);
            veranstalter = readAttribute(vFile, 0x1a2, 50);
            ausrichter = readAttribute(vFile, 0x1d4, 50);
            ort = readAttribute(vFile, 0x200, 50);
            wettkampfstätte = readAttribute(vFile, 0x249, 50);
            veranstaltungsNummer = readAttribute(vFile, 0x238, 13);
            
            //TODO: aktiverte datumseinträge 0x2c1+4
            datum1 = readAttribute(vFile, 0x27b, 10);
            datum2 = readAttribute(vFile, 0x285, 10);
            datum3 = readAttribute(vFile, 0x28f, 10);
            datum4 = readAttribute(vFile, 0x299, 10);
            
            veranstaltungsSaison = readAttribute(vFile, 0xce, 4);
            stellplatzzeit = readAttribute(vFile, 0x9, 3);
            gebührZusendungErgebnisliste = readAttribute(vFile, 0xc, 5);
            aufschlagNachmeldegebühren = readAttribute(vFile, 0x11, 6);
            aufschlagProzent = readAttribute(vFile, 0x17, 1).equals("1");
            beginnNachmeldung = readAttribute(vFile, 0x18, 1).equals("1");
            
            orgGebührErwEinzel = readAttribute(vFile, 0x19, 5);
            orgGebührErwStaffel = readAttribute(vFile, 0x28, 5);
            orgGebührErwMehr = readAttribute(vFile, 0x37, 5);
            orgGebührErwLauf = readAttribute(vFile, 0x46, 5);
            orgGebührErwDMM = readAttribute(vFile, 0x55, 6);
            
            orgGebührU20U18Einzel = readAttribute(vFile, 0x1e, 5);
            orgGebührU20U18Staffel = readAttribute(vFile, 0x2d, 5);
            orgGebührU20U18Mehr = readAttribute(vFile, 0x3c, 5);
            orgGebührU20U18Lauf = readAttribute(vFile, 0x4b, 5);
            orgGebührU20U18DMM = readAttribute(vFile, 0x5b, 6);
            
            orgGebührU16U8Einzel = readAttribute(vFile, 0x23, 5);
            orgGebührU16U8Staffel = readAttribute(vFile, 0x32, 5);
            orgGebührU16U8Mehr = readAttribute(vFile, 0x41, 5);
            orgGebührU16U8Lauf = readAttribute(vFile, 0x50, 5);
            orgGebührU16U8DMM = readAttribute(vFile, 0x61, 6);

            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(this);
                System.out.printf("%s = %s%n", name, value);
            }

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

    private String readAttribute(RandomAccessFile file, long pos, int length) throws IOException {
        byte[] readBuffer = new byte[length];
        file.seek(pos);
        file.read(readBuffer, 0, length);
        return new String(readBuffer, Charset.forName("ISO-8859-1")).trim();
    }
}
