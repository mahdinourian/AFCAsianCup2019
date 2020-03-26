package desertstars.afcasiancup2019;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Calendar;
import java.util.TimeZone;


public class AlarmReceiver extends BroadcastReceiver {

    SharedPreferences SETTING;
    boolean isNotificationOn;
    int MIN;

    AlarmManager alarmManager;
    Intent intent;
    PendingIntent pendingIntent;
    Calendar calendar;


    @Override
    public void onReceive(Context context, Intent myIntent) {

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, MyNotification.class);
        calendar = Calendar.getInstance();

        SETTING = context.getSharedPreferences("SETTING",0);
        MIN  = SETTING.getInt("MIN", 15);
        isNotificationOn = SETTING.getBoolean("NOTIF",false);

        if(isNotificationOn) {

            SaveAlarm(context, 1, 1, 1, 1, 1, MIN);
            SaveAlarm(context,1, 0, 5, 19,30, MIN);
            SaveAlarm(context,2, 0, 6, 17,0, MIN);
            SaveAlarm(context,3, 0, 10, 14,30, MIN);
            SaveAlarm(context,4, 0, 10, 19,30, MIN);
            SaveAlarm(context,5, 0, 14, 19,30, MIN);
            SaveAlarm(context,6, 0, 14, 19,30, MIN);
            SaveAlarm(context,7, 0, 6, 14,30, MIN);
            SaveAlarm(context,8, 0, 6, 19,30, MIN);
            SaveAlarm(context,9, 0, 10, 17,0, MIN);
            SaveAlarm(context,10, 0, 11, 14,30, MIN);

            SaveAlarm(context,11, 0, 15, 17,0, MIN);
            SaveAlarm(context,12, 0, 15, 17,0, MIN);
            SaveAlarm(context,13, 0, 7, 14,30, MIN);
            SaveAlarm(context,14, 0, 7, 17,0, MIN);
            SaveAlarm(context,15, 0, 11, 17,0, MIN);
            SaveAlarm(context,16, 0, 11, 19,30, MIN);
            SaveAlarm(context,17, 0, 16, 17,0, MIN);
            SaveAlarm(context,18, 0, 16, 17,0, MIN);
            SaveAlarm(context,19, 0, 7, 19,30, MIN);
            SaveAlarm(context,20, 0, 8, 17,0, MIN);

            SaveAlarm(context,21, 0, 12, 14,30, MIN);
            SaveAlarm(context,22, 0, 12, 17,0, MIN);
            SaveAlarm(context,23, 0, 16, 19,30, MIN);
            SaveAlarm(context,24, 0, 16, 19,30, MIN);
            SaveAlarm(context,25, 0, 8, 19,30, MIN);
            SaveAlarm(context,26, 0, 9, 19,30, MIN);
            SaveAlarm(context,27, 0, 12, 19,30, MIN);
            SaveAlarm(context,28, 0, 13, 14,30, MIN);
            SaveAlarm(context,29, 0, 17, 19,30, MIN);
            SaveAlarm(context,30, 0, 17, 19,30, MIN);

            SaveAlarm(context,31, 0, 9, 14,30, MIN);
            SaveAlarm(context,32, 0, 9, 17,0, MIN);
            SaveAlarm(context,33, 0, 13, 17,0, MIN);
            SaveAlarm(context,34, 0, 13, 19,30, MIN);
            SaveAlarm(context,35, 0, 17, 17,0, MIN);
            SaveAlarm(context,36, 0, 17, 17,0, MIN);

            SaveAlarm(context,37, 0, 20, 14,30, MIN);
            SaveAlarm(context,38, 0, 20, 17,30, MIN);
            SaveAlarm(context,39, 0, 20, 20,30, MIN);
            SaveAlarm(context,40, 0, 21, 14,30, MIN);
            SaveAlarm(context,41, 0, 21, 17,30, MIN);
            SaveAlarm(context,42, 0, 21, 20,30, MIN);
            SaveAlarm(context,43, 0, 22, 16,30, MIN);
            SaveAlarm(context,44, 0, 22, 19,30, MIN);
            SaveAlarm(context,45, 0, 24, 16,30, MIN);
            SaveAlarm(context,46, 0, 24, 19,30, MIN);
            SaveAlarm(context,47, 0, 25, 16,30, MIN);
            SaveAlarm(context,48, 0, 25, 19,30, MIN);
            SaveAlarm(context,49, 0, 28, 17,30, MIN);
            SaveAlarm(context,50, 0, 29, 17,30, MIN);
            SaveAlarm(context,51, 1, 1, 17,30, MIN);

        }

    }


    private void SaveAlarm(Context context, int matchNumber, int month, int day, int hour, int min, int minAlarm) {

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("NUMBER_MATCH", matchNumber);
        pendingIntent = PendingIntent.getBroadcast(context, matchNumber, intent, PendingIntent.FLAG_UPDATE_CURRENT);



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