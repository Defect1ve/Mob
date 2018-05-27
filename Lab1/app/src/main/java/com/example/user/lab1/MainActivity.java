package com.example.user.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] SPINNERLIST= {"Выберите фигуру", "Треугольник", "Квадрат", "Круг"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        Spinner betterSpinner =findViewById(R.id.spinner);
        betterSpinner.setAdapter(arrayAdapter);
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
            wtf = "Киця, рыбка, самолетик, заполни все поля, пожалуйста";
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
            wtf += " Вашего " + spinner + "a";
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                wtf,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, -150);
        toast.show();
    }
}
