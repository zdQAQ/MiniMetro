package javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
public class fxTimer extends Group {

    private Timeline animation;
    private String S = "";
    private int tmp = 1200;

    Label label = new Label("倒计时 20:00");


    public fxTimer() {
    	super();
        label.setFont(javafx.scene.text.Font.font(20));

        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void timelabel() {
        tmp--;
        S = "倒计时 " + tmp/60 + ":" + tmp%60;
        label.setText(S);
    }

}