package com.home.questingtracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagesAdapter extends FragmentPagerAdapter {

	private static final int NB_PAGES = 2;
	private static final String title_prefix = "Staging Area ";
	
	public <T extends Fragment> PagesAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int position) {
		return new TrackerPageFragment(getTitle(position));
	}

	@Override
	public int getCount() {
		return NB_PAGES;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return getTitle(position);
	}

	private String getTitle(int position) {
		return title_prefix + (position + 1);
	}
}
