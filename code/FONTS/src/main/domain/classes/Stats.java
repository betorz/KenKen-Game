package main.domain.classes;
import java.util.Arrays;

public class Stats {
    private int points;
    private int solvedPerc; //% of solved KenKens from the database
    private int solved; //# of solved kenkens
    private long[][] times; //first -> 0-6 (3x3, 4x4, 5x5, 6x6, 7x7, 8x8, 9x9). Second -> Difficulty 0-3 (easy, medium, hard, expert)
    private int[] solvedSize; //0-6 (3x3, 4x4, 5x5, 6x6, 7x7, 8x8, 9x9) -> # of solved for each size type
    private int[] solvedDifficulty; //Difficulty 0-3 (easy, medium, hard, expert) -> # of solved for each difficulty level

    public final long MAX_TIME = 99999999;
    public Stats() {
        this.points = 0;
        this.solvedPerc = 0;
        this.solved = 0;
        this.times = new long[7][4];
        for (int i = 0; i < times.length; i++) {
            Arrays.fill(times[i], MAX_TIME);
        }
        this.solvedSize = new int[7];
        this.solvedDifficulty = new int[4];
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setSolvedPerc(int solvedPerc) {
        this.solvedPerc = solvedPerc;
    }

    public void setSolved(int solved) {
        this.solved = solved;
        updateSolvedPerc();
    }

    public void setTime(int size, int difficulty, long time) {
        times[size][difficulty] = time;
    }

    public void setSolvedSize(int size, int solved) {
        solvedSize[size] = solved;
    }

    public void setSolvedDifficulty(int difficulty, int solved) {
        solvedDifficulty[difficulty] = solved;
    }

    public int getPoints() {
        return points;
    }

    public int getSolvedPerc() {
        return solvedPerc;
    }

    public int getSolved() {
        return solved;
    }

    public long getTimes(int size, int difficulty) {
        return times[size][difficulty];
    }

    public int getSolvedSize(int size) {
        return solvedSize[size];
    }

    public int getSolvedDifficulty(int difficulty) {
        return solvedDifficulty[difficulty];
    }

    public void updatePoints(int points) {
        this.points += points;
    }

    public void updateSolvedPerc() {
        solvedPerc = (int)(((double)solved/28)*100);     //canviar num si posem més kenkens (ara he contat un per cada mida i dificultat)
    }

    public void updateSolvedSize(int size) {
        solvedSize[size]++;
    }

    public void updateSolvedDifficulty(int difficulty) {
        solvedDifficulty[difficulty]++;
    }

    public void updateStats(int size, int difficulty, long time, int points) {
        if(times[size][difficulty] == MAX_TIME) {   //en cas de ser un nivell ja completat no hauria de contar per les stats

            updatePoints(points);
            updateSolvedDifficulty(difficulty);
            updateSolvedSize(size);
            ++solved;
            updateSolvedPerc();
            setTime(size, difficulty, time);
        }
        else {
            if(time < times[size][difficulty]) setTime(size, difficulty, time);
        }
    }
}