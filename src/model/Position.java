package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Position {
    private IntegerProperty xPosition = new SimpleIntegerProperty();
    private IntegerProperty yPosition = new SimpleIntegerProperty();

    public int getxPosition() {
        return xPosition.get();
    }

    public IntegerProperty xPositionProperty() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition.set(xPosition);
    }

    public int getyPosition() {
        return yPosition.get();
    }

    public IntegerProperty yPositionProperty() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition.set(yPosition);
    }
}
