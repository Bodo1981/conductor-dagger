package com.christianbahl.conductor;

import com.bluelinelabs.conductor.Controller;
import dagger.android.DispatchingAndroidInjector;

/**
 * Created by cbahl on 12.03.17.
 */
public interface HasDispatchingControllerInjector {
  DispatchingAndroidInjector<Controller> controllerInjector();
}
