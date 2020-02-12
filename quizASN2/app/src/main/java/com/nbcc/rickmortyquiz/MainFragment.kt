package com.nbcc.rickmortyquiz


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.nbcc.rickmortyquiz.databinding.FragmentMainBinding


/**
 * Main fragment with questions.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    //question that are currently displayed in a view
    private var questionIndex = 0;

    //list of questions
    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       questionIndex = savedInstanceState?.getInt("questionId") ?: questionIndex

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main,
            container, false
        )

        //options menu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateView(questionIndex)

        assignListeners()

    }
    //options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    //Preserve the state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("questionId", questionIndex)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        questionIndex = savedInstanceState?.getInt("questionId") ?: questionIndex
//        updateView(questionIndex)
//
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//
//        questionIndex = savedInstanceState?.getInt("questionId") ?: questionIndex
//        updateView(questionIndex)
//
//    }

    /**
     * Shows new question in a text label
     */
    private fun updateView(questionId : Int) {
        //set text to resource Id(question)
        binding.apply {
            questionLabel.setText(questionBank[questionId].resourceId)
        }

    }

    private fun assignListeners(){
        //assign event listeners to answer buttons
        binding.apply {
            falseButton.setOnClickListener { evaluateAnswer(false) }
            trueButton.setOnClickListener { evaluateAnswer(true) }

            //iterate through questions
            nextButton.setOnClickListener {
                questionIndex++;
                if (questionIndex % questionBank.size == 0) {
                    questionIndex = 0;
                }
                updateView(questionIndex);
            }

            prevButton.setOnClickListener {
                questionIndex--;
                if (questionIndex == -1) {
                    questionIndex = questionBank.size - 1;
                }
                updateView(questionIndex);
            }

            //navigate to Cheat Fragment
            cheatButton.setOnClickListener{

                val questionText = questionBank[questionIndex].resourceId
                val answer = questionBank[questionIndex].answer.toString();


                val action = MainFragmentDirections
                    .actionMainFragmentToCheatFragment(answer, questionText)

                view!!.findNavController().navigate(action)
            }

        }
    }

    /**
     * Evaluates user answer and triggers the toast with correct answer
     * @param userAnswer true or false user input
     */
    private fun evaluateAnswer(userAnswer: Boolean) {
        if (userAnswer == questionBank[questionIndex].answer) {
            Toast.makeText(
                requireContext(),
                "Correct!", Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(
                requireContext(),
                "Incorrect!", Toast.LENGTH_SHORT
            ).show();
        }
    }


}
