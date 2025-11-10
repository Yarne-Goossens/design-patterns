package be.ucll.crafsmanship.command.gamecontroller;

public class PS5Controller {
    Command crossButton;
    Command squareButton;
    Command circleButton;
    Command triangleButton;

    public PS5Controller() {}

    public void setCrossButton(Command crossButton) {
        this.crossButton = crossButton;
    }

    public void setSquareButton(Command squareButton) {
        this.squareButton = squareButton;
    }

    public void setCircleButton(Command circleButton) {
        this.circleButton = circleButton;
    }

    public void setTriangleButton(Command triangleButton) {
        this.triangleButton = triangleButton;
    }

    public void pressCross() {
        crossButton.execute();
    }

    public void pressTriangle() {
        triangleButton.execute();
    }

    public void pressSquare() {
        squareButton.execute();
    }

    public void pressCircle() {
        circleButton.execute();
    }
}
