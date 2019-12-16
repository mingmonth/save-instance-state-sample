package yskim.sample.saveinstancestatesample;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityB extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView)
    TextView textView;

    static final String INPUT_DATA = "input_data";
    static final String SCREEN_DATA = "screen_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_b);
        ButterKnife.bind(this);

        textView.setMovementMethod(new ScrollingMovementMethod());

        if (savedInstanceState != null) {
            Debug.logd(new Exception(), "");
            String data = savedInstanceState.getString(INPUT_DATA);
            editText.setText(data);
        } else {
            Intent intent = getIntent();
            Debug.logd(new Exception(), "");
            Bundle savedState = intent.getBundleExtra("saved_state");
            if (savedState != null) {
                String screenData = savedState.getString(SCREEN_DATA);
                String inputData = savedState.getString(INPUT_DATA);
                Debug.logd(new Exception(), "screenData: " + screenData);
                Debug.logd(new Exception(), "inputData: " + inputData);
                textView.setText(screenData);
                editText.setText(inputData);
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        Debug.logd(new Exception(), "");
        Bundle savedState = new Bundle();
        String screenData = textView.getText().toString();
        String inputData = editText.getText().toString();
        savedState.putString(SCREEN_DATA, screenData);
        savedState.putString(INPUT_DATA, inputData);
        Intent data = new Intent();
        data.putExtra("saved_state", savedState);
        setResult(RESULT_OK, data);
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String inputData = editText.getText().toString();
        outState.putString(INPUT_DATA, inputData);
    }

    @OnClick({R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                setTextView();
                break;
        }
    }

    private void setTextView() {
        String textViewContents = textView.getText().toString();
        String editTextContents = editText.getText().toString();
        StringBuilder sb = new StringBuilder();
        if(textViewContents != null) {
            sb.append(textView.getText().toString());
            sb.append("\n");
        }

        sb.append(editTextContents);

        textView.setText(sb.toString());
        scrollBottom(textView);
        editText.setText(null);
    }

    private void scrollBottom(TextView textView) {
        int lineTop =  textView.getLayout().getLineTop(textView.getLineCount()) ;
        int scrollY = lineTop - textView.getHeight();
        if (scrollY > 0) {
            textView.scrollTo(0, scrollY);
        } else {
            textView.scrollTo(0, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear_screen) {
            textView.setText(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
