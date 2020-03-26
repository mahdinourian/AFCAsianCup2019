package desertstars.afcasiancup2019.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import desertstars.afcasiancup2019.Activity.MainActivity;
import desertstars.afcasiancup2019.R;


public class AdapterTeamFlag extends ArrayAdapter<String> {
	private final Activity activity;
	private final String[] text;
	private final Integer[] icon;
	
public AdapterTeamFlag(Activity activity, String[] text, Integer[] icon) {
	super(activity, R.layout.row_team_flag, text);
	
	this.activity = activity;
	this.text = text;
	this.icon = icon;
}


public View getView(int position, View convertView, ViewGroup parent) {

	LayoutInflater infalter = activity.getLayoutInflater();
	View v = infalter.inflate(R.layout.row_team_flag, null , true);

	TextView txt = v.findViewById(R.id.title_row);
	ImageView img = v.findViewById(R.id.icon_row);


    v.setLayoutParams(    new ListView.LayoutParams(MainActivity.screen_width,MainActivity.screen_height/10));

	txt.setText(text[position]);
	txt.setTypeface(MainActivity.titleTypeface);
	img.setImageResource(icon[position]);
	
return v ;

}



}
