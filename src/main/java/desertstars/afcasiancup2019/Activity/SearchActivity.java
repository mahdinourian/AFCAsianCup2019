package desertstars.afcasiancup2019.Activity;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import desertstars.afcasiancup2019.Adapter.AdapterSearch;
import desertstars.afcasiancup2019.DATA;
import desertstars.afcasiancup2019.R;
import desertstars.afcasiancup2019.SearchItems;

public class SearchActivity extends AppCompatActivity {

	ListView list;
	AdapterSearch adapter;
	SearchView searchView;

	String[] teamNumbers;
	String[] teamNames;
	Integer[] flags;

	ArrayList<SearchItems> searchItems = new ArrayList<>();


	@SuppressLint("ResourceAsColor")
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		searchView = findViewById(R.id.input_search);
		list = findViewById(R.id.list_search);

		teamNumbers = getResources().getStringArray(R.array.TeamNumbers);
		teamNames = getResources().getStringArray(R.array.TeamNames);
		flags = DATA.flags;


		for (int i=0 ;i<teamNumbers.length ;i++)
		{
			SearchItems searchItems = new SearchItems(teamNumbers[i], teamNames[i], flags[i]);
			this.searchItems.add(searchItems);
		}


		adapter = new AdapterSearch(this, searchItems);

		list.setAdapter(adapter);

        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = searchPlate.findViewById(searchTextId);

        searchText.setTextColor(Color.parseColor("#ffffff"));
        searchText.setTypeface(MainActivity.titleTypeface);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				adapter.filter(newText);
				return false;
			}
		});


	}


}
