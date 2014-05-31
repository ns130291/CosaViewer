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
public enum Altersklasse {

    MÄNNER("Männer", SubAltersklasse.AKTIVE, Geschlecht.MÄNNLICH),
    FRAUEN("Frauen", SubAltersklasse.AKTIVE, Geschlecht.WEIBLICH),
    M_U23("Junioren U23", SubAltersklasse.AKTIVE, Geschlecht.MÄNNLICH),
    W_U23("Juniorinnen U23", SubAltersklasse.AKTIVE, Geschlecht.WEIBLICH),
    MJ_U20("Männliche Jugend U20", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    WJ_U20("Weibliche Jugend U20", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    MJ_U18("Männliche Jugend U18", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    WJ_U18("Weibliche Jugend U18", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    MJ_U16("Männliche Jugend U16", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    WJ_U16("Weibliche Jugend U16", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    MJ_U14("Männliche Jugend U14", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    WJ_U14("Weibliche Jugend U14", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    MK_U12("Männliche Kinder U12", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    WK_U12("Weibliche Kinder U12", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    MK_U10("Männliche Kinder U10", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    WK_U10("Weibliche Kinder U10", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    MK_U08("Männliche Kinder U08", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    WK_U08("Weibliche Kinder U08", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M3("Kinder M03", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W3("Kinder W03", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M4("Kinder M04", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W4("Kinder W04", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M5("Kinder M05", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W5("Kinder W05", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M6("Kinder M06", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W6("Kinder W06", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M7("Kinder M07", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W7("Kinder W07", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M8("Kinder M08", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W8("Kinder W08", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M9("Kinder M09", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W9("Kinder W09", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M10("Kinder M10", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W10("Kinder W10", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M11("Kinder M11", SubAltersklasse.KINDER, Geschlecht.MÄNNLICH),
    W11("Kinder W11", SubAltersklasse.KINDER, Geschlecht.WEIBLICH),
    M12("Jugend M12", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    W12("Jugend W12", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    M13("Jugend M13", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    W13("Jugend W13", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    M14("Jugend M14", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    W14("Jugend W14", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    M15("Jugend M15", SubAltersklasse.JUGEND, Geschlecht.MÄNNLICH),
    W15("Jugend W15", SubAltersklasse.JUGEND, Geschlecht.WEIBLICH),
    SENIOREN_M30("Senioren M30", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W30("Seniorinnen W30", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M35("Senioren M35", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W35("Seniorinnen W35", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M40("Senioren M40", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W40("Seniorinnen W40", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M45("Senioren M45", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W45("Seniorinnen W45", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M50("Senioren M50", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W50("Seniorinnen W50", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M55("Senioren M55", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W55("Seniorinnen W55", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M60("Senioren M60", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W60("Seniorinnen W60", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M65("Senioren M65", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W65("Seniorinnen W65", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M70("Senioren M70", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W70("Seniorinnen W70", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M75("Senioren M75", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W75("Seniorinnen W75", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M80("Senioren M80", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W80("Seniorinnen W80", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M85("Senioren M85", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W85("Seniorinnen W85", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIOREN_M90("Senioren M90", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    SENIORINNEN_W90("Seniorinnen W90", SubAltersklasse.SENIOREN, Geschlecht.MÄNNLICH),
    ;

    private String name;
    private SubAltersklasse sub;
    private Geschlecht g;

    private Altersklasse(String name, SubAltersklasse sub, Geschlecht g) {
        this.name = name;
        this.sub = sub;
        this.g = g;
    }

    @Override
    public String toString() {
        return name;
    }

}
