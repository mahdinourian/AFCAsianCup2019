package desertstars.afcasiancup2019.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import desertstars.afcasiancup2019.Activity.MainActivity;
import desertstars.afcasiancup2019.R;

public class FragmentTable extends Fragment {

    TextView[][] txtTable;
    int groupNumber;

    String myURI = "http://kavir-stars.ir/table.php";


    JSONArray tableMatches;
    RequestQueue requestQueue;

    SwipeRefreshLayout refreshTable;
    ProgressDialog waitingDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intentSubjectMatch = getActivity().getIntent();
        groupNumber = intentSubjectMatch.getIntExtra("GROUP_NUMBER", 1);


        requestQueue = Volley.newRequestQueue(getContext());

        waitingDialog = new ProgressDialog(getContext());
        waitingDialog.setTitle("در حال دریافت اطلاعات");
        waitingDialog.setMessage("لطفا صبر کنید...");
        waitingDialog.setCancelable(false);

        RefreshPage();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.fragment_table, container, false);
        refreshTable = (SwipeRefreshLayout) myFragmentView.findViewById(R.id.swipe_refresh_table);

        txtTable = new TextView[5][3];

        txtTable[0][0] = myFragmentView.findViewById(R.id.team_title);
        txtTable[0][1] = myFragmentView.findViewById(R.id.game_title);
        txtTable[0][2] = myFragmentView.findViewById(R.id.score_title);

        txtTable[1][0] = myFragmentView.findViewById(R.id.team1);
        txtTable[1][1] = myFragmentView.findViewById(R.id.game1);
        txtTable[1][2] = myFragmentView.findViewById(R.id.score1);

        txtTable[2][0] = myFragmentView.findViewById(R.id.team2);
        txtTable[2][1] = myFragmentView.findViewById(R.id.game2);
        txtTable[2][2] = myFragmentView.findViewById(R.id.score2);

        txtTable[3][0] = myFragmentView.findViewById(R.id.team3);
        txtTable[3][1] = myFragmentView.findViewById(R.id.game3);
        txtTable[3][2] = myFragmentView.findViewById(R.id.score3);

        txtTable[4][0] = myFragmentView.findViewById(R.id.team4);
        txtTable[4][1] = myFragmentView.findViewById(R.id.game4);
        txtTable[4][2] = myFragmentView.findViewById(R.id.score4);

        for(int i=0; i<=4; i++){
            for(int j=0; j<=2; j++) {

                txtTable[i][j].setTypeface(MainActivity.titleTypeface);

            }
        }


        refreshTable.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                            tableMatches = new JSONArray(response);
                            JSONArray table = (JSONArray) tableMatches.get(groupNumber - 1);

                            for (int i=1; i<=4; i++){

                                JSONObject infoTable = (JSONObject) table.get(i - 1);

                                String team = infoTable.getString("team");
                                String game = infoTable.getString("game");
                                String score = infoTable.getString("score");

                                txtTable[i][0].setText(team);
                                txtTable[i][1].setText(game);
                                txtTable[i][2].setText(score);

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
        refreshTable.setRefreshing(false);

    }



}