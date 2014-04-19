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
import de.nsvb.cosaviewer.CosaViewer;
import de.nsvb.cosaviewer.Veranstaltungsdaten;
import de.nsvb.cosaviewer.Wettbewerbe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ns130291
 */
public class MainWindowController implements Initializable {

    private FileChooser fileChooser = new FileChooser();
    private DirectoryChooser directoryChooser = new DirectoryChooser();
    private Wettbewerbe wettbewerbe;
    private Veranstaltungsdaten veranstaltungsdaten;
    private File previousOpen;
    private File previousOpenDirectory;
    private int selection = 1;

    private void sectionVeranstaltungsdaten() {
        if (veranstaltungsdaten != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VeranstaltungsScene.fxml"));
                Node n = loader.load();
                VeranstaltungsSceneController controller = loader.<VeranstaltungsSceneController>getController();
                if (veranstaltungsdaten != null) {
                    controller.setData(veranstaltungsdaten);
                }
                content.getChildren().setAll(n);
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void sectionVorgaben() {
        if (veranstaltungsdaten != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VorgabenScene.fxml"));
                Node n = loader.load();
                VorgabenSceneController controller = loader.<VorgabenSceneController>getController();
                if (veranstaltungsdaten != null) {
                    controller.setData(veranstaltungsdaten);
                }
                content.getChildren().setAll(n);
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void readVeranstaltungsdaten(ZipInputStream zip, byte[] data, int length) throws IOException {
        zip.read(data, 0, length);
        veranstaltungsdaten = new Veranstaltungsdaten();
        veranstaltungsdaten.read(data);
    }

    private void readWettbewerbe(ZipInputStream zip, byte[] data, int length) throws IOException {
        zip.read(data, 0, length);
        wettbewerbe = new Wettbewerbe();
        wettbewerbe.read(data);
    }

    private void readFileFromZip(String filename, ZipInputStream zip, byte[] data, int length) throws IOException {
        switch (filename) {
            case "vandat.c01":
                readVeranstaltungsdaten(zip, data, length);
                break;
            case "Wettbew.c01":
                readWettbewerbe(zip, data, length);
                break;
        }
    }

    private void readVeranstaltungsdaten(File file) {
        veranstaltungsdaten = new Veranstaltungsdaten();
        veranstaltungsdaten.read(file);
    }

    private void readWettbewerbe(File file) {
        wettbewerbe = new Wettbewerbe();
        wettbewerbe.read(file);
    }

    @FXML
    private ListView section;
    @FXML
    private AnchorPane content;

    @FXML
    private void handleOpen(ActionEvent event) {
        System.out.println("Datei öffnen");
        if (previousOpen != null) {
            fileChooser.setInitialDirectory(previousOpen);
        }
        File file = fileChooser.showOpenDialog((Stage) content.getScene().getWindow());
        if (file != null) {
            previousOpen = file.getParentFile();
            String name = file.getName();
            if (name.substring(name.lastIndexOf('.') + 1).toLowerCase().equals("zip")) {
                System.out.println("Zip-Inhalt");

                try {
                    ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
                    ZipEntry entry;
                    while ((entry = zip.getNextEntry()) != null) {
                        if (entry.getSize() > -1 && entry.getSize() <= Integer.MAX_VALUE) {
                            byte data[] = new byte[(int) entry.getSize()];
                            String filename = entry.getName();
                            System.out.println("* " + filename);

                            readFileFromZip(filename, zip, data, (int) entry.getSize());
                        } else {
                            System.out.println("Fehler beim Lesen der Zip-Datei");
                        }
                    }

                    sectionVeranstaltungsdaten();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Not supported file format");
            }
        } else {
            System.out.println("Auswahl abgebrochen");
        }
    }

    @FXML
    private void handleOpenFolder(ActionEvent event) {
        System.out.println("Ordner öffnen");
        if (previousOpenDirectory != null) {
            directoryChooser.setInitialDirectory(previousOpenDirectory);
        }
        File directory = directoryChooser.showDialog((Stage) content.getScene().getWindow());
        if (directory != null) {
            previousOpenDirectory = directory;
            for (File file : directory.listFiles()) {
                switch (file.getName()) {
                    case "vandat.c01":
                        readVeranstaltungsdaten(file);
                        break;
                    case "Wettbew.c01":
                        readWettbewerbe(file);
                        break;
                }
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VeranstaltungsScene.fxml"));
                Node n = loader.load();
                VeranstaltungsSceneController controller = loader.<VeranstaltungsSceneController>getController();
                if (veranstaltungsdaten != null) {
                    controller.setData(veranstaltungsdaten);
                }
                content.getChildren().setAll(n);
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Auswahl abgebrochen");
        }
    }

    @FXML
    private void handleClose(ActionEvent event) {
        System.out.println("Beenden");
        Stage stage = (Stage) content.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        System.out.println("Über");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Node n = FXMLLoader.load(getClass().getResource("EmptyScene.fxml"));
            content.getChildren().setAll(n);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SelectionModel sm = section.getSelectionModel();
        sm.selectFirst();
        InvalidationListener listener = (Observable observable) -> {
            System.out.println(sm.getSelectedItem() + " " + ((SectionItem) sm.getSelectedItem()).getId());
            int sel = ((SectionItem) sm.getSelectedItem()).getId();
            if (sel != selection) {
                selection = sel;
                switch (sel) {
                    case SectionItem.VERANSTALTUNG:
                        sectionVeranstaltungsdaten();
                        break;
                    case SectionItem.VORGABEN:
                        sectionVorgaben();
                        break;
                }
            }
        };
        sm.selectedIndexProperty().addListener(listener);

        fileChooser.setTitle("COSA Zip-Datei öffnen");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("COSA Zip", "*.zip")
        );

        directoryChooser.setTitle("COSA Veranstaltungsordner öffnen");
    }

}
