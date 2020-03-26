package desertstars.afcasiancup2019.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import desertstars.afcasiancup2019.JustifiedTextView;
import desertstars.afcasiancup2019.R;

public class HistoryActivity extends AppCompatActivity {

	TextView title;
	JustifiedTextView text;
	
	SharedPreferences SETTING;
	int SIZE,FONT;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_history);

	String historyText = getResources().getString(R.string.history_string);

	title = findViewById(R.id.title_history);
	text = findViewById(R.id.text_history);

	SETTING = getSharedPreferences("SETTING",0);

	FONT = SETTING.getInt("FONT", 1); 
	SIZE = SETTING.getInt("SIZE", 20);


	title.setText("تاریخچه جام ملت های آسیا");
	title.setTypeface(MainActivity.titleTypeface);
	title.setTextSize(SIZE);
	
	text.setText(historyText);
	text.setTypeFace(MainActivity.font[FONT]);
	text.setTextSize(2*SIZE);
	text.setLineSpacing(25);
	
	
}
}
