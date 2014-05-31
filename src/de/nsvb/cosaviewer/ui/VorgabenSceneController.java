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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author ns130291
 */
public class VorgabenSceneController implements Initializable {

    @FXML
    private RunningTrack runningtrackFlach;
    @FXML
    private RunningTrack runningtrackHürden;
    @FXML
    private ScrollPane content;
    @FXML
    private Label erwEinzel;
    @FXML
    private Label erwStaffel;
    @FXML
    private Label erwMehr;
    @FXML
    private Label erwLauf;
    @FXML
    private Label erwDMM;
    @FXML
    private Label U20U18Einzel;
    @FXML
    private Label U20U18Staffel;
    @FXML
    private Label U20U18Mehr;
    @FXML
    private Label U20U18Lauf;
    @FXML
    private Label U20U18DMM;
    @FXML
    private Label U16U8Einzel;
    @FXML
    private Label U16U8Staffel;
    @FXML
    private Label U16U8Mehr;
    @FXML
    private Label U16U8Lauf;
    @FXML
    private Label U16U8DMM;
    @FXML
    private Label g3_4KampfU16U8;
    @FXML
    private Label g4_9KampfMJU16;
    @FXML
    private Label g4_7KampfWJU16;
    @FXML
    private Label g5_10KampfMJU20U18;
    @FXML
    private Label g4_7KampfWJU20U18;
    @FXML
    private Label bahnenFlachGerade;
    @FXML
    private Label bahnenFlachRund;
    @FXML
    private Label hürdenFlachGerade;
    @FXML
    private Label hürdenFlachRund;
    @FXML
    private Label aufschlagNachmeldung;
    @FXML
    private Label zusendungErgebnisliste;
    @FXML
    private Label freieStartposLauf;
    @FXML
    private Label freieStartposTechnik;
    @FXML
    private Label zeitnahme;
    @FXML
    private GridPane flach_gerade;
    @FXML
    private GridPane flach_rund;
    @FXML
    private GridPane hürden_gerade;
    @FXML
    private GridPane hürden_rund;
    @FXML
    private CheckBox nachmeldezeitraum;
    @FXML
    private CheckBox pokalwertung;
    @FXML
    private CheckBox altersklassenprüfung;
    @FXML
    private CheckBox wertungsgruppen;
    @FXML
    private CheckBox hallenveranstaltung;
    @FXML
    private CheckBox automQualiKennzSetzen;
    @FXML
    private CheckBox andruckKennz;
    @FXML
    private CheckBox bewertSenBereich;
    @FXML
    private CheckBox mitWettbewerbenDMMDAMM;
    @FXML
    private CheckBox winderfassungKleinerU16;
    @FXML
    private CheckBox altersklassenKleinerU10;
    @FXML
    private CheckBox gemischteWettbewerbe;

    public void setData(Veranstaltungsdaten v) {
        runningtrackFlach.setTracks(v.getAnzahlBahnenFlachGerade(), v.getAnzahlBahnenFlachRund(), v.getGesperrtFlachGerade(), v.getGesperrtFlachRund());
        runningtrackHürden.setTracks(v.getAnzahlBahnenHürdenGerade(), v.getAnzahlBahnenHürdenRund(), v.getGesperrtHürdenGerade(), v.getGesperrtHürdenRund());

        bahnenFlachGerade.setText(v.getAnzahlBahnenFlachGerade() + "");
        bahnenFlachRund.setText(v.getAnzahlBahnenFlachRund() + "");
        hürdenFlachGerade.setText(v.getAnzahlBahnenHürdenGerade() + "");
        hürdenFlachRund.setText(v.getAnzahlBahnenHürdenRund() + "");

        erwEinzel.setText(v.getOrgGebührErwEinzel());
        erwStaffel.setText(v.getOrgGebührErwStaffel());
        erwMehr.setText(v.getOrgGebührErwMehr());
        erwLauf.setText(v.getOrgGebührErwLauf());
        erwDMM.setText(v.getOrgGebührErwDMM());

        U20U18Einzel.setText(v.getOrgGebührU20U18Einzel());
        U20U18Staffel.setText(v.getOrgGebührU20U18Staffel());
        U20U18Mehr.setText(v.getOrgGebührU20U18Mehr());
        U20U18Lauf.setText(v.getOrgGebührU20U18Lauf());
        U20U18DMM.setText(v.getOrgGebührU20U18DMM());

        U16U8Einzel.setText(v.getOrgGebührU16U8Einzel());
        U16U8Staffel.setText(v.getOrgGebührU16U8Staffel());
        U16U8Mehr.setText(v.getOrgGebührU16U8Mehr());
        U16U8Lauf.setText(v.getOrgGebührU16U8Lauf());
        U16U8DMM.setText(v.getOrgGebührU16U8DMM());

        g3_4KampfU16U8.setText(v.getOrgGebühr3_4KampfU16U8());
        g4_9KampfMJU16.setText(v.getOrgGebühr4_9KampfMJU16());
        g4_7KampfWJU16.setText(v.getOrgGebühr4_7KampfWJU16());
        g5_10KampfMJU20U18.setText(v.getOrgGebühr5_10KampfMJU20U18());
        g4_7KampfWJU20U18.setText(v.getOrgGebühr4_7KampfWJU20U18());

        nachmeldezeitraum.setSelected(v.isBeginnNachmeldung());
        aufschlagNachmeldung.setText(v.getAufschlagNachmeldegebühren() + ((v.isAufschlagProzent()) ? " %" : ""));
        zusendungErgebnisliste.setText(v.getGebührZusendungErgebnisliste());

        freieStartposLauf.setText(v.getAnzahlFreieStartpositionenLauf() + "");
        freieStartposTechnik.setText(v.getAnzahlFreieStartpositionenTechnik() + "");
        
        zeitnahme.setText(v.isZeitnahmeElektronisch()?"elektronisch":"Handzeit");
        
        pokalwertung.setSelected(v.isPokalwertung());
        altersklassenprüfung.setSelected(v.isAltersklassenPrüfung());
        wertungsgruppen.setSelected(v.isAuswertungNachWertungsgruppen());
        hallenveranstaltung.setSelected(v.isHallenveranstaltung());
        automQualiKennzSetzen.setSelected(v.isAutomQualiKennzSetzen());
        andruckKennz.setSelected(v.isAndruckKennzBSGbeiBewertNachDLVMehrkampfabz());
        bewertSenBereich.setSelected(v.isBewertSenBereichAltersklassenfaktorenMehrkampf());
        mitWettbewerbenDMMDAMM.setSelected(v.isMitWettbewerbenDMMDAMM());
        winderfassungKleinerU16.setSelected(v.isWinderfassungKleinerU16());
        altersklassenKleinerU10.setSelected(v.isAltersklassenKleinerU10());
        gemischteWettbewerbe.setSelected(v.isGemischteWettbewerbe());

        gesperrteBahnen(flach_gerade, v.getAnzahlBahnenFlachGerade(), v.getGesperrtFlachGerade());
        gesperrteBahnen(flach_rund, v.getAnzahlBahnenFlachRund(), v.getGesperrtFlachRund());
        gesperrteBahnen(hürden_gerade, v.getAnzahlBahnenHürdenGerade(), v.getGesperrtHürdenGerade());
        gesperrteBahnen(hürden_rund, v.getAnzahlBahnenHürdenRund(), v.getGesperrtHürdenRund());
    }

    private void gesperrteBahnen(GridPane bahnen, int anzahlBahnen, boolean[] gesperrt) {
        boolean sperre = false;
        for (int i = 0; i < gesperrt.length && i < anzahlBahnen; i++) {
            Label num = new Label((i + 1) + "");
            bahnen.add(num, i, 0);
            GridPane.setHalignment(num, HPos.CENTER);
            if (gesperrt[i]) {
                sperre = true;
                Label x = new Label("x");
                bahnen.add(x, i, 1);
                GridPane.setHalignment(x, HPos.CENTER);
            }
        }
        if (!sperre) {
            Label nichtGesperrt = new Label("keine gesperrt");
            nichtGesperrt.setPadding(new Insets(0, 0, 0, 5));
            //nichtGesperrt.setStyle(null);
            bahnen.add(nichtGesperrt, 0, 1, 10, 1);
            //GridPane.setHalignment(nichtGesperrt, HPos.CENTER);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        content.setFitToWidth(true);
    }

}
