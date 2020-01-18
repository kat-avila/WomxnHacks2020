package com.example.myapplicationtest1;


import android.os.Build;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    //TextView questionText;
    //String[] questions;
    //int[] answers;
    boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView exitText = (TextView) findViewById(R.id.exitText);
        final Button yesBtn = (Button) findViewById(R.id.yesBtn);
        final Button noBtn = (Button) findViewById(R.id.noBtn);

        final FloatingActionButton exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitText.setVisibility(View.VISIBLE);
                yesBtn.setVisibility(View.VISIBLE);
                noBtn.setVisibility(View.VISIBLE);

                yesBtn.setOnClickListener(new View.OnClickListener() {           // Yes Button
                    @Override
                    public void onClick(View v) {
                        System.exit(0);
                    }
                });
                //no button stuff
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitText.setVisibility(View.GONE);
                        yesBtn.setVisibility(View.GONE);
                        noBtn.setVisibility(View.GONE);
                    }
                });

                }
            });


        //this is me trying to do random question functionality
        //questions = {getString(R.string.question1), getString(R.string.question2), getString(R.string.question3)};
       // answers = {Integer.parseInt(getString(R.string.question1Ans)), Integer.parseInt(getString(R.string.question2Ans)), Integer.parseInt(getString(R.string.question3Ans))};
        //questionText = (TextView) findViewById(R.id.question);


        final ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.layoutBg);
        //#B97299 , #DBD0D9 PINKS
        //#72B9B9, #D0DBDB  BLUES

        //Set default bg and toolbar theme to blue
        bg.setBackgroundColor(0xFFD0DBDB);
        toolbar.setBackgroundColor(0xFF72B9B9);


        final EditText editText = (EditText) findViewById(R.id.editText);
        final Button submitBtn = (Button) findViewById(R.id.submitButton);
        final TextView question = (TextView) findViewById(R.id.question);
        final Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // pin the screen
                    question.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    submitBtn.setVisibility(View.VISIBLE);
                    bg.setBackgroundColor(0xFFDBD0D9);
                    toolbar.setBackgroundColor(0xFFB97299);
                    startLockTask();
                }else{
                    //int rand = (int)(Math.random() * questions.length);
                    Toast toast = Toast.makeText(getApplicationContext(), "Answer the question correctly to unlock study mode.", Toast.LENGTH_SHORT);
                    toast.show();
                        if(answered){
                            question.setVisibility(View.INVISIBLE);
                            editText.setVisibility(View.INVISIBLE);
                            submitBtn.setVisibility(View.INVISIBLE);
                            stopLockTask();
                            bg.setBackgroundColor(0xFFD0DBDB);
                            toolbar.setBackgroundColor(0xFF72B9B9);
                            answered = false;
                        }else{
                            sw.toggle();

                        }
                }
            }
        });


    }


    public void onButtonTap(View v){
            EditText userAns = (EditText) findViewById(R.id.editText);
            String compare = userAns.getText().toString();
            if(compare.equals(getString(R.string.question1Ans))) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Correct! You can now turn off study mode.", Toast.LENGTH_SHORT);
                toast1.show();
                answered = true;
            }
            else {
                answered = false;
                Toast toast2 = Toast.makeText(getApplicationContext(), "Wrong! Try again.", Toast.LENGTH_SHORT);
                toast2.show();
            }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
