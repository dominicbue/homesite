package domin.homesite.cookbook.adapterpersistence.weld;

import org.jboss.weld.context.bound.BoundRequestContext;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.HashMap;
import java.util.Map;

public class ManageRequestScope {

    private final Map<String, Object> reqeustDataStore = new HashMap<>();

    private final WeldContainer container;

    public ManageRequestScope() {
        this.container = WeldRunner.getInstance().weldContainer;
    }

    public void createRequestContext() {
        BoundRequestContext context = container.select(BoundRequestContext.class).get();
        context.associate(reqeustDataStore);
        context.activate();
    }

    public void closeRequestContext(){
        BoundRequestContext context = container.select(BoundRequestContext.class).get();

        if(context.isActive()){
            try{
                context.invalidate();
                context.deactivate();
            } finally {
                context.dissociate(reqeustDataStore);
            }
        }
    }
}
