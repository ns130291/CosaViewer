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

/**
 *
 * @author ns130291
 */
public class RandomFileReader extends Reader{
    
    private RandomAccessFile file;
    
    public RandomFileReader(RandomAccessFile file){
        this.file = file;
    }

    @Override
    public byte[] readBytes(long pos, int length) throws IOException {
        byte[] readBuffer = new byte[length];
        file.seek(pos);
        file.read(readBuffer, 0, length);
        return readBuffer;
    }

    @Override
    public long length() throws IOException {
        return file.length();
    }
}
