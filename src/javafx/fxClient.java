package javafx;

import javafx.scene.shape.Shape;
import model.Client;
import model.Position;
import model.Station;

import static javafx.defaultShapes.*;

import java.util.List;

public class fxClient {
    Shape shape;

    public fxClient(Client c) {
        switch(c.getType()) {
            case CIRCLE: shape = getLittleCircle(); break;
            case SQUARE: shape = getLittleSquare(); break;
            case TRIANGLE: shape = getLittleTriangle() ; break;
            case STAR: shape = getLittleStar(); break;
            case CROSS: shape = getLittleCross(); break;
            case SECTOR: shape = getLittleSector();break;
            case DIAMOND: shape = getLittleDiamond();break;
            case PENTAGON: shape = getLittlePentagon();break;
            default: shape = null;
        }

        Station st = c.getStation();
        int index = st.getClientList().size();
        Position p = new Position(st.getPosition().getX()+10+index*12,st.getPosition().getY()-10);
        defaultShapes.setPosition(shape,p);

        System.out.println("clientSize:"+index);

    }

    public fxClient(Shape s) {
        shape = s;
    }


    public void updatePos(Station st,int index) {
        Position p = new Position(st.getPosition().getX()+10+index*12,st.getPosition().getY()-10);
        defaultShapes.setPosition(shape,p);
    }
}
