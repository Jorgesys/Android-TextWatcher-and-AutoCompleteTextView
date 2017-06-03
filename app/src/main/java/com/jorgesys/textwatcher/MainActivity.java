package com.jorgesys.textwatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText wordEditText;
    private TextView textView;
    private InstantAutoComplete txtSearch;
    private SuggestionsAdapter sugerenciasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordEditText = (EditText) findViewById(R.id.word);
        textView = (TextView) findViewById(R.id.wordHint);
        textView.setVisibility(View.GONE);

        // Set TextWatcher listener
        wordEditText.addTextChangedListener(wordWatcher);

        txtSearch = (InstantAutoComplete) findViewById(R.id.search);

        sugerenciasAdapter = new SuggestionsAdapter(this, createList());
        txtSearch.setAdapter(sugerenciasAdapter);

    }

    private List<String> createList(){
        String[] array = {"arbeit","bebelus" , "carpatos", "deere", "earn", "fut", "gato", "heinz", "il nino", "Jorgesys","kelner", "light", "mere", "nuuu", "oglinda", "puisor", "queso"};
        return Arrays.asList(array);
    }



    private final TextWatcher wordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                textView.setVisibility(View.GONE);
            } else{
                textView.setText("You have entered : " + wordEditText.getText());
            }
        }
    };





}
