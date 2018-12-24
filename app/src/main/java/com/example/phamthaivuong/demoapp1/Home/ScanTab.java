package com.example.phamthaivuong.demoapp1.Home;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.phamthaivuong.demoapp1.MainActivity;
import com.example.phamthaivuong.demoapp1.QRCode.QRcodeActivity;
import com.example.phamthaivuong.demoapp1.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class ScanTab  extends Fragment implements View.OnClickListener {

    Button scan;



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pageScanTab = inflater.inflate(R.layout.fragment_scantab,container,false);

        scan = (Button)pageScanTab.findViewById(R.id.scan);
        scan.setOnClickListener(this);
        return pageScanTab;
    }


    @Override
    public void onClick(View v) {
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),QRcodeActivity.class);
                startActivity(i);
            }
        });
    }
}
