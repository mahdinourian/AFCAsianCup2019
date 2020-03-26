package desertstars.afcasiancup2019.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import desertstars.afcasiancup2019.Adapter.AdapterMatches;
import desertstars.afcasiancup2019.DATA;
import desertstars.afcasiancup2019.R;


public class FragmentMatches extends Fragment {

    int groupNumber;
    String[] goal1, goal2;
    String myURI = "http://kavir-stars.ir/livescore.php";

    SwipeRefreshLayout refreshMatches;


    JSONArray liveScore;

    RequestQueue requestQueue;

    RecyclerView recyclerView;
    String[] teams1, teams2, times;
    Integer[]flag1, flag2;

    AdapterMatches adapter;
    ProgressDialog waitingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intentSubjectMatch = getActivity().getIntent();
        groupNumber = intentSubjectMatch.getIntExtra("GROUP_NUMBER", 1);


        switch (groupNumber){

            case 1: {
                teams1 = getResources().getStringArray(R.array.Group1_Teams_left);
                teams2 = getResources().getStringArray(R.array.Group1_Teams_Right);
                times = getResources().getStringArray(R.array.Group1_Timees);

                flag1 = DATA.Group1_Teams_Left;
                flag2 = DATA.Group1_Teams_Right;
            }
            break;

            case 2: {
                teams1 = getResources().getStringArray(R.array.Group2_Teams_left);
                teams2 = getResources().getStringArray(R.array.Group2_Teams_Right);
                times = getResources().getStringArray(R.array.Group2_Timees);

                flag1 = DATA.Group2_Teams_Left;
                flag2 = DATA.Group2_Teams_Right;
            }
            break;

            case 3: {
                teams1 = getResources().getStringArray(R.array.Group3_Teams_left);
                teams2 = getResources().getStringArray(R.array.Group3_Teams_Right);
                times = getResources().getStringArray(R.array.Group3_Timees);

                flag1 = DATA.Group3_Teams_Left;
                flag2 = DATA.Group3_Teams_Right;
            }
            break;

            case 4: {
                teams1 = getResources().getStringArray(R.array.Group4_Teams_left);
                teams2 = getResources().getStringArray(R.array.Group4_Teams_Right);
                times = getResources().getStringArray(R.array.Group4_Timees);

                flag1 = DATA.Group4_Teams_Left;
                flag2 = DATA.Group4_Teams_Right;
            }
            break;

            case 5: {
                teams1 = getResources().getStringArray(R.array.Group5_Teams_left);
                teams2 = getResources().getStringArray(R.array.Group5_Teams_Right);
                times = getResources().getStringArray(R.array.Group5_Timees);

                flag1 = DATA.Group5_Teams_Left;
                flag2 = DATA.Group5_Teams_Right;
            }
            break;

            case 6: {
                teams1 = getResources().getStringArray(R.array.Group6_Teams_left);
                teams2 = getResources().getStringArray(R.array.Group6_Teams_Right);
                times = getResources().getStringArray(R.array.Group6_Timees);

                flag1 = DATA.Group6_Teams_Left;
                flag2 = DATA.Group6_Teams_Right;
            }
            break;

        }


        goal1 = new String[]{"0","0","0","0","0","0"};
        goal2 = new String[]{"0","0","0","0","0","0"};

        adapter = new AdapterMatches(getContext(), times, teams1, teams2, flag1, flag2, goal1, goal2);

        requestQueue = Volley.newRequestQueue(getActivity());


        waitingDialog = new ProgressDialog(getContext());
        waitingDialog.setTitle("در حال دریافت اطلاعات");
        waitingDialog.setMessage("لطفا صبر کنید...");
        waitingDialog.setCancelable(false);

        RefreshPage();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View myFragmentView = inflater.inflate(R.layout.fragment_matches, container, false);

        refreshMatches = (SwipeRefreshLayout) myFragmentView.findViewById(R.id.swipe_refresh_matches);
        recyclerView = (RecyclerView) refreshMatches.findViewById(R.id.rec_list_of_matches);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        refreshMatches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                RefreshPage();

            }


        });


        return myFragmentView;





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
                            liveScore = new JSONArray(response);

                            for (int i=0; i<6; i++){

                                JSONObject jsonObject = (JSONObject) liveScore.get(6*(groupNumber - 1) + i);
                                goal1[i] = jsonObject.getString("team1");
                                goal2[i] = jsonObject.getString("team2");

                            }

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

        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private void CompleteRefresh(){

        waitingDialog.dismiss();
        refreshMatches.setRefreshing(false);
        adapter = new AdapterMatches(getContext(), times, teams1, teams2, flag1, flag2, goal1, goal2);
        recyclerView.setAdapter(adapter);

    }


}