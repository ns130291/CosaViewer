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
import java.util.Arrays;

/**
 *
 * @author ns130291
 */
public class ByteArrayReader extends Reader{
    
    byte[] file;
    
    public ByteArrayReader(byte[] file){
        this.file = file;
    }

    @Override
    public byte[] readBytes(long pos, int length) throws IOException {
        if(pos >= file.length){
            return new byte[]{};
        }
        int length2 = length;
        if(pos + length >= file.length){
            length2 = (int) pos + length - file.length;
        }
        //System.out.println("pos " + pos + " length " + length + " file.length " + file.length + " new length " + length2);
        return Arrays.copyOfRange(file, (int) pos, (int) pos + length2);
    }

    @Override
    public long length() throws IOException {
        return file.length;
    }
    
}
