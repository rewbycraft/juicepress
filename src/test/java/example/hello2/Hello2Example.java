package example.hello2;

import example.hello1.IHello;
import org.roelf.juicepress.InstanceManager;

public class Hello2Example {
    public static void main(String[] args) {
        InstanceManager.addDefaultReflectionModule("example.hello2");
        InstanceManager.init();

        IHello hello = InstanceManager.getInstance(IHello.class);
        hello.sayHello();
    }
}
