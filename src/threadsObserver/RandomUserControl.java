package threadsObserver;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import randomperson.RandomUser;
import randomperson.RandomUserGenerator;

//NOTE: extends Observable - which will be the subject that maintains a list of its dependents, the Observers.
public class RandomUserControl extends Observable {

    Observer o;

    //Creating an observer object referenced to the randomuserform, this method is callfed from btn1Clicked() method
    //then starts the thread, calling the run() and fetchRandomUser by returning a user in line 33
    public void fetchRandomUser(Observer o) {

        this.addObserver(o);//the Observer o is the form which will be registered as an observer
        new MyThread().start(); //start() method calls the thread's run() method

    }

    class MyThread extends Thread {

        public void fetchRandomUser() {
            RandomUser user = null;

            try {
                System.out.println("here");
                user = RandomUserGenerator.getRandomUser(); //get a random user from auto generated test data

            } catch (InterruptedException ex) {
                Logger.getLogger(RandomUserControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            setChanged(); // sets the Observable object hasChanged-true.
            notifyObservers(user); // will notify observers by calling its update() method and the arg argument will get the user object
        }

        // Made along with the extends thread and then takes the info that needs to be made into a thread.  
        @Override
        public void run() {
            fetchRandomUser();
        }
    }
}
