package yskim.sample.saveinstancestatesample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.bind(this);
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
            String inputData = activityBData.getString("input_data");
            Debug.logd(new Exception(), "inputData: " + inputData);
        }
    }

    @OnClick({R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, ActivityB.class);
                if(activityBData != null) {
                    Debug.logd(new Exception(), "");
                    intent.putExtra("saved_state", activityBData);
                }
                startActivityForResult(intent, 1);
                break;
        }
    }
}
