package com.firebee.pengingattugas;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends Activity{
    TextView nama_alarm, textket;
    String get_namaalarm, get_ketalarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nama_alarm = (TextView) findViewById(R.id.tes);
        textket = (TextView) findViewById(R.id.text_ket);
        Bundle b = getIntent().getExtras();
        get_ketalarm = b.getString("ket_alarm");
        get_namaalarm = b.getString("nama_alarm");
        textket.setText(get_ketalarm);
        nama_alarm.setText(get_namaalarm);
    }
}
