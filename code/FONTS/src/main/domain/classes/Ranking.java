package main.domain.classes;

import java.util.*;

import main.domain.classes.exceptions.ExceptionRankingEmpty;

//Aquesta classe es un singelton


public class Ranking {

    private static final Ranking INSTANCE = new Ranking();
    
    private List<User> userList; //llista de users registrats 


    //privat perque es un singleton
    private Ranking() {
        userList = new ArrayList<>();
    }

    ///////////GETTERS

    //amb aquest instance accedirem al singleton
    public static Ranking getInstance() {
        return INSTANCE;
    }

    public List<User> getUserList() {
        return userList;
    }


    //afegim user al ranking (aixo passa quan es registra un user nou)
    public void addUser(User user) { //
        userList.add(user);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }
    
    public void orderRankingByPoints() throws ExceptionRankingEmpty { //ordena la llista per els punts
        if (userList.isEmpty()) throw new ExceptionRankingEmpty();
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getPoints(), u1.getPoints());
            }
        });

        int position = 0;
        for (User u : userList) {
            ++position;
            System.out.println(position + ": " + u.getUsername() + " " + u.getPoints() + " POINTS");
        }    
    }

    public void orderRankingByName() throws ExceptionRankingEmpty { //ordena la llista pel nom
        if (getUserList().isEmpty()) throw new ExceptionRankingEmpty();
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return u1.getUsername().toLowerCase().compareTo(u2.getUsername().toLowerCase());
            }
        });

        int position = 0;
        for (User u : userList) {
            ++position;
            System.out.println(position + ": " + u.getUsername() + " " + u.getPoints() + " POINTS");
        }
    }

    public void orderRankingByTime(int kenkenSize, int difficulty) throws ExceptionRankingEmpty { //ordena la llista pel temps 
        if (getUserList().isEmpty()) throw new ExceptionRankingEmpty();
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Long.compare(u1.getTimes(kenkenSize, difficulty), u2.getTimes(kenkenSize, difficulty));
            }
        });

        int position = 0;
        for (User u : userList) {
            ++position;
            System.out.println(position + ": " + u.getUsername() + " " + u.getTimes(kenkenSize, difficulty) + " SECONDS");
        }
    }

    public void orderRankingByDifficulty(int kenkenDifficulty) throws ExceptionRankingEmpty { //ordena la llista per dificultat??
        if (getUserList().isEmpty()) throw new ExceptionRankingEmpty();
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getSolvedDifficulty(kenkenDifficulty), u1.getSolvedDifficulty(kenkenDifficulty));
            }
        });

        int position = 0;
        for (User u : userList) {
            ++position;
            System.out.println(position + ": " + u.getUsername() + " " + u.getSolvedDifficulty(kenkenDifficulty) + " KENKENS");
        }

    }

    public void orderRankingByNumberOfSolved(int kenkenSize) throws ExceptionRankingEmpty { //ordena la llista pel numero de kenkens 
        if (getUserList().isEmpty()) throw new ExceptionRankingEmpty();
        Collections.sort(userList, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getSolvedSize(kenkenSize), u1.getSolvedSize(kenkenSize));
            }
        });

        int position = 0;
        for (User u : userList) {
            ++position;
            System.out.println(position + ": " + u.getUsername() + " " + u.getSolvedSize(kenkenSize) + " KENKENS");
        }
    }
    
}

