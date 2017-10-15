package com.home.questingtracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class PagesAdapter extends FragmentPagerAdapter {

	private static final int NB_PAGES = 2;
	private static final String title_prefix = "Staging Area ";
	
	PagesAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int position) {
		Bundle bundle = new Bundle();
		bundle.putString(TrackerPageFragment.TITLE_KEY, getTitle(position));
		TrackerPageFragment trackerPageFragment = new TrackerPageFragment();
		trackerPageFragment.setArguments(bundle);
		return trackerPageFragment;
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
