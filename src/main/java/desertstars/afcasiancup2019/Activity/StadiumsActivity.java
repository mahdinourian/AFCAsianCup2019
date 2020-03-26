package desertstars.afcasiancup2019.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import desertstars.afcasiancup2019.Adapter.AdapterStadiums;
import desertstars.afcasiancup2019.DATA;
import desertstars.afcasiancup2019.R;

public class StadiumsActivity extends AppCompatActivity {

    TextView title;
    RecyclerView listOfStadiums;

    SharedPreferences SETTING;
    int SIZE,FONT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadiums);

        SETTING = getSharedPreferences("SETTING",0);

        FONT = SETTING.getInt("FONT", 1);
        SIZE = SETTING.getInt("SIZE", 20);

        title = findViewById(R.id.title_stadiums);
        title.setTypeface(MainActivity.titleTypeface);
        title.setTextSize(SIZE);

        listOfStadiums = findViewById(R.id.rec_list_of_stadiums);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listOfStadiums.setLayoutManager(mLayoutManager);
        listOfStadiums.setItemAnimator(new DefaultItemAnimator());


        String[] names = getResources().getStringArray(R.array.stadiums_names);
        String[] cities = getResources().getStringArray(R.array.stadiums_cities);
        String[] capacities = getResources().getStringArray(R.array.stadiums_capacities);
        String[] clubs = getResources().getStringArray(R.array.stadiums_clubs);
        String[] years = getResources().getStringArray(R.array.stadiums_years);

        Integer[] imageStadiums = DATA.stadiumsImages;

        AdapterStadiums adapterStadiums = new AdapterStadiums(names, cities, capacities, clubs, years,  imageStadiums);

        listOfStadiums.setAdapter(adapterStadiums);

    }

}