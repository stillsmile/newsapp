package com.example.lenovo.helloworld;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class DataAndTime extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_and_time);

        Calendar calendar = Calendar.getInstance();
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker2);
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(DataAndTime.this,"你选择的时间为："
                                + year + "年"
                                + monthOfYear + "月"
                                + dayOfMonth + "日",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        TimePicker timePicker = new TimePicker(this);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(DataAndTime.this,"你选择的时间为："
                                + hourOfDay + "点"
                                + minute + "分",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dataDialog(View view){
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(DataAndTime.this,"你选择的时间为："
                                + year + "年"
                                + month + "月"
                                + dayOfMonth + "日",
                        Toast.LENGTH_SHORT).show();
            }
        },calendar.get(Calendar.YEAR),
          calendar.get(Calendar.MONTH),
          calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
    public void timeDialog(View view){
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(DataAndTime.this,"你选择的时间为："
                                + hourOfDay + "点"
                                + minute + "分",
                        Toast.LENGTH_SHORT).show();
            }
        },12,12,true).show();

    }
}
