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
import java.nio.charset.Charset;

/**
 *
 * @author ns130291
 */
public abstract class Reader {

    public abstract byte[] readBytes(long pos, int length) throws IOException;

    public String readAttribute(long pos, int length) throws IOException {
        return new String(readBytes(pos, length), Charset.forName("ISO-8859-1")).trim();
    }

    public byte readByte(long pos) throws IOException {
        return readBytes(pos, 1)[0];
    }

    public boolean[] readBooleans(long pos, int length) throws IOException {
        boolean[] result = new boolean[length];
        char[] split = readAttribute(pos, length).toCharArray();
        for (int i = 0; i < split.length; i++) {
            result[i] = (split[i] == '1');
        }
        return result;
    }

    public int readIntString(long pos, int length) throws IOException {
        String attribute = readAttribute(pos, length);
        if (attribute.equals("")) {
            return -1;
        } else {
            return Integer.parseInt(attribute);
        }
    }
    
    public abstract long length() throws IOException;
}
