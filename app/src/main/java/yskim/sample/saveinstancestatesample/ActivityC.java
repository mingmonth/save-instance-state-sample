package yskim.sample.saveinstancestatesample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yskim.sample.saveinstancestatesample.data.INPUT;
import yskim.sample.saveinstancestatesample.data.LED;

public class ActivityC extends AppCompatActivity {

    @BindView(R.id.current_led_name)
    TextView currentLedName;

    HashMap<Integer, ImageView> ledHashMap = new HashMap<Integer, ImageView>();
    HashMap<Integer, ImageView> ledInputValueHashMap = new HashMap<Integer, ImageView>();
    HashMap<Integer, ImageView> inputHashMap = new HashMap<Integer, ImageView>();

    LED[] leds = new LED[6];
    int ledIndex = 0;
    int inputIndex = 0;

    private void initializeData() {
        for(int i = 0; i < leds.length; i++) {
            leds[i] = new LED();
            int resId = this.getResources().getIdentifier("led" + (i + 1), "id", this.getPackageName());
            ledHashMap.put(i, (ImageView) findViewById(resId));
            int resId2 = this.getResources().getIdentifier("ic_led" + (i + 1) + "_24px", "drawable", this.getPackageName());
            ledHashMap.get(i).setImageResource(resId2);
            int resId3 = this.getResources().getIdentifier("led" + (i + 1) + "_input_value", "id", this.getPackageName());
            ledInputValueHashMap.put(i, (ImageView) findViewById(resId3));
        }

        for(int j = 0; j < 9; j ++) {
            int resId = this.getResources().getIdentifier("input" + (j + 1), "id", this.getPackageName());
            inputHashMap.put(j, (ImageView) findViewById(resId));
            inputHashMap.get(j).setImageResource(R.drawable.ic_uncheck_24px);
        }
    }

    private void displayData() {
        INPUT[] inputs = null;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                inputs = leds[i].getInputs();
                Debug.loge(new Exception(), "LED [ " + i + " ] / INPUT [ " + j +" ] / CHK [ " + ((inputs[j].isChecked() == false) ? "F" : "T") + " ] / DIS [ " + ((inputs[j].isDisabled() == false) ? "F" : "T") + " ]");
            }
            Debug.logd(new Exception(), "");
        }
    }

    private void setImageResourceToInput(INPUT[] inputs) {

        //int resId = this.getResources().getIdentifier("ic_filter_" + (j + 1) + "_24px", "drawable", this.getPackageName());

        for(int i = 0; i < inputs.length; i++) {
            if(inputs[i].isDisabled()) {
                // set disable image
                // non-clickable
                inputHashMap.get(i).setImageResource(R.drawable.ic_disable_24px);
                inputHashMap.get(i).setClickable(false);
            } else if(inputs[i].isChecked()) {
                // set checked image
                inputHashMap.get(i).setImageResource(R.drawable.ic_check_24px);
                inputHashMap.get(i).setClickable(true);
            } else {// if(!inputs[i].isDisabled() || !inputs[i].isChecked()) {
                // clickable
                // set enable image, unchecked image
                // clickable
                inputHashMap.get(i).setImageResource(R.drawable.ic_uncheck_24px);
                inputHashMap.get(i).setClickable(true);
            }
//            else if(!inputs[i].isChecked()) {
//                // set unchecked image
//            }
        }
    }

    // LED 가 선택되었을 때 이전 선택된 값이 있으면 해당 값으로 input 값이 불려져야 함.
    private void setImageResourceToLED(int ledIndex, int inputIndex, INPUT[] inputs) {
        Debug.logd(new Exception(), "ledIndex: " + ledIndex +  ", inputIndex: " + inputIndex);
        if(inputs[inputIndex].isChecked()) {
            int resId = this.getResources().getIdentifier("ic_filter_" + (inputIndex + 1) + "_24px", "drawable", this.getPackageName());
            ledInputValueHashMap.get(ledIndex).setImageResource(resId);
        } else {
            ledInputValueHashMap.get(ledIndex).setImageResource(0);
        }
        setImageResourceToInput(inputs);
    }

    private void setBackgroundToLED(int ledIndex) {
        // 선택되었는데 value 가 있으면 white
        // 선택되지 않았는데 value 가 있으면 gray
        // value 가 없으면 검정색
        INPUT[] inputs = null;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                inputs = leds[i].getInputs();
                if(i == ledIndex) {
                    if(inputs[j].isChecked()) {
                        ledInputValueHashMap.get(i).setBackgroundColor(this.getResources().getColor(R.color.colorWhite));
                        break;
                    }
                } else {
                    if(inputs[j].isChecked()) {
                        ledInputValueHashMap.get(i).setBackgroundColor(this.getResources().getColor(R.color.colorGray));
                        break;
                    }
                }
                ledInputValueHashMap.get(i).setBackgroundColor(this.getResources().getColor(R.color.colorBlack));
            }
        }
    }

    private void displayUI(int ledIndex, int inputIndex) {
        INPUT[] inputs = null;
        inputs = leds[ledIndex].getInputs();
        int checkedIndex = getCheckedIndex(ledIndex);
        if(checkedIndex != -1) {
             inputIndex = checkedIndex;
        }
        setImageResourceToLED(ledIndex, inputIndex, inputs);
    }

    private void displayUI() {
        INPUT[] inputs = null;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                inputs = leds[i].getInputs();
                Debug.loge(new Exception(), "LED [ " + i + " ] / INPUT [ " + j +" ] / CHK [ " + ((inputs[j].isChecked() == false) ? "F" : "T") + " ] / DIS [ " + ((inputs[j].isDisabled() == false) ? "F" : "T") + " ]");
                if(i == 0) {
                    if(j == 0) {
                        //setImageResourceToLED(inputs[j], j);
                    }
                }
            }
            Debug.logd(new Exception(), "");
        }
    }

    private int getCheckedIndex(int ledIndex) {
        INPUT[] inputs = leds[ledIndex].getInputs();
        for(int j = 0; j < inputs.length; j++) {
            if(inputs[j].isChecked()) {
                return j;
            }
        }
        // 아무것도 체크되지 않은 경우.
        return -1;
    }

    private void setCheckValue(int ledIndex, int inputIndex) {
        INPUT[] inputs = leds[ledIndex].getInputs();
        if(inputs[inputIndex].isChecked()) {
            inputs[inputIndex].setChecked(false);
        } else {
            inputs[inputIndex].setChecked(true);
        }
    }

    private boolean isChecked(int ledIndex, int inputIndex) {
        INPUT[] inputs = leds[ledIndex].getInputs();
        return inputs[inputIndex].isChecked();
    }

    private boolean isDisabled(int ledIndex, int inputIndex) {
        INPUT[] inputs = leds[ledIndex].getInputs();
        return inputs[inputIndex].isDisabled();
    }

    private void setDisabled(int ledIndex, int inputIndex, boolean disabled) {
        INPUT[] inputs = leds[ledIndex].getInputs();
        inputs[inputIndex].setDisabled(disabled);
    }

    private void setChecked(int ledIndex, int inputIndex, boolean checked) {
        INPUT[] inputs = leds[ledIndex].getInputs();
        inputs[inputIndex].setChecked(checked);
    }

    // check_state1 porting.
    private void checkData(int ledIndex, int inputIndex) {
        int checkedIndex = getCheckedIndex(ledIndex);

        Debug.logd(new Exception(), "checkedIndex: " + checkedIndex);

        setCheckValue(ledIndex, inputIndex);

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                if(i == ledIndex) {
                    if(j != inputIndex) {
                        // 하나의 input 값 씩 선택되야 하므로 선택된 값을 제외하고 리셋.
                        setChecked(ledIndex, j, false);
                    }
                } else {
                    // 다른 LED 들에 속한 input 값 들에대한 처리.
                    if(j == inputIndex) {
                        // 선택된 값이 check 되었으면 disable 처리, uncheck 되었으면 enable 처리
                        if(isChecked(ledIndex, inputIndex)) {
                            setDisabled(i, j, true);
                        } else {
                            setDisabled(i, j, false);
                        }
                    } else if(j == checkedIndex) {
                        setDisabled(i, j, false);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        ButterKnife.bind(this);
        initializeData();
        //Debug.logd(new Exception(), "" + leds[0]);
    }

    @OnClick({R.id.led1, R.id.led3, R.id.led5, R.id.led2, R.id.led4, R.id.led6, R.id.input1, R.id.input4, R.id.input7, R.id.input2, R.id.input5, R.id.input8, R.id.input3, R.id.input6, R.id.input9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.led1:
                ledIndex = 0;
                currentLedName.setText("LED1");
                //setBackgroundToLED(ledIndex);
                break;
            case R.id.led2:
                ledIndex = 1;
                currentLedName.setText("LED2");
                //setBackgroundToLED(ledIndex);
                break;
            case R.id.led3:
                ledIndex = 2;
                currentLedName.setText("LED3");
                //setBackgroundToLED(ledIndex);
                break;
            case R.id.led4:
                ledIndex = 3;
                currentLedName.setText("LED4");
                //setBackgroundToLED(ledIndex);
                break;
            case R.id.led5:
                ledIndex = 4;
                currentLedName.setText("LED5");
                //setBackgroundToLED(ledIndex);
                break;
            case R.id.led6:
                ledIndex = 5;
                currentLedName.setText("LED6");
                //setBackgroundToLED(ledIndex);
                break;
            case R.id.input1:
                inputIndex = 0;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input2:
                inputIndex = 1;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input3:
                inputIndex = 2;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input4:
                inputIndex = 3;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input5:
                inputIndex = 4;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input6:
                inputIndex = 5;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input7:
                inputIndex = 6;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input8:
                inputIndex = 7;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
            case R.id.input9:
                inputIndex = 8;
                Debug.logd(new Exception(), "led Index: " + ledIndex);
                Debug.logd(new Exception(), "input Index: " + inputIndex);
                //setCheckValue(ledIndex, inputIndex);
                checkData(ledIndex, inputIndex);
                break;
        }
        //displayUI();
        setBackgroundToLED(ledIndex);
        displayData();
        displayUI(ledIndex, inputIndex);
    }
}
