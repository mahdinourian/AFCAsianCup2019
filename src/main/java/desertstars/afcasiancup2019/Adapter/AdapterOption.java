package desertstars.afcasiancup2019.Adapter;


import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import desertstars.afcasiancup2019.Activity.MainActivity;
import desertstars.afcasiancup2019.R;

public class AdapterOption extends ArrayAdapter<String> {
	private final Activity activity;
	private final String[] text;
	private final Integer[] icon;
	
public AdapterOption(Activity activity, String[] text, Integer[] icon) {
	super(activity, R.layout.row_option, text);
	
	this.activity = activity;
	this.text = text;
	this.icon = icon;
}


public View getView(int position, View convertView, ViewGroup parent) {

	LayoutInflater infalter = activity.getLayoutInflater();
	View v = infalter.inflate(R.layout.row_option, null , true);


	DisplayMetrics metrics = new DisplayMetrics();
	((Activity) activity).getWindowManager().getDefaultDisplay().getMetrics(metrics);

	int screen_width = metrics.widthPixels;
	int screen_height = metrics.heightPixels;


	v.setLayoutParams(new ListView.LayoutParams(screen_width*65/100,screen_height*2/24));


	TextView txt = (TextView) v.findViewById(R.id.title_row);
	ImageView img = (ImageView) v.findViewById(R.id.icon_row);
	
	txt.setText(text[position]);
	txt.setTypeface(MainActivity.titleTypeface);
	img.setImageResource(icon[position]);
	
return v ;

}



}
