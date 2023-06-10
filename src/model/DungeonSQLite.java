package model;

import org.sqlite.SQLiteDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates and contains our SQLite database.
 *
 * @author tom capaul
 *
 * @see <a href="https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/">
https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/</a>
 *
 */
public class DungeonSQLite implements Serializable {
    /**
     * Field for datasource
     */
    private static SQLiteDataSource myDataSource = null;

    /**
     * Method to test connection to our database.
     */
    public void testConnection(){

        //establish connection (creates db file if it does not exist :-)
        try {
            myDataSource = new SQLiteDataSource();
            myDataSource.setUrl("jdbc:sqlite:dungeonEnemyModels.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Method to create empty table
     */
    public void createMonsterTable() {
        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS dungeonEnemyHard ( " +
                "ENEMY_NAME TEXT NOT NULL, " +
                "HEALTH_POINTS TEXT NOT NULL, " +
                "MIN_DMG TEXT NOT NULL, " +
                "MAX_DMG TEXT NOT NULL, " +
                "CHANCE_TO_HIT TEXT NOT NULL, " +
                "ATTACKSPD TEXT NOT NULL, " +
                "CHANCE_TO_HEAL TEXT NOT NULL, " +
                "MIN_HEAL TEXT NOT NULL, " +
                "MAX_HEAL TEXT NOT NULL)" ;
        try ( Connection conn = myDataSource.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    /**
     * Method to populate table with monsters and stats
     */
    public void addMonstersToTable() {
        String query1 = "INSERT INTO dungeonEnemyHard ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Ogre', '200', '30', '60', '0.60', '2.00', '0.10'," +
                "                                                '30', '60' )";

        String query2 = "INSERT INTO dungeonEnemyHard ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Skeleton', '150', '30', '50', '0.80', '3.00', '0.30'," +
                "                                                '30', '50' )";

        String query3 = "INSERT INTO dungeonEnemyHard ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Gremlin', '100', '15', '30', '0.80', '5.00', '0.40'," +
                "                                                '20', '40' )";

        try ( Connection conn = myDataSource.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );

            rv = stmt.executeUpdate( query2 );

            rv = stmt.executeUpdate( query3 );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

    }

    /**
     * Method to fetch monsters from tables
     * @param theDifficulty passes the chosen user difficulty
     * @return list of monsters according to difficulty selected
     */
    public List<Monster> fetchMonsters(String theDifficulty) {
        List<Monster> monsters = new ArrayList<Monster>();
        String query = "";
        if (theDifficulty.equals("Easy")) {
            query = "SELECT * FROM dungeonEnemyEasy";
        } else if(theDifficulty.equals("Medium")) {
            query = "SELECT * FROM dungeonEnemyMedium";
        } else if(theDifficulty.equals("Hard")) {
            query = "SELECT * FROM dungeonEnemyHard";
        }
        Monster monster = null;
        try (PreparedStatement statement = myDataSource.getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("ENEMY_NAME");
                int health = resultSet.getInt("HEALTH_POINTS");
                int minDmg = resultSet.getInt("MIN_DMG");
                int maxDmg = resultSet.getInt("MAX_DMG");
                double chanceToHit = resultSet.getDouble("CHANCE_TO_HIT");
                double attackSpeed = resultSet.getDouble("ATTACKSPD");
                double chanceToHeal = resultSet.getDouble("CHANCE_TO_HEAL");
                int minHeal = resultSet.getInt("MIN_HEAL");
                int maxHeal = resultSet.getInt("MAX_HEAL");
                // Create a new Monster instance and add it to the list
                monster = new Monster(name, health, health, minDmg, maxDmg, chanceToHit, attackSpeed, true, chanceToHeal, minHeal, maxHeal);
                monsters.add(monster);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monsters;
    }

}