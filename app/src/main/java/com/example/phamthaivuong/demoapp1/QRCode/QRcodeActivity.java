package com.example.phamthaivuong.demoapp1.QRCode;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.phamthaivuong.demoapp1.MainActivity;
import com.example.phamthaivuong.demoapp1.R;
import com.example.phamthaivuong.demoapp1.TestSingleton;
import com.example.phamthaivuong.demoapp1.Testxxx;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class QRcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private static final String TAG = "TipCalculatorActivity";
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
        {
            if (checkPremission()){
                Toast.makeText(QRcodeActivity.this, "", Toast.LENGTH_SHORT).show();
            }
            else
            {
                requestPermissions();
            }
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,new String[]{CAMERA},REQUEST_CAMERA);
    }

    private boolean checkPremission(){
        return (ContextCompat.checkSelfPermission(QRcodeActivity.this,CAMERA) == PackageManager.PERMISSION_GRANTED);

    }

    public void onRequestPermissionsResult(int requestCode, String permission[] ,int grantResults[]){
        switch (requestCode)
        {
            case REQUEST_CAMERA:
                if (grantResults.length > 0)
                {
                    boolean cameraAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted)
                    {
                        Toast.makeText(QRcodeActivity.this, "Đã được cho phép", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(QRcodeActivity.this, "Sự cho phép bị từ chối", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if (shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("bạn cần cho phép truy cập cho cả hai quyền", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                                            requestPermissions(new String[]{CAMERA},REQUEST_CAMERA);
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkPremission()){
                if (scannerView == null)
                {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else {
                requestPermissions();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.startCamera();
    }

    public void displayAlertMessage(String message , DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(QRcodeActivity.this)
                .setMessage(message)
                .setPositiveButton("Ok",listener)
                .setNegativeButton("Cancel",null)
                .create()
                .show();
    }

    @Override
    public void handleResult(final Result result) {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin sản phẩm");
        Log.d(TAG,"abc " + scanResult);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                scannerView.resumeCameraPreview(QRcodeActivity.this);
            }
        });
        builder.setNegativeButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent  = new Intent(Intent.ACTION_VIEW,Uri.parse(scanResult));
                startActivity(intent);
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();
        //luu

        TestSingleton.getInstance().setText(result.getText());
    }


}

