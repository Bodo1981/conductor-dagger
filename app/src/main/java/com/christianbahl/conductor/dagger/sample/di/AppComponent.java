package com.christianbahl.conductor.dagger.sample.di;

import com.christianbahl.conductor.ConductorInjectionModule;
import com.christianbahl.conductor.dagger.sample.CustomApplication;
import com.christianbahl.conductor.dagger.sample.controller.MainModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by cbahl on 12.03.17.
 */
@Singleton @Component(modules = { ConductorInjectionModule.class, MainModule.class }) public interface AppComponent {
  void inject(CustomApplication application);
}
