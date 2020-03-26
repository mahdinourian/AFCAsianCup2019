package desertstars.afcasiancup2019.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import desertstars.afcasiancup2019.Fragment.FragmentMatches;
import desertstars.afcasiancup2019.Fragment.FragmentTable;
import desertstars.afcasiancup2019.R;

public class InformationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment matchesFragment = new FragmentMatches();
        Fragment tableFragment = new FragmentTable();
        adapter.addFragment(tableFragment, "جدول");
        adapter.addFragment(matchesFragment, "بازی ها و نتایج");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        for(int i=0; i<2; i++) {
            ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(i);
            View tabViewChild = vgTab.getChildAt(1);

            if (tabViewChild instanceof TextView) {
                ((TextView) tabViewChild).setTypeface(MainActivity.titleTypeface);
            }
        }

    }



    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

