package com.ashutosh.iiitd.mymedicine;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.AlarmClock;

import java.util.ArrayList;
import java.util.List;

public class alarm_activity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener
{
    Spinner spinner2,spinner3;
    private Alarm_adapter mAdapterTime;
    private ArrayList<Alarm_row> alarmList = new ArrayList<>();
    RecyclerView rv_edit_alarm;
    private int hour_alarm_1, minute_alarm_1, hour_alarm_2, minute_alarm_2, hour_alarm_3, minute_alarm_3;
    private String tag_for_alarm = "";

    String categories[]={"Daily","Weekly","Monthly"};
    String Duration[]={"1","2","3","4","5","6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle from_details = getIntent().getExtras();
        //int alarm_type_id = from_details.getInt("alarm_table_id");
        //tag_for_alarm = from_details.getString("med_name");
        setContentView(R.layout.activity_alarm_activity);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        // spinner3.setVisibility(View.GONE);
        //Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements

        //Recycler view for editing alarms

        rv_edit_alarm = (RecyclerView)findViewById(R.id.rv_edit_alarm);
        mAdapterTime = new Alarm_adapter(alarmList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_edit_alarm.setLayoutManager(mLayoutManager);
        rv_edit_alarm.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_edit_alarm.setItemAnimator(new DefaultItemAnimator());
        rv_edit_alarm.setAdapter(mAdapterTime);
        prepareInitialAlarmData();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Duration);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
        List<String> weekdays = new ArrayList<String>();
        weekdays.add("Sunday");
        weekdays.add("Monday");
        weekdays .add("Tuesday");
        weekdays.add("Wednesday");
        weekdays.add("Thursday");
        weekdays.add("Friday");
        weekdays.add("Saturday");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weekdays);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter3);
    }

    //prepare Data for alarm recycler view
    void prepareInitialAlarmData(){


        Alarm_row alarm = new Alarm_row("Breakfast");
        alarmList.add(alarm);
        alarm = new Alarm_row("Lunch");
        alarmList.add(alarm);
        alarm = new Alarm_row("Dinner");
        alarmList.add(alarm);
        mAdapterTime.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }


    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data )
    {
        if(requestcode==1)
            if(resultcode==RESULT_OK)
            {
                int hr=data.getIntExtra("Hour",9);
                hour_alarm_1 = hr;
                int min=data.getIntExtra("Minute",30);
                minute_alarm_1 = min;
                String str="AM";
                if(hr>=12)
                    str="PM";
                if(hr>12)
                    hr=hr-12;

                String hr1=String.valueOf(hr);
                if(hr<10)
                    hr1="0"+hr1;
                String min1=String.valueOf(min);
                if(min<10)
                    min1="0"+min1;


            }
        if(requestcode==2)
            if(resultcode==RESULT_OK)
            {
                int hr=data.getIntExtra("Hour",9);
                hour_alarm_2 = hr;
                int min=data.getIntExtra("Minute",30);
                minute_alarm_2 = min;
                String str="AM";
                if(hr>=12)
                    str="PM";
                if(hr>12)
                    hr=hr-12;

                String hr1=String.valueOf(hr);
                if(hr<10)
                    hr1="0"+hr1;
                String min1=String.valueOf(min);
                if(min<10)
                    min1="0"+min1;
            }

        if(requestcode==3)
            if(resultcode==RESULT_OK)
            {
                int hr=data.getIntExtra("Hour",9);
                hour_alarm_3 = hr;
                int min=data.getIntExtra("Minute",30);
                minute_alarm_3 = min;
                String str="AM";
                if(hr>=12)
                    str="PM";
                if(hr>12)
                    hr=hr-12;

                String hr1=String.valueOf(hr);
                if(hr<10)
                    hr1="0"+hr1;
                String min1=String.valueOf(min);
                if(min<10)
                    min1="0"+min1;
            }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.spinner)
        {
            //  Toast.makeText(this, "Your choose :" + categories[ position],Toast.LENGTH_SHORT).show();
            String item = spin.getItemAtPosition(position).toString();
            TextView textView=(TextView)findViewById(R.id.text);
            if(item.equals("Daily"))
                textView.setText("Days");
            if(item.equals("Weekly")) {

                textView.setText("Weeks");

                spinner3.performClick();
            }
            if(item.equals("Monthly"))
                textView.setText("Months");
        }
        if(spin2.getId() == R.id.spinner2)
        {
            //  Toast.makeText(this, "Your choose :" + Duration[position],Toast.LENGTH_SHORT).show();
        }




        // Showing selected spinner item
        //     Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void SetAlarm(View v){
        //Toast.makeText(getApplicationContext(), "Fuck off !", Toast.LENGTH_SHORT).show();
        //Spinner repeat = (Spinner)findViewById(R.id.spinner1);
        //Spinner duration = (Spinner)findViewById(R.id.spinner2);
        //String repeat_time = repeat.getSelectedItem().toString();
        //int time = Integer.parseInt(duration.getSelectedItem().toString());
        int hour_increment = 0;
        int min_increment = 0;
        boolean alarm_set = false;
        /*if(repeat_time.equals("Daily")){


        }
        else if(repeat_time.equals("Weekly")){
            //do increment for Weekly
        }
        else{
            //do increment for Monthly
        }*/
        //currently doing only for 3 alarms. Will be done dynamically later

        Toast.makeText(getApplicationContext(), "Alarm Set !", Toast.LENGTH_SHORT).show();
        if(alarm_set == true){
            setResult(RESULT_OK);
            finish();
        }
        else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}