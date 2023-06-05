package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
public class DungeonSQLite {
    private static SQLiteDataSource myDataSource = null;
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
        String query = "CREATE TABLE IF NOT EXISTS " + Monsters + " ( " +
                "ENEMY_NAME TEXT NOT NULL, " +
                "HEALTH_POINTS TEXT NOT NULL, " +
                "CURRENT_HEALTH TEXT NOT NULL, " +
                "MIN_DMG TEXT NOT NULL, " +
                "MAX_DMG TEXT NOT NULL, " +
                "CHANCE_TO_HIT TEXT NOT NULL, " +
                "ATTACKSPD TEXT NOT NULL, " +
                "ALIVE TEXT NOT NULL, " +
                "CHANCE_TO_HEAL TEXT NOT NULL, " +
                "MIN_HEAL TEXT NOT NULL, " +
                "MAX_HEAL TEXT NOT NULL)" ;
        try ( Connection conn = myDataSource.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public void addMonstersToTable() {
        String query1 = "INSERT INTO " + Monsters + " ( ENEMY_NAME, HEALTH_POINTS, CURRENT_HEALTH, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " ALIVE, CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Ogre', '200', '200','30', '60', '0.60', '2.00', '0.10'," +
                "                                                '30', '60' )";

        String query2 = "INSERT INTO " + Monsters + " ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Skeleton', '100', '30', '50', '0.80', '3.00', '0.30'," +
                "                                                '30', '50' )";

        String query3 = "INSERT INTO " + Monsters + " ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Gremlin', '70', '15', '30', '0.80', '5.00', '0.40'," +
                "                                                '20', '40' )";

        try ( Connection conn = myDataSource.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query3 );
            System.out.println( "3rd executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}

