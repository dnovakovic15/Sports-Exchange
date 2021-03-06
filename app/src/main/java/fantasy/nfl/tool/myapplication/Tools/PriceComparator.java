package fantasy.nfl.tool.myapplication.Tools;

import java.util.ArrayList;
import java.util.List;

import fantasy.nfl.tool.myapplication.Models.Player;

/**
 * Created by Darko on 10/15/2016.
 * Sorts and returns an ArrayList of Players based on their projected average.
 */

public class PriceComparator {

    private List<Player> sortedPlayers = new ArrayList<Player>();
    private List<Player> players = new ArrayList<Player>();
    private List<Integer> counter = new ArrayList<Integer>();


    public PriceComparator(List<Player> sortedPlayers) {
        this.sortedPlayers = sortedPlayers;

        for(int i = 0; i < sortedPlayers.size(); i++) {
            players.add(i, sortedPlayers.get(i));
        }
    }

    public List<Player> getSortedPlayers() {

        for(int i = 0; i < sortedPlayers.size(); i++) {
            int incrementCount = 0;

            for (int j = 0; j < sortedPlayers.size(); j++) {
                if (sortedPlayers.get(i).getPrice() > sortedPlayers.get(j).getPrice() || (sortedPlayers.get(i).getPrice() == sortedPlayers.get(j).getPrice() && i < j)) {
                    incrementCount++;
                }
            }
            counter.add(i, incrementCount);
            players.set(((players.size() - 1) - counter.get(i)), sortedPlayers.get(i));
        }
        return players;
    }
}
