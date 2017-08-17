package example.singleton1;

import org.roelf.juicepress.InstanceManager;

public class Singleton1Example {
    public static void main(String[] args) {
        InstanceManager.addDefaultReflectionModule("example.singleton1");
        InstanceManager.init();

        example();
        example();
    }

    public static void example() {
        SingletonObject obj = InstanceManager.getInstance(SingletonObject.class);
        obj.saySomething();
    }
}
