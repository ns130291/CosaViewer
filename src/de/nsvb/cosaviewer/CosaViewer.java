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

import com.sun.nio.zipfs.ZipFileSystem;
import com.sun.nio.zipfs.ZipFileSystemProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author ns130291
 */
public class CosaViewer {

    private static FileSystemProvider getZipFSProvider() {
        for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
            if ("jar".equals(provider.getScheme())) {
                return provider;
            }
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("CosaViewer (C) 2014 ns130291");
        if (args.length > 0) {

            File file = new File(args[0]);
            String name = file.getName();
            if (name.substring(name.lastIndexOf('.') + 1).toLowerCase().equals("zip")) {
                System.out.println("Zip-Inhalt");

                try {
                    ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
                    ZipEntry entry;
                    while ((entry = zip.getNextEntry()) != null) {
                        if (entry.getSize() > -1 && entry.getSize() <= Integer.MAX_VALUE) {
                            byte data[] = new byte[(int) entry.getSize()];
                            String filename = entry.getName();
                            System.out.println("* " + filename);

                            readFileFromZip(filename, zip, data, (int) entry.getSize());
                        }else{
                            System.out.println("Fehler beim lesen der Zip-Datei");
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CosaViewer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CosaViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                switch (name) {
                    case "vandat.c01":
                        readVeranstaltungsdaten(file);
                        break;
                    case "Wettbew.c01":
                        readWettbewerbe(file);
                        break;
                }
            }
        }
    }

    private static void readVeranstaltungsdaten(File file) {
        Veranstaltungsdaten v = new Veranstaltungsdaten();
        v.read(file);
    }

    private static void readWettbewerbe(File file) {
        Wettbewerbe w = new Wettbewerbe();
        w.read(file);
    }

    private static void readVeranstaltungsdaten(ZipInputStream zip, byte[] data, int length) throws IOException {
        zip.read(data, 0, length);
        Veranstaltungsdaten v = new Veranstaltungsdaten();
        v.read(data);
    }

    private static void readWettbewerbe(ZipInputStream zip, byte[] data, int length) throws IOException {
        zip.read(data, 0, length);
        Wettbewerbe w = new Wettbewerbe();
        w.read(data);
    }

    private static void readFileFromZip(String filename, ZipInputStream zip, byte[] data, int length) throws IOException {
        switch (filename) {
            case "vandat.c01":
                readVeranstaltungsdaten(zip, data, length);
                break;
            case "Wettbew.c01":
                readWettbewerbe(zip, data, length);
                break;
        }
//
    }
}
