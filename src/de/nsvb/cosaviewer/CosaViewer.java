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

import com.sun.jndi.toolkit.url.Uri;
import java.io.File;

/**
 *
 * @author ns130291
 */
public class CosaViewer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("CosaViewer (C) 2014 ns130291");
        if (args.length > 0) {

            File file = new File(args[0]);

            switch (file.getName()) {
                case "vandat.c01":
                    readVeranstaltungsdaten(file);
                    break;
            }
        }
    }

    private static void readVeranstaltungsdaten(File file) {
        Veranstaltungsdaten v = new Veranstaltungsdaten();
        v.read(file);
        
    }
}
