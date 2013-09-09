package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {
	
	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		fm = this.getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		if(fragment == null){
			fragment = createFragment();
			fm.beginTransaction().
			add(R.id.fragment_container, fragment)
			.commit();
		}
		
	}

	protected abstract Fragment createFragment();

	

}

