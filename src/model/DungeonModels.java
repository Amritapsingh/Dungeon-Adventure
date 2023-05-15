package model;

public interface DungeonModels {
    String getName();

    int getHealth();

    double getAttkSpd();

    double getChanceToHit();

    int getDmgMin();

    int getDmgMax();

    void attck();
}
