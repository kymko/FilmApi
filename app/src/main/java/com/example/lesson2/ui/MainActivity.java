package com.example.lesson2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lesson2.App;
import com.example.lesson2.R;
import com.example.lesson2.data.model.Films;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btn_basket);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(MainActivity.this,R.id.fragment);
                navController.navigate(R.id.roomFragment);


            }
        });




    }

}




//        ДЗ / Дедлайн 6 - Июня (Вторник)

//        1. Получить список фильмов с https://ghibliapi.herokuapp.com/films
//        2. Отобразить список фильмов в RecyclerView
//        3. По нажатию на фильм открывать новый экран с подробным описанием фильма (сделать запрос на получения фильма по id)
//        4. (Дополнительно) Добавить три кнопки (peolple, species, locations) по нажатию на эти
//        кнопки открывать новый экран и выводить туда результат (в RecyclerView)