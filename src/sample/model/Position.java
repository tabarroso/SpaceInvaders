package sample.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Position {
    private DoubleProperty xPosition = new SimpleDoubleProperty();
    private DoubleProperty yPosition = new SimpleDoubleProperty();

    public double getxPosition() {
        return xPosition.get();
    }

    public DoubleProperty xPositionProperty() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition.set(xPosition);
    }

    public double getyPosition() {
        return yPosition.get();
    }

    public DoubleProperty yPositionProperty() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition.set(yPosition);
    }
}