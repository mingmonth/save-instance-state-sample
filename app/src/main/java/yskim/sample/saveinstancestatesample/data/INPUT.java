package yskim.sample.saveinstancestatesample.data;

public class INPUT {
    private boolean isChecked = false;    // 1: checked, 0: unchecked
    private boolean isDisabled = false;   // 1: disabled, 0: enabled

    public INPUT(boolean isChecked, boolean isDisabled) {
        this.isChecked = isChecked;
        this.isDisabled = isDisabled;
    }

    public INPUT(){}

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
