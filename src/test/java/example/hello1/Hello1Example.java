package example.hello1;

import org.roelf.juicepress.InstanceManager;

public class Hello1Example {
    public static void main(String[] args) {
        InstanceManager.addDefaultReflectionModule("example.hello1");
        InstanceManager.init();

        IHello hello = InstanceManager.getInstance(IHello.class);
        hello.sayHello();
    }
}
