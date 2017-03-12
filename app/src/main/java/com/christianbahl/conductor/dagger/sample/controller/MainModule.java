package com.christianbahl.conductor.dagger.sample.controller;

/**
 * Created by cbahl on 12.03.17.
 */

import com.bluelinelabs.conductor.Controller;
import com.christianbahl.conductor.dagger.ControllerKey;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = { MainComponent.class }) public abstract class MainModule {

  @Binds @IntoMap @ControllerKey(MainController.class)
  abstract AndroidInjector.Factory<? extends Controller> bindHomeControllerInjectorFactory(MainComponent.Builder builder);
}
