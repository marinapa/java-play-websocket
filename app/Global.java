import play.*;

import org.springframework.context.*;
import org.springframework.context.support.*;

/**
 * Created by Maryna Pashkouskaya on 2015-12-18.
 */
public class Global extends GlobalSettings {

    private ApplicationContext ctx;

    @Override
    public void onStart(Application app) {
        ctx = new ClassPathXmlApplicationContext("spring-context.xml");
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        return ctx.getBean(clazz);
    }

}
