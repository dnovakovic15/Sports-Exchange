package fantasy.nfl.tool.myapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Created by Darko on 10/15/2016.
 */

class LineupCreator {

    public LineupCreator(){
    }

    public static ArrayList createLineup(List<List<Player>> thisLineup, int minDB) throws IOException {

        //API api = new API();
        //api.execute();
        ArrayList<Player> optimizedLineup = new ArrayList<Player>();

        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, i = 0, j = 0, x = 0, y = 0, z = 0;
        int aMax = -1, bMax = -1, cMax = -1, dMax = -1, eMax = -1, xMax = -1, yMax = -1, zMax = -1;
        int aSub = a + 1, bSub = b + 1, cSub = c + 1, dSub = d + 1, eSub = e + 1, xSub = x + 1, ySub = y + 1, zSub = z + 1;
        int totalPoints;

        List<Player> guards1 = new ArrayList<Player>();

        for (int fNext = 0; fNext < thisLineup.get(0).size(); fNext++) {
            guards1.add(fNext, thisLineup.get(0).get(fNext));
        }
        guards1.addAll(thisLineup.get(1));
        Comparator newCompared1 = new Comparator(guards1);
        guards1 = newCompared1.getSortedPlayers();
        Set<Player> subGuards = new LinkedHashSet<Player>(guards1);
        ArrayList<Player> guards = new ArrayList<Player>(subGuards);
        for (int outerCount = 0; outerCount < guards.size(); outerCount++) {
            for (int innerCount = outerCount + 1; innerCount < guards.size(); innerCount++) {
                if (Objects.equals(guards.get(outerCount).getName(), guards.get(innerCount).getName())) {
                    guards.remove(innerCount);
                }
            }
        }

        List<Player> forwards1 = new ArrayList<Player>();

        for (int fNext = 0; fNext < thisLineup.get(2).size(); fNext++) {
            forwards1.add(fNext, thisLineup.get(2).get(fNext));
        }
        forwards1.addAll(thisLineup.get(3));
        Comparator newCompared = new Comparator(forwards1);
        forwards1 = newCompared.getSortedPlayers();
        Set<Player> subForwards = new LinkedHashSet<Player>(forwards1);
        ArrayList<Player> forwards = new ArrayList<Player>(subForwards);
        for (int outerCount = 0; outerCount < forwards.size(); outerCount++) {
            for (int innerCount = outerCount + 1; innerCount < forwards.size(); innerCount++) {
                if (Objects.equals(forwards.get(outerCount).getName(), forwards.get(innerCount).getName())) {
                    forwards.remove(innerCount);
                }
            }
        }


        Set<Player> subUtility = new LinkedHashSet<Player>();
        for (int uNext = 0; uNext < thisLineup.get(0).size(); uNext++) {
            subUtility.add(thisLineup.get(0).get(uNext));
        }
        subUtility.addAll(thisLineup.get(1));
        subUtility.addAll(thisLineup.get(2));
        subUtility.addAll(thisLineup.get(3));
        subUtility.addAll(thisLineup.get(4));
        List<Player> utility = new ArrayList<Player>(subUtility);
        Comparator newComparedU = new Comparator(utility);
        utility = newComparedU.getSortedPlayers();
        for (int outerCount = 0; outerCount < utility.size(); outerCount++) {
            for (int innerCount = outerCount + 1; innerCount < utility.size(); innerCount++) {
                if (Objects.equals(utility.get(outerCount).getName(), utility.get(innerCount).getName())) {
                    utility.remove(innerCount);
                }
            }
        }

        thisLineup.add(guards);
        thisLineup.add(forwards);
        thisLineup.add(utility);

        optimizedLineup.add(0, thisLineup.get(0).get(0));
        optimizedLineup.add(1, thisLineup.get(1).get(0));
        optimizedLineup.add(2, thisLineup.get(2).get(0));
        optimizedLineup.add(3, thisLineup.get(3).get(0));
        optimizedLineup.add(4, thisLineup.get(4).get(0));
        optimizedLineup.add(5, thisLineup.get(5).get(0));
        optimizedLineup.add(6, forwards.get(0));
        optimizedLineup.add(7, utility.get(0));

//        for (int uNext = 0; uNext < optimizedLineup.size(); uNext++) {
//            System.out.println("Duplicate Optimized: " + optimizedLineup.get(uNext).getName());
//        }


        for (int countNext = 0; countNext < 1; countNext++) {
            if (Objects.equals(optimizedLineup.get(countNext).getName(), optimizedLineup.get(1).getName())) {
                b++;
                optimizedLineup.set(1, thisLineup.get(1).get(b));
                countNext = -1;
            }
        }
        for (int countNext = 1; countNext < 2; countNext++) {
            if (Objects.equals(optimizedLineup.get(countNext).getName(), optimizedLineup.get(2).getName())) {
                c++;
                optimizedLineup.set(2, thisLineup.get(2).get(c));
                countNext = -1;
            }
        }
        for (int countNext = 2; countNext < 3; countNext++) {
            if (Objects.equals(optimizedLineup.get(countNext).getName(), optimizedLineup.get(3).getName())) {
                d++;
                optimizedLineup.set(3, thisLineup.get(3).get(d));
                countNext = -1;
            }
        }
        for (int countNext = 0; countNext < 4; countNext++) {
            if (Objects.equals(optimizedLineup.get(countNext).getName(), optimizedLineup.get(4).getName())) {
                e++;
                optimizedLineup.set(4, thisLineup.get(4).get(e));
                countNext = -1;
            }

        }
        for (int countNext = 0; countNext < 2; countNext++) {
            if (Objects.equals(optimizedLineup.get(countNext).getName(), optimizedLineup.get(5).getName())) {
                x++;
                optimizedLineup.set(5, thisLineup.get(5).get(x));
                countNext = -1;
            }
        }
        for (int countNext = 0; countNext < 6; countNext++) {
            if (Objects.equals(optimizedLineup.get(countNext).getName(), optimizedLineup.get(6).getName())) {
                y++;
                optimizedLineup.set(6, forwards.get(y));
                countNext = -1;
            }
        }

        for (int uNext = 0; uNext < 7; uNext++) {
            if (Objects.equals(optimizedLineup.get(uNext).getName(), optimizedLineup.get(7).getName())) {
                z++;
                optimizedLineup.set(7, utility.get(z));
                uNext = -1;
            }
        }


        double totalInitialCost = optimizedLineup.get(0).getPrice() + optimizedLineup.get(1).getPrice() + optimizedLineup.get(2).getPrice() + optimizedLineup.get(3).getPrice() + optimizedLineup.get(4).getPrice() + optimizedLineup.get(5).getPrice() + optimizedLineup.get(6).getPrice() + optimizedLineup.get(7).getPrice();
        ArrayList<Double> difference = new ArrayList<Double>(7);
        ArrayList<Double> priceDifference = new ArrayList<Double>(7);

        while (totalInitialCost < minDB || totalInitialCost > 50000) {
            while (totalInitialCost < minDB) {
                int subCounter = 1;

                for (int uNext = 0; uNext < 8; uNext++) {
                    if (a == 3) {
                        aSub = -1;
                        break;
                    } else if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(0).get(a + subCounter).getName()) && uNext != 0) {
                        subCounter++;
                        uNext = 0;
                    }
                    aSub = a + subCounter;
                    if (aSub > 3) {
                        aSub = -1;
                        break;
                    }
                }
                subCounter = 1;
                for (int uNext = 0; uNext < 8; uNext++) {
                    if (b == 3) {
                        bSub = -1;
                        break;
                    }
                    if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(1).get(b + subCounter).getName()) && uNext != 1) {
                        subCounter++;
                        uNext = 0;
                    }
                    bSub = b + subCounter;
                    if (bSub > 3) {
                        bSub = -1;
                        break;
                    }
                }
                subCounter = 1;
                for (int uNext = 0; uNext < 8; uNext++) {
                    if (c == 3) {
                        cSub = -1;
                        break;
                    }
                    if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(2).get(c + subCounter).getName()) && uNext != 2) {
                        subCounter++;
                        uNext = 0;
                    }
                    cSub = c + subCounter;
                    if (cSub > 3) {
                        cSub = -1;
                        break;
                    }
                }
                subCounter = 1;
                for (int uNext = 0; uNext < 8; uNext++) {
                    if (d == 3) {
                        dSub = -1;
                        break;
                    }
                    if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(3).get(d + subCounter).getName()) && uNext != 3) {
                        subCounter++;
                        uNext = 0;
                    }
                    dSub = d + subCounter;
                    if (dSub > 3) {
                        dSub = -1;
                        break;
                    }
                }
                subCounter = 1;
                for (int uNext = 0; uNext < 8; uNext++) {
                    if (e == 3) {
                        eSub = -1;
                        break;
                    }
                    if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(4).get(e + subCounter).getName()) && uNext != 4) {
                        subCounter++;
                        uNext = 0;
                    }
                    eSub = e + subCounter;
                    if (eSub > 3) {
                        eSub = -1;
                        break;
                    }
                }
                subCounter = 1;
                for (int uNext = 0; uNext < 8; uNext++) {
                    if (x == 7) {
                        xSub = -1;
                        break;
                    }
                    if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(5).get(x + subCounter).getName()) && uNext != 5) {
                        subCounter++;
                        uNext = 0;
                    }
                    xSub = x + subCounter;
                    if (xSub > 7) {
                        xSub = -1;
                        break;
                    }
                }
                subCounter = 1;
                for (int uNext = 0; uNext < 8; uNext++) {
                    if (y == 7) {
                        ySub = -1;
                        break;
                    }
                    if (Objects.equals(optimizedLineup.get(uNext).getName(), forwards.get(y + subCounter).getName()) && uNext != 6) {
                        subCounter++;
                        uNext = 0;
                    }
                    ySub = y + subCounter;
                    if (ySub > 7) {
                        ySub = -1;
                        break;
                    }
                }
                subCounter = 1;
                for (int uNext = 0; uNext < 7; uNext++) {
                    if (z == 19) {
                        zSub = -1;
                        break;
                    }
                    if (Objects.equals(optimizedLineup.get(uNext).getName(), utility.get(z + subCounter).getName())) {
                        subCounter++;
                        uNext = 0;
                    }
                    zSub = z + subCounter;
                    if (zSub > 20) {
                        zSub = -1;
                        break;
                    }
                }


                if (aSub < 0) {
                    aMax = 0;
                    difference.add(0, 0.0);
                    priceDifference.add(0, 0.0);
                } else {
                    difference.add(0, (thisLineup.get(0).get(a).getAverage() - thisLineup.get(0).get(aSub).getAverage()));
                    priceDifference.add(0, (thisLineup.get(0).get(aSub).getPrice() - thisLineup.get(0).get(a).getPrice()));
                }

                if (bSub < 0) {
                    bMax = 1;
                    difference.add(1, 0.0);
                    priceDifference.add(1, 0.0);
                }
                else {
                    difference.add(1, (thisLineup.get(1).get(b).getAverage() - thisLineup.get(1).get(bSub).getAverage()));
                    priceDifference.add(1, (thisLineup.get(1).get(bSub).getPrice() - thisLineup.get(1).get(b).getPrice()));
                }

                if (cSub < 0) {
                    cMax = 2;
                    difference.add(2, 0.0);
                    priceDifference.add(2, 0.0);
                }
                else {
                    difference.add(2, (thisLineup.get(2).get(c).getAverage() - thisLineup.get(2).get(cSub).getAverage()));
                    priceDifference.add(2, (thisLineup.get(2).get(cSub).getPrice() - thisLineup.get(2).get(c).getPrice()));
                }

                if (dSub < 0) {
                    dMax = 3;
                    difference.add(3, 0.0);
                    priceDifference.add(3, 0.0);
                }
                else {
                    difference.add(3, (thisLineup.get(3).get(d).getAverage() - thisLineup.get(3).get(dSub).getAverage()));
                    priceDifference.add(3, (thisLineup.get(3).get(dSub).getPrice() - thisLineup.get(3).get(d).getPrice()));
                }

                if (eSub < 0) {
                    eMax = 4;
                    difference.add(4, 0.0);
                    priceDifference.add(4, 0.0);
                } else {
                    difference.add(4, (thisLineup.get(4).get(e).getAverage() - thisLineup.get(4).get(eSub).getAverage()));
                    priceDifference.add(4, (thisLineup.get(4).get(eSub).getPrice() - thisLineup.get(4).get(e).getPrice()));
                }
                if (xSub < 0) {
                    xMax = 5;
                    difference.add(5, 0.0);
                    priceDifference.add(5, 0.0);
                } else {
                    difference.add(5, (thisLineup.get(5).get(x).getAverage() - thisLineup.get(5).get(xSub).getAverage()));
                    priceDifference.add(5, (thisLineup.get(5).get(xSub).getPrice() - thisLineup.get(5).get(x).getPrice()));
                }
                if (ySub < 0) {
                    yMax = 6;
                    difference.add(6, 0.0);
                    priceDifference.add(6, 0.0);
                } else {
                    difference.add(6, (forwards.get(y).getAverage() - forwards.get(ySub).getAverage()));
                    priceDifference.add(6, (forwards.get(ySub).getPrice() - forwards.get(y).getPrice()));
                }
                if (zSub < 0) {
                    zMax = 7;
                    difference.add(7, 0.0);
                    priceDifference.add(7, 0.0);
                } else {
                    difference.add(7, (utility.get(z).getAverage() - utility.get(zSub).getAverage()));
                    priceDifference.add(7, (utility.get(zSub).getPrice() - utility.get(z).getPrice()));
                }


                double maxDifference = 100;
                int sub = 10;
                int n = 0;


                while (n <= 7) {
                    if (difference.get(n) < maxDifference && priceDifference.get(n) >= 0 && (n != aMax && n != bMax && n != cMax && n != dMax && n != eMax && n != zMax && n != yMax && n != xMax)) {
                        maxDifference = difference.get(n);
                        sub = n;
                    }
                    n++;
                }

                if(sub == 10){
                    return null;
                }

//                System.out.println(sub);

                Comparator sortedOpt = new Comparator(optimizedLineup);
                List<Player> sortedOptimized = new ArrayList<Player>();
                sortedOptimized = sortedOpt.getSortedPlayers();
                double positionDiff = 0;
                int positionHolder = -1;

                for (int count = 0; count < 8; count++) {
                    for (int counter = 0; counter < (thisLineup.get(count).size() - 1); counter++) {
                        if(Objects.equals(thisLineup.get(count).get(counter).getName(), optimizedLineup.get(sub).getName()) && (thisLineup.get(count).get(counter).getAverage() < optimizedLineup.get(sub).getAverage()) && ((optimizedLineup.get(sub).getAverage() - thisLineup.get(count).get(counter).getAverage()) > positionDiff)){
                            positionDiff = optimizedLineup.get(sub).getAverage() - thisLineup.get(count).get(counter).getAverage();
                            positionHolder = count;
                        }
                    }
                }


                if (sub == 0) {
                    for (int count = 0; count < 4 && positionHolder == 0; count++) {
                        if(Objects.equals(thisLineup.get(1).get(count).getName(), optimizedLineup.get(0).getName()) && count < b && optimizedLineup.get(1).getPrice() < thisLineup.get(1).get(count).getPrice()){
                            int oldCount = b;
                            b = count;
                            for(int counter = 0; counter < 7; count++){
                                b = count;
                                optimizedLineup.set(1, thisLineup.get(1).get(b));
                            }
                            optimizedLineup.set(1, thisLineup.get(1).get(b));
                        }
                    }
                    for(int count = 0; count < 8 && positionHolder == 0; count++){
                        if(Objects.equals(thisLineup.get(5).get(count).getName(), optimizedLineup.get(0).getName()) && count < x && optimizedLineup.get(5).getPrice() < thisLineup.get(5).get(count).getPrice()){
                            x = count;
                            optimizedLineup.set(5, thisLineup.get(5).get(x));
                        }
                    }
                    for(int count = 0; count < utility.size() && positionHolder == 0; count++){
                        if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(0).getName()) && count < z && optimizedLineup.get(7).getPrice() < utility.get(count).getPrice()){
                            z = count;
                            optimizedLineup.set(7, utility.get(z));
                        }
                    }
                        optimizedLineup.set(0, thisLineup.get(0).get(aSub));
                        a++;
                    }
                    if (sub == 1) {
                    for(int count = 0; count < 4 && positionHolder == 1; count++){
                        if(Objects.equals(thisLineup.get(0).get(count).getName(), optimizedLineup.get(1).getName()) && count < a && optimizedLineup.get(0).getPrice() < thisLineup.get(0).get(count).getPrice()){
                            a = count;
                            optimizedLineup.set(0, thisLineup.get(0).get(a));
                        }
                    }
                    for(int count = 0; count < 8 && positionHolder == 1; count++){
                        if(Objects.equals(thisLineup.get(5).get(count).getName(), optimizedLineup.get(1).getName()) && count < x && optimizedLineup.get(5).getPrice() < thisLineup.get(5).get(count).getPrice()){
                            x = count;
                            optimizedLineup.set(5, thisLineup.get(5).get(x));
                        }
                    }
                    for(int count = 0; count < utility.size() && positionHolder == 1; count++){
                        if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(1).getName()) && count < z && optimizedLineup.get(7).getPrice() < utility.get(count).getPrice()){
                            z = count;
                            optimizedLineup.set(7, utility.get(z));
                        }
                    }
                        optimizedLineup.set(1, thisLineup.get(1).get(bSub));
                        b++;
                    }
                    if (sub == 2) {
                        for(int count = 0; count < 4 && positionHolder == 2; count++){
                            if(Objects.equals(thisLineup.get(3).get(count).getName(), optimizedLineup.get(2).getName()) && count < d && optimizedLineup.get(4).getPrice() < thisLineup.get(4).get(count).getPrice()){
                                d = count;
                            }
                        }
                        for(int count = 0; count < forwards.size() && positionHolder == 2; count++){
                            if(Objects.equals(forwards.get(count).getName(), optimizedLineup.get(2).getName()) && count < y && optimizedLineup.get(6).getPrice() < forwards.get(count).getPrice()){
                                y = count;
                                optimizedLineup.set(6, forwards.get(y));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 2; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(2).getName()) && count < z && optimizedLineup.get(7).getPrice() < utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(2, thisLineup.get(2).get(cSub));
                        c++;
                    }
                    if (sub == 3) {
                        for(int count = 0; count < 4 && positionHolder == 3; count++){
                            if(Objects.equals(thisLineup.get(2).get(count).getName(), optimizedLineup.get(3).getName()) && count < c && optimizedLineup.get(3).getPrice() < thisLineup.get(3).get(count).getPrice()){
                                c = count;
                                optimizedLineup.set(2, thisLineup.get(2).get(c));
                            }
                        }
                        for(int count = 0; count < forwards.size() && positionHolder == 3; count++){
                            if(Objects.equals(forwards.get(count).getName(), optimizedLineup.get(3).getName()) && count < y && optimizedLineup.get(6).getPrice() < forwards.get(count).getPrice()){
                                y = count;
                                optimizedLineup.set(6, forwards.get(y));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 3; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(3).getName()) && count < z && optimizedLineup.get(7).getPrice() < utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(3, thisLineup.get(3).get(dSub));
                        d++;
                    }
                    if (sub == 4) {
                        for(int count = 0; count < utility.size() && positionHolder == 4; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(4).getName()) && count < z && optimizedLineup.get(7).getPrice() < utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(4, thisLineup.get(4).get(eSub));
                        e++;
                    }
                    if (sub == 5) {
                        for(int count = 0; count < 4 && positionHolder == 5; count++){
                            if(Objects.equals(thisLineup.get(0).get(count).getName(), optimizedLineup.get(5).getName()) && count < a && optimizedLineup.get(0).getPrice() < thisLineup.get(0).get(count).getPrice()){
                                a = count;
                                optimizedLineup.set(0, thisLineup.get(0).get(a));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 5; count++){
                            if(Objects.equals(thisLineup.get(1).get(count).getName(), optimizedLineup.get(5).getName()) && count < b && optimizedLineup.get(1).getPrice() < thisLineup.get(1).get(count).getPrice()){
                                b = count;
                                optimizedLineup.set(1, thisLineup.get(1).get(b));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 5; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(5).getName()) && count < z && optimizedLineup.get(7).getPrice() < utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(5, thisLineup.get(5).get(xSub));
                        x++;
                    }
                    if (sub == 6) {
                        for(int count = 0; count < 4 && positionHolder == 6; count++){
                            if(Objects.equals(thisLineup.get(2).get(count).getName(), optimizedLineup.get(6).getName()) && count < c && optimizedLineup.get(2).getPrice() < thisLineup.get(2).get(count).getPrice()){
                                c = count;
                                optimizedLineup.set(2, thisLineup.get(2).get(c));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 6; count++){
                            if(Objects.equals(thisLineup.get(3).get(count).getName(), optimizedLineup.get(6).getName()) && count < d && optimizedLineup.get(3).getPrice() < thisLineup.get(3).get(count).getPrice()){
                                d = count;
                                optimizedLineup.set(3, thisLineup.get(3).get(d));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 6; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(6).getName()) && count < z && optimizedLineup.get(7).getPrice() < utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(6, forwards.get(ySub));
                        y++;
                    }
                    if (sub == 7) {
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(0).get(count).getName(), optimizedLineup.get(7).getName()) && count < a && optimizedLineup.get(0).getPrice() < thisLineup.get(0).get(count).getPrice()){
                                a = count;
                                optimizedLineup.set(0, thisLineup.get(0).get(a));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(1).get(count).getName(), optimizedLineup.get(7).getName()) && count < b && optimizedLineup.get(1).getPrice() < thisLineup.get(1).get(count).getPrice()){
                                b = count;
                                optimizedLineup.set(1, thisLineup.get(1).get(b));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(2).get(count).getName(), optimizedLineup.get(7).getName()) && count < c && optimizedLineup.get(2).getPrice() < thisLineup.get(2).get(count).getPrice()){
                                c = count;
                                optimizedLineup.set(2, thisLineup.get(2).get(c));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(3).get(count).getName(), optimizedLineup.get(7).getName()) && count < d && optimizedLineup.get(3).getPrice() < thisLineup.get(3).get(count).getPrice()){
                                d = count;
                                optimizedLineup.set(3, thisLineup.get(3).get(d));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(4).get(count).getName(), optimizedLineup.get(7).getName()) && count < e && optimizedLineup.get(4).getPrice() < thisLineup.get(4).get(count).getPrice()){
                                e = count;
                                optimizedLineup.set(4, thisLineup.get(4).get(e));
                            }
                        }
                        for(int count = 0; count < 8 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(5).get(count).getName(), optimizedLineup.get(7).getName()) && count < x && optimizedLineup.get(6).getPrice() < thisLineup.get(5).get(count).getPrice()){
                                x = count;
                                optimizedLineup.set(5, thisLineup.get(5).get(x));
                            }
                        }
                        for(int count = 0; count < forwards.size() && positionHolder == 7; count++){
                            if(Objects.equals(forwards.get(count).getName(), optimizedLineup.get(7).getName()) && count < y && optimizedLineup.get(6).getPrice() < forwards.get(count).getPrice()){
                                y = count;
                                optimizedLineup.set(6, forwards.get(y));
                            }
                        }
                        optimizedLineup.set(7, utility.get(zSub));
                        z++;
                    }
                    if (sub == 10) {
                        a++;
                        b++;
                        c++;
                        d++;
                        e++;
                        x++;
                        y++;
                        z++;
                    }
//                while (thisLineup.get(5).get(x).getName() == optimizedLineup.get(0).getName() || thisLineup.get(5).get(x).getName() == optimizedLineup.get(1).getName()) {
//                    x++;
//                    optimizedLineup.set(5, thisLineup.get(5).get(x));
//                }
//
//                while (forwards.get(y).getName() == optimizedLineup.get(2).getName() || forwards.get(y).getName() == optimizedLineup.get(3).getName()) {
//                    y++;
//                    optimizedLineup.set(6, forwards.get(y));
//                }
//
//                while (utility.get(z).getName() == optimizedLineup.get(0).getName() || utility.get(z).getName() == optimizedLineup.get(1).getName() || utility.get(z).getName() == optimizedLineup.get(2).getName() || utility.get(z).getName() == optimizedLineup.get(3).getName() || utility.get(z).getName() == optimizedLineup.get(4).getName() || utility.get(z).getName() == optimizedLineup.get(5).getName() || utility.get(z).getName() == optimizedLineup.get(6).getName()) {
//                    z++;
//                    optimizedLineup.set(7, utility.get(z));
//                }

                    totalInitialCost = optimizedLineup.get(0).getPrice() + optimizedLineup.get(1).getPrice() + optimizedLineup.get(2).getPrice() + optimizedLineup.get(3).getPrice() + optimizedLineup.get(4).getPrice() + optimizedLineup.get(5).getPrice() + optimizedLineup.get(6).getPrice() + optimizedLineup.get(7).getPrice();
                }

                while (totalInitialCost > 50000) {
                    int subCounter = 1;

                    for (int uNext = 0; uNext < 8; uNext++) {
                        if (a == 3) {
                            aSub = -1;
                            break;
                        } else if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(0).get(a + subCounter).getName()) && uNext != 0) {
                            subCounter++;
                            uNext = 0;
                        }
                        aSub = a + subCounter;
                        if (aSub > 3) {
                            aSub = -1;
                            break;
                        }
                    }
                    subCounter = 1;
                    for (int uNext = 0; uNext < 8; uNext++) {
                        if (b == 3) {
                            bSub = -1;
                            break;
                        }
                        if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(1).get(b + subCounter).getName()) && uNext != 1) {
                            subCounter++;
                            uNext = 0;
                        }
                        bSub = b + subCounter;
                        if (bSub > 3) {
                            bSub = -1;
                            break;
                        }
                    }
                    subCounter = 1;
                    for (int uNext = 0; uNext < 8; uNext++) {
                        if (c == 3) {
                            cSub = -1;
                            break;
                        }
                        if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(2).get(c + subCounter).getName()) && uNext != 2) {
                            subCounter++;
                            uNext = 0;
                        }
                        cSub = c + subCounter;
                        if (cSub > 3) {
                            cSub = -1;
                            break;
                        }
                    }
                    subCounter = 1;
                    for (int uNext = 0; uNext < 8; uNext++) {
                        if (d == 3) {
                            dSub = -1;
                            break;
                        }
                        if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(3).get(d + subCounter).getName()) && uNext != 3) {
                            subCounter++;
                            uNext = 0;
                        }
                        dSub = d + subCounter;
                        if (dSub > 3) {
                            dSub = -1;
                            break;
                        }
                    }
                    subCounter = 1;
                    for (int uNext = 0; uNext < 8; uNext++) {
                        if (e == 3) {
                            eSub = -1;
                            break;
                        }
                        if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(4).get(e + subCounter).getName()) && uNext != 4) {
                            subCounter++;
                            uNext = 0;
                        }
                        eSub = e + subCounter;
                        if (eSub > 3) {
                            eSub = -1;
                            break;
                        }
                    }
                    subCounter = 1;
                    for (int uNext = 0; uNext < 8; uNext++) {
                        if (x == 7) {
                            xSub = -1;
                            break;
                        }
                        if (Objects.equals(optimizedLineup.get(uNext).getName(), thisLineup.get(5).get(x + subCounter).getName()) && uNext != 5) {
                            subCounter++;
                            uNext = 0;
                        }
                        xSub = x + subCounter;
                        if (xSub > 7) {
                            xSub = -1;
                            break;
                        }
                    }
                    subCounter = 1;
                    for (int uNext = 0; uNext < 8; uNext++) {
                        if (y == 7) {
                            ySub = -1;
                            break;
                        }
                        if (Objects.equals(optimizedLineup.get(uNext).getName(), forwards.get(y + subCounter).getName()) && uNext != 6) {
                            subCounter++;
                            uNext = 0;
                        }
                        ySub = y + subCounter;
                        if (ySub > 7) {
                            ySub = -1;
                            break;
                        }
                    }
                    subCounter = 1;
                    for (int uNext = 0; uNext < 7; uNext++) {
                        if (z == 19) {
                            zSub = -1;
                            break;
                        }
                        if (Objects.equals(optimizedLineup.get(uNext).getName(), utility.get(z + subCounter).getName())) {
                            subCounter++;
                            uNext = 0;
                        }
                        zSub = z + subCounter;
                        if (zSub > 20) {
                            zSub = -1;
                            break;
                        }
                    }


                    if (aSub < 0) {
                        aMax = 0;
                        difference.add(0, 0.0);
                        priceDifference.add(0, 0.0);
                    } else {
                        difference.add(0, (thisLineup.get(0).get(a).getAverage() - thisLineup.get(0).get(aSub).getAverage()));
                        priceDifference.add(0, (thisLineup.get(0).get(aSub).getPrice() - thisLineup.get(0).get(a).getPrice()));
                    }

                    if (bSub < 0) {
                        bMax = 1;
                        difference.add(1, 0.0);
                        priceDifference.add(1, 0.0);
                    } else {
                        difference.add(1, (thisLineup.get(1).get(b).getAverage() - thisLineup.get(1).get(bSub).getAverage()));
                        priceDifference.add(1, (thisLineup.get(1).get(bSub).getPrice() - thisLineup.get(1).get(b).getPrice()));
                    }

                    if (cSub < 0) {
                        cMax = 2;
                        difference.add(2, 0.0);
                        priceDifference.add(2, 0.0);
                    } else {
                        difference.add(2, (thisLineup.get(2).get(c).getAverage() - thisLineup.get(2).get(cSub).getAverage()));
                        priceDifference.add(2, (thisLineup.get(2).get(cSub).getPrice() - thisLineup.get(2).get(c).getPrice()));
                    }

                    if (dSub < 0) {
                        dMax = 3;
                        difference.add(3, 0.0);
                        priceDifference.add(3, 0.0);
                    } else {
                        difference.add(3, (thisLineup.get(3).get(d).getAverage() - thisLineup.get(3).get(dSub).getAverage()));
                        priceDifference.add(3, (thisLineup.get(3).get(dSub).getPrice() - thisLineup.get(3).get(d).getPrice()));
                    }

                    if (eSub < 0) {
                        eMax = 4;
                        difference.add(4, 0.0);
                        priceDifference.add(4, 0.0);
                    } else {
                        difference.add(4, (thisLineup.get(4).get(e).getAverage() - thisLineup.get(4).get(eSub).getAverage()));
                        priceDifference.add(4, (thisLineup.get(4).get(eSub).getPrice() - thisLineup.get(4).get(e).getPrice()));
                    }
                    if (xSub < 0) {
                        xMax = 5;
                        difference.add(5, 0.0);
                        priceDifference.add(5, 0.0);
                    } else {
                        difference.add(5, (thisLineup.get(5).get(x).getAverage() - thisLineup.get(5).get(xSub).getAverage()));
                        priceDifference.add(5, (thisLineup.get(5).get(xSub).getPrice() - thisLineup.get(5).get(x).getPrice()));
                    }
                    if (ySub < 0) {
                        yMax = 6;
                        difference.add(6, 0.0);
                        priceDifference.add(6, 0.0);
                    } else {
                        difference.add(6, (forwards.get(y).getAverage() - forwards.get(ySub).getAverage()));
                        priceDifference.add(6, (forwards.get(ySub).getPrice() - forwards.get(y).getPrice()));
                    }
                    if (zSub < 0) {
                        zMax = 7;
                        difference.add(7, 0.0);
                        priceDifference.add(7, 0.0);
                    } else {
                        difference.add(7, (utility.get(z).getAverage() - utility.get(zSub).getAverage()));
                        priceDifference.add(7, (utility.get(zSub).getPrice() - utility.get(z).getPrice()));
                    }


                    double maxDifference = 100;
                    int sub = 10;
                    int n = 0;


                    while (n <= 7) {
                        if (difference.get(n) < maxDifference && priceDifference.get(n) <= 0 && (n != aMax && n != bMax && n != cMax && n != dMax && n != eMax && n != zMax && n != yMax && n != xMax)) {
                            maxDifference = difference.get(n);
                            sub = n;
                        }
                        n++;
                    }

                    int positionHolder = -1;


                    if (sub == 0) {
                        for (int count = 0; count < 4 && positionHolder == 0; count++) {
                            if(Objects.equals(thisLineup.get(1).get(count).getName(), optimizedLineup.get(0).getName()) && count < b && optimizedLineup.get(1).getPrice() > thisLineup.get(1).get(count).getPrice()){
                                b = count;
                                optimizedLineup.set(1, thisLineup.get(1).get(b));
                            }
                        }
                        for(int count = 0; count < 8 && positionHolder == 0; count++){
                            if(Objects.equals(thisLineup.get(5).get(count).getName(), optimizedLineup.get(0).getName()) && count < x && optimizedLineup.get(5).getPrice() > thisLineup.get(5).get(count).getPrice()){
                                x = count;
                                optimizedLineup.set(5, thisLineup.get(5).get(x));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 0; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(0).getName()) && count < z && optimizedLineup.get(7).getPrice() > utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(0, thisLineup.get(0).get(aSub));
                        a++;
                    }
                    if (sub == 1) {
                        for(int count = 0; count < 4 && positionHolder == 1; count++){
                            if(Objects.equals(thisLineup.get(0).get(count).getName(), optimizedLineup.get(1).getName()) && count < a && optimizedLineup.get(0).getPrice() > thisLineup.get(0).get(count).getPrice()){
                                a = count;
                                optimizedLineup.set(0, thisLineup.get(0).get(a));
                            }
                        }
                        for(int count = 0; count < 8 && positionHolder == 1; count++){
                            if(Objects.equals(thisLineup.get(5).get(count).getName(), optimizedLineup.get(1).getName()) && count < x && optimizedLineup.get(5).getPrice() > thisLineup.get(5).get(count).getPrice()){
                                x = count;
                                optimizedLineup.set(5, thisLineup.get(5).get(x));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 1; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(1).getName()) && count < z && optimizedLineup.get(7).getPrice() > utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(1, thisLineup.get(1).get(bSub));
                        b++;
                    }
                    if (sub == 2) {
                        for(int count = 0; count < 4 && positionHolder == 2; count++){
                            if(Objects.equals(thisLineup.get(3).get(count).getName(), optimizedLineup.get(2).getName()) && count < d && optimizedLineup.get(4).getPrice() > thisLineup.get(4).get(count).getPrice()){
                                d = count;
                            }
                        }
                        for(int count = 0; count < forwards.size() && positionHolder == 2; count++){
                            if(Objects.equals(forwards.get(count).getName(), optimizedLineup.get(2).getName()) && count < y && optimizedLineup.get(6).getPrice() > forwards.get(count).getPrice()){
                                y = count;
                                optimizedLineup.set(6, forwards.get(y));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 2; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(2).getName()) && count < z && optimizedLineup.get(7).getPrice() > utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(2, thisLineup.get(2).get(cSub));
                        c++;
                    }
                    if (sub == 3) {
                        for(int count = 0; count < 4 && positionHolder == 3; count++){
                            if(Objects.equals(thisLineup.get(2).get(count).getName(), optimizedLineup.get(3).getName()) && count < c && optimizedLineup.get(3).getPrice() > thisLineup.get(3).get(count).getPrice()){
                                c = count;
                                optimizedLineup.set(2, thisLineup.get(2).get(c));
                            }
                        }
                        for(int count = 0; count < forwards.size() && positionHolder == 3; count++){
                            if(Objects.equals(forwards.get(count).getName(), optimizedLineup.get(3).getName()) && count < y && optimizedLineup.get(6).getPrice() > forwards.get(count).getPrice()){
                                y = count;
                                optimizedLineup.set(6, forwards.get(y));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 3; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(3).getName()) && count < z && optimizedLineup.get(7).getPrice() > utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(3, thisLineup.get(3).get(dSub));
                        d++;
                    }
                    if (sub == 4) {
                        for(int count = 0; count < utility.size() && positionHolder == 4; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(4).getName()) && count < z && optimizedLineup.get(7).getPrice() > utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(4, thisLineup.get(4).get(eSub));
                        e++;
                    }
                    if (sub == 5) {
                        for(int count = 0; count < 4 && positionHolder == 5; count++){
                            if(Objects.equals(thisLineup.get(0).get(count).getName(), optimizedLineup.get(5).getName()) && count < a && optimizedLineup.get(0).getPrice() > thisLineup.get(0).get(count).getPrice()){
                                a = count;
                                optimizedLineup.set(0, thisLineup.get(0).get(a));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 5; count++){
                            if(Objects.equals(thisLineup.get(1).get(count).getName(), optimizedLineup.get(5).getName()) && count < b && optimizedLineup.get(1).getPrice() > thisLineup.get(1).get(count).getPrice()){
                                b = count;
                                optimizedLineup.set(1, thisLineup.get(1).get(b));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 5; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(5).getName()) && count < z && optimizedLineup.get(7).getPrice() > utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(5, thisLineup.get(5).get(xSub));
                        x++;
                    }
                    if (sub == 6) {
                        for(int count = 0; count < 4 && positionHolder == 6; count++){
                            if(Objects.equals(thisLineup.get(2).get(count).getName(), optimizedLineup.get(6).getName()) && count < c && optimizedLineup.get(2).getPrice() > thisLineup.get(2).get(count).getPrice()){
                                c = count;
                                optimizedLineup.set(2, thisLineup.get(2).get(c));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 6; count++){
                            if(Objects.equals(thisLineup.get(3).get(count).getName(), optimizedLineup.get(6).getName()) && count < d && optimizedLineup.get(3).getPrice() > thisLineup.get(3).get(count).getPrice()){
                                d = count;
                                optimizedLineup.set(3, thisLineup.get(3).get(d));
                            }
                        }
                        for(int count = 0; count < utility.size() && positionHolder == 6; count++){
                            if(Objects.equals(utility.get(count).getName(), optimizedLineup.get(6).getName()) && count < z && optimizedLineup.get(7).getPrice() > utility.get(count).getPrice()){
                                z = count;
                                optimizedLineup.set(7, utility.get(z));
                            }
                        }
                        optimizedLineup.set(6, forwards.get(ySub));
                        y++;
                    }
                    if (sub == 7) {
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(0).get(count).getName(), optimizedLineup.get(7).getName()) && count < a && optimizedLineup.get(0).getPrice() > thisLineup.get(0).get(count).getPrice()){
                                a = count;
                                optimizedLineup.set(0, thisLineup.get(0).get(a));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(1).get(count).getName(), optimizedLineup.get(7).getName()) && count < b && optimizedLineup.get(1).getPrice() > thisLineup.get(1).get(count).getPrice()){
                                b = count;
                                optimizedLineup.set(1, thisLineup.get(1).get(b));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(2).get(count).getName(), optimizedLineup.get(7).getName()) && count < c && optimizedLineup.get(2).getPrice() > thisLineup.get(2).get(count).getPrice()){
                                c = count;
                                optimizedLineup.set(2, thisLineup.get(2).get(c));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(3).get(count).getName(), optimizedLineup.get(7).getName()) && count < d && optimizedLineup.get(3).getPrice() > thisLineup.get(3).get(count).getPrice()){
                                d = count;
                                optimizedLineup.set(3, thisLineup.get(3).get(d));
                            }
                        }
                        for(int count = 0; count < 4 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(4).get(count).getName(), optimizedLineup.get(7).getName()) && count < e && optimizedLineup.get(4).getPrice() > thisLineup.get(4).get(count).getPrice()){
                                e = count;
                                optimizedLineup.set(4, thisLineup.get(4).get(e));
                            }
                        }
                        for(int count = 0; count < 8 && positionHolder == 7; count++){
                            if(Objects.equals(thisLineup.get(5).get(count).getName(), optimizedLineup.get(7).getName()) && count < x && optimizedLineup.get(6).getPrice() > thisLineup.get(5).get(count).getPrice()){
                                x = count;
                                optimizedLineup.set(5, thisLineup.get(5).get(x));
                            }
                        }
                        for(int count = 0; count < forwards.size() && positionHolder == 7; count++){
                            if(Objects.equals(forwards.get(count).getName(), optimizedLineup.get(7).getName()) && count < y && optimizedLineup.get(6).getPrice() > forwards.get(count).getPrice()){
                                y = count;
                                optimizedLineup.set(6, forwards.get(y));
                            }
                        }
                        optimizedLineup.set(7, utility.get(zSub));
                        z++;
                    }
                    if (sub == 10) {
                        a++;
                        b++;
                        c++;
                        d++;
                        e++;
                        x++;
                        y++;
                        z++;
                    }


//                while (thisLineup.get(5).get(x).getName() == optimizedLineup.get(0).getName() || thisLineup.get(5).get(x).getName() == optimizedLineup.get(1).getName()) {
//                    x++;
//                    optimizedLineup.set(5, thisLineup.get(5).get(x));
//                    System.out.println("X has duplicated");
//                }
//
//                while (forwards.get(y).getName() == optimizedLineup.get(2).getName() || forwards.get(y).getName() == optimizedLineup.get(3).getName()) {
//                    y++;
//                    optimizedLineup.set(6, forwards.get(y));
//                    System.out.println("Y has duplicated");
//                }
//
//                while (utility.get(z).getName() == optimizedLineup.get(0).getName() || utility.get(z).getName() == optimizedLineup.get(1).getName() || utility.get(z).getName() == optimizedLineup.get(2).getName() || utility.get(z).getName() == optimizedLineup.get(3).getName() || utility.get(z).getName() == optimizedLineup.get(4).getName() || utility.get(z).getName() == optimizedLineup.get(5).getName() || utility.get(z).getName() == optimizedLineup.get(6).getName()) {
//                    z++;
//                    optimizedLineup.set(7, utility.get(z));
//                    System.out.println("Z has duplicated");
//                    System.out.println(utility.get(z).getName());
//                }

                    totalInitialCost = optimizedLineup.get(0).getPrice() + optimizedLineup.get(1).getPrice() + optimizedLineup.get(2).getPrice() + optimizedLineup.get(3).getPrice() + optimizedLineup.get(4).getPrice() + optimizedLineup.get(5).getPrice() + optimizedLineup.get(6).getPrice() + optimizedLineup.get(7).getPrice();
//                    for (int t = 0; t < optimizedLineup.size(); t++) {
//                        System.out.println(optimizedLineup.get(t).getName());
//                    }
//                    System.out.println(totalInitialCost);
                }


                totalInitialCost = optimizedLineup.get(0).getPrice() + optimizedLineup.get(1).getPrice() + optimizedLineup.get(2).getPrice() + optimizedLineup.get(3).getPrice() + optimizedLineup.get(4).getPrice() + optimizedLineup.get(5).getPrice() + optimizedLineup.get(6).getPrice() + optimizedLineup.get(7).getPrice();

            }
        double totalTeamPoints = optimizedLineup.get(0).getProjectedPoints() + optimizedLineup.get(1).getProjectedPoints() + optimizedLineup.get(2).getProjectedPoints() + optimizedLineup.get(3).getProjectedPoints() + optimizedLineup.get(4).getProjectedPoints() + optimizedLineup.get(5).getProjectedPoints() + optimizedLineup.get(6).getProjectedPoints() + optimizedLineup.get(7).getProjectedPoints();
        Player dummy = new Player("ProjectedTotalPoints", totalTeamPoints, (int)totalInitialCost);
        optimizedLineup.add(dummy);
        return optimizedLineup;
    }}





















//            while(priceDifference.get(0) >= 0 || (a + f) <= 3){
//                priceDifference.set(0, (thisLineup.get(0).get(a).getPrice() - thisLineup.get(0).get(a + f).getPrice()));
//                difference.set(0, (thisLineup.get(0).get(a).getAverage() - thisLineup.get(0).get(a + f).getAverage()));
//                f++;
//                if(f == 3){
//                    difference.set(0,1000.0);
//                }
//            }
//            while(priceDifference.get(1) >= 0 || g == 3){
//                g++;
//                priceDifference.set(1, (thisLineup.get(1).get(b).getPrice() - thisLineup.get(1).get(b + g).getPrice()));
//                difference.add(1, (thisLineup.get(1).get(b).getAverage() - thisLineup.get(1).get(b + g).getAverage()));
//                if(g == 3){
//                    difference.set(1,1000.0);
//                }
//            }
//            while(priceDifference.get(2) >= 0 || h == 3){
//                h++;
//                priceDifference.set(2, (thisLineup.get(2).get(c).getPrice() - thisLineup.get(1).get(c + h).getPrice()));
//                difference.add(2, (thisLineup.get(0).get(c).getAverage() - thisLineup.get(0).get(c + h).getAverage()));
//                if(h == 3){
//                    difference.set(2,1000.0);
//                }
//            }
//            while(priceDifference.get(3) >= 0 || i == 3){
//                i++;
//                priceDifference.set(3, (thisLineup.get(3).get(d).getPrice() - thisLineup.get(3).get(d + i).getPrice()));
//                difference.add(3, (thisLineup.get(1).get(d).getAverage() - thisLineup.get(1).get(d + i).getAverage()));
//                if(i == 3){
//                    difference.set(3,1000.0);
//                }
//            }
//            while(priceDifference.get(4) >= 0 || j == 3){
//                j++;
//                priceDifference.set(4, (thisLineup.get(4).get(e).getPrice() - thisLineup.get(1).get(e + j).getPrice()));
//                difference.add(4, (thisLineup.get(0).get(e).getAverage() - thisLineup.get(0).get(e + h).getAverage()));
//                if(j == 3){
//                    difference.set(4,1000.0);
//                }
//            }
//            int minIndex = difference.indexOf(Collections.min(difference));
//            if(minIndex == 0){
//                optimizedLineup.set(0, thisLineup.get(0).get(a + f));
//                a = a + f;
        //}


        //return optimizedLineup;

