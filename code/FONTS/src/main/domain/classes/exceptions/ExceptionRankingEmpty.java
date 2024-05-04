package main.domain.classes.exceptions;

public class ExceptionRankingEmpty extends Exception {
    
    public ExceptionRankingEmpty() {
        super("\nRanking exception: The ranking is empty");
    }
    
}
