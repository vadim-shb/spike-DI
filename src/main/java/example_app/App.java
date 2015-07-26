package example_app;

import spike_di.Context;
import spike_di.ContextFactory;

public class App {
    public static void main(String[] args) {
        Context appContext = ContextFactory.buildContext(ContextConfig.class);
        appContext.runExecutables();
    }
}
