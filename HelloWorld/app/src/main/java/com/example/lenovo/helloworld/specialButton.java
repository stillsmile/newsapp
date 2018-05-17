package com.example.lenovo.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class specialButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_button);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                System.out.println("进入响应事件。。");
              RadioButton bt = (RadioButton) findViewById(checkedId);

                Toast.makeText(specialButton.this,bt.getText()+"  你倒是显示啊 " +bt.getTag(),Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox1.setOnCheckedChangeListener(mycheckbox);
        checkBox2.setOnCheckedChangeListener(mycheckbox);
        checkBox3.setOnCheckedChangeListener(mycheckbox);


    }

    private CompoundButton.OnCheckedChangeListener mycheckbox = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {

                Toast.makeText(specialButton.this,buttonView.getId()+","+
                        buttonView.getText()+"你被选中了",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(specialButton.this,buttonView.getId()+","+
                        buttonView.getText()+"你被取消了",Toast.LENGTH_SHORT).show();
            }
        }
    };

}
