package com.example.androidjokelib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JokeFragment extends Fragment {
    private static final String ARG_JOKE = "joke";

    private String mJoke;

    public JokeFragment() {
        // Required empty public constructor
    }

    public static JokeFragment newInstance(String joke) {
        JokeFragment fragment = new JokeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_JOKE, joke);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mJoke = getArguments().getString(ARG_JOKE);
        } else if (savedInstanceState != null) {
            mJoke = savedInstanceState.getString(ARG_JOKE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        TextView tvJoke = (TextView) view.findViewById(R.id.tv_joke);
        tvJoke.setText(mJoke);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mJoke != null && !mJoke.isEmpty()) outState.putString(ARG_JOKE, mJoke);
        super.onSaveInstanceState(outState);
    }
}
