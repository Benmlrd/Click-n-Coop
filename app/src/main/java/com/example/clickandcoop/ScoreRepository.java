package com.example.clickandcoop;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScoreRepository {

    private ScoreDAO scoreDAO;


    /*public ScoreRepository(ScoreDAO scoreDAO) {
        this.scoreDAO = scoreDAO;
    }*/

    public LiveData<List<Integer>> getScoresMash(String gameMode) {
        if (scoreDAO == null) {
            scoreDAO = new ScoreDAO() {
                @Override
                public LiveData<List<Integer>> getScores(String gameMode) {
                    return null;
                }

                @Override
                public void insertScore(Score score) {

                }
            };
        }
        return this.scoreDAO.getScores(gameMode);
    }

    public LiveData<List<Integer>> getScoresRythm(String gameMode) {
        if (scoreDAO == null) {
            scoreDAO = new ScoreDAO() {
                @Override
                public LiveData<List<Integer>> getScores(String gameMode) {
                    return null;
                }

                @Override
                public void insertScore(Score score) {

                }
            };
        }
        return this.scoreDAO.getScores(gameMode);
    }
}
