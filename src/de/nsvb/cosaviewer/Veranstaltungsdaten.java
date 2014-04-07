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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
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
    private String ort;
    private String wettkampfstätte;
    private String datum;

    public void read(File file) {
        try {
            RandomAccessFile vFile = new RandomAccessFile(file, "r");

            name = readAttribute(vFile, 0x120, 90);
            kurzName = readAttribute(vFile, 0x17a, 40);
            
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
        return new String(readBuffer).trim();
    }
}
