package desertstars.afcasiancup2019.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import desertstars.afcasiancup2019.Activity.MainActivity;
import desertstars.afcasiancup2019.R;


public class AdapterMatches extends RecyclerView.Adapter<AdapterMatches.MyViewHolder>{

	TextView txtNameTeam1, txtNameTeam2, txtTime, txtGoalTeam1, txtGoalTeam2;
	ImageView flagTeam1, flagTeam2;


	Integer[] flagsTeam1, flagsTeam2;
	String[] times, namesTeam1, namesTeam2, goalTeam1, goalTeam2;

	Context context;

	public class MyViewHolder extends RecyclerView.ViewHolder {

		public MyViewHolder(View view) {
			super(view);

			txtNameTeam1 = (TextView) view.findViewById(R.id.name_team_1);
			txtNameTeam2 = (TextView) view.findViewById(R.id.name_team_2);
			txtTime = (TextView) view.findViewById(R.id.date_and_time);
			txtGoalTeam1 = (TextView) view.findViewById(R.id.goal_1);
			txtGoalTeam2 = (TextView) view.findViewById(R.id.goal_2);

			flagTeam1 = (ImageView) view.findViewById(R.id.flag_team_1);
			flagTeam2 = (ImageView) view.findViewById(R.id.flag_team_2);


		}

	}

	public AdapterMatches(Context context, String[] times,
						  String[] namesTeam1, String[] namesTeam2,
						  Integer[] flagsTeam1, Integer[] flagsTeam2,
						  String[] goalTeam1, String[] goalTeam2) {

		this.context = context;
		this.times = times;
		this.namesTeam1 = namesTeam1;
		this.namesTeam2 = namesTeam2;
		this.flagsTeam1 = flagsTeam1;
		this.flagsTeam2 = flagsTeam2;
		this.goalTeam1 = goalTeam1;
		this.goalTeam2 = goalTeam2;

	}


	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_match, parent, false);
		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {

		txtNameTeam1.setText(namesTeam1[position]);
		txtNameTeam2.setText(namesTeam2[position]);

		txtGoalTeam1.setText(goalTeam1[position]);
		txtGoalTeam2.setText(goalTeam2[position]);

		txtTime.setText(times[position]);

		txtNameTeam1.setTypeface(MainActivity.titleTypeface);
		txtNameTeam2.setTypeface(MainActivity.titleTypeface);

		txtGoalTeam1.setTypeface(MainActivity.titleTypeface);
		txtGoalTeam2.setTypeface(MainActivity.titleTypeface);

		txtTime.setTypeface(MainActivity.titleTypeface);


		flagTeam1.setImageResource(flagsTeam1[position]);
		flagTeam2.setImageResource(flagsTeam2[position]);



	}

	@Override
	public int getItemCount() {
		return namesTeam1.length;
	}

}