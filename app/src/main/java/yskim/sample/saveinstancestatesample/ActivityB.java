package yskim.sample.saveinstancestatesample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityB extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;

    static final String INPUT_DATA = "input_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (savedInstanceState == null) {
//            Intent intent = getIntent();
//            Bundle savedState = intent.getBundleExtra("saved_state");
//            if (savedState != null) {
//                onRestoreInstanceState(savedState);
//            }
//        }

        setContentView(R.layout.activity_b);
        ButterKnife.bind(this);
        if(savedInstanceState != null) {
            Debug.logd(new Exception(), "");
            String data = savedInstanceState.getString(INPUT_DATA);
            editText.setText(data);
        } else {
            Intent intent = getIntent();
            Debug.logd(new Exception(), "");
            Bundle savedState = intent.getBundleExtra("saved_state");
            if(savedState != null) {
                String data = savedState.getString(INPUT_DATA);
                Debug.logd(new Exception(), "data: " + data);
                editText.setText(data);
                //editText.setText(data);
//            if (savedState != null) {
//                onRestoreInstanceState(savedState);
//            }
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
//        Debug.logd(new Exception(), "");
//        Bundle savedState = new Bundle();
//        String inputData = editText.getText().toString();
//        savedState.putString(INPUT_DATA, inputData);
//        Intent data = new Intent();
//        data.putExtra("saved_state", savedState);
//        setResult(1, data);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Debug.logd(new Exception(), "");
        Bundle savedState = new Bundle();
        String inputData = editText.getText().toString();
        savedState.putString(INPUT_DATA, inputData);
        Intent data = new Intent();
        data.putExtra("saved_state", savedState);
        setResult(RESULT_OK, data);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
//        Bundle savedState = new Bundle();
//        if (savedState != null) {
//            onSaveInstanceState(savedState);
//        }
//        Intent data = new Intent();
//        data.putExtra("saved_state", savedState);
//        setResult(RESULT_OK, data);
        super.onDestroy();
//        Debug.logd(new Exception(), "");
//        Bundle savedState = new Bundle();
//        String inputData = editText.getText().toString();
//        savedState.putString(INPUT_DATA, inputData);
//        Intent data = new Intent();
//        data.putExtra("saved_state", savedState);
//        setResult(1, data);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Debug.logd(new Exception(), "");
        String inputData = editText.getText().toString();
        outState.putString(INPUT_DATA, inputData);
    }
}
