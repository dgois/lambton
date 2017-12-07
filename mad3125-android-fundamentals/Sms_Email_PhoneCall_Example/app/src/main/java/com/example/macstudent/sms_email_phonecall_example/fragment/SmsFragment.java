package com.example.macstudent.sms_email_phonecall_example.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.macstudent.sms_email_phonecall_example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SmsFragment extends Fragment {


    @InjectView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;

    @InjectView(R.id.edtText)
    EditText edtText;

    @InjectView(R.id.btnSms)
    Button btnSms;

    public SmsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sms, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btnSms)
    public void onViewClicked() {
        String message = edtText.getText().toString();
        String phoneNo = edtPhoneNumber.getText().toString();

        if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo)) {
            Log.d("DENIS", "Prepare to opent intent");
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        }
    }
}
