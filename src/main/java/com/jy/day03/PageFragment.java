package com.jy.day03;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PageFragment extends Fragment {

    public static Fragment newInstance(String url) {
        
        Bundle args = new Bundle();
        args.putString("url", url);
        Fragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    private ImageView mGirl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.frag, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mGirl = inflate.findViewById(R.id.girl);
        String url = getArguments().getString("url");
        Glide.with(getActivity()).load(url).into(mGirl);
    }
}
