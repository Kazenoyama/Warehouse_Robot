package com.warehouse;

public class Main {
    public static void main(String[] args) {
        
        Integer[][] mapTile = {
            {2,2,2,2,2,2,2,2,2,2},
            {2,0,0,0,0,0,0,0,0,4},
            {2,1,1,0,0,0,2,0,2,2},
            {2,2,2,1,0,1,2,0,1,2},
            {2,1,1,0,0,1,2,0,1,2},
            {2,0,0,0,0,0,2,0,0,2},
            {2,0,0,0,0,0,0,0,1,2},
            {2,1,0,0,1,2,0,0,0,2},
            {2,1,0,0,1,2,0,0,0,3},
            {2,2,2,2,2,2,2,2,2,2}
        };

        GameEngine game = new GameEngine(mapTile);
        DecisionMaker decisionMaker = new DecisionMaker(game);

        game.start(decisionMaker);
    } 
}
