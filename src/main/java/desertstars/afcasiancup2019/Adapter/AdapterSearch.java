package desertstars.afcasiancup2019.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import desertstars.afcasiancup2019.SearchItems;
import desertstars.afcasiancup2019.Activity.MainActivity;
import desertstars.afcasiancup2019.R;
import desertstars.afcasiancup2019.Activity.TeamActivity;

public class AdapterSearch extends BaseAdapter {
	
	Context mContext;
	LayoutInflater inflater;
	
	private List<SearchItems> country_list = null;
	private ArrayList<SearchItems> arraylist;
	
	
	public AdapterSearch(Context context,
						 List<SearchItems> country_list) {
		mContext = context;
		this.country_list = country_list;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<SearchItems>();
		this.arraylist.addAll(country_list);
		}

	public class ViewHolder {
		TextView name;
		ImageView icon;
	}

	@Override
	public int getCount() {
		return country_list.size();
	}

	@Override
	public SearchItems getItem(int position) {
		return country_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.row_team_flag, null);

		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int screen_width = metrics.widthPixels;
		int screen_height = metrics.heightPixels;

		view.setLayoutParams(new ListView.LayoutParams(screen_width,screen_height/10));

		holder.name = (TextView) view.findViewById(R.id.title_row);
		holder.icon = (ImageView) view.findViewById(R.id.icon_row);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.name.setText(country_list.get(position).getName());
		holder.name.setTypeface(MainActivity.titleTypeface);
		holder.icon.setImageResource(country_list.get(position).getIcon());
		
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

			Intent intentTeam = new Intent(mContext, TeamActivity.class);
			String Subject_number = country_list.get(position).getRank();
			intentTeam.putExtra("ITEM_SELECTED", Integer.parseInt(Subject_number));
			mContext.startActivity(intentTeam);

			}
		});

		return view;
	}
	
	

	public void filter(String charText) {

		charText = charText.toLowerCase(Locale.getDefault());
		country_list.clear();
		if (charText.length() == 0) {
			country_list.addAll(arraylist);
		} else {
			for (SearchItems searchItems : arraylist) {
				if (searchItems.getName().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					country_list.add(searchItems);
				}
			}
			
		}
		notifyDataSetChanged();
	}

}