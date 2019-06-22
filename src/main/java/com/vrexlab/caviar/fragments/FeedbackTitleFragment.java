package com.vrexlab.caviar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.vrexlab.caviar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackTitleFragment extends Fragment {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.question)
    LinearLayout question;
    @Bind(R.id.need)
    LinearLayout need;
    @Bind(R.id.bug)
    LinearLayout bug;
    @Bind(R.id.other)
    LinearLayout other;
    @Bind(R.id.question_title)
    TextView questionTitle;
    @Bind(R.id.need_title)
    TextView needTitle;
    @Bind(R.id.bug_title)
    TextView bugTitle;
    @Bind(R.id.other_title)
    TextView otherTitle;

    public FeedbackTitleFragment() {
        // Required empty public constructor
    }

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_feedback_title, container, false);
            ButterKnife.bind(this, rootView);
        } else {
            ButterKnife.bind(this, rootView);
        }
        final FeedContentFragment feedback = (FeedContentFragment) getFragmentManager().findFragmentByTag("feedback");

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedback.title.setText(questionTitle.getText().toString());
                getActivity().onBackPressed();
            }
        });
        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedback.title.setText(needTitle.getText().toString());
                getActivity().onBackPressed();
            }
        });

        bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedback.title.setText(bugTitle.getText().toString());
                getActivity().onBackPressed();
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedback.title.setText(otherTitle.getText().toString());
                getActivity().onBackPressed();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
