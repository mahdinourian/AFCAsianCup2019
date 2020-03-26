package desertstars.afcasiancup2019.Activity;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import desertstars.afcasiancup2019.MyNotification;
import desertstars.afcasiancup2019.R;

public class SettingActivity extends AppCompatActivity {

	SharedPreferences SETTING;
	SharedPreferences.Editor SETTING_EDITOR;

    TextView txtFontTitle, txtSizeTitle, txtFont, txtSize, txtAlarm, txtMinTitle, txtMin, fontsTitle, sizesTitle;
	Button saveSetting;
	SeekBar seekbarMin;
	Switch switchAlarm;
    RadioGroup fontRadioGroup, sizeRadioGroup;
    RadioButton[] fonts, sizes;
    Dialog fontDialog, sizeDialog;

	AlarmManager alarmManager;
	Intent intent;
	PendingIntent pendingIntent;
	Calendar calendar;

    int SIZE, FONT, MIN, numberFont = 6, numberSize = 9;
    boolean isNotificationOn;
    String[] fontsArray, sizesArray;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		SETTING = getSharedPreferences("SETTING",0);
        SETTING_EDITOR = SETTING.edit();

        SIZE = SETTING.getInt("SIZE", 20);
        FONT = SETTING.getInt("FONT", 1);
        MIN  = SETTING.getInt("MIN", 15);
        isNotificationOn = SETTING.getBoolean("NOTIF",false);


        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(SettingActivity.this, MyNotification.class);
        calendar = Calendar.getInstance();


        txtFont = findViewById(R.id.text_font);
        txtFontTitle = findViewById(R.id.text_font_title);
        txtSize = findViewById(R.id.text_size);
        txtSizeTitle = findViewById(R.id.text_size_title);
        txtAlarm = findViewById(R.id.text_switch_alarm);
        txtMin = findViewById(R.id.text_min);
        txtMinTitle = findViewById(R.id.text_min_title);
        seekbarMin = findViewById(R.id.seekbar_min);
        switchAlarm = findViewById(R.id.switch_alarm);
		saveSetting = findViewById(R.id.save_setting);

		txtFont.setTypeface(MainActivity.titleTypeface);
        txtFontTitle.setTypeface(MainActivity.titleTypeface);
        txtSize.setTypeface(MainActivity.titleTypeface);
        txtSizeTitle.setTypeface(MainActivity.titleTypeface);
        txtAlarm.setTypeface(MainActivity.titleTypeface);
        txtMin.setTypeface(MainActivity.titleTypeface);
        txtMinTitle.setTypeface(MainActivity.titleTypeface);

        saveSetting.setTypeface(MainActivity.titleTypeface);

        fontDialog = new Dialog(SettingActivity.this);
        fontDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fontDialog.setContentView(R.layout.dialog_font);
        fontDialog.getWindow().setLayout(MainActivity.screen_width*3/4,MainActivity.screen_height*3/5);


        fontsTitle = fontDialog.findViewById(R.id.fonts_title );
        fontsTitle.setTypeface(MainActivity.titleTypeface);

        fontRadioGroup = fontDialog.findViewById(R.id.fonts);

        fontsArray = getResources().getStringArray(R.array.fonts);
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,MainActivity.screen_height*3/35);


        fonts = new RadioButton[numberFont];

        for (int i=0; i<numberFont; i++){

            fonts[i] = new RadioButton(this);
            fonts[i].setText(fontsArray[i]);

            params.setMargins(15, 5, 50, 5);

            fonts[i].setLayoutParams(params);
            fonts[i].setTypeface(MainActivity.titleTypeface);
            fontRadioGroup.addView(fonts[i]);

        }


        sizeDialog = new Dialog(SettingActivity.this);
        sizeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sizeDialog.setContentView(R.layout.dialog_size);
        sizeDialog.getWindow().setLayout(MainActivity.screen_width*3/4,MainActivity.screen_height*3/5);


        sizesTitle = sizeDialog.findViewById(R.id.sizes_title);
        sizesTitle.setTypeface(MainActivity.titleTypeface);

        sizeRadioGroup = sizeDialog.findViewById(R.id.sizes);

        sizesArray = getResources().getStringArray(R.array.sizes);

        sizes = new RadioButton[numberSize];

        for (int i=0; i<numberSize; i++){

            sizes[i] = new RadioButton(this);
            sizes[i].setText(sizesArray[i]);

            params.setMargins(15, 5, 50, 5);

            sizes[i].setLayoutParams(params);
            sizes[i].setTypeface(MainActivity.titleTypeface);
            sizeRadioGroup.addView(sizes[i]);

        }

        fonts[FONT].setChecked(true);
        sizes[SIZE - 17].setChecked(true);

        txtFont.setText(fontsArray[FONT]);
        txtSize.setText(""+SIZE);

        switchAlarm.setChecked(isNotificationOn);
        seekbarMin.setProgress(MIN);
        txtMin.setText(MIN + " دقیقه");


        ShowCardView(isNotificationOn);


        txtFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fontDialog.show();
            }
        });

        txtFontTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fontDialog.show();
            }
        });

        txtSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeDialog.show();
            }
        });

        txtSizeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeDialog.show();
            }
        });


        fontRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                FONT = (i-1) % (numberFont + numberSize);
                fontDialog.dismiss();
                txtFont.setText(fontsArray[FONT]);

            }
        });


        sizeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                SIZE = Integer.parseInt(sizesArray[((i-1) % (numberFont + numberSize)) - numberFont]);
                sizeDialog.dismiss();
                txtSize.setText(""+SIZE);

            }
        });



        switchAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ShowCardView(b);
            }
        });


        seekbarMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                MIN = i;
                txtMin.setText(MIN + " دقیقه");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        saveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(switchAlarm.isChecked()){

                    SaveAlarm(1, 0, 5, 19,30, MIN);
                    SaveAlarm(2, 0, 6, 17,0, MIN);
                    SaveAlarm(3, 0, 10, 14,30, MIN);
                    SaveAlarm(4, 0, 10, 19,30, MIN);
                    SaveAlarm(5, 0, 14, 19,30, MIN);
                    SaveAlarm(6, 0, 14, 19,30, MIN);
                    SaveAlarm(7, 0, 6, 14,30, MIN);
                    SaveAlarm(8, 0, 6, 19,30, MIN);
                    SaveAlarm(9, 0, 10, 17,0, MIN);
                    SaveAlarm(10, 0, 11, 14,30, MIN);

                    SaveAlarm(11, 0, 15, 17,0, MIN);
                    SaveAlarm(12, 0, 15, 17,0, MIN);
                    SaveAlarm(13, 0, 7, 14,30, MIN);
                    SaveAlarm(14, 0, 7, 17,0, MIN);
                    SaveAlarm(15, 0, 11, 17,0, MIN);
                    SaveAlarm(16, 0, 11, 19,30, MIN);
                    SaveAlarm(17, 0, 16, 17,0, MIN);
                    SaveAlarm(18, 0, 16, 17,0, MIN);
                    SaveAlarm(19, 0, 7, 19,30, MIN);
                    SaveAlarm(20, 0, 8, 17,0, MIN);

                    SaveAlarm(21, 0, 12, 14,30, MIN);
                    SaveAlarm(22, 0, 12, 17,0, MIN);
                    SaveAlarm(23, 0, 16, 19,30, MIN);
                    SaveAlarm(24, 0, 16, 19,30, MIN);
                    SaveAlarm(25, 0, 8, 19,30, MIN);
                    SaveAlarm(26, 0, 9, 19,30, MIN);
                    SaveAlarm(27, 0, 12, 19,30, MIN);
                    SaveAlarm(28, 0, 13, 14,30, MIN);
                    SaveAlarm(29, 0, 17, 19,30, MIN);
                    SaveAlarm(30, 0, 17, 19,30, MIN);

                    SaveAlarm(31, 0, 9, 14,30, MIN);
                    SaveAlarm(32, 0, 9, 17,0, MIN);
                    SaveAlarm(33, 0, 13, 17,0, MIN);
                    SaveAlarm(34, 0, 13, 19,30, MIN);
                    SaveAlarm(35, 0, 17, 17,0, MIN);
                    SaveAlarm(36, 0, 17, 17,0, MIN);

                    SaveAlarm(37, 0, 20, 14,30, MIN);
                    SaveAlarm(38, 0, 20, 17,30, MIN);
                    SaveAlarm(39, 0, 20, 20,30, MIN);
                    SaveAlarm(40, 0, 21, 14,30, MIN);
                    SaveAlarm(41, 0, 21, 17,30, MIN);
                    SaveAlarm(42, 0, 21, 20,30, MIN);
                    SaveAlarm(43, 0, 22, 16,30, MIN);
                    SaveAlarm(44, 0, 22, 19,30, MIN);
                    SaveAlarm(45, 0, 24, 16,30, MIN);
                    SaveAlarm(46, 0, 24, 19,30, MIN);
                    SaveAlarm(47, 0, 25, 16,30, MIN);
                    SaveAlarm(48, 0, 25, 19,30, MIN);
                    SaveAlarm(49, 0, 28, 17,30, MIN);
                    SaveAlarm(50, 0, 29, 17,30, MIN);
                    SaveAlarm(51, 1, 1, 17,30, MIN);


                    SETTING_EDITOR.putInt("FONT", FONT);
                    SETTING_EDITOR.putInt("SIZE", SIZE);
                    SETTING_EDITOR.putInt("MIN", MIN);
                    SETTING_EDITOR.putBoolean("NOTIF", true);
                    SETTING_EDITOR.apply();

                }else{

                    for (int i=1;i<=51;i++){
                        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager.cancel(pendingIntent);
                    }

                    SETTING_EDITOR.putInt("FONT", FONT);
                    SETTING_EDITOR.putInt("SIZE", SIZE);
                    SETTING_EDITOR.putBoolean("NOTIF", false);
                    SETTING_EDITOR.apply();

                }

                Toast.makeText(getApplicationContext(), "ذخیره شد", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    private void ShowCardView(boolean notif) {

        CardView settingCardview = findViewById(R.id.setting_cardview);

        LinearLayout.LayoutParams par1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MainActivity.screen_width/2);
        par1.gravity = Gravity.CENTER;
        par1.setMargins(25,25,25,25);

        LinearLayout.LayoutParams par2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MainActivity.screen_width*17/100);
        par2.gravity = Gravity.CENTER;
        par2.setMargins(25,25,25,25);


        if(notif){

            settingCardview.setLayoutParams(par1);

            txtMinTitle.setVisibility(View.VISIBLE);
            seekbarMin.setVisibility(View.VISIBLE);
            txtMin.setVisibility(View.VISIBLE);

        }
        else {
            settingCardview.setLayoutParams(par2);

            txtMinTitle.setVisibility(View.GONE);
            seekbarMin.setVisibility(View.GONE);
            txtMin.setVisibility(View.GONE);
        }
    }


    void SaveAlarm(int matchNumber, int month, int day, int hour, int min, int minAlarm){

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("NUMBER_MATCH", matchNumber);

        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), matchNumber, intent, PendingIntent.FLAG_UPDATE_CURRENT);

      // Summer
      // calendar.setTimeZone(TimeZone.getTimeZone("GMT+4:30"));
      // Winter
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+3:30"));


        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        if (min == 30) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, 30 - minAlarm);
            calendar.set(Calendar.SECOND, 0);
        }
        if (min == 0) {
            calendar.set(Calendar.HOUR_OF_DAY, hour - 1);
            calendar.set(Calendar.MINUTE, 59 - minAlarm);
            calendar.set(Calendar.SECOND, 59);
        }



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        }


    }

}
