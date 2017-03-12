package com.christianbahl.conductor.dagger.sample.controller;

import com.christianbahl.conductor.dagger.sample.di.ScreenModule;
import com.christianbahl.conductor.dagger.sample.di.ScreenScope;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by cbahl on 12.03.17.
 */
@ScreenScope @Subcomponent(modules = { ScreenModule.class }) public interface MainComponent extends AndroidInjector<MainController> {
  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<MainController> {
    public abstract Builder screenModule(ScreenModule screenModule);

    @Override public void seedInstance(MainController mainController) {
      screenModule(new ScreenModule(mainController.getClass().getSimpleName()));
    }
  }
}