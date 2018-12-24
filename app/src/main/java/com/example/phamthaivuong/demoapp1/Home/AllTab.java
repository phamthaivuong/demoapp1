package com.example.phamthaivuong.demoapp1.Home;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phamthaivuong.demoapp1.R;
import com.example.phamthaivuong.demoapp1.TestSingleton;

public class AllTab extends Fragment {
    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pageAllTab = inflater.inflate(R.layout.fragment_alltab,container,false);

        return pageAllTab;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TestSingleton.getInstance().getText() != null){
            String test = TestSingleton.getInstance().getText();
            Toast.makeText(getActivity(),test, Toast.LENGTH_SHORT).show();
        }

    }
}

