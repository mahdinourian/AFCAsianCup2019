package desertstars.afcasiancup2019.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import desertstars.afcasiancup2019.R;
import desertstars.afcasiancup2019.TouchImageView;

public class KnockoutStageActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

     	TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.knockout_stage);
        img.setMaxZoom(4f);
        setContentView(img);

	}

}
