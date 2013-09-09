package com.bignerdranch.android.criminalintent;

import java.util.UUID;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;


	public class CrimePagerActivity extends SingleFragmentActivity {
		private ViewPager mViewPager;
		
		
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			mViewPager = new ViewPager(this);
			mViewPager.setId(R.id.viewPager);
			this.setContentView(mViewPager);
			
		}



		@Override
	    protected Fragment createFragment() {
	        UUID crimeId = (UUID)getIntent()
	            .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
	        return CrimeFragment.newInstance(crimeId);
	    }
	}




	

