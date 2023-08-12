package domin.homesite.cookbook.adapterpersistence.testutil;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.lang.annotation.Annotation;

public class WeldRunner {

    private static WeldRunner INSTANCE;

    public final WeldContainer weldContainer;

    private WeldRunner() {
        INSTANCE = this;
        Weld weld = new Weld();
        this.weldContainer = weld.initialize();
    }

    public static WeldRunner getInstance(){
        if(INSTANCE == null){
            new WeldRunner();
        }
        return INSTANCE;
    }

    public <T> T getCDIBean(Class<T> clazz) {
        return weldContainer.select(clazz).get();
    }

    public <T> T getCDIBean(Class<T> clazz, Annotation... qualifiers) {
        return  weldContainer.select(clazz, qualifiers).get();
    }
}
