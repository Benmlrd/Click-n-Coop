package com.example.clickandcoop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

public class ScoreViewModel extends ViewModel {

    private ScoreRepository scoreSource;


    public LiveData<List<Integer>> getScoresMash(String gameMode) {
        if (scoreSource == null) {
            scoreSource = new ScoreRepository();
        }
        return scoreSource.getScoresMash(gameMode);
    }

    public LiveData<List<Integer>> getScoresRythm(String gameMode) {
        if (scoreSource == null) {
            scoreSource = new ScoreRepository();
        }
        return scoreSource.getScoresRythm(gameMode);
    }
}
