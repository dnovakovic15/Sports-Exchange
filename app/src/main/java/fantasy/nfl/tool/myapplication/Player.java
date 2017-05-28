package fantasy.nfl.tool.myapplication;

import java.io.Serializable;

/**
 * Created by Darko on 10/14/2016.
 */

class Player implements Serializable {
    private String name, position, team, game;
    private double price, ppg, projectedPoints;
    private int id;


    public Player(String name, double projectedPoints, int price){
        this.name = name;
        this.projectedPoints = projectedPoints;
        this.price = price;
    }

    public Player(String name, double price, double ppg, String game, String team, String position){
        this.name = name;
        this.price = price;
        this.team = team;
        this.ppg = ppg;
        this.game = game;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {return name;}

    public double getPpg() {return ppg;}

    public double getPrice() {
        return price;
    }

    public double getProjectedPoints() {
        return projectedPoints;
    }

    public double getAverage() {
        return projectedPoints/price;
    }

    public String getOpponent() {
        game = game.replace(team, "");
        game = game.replace("@", "");
        String[] opponent = game.split(" ");
        return opponent[0];
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectedPoints(double projectedPoints) {
        this.projectedPoints = projectedPoints;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
