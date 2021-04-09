package com.example.clickandcoop;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.concurrent.TimeUnit;

public class Mash extends Fragment {
    private int compteur_j1;
    private int compteur_j2;

    private boolean estCommence_j1;
    private boolean estCommence_j2;
    private boolean valide;

    private TextView scoreJoueur1;
    private TextView tempsJoueur1;

    private TextView scoreJoueur2;
    private TextView tempsJoueur2;

    private Button bouton_joueur1;
    private Button bouton_joueur2;

    private CountDownTimer countDownTimer;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mash, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        compteur_j1 = 0;
        compteur_j2 = 0;

        estCommence_j1 = false;
        estCommence_j2 = false;
        valide = false;

        bouton_joueur1 = view.findViewById(R.id.b_joueur1);
        bouton_joueur2 = view.findViewById(R.id.b_joueur2);

        scoreJoueur1 = view.findViewById(R.id.s_joueur1);
        scoreJoueur2 = view.findViewById(R.id.s_joueur2);

        tempsJoueur1 = view.findViewById(R.id.st_joueur1);
        tempsJoueur2 = view.findViewById(R.id.st_joueur2);

        countDownTimer = new CountDownTimer(10000, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                String format = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)%60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60,
                        TimeUnit.MILLISECONDS.toMillis(millisUntilFinished)%60);
                tempsJoueur1.setText(format);
                tempsJoueur2.setText(format);
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                tempsJoueur1.setText("00:00:00");
                tempsJoueur2.setText("00:00:00");
                NavHostFragment.findNavController(Mash.this)
                        .navigate(R.id.action_Mash_to_PageScore);
            }
        };

        bouton_joueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!estCommence_j1) {
                    estCommence_j1 = true;
                }

                if (estCommence_j1 && estCommence_j2) {
                    if (!valide) {
                        countDownTimer.start();
                        valide = true;
                    }
                    compteur_j1++;
                    scoreJoueur1.setText(String.valueOf(compteur_j1));
                }
            }
        });

        bouton_joueur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!estCommence_j2) {
                    estCommence_j2 = true;
                }

                if (estCommence_j1 && estCommence_j2) {
                    if (!valide) {
                        countDownTimer.start();
                        valide = true;
                    }
                    compteur_j2++;
                    scoreJoueur2.setText(String.valueOf(compteur_j2));
                }
            }
        });

    }
}