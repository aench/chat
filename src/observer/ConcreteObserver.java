package observer;

public class ConcreteObserver implements Observer {

    ConcreteSubject subject;

    private String observerState;

    @Override
    public void update() {
        observerState = subject.getState();
    }
}
