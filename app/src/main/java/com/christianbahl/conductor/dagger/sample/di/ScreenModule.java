package com.christianbahl.conductor.dagger.sample.di;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by cbahl on 12.03.17.
 */
@Module public class ScreenModule {
  private String controllerName;

  public ScreenModule(String controllerName) {
    this.controllerName = controllerName;
  }

  @Provides @ScreenScope @Named("controllerName") String getControllerName() {
    return controllerName;
  }
}
