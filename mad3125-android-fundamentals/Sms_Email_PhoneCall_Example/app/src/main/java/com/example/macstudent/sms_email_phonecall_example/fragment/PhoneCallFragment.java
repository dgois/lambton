package com.example.macstudent.sms_email_phonecall_example.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macstudent.sms_email_phonecall_example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneCallFragment extends Fragment {


    @InjectView(R.id.edtPhoneNumberCall)
    EditText edtPhoneNumberCall;

    @InjectView(R.id.btnCall)
    Button btnCall;

    public PhoneCallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone_call, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btnCall)
    public void onViewClicked() {
        String phoneNo = edtPhoneNumberCall.getText().toString();
        if(!TextUtils.isEmpty(phoneNo)) {
            String dial = "tel:" + phoneNo;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        } else {
            Toast.makeText(getActivity().getBaseContext(), "Enter phone number", Toast.LENGTH_SHORT).show();
        }
    }
}
