package fantasy.nfl.tool.myapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Darko on 10/15/2016.
 */

class BruteLineupOptimizer {

    public BruteLineupOptimizer() {
    }


    public static ArrayList createLineup(List<List<Player>> thisLineup) throws IOException {
        double totalPoints = 0;
        int finalA = 0, finalB = 0, finalC = 0, finalD = 0, finalE = 0, finalF = 0, finalG = 0, finalH = 0;
        ArrayList<Player> optimal = new ArrayList<Player>();
        String lineuptest = "false";


        List<Player> guards1 = new ArrayList<Player>();
        guards1.addAll(thisLineup.get(0));
        guards1.addAll(thisLineup.get(1));
        PriceComparator newCompared1 = new PriceComparator(guards1);
        guards1 = newCompared1.getSortedPlayers();
        Set<Player> subGuards = new LinkedHashSet<Player>(guards1);
        List<Player> guards = new ArrayList<Player>(subGuards);

        List<Player> forwards1 = new ArrayList<Player>();
        forwards1.addAll(thisLineup.get(2));
        forwards1.addAll(thisLineup.get(3));
        PriceComparator newCompared = new PriceComparator(forwards1);
        forwards1 = newCompared.getSortedPlayers();
        Set<Player> subForwards = new LinkedHashSet<Player>(forwards1);
        List<Player> forwards = new ArrayList<Player>(subForwards);

        Set<Player> subUtility = new LinkedHashSet<Player>();
        subUtility.addAll(thisLineup.get(0));
        subUtility.addAll(thisLineup.get(1));
        subUtility.addAll(thisLineup.get(2));
        subUtility.addAll(thisLineup.get(3));
        subUtility.addAll(thisLineup.get(4));
        List<Player> utility = new ArrayList<Player>(subUtility);
        PriceComparator newComparedU = new PriceComparator(utility);
        utility = newComparedU.getSortedPlayers();
        thisLineup.add(guards);
        thisLineup.add(forwards);
        thisLineup.add(utility);
        int count = 0;




        for(int a = 0; a < 4; a++){
            for(int b = 0; b < 4; b++){
                for(int c = 0; c < 4; c++){
                    for(int d = 0; d < 4; d++){
                        for(int e = 0; e < 4; e++){
                            for(int f = 0; f < guards.size(); f++){
                                if(!guards.get(f).getName().equals(thisLineup.get(1).get(b).getName()) && !guards.get(f).getName().equals(thisLineup.get(0).get(a).getName()) && !guards.get(f).getName().equals(forwards.get(c).getName())){
                                    for(int g = 0; g < forwards.size(); g++){
                                        if(!forwards.get(g).getName().equals(guards.get(2).getName()) && !forwards.get(g).getName().equals(thisLineup.get(3).get(d).getName()) && !forwards.get(g).getName().equals(thisLineup.get(2).get(c).getName()) && !forwards.get(g).getName().equals(thisLineup.get(4).get(d).getName())){
                                            for(int h = 0; h < 20; h++){
                                                if(!utility.get(h).getName().equals(forwards.get(g).getName()) && !utility.get(h).getName().equals(guards.get(f).getName()) && !utility.get(h).getName().equals(thisLineup.get(4).get(e).getName()) && !utility.get(h).getName().equals(thisLineup.get(3).get(d).getName()) && !utility.get(h).getName().equals(thisLineup.get(2).get(c).getName()) && !utility.get(h).getName().equals(thisLineup.get(1).get(b).getName()) && !utility.get(h).getName().equals(thisLineup.get(0).get(a).getName())){
                                                    if(thisLineup.get(0).get(a).getPrice() + thisLineup.get(1).get(b).getPrice() + thisLineup.get(2).get(c).getPrice() + thisLineup.get(3).get(d).getPrice() + thisLineup.get(4).get(e).getPrice() + guards.get(f).getPrice() + forwards.get(g).getPrice() + utility.get(h).getPrice() < 50000){
                                                        if(totalPoints < thisLineup.get(0).get(a).getProjectedPoints()  + thisLineup.get(1).get(b).getProjectedPoints() + thisLineup.get(2).get(c).getProjectedPoints() + thisLineup.get(3).get(d).getProjectedPoints() + thisLineup.get(4).get(e).getProjectedPoints() + guards.get(f).getProjectedPoints() + forwards.get(g).getProjectedPoints() + utility.get(h).getProjectedPoints()){
                                                            if(playerCheck(a, b, c, d, e, f, g, h, thisLineup, guards, forwards, utility)){
                                                                totalPoints = thisLineup.get(0).get(a).getProjectedPoints()  + thisLineup.get(1).get(b).getProjectedPoints() + thisLineup.get(2).get(c).getProjectedPoints() + thisLineup.get(3).get(d).getProjectedPoints() + thisLineup.get(4).get(e).getProjectedPoints() + guards.get(f).getProjectedPoints() + forwards.get(g).getProjectedPoints() + utility.get(h).getProjectedPoints();
                                                                finalA = a;
                                                                finalB = b;
                                                                finalC = c;
                                                                finalD = d;
                                                                finalE = e;
                                                                finalF = f;
                                                                finalG = g;
                                                                finalH = h;
                                                                count++;
                                                                lineuptest = "true";
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        optimal.add(thisLineup.get(0).get(finalA));
        optimal.add(thisLineup.get(1).get(finalB));
        optimal.add(thisLineup.get(2).get(finalC));
        optimal.add(thisLineup.get(3).get(finalD));
        optimal.add(thisLineup.get(4).get(finalE));
        optimal.add(guards.get(finalF));
        optimal.add(forwards.get(finalG));
        optimal.add(utility.get(finalH));


        double totalTeamPoints = optimal.get(0).getProjectedPoints() + optimal.get(1).getProjectedPoints() + optimal.get(2).getProjectedPoints() + optimal.get(3).getProjectedPoints() + optimal.get(4).getProjectedPoints() + optimal.get(5).getProjectedPoints() + optimal.get(6).getProjectedPoints() + optimal.get(7).getProjectedPoints();
        double totalInitialCost = optimal.get(0).getPrice() + optimal.get(1).getPrice() + optimal.get(2).getPrice() + optimal.get(3).getPrice() + optimal.get(4).getPrice() + optimal.get(5).getPrice() + optimal.get(6).getPrice() + optimal.get(7).getPrice();
        Player team = new Player(lineuptest, totalTeamPoints, (int) totalInitialCost);
        optimal.add(team);

        for(int i = 0; i < 8; i++) {
            System.out.println("optimal: " + optimal.get(i).getName());
        }
        return optimal;

    }

    public static boolean playerCheck(int a, int b, int c, int d, int e, int f, int g, int h, List<List<Player>> thisLineup, List<Player> guards, List<Player> forwards, List<Player> utility){
        if(thisLineup.get(0).get(a).getProjectedPoints() < 0.5){
            return false;
        }
        else if(thisLineup.get(1).get(b).getProjectedPoints() < 0.5){
            return false;
        }
        else if(thisLineup.get(2).get(c).getProjectedPoints() < 0.5){
            return false;
        }
        else if(thisLineup.get(3).get(d).getProjectedPoints() < 0.5){
            return false;
        }
        else if(thisLineup.get(4).get(e).getProjectedPoints() < 0.5){
            return false;
        }
        else if(guards.get(f).getProjectedPoints() < 0.5){
            return false;
        }
        else if(forwards.get(g).getProjectedPoints() < 0.5){
            return false;
        }
        else return utility.get(h).getProjectedPoints() >= 0.5;
    }
}

