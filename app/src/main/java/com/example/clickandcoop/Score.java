package com.example.clickandcoop;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Score {

    @PrimaryKey
    @NonNull
    private UUID id;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "gameMode")
    private String gameMode;


    public Score() {
    }

    public Score(UUID unId, int leScore, String leGameMode) {
        id = unId;
        score = leScore;
        gameMode = leGameMode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID unId) {
        id = unId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int unScore) {
        score = unScore;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String unGameMode) {
        gameMode = unGameMode;
    }
}
