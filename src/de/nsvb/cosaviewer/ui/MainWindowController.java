package de.nsvb.cosaviewer.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author ns130291
 */
public class MainWindowController implements Initializable {

    @FXML
    private ListView section;
    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SelectionModel sm = section.getSelectionModel();
        sm.selectFirst();
        try {
            Node n = FXMLLoader.load(getClass().getResource("VeranstaltungsScene.fxml"));
            content.getChildren().setAll(n);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        InvalidationListener listener = (Observable observable) -> {
            System.out.println(sm.getSelectedItem());
            try {
                Node n = FXMLLoader.load(getClass().getResource("VeranstaltungsScene.fxml"));
                content.getChildren().setAll(n);
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        sm.selectedIndexProperty().addListener(listener);
    }

}
