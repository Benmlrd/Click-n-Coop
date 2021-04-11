package com.example.clickandcoop;

import android.graphics.Color;
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

public class Rythm extends Fragment {

    private int verifChaine;
    private int compteur;
    private boolean estCommence;
    private boolean estFini;

    private Button premierBoutton;
    private Button deuxiemeBoutton;
    private Button troisiemeBoutton;
    private Button quatriemeButton;

    private TextView scoreJ1;
    private TextView tempsJ1;
    private TextView scoreJ2;
    private TextView tempsJ2;

    private CountDownTimer countDownTimer;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.rythm, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        verifChaine = 0;
        compteur = 0;
        estCommence = false;
        estFini = false;

        premierBoutton = view.findViewById(R.id.b1_cliqueJ1);
        deuxiemeBoutton = view.findViewById(R.id.b2_cliqueJ2);
        troisiemeBoutton = view.findViewById(R.id.b2_cliqueJ1);
        quatriemeButton = view.findViewById(R.id.b1_cliqueJ2);

        scoreJ1 = view.findViewById(R.id.tv_scoreJ1);
        scoreJ2 = view.findViewById(R.id.tv_scoreJ2);

        tempsJ1 = view.findViewById(R.id.tv_tempsJ1);
        tempsJ2 = view.findViewById(R.id.tv_tempsJ2);

        countDownTimer = new CountDownTimer(10000, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                String format = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)%60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60,
                        TimeUnit.MILLISECONDS.toMillis(millisUntilFinished)%60);
                tempsJ1.setText(format);
                tempsJ2.setText(format);
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                tempsJ1.setText("00:00:00");
                tempsJ2.setText("00:00:00");
                premierBoutton.setBackgroundColor(Color.rgb(98, 0, 238));
                deuxiemeBoutton.setBackgroundColor(Color.rgb(7, 117, 166));
                troisiemeBoutton.setBackgroundColor(Color.rgb(7, 117, 166));
                quatriemeButton.setBackgroundColor(Color.rgb(7, 117, 166));
                estFini = true;
                Bundle bundle = new Bundle();
                bundle.putString("source", "Rythm.java");
                bundle.putString("score", scoreJ1.getText().toString());
                NavHostFragment.findNavController(Rythm.this)
                        .navigate(R.id.action_Rythm_to_PageScore, bundle);
            }
        };

        premierBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verifChaine == 0 && !estFini) {
                    verifChaine++;
                    compteur++;
                    premierBoutton.setBackgroundColor(Color.rgb(7, 117, 166));
                    deuxiemeBoutton.setBackgroundColor(Color.rgb(98, 0, 238));
                    scoreJ1.setText(String.valueOf(compteur));
                    scoreJ2.setText(String.valueOf(compteur));
                    if (!estCommence) {
                        countDownTimer.start();
                        estCommence = true;
                    }
                }
                else {
                    reInitialiserJeu();
                }
            }
        });

        deuxiemeBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estCommence) {
                    if (verifChaine == 1 && !estFini) {
                        verifChaine++;
                        compteur++;
                        deuxiemeBoutton.setBackgroundColor(Color.rgb(7, 117, 166));
                        troisiemeBoutton.setBackgroundColor(Color.rgb(98, 0, 238));
                        scoreJ1.setText(String.valueOf(compteur));
                        scoreJ2.setText(String.valueOf(compteur));
                    }
                    else {
                        reInitialiserJeu();
                    }
                }
            }
        });

        troisiemeBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estCommence) {
                    if (verifChaine == 2 && !estFini) {
                        verifChaine++;
                        compteur++;
                        troisiemeBoutton.setBackgroundColor(Color.rgb(7, 117, 166));
                        quatriemeButton.setBackgroundColor(Color.rgb(98, 0, 238));
                        scoreJ1.setText(String.valueOf(compteur));
                        scoreJ2.setText(String.valueOf(compteur));
                    }
                    else {
                        reInitialiserJeu();
                    }
                }
            }
        });

        quatriemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estCommence) {
                    if (verifChaine == 3 && !estFini) {
                        verifChaine++;
                        compteur++;
                        quatriemeButton.setBackgroundColor(Color.rgb(7, 117, 166));
                        premierBoutton.setBackgroundColor(Color.rgb(98, 0, 238));
                        scoreJ1.setText(String.valueOf(compteur));
                        scoreJ2.setText(String.valueOf(compteur));
                        verifChaine = 0;
                    } else {
                        reInitialiserJeu();
                    }
                }
            }
        });
    }

    public void reInitialiserJeu() {
        verifChaine = 0;
        compteur = 0;
        premierBoutton.setBackgroundColor(Color.rgb(98, 0, 238));
        deuxiemeBoutton.setBackgroundColor(Color.rgb(7, 117, 166));
        troisiemeBoutton.setBackgroundColor(Color.rgb(7, 117, 166));
        quatriemeButton.setBackgroundColor(Color.rgb(7, 117, 166));
        countDownTimer.onFinish();
    }
}