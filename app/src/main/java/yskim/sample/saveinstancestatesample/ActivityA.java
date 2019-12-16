package yskim.sample.saveinstancestatesample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.bind(this);
        Debug.loge(new Exception(), "");
        //String encryptData = null;
        String encryptData = "kIIo6TF6V0pv6aFSPVUadsTdmW64BML5CQxAoKte1KYdQBp459oVgDs8WuoY+P+7xtRRxYFE6ji08QRje9FEkBlUrVLIUMMtxWHzdrBG7x6g7uJ56FmGfkjt5cu17g13Jippt7rsHDx+3+rZTGrf1TqKcoXUaNM4yojj45Ur/rg=";
        try {
            encryptData = RSATest.encrypt("test5555");
            Debug.loge(new Exception(), "Encrypt Data: " + encryptData);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            String decryptData = RSATest.decrypt(encryptData);
            Debug.loge(new Exception(), "Decrypt Data: " + decryptData);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for(final byte b: a)
            sb.append(String.format("%02X ", b&0xff));
        return sb.toString();
    }

    Bundle activityBData;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Debug.logd(new Exception(), "requestCode: " + requestCode);
        Debug.logd(new Exception(), "resultCode: " + resultCode);
        if (resultCode == RESULT_OK) {
            Debug.logd(new Exception(), "");
            activityBData = data.getBundleExtra("saved_state");
            String screenData = activityBData.getString("screen_data");
            String inputData = activityBData.getString("input_data");
            Debug.logd(new Exception(), "screenData: " + screenData);
            Debug.logd(new Exception(), "inputData: " + inputData);
        }
    }

    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button:
                intent = new Intent(this, ActivityB.class);
                if(activityBData != null) {
                    Debug.logd(new Exception(), "");
                    intent.putExtra("saved_state", activityBData);
                }
                startActivityForResult(intent, 1);
                break;
            case R.id.button2:
                intent = new Intent(this, ActivityC.class);
                startActivity(intent);
                break;
        }
    }
}
