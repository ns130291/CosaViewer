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

package de.nsvb.cosaviewer.ui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ns130291
 */
public final class SectionItem {
    
    public static final int VERANSTALTUNG = 1;
    public static final int VORGABEN = 2;
    public static final int WETTBEWERBE = 3;
    public static final int TEILNEHMER = 4;
    public static final int VEREINE = 5;
    public static final int DRUCKOPTIONEN = 6;

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    
    public SectionItem(){
        
    }
    
    public SectionItem(int id, String name){
        this.id.set(id);
        this.name.set(name);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
    
    public IntegerProperty idProperty(){
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty(){
        return name;
    }
    
    @Override
    public String toString() {
        return name.get(); //To change body of generated methods, choose Tools | Templates.
    }
}
