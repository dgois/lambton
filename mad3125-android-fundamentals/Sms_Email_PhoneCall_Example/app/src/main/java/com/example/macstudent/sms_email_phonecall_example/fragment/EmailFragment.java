package com.example.macstudent.sms_email_phonecall_example.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macstudent.sms_email_phonecall_example.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {

    @InjectView(R.id.edtEmail)
    EditText edtEmail;

    @InjectView(R.id.edtSubject)
    EditText edtSubject;

    @InjectView(R.id.edtBody)
    EditText edtBody;

    @InjectView(R.id.btnSend)
    Button btnSend;

    public EmailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked() {
        String to = edtEmail.getText().toString();
        String subject = edtSubject.getText().toString();
        String body = edtBody.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        emailIntent.setData(Uri.parse("mailto:" + to));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        //need this to prompts email client only
//        emailIntent.setType("message/rfc822");
        //Send File attachment to email
        File Root= Environment.getExternalStorageDirectory();
        Log.d("DENIS", Root.getAbsolutePath());
//        String fileLocation=Root.getAbsolutePath() + "files_folder/test.jpg";
//        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse( "file://"+fileLocation));

        startActivity(Intent.createChooser(emailIntent, "Select Email Client"));

    }
}
