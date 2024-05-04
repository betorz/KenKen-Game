package main.domain.controllers;

import main.domain.classes.Ranking;
import main.domain.classes.exceptions.ExceptionRankingEmpty;

public class CtrlRanking {
    
    private Ranking ranking = Ranking.getInstance();

    
    public Ranking getRanking() {
        return ranking;
    }

    public void orderRankingByPoints() throws ExceptionRankingEmpty { //ordena la llista per els punts
        
        ranking.orderRankingByPoints();
    }

    public void orderRankingByName() throws ExceptionRankingEmpty { //ordena la llista pel nom
        ranking.orderRankingByName();
    }

    public void orderRankingByTime(int kenkenSize, int difficulty) throws ExceptionRankingEmpty { //ordena la llista pel temps 
        ranking.orderRankingByTime(kenkenSize, difficulty);
    }

    public void orderRankingByDifficulty(int kenkenDifficulty) throws ExceptionRankingEmpty { //ordena la llista per dificultat??
        ranking.orderRankingByDifficulty(kenkenDifficulty);
    }

    public void orderRankingByNumberOfSolved(int kenkenSize) throws ExceptionRankingEmpty { //ordena la llista pel numero de kenkens 
        ranking.orderRankingByNumberOfSolved(kenkenSize);
    }


}
