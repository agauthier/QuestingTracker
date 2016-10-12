package com.home.questingtracker;

import com.home.questingtracker.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initPages();
	}

	private <T extends Fragment> void initPages() {
		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		if (viewPager != null) {
			viewPager.setAdapter(new PagesAdapter(getSupportFragmentManager()));
		}
	}
}
