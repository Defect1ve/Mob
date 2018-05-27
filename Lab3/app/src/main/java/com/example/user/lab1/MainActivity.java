package com.example.user.lab1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends FragmentActivity {


    String[] SPINNERLIST= {"Выберите фигуру", "Треугольник", "Квадрат", "Круг"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        Spinner betterSpinner =findViewById(R.id.spinner);
        betterSpinner.setAdapter(arrayAdapter);
    }

    public void onOpenClick(View v){
                Intent intent = new Intent(this, File.class);
                FileInputStream fin = null;
                String text = "";
                try {
                    fin = openFileInput("example.txt");
                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes);
                    text = new String (bytes);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.putExtra("text", text);
                startActivity(intent);

    }

    public void write(View view, String wtf) {
        try {
            FileOutputStream fileOutput = openFileOutput("example.txt", MODE_APPEND);
            fileOutput.write(wtf.getBytes());
            fileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onButtonClick(View v)
    {
        EditText n = findViewById(R.id.name);
        Spinner s = findViewById(R.id.spinner);
        CheckBox c1 = findViewById(R.id.check1);
        CheckBox c2 = findViewById(R.id.check2);


        String name = n.getText().toString();
        String spinner = s.getSelectedItem().toString();
        String wtf = "";
        if (name.trim().length() == 0 || spinner == "Выберите фигуру")
            wtf = "Киця, рыбка, самолетик, заполни все поля, пожалуйста\n\n";
        else {
            wtf += name + ", eсли бы за это давали доп баллы, я бы посчитал ";
            if (c1.isChecked() && c2.isChecked())
                wtf += "площадь и периметр";
            else if (c1.isChecked())
                wtf += "площадь";
            else if (c2.isChecked())
                wtf += "периметр";
            else
                wtf += "что угодно для";
            wtf += " Вашего " + spinner + "a\n\n";
        }
        write(v, wtf);
        ItemListDialogFragment.newInstance(wtf).show(getSupportFragmentManager(), "dialog");
    }
}
