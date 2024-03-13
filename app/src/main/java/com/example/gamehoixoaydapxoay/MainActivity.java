package com.example.gamehoixoaydapxoay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvQuestion;
    private TextView tvContentQuestion;
    private TextView tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4;

    private List<Question> mListQuestion;
    private Question mQuestion;
    private int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        mListQuestion = getListQuestion();
        if (mListQuestion.isEmpty()) {
            return;
        }

        setDataQuestion(mListQuestion.get(currentQuestion));
    }

    private void setDataQuestion(Question question) {
        if (question == null) {
            return;
        }

        mQuestion = question;

        String titleQuestion = "Question " + question.getNumber();
        tvQuestion.setText(titleQuestion);
        tvContentQuestion.setText(question.getContent());
        tvAnswer1.setText(question.getListAnswer().get(0).getContent());
        tvAnswer2.setText(question.getListAnswer().get(1).getContent());
        tvAnswer3.setText(question.getListAnswer().get(2).getContent());
        tvAnswer4.setText(question.getListAnswer().get(3).getContent());

        tvAnswer1.setOnClickListener(this);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4.setOnClickListener(this);
    }

    private void initUi(){
        tvQuestion = findViewById(R.id.tv_question);
        tvContentQuestion = findViewById(R.id.tv_content_question);
        tvAnswer1 = findViewById(R.id.tv_answer1);
        tvAnswer2 = findViewById(R.id.tv_answer2);
        tvAnswer3 = findViewById(R.id.tv_answer3);
        tvAnswer4 = findViewById(R.id.tv_answer4);
    }

    private List<Question> getListQuestion() {
        List<Question> list = new ArrayList<>();

        List<Answer> answerList1 = new ArrayList<>();
        answerList1.add(new Answer("Gà", true));
        answerList1.add(new Answer("Cá", false));
        answerList1.add(new Answer("Bò", false));
        answerList1.add(new Answer("Lợn", false));

        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer("Gà", false));
        answerList2.add(new Answer("Cá", true));
        answerList2.add(new Answer("Bò", false));
        answerList2.add(new Answer("Lợn", false));

        List<Answer> answerList3 = new ArrayList<>();
        answerList3.add(new Answer("Gà", false));
        answerList3.add(new Answer("Cá", false));
        answerList3.add(new Answer("Bò", false));
        answerList3.add(new Answer("Lợn", true));

        list.add(new Question(1, "Con nào là gia cầm ?", answerList1));
        list.add(new Question(2, "Con nào là như heo ?", answerList2));
        list.add(new Question(3, "Con nào là biết bơi ?", answerList3));

        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer1:
                v.setBackgroundResource(R.drawable.bg_orange_comer_30);
                checkAnswer((TextView) v, mQuestion, mQuestion.getListAnswer().get(0));
                break;

            case R.id.tv_answer2:
                v.setBackgroundResource(R.drawable.bg_orange_comer_30);
                checkAnswer((TextView) v, mQuestion, mQuestion.getListAnswer().get(1));
                break;

            case R.id.tv_answer3:
                v.setBackgroundResource(R.drawable.bg_orange_comer_30);
                checkAnswer((TextView) v, mQuestion, mQuestion.getListAnswer().get(2));
                break;

            case R.id.tv_answer4:
                v.setBackgroundResource(R.drawable.bg_orange_comer_30);
                checkAnswer((TextView) v, mQuestion, mQuestion.getListAnswer().get(3));
                break;
        }
    }

    private void checkAnswer(final TextView textView, Question question, Answer answer) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (answer.isCorrect()) {
                    textView.setBackgroundResource((R.drawable.bg_green_comer_30));
                    nextQuestion();
                }
                else {
                    textView.setBackgroundResource((R.drawable.bg_red_comer_30));
                    showAnswerCorrect(question);
                    gameOver();
                }
            }
        }, 1000);
    }

    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               showDialog("Game Over");
            }
        },1000);
    }

    private void showAnswerCorrect(Question question) {
        if (question == null || question.getListAnswer() == null || question.getListAnswer().isEmpty()){
            return;
        }
        if (question.getListAnswer().get(0).isCorrect()){
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_comer_30);
        }
            else if (question.getListAnswer().get(1).isCorrect()){
            tvAnswer2.setBackgroundResource(R.drawable.bg_green_comer_30);
        }
            else if (question.getListAnswer().get(2).isCorrect()){
            tvAnswer3.setBackgroundResource(R.drawable.bg_green_comer_30);
        }
            else if (question.getListAnswer().get(3).isCorrect()){
            tvAnswer4.setBackgroundResource(R.drawable.bg_green_comer_30);
        }


            }


    private void nextQuestion() {
        if(currentQuestion == mListQuestion.size() -1){
            showDialog("You win");
        } else {
            currentQuestion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDataQuestion(mListQuestion.get(currentQuestion));
                }
            },1000);

        }
    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentQuestion = 0;
                setDataQuestion(mListQuestion.get(currentQuestion));
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}