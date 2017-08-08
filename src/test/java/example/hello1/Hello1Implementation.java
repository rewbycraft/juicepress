package example.hello1;

import org.roelf.juicepress.annotations.BindsTo;

@BindsTo(IHello.class)
public class Hello1Implementation implements IHello {
    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
