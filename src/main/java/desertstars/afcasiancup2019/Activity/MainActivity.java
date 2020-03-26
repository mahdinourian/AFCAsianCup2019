package desertstars.afcasiancup2019.Activity;

import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.wdullaer.materialdatetimepicker.TypefaceHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.util.PersianCalendar;

import java.util.Calendar;

import desertstars.afcasiancup2019.Adapter.AdapterOption;
import desertstars.afcasiancup2019.Adapter.AdapterTeamFlag;
import desertstars.afcasiancup2019.DATA;
import desertstars.afcasiancup2019.R;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

	DrawerLayout drawer;
	TabHost tabHost;
	ListView ListTeam, list_option;
	ImageView imageKnockout;
	TextView groupTitle, groupMatch,
	countDay, countHour, countMin, countSec, 
    titleDay, titleHour, titleMin, titleSec; 
	Button option, calender;

	DisplayMetrics metrics;
	AdapterTeamFlag[] adapter_group = new AdapterTeamFlag[9];

	boolean doubleBackToExitPressedOnce = false;
	int groupNumber = 1;
    String[] groupLables;

    public static int screen_width, screen_height;
    public static Typeface titleTypeface;
    public static Typeface[] font = new Typeface[6];

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		titleTypeface = Typeface.createFromAsset(getAssets(), "font/Yekan.ttf");

		font[0] = Typeface.createFromAsset(getAssets(), "font/Nazanin.ttf");
		font[1] = Typeface.createFromAsset(getAssets(), "font/Yekan.ttf");
		font[2] = Typeface.createFromAsset(getAssets(), "font/Koodak.ttf");
		font[3] = Typeface.createFromAsset(getAssets(), "font/Yaghut.ttf");
		font[4] = Typeface.createFromAsset(getAssets(), "font/Mitra.ttf");
		font[5] = Typeface.createFromAsset(getAssets(), "font/Traffic.ttf");



		groupTitle = findViewById(R.id.group_name);
		groupMatch = findViewById(R.id.group_match);
		ListTeam = findViewById(R.id.list_grouping);
		imageKnockout = findViewById(R.id.image_knockout);

		countDay = findViewById(R.id.countdown_day);
		countHour = findViewById(R.id.countdown_hour);
		countMin = findViewById(R.id.countdown_min);
		countSec = findViewById(R.id.countdown_sec);

		titleDay = findViewById(R.id.title_day);
		titleHour = findViewById(R.id.title_hour);
		titleMin = findViewById(R.id.title_min);
		titleSec = findViewById(R.id.title_sec);

		
		drawer = findViewById(R.id.drawer_layout);
		list_option = findViewById(R.id.left_drawer);

		option = findViewById(R.id.opt);
		calender = findViewById(R.id.calender);

	    titleDay.setTypeface(titleTypeface);
	    titleHour.setTypeface(titleTypeface);
	    titleMin.setTypeface(titleTypeface);
	    titleSec.setTypeface(titleTypeface);

		countDay.setTypeface(titleTypeface);
		countHour.setTypeface(titleTypeface);
		countMin.setTypeface(titleTypeface);
		countSec.setTypeface(titleTypeface);

		groupTitle.setTypeface(titleTypeface);
		groupMatch.setTypeface(titleTypeface);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        screen_width = metrics.widthPixels;
        screen_height = metrics.heightPixels;

        LinearLayout.LayoutParams groupMatchLayout = new LinearLayout.LayoutParams(screen_width*2/3, screen_height/12);
        groupMatchLayout.gravity = Gravity.CENTER;
        groupMatch.setLayoutParams(groupMatchLayout);

        groupLables = getResources().getStringArray(R.array.group_labels);


        SetCountDown();

		SetOptionItems();

		SetGrouping();



		imageKnockout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

			startActivity(new Intent(MainActivity.this, KnockoutStageActivity.class));

			}
		});


		calender.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

                PersianCalendar minDate = new PersianCalendar();
				minDate.set(Calendar.YEAR, 2019);
				minDate.set(Calendar.MONTH, 0);
                minDate.set(Calendar.DAY_OF_MONTH, 5);

				PersianCalendar maxDate = new PersianCalendar();
				maxDate.set(Calendar.YEAR, 2019);
				maxDate.set(Calendar.MONTH, 1);
                maxDate.set(Calendar.DAY_OF_MONTH, 1);


				PersianCalendar[] disableDays = new PersianCalendar[7];

				for(int i=0; i<7; i++){

					disableDays[i] = new PersianCalendar();
					disableDays[i].set(Calendar.YEAR, 2019);
					disableDays[i].set(Calendar.MONTH, 0);

				}

				disableDays[0].set(Calendar.DAY_OF_MONTH, 18);
				disableDays[1].set(Calendar.DAY_OF_MONTH, 19);
				disableDays[2].set(Calendar.DAY_OF_MONTH, 23);
				disableDays[3].set(Calendar.DAY_OF_MONTH, 26);
				disableDays[4].set(Calendar.DAY_OF_MONTH, 27);
				disableDays[5].set(Calendar.DAY_OF_MONTH, 30);
				disableDays[6].set(Calendar.DAY_OF_MONTH, 31);



				TypefaceHelper.get(MainActivity.this, "Yekan");

				DatePickerDialog dpd = DatePickerDialog.newInstance(
						MainActivity.this,

                        1397,
						9,
						15

				);

				dpd.setTypeface("Yekan", "Yekan", "Yekan");
				dpd.show(getFragmentManager(), "Datepickerdialog");
				dpd.setVersion(DatePickerDialog.Version.VERSION_2);
                dpd.setThemeDark(true);
				dpd.setMinDate(minDate);
				dpd.setMaxDate(maxDate);
				dpd.setDisabledDays(disableDays);

		}
		});


		option.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (drawer.isDrawerOpen(list_option)) drawer.closeDrawer(list_option);
				else drawer.openDrawer(list_option);

			}
		});


		list_option.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
									long arg3) {

				switch (position + 1) {
					case 1: {
						drawer.closeDrawer(list_option);
                        startActivity(new Intent(MainActivity.this, HistoryActivity.class));
					}
					break;
					case 2: {
						drawer.closeDrawer(list_option);
                        startActivity(new Intent(MainActivity.this, StadiumsActivity.class));
					}
					break;
					case 3: {
						drawer.closeDrawer(list_option);
						startActivity(new Intent(MainActivity.this, SettingActivity.class));
					}
					break;
					case 4: {
						drawer.closeDrawer(list_option);
					    startActivity(new Intent(MainActivity.this, SearchActivity.class));
					}
					break;
					case 5: {
						drawer.closeDrawer(list_option);
						Intent browserIntent = new Intent(Intent.ACTION_EDIT, Uri.parse("https://cafebazaar.ir/app/desertstars.afcasiancup2019/?l=fa"));
						startActivity(browserIntent);
					}
					break;
					case 6: {
						drawer.closeDrawer(list_option);
						Intent browserIntent2 = new Intent(Intent.ACTION_VIEW);
						browserIntent2.setData(Uri.parse("bazaar://collection?slug=by_author&aid=mahdinourianzavareh"));
						browserIntent2.setPackage("com.farsitel.bazaar");
						startActivity(browserIntent2);
					}
					break;
					case 7: {
						drawer.closeDrawer(list_option);
						startActivity (new Intent(MainActivity.this, AboutActivity.class));
					}
					break;

				}

			}
		});


		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tag) {

				groupNumber = Integer.parseInt(tag);
				setTabColor(tabHost);
				AdaptingGroup(groupNumber);

				if (Integer.parseInt(tag) == 7) {
					ListTeam.setVisibility(View.GONE);
					imageKnockout.setVisibility(View.VISIBLE);
				} else {
					ListTeam.setVisibility(View.VISIBLE);
					imageKnockout.setVisibility(View.GONE);
				}
			}
		});


		ListTeam.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int positionList,
					long arg3) {

				Intent intentTeam = new Intent(MainActivity.this, TeamActivity.class);
				int itemSelected = (4 * (groupNumber - 1)) + (positionList + 1);
				intentTeam.putExtra("ITEM_SELECTED", itemSelected);
				startActivity(intentTeam);

			}
		});


		groupMatch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if(groupNumber != 7) {
					Intent intentGrouping = new Intent(MainActivity.this, InformationActivity.class);
					intentGrouping.putExtra("GROUP_NUMBER", groupNumber);
					startActivity(intentGrouping);
				}else{
					startActivity(new Intent(MainActivity.this, KnockoutActivity.class));
				}

			}
		});



	}

	private void SetCountDown() {

		Time now = new Time();
		now.setToNow();

		int yearDay = now.yearDay;
		int daysLeft = 0;
		int hourLeft, minLeft, secLeft, overflow1 = 0, overflow2 = 0;
		int isRemain = 1;

		secLeft = (60 - now.second);

		minLeft = (29 - now.minute);
		if(minLeft < 0)
		{
			minLeft = minLeft + 60;
			overflow1=1;
		}


		hourLeft = (19 - now.hour - overflow1);
		if(hourLeft < 0)
		{
			hourLeft = hourLeft + 24;
			overflow2=1;
		}


		if(now.year == 2019) {
			daysLeft = 4 - yearDay - overflow2;
		}
		if(now.year == 2018) {
			daysLeft = 369 - yearDay - overflow2;
		}
		if(daysLeft < 0) isRemain = 0;


		long timeLeft = (secLeft + (minLeft * 60) + (hourLeft * 60 * 60) +
				(daysLeft * 60 * 60 *24))* isRemain;

		new CountDownTimer(timeLeft*1000, 1000) {

			public void onTick(long millisUntilFinished) {

				int seconds = (int) (millisUntilFinished / 1000) % 60 ;
				int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
				int hours   = (int) ((millisUntilFinished / (1000*60*60)) % 24);
				int days   = (int) ((millisUntilFinished / (1000*60*60*24)));

				countSec.setText(""+seconds);
				countMin.setText(""+minutes);
				countHour.setText(""+hours);
				countDay.setText("" + days);

			}

			public void onFinish() {
				countSec.setText(""+0);
			}

		}.start();



	}

	private void SetGrouping() {

        String[] nameGroup1 = getResources().getStringArray(R.array.group_1);
		String[] nameGroup2 = getResources().getStringArray(R.array.group_2);
		String[] nameGroup3 = getResources().getStringArray(R.array.group_3);
		String[] nameGroup4 = getResources().getStringArray(R.array.group_4);
		String[] nameGroup5 = getResources().getStringArray(R.array.group_5);
		String[] nameGroup6 = getResources().getStringArray(R.array.group_6);


		Integer[] flagGroup1 = DATA.flagGroup1;
		Integer[] flagGroup2 = DATA.flagGroup2;
		Integer[] flagGroup3 = DATA.flagGroup3;
		Integer[] flagGroup4 = DATA.flagGroup4;
		Integer[] flagGroup5 = DATA.flagGroup5;
		Integer[] flagGroup6 = DATA.flagGroup6;


		String[][] nameGroups = new String[7][4];
		Integer[][] flagGroups = new Integer[7][4];

		for(int i=0;i<=3;i++){

			nameGroups[1][i] = nameGroup1[i];
			nameGroups[2][i] = nameGroup2[i];
			nameGroups[3][i] = nameGroup3[i];
			nameGroups[4][i] = nameGroup4[i];
			nameGroups[5][i] = nameGroup5[i];
			nameGroups[6][i] = nameGroup6[i];

			flagGroups[1][i] = flagGroup1[i];
			flagGroups[2][i] = flagGroup2[i];
			flagGroups[3][i] = flagGroup3[i];
			flagGroups[4][i] = flagGroup4[i];
			flagGroups[5][i] = flagGroup5[i];
			flagGroups[6][i] = flagGroup6[i];

		}



		for(int i=1; i<=6; i++){

			adapter_group[i] = new AdapterTeamFlag(this, nameGroups[i], flagGroups[i]);

		}




		View[] viewTab = new View[8];
		String[] labelTabs = new String[8];

		for(int i=1; i<=6; i++){
			labelTabs[i] = groupLables[i-1];
			viewTab[i] = createTabView(labelTabs[i]);
		}

		labelTabs[7] = "KO";
		viewTab[7] = createTabView(labelTabs[7]);

		tabHost=findViewById(R.id.tabhost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("1");
		spec1.setContent(R.id.layout_grouping);
		spec1.setIndicator(viewTab[1]);

		TabSpec spec2=tabHost.newTabSpec("2");
		spec2.setContent(R.id.layout_grouping);
		spec2.setIndicator(viewTab[2]);

		TabSpec spec3=tabHost.newTabSpec("3");
		spec3.setContent(R.id.layout_grouping);
		spec3.setIndicator(viewTab[3]);

		TabSpec spec4=tabHost.newTabSpec("4");
		spec4.setContent(R.id.layout_grouping);
		spec4.setIndicator(viewTab[4]);

		TabSpec spec5=tabHost.newTabSpec("5");
		spec5.setContent(R.id.layout_grouping);
		spec5.setIndicator(viewTab[5]);

		TabSpec spec6=tabHost.newTabSpec("6");
		spec6.setContent(R.id.layout_grouping);
		spec6.setIndicator(viewTab[6]);

		TabSpec spec7 = tabHost.newTabSpec("7");
		spec7.setContent(R.id.layout_grouping);
		spec7.setIndicator(viewTab[7]);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
		tabHost.addTab(spec4);
		tabHost.addTab(spec5);
		tabHost.addTab(spec6);
		tabHost.addTab(spec7);

		setTabColor(tabHost);
		AdaptingGroup(groupNumber);

	}

	private void SetOptionItems() {

		String[] optionItems = getResources().getStringArray(R.array.options);

		Integer[] optionIcons = DATA.optionIcons;

		AdapterOption adapter_option = new AdapterOption(MainActivity.this, optionItems, optionIcons);
		list_option.setBackgroundResource(R.color.colorPrimary);

        ViewGroup.LayoutParams par = list_option.getLayoutParams();
		par.width = screen_width*65/100;
		list_option.setLayoutParams(par);


		list_option.setAdapter(adapter_option);

	}

	private  View createTabView(String name) {
		View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_group, null);
		
		TextView tvName = view.findViewById(R.id.label_group_grid);
		tvName.setTypeface(titleTypeface);
		tvName.setText(name);
		tvName.setTextColor(Color.BLACK);
		
		return view;
	}

	public void AdaptingGroup(int groupNumber) {


		if(groupNumber != 7 ) {
			groupTitle.setText("گروه " + groupLables[groupNumber - 1]);
			groupMatch.setText("جدول و نتایج گروه " + groupLables[groupNumber - 1]);

			ListTeam.setAdapter(adapter_group[groupNumber]);
		}else{
			groupTitle.setText("مرحله حذفی");
			groupMatch.setText("بازی ها و نتایج مرحله حذفی");
		}
	}
	
	public void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.bg_grid_unselected);
            TextView tv = tabhost.getTabWidget().getChildAt(i).findViewById(R.id.label_group_grid); //Unselected Tabs
    		tv.setTypeface(titleTypeface);

        }

        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
                .setBackgroundResource(R.drawable.selected);
        TextView tv = tabhost.getCurrentTabView().findViewById(R.id.label_group_grid); //for Selected Tab
		tv.setTypeface(titleTypeface,Typeface.BOLD);
        }

	public void onBackPressed() {

		if (doubleBackToExitPressedOnce) {
		super.onBackPressed();
		return;
		}
		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "برای خروج مجددا دکمه بازگشت را بزنید", Toast.LENGTH_SHORT).show();
		new Handler().postDelayed(new Runnable() {
		@Override
		public void run() {
		doubleBackToExitPressedOnce=false;
		}
		}, 2000);

	}

		@Override
		public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

			Intent intentCalender = new Intent(MainActivity.this, CalenderActivity.class);
			intentCalender.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Bundle bundle = new Bundle();
			bundle.putInt("DAY_SELECTED", dayOfMonth);
			bundle.putInt("MONTH_SELECTED", monthOfYear);
			intentCalender.putExtras(bundle);
            startActivity(intentCalender);

			}

}
