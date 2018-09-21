package com.droidman.exhangeapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droidman.exhangeapp.R;

public class IntroPageFragment extends Fragment {
    private int mBackgroundColor, mPage;

    public static IntroPageFragment newInstance(int backgroundColor, int page) {
        IntroPageFragment frag = new IntroPageFragment();
        frag.mBackgroundColor = backgroundColor;
        frag.mPage = page;
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutResId;
        switch (mPage) {
            case 0:
                layoutResId = R.layout.fragment_welcome;
                break;
            default:
                layoutResId = R.layout.fragment_main_feature;
        }
        View view = getActivity().getLayoutInflater().inflate(layoutResId, container, false);
        view.setTag(mPage);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View background = view.findViewById(R.id.intro_background);
        background.setBackgroundColor(mBackgroundColor);
    }

}
