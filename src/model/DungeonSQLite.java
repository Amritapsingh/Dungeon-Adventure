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

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:dungeonEnemyModels.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );


        //now create a table
        String query = "CREATE TABLE IF NOT EXISTS dungeonEnemy ( " +
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
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created questions table successfully" );

        //next insert two rows of data
        System.out.println( "Attempting to insert two rows into dungeonEnemy table" );

        String query1 = "INSERT INTO dungeonEnemy ( ENEMY_NAME, HEALTH_POINTS, CURRENT_HEALTH, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                        " ALIVE, CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Ogre', '200', '200','30', '60', '0.60', '2.00', '0.10'," +
                        "                                                '30', '60' )";

        String query2 = "INSERT INTO dungeonEnemy ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                        " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Skeleton', '100', '30', '50', '0.80', '3.00', '0.30'," +
                        "                                                '30', '50' )";

        String query3 = "INSERT INTO dungeonEnemy ( ENEMY_NAME, HEALTH_POINTS, MIN_DMG, MAX_DMG, CHANCE_TO_HIT, ATTACKSPD," +
                " CHANCE_TO_HEAL, MIN_HEAL, MAX_HEAL ) VALUES ( 'Gremlin', '70', '15', '30', '0.80', '5.00', '0.40'," +
                "                                                '20', '40' )";

        try ( Connection conn = ds.getConnection();
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


        //now query the database table for all its contents and display the results
        System.out.println( "Selecting all rows from dungeonEnemy table" );
        query = "SELECT * FROM dungeonEnemy";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                String name = rs.getString( "ENEMY_NAME" );
                String health = rs.getString( "HEALTH_POINTS" );
                String minDmg = rs.getString("MIN_DMG");
                String maxDmg = rs.getString("MAX_DMG");
                String chanceHit = rs.getString("CHANCE_TO_HIT");
                String attackSpd = rs.getString("ATTACKSPD");
                String chanceHeal = rs.getString("CHANCE_TO_HEAL");
                String minHeal = rs.getString("MIN_HEAL");
                String maxHeal = rs.getString("MAX_HEAL");

                System.out.println( "Result: ");
                System.out.println("Name: " + name);
                System.out.println("Health: " + health);
                System.out.println("Min Dmg: " + minDmg);
                System.out.println("Max Dmg: " + maxDmg);
                System.out.println("Chance To Hit: " + chanceHit);
                System.out.println("Attack Speed: " + attackSpd);
                System.out.println("Chance To Heal: " + chanceHeal);
                System.out.println("Min Heal: " + minHeal);
                System.out.println("Max Heal: " + maxHeal);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println("press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

}

