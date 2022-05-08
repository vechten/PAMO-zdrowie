package com.example.zdrowie.java.ui.quiz

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.zdrowie.databinding.FragmentQuizBinding
import java.util.ArrayList

class QuizFragment : Fragment() {
    private var tvQueCounter: TextView? = null
    private var tvQue: TextView? = null
    private var btOpt1: Button? = null
    private var btOpt2: Button? = null
    private var btOpt3: Button? = null
    private val queList = ArrayList<Question>()
    private var counter = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentQuizBinding.inflate(inflater, container, false)
        val root: View = binding.root
        btOpt1 = binding.btOpt1
        btOpt2 = binding.btOpt2
        btOpt3 = binding.btOpt3
        tvQue = binding.tvQue
        tvQueCounter = binding.tvQueConter
        queList.add(
            Question(
                "Jeśli ktoś w swojej diecie unika glutenu, to nie powinien jeść:",
                "Orkiszu",
                "Kaszy gryczanej",
                "Kaszy jaglanej",
                "Orkiszu"
            )
        )
        queList.add(
            Question(
                "Ludzie, którzy wykluczają ze swojej diety laktozę, muszą unikać",
                "Mleka",
                "Soi",
                "Jajek",
                "Mleka"
            )
        )
        queList.add(
            Question(
                "Co stanowi podstawę diety Dukana?\n",
                "Węglowodany",
                "Białko",
                "Tłuszcze",
                "Białko"
            )
        )
        queList.add(
            Question(
                "Jeśli nie jesz pokarmów przetworzonych termicznie, to jesteś:\n",
                "Weganinem",
                "Witarianinem",
                "Fleksiganinem",
                "Witarianinem"
            )
        )
        queList.add(
            Question(
                "Co jest podstawą diety makrobiotycznej?",
                "Niełuskane ziarna pszenicy, kukurydzy, żyta, ryżu",
                "Owoce i warzywa",
                "Mleko i jaja",
                "Niełuskane ziarna pszenicy, kukurydzy, żyta, ryżu"
            )
        )
        queList.add(
            Question(
                "Dieta paleo…",
                "Przenosi nas w przyszłośćm proponując kosmiczny posiłek",
                "wykorzystuje zioła i przyprawy znane w średniowieczu",
                "zabiera nas w kuliarną podróż do prehistorii",
                "zabiera nas w kuliarną podróż do prehistorii"
            )
        )
        queList.add(
            Question(
                "Co kryje się pod nazwą superfood?",
                "To różne produkty o niezwykłych właściowościach odżywczych",
                "Bardzo drogie, egzotyczne produkty",
                "Specjalnie przetworzone i lepszone produkty",
                "To różne produkty o niezwykłych właściowościach odżywczych"
            )
        )
        counter = 0
        loadQuestions(counter)
        return root
    }

    @SuppressLint("SetTextI18n")
    fun loadQuestions(n: Int) {
        val q = queList[n]
        tvQueCounter!!.text = (n + 1).toString() + "/" + queList.size
        tvQue!!.text = "#" + (n + 1) + " " + q.que
        btOpt1!!.text = "" + q.opt1
        btOpt2!!.text = "" + q.opt2
        btOpt3!!.text = "" + q.opt3
        btOpt1!!.setOnClickListener {
            if (btOpt1!!.text == q.rightOpt) {
                Toast.makeText(activity, "Correct Answer", Toast.LENGTH_SHORT).show()
                if (counter < queList.size - 1) {
                    counter++
                    loadQuestions(counter)
                } else {
                    Toast.makeText(activity, "All Que Completed!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(activity, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }
        }
        btOpt2!!.setOnClickListener {
            if (btOpt2!!.text == q.rightOpt) {
                Toast.makeText(context, "Correct Answer", Toast.LENGTH_SHORT).show()
                if (counter < queList.size - 1) {
                    counter++
                    loadQuestions(counter)
                } else {
                    Toast.makeText(context, "All Que Completed!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }
        }
        btOpt3!!.setOnClickListener {
            if (btOpt3!!.text == q.rightOpt) {
                Toast.makeText(activity, "Correct Answer", Toast.LENGTH_SHORT).show()
                if (counter < queList.size - 1) {
                    counter++
                    loadQuestions(counter)
                } else {
                    Toast.makeText(activity, "All Que Completed!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(activity, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}