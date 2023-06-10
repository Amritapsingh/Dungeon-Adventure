package model;

import java.io.*;

/**
 * This class is used for saving and loading an instance of the dungeon
 */
public class SaveLoad {

    /**
     * This method saves the dungeon to a file.
     *
     * @param theFileName the name of the file.
     * @param theDungeon  the current state of the dungeon.
     */
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

    /**
     * This method loads in the file of a saved dungeon.
     *
     * @param theFileName the name of the file.
     * @return the saved instance of the dungeon.
     */
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
