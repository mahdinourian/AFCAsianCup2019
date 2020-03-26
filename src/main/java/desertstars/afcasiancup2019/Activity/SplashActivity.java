package desertstars.afcasiancup2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import desertstars.afcasiancup2019.R;

public class SplashActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

        Handler h = new Handler();
		h.postDelayed(new Runnable() {

		public void run() {
		finish();
		startActivity(new Intent(SplashActivity.this, MainActivity.class));}
		}, 4000);

	}
}