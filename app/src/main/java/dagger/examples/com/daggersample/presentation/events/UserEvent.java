package dagger.examples.com.daggersample.presentation.events;

public class UserEvent {
    private boolean result;

    public UserEvent(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
