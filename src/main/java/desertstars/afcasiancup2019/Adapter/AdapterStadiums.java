package desertstars.afcasiancup2019.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import desertstars.afcasiancup2019.Activity.MainActivity;
import desertstars.afcasiancup2019.R;


public class AdapterStadiums extends RecyclerView.Adapter<AdapterStadiums.MyViewHolder>{

	TextView txtName, txtCity, txtCapacity, txtClub, txtYear;
	ImageView imgStadium;

	LinearLayout linearLayout;

	Integer[] stadiumsImages;
	String[] names, cities, capacities, clubs, years;


	public class MyViewHolder extends RecyclerView.ViewHolder{

		public MyViewHolder(View view) {
			super(view);

			txtName = view.findViewById(R.id.stadium_name);
			txtCity = view.findViewById(R.id.stadium_city);
			txtCapacity = view.findViewById(R.id.stadium_capacity);
			txtClub = view.findViewById(R.id.stadium_club);
			txtYear = view.findViewById(R.id.stadium_year);

			imgStadium = view.findViewById(R.id.stadium_image);

            linearLayout = view.findViewById(R.id.layout_stadium);

        }

    }

	public AdapterStadiums(String[] names, String[] cities, String[] capacities,
                           String[] clubs, String[] years, Integer[] stadiumsImages) {

		this.names = names;
		this.cities = cities;
		this.capacities = capacities;
		this.clubs = clubs;
		this.years = years;
		this.stadiumsImages = stadiumsImages;


    }


	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_stadium, parent, false);
		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {

		txtName.setText(names[position]);

		txtCity.setText("شهر:\n"+cities[position]);
		txtCapacity.setText("گنجایش:\n"+capacities[position]);
		txtClub.setText("مالک:\n"+clubs[position]);
		txtYear.setText("سال تاسیس:\n"+years[position]);


		txtName.setTypeface(MainActivity.titleTypeface);
		txtCity.setTypeface(MainActivity.titleTypeface);
		txtCapacity.setTypeface(MainActivity.titleTypeface);
		txtClub.setTypeface(MainActivity.titleTypeface);
		txtYear.setTypeface(MainActivity.titleTypeface);

		imgStadium.setImageResource(stadiumsImages[position]);


        int w = MainActivity.screen_width*95/100;
        int h = w*2/3;

        LinearLayout.LayoutParams par1 = new LinearLayout.LayoutParams(w, h);
        par1.gravity = Gravity.CENTER;
        LinearLayout.LayoutParams par2 = new LinearLayout.LayoutParams(w, h/4);
        par2.gravity = Gravity.CENTER;


        imgStadium.setLayoutParams(par1);
        txtName.setLayoutParams(par2);
        linearLayout.setLayoutParams(par2);


    }

	@Override
	public int getItemCount() {
		return names.length;
	}

}