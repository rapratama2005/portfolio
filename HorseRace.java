//R. A. Pratama
//December 31, 2021 @ 09:46

import java.io.*;
import java.util.*;

public class HorseRace {

    //Generates numOfStats stats
    public static int[] generateStats(int numOfStats){
        int[] stats = new int[numOfStats];
        for (int i = 0; i < numOfStats; i++){
            int stat = (int) (Math.random() * 100) + 1;
            stats[i] = stat;
        }
        return stats;
    }

    //Generates numOfHorses names
    public static String[] generateNames(int numOfHorses, ArrayList<String> adjectives, ArrayList<String> properNouns){
        Collections.shuffle(adjectives);
        Collections.shuffle(properNouns);
        String[] names = new String[numOfHorses];
        for (int i = 0; i < numOfHorses; i++){
            String name = adjectives.get(i) + " " + properNouns.get(i);
            names[i] = name;
        }
        return names;
    }

    //Creates fullStatsTable
    public static int[][] fullStatTable(int numOfHorses, int numOfStats){
        int[][] statTable = new int[numOfHorses][numOfStats];
        for(int i = 0; i < numOfHorses; i++){
            int[] stats = generateStats(numOfStats);
            for(int j = 0; j < numOfStats; j++){
                statTable[i][j] = stats[j];
            }
        }
        return statTable;
    }

    //display Horses
    public static void displayHorses(String[] names){
        for (int i = 0; i < names.length; i++){
            System.out.printf("\n%3d | %-20s", i, names[i]);
        }
    }

    //display Horse Stats
    public static void displayHorseStat(String[] names, int[][] statTable, String[] statCategories, int horseIndex){
        System.out.print("\n\nHorse Name: " + names[horseIndex]);
        for (int i = 0; i < statCategories.length; i++){
            System.out.printf("\n%15s | %-2d", statCategories[i], statTable[horseIndex][i]);
        }
    }

    //returns how far horse went in that round
    public static int[] round(int[][] statTable, int numOfHorses, int numOfStats){
        int[] roundAdds = new int[numOfHorses];
        for (int i = 0; i < numOfHorses; i++){
            int randomStat = (int) (Math.random() * 6);
            int randomAdd = statTable[i][randomStat];
            roundAdds[i] = randomAdd;
        }
        return roundAdds;
    }

    //returns current state
    public static int[] roundState(int numOfHorses, int[] roundAdds, int[] currentState){
        int[] newState = new int[numOfHorses];
        for (int i = 0; i < numOfHorses; i++){
            newState[i] = currentState[i] + roundAdds[i];
        }
        return newState;
    }




    public static void main(String[] args){

        String[] statCategories = {"Speed", "Endurance", "Luck", "Strength", "Flexibility", "Plot Armor"};
        int numOfStats = statCategories.length;
        int numOfHorses = 5;

        List<String> adjList = Arrays.asList("Antsy", "Big", "Cool", "Dumb", "Even", "Fast", "Gorgeous", "Hideous", "Idiotic", "Jumpy", "Kwik", "Large");
        
        ArrayList<String> adjectives = new ArrayList<>();
        adjectives.addAll(adjList);

        List<String> namesList = Arrays.asList("Aaron", "Bob", "Clements", "Daniel", "Ethan", "Frank", "Allison", "Bailey", "Claire", "Dolly", "Evelyn", "Fania");
        
        ArrayList<String> properNouns = new ArrayList<>();
        properNouns.addAll(namesList);

        String[] names = generateNames(numOfHorses, adjectives, properNouns);
        int[][] fullStatsTable = fullStatTable(numOfHorses, numOfStats);
        displayHorses(names);
        for (int i = 0; i < names.length; i++){
            displayHorseStat(names, fullStatsTable, statCategories, i);
        }



    }
}

//December 31, 2021 @ 09:46 PST - Program Created
//December 31, 2021 @ 12:57 PST - uploaded to github