package example.hello1;

import org.roelf.juicepress.InstanceManager;

public class Hello1Example {
    public static void main(String[] args) {
        InstanceManager.init("example.hello1");

        IHello hello = InstanceManager.getInstance(IHello.class);
        hello.sayHello();
    }
}
