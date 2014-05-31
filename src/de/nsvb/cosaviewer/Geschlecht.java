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

/**
 *
 * @author ns130291
 */
public enum Geschlecht {
    WEIBLICH("weiblich", "W"),
    MÄNNLICH("männlich", "M");
    
    private String name;
    private String kürzel;   

    private Geschlecht(String name, String kürzel) {
        this.name = name;
        this.kürzel = kürzel;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
