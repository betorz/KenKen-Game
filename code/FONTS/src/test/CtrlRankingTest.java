package test;

import main.domain.classes.exceptions.ExceptionRankingEmpty;
import main.domain.controllers.CtrlRanking;

import org.junit.Before;
import org.junit.Test;


public class CtrlRankingTest {

    private CtrlRanking ctrlRanking;

    @Before
    public void setUp() {
        ctrlRanking = new CtrlRanking();
    }

    @Test (expected = ExceptionRankingEmpty.class)
    public void testOrderRankingByPoints() throws ExceptionRankingEmpty {

        ctrlRanking.orderRankingByPoints();

    }

    @Test (expected = ExceptionRankingEmpty.class)
    public void testOrderRankingByName() throws ExceptionRankingEmpty {

            ctrlRanking.orderRankingByName();
    }

    @Test (expected = ExceptionRankingEmpty.class)
    public void testOrderRankingByTime() throws ExceptionRankingEmpty{
        int kenkenSize = 9;
        int difficulty = 2;
        ctrlRanking.orderRankingByTime(kenkenSize, difficulty);
    }

    @Test (expected = ExceptionRankingEmpty.class)
    public void testOrderRankingByDifficulty() throws ExceptionRankingEmpty {
        int kenkenDifficulty = 2;
        ctrlRanking.orderRankingByDifficulty(kenkenDifficulty);
    }

    @Test (expected = ExceptionRankingEmpty.class)
    public void testOrderRankingByNumberOfSolved() throws ExceptionRankingEmpty {
        int kenkenSize = 9;
        ctrlRanking.orderRankingByNumberOfSolved(kenkenSize);

    }
}
