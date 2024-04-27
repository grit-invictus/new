import java.util.ArrayList;
import java.util.List;


interface subject{


    public void removeObserver(Observer observer);

    void registerObserver(Observer observer);



    public void notifyObserver();


}
class concreteSubject implements subject{
    List<Observer> list = new ArrayList<>();
    Observer observer;
    private int state;



    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObserver();

    }



    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
      list.add(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer: list)
        {
            observer.update();
        }

    }
}
interface Observer{
    public void update();
}
class ConcreteObserver implements Observer{

  public concreteSubject subject;
    ConcreteObserver(concreteSubject subject)
    {
        this.subject=subject;
        this.subject.registerObserver(this);
    }
    @Override
    public void update() {
       System.out.println("change in state of observer"+subject.getState());
    }
}
public class Main {
    public static void main(String[] args) {
        concreteSubject subject = new concreteSubject();
        ConcreteObserver observer1 = new ConcreteObserver(subject);
        ConcreteObserver observer2 = new ConcreteObserver(subject);

        // Changing state of the subject will notify all observers
        subject.setState(10);

    }
}
