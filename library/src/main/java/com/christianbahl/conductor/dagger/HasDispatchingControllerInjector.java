package com.christianbahl.conductor.dagger;

import com.bluelinelabs.conductor.Controller;
import dagger.android.DispatchingAndroidInjector;

/**
 * Created by cbahl on 12.03.17.
 */

public interface HasDispatchingControllerInjector {

  DispatchingAndroidInjector<Controller> controllerInjector();
}
