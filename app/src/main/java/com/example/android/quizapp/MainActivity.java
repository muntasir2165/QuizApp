package com.example.android.quizapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void grade(View view) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.consent_checkbox);
        if (!checkBox.isChecked()) {
            Toast.makeText(this, R.string.checkbox_unchecked_message, Toast.LENGTH_SHORT).show();
            return;
        }
        int number_of_questions = 4;
        int points = gradeQuestions();
        EditText nameEditText = (EditText) findViewById(R.id.name);
        String name = nameEditText.getText().toString();
        String message = getString(R.string.quiz_feedback, name, points, number_of_questions);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        //the following alert dialog code was obtained from a stackoverflow post
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
//        alertDialogBuilder.setTitle("");

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.dialog_question))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private int gradeQuestions() {
        //track the number of correct answers
        int points = 0;

        //question 1
        RadioButton question1Answer = (RadioButton) findViewById(R.id.question_1_option_2);
        points += question1Answer.isChecked() ? 1 : 0;

        //question 2
        RadioButton question2Answer = (RadioButton) findViewById(R.id.question_2_option_4);
        points += question2Answer.isChecked() ? 1 : 0;

        //question 3
        EditText question3Answer = (EditText) findViewById(R.id.answer_3);
        points += (question3Answer.getText().toString().equalsIgnoreCase("manifest")) ? 1 : 0;

        //question 4
        CheckBox question4Answer1 = (CheckBox) findViewById(R.id.question_4_option_1); //right option
        CheckBox question4Answer2 = (CheckBox) findViewById(R.id.question_4_option_2); //wrong option
        CheckBox question4Answer3 = (CheckBox) findViewById(R.id.question_4_option_3); //right option
        CheckBox question4Answer4 = (CheckBox) findViewById(R.id.question_4_option_4); //right option
        points += (question4Answer1.isChecked() && !question4Answer2.isChecked() && question4Answer3.isChecked() && question4Answer4.isChecked()) ? 1 : 0;

        return points;
    }
}
