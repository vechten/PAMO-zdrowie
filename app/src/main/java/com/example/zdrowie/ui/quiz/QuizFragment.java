package com.example.zdrowie.ui.quiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.zdrowie.databinding.FragmentQuizBinding;

import java.util.ArrayList;

public class QuizFragment extends Fragment {

    private TextView tvQueCounter, tvQue;

    private Button btOpt1, btOpt2, btOpt3;

    private final ArrayList<Question> queList = new ArrayList<>();

    private int counter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        FragmentQuizBinding binding = FragmentQuizBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btOpt1 = binding.btOpt1;
        btOpt2 = binding.btOpt2;
        btOpt3 = binding.btOpt3;
        tvQue = binding.tvQue;
        tvQueCounter = binding.tvQueConter;

        queList.add(new Question(
                "Jeśli ktoś w swojej diecie unika glutenu, to nie powinien jeść:",
                "Orkiszu",
                "Kaszy gryczanej",
                "Kaszy jaglanej",
                "Orkiszu"));

        queList.add(new Question(
                "Ludzie, którzy wykluczają ze swojej diety laktozę, muszą unikać",
                "Mleka",
                "Soi",
                "Jajek",
                "Mleka"));

        queList.add(new Question(
                "Co stanowi podstawę diety Dukana?\n",
                "Węglowodany",
                "Białko",
                "Tłuszcze",
                "Białko"));

        queList.add(new Question(
                "Jeśli nie jesz pokarmów przetworzonych termicznie, to jesteś:\n",
                "Weganinem",
                "Witarianinem",
                "Fleksiganinem",
                "Witarianinem"));

        queList.add(new Question(
                "Co jest podstawą diety makrobiotycznej?",
                "Niełuskane ziarna pszenicy, kukurydzy, żyta, ryżu",
                "Owoce i warzywa",
                "Mleko i jaja",
                "Niełuskane ziarna pszenicy, kukurydzy, żyta, ryżu"));

        queList.add(new Question(
                "Dieta paleo…",
                "Przenosi nas w przyszłośćm proponując kosmiczny posiłek",
                "wykorzystuje zioła i przyprawy znane w średniowieczu",
                "zabiera nas w kuliarną podróż do prehistorii",
                "zabiera nas w kuliarną podróż do prehistorii"));

        queList.add(new Question(
                "Co kryje się pod nazwą superfood?",
                "To różne produkty o niezwykłych właściowościach odżywczych",
                "Bardzo drogie, egzotyczne produkty",
                "Specjalnie przetworzone i lepszone produkty",
                "To różne produkty o niezwykłych właściowościach odżywczych"));
        counter = 0;
        loadQuestions(counter);
        return root;
    }

    @SuppressLint("SetTextI18n")
    public void loadQuestions(int n) {

        final Question q = queList.get(n);

        tvQueCounter.setText((n + 1) + "/" + queList.size());


        tvQue.setText("#" + (n + 1) + " " + q.getQue());
        btOpt1.setText("" + q.getOpt1());
        btOpt2.setText("" + q.getOpt2());
        btOpt3.setText("" + q.getOpt3());

        btOpt1.setOnClickListener(v -> {

            if (btOpt1.getText().equals(q.getRightOpt())) {
                Toast.makeText(getActivity(), "Correct Answer", Toast.LENGTH_SHORT).show();

                if (counter < (queList.size() - 1)) {
                    counter++;
                    loadQuestions(counter);
                } else {
                    Toast.makeText(getActivity(), "All Que Completed!", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }


        });

        btOpt2.setOnClickListener(v -> {

            if (btOpt2.getText().equals(q.getRightOpt())) {

                Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();

                if (counter < (queList.size() - 1)) {
                    counter++;
                    loadQuestions(counter);
                } else {
                    Toast.makeText(getContext(), "All Que Completed!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }

        });

        btOpt3.setOnClickListener(v -> {

            if (btOpt3.getText().equals(q.getRightOpt())) {

                Toast.makeText(getActivity(), "Correct Answer", Toast.LENGTH_SHORT).show();
                if (counter < (queList.size() - 1)) {
                    counter++;
                    loadQuestions(counter);
                } else {
                    Toast.makeText(getActivity(), "All Que Completed!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
