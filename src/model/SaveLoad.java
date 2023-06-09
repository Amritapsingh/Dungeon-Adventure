package model;

import view.GameScreen;

import java.io.*;
import java.util.Scanner;

public class SaveLoad {

    public static void saveGame(final String theFileName, Dungeon theDungeon) {
        try {
            FileOutputStream fileOut = new FileOutputStream(theFileName);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(theDungeon);
            objOut.close();
            fileOut.close();
            System.out.println("Dungeon saved to " + theFileName + ".ser");
        } catch (IOException e) {
            System.out.println("Error saving dungeon: " + e.getMessage());
        }
    }

    public static Dungeon loadGame(final String theFileName) {
        try {
            FileInputStream fileIn = new FileInputStream(theFileName);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Dungeon loadedDungeon = (Dungeon) objIn.readObject();
            objIn.close();
            fileIn.close();
            System.out.println("Dungeon loaded from " + theFileName + ".ser");
            return loadedDungeon;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading dungeon: " + e.getMessage());
            return null;
        }
    }

}
