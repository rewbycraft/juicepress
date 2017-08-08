package example.hello2;

import com.google.inject.Inject;
import example.hello1.IHello;
import org.roelf.juicepress.annotations.BindsTo;

@BindsTo(IHello.class)
public class Hello2Implementation implements IHello {
    @Inject
    private IWorld world;

    @Override
    public void sayHello() {
        System.out.print("Hello ");
        world.sayWorld();
    }
}
