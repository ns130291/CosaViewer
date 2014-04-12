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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ns130291
 */
public class Wettbewerbe {

    private ArrayList<Wettbewerb> wettbewerbe = new ArrayList<Wettbewerb>() {

        @Override
        public String toString() {
            Iterator<Wettbewerb> it = iterator();
            if (!it.hasNext()) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder();
            sb.append('[').append("\n");
            for (;;) {
                Wettbewerb e = it.next();
                sb.append("\t").append(e);
                if (!it.hasNext()) {
                    return sb.append("\n").append(']').toString();
                }
                sb.append(',').append('\n');
            }
        }

    };

    //alle 389 Byte ein Eintrag
    public void read(File file) {
        try {
            RandomAccessFile vFile = new RandomAccessFile(file, "r");

            int next = 389;
            int pos = 0;

            //int i = 0;
            while (vFile.length() > pos) {
                if (!FileReader.readAttribute(vFile, pos, 3).equals("")) {
                    Wettbewerb w = new Wettbewerb();
                    w.setNummer(FileReader.readIntString(vFile, pos, 3));
                    w.setName(FileReader.readAttribute(vFile, pos + 0x3, 28));//genaue Länge noch nicht bekannt
                    w.setWindmessung(FileReader.readAttribute(vFile, pos + 0x2e, 1).equals("1"));
                    w.setOrgGebühr(FileReader.readAttribute(vFile, pos + 0x33, 6));
                    w.setUrkundenTyp(FileReader.readIntString(vFile, pos + 0x31, 1));
                    //Siegertexte
                    w.setAnzahlVersuche(FileReader.readIntString(vFile, pos + 0x2d, 1));
                    w.setTeilnehmerProStaffelMannschaft(FileReader.readIntString(vFile, pos + 0x2c, 1));
                    w.setStellplatzzeit(FileReader.readIntString(vFile, pos + 0x48, 3));
                    w.setPokalwertungsGruppe(FileReader.readIntString(vFile, pos + 0x2f, 2));

                    wettbewerbe.add(w);
                }
                //i++;
                pos += next;
            }

            //System.out.println("i = " + i);
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
}
