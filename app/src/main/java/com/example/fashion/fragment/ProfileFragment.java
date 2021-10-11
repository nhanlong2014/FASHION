package com.example.fashion.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fashion.R;
import com.example.fashion.activities.DangNhapActivity;


public class ProfileFragment extends Fragment {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_profile2, container, false);
    textView = view.findViewById(R.id.textView);
    textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), DangNhapActivity.class);
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
        }
    });
    return view;
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // check that it is the SecondActivity with an OK result
//        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
//
//                // get String data from Intent
//                String returnString = data.getStringExtra("keyName");
//
//                // set text view with string
//                TextView textView = (TextView) findViewById(R.id.textView);
//                textView.setText(returnString);
//            }
//        }
//    }
}