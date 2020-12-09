package com.example.scrollview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;

public class reminderActivity extends AppCompatActivity {
    private TextInputEditText venu;
    private static final String TAG = "reminderActivity";

    //Flag:Notification and Alarm button
    private boolean nFlag = false;
    private boolean aFlag = false;
    final Calendar myCalendar = Calendar.getInstance();

    //notification and alarm onclick listener
    public void notification(View view) {

        if(nFlag)
        {
            nFlag = false;
            view.setAlpha(0.5f);
        }
        else
        {
            nFlag=true;
            view.setAlpha(1);
        }

    }
    public void alarm(View view) {

        if(aFlag)
        {
            aFlag = false;
            view.setAlpha(0.5f);

        }
        else
        {
            aFlag=true;
            view.setAlpha(1);
        }

    }

    //date and time:EditText
    TextInputEditText date;
    TextInputEditText time;

    //spinner (reminder type):
    Spinner spin;
    String[] type = {"Academics","Groups","Personal"} ;


    //Onclick for tick:
    public void  submit (View view) {
        Log.d(TAG, "submit: button clicked");
        String text = spin.getSelectedItem().toString();
        Log.d(TAG, "submit: " + text);
        startActivity(new Intent(reminderActivity.this,MainActivity.class));
        finish();

    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        venu = findViewById(R.id.venu);
        date = (TextInputEditText) findViewById(R.id.event_date);
        time = (TextInputEditText)findViewById(R.id.event_time);
        spin = (Spinner) findViewById(R.id.spinner);



        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_row,type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);


        //Taking date in Edit Text using DatePicker and on focus change listener
        final DatePickerDialog.OnDateSetListener datelistener = new DatePickerDialog.OnDateSetListener() {


            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                date.clearFocus();
            }

        };

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    new DatePickerDialog(reminderActivity.this, datelistener, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    date.clearFocus();

                }
            }

        });

        //Taking time in EditText using onFocusChange listener and TimePicker Dialog box


        time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    final int AMPM = mcurrentTime.get(Calendar.AM_PM);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(reminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            updateTime(selectedHour,selectedMinute);

                        }
                    }, hour, minute, false);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
                time.clearFocus();
            }

        });
        venu.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    venu.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                    return true;
                }
                return false;
            }
        });



    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "E, dd MMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }                    //feeding date to Edit Text
    private void updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        time.setText(aTime);
    }  //converting to 12 hour format and feeding in Edit text
 }