package desertstars.afcasiancup2019;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MyNotification extends BroadcastReceiver{

    NotificationManager notificationManager;

    RequestQueue requestQueue;
    JSONArray gamesArray;

    String myURI = "http://kavir-stars.ir/games.php";
    String[] strGames;
    int numberMatch;

    @Override
	public void onReceive(final Context context, Intent intent) {

		numberMatch = intent.getIntExtra("NUMBER_MATCH", 1);
        strGames = context.getResources().getStringArray(R.array.Games);


		MediaPlayer notifSound = MediaPlayer.create(context, R.raw.notif);
		notifSound.start();

		notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                myURI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            gamesArray = new JSONArray(response);


                            for (int i=36; i<51; i++){ // 36 ???

                                strGames[i] = gamesArray.getString(i);

                            }

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(context)
                                            .setSmallIcon(R.drawable.notif)
                                            .setContentTitle("بازی شروع شد")
                                            .setContentText(strGames[numberMatch-1]);


                            notificationManager.notify(0, mBuilder.build());


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(context)
                                        .setSmallIcon(R.drawable.notif)
                                        .setContentTitle("بازی شروع شد")
                                        .setContentText(strGames[numberMatch-1]);


                        notificationManager.notify(0, mBuilder.build());



                    }

                });

        requestQueue.add(stringRequest);


	}


	
}
