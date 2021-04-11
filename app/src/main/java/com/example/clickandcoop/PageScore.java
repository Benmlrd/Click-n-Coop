package com.example.clickandcoop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;
import java.util.UUID;

public class PageScore extends Fragment {

    private ScoreViewModel model;

    private TextView premierScore;
    private TextView deuxiemeScore;
    private TextView troisiemeScore;
    private TextView scoreActuel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_score, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        premierScore = view.findViewById(R.id.tv_score1);
        deuxiemeScore = view.findViewById(R.id.tv_score2);
        troisiemeScore = view.findViewById(R.id.tv_score3);
        scoreActuel = view.findViewById(R.id.tv_scoreActuel);
        scoreActuel.setText(getArguments().getString("source"));
        scoreActuel.setText(getArguments().getString("score"));

        Bundle bundle = getArguments();

        BDDSingleton db = BDDSingleton.getInstance(getActivity().getApplicationContext());
        ScoreDAO scoreDAO = db.scoreDAO();

        model = new ViewModelProvider(requireActivity()).get(ScoreViewModel.class);

        if (bundle.getString("source").equals("Mash.java")) {

            final Observer<List<Integer>> scoresMashObserver = new Observer<List<Integer>>() {
                @Override
                public void onChanged(List<Integer> lesScores) {
                    premierScore.setText("1. " + lesScores.get(0));
                    deuxiemeScore.setText("2. " + lesScores.get(1));
                    troisiemeScore.setText("3. " + lesScores.get(2));

                    UUID leNouveauId = UUID.randomUUID();
                    int leNouveauScore = Integer.parseInt(scoreActuel.getText().toString());
                    String leGameMode = bundle.getString("source");
                    Score nouveauScore = new Score(leNouveauId, leNouveauScore, leGameMode);
                    scoreDAO.insertScore(nouveauScore);
                }
            };
            model.getScoresMash(bundle.getString("source")).observe(requireActivity(), scoresMashObserver);
        }
        else {

            final Observer<List<Integer>> scoresRythmObserver = new Observer<List<Integer>>() {
                @Override
                public void onChanged(List<Integer> lesScores) {
                    premierScore.setText("1. " + lesScores.get(0));
                    deuxiemeScore.setText("2. " + lesScores.get(1));
                    troisiemeScore.setText("3. " + lesScores.get(2));

                    UUID leNouveauId = UUID.randomUUID();
                    int leNouveauScore = Integer.parseInt(scoreActuel.getText().toString());
                    String leGameMode = bundle.getString("source");
                    Score nouveauScore = new Score(leNouveauId, leNouveauScore, leGameMode);
                    scoreDAO.insertScore(nouveauScore);
                }
            };
            model.getScoresRythm(bundle.getString("source")).observe(requireActivity(), scoresRythmObserver);
        }

        view.findViewById(R.id.b_rejouer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PageScore.this)

                        .navigate(R.id.action_PageScore_to_Mash);
            }
        });

        view.findViewById(R.id.b_rejouer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PageScore.this)

                        .navigate(R.id.action_PageScore_to_Rythm);
            }
        });

        view.findViewById(R.id.b_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PageScore.this)

                        .navigate(R.id.action_PageScore_to_FirstFragment);
            }
        });
    }
}
