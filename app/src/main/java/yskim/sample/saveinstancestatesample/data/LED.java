package yskim.sample.saveinstancestatesample.data;

public class LED {
    private INPUT[] inputs = new INPUT[9];

    public INPUT[] getInputs() {
        return inputs;
    }

    public void setInputs(INPUT[] inputs) {
        this.inputs = inputs;
    }

    public LED() {
        for(int i = 0; i < inputs.length; i++) {
            inputs[i] = new INPUT(false, false);
        }
    }
}
