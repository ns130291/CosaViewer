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

import de.nsvb.cosaviewer.Veranstaltungsdaten;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ns130291
 */
public class VeranstaltungsSceneController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label veranstalter;
    @FXML
    private Label ausrichter;
    @FXML
    private Label ort;
    @FXML
    private Label wettkampfstätte;
    @FXML
    private Label veranstaltungsnummer;
    @FXML
    private Label kurzname;
    @FXML
    private Label kategorie;
    @FXML
    private Label datum;

    public void setData(Veranstaltungsdaten v) {
        setText(name, v.getName());
        setText(veranstalter, v.getVeranstalter());
        setText(ausrichter, v.getAusrichter());
        setText(ort, v.getOrt());
        setText(wettkampfstätte, v.getWettkampfstätte());
        setText(veranstaltungsnummer, v.getVeranstaltungsNummer());
        setText(kurzname, v.getKurzName());
        setText(kategorie, v.getVeranstaltungsTyp().toString());
        setText(datum, createDatumString(v.getDatum1(), v.getDatum2(), v.getDatum3(), v.getDatum4(), v.getTage()));
    }

    private String createDatumString(String datum1, String datum2, String datum3, String datum4, boolean[] tage) {
        int t = 0;
        for (boolean tag : tage) {
            if (tag) {
                t++;
            }
        }
        //TODO aufeinanderfolgende Tage anders formatieren
        if (t == 1) {
            return datum1;
        } else if (t == 2) {
            return datum1 + " & " + datum2;
        } else if (t == 3) {
            return datum1 + ", " + datum2 + " & " + datum3;
        } else if (t == 4) {
            return datum1 + ", " + datum2 + ", " + datum3 + " & " + datum4;
        } else {
            return "";
        }
    }

    private void setText(Label l, String text) {
        if (text == null || text.equals("")) {
            l.setText("nicht gesetzt");
            l.getStyleClass().add("emptyLabel");
        } else {
            l.setText(text);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
