package desertstars.afcasiancup2019.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import desertstars.afcasiancup2019.Adapter.AdapterMatches;
import desertstars.afcasiancup2019.DATA;
import desertstars.afcasiancup2019.R;

public class KnockoutActivity extends AppCompatActivity {

	TextView[] knockTxts = new TextView[5];
	RecyclerView[] listKnockout = new RecyclerView[4];

	SwipeRefreshLayout refreshMatches;

	RequestQueue requestQueue;
	JSONArray knokoutArray;

	String myURI = "http://kavir-stars.ir/knockout.php";

	String[] stage8Teams1, stage8Teams2, stage8Goal1, stage8Goal2, stage8Times,
			 stage4Teams1, stage4Teams2, stage4Goal1, stage4Goal2, stage4Times,
			 stage2Teams1, stage2Teams2, stage2Goal1, stage2Goal2, stage2Times,
			 stage1Teams1, stage1Teams2, stage1Goal1, stage1Goal2, stage1Times;

	Integer[] stage8Flag1, stage8Flag2, stage4Flag1, stage4Flag2,
			  stage2Flag1, stage2Flag2, stage1Flag1, stage1Flag2;

	AdapterMatches[] matches = new AdapterMatches[4];
	ProgressDialog waitingDialog;

	ArrayList<Integer> flagsArray8_1, flagsArray8_2,
                       flagsArray4_1, flagsArray4_2,
                       flagsArray2_1, flagsArray2_2,
                       flagsArray1_1, flagsArray1_2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knoukout);
		
		
		knockTxts[0] = (TextView) findViewById(R.id.knock_titie);
		knockTxts[1] = (TextView) findViewById(R.id.knock_txt_1);
		knockTxts[2] = (TextView) findViewById(R.id.knock_txt_2);
		knockTxts[3] = (TextView) findViewById(R.id.knock_txt_3);
		knockTxts[4] = (TextView) findViewById(R.id.knock_txt_4);

		for (int i=0; i<5; i++){
			knockTxts[i].setTypeface(MainActivity.titleTypeface);
		}
		
		listKnockout[0] = (RecyclerView) findViewById(R.id.knoukout_1);
		listKnockout[1] = (RecyclerView) findViewById(R.id.knoukout_2);
		listKnockout[2] = (RecyclerView) findViewById(R.id.knoukout_3);
		listKnockout[3] = (RecyclerView) findViewById(R.id.knoukout_4);

		refreshMatches = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_matches);

		requestQueue = Volley.newRequestQueue(getApplicationContext());

		waitingDialog = new ProgressDialog(KnockoutActivity.this);
		waitingDialog.setTitle("در حال دریافت اطلاعات");
		waitingDialog.setMessage("لطفا صبر کنید...");
		waitingDialog.setCancelable(false);


		//Stage 8
		stage8Times = getResources().getStringArray(R.array.Stage8_Times);
		stage8Teams1 = getResources().getStringArray(R.array.Stage8_Teams_left);
		stage8Teams2 = getResources().getStringArray(R.array.Stage8_Teams_Right);

		stage8Goal1 = new String[]{"0","0","0","0","0","0","0","0"};
		stage8Goal2 = new String[]{"0","0","0","0","0","0","0","0"};

		stage8Flag1 = DATA.Stage8_Teams_Left;
		stage8Flag2 = DATA.Stage8_Teams_Right;


		//Stage 4
		stage4Times = getResources().getStringArray(R.array.Stage4_Times);
		stage4Teams1 = getResources().getStringArray(R.array.Stage4_Teams_left);
		stage4Teams2 = getResources().getStringArray(R.array.Stage4_Teams_Right);

		stage4Goal1 = new String[]{"0","0","0","0"};
		stage4Goal2 = new String[]{"0","0","0","0"};

		stage4Flag1 = DATA.Stage4_Teams_Left;
		stage4Flag2 = DATA.Stage4_Teams_Right;


		//Stage 2
		stage2Times = getResources().getStringArray(R.array.Stage2_Times);
		stage2Teams1 = getResources().getStringArray(R.array.Stage2_Teams_left);
		stage2Teams2 = getResources().getStringArray(R.array.Stage2_Teams_Right);

		stage2Goal1 = new String[]{"0","0"};
		stage2Goal2 = new String[]{"0","0"};

		stage2Flag1 = DATA.Stage2_Teams_Left;
		stage2Flag2 = DATA.Stage2_Teams_Right;


		//Stage 1
		stage1Times = getResources().getStringArray(R.array.Stage1_Times);
		stage1Teams1 = getResources().getStringArray(R.array.Stage1_Teams_left);
		stage1Teams2 = getResources().getStringArray(R.array.Stage1_Teams_Right);

		stage1Goal1 = new String[]{"0"};
		stage1Goal2 = new String[]{"0"};

		stage1Flag1 = DATA.Stage1_Teams_Left;
		stage1Flag2 = DATA.Stage1_Teams_Right;

		matches[0] = new AdapterMatches(getApplicationContext(),stage8Times, stage8Teams1, stage8Teams2, stage8Flag1, stage8Flag2, stage8Goal1, stage8Goal2);
		matches[1] = new AdapterMatches(getApplicationContext(),stage4Times, stage4Teams1, stage4Teams2, stage4Flag1, stage4Flag2, stage4Goal1, stage4Goal2);
		matches[2] = new AdapterMatches(getApplicationContext(),stage2Times, stage2Teams1, stage2Teams2, stage2Flag1, stage2Flag2, stage2Goal1, stage2Goal2);
		matches[3] = new AdapterMatches(getApplicationContext(),stage1Times, stage1Teams1, stage1Teams2, stage1Flag1, stage1Flag2, stage1Goal1, stage1Goal2);


		for (int i=0; i<4; i++){

			listKnockout[i].setNestedScrollingEnabled(false);
			listKnockout[i].setLayoutManager(new LinearLayoutManager(getApplicationContext()));
			listKnockout[i].setItemAnimator(new DefaultItemAnimator());
			listKnockout[i].setAdapter(matches[i]);

		}



		refreshMatches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {

				RefreshPage();

			}


		});



        flagsArray8_1 = new ArrayList<>();
        flagsArray8_2 = new ArrayList<>();

        flagsArray4_1 = new ArrayList<>();
        flagsArray4_2 = new ArrayList<>();

        flagsArray2_1 = new ArrayList<>();
        flagsArray2_2 = new ArrayList<>();

        flagsArray1_1 = new ArrayList<>();
        flagsArray1_2 = new ArrayList<>();


        flagsArray8_1.add(15);
        flagsArray8_1.add(9);
        flagsArray8_1.add(24);
        flagsArray8_1.add(17);
        flagsArray8_1.add(21);
        flagsArray8_1.add(11);
        flagsArray8_1.add(2);
        flagsArray8_1.add(14);

        flagsArray8_2.add(5);
        flagsArray8_2.add(3);
        flagsArray8_2.add(13);
        flagsArray8_2.add(23);
        flagsArray8_2.add(6);
        flagsArray8_2.add(1);
        flagsArray8_2.add(12);
        flagsArray8_2.add(18);


        flagsArray4_1.add(23);
        flagsArray4_1.add(13);
        flagsArray4_1.add(18);
        flagsArray4_1.add(6);

        flagsArray4_2.add(15);
        flagsArray4_2.add(9);
        flagsArray4_2.add(12);
        flagsArray4_2.add(1);


        flagsArray2_1.add(23);
        flagsArray2_1.add(1);

        flagsArray2_2.add(13);
        flagsArray2_2.add(18);

        flagsArray1_1.add(18);

        flagsArray1_2.add(23);


        for (int i=0; i<8; i++) {

            stage8Flag1[i] = DATA.knockoutFlags[flagsArray8_1.get(i)];
            stage8Flag2[i] = DATA.knockoutFlags[flagsArray8_2.get(i)];
        }

        for (int i=0; i<4; i++) {

            stage4Flag1[i] = DATA.knockoutFlags[flagsArray4_1.get(i)];
            stage4Flag2[i] = DATA.knockoutFlags[flagsArray4_2.get(i)];
        }

        for (int i=0; i<2; i++) {

            stage2Flag1[i] = DATA.knockoutFlags[flagsArray2_1.get(i)];
            stage2Flag2[i] = DATA.knockoutFlags[flagsArray2_2.get(i)];
        }

            stage1Flag1[0] = DATA.knockoutFlags[flagsArray1_1.get(0)];
            stage1Flag2[0] = DATA.knockoutFlags[flagsArray1_2.get(0)];

        RefreshPage();

	}



	private void RefreshPage() {

		waitingDialog.show();

		StringRequest stringRequest = new StringRequest(
				Request.Method.POST,
				myURI,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						try {
							knokoutArray = new JSONArray(response);

							for (int i=0; i<8; i++){

								JSONObject jsonObject = (JSONObject) knokoutArray.get(i);

								stage8Teams1[i] = jsonObject.getString("team1");
								stage8Teams2[i] = jsonObject.getString("team2");
								stage8Goal1[i] = jsonObject.getString("goal1");
								stage8Goal2[i] = jsonObject.getString("goal2");

								stage8Flag1[i] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag1"))];
								stage8Flag2[i] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag2"))];

							}

							for (int i=0; i<4; i++){

								JSONObject jsonObject = (JSONObject) knokoutArray.get(i + 8);

								stage4Teams1[i] = jsonObject.getString("team1");
								stage4Teams2[i] = jsonObject.getString("team2");
								stage4Goal1[i] = jsonObject.getString("goal1");
								stage4Goal2[i] = jsonObject.getString("goal2");

								stage4Flag1[i] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag1"))];
								stage4Flag2[i] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag2"))];

							}

							for (int i=0; i<2; i++){

								JSONObject jsonObject = (JSONObject) knokoutArray.get(i + 12);

								stage2Teams1[i] = jsonObject.getString("team1");
								stage2Teams2[i] = jsonObject.getString("team2");
								stage2Goal1[i] = jsonObject.getString("goal1");
								stage2Goal2[i] = jsonObject.getString("goal2");

								stage2Flag1[i] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag1"))];
								stage2Flag2[i] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag2"))];

							}

								JSONObject jsonObject = (JSONObject) knokoutArray.get(14);

								stage1Teams1[0] = jsonObject.getString("team1");
								stage1Teams2[0] = jsonObject.getString("team2");
								stage1Goal1[0] = jsonObject.getString("goal1");
								stage1Goal2[0] = jsonObject.getString("goal2");

								stage1Flag1[0] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag1"))];
								stage1Flag2[0] = DATA.knockoutFlags[Integer.parseInt(jsonObject.getString("flag2"))];

							CompleteRefresh();

						} catch (JSONException e) {
							e.printStackTrace();
							SetToast("خطا");
							CompleteRefresh();
						}

					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

						SetToast("خطا در دریافت اطلاعات");
						CompleteRefresh();
					}

				});

		requestQueue.add(stringRequest);

	}

	private void SetToast(String msg) {

		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

	}

	private void CompleteRefresh(){

		waitingDialog.dismiss();
		refreshMatches.setRefreshing(false);

		matches[0] = new AdapterMatches(getApplicationContext(),stage8Times, stage8Teams1, stage8Teams2, stage8Flag1, stage8Flag2, stage8Goal1, stage8Goal2);
		matches[1] = new AdapterMatches(getApplicationContext(),stage4Times, stage4Teams1, stage4Teams2, stage4Flag1, stage4Flag2, stage4Goal1, stage4Goal2);
		matches[2] = new AdapterMatches(getApplicationContext(),stage2Times, stage2Teams1, stage2Teams2, stage2Flag1, stage2Flag2, stage2Goal1, stage2Goal2);
		matches[3] = new AdapterMatches(getApplicationContext(),stage1Times, stage1Teams1, stage1Teams2, stage1Flag1, stage1Flag2, stage1Goal1, stage1Goal2);


		for (int i=0; i<4; i++){

			listKnockout[i].setAdapter(matches[i]);

		}
	}

}
