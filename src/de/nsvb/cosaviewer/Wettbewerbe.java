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
import java.util.regex.Pattern;

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

    public void read(File file) {
        try {
            read(new RandomFileReader(new RandomAccessFile(file, "r")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Veranstaltungsdaten.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read(byte[] file) {
        read(new ByteArrayReader(file));
    }

    //alle 389 Byte ein Eintrag
    private void read(Reader reader) {
        try {
            final int next = 389;
            int pos = 0;

            //int i = 0;
            while (reader.length() > pos) {
                if (!reader.readAttribute(pos, 3).equals("") && !reader.readAttribute(pos + 0x3, 28).equals("")) {
                    Wettbewerb w = new Wettbewerb();
                    w.setNummer(reader.readIntString(pos, 3));
                    w.setName(reader.readAttribute(pos + 0x3, 28));//TODO: genaue Länge noch nicht bekannt
                    w.setWindmessung(reader.readAttribute(pos + 0x2e, 1).equals("1"));
                    w.setOrgGebühr(reader.readAttribute(pos + 0x33, 6));
                    w.setUrkundenTyp(reader.readIntString(pos + 0x31, 1));
                    //Siegertexte
                    w.setAnzahlVersuche(reader.readIntString(pos + 0x2d, 1));
                    w.setTeilnehmerProStaffelMannschaft(reader.readIntString(pos + 0x2c, 1));
                    w.setStellplatzzeit(reader.readIntString(pos + 0x48, 3));
                    w.setPokalwertungsGruppe(reader.readIntString(pos + 0x2f, 2));

                    w.setAltersklasse(extractAltersklasse(reader.readAttribute(pos + 0x14c, 25)));

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

    private Altersklasse extractAltersklasse(String altersklasse) {
        altersklasse = altersklasse.trim();
        if (altersklasse.matches("Männer")) {
            return Altersklasse.MÄNNER;
        } else if (altersklasse.matches("Frauen")) {
            return Altersklasse.FRAUEN;
        } else if (altersklasse.matches("Senioren[A-Za-z0-9.ä ]*")) {
            int alter = Integer.parseInt(altersklasse.substring(10, 12));
            switch (alter) {
                case 30:
                    return Altersklasse.SENIOREN_M30;
                case 35:
                    return Altersklasse.SENIOREN_M35;
                case 40:
                    return Altersklasse.SENIOREN_M40;
                case 45:
                    return Altersklasse.SENIOREN_M45;
                case 50:
                    return Altersklasse.SENIOREN_M50;
                case 55:
                    return Altersklasse.SENIOREN_M55;
                case 60:
                    return Altersklasse.SENIOREN_M60;
                case 65:
                    return Altersklasse.SENIOREN_M65;
                case 70:
                    return Altersklasse.SENIOREN_M70;
                case 75:
                    return Altersklasse.SENIOREN_M75;
                case 80:
                    return Altersklasse.SENIOREN_M80;
                case 85:
                    return Altersklasse.SENIOREN_M85;
                case 90:
                    return Altersklasse.SENIOREN_M90;
            }
        } else if (altersklasse.matches("Seniorinnen[A-Za-z0-9.ä ]*")) {
            int alter = Integer.parseInt(altersklasse.substring(13, 15));
            switch (alter) {
                case 30:
                    return Altersklasse.SENIORINNEN_W30;
                case 35:
                    return Altersklasse.SENIORINNEN_W35;
                case 40:
                    return Altersklasse.SENIORINNEN_W40;
                case 45:
                    return Altersklasse.SENIORINNEN_W45;
                case 50:
                    return Altersklasse.SENIORINNEN_W50;
                case 55:
                    return Altersklasse.SENIORINNEN_W55;
                case 60:
                    return Altersklasse.SENIORINNEN_W60;
                case 65:
                    return Altersklasse.SENIORINNEN_W65;
                case 70:
                    return Altersklasse.SENIORINNEN_W70;
                case 75:
                    return Altersklasse.SENIORINNEN_W75;
                case 80:
                    return Altersklasse.SENIORINNEN_W80;
                case 85:
                    return Altersklasse.SENIORINNEN_W85;
                case 90:
                    return Altersklasse.SENIORINNEN_W90;
            }
        } else if (altersklasse.matches("Männliche[A-Za-z0-9 ]*")) {
            int alter = Integer.parseInt(altersklasse.substring(18));
            switch (alter) {
                case 8:
                    return Altersklasse.MK_U08;
                case 10:
                    return Altersklasse.MK_U10;
                case 12:
                    return Altersklasse.MK_U12;
                case 14:
                    return Altersklasse.MJ_U14;
                case 16:
                    return Altersklasse.MJ_U16;
                case 18:
                    return Altersklasse.MJ_U18;
                case 20:
                    return Altersklasse.MJ_U20;
            }
        } else if (altersklasse.matches("Weibliche[A-Za-z0-9 ]*")) {
            int alter = Integer.parseInt(altersklasse.substring(18));
            switch (alter) {
                case 8:
                    return Altersklasse.WK_U08;
                case 10:
                    return Altersklasse.WK_U10;
                case 12:
                    return Altersklasse.WK_U12;
                case 14:
                    return Altersklasse.WJ_U14;
                case 16:
                    return Altersklasse.WJ_U16;
                case 18:
                    return Altersklasse.WJ_U18;
                case 20:
                    return Altersklasse.WJ_U20;
            }
        } else if (altersklasse.matches("Jugend[A-Za-z0-9 ]*")) {
            altersklasse = altersklasse.substring(6);
            if (altersklasse.matches("M[0-9]{1,2}")) {
                int alter = Integer.parseInt(altersklasse.substring(1));
                switch (alter) {
                    case 3:
                        return Altersklasse.M3;
                    case 4:
                        return Altersklasse.M4;
                    case 5:
                        return Altersklasse.M5;
                    case 6:
                        return Altersklasse.M6;
                    case 7:
                        return Altersklasse.M7;
                    case 8:
                        return Altersklasse.M8;
                    case 9:
                        return Altersklasse.M9;
                    case 10:
                        return Altersklasse.M10;
                    case 11:
                        return Altersklasse.M11;
                    case 12:
                        return Altersklasse.M12;
                    case 13:
                        return Altersklasse.M13;
                    case 14:
                        return Altersklasse.M14;
                    case 15:
                        return Altersklasse.M15;
                }
            } else if (altersklasse.matches("W[0-9]{1,2}")) {
                int alter = Integer.parseInt(altersklasse.substring(1));
                switch (alter) {
                    case 3:
                        return Altersklasse.W3;
                    case 4:
                        return Altersklasse.W4;
                    case 5:
                        return Altersklasse.W5;
                    case 6:
                        return Altersklasse.W6;
                    case 7:
                        return Altersklasse.W7;
                    case 8:
                        return Altersklasse.W8;
                    case 9:
                        return Altersklasse.W9;
                    case 10:
                        return Altersklasse.W10;
                    case 11:
                        return Altersklasse.W11;
                    case 12:
                        return Altersklasse.W12;
                    case 13:
                        return Altersklasse.W13;
                    case 14:
                        return Altersklasse.W14;
                    case 15:
                        return Altersklasse.W15;
                }
            }
        } else if (altersklasse.matches("Junioren U23")) {
            return Altersklasse.M_U23;
        } else if (altersklasse.matches("Juniorinnen U23")) {
            return Altersklasse.W_U23;
        }
        return null;
    }
}
