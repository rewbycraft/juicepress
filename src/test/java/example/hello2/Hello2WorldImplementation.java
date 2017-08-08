package example.hello2;

import org.roelf.juicepress.annotations.BindsTo;

@BindsTo(IWorld.class)
public class Hello2WorldImplementation implements IWorld {
    @Override
    public void sayWorld() {
        System.out.println("World!");
    }
}
