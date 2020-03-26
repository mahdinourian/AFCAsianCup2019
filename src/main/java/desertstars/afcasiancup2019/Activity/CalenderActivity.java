package desertstars.afcasiancup2019.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import desertstars.afcasiancup2019.Adapter.AdapterCalender;
import desertstars.afcasiancup2019.DATA;
import desertstars.afcasiancup2019.R;

public class CalenderActivity extends AppCompatActivity {

    RecyclerView calenderList;
    TextView calenderTitle;

    String[] teams1, teams2, times;
    Integer[]flag1, flag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        calenderList = (RecyclerView) findViewById(R.id.rec_calender);
        calenderTitle = (TextView) findViewById(R.id.calender_titie);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        calenderList.setLayoutManager(mLayoutManager);
        calenderList.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = this.getIntent().getExtras();
        int day = bundle.getInt("DAY_SELECTED");
        int month = bundle.getInt("MONTH_SELECTED");

        if (month==10)
            calenderTitle.setText(day + " دی 1397");
        if (month==11)
            calenderTitle.setText(day + " بهمن 1397");

        calenderTitle.setTypeface(MainActivity.titleTypeface);

        if(month == 10) {
            switch (day) {

                case 15: {
                    teams1 = getResources().getStringArray(R.array.Day1_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day1_Teams_Right);
                    times = getResources().getStringArray(R.array.Day1_Timees);

                    flag1 = DATA.Day1_Teams_Left;
                    flag2 = DATA.Day1_Teams_Right;
                }
                break;

                case 16: {
                    teams1 = getResources().getStringArray(R.array.Day2_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day2_Teams_Right);
                    times = getResources().getStringArray(R.array.Day2_Timees);

                    flag1 = DATA.Day2_Teams_Left;
                    flag2 = DATA.Day2_Teams_Right;
                }
                break;

                case 17: {
                    teams1 = getResources().getStringArray(R.array.Day3_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day3_Teams_Right);
                    times = getResources().getStringArray(R.array.Day3_Timees);

                    flag1 = DATA.Day3_Teams_Left;
                    flag2 = DATA.Day3_Teams_Right;
                }
                break;

                case 18: {
                    teams1 = getResources().getStringArray(R.array.Day4_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day4_Teams_Right);
                    times = getResources().getStringArray(R.array.Day4_Timees);

                    flag1 = DATA.Day4_Teams_Left;
                    flag2 = DATA.Day4_Teams_Right;
                }
                break;

                case 19: {
                    teams1 = getResources().getStringArray(R.array.Day5_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day5_Teams_Right);
                    times = getResources().getStringArray(R.array.Day5_Timees);

                    flag1 = DATA.Day5_Teams_Left;
                    flag2 = DATA.Day5_Teams_Right;
                }
                break;

                case 20: {
                    teams1 = getResources().getStringArray(R.array.Day6_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day6_Teams_Right);
                    times = getResources().getStringArray(R.array.Day6_Timees);

                    flag1 = DATA.Day6_Teams_Left;
                    flag2 = DATA.Day6_Teams_Right;
                }
                break;

                case 21: {
                    teams1 = getResources().getStringArray(R.array.Day7_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day7_Teams_Right);
                    times = getResources().getStringArray(R.array.Day7_Timees);

                    flag1 = DATA.Day7_Teams_Left;
                    flag2 = DATA.Day7_Teams_Right;
                }
                break;

                case 22: {
                    teams1 = getResources().getStringArray(R.array.Day8_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day8_Teams_Right);
                    times = getResources().getStringArray(R.array.Day8_Timees);

                    flag1 = DATA.Day8_Teams_Left;
                    flag2 = DATA.Day8_Teams_Right;
                }
                break;

                case 23: {
                    teams1 = getResources().getStringArray(R.array.Day9_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day9_Teams_Right);
                    times = getResources().getStringArray(R.array.Day9_Timees);

                    flag1 = DATA.Day9_Teams_Left;
                    flag2 = DATA.Day9_Teams_Right;
                }
                break;

                case 24: {
                    teams1 = getResources().getStringArray(R.array.Day10_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day10_Teams_Right);
                    times = getResources().getStringArray(R.array.Day10_Timees);

                    flag1 = DATA.Day10_Teams_Left;
                    flag2 = DATA.Day10_Teams_Right;
                }
                break;

                case 25: {
                    teams1 = getResources().getStringArray(R.array.Day11_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day11_Teams_Right);
                    times = getResources().getStringArray(R.array.Day11_Timees);

                    flag1 = DATA.Day11_Teams_Left;
                    flag2 = DATA.Day11_Teams_Right;
                }
                break;
                case 26: {
                    teams1 = getResources().getStringArray(R.array.Day12_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day12_Teams_Right);
                    times = getResources().getStringArray(R.array.Day12_Timees);

                    flag1 = DATA.Day12_Teams_Left;
                    flag2 = DATA.Day12_Teams_Right;
                }
                break;

                case 27: {
                    teams1 = getResources().getStringArray(R.array.Day13_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day13_Teams_Right);
                    times = getResources().getStringArray(R.array.Day13_Timees);

                    flag1 = DATA.Day13_Teams_Left;
                    flag2 = DATA.Day13_Teams_Right;
                }
                break;


                case 30: {
                    teams1 = getResources().getStringArray(R.array.Day14_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day14_Teams_Right);
                    times = getResources().getStringArray(R.array.Day14_Timees);

                    flag1 = DATA.Day14_Teams_Left;
                    flag2 = DATA.Day14_Teams_Right;
                }
                break;


            }
        }



        if(month == 11) {
            switch (day) {

                case 1: {
                    teams1 = getResources().getStringArray(R.array.Day15_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day15_Teams_Right);
                    times = getResources().getStringArray(R.array.Day15_Timees);

                    flag1 = DATA.Day15_Teams_Left;
                    flag2 = DATA.Day15_Teams_Right;
                }
                break;

                case 2: {
                    teams1 = getResources().getStringArray(R.array.Day16_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day16_Teams_Right);
                    times = getResources().getStringArray(R.array.Day16_Timees);

                    flag1 = DATA.Day16_Teams_Left;
                    flag2 = DATA.Day16_Teams_Right;
                }
                break;

                case 4: {
                    teams1 = getResources().getStringArray(R.array.Day17_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day17_Teams_Right);
                    times = getResources().getStringArray(R.array.Day17_Timees);

                    flag1 = DATA.Day17_Teams_Left;
                    flag2 = DATA.Day17_Teams_Right;
                }
                break;

                case 5: {
                    teams1 = getResources().getStringArray(R.array.Day18_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day18_Teams_Right);
                    times = getResources().getStringArray(R.array.Day18_Timees);

                    flag1 = DATA.Day18_Teams_Left;
                    flag2 = DATA.Day18_Teams_Right;
                }
                break;

                case 8: {
                    teams1 = getResources().getStringArray(R.array.Day19_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day19_Teams_Right);
                    times = getResources().getStringArray(R.array.Day19_Timees);

                    flag1 = DATA.Day19_Teams_Left;
                    flag2 = DATA.Day19_Teams_Right;
                }
                break;

                case 9: {
                    teams1 = getResources().getStringArray(R.array.Day20_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day20_Teams_Right);
                    times = getResources().getStringArray(R.array.Day20_Timees);

                    flag1 = DATA.Day20_Teams_Left;
                    flag2 = DATA.Day20_Teams_Right;
                }
                break;

                case 12: {
                    teams1 = getResources().getStringArray(R.array.Day21_Teams_Left);
                    teams2 = getResources().getStringArray(R.array.Day21_Teams_Right);
                    times = getResources().getStringArray(R.array.Day21_Timees);

                    flag1 = DATA.Day21_Teams_Left;
                    flag2 = DATA.Day21_Teams_Right;
                }
                break;


            }
        }


        AdapterCalender adapterCalender = new AdapterCalender(getApplicationContext(), times, teams1, teams2, flag1, flag2);

        calenderList.setAdapter(adapterCalender);


    }

}