package com.bignerdranch.android.criminalintent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {
	private ArrayList<Crime> mCrimes;
	private final static String TAG = "CrimeListFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setTheme(R.string.crimes_titlle);
		mCrimes = CrimeLab.getInstance(getActivity()).getCrimes();
		
		for(int i=1; i<=100; i++){
			Crime c = new Crime();
			c.setTitle("Crime #"+i);
			c.setSolved(i%3 ==  1);
			mCrimes.add(c);
		}
		
		CrimeAdapter mAdapter = new CrimeAdapter(mCrimes);
		setListAdapter(mAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
		Log.d(TAG, "Item " + c.getTitle() + " clicked");
		
		Intent i = new Intent(getActivity(), CrimePagerActivity.class);
		i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
		
		startActivityForResult(i,0);
	}
	
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
		
	}



	private class CrimeAdapter extends ArrayAdapter<Crime>{

		public CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null){
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
			}
			Crime c = this.getItem(position);
			
			TextView title_view = (TextView)convertView.findViewById(R.id.crime_item_title_textview);
			title_view.setText(c.getTitle());
			
			TextView date_view = (TextView)convertView.findViewById(R.id.crime_item_date_textview);
			//String format = "E, MMM dd, yyyy";
			//SimpleDateFormat df = new SimpleDateFormat(format, Locale.US);
			date_view.setText(c.getDate().toString());
			
			CheckBox solved = (CheckBox)convertView.findViewById(R.id.crime_item_solved_checkbox);
			solved.setChecked(c.isSolved());
			return convertView;
			
		}
		
	}
	
}
