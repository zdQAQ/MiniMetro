package javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class fxTimer extends Group {

    private Timeline animation;
    private String S;

    private Label label;
    private int seconds;
    private Runnable cb;

    public fxTimer(int minutes) {
        super();
        seconds = minutes * 60;
        label = new Label("倒计时 " + minutes + ":00");
        label.setFont(Font.font(20));

        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> timelabel()));
        animation.setCycleCount(seconds);
        animation.play();
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Platform.runLater(cb);
            }
        });
    }

    public void setCallBack(Runnable callBack) {
        cb = callBack;
    }

    public void timelabel() {
        seconds--;
        S = "倒计时 " + seconds / 60 + ":" + seconds % 60;
        label.setText(S);
    }

}