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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 *
 * @author ns130291
 */
public class FileReader {

    public static String readAttribute(RandomAccessFile file, long pos, int length) throws IOException {
        byte[] readBuffer = new byte[length];
        file.seek(pos);
        file.read(readBuffer, 0, length);
        return new String(readBuffer, Charset.forName("ISO-8859-1")).trim();
    }

    public static byte[] readBytes(RandomAccessFile file, long pos, int length) throws IOException {
        byte[] readBuffer = new byte[length];
        file.seek(pos);
        file.read(readBuffer, 0, length);
        return readBuffer;
    }

    public static byte readByte(RandomAccessFile file, long pos) throws IOException {
        return readBytes(file, pos, 1)[0];
    }

    public static boolean[] readBooleans(RandomAccessFile file, long pos, int length) throws IOException {
        boolean[] result = new boolean[length];
        char[] split = readAttribute(file, pos, length).toCharArray();
        for (int i = 0; i < split.length; i++) {
            result[i] = (split[i] == '1');
        }
        return result;
    }

    public static int readIntString(RandomAccessFile file, long pos, int length) throws IOException {
        String attribute = readAttribute(file, pos, length);
        if (attribute.equals("")) {
            return -1;
        } else {
            return Integer.parseInt(attribute);
        }
    }
}
