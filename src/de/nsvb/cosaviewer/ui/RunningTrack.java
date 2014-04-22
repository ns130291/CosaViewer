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

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author ns130291
 */
public class RunningTrack extends Canvas{
    public RunningTrack(){
        super(300, 250);
    }
    
    public void setTracks(int bahnenGerade, int bahnenRund, boolean[] gesperrtGerade, boolean[] gesperrtRund){
        GraphicsContext gc = getGraphicsContext2D();
        
        //TODO: gesperrte Bahnen
        
        gc.setFill(Color.GREY);
        gc.fillRect(0, 0, 1000, 1000);

        //sonst wirds hässlich mit dem Rechnen!
        gc.setLineCap(StrokeLineCap.BUTT);
        double x = 0;
        double y = 10;

        double k = 10;
        double laneWidth = 1.22 * k;
        double radius = 36.5 * k;
        double length = 84.39 * k;
        double length_line = 113 * k - length;
        double hundred_meter = 100.0 * k - length;
        double hundred_ten_meter = 110.0 * k - length;

        double lineWidth = 0.05 * k;
        if (laneWidth < 1) {
            lineWidth = 1;
        }

        //Bahnen
        gc.setLineWidth(laneWidth);
        gc.setStroke(Color.DARKRED);
        //gc.setStroke(Color.BLUE);
        for (int i = 1; i <= bahnenGerade; i++) {
            //obere Gerade
            double y_line = y + laneWidth / 2 + laneWidth * (10 - i);
            gc.strokeLine(x, y_line, x + length_line, y_line);

        }
        //gc.setStroke(Color.DARKRED);
        for (int i = 1; i <= bahnenRund; i++) {
            //rechte Kurve
            double a = (radius + laneWidth * i - laneWidth / 2) * 2;
            gc.strokeArc(x - laneWidth * i - radius + laneWidth / 2, y + laneWidth / 2 + laneWidth * (10 - i), a, a, 0, 90, ArcType.OPEN);
        }

        //Linien
        gc.setLineWidth(lineWidth);
        gc.setStroke(Color.WHITE);
        double y_line = y + laneWidth * (10 - bahnenGerade);
        gc.strokeLine(x + hundred_meter, y_line, x + hundred_meter, y_line + laneWidth * bahnenGerade);
        gc.strokeLine(x + hundred_ten_meter, y_line, x + hundred_ten_meter, y_line + laneWidth * bahnenGerade);
        for (int i = 0; i <= bahnenGerade; i++) {
            //obere Gerade
            y_line = y + laneWidth * (10 - i);
            gc.strokeLine(x, y_line, x + hundred_ten_meter, y_line);
        }
        for (int i = 0; i <= bahnenRund; i++) {
            //rechte Kurve
            double a = (radius + laneWidth * i) * 2;
            gc.strokeArc(x - laneWidth * i - radius, y + laneWidth * (10 - i), a, a, 0, 90, ArcType.OPEN);
        }
    }
}
