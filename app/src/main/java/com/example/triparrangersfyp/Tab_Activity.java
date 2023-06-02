package com.example.triparrangersfyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.triparrangersfyp.Adapter.VViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Tab_Activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.view);
        tabLayout.setupWithViewPager(viewPager);
        VViewPagerAdapter vpAdapter= new VViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Fragment1(),"Active");
        vpAdapter.addFragment(new Fragment2(),"Pending");
        vpAdapter.addFragment(new Fragment3(),"Blocked");
        viewPager.setAdapter(vpAdapter);

    }
}