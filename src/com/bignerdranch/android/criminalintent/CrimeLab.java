package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {

	private ArrayList<Crime> mCrimes;
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	private CrimeLab(Context appContext){
		mAppContext = appContext;
		mCrimes = new ArrayList<Crime>();
	}
	
	public static CrimeLab getInstance(Context c){
		if(sCrimeLab == null){
			sCrimeLab = new CrimeLab(c);
		}
		return sCrimeLab;
	}
	
	
	public ArrayList<Crime> getCrimes(){
		return mCrimes;
	}
	
	public Crime getCrime(UUID id){
		for(Crime crime: mCrimes){
			if(crime.getId().equals(id)) return crime;
		}
		return null;
	}
}
