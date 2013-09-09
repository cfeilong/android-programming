package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CrimeFragment extends Fragment {
	
	public final static String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
	private final static int REQUEST_DATE = 0;	
	public final static String DATE_PICKER_DIALOG = "date_dialog";
	private Crime mCrime;
	private EditText mEditText;
	private CheckBox mSolved;
	private Button mDate;

	
	 public static CrimeFragment newInstance(UUID crimeId) {
	        Bundle args = new Bundle();
	        args.putSerializable(EXTRA_CRIME_ID, crimeId);

	        CrimeFragment fragment = new CrimeFragment();
	        fragment.setArguments(args);

	        return fragment;
	    }

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
	        mCrime = CrimeLab.getInstance(getActivity()).getCrime(crimeId);
	    }


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_crime, container, false);
		
		Log.d("DEBUG", mCrime.getTitle());
		
		mEditText = (EditText)v.findViewById(R.id.crime_tittle);
		mEditText.setText(mCrime.getTitle());
		mEditText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence c, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				mCrime.setTitle(c.toString());
			}
			
			
		});
		
		
		mDate = (Button)v.findViewById(R.id.crime_date);
		updateDate();
	
		mDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getFragmentManager();
				DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCrime.getDate());
				datePickerFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
				
				datePickerFragment.show(fm, DATE_PICKER_DIALOG);
			}
		});
		
		
		mSolved = (CheckBox)v.findViewById(R.id.crime_solved);
		mSolved.setChecked(mCrime.isSolved());
		mSolved.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton button, boolean isChecked) {
				// TODO Auto-generated method stub
				mCrime.setSolved(isChecked);
			}
			
		});
		
		return v;
	}
	
	public void setResult(){
		getActivity().setResult(Activity.RESULT_OK, null);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode != android.R.string.ok) return;
		if(requestCode ==  REQUEST_DATE){
			Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			updateDate();
		}
	}

	
	private void updateDate(){
		String format = "E, MMM dd, yyyy";
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.US);
		mDate.setText(df.format(mCrime.getDate()));
	}

	

}
