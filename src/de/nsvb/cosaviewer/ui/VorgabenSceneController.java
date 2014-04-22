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
import javafx.scene.control.ScrollPane;

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

    public void setData(Veranstaltungsdaten v){
        if(runningtrackFlach == null){//TODO: Remove
            //throw new NullPointerException("runningTrack ist null");
            System.out.println("runningTrack ist null");
            //runningtrack = new RunningTrack();
            //content.getChildren().add(runningtrack);
        }else{
            System.out.println("runningTrack ist da");
            runningtrackFlach.setTracks(v.getAnzahlBahnenFlachGerade(), v.getAnzahlBahnenFlachRund(), v.getGesperrtFlachGerade(), v.getGesperrtFlachRund());
        }
        runningtrackHürden.setTracks(v.getAnzahlBahnenHürdenGerade(), v.getAnzahlBahnenHürdenRund(), v.getGesperrtHürdenGerade(), v.getGesperrtHürdenRund());
        bahnenFlachGerade.setText(v.getAnzahlBahnenFlachGerade() + "");
        bahnenFlachRund.setText(v.getAnzahlBahnenFlachRund() + "");
        
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
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        content.setFitToWidth(true);
    }    
    
}
