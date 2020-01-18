package com.example.myapplicationtest1;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //TextView questionText;
    //String[] questions;
    //int[] answers;
    boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //this is me trying to do random question functionality
        //questions = {getString(R.string.question1), getString(R.string.question2), getString(R.string.question3)};
       // answers = {Integer.parseInt(getString(R.string.question1Ans)), Integer.parseInt(getString(R.string.question2Ans)), Integer.parseInt(getString(R.string.question3Ans))};


        //questionText = (TextView) findViewById(R.id.question);
        Button submitBtn = (Button) findViewById(R.id.submitButton);



        final TextView question = (TextView) findViewById(R.id.question);
        final Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // pin the screen
                    question.setVisibility(View.VISIBLE);
                    startLockTask();
                }else{
                    //int rand = (int)(Math.random() * questions.length);
                        if(answered){
                            stopLockTask();
                        }else{
                            sw.toggle();
                            Toast toast = Toast.makeText(getApplicationContext(), "Answer the question correctly to unlock study mode.", Toast.LENGTH_SHORT);
                            toast.show();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
