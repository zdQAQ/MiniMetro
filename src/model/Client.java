package model;

import javafx.Controller;

import static model.Game.addTransportedClient;

public class Client {
    private Station station = null;
    private ShapeType destinationType = null;

    public Client(Station st, ShapeType type) {
        station = st;
        destinationType = type;
        st.addClient(this);
    }

    public ShapeType getType() {
        return destinationType;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station st) {
        station = st;
    }

    public boolean tryBoarding(Train train) {
        if (train.isFull()) {
            return false;
        }
        /*
         * boards if the the line contains the type of the client or if the client will
         * get closer to his type
         */
        if (train.getLine().containsShape(destinationType)
                || train.nextStation().getMinDistance(destinationType) < train.currentStation()
                        .getMinDistance(destinationType)
                || (train.getLine().isLoop() && train.getLine().canGoToDestination(destinationType) && !train.currentStation().shouldTransferLine(destinationType,train.getLine()))) {
            station.removeClient(this);
            train.addClient(this);
            // If the station is actually not full and was full before
            if ((station.getClientList().size() <= station.getCapacity()) && station.getIsFull()) {
                station.decreaseFullTimer();
            }
            return true;
        }
        return false;

    }

    public boolean willGetOff(Train train) {
        /*
         * the client gets off the train if the current station is his type or if the
         * line doesn't bring him closer to a station of his type
         */
        if (train.currentStation().getType() == destinationType
                || ((!train.getLine().containsShape(destinationType) && train.nextStation()
                        .getMinDistance(destinationType) >= train.currentStation().getMinDistance(destinationType)))
                || (!train.getLine().containsShape(destinationType)
                        && train.currentStation().shouldTransferLine(destinationType,train.getLine()) && train.getLine().isLoop())) {
                            System.out.println(this);
            train.removeClient(this);
            station = train.currentStation();
            Controller.gameView.removeClientFromTrain(train, this);
            if (station.getType() == this.getType()) {
                // 真实到站
                addTransportedClient();
                Controller.gameView.updateNbClient();
                if (Controller.mapType == 0) {
                    Controller.game.setGift();
                }
                // System.err.println("Transported client ");
            } else {
                // 换乘
                station.addClient(this);
                Controller.gameView.put(this);
                // If the station is actually full and was not already full
                if ((station.getClientList().size() > station.getCapacity()) && !station.getIsFull()) {
                    station.startFullTimer();
                }
            }
            return true;
        }

        // If a wagon willswap, client will get off
        // for (Wagon wagon:train.getWagonList()){
        // if(wagon.getClientList().contains(this)){
        // wagon.removeClient(this);
        // station = train.currentStation();
        // station.addClient(this);
        // //If the station is actually full and was not already full
        // if( (station.getClientList().size() > station.getCapacity()) &&
        // !station.getIsFull()){
        // station.startFullTimer();
        // }
        // wagon.setWillSwap(false);
        // //...
        // return true;
        // }
        // }
        return false;
    }

    public String toString() {
        return "Client go to:" + destinationType;
    }
}
