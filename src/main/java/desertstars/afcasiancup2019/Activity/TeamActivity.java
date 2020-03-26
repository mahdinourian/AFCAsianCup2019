package desertstars.afcasiancup2019.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import desertstars.afcasiancup2019.DATA;
import desertstars.afcasiancup2019.JustifiedTextView;
import desertstars.afcasiancup2019.R;

public class TeamActivity extends AppCompatActivity {

	TextView title;
	JustifiedTextView txtSubject, txtFormation;
	ImageView imgPlayer, imgTeam;
	
	SharedPreferences SETTING;
	int SIZE,FONT;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
	

		
		String[] subjectTitle = getResources().getStringArray(R.array.TeamNames);
		String[] subjectText = getResources().getStringArray(R.array.subject_team_texts);
		String[] subjectFormation = getResources().getStringArray(R.array.subject_formation);
		
		Integer[] playerImages = DATA.playerImages;
		Integer[] teamImages = DATA.teamImages;
				
		title = findViewById(R.id.subject_team_title);
		txtSubject = findViewById(R.id.subject_team_text);
		txtFormation = findViewById(R.id.subject_team_formation);
		imgPlayer = findViewById(R.id.subject_team_image_player);
		imgTeam = findViewById(R.id.subject_team_image_team);
		
		
		SETTING = getSharedPreferences("SETTING",0);

		FONT = SETTING.getInt("FONT", 1); 
		SIZE = SETTING.getInt("SIZE", 20);

		Intent intentSubjectTeam = getIntent();

		int itemSelected = intentSubjectTeam.getIntExtra("ITEM_SELECTED", 1);

		title.setText(subjectTitle[itemSelected - 1]);
		title.setTypeface(MainActivity.titleTypeface);
		title.setTextSize(SIZE);

		txtSubject.setText(subjectText[itemSelected - 1]);
		txtSubject.setTypeFace(MainActivity.font[FONT]);
		txtSubject.setTextSize(2*SIZE);
        txtSubject.setLineSpacing(25);

		txtFormation.setText("");
//		txtFormation.setText(subjectFormation[itemSelected - 1]);
		txtFormation.setTypeFace(MainActivity.font[FONT]);
		txtFormation.setTextSize(2*SIZE);
        txtFormation.setLineSpacing(25);

		imgPlayer.setImageResource(playerImages[itemSelected - 1]);
		imgTeam.setImageResource(teamImages[itemSelected - 1]);
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		int screen_width = displayMetrics.widthPixels;

		int w = screen_width*9/10;
		int h1 = w*6/9;
		int h2 = w*19/34;
		
	
		 LinearLayout.LayoutParams parPlayer = new LinearLayout.LayoutParams(w, h1);
		 parPlayer.gravity = Gravity.CENTER;
		 imgPlayer.setLayoutParams(parPlayer);

		 LinearLayout.LayoutParams parTeam = new LinearLayout.LayoutParams(w, h2);
		 parTeam.gravity = Gravity.CENTER;
		 imgTeam.setLayoutParams(parTeam);


    }

	
	}
	
