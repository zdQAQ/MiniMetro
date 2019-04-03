package javafx;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import model.Client;
import model.Event;
import model.Game;
import model.Position;
import model.Schedule;
import model.Station;
import model.Train;

import static java.awt.geom.Point2D.distance;
import static javafx.defaultShapes.*;
import static javafx.defaultShapes.getCross;
import static model.Position.angle;

/**
 * Created by KadirF on 18/12/2016.
 */
public class fxTrain extends Group {

    static final double clientScale = 0.25;
    private double width = 25;
    private double height = 13;
    TranslateTransition move;
    TranslateTransition wagonAnimation;

    double trainX, trainY;
    Rectangle r;
    Train train;

    private Boolean isWagon = false;

    public fxTrain(Train t) {
        super();
        Position p = t.getLine().getPath().get(0);
        trainX = p.getX();
        trainY = p.getY();
        r = new Rectangle(p.getX() - width / 2, p.getY() - (height / 2), width, height);
        r.setFill(t.getLine().getColor());
        getChildren().add(r);
        train = t;
    }

    public fxTrain(Train t, Position p) {
        super();
        trainX = p.getX();
        trainY = p.getY();
        r = new Rectangle(p.getX() - width / 2, p.getY() - (height / 2), width, height);
        r.setFill(t.getLine().getColor());
        getChildren().add(r);
        train = t;
    }

    public fxTrain(Train t, Boolean wagon) {
        if (wagon == true) {
            this.isWagon = true;
            this.width = this.width * 2;
            t.setCapacity(12);
            Position p = t.getLine().getPath().get(0);
            trainX = p.getX();
            trainY = p.getY();
            r = new Rectangle(p.getX() - width / 2, p.getY() - (height / 2), width, height);
            r.setFill(t.getLine().getColor());
            getChildren().add(r);
            train = t;
        }
    }

    public void move(Position p, int speed) {
        // System.err.println("trainX,trainY:("+trainX+","+trainY+")");
        if (speed == 0) {
            System.err.println("SPEED == 0 : TRAIN STOP");
            return;
        }
        double rotation = angle(p, new Position(trainX, trainY)), x = p.getX(), y = p.getY();
        setRotate(rotation);
        /* +100 to avoid bug with a duration equal to 0 with the translate transition */
        double millis = 10 * distance(trainX, trainY, x, y);
        move = new TranslateTransition(new Duration(millis), this);
        move.setByX(x - trainX);
        move.setByY(y - trainY);

        int nextPointIndex = train.getNextPointIndex();

        for (Event eve : Schedule.events) {
            if (eve.getPosition().getX() == trainX && eve.getPosition().getY() == trainY) {
                move.setDelay(new Duration(1000));
                break;
            }
        }

        move.play();

        move.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nextPointIndex % 2 == 0) {
                    train.stopAtStation();
                } else {
                    train.move();
                }
            }
        });

        trainX = x;
        trainY = y;

    }

    public void addClient(Shape s) {

        s.setScaleX(clientScale);
        s.setScaleY(clientScale);

        int index = this.getChildren().size() - 1;

        double tempX = trainX, tempY = trainY;

        Rectangle r = (Rectangle) getChildren().get(0);

        double dx = r.getX() + (width / 4) + (index % 3) * (width / 4);
        double dy = r.getY() + (height / 4) + (index / 3) * (height / 2);

        s.setLayoutX(dx);
        s.setLayoutY(dy);

        s.setFill(Color.WHITE);
        s.setStroke(Color.WHITE);

        getChildren().add(s);
        // Controller.group2.getChildren().add(s);
        // System.err.println(s);
        trainX = tempX;
        trainY = tempY;
    }

    public Shape addClient(Client cl) {
        Shape shape;

        int index = this.getChildren().size() - 1;
        Rectangle r = (Rectangle) getChildren().get(0);
        double dx = 0;
        double dy = 0;
        if (this.isWagon) {
            dx = r.getX() + (width / 8) + (index % 6) * (width / 8);
            dy = r.getY() + (height / 4) + (index / 6) * (height / 2);
        } else {
            dx = r.getX() + (width / 4) + (index % 3) * (width / 4);
            dy = r.getY() + (height / 4) + (index / 3) * (height / 2);
        }

        switch (cl.getType()) {
        case CIRCLE:
            Circle c = getCircle();
            c.setCenterX(dx);
            c.setCenterY(dy);
            shape = c;
            break;
        case SQUARE:
            Polygon square = getSquare();
            for (int i = 0; i < square.getPoints().size(); i += 2) {
                double tempX = square.getPoints().get(i);
                double tempY = square.getPoints().get(i + 1);
                square.getPoints().set(i, dx + tempX);
                square.getPoints().set(i + 1, dy + tempY);
            }
            shape = square;
            break;
        case TRIANGLE:
            Polygon p = getTriangle();
            for (int i = 0; i < p.getPoints().size(); i += 2) {
                double tempX = p.getPoints().get(i);
                double tempY = p.getPoints().get(i + 1);
                p.getPoints().set(i, dx + tempX);
                p.getPoints().set(i + 1, dy + tempY);
            }
            shape = p;
            break;
        case STAR:
            Polygon star = getStar();
            for (int i = 0; i < star.getPoints().size(); i += 2) {
                double tempX = star.getPoints().get(i);
                double tempY = star.getPoints().get(i + 1);
                star.getPoints().set(i, dx + tempX);
                star.getPoints().set(i + 1, dy + tempY);
            }
            shape = star;
            break;
        case CROSS:
            Polygon cross = getCross();
            for (int i = 0; i < cross.getPoints().size(); i += 2) {
                double tempX = cross.getPoints().get(i);
                double tempY = cross.getPoints().get(i + 1);
                cross.getPoints().set(i, dx + tempX);
                cross.getPoints().set(i + 1, dy + tempY);
            }
            shape = cross;
            break;
        case SECTOR:
            Arc arc = getSector();
            arc.setCenterX(dx);
            arc.setCenterY(dy);
            shape = arc;
            break;
        case DIAMOND:
            Polygon diamond = getDiamond();
            for (int i = 0; i < diamond.getPoints().size(); i += 2) {
                double tempX = diamond.getPoints().get(i);
                double tempY = diamond.getPoints().get(i + 1);
                diamond.getPoints().set(i, dx + tempX);
                diamond.getPoints().set(i + 1, dy + tempY);
            }
            shape = diamond;
            break;
        case PENTAGON:
            Polygon pentagon = getPentagon();
            for (int i = 0; i < pentagon.getPoints().size(); i += 2) {
                double tempX = pentagon.getPoints().get(i);
                double tempY = pentagon.getPoints().get(i + 1);
                pentagon.getPoints().set(i, dx + tempX);
                pentagon.getPoints().set(i + 1, dy + tempY);
            }
            shape = pentagon;
            break;

        default:
            shape = null;
        }

        shape.setScaleX(clientScale);
        shape.setScaleY(clientScale);

        shape.setFill(Color.WHITE);
        shape.setStroke(Color.WHITE);
        getChildren().add(shape);
        return shape;
        // Controller.group2.getChildren().add(s);

    }

    public void removeClient(Shape s) {
        getChildren().remove(s);
        // System.err.println("REMOVAL sucess ? : "+getChildren().remove(s));
    }

    public void pause() {
        move.pause();
    }

    public void resume() {

        move.play();
    }
}
