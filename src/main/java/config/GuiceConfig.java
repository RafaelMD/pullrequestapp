package config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.squarespace.jersey2.guice.JerseyGuiceModule;
import com.squarespace.jersey2.guice.JerseyGuiceUtils;

import java.util.ArrayList;
import java.util.List;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        List<Module> modules = new ArrayList<Module>();
        modules.add(new JerseyGuiceModule("__HK2_Generated_0"));
        modules.add(new AppModule());

        Injector injector = Guice.createInjector(modules);
        JerseyGuiceUtils.install(injector);
        return injector;
    }
}
