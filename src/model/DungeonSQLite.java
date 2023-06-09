package model;

import org.sqlite.SQLiteDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tom capaul
 *
 * Simple class to demonstrate SQLite connectivity
 * 1) create connection
 * 2) add a table
 * 3) add entries to the table
 * 4) query the table for its contents
 * 5) display the results
 *
 * NOTE: any interactions with a database should utilize a try/catch
 * since things can go wrong
 *
 * @see <a href="https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/">
https://shanemcd.org/2020/01/24/how-to-set-up-sqlite-with-jdbc-in-eclipse-on-windows/</a>
 *
 */
public class DungeonSQLite implements Serializable {
    private static SQLiteDataSource myDataSource = null;
    private static final DungeonSQLite INSTANCE = new DungeonSQLite();

    private final String Monsters = "Monsters";

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

    public void createMonsterTable() {
        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS dungeonEnemy ( " +
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

    public void addMonstersToTable() {
        String query1 = "INSERT INTO dungeonEnemy ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Ogre', '200', '30', '60', '0.60', '2.00', '0.10'," +
                "                                                '30', '60' )";

        String query2 = "INSERT INTO dungeonEnemy ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Skeleton', '100', '30', '50', '0.80', '3.00', '0.30'," +
                "                                                '30', '50' )";

        String query3 = "INSERT INTO dungeonEnemy ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Gremlin', '70', '15', '30', '0.80', '5.00', '0.40'," +
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
    public List<Monster> fetchMonsters() {
        List<Monster> monsters = new ArrayList<Monster>();
        String query = "SELECT * FROM dungeonEnemy";
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