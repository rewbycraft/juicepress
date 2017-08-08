package example.singleton1;

import com.google.inject.Singleton;

@Singleton
public class SingletonObject {
    private int i = 0;

    public void saySomething() {
        System.out.println("Something: " + i);
        i++;
    }
}
