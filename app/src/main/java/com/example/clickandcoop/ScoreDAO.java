package com.example.clickandcoop;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDAO {

    @Query("SELECT score FROM Score WHERE gameMode = :gameMode ORDER BY score ASC")
    LiveData<List<Integer>> getScores(String gameMode);

    @Insert
    void insertScore(Score score);
}
