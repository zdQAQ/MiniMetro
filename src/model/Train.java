package model;

import javafx.Controller;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

import static model.Position.angle;


public class Train {
    private int nextPointIndex; // +2 if at a station to know the next station
    private java.util.List<Client> clientList;
    private java.util.List<Wagon> wagonList;
    //private int numberWagon ;
    private Line line ;
    private boolean direction ;
    private int capacity;


    public Train (int index, Line l, boolean dir) {
        nextPointIndex = index;
        line = l;
        direction = dir;
        clientList = new ArrayList<>();
        wagonList = new ArrayList<>();
        capacity = 6;
    }

    public boolean isFull() {
        if(clientList.size()<this.capacity){
            return false;
        } else {
            return true;
        }
    }

    public void setCapacity(int c){
        this.capacity = c;
    }

    public void setDirection(boolean dir) {
    	direction = dir;
    }

    public boolean getDirection() {
        return direction;
    }


    public int getNextPointIndex() {
        return nextPointIndex;
    }

    public void setNextPointIndex(int index) {
        nextPointIndex = index;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public Station nextStation() {
        verif();
        if(direction)
            return line.getStationList().get((nextPointIndex+2)/2);
        return line.getStationList().get((nextPointIndex-2)/2);
    }

    public Station currentStation() {
        verif();
        return line.getStationList().get(nextPointIndex/2);
    }

    public Line getLine() {
        return line;
    }
    public void setLine(Line l) {
        line = l;
    }

    public java.util.List<Wagon> getWagonList(){return wagonList;}

    public void addWagon(Wagon added) {
        wagonList.add(added);

    }


    public void changeLine (Position newPosition,Line newLine) {
        line.removeTrain(this);
        newLine.addTrain(this);
        setNextPointIndex(nextPointIndex/2);
        verif();
    }

    public void setPosition(Position pos) {

    }
    
    public void addClient(Client client) {
        //Add client to train
        if(clientList.size()<Game.vehicleCapacity)
            clientList.add(client);
        else{
            for (Wagon current : wagonList){
                if(!current.isFull()) {
                    current.addClient(client);
                    return;
                }
            }
        }
    }




    public void removeClient(Client client) {

        if(clientList.contains(client))
            clientList.remove(client);
        else {
            for (Wagon current : wagonList) {
                if (current.getClientList().contains(client)) {
                    current.removeClient(client);
                    return;
                }
            }
        }
    }



    public void stopAtStation () {

        if(line == null)
            return;

        /* Makes the clients to get off the train */
        for(int i = 0;i<clientList.size();++i) {
            Client client = clientList.get(i);
            if(client.willGetOff(this)) {
                --i;
            }
        }
        /* Makes the clients of the current station try to board in */
        Station station = currentStation();

        for(int i = 0; i<station.getClientList().size();++i) {
            Client cl = station.getClientList().get(i);
            if(cl.tryBoarding(this)) {
                --i;
                Controller.gameView.remove(cl);
                Controller.gameView.addClientToTrain(this,cl);
            }
        }
        move();
    }



    public void move () {

        if(line == null)
            return;

        /* avoids stopping at the middle of a line*/
        if(  nextPointIndex != line.getPath().size()-1 && nextPointIndex%2 == 0) {
            if( direction && angle(line.getPath().get(nextPointIndex),line.getPath().get(nextPointIndex+1))
                    == angle(line.getPath().get(nextPointIndex+1),line.getPath().get(nextPointIndex+2))) {
                ++nextPointIndex;
                verif();
            }
        }
        if(  nextPointIndex != 0 && nextPointIndex%2 == 0) {
            if( !direction && angle(line.getPath().get(nextPointIndex),line.getPath().get(nextPointIndex-1))
                    == angle(line.getPath().get(nextPointIndex-1),line.getPath().get(nextPointIndex-2))) {
                --nextPointIndex;
                verif();
            }
        }

        /* moving the train index on his line */
        if(direction)
            ++nextPointIndex;
        else
            --nextPointIndex;

        if(direction) {
            /* checking for the end of the line and if the line is a loop */
            if(nextPointIndex == line.getPath().size()-1 && line.isLoop())
                nextPointIndex = 0;
            else if(nextPointIndex >= line.getPath().size()-1) {
                direction = false;
                nextPointIndex = line.getPath().size()-1;
            }
        }
        else {
            if(nextPointIndex == 0 && line.isLoop())
                nextPointIndex = line.getPath().size()-1;
            else if(nextPointIndex == 0)
                direction = true;
        }
        Controller.gameView.move(this);
    }



    public void verif() {
        if(nextPointIndex > line.getPath().size()-1) {
            if(direction) {
                nextPointIndex = line.getPath().size()-2;
            }
            else {
                nextPointIndex = line.getPath().size()-1;
            }
        }

        if(nextPointIndex < 0) {
            nextPointIndex = 0;
        }

        if(direction) {
            /* checking for the end of the line and if the line is a loop */
            if(nextPointIndex == line.getPath().size()-1 && line.isLoop())
                nextPointIndex = 0;
            else if(nextPointIndex >= line.getPath().size()-1) {
                direction = false;
                nextPointIndex = line.getPath().size()-1;
            }
        }
        else {
            if(nextPointIndex == 0 && line.isLoop())
                nextPointIndex = line.getPath().size()-1;
            else if(nextPointIndex == 0)
                direction = true;
        }
    }
}
