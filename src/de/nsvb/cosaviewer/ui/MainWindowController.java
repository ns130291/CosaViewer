package de.nsvb.cosaviewer.ui;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ns130291
 */
public class MainWindowController implements Initializable {

    private FileChooser fileChooser = new FileChooser();

    @FXML
    private ListView section;
    @FXML
    private AnchorPane content;

    @FXML
    private void handleOpen(ActionEvent event) {
        System.out.println("Öffnen");
        fileChooser.showOpenDialog((Stage) content.getScene().getWindow());
    }

    @FXML
    private void handleClose(ActionEvent event) {
        System.out.println("Beenden");
        Stage stage = (Stage) content.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Node n = FXMLLoader.load(getClass().getResource("VeranstaltungsScene.fxml"));
            content.getChildren().setAll(n);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SelectionModel sm = section.getSelectionModel();
        sm.selectFirst();
        InvalidationListener listener = (Observable observable) -> {
            System.out.println(sm.getSelectedItem());
            /*try {
             Node n = FXMLLoader.load(getClass().getResource("VeranstaltungsScene.fxml"));
             content.getChildren().setAll(n);
             } catch (IOException ex) {
             Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
             }*/
        };
        sm.selectedIndexProperty().addListener(listener);

        fileChooser.setTitle("COSA Zip-Datei öffnen");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("COSA Zip", "*.zip")
        );
    }

}
