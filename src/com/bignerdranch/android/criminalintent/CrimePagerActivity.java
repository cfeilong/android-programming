package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;


	public class CrimePagerActivity extends FragmentActivity {
		private ViewPager mViewPager;
		private ArrayList<Crime> mCrimes;
		
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			mViewPager = new ViewPager(this);
			mViewPager.setId(R.id.viewPager);
			this.setContentView(mViewPager);
			
			mCrimes = CrimeLab.getInstance(CrimePagerActivity.this).getCrimes();;
			FragmentManager fm = this.getSupportFragmentManager();
			
			
			mViewPager.setAdapter(new FragmentStatePagerAdapter(fm){

				@Override
				public Fragment getItem(int position) {
					// TODO Auto-generated method stub
					return CrimeFragment.newInstance(mCrimes.get(position).getId());
				}

				@Override
				public int getCount() {
					// TODO Auto-generated method stub
					return mCrimes.size();
				}
				
			});
			UUID crime_id = (UUID)this.getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
			for(int i=0; i<mCrimes.size(); i++){
				if(mCrimes.get(i).getId().equals(crime_id)){
					mViewPager.setCurrentItem(i);
					if(mCrimes.get(i).getTitle() != null)
						setTitle(mCrimes.get(i).getTitle());
					break;
				}
			}
			mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int position) {
					// TODO Auto-generated method stub
					Crime crime = mCrimes.get(position);
					if(crime.getTitle() != null)
						setTitle(crime.getTitle());
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}



	    protected Fragment createFragment() {
	        UUID crimeId = (UUID)getIntent()
	            .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
	        return CrimeFragment.newInstance(crimeId);
	    }
	}




	

