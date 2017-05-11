package com.christianbahl.conductor;

import android.app.Activity;
import android.util.Log;
import com.bluelinelabs.conductor.Controller;
import dagger.android.DispatchingAndroidInjector;
import dagger.internal.Preconditions;

/**
 * Created by cbahl on 12.03.17.
 */
public class ConductorInjection {

  public static void inject(Controller controller) {
    Preconditions.checkNotNull(controller, "controller");
    HasControllerInjector hasDispatchingControllerInjector = findHasControllerInjector(controller);
    Log.d("dagger.android", String.format("An injector for %s was found in %s", controller.getClass().getCanonicalName(),
        hasDispatchingControllerInjector.getClass().getCanonicalName()));
    DispatchingAndroidInjector<Controller> controllerInjector = hasDispatchingControllerInjector.controllerInjector();
    Preconditions.checkNotNull(controllerInjector, "%s.controllerInjector() returned null",
        hasDispatchingControllerInjector.getClass().getCanonicalName());
    controllerInjector.inject(controller);
  }

  private static HasControllerInjector findHasControllerInjector(Controller controller) {
    Controller parentController = controller;

    do {
      if ((parentController = parentController.getParentController()) == null) {
        Activity activity = controller.getActivity();
        if (activity instanceof HasControllerInjector) {
          return (HasControllerInjector) activity;
        }

        if (activity.getApplication() instanceof HasControllerInjector) {
          return (HasControllerInjector) activity.getApplication();
        }

        throw new IllegalArgumentException(
            String.format("No injector was found for %s", new Object[] { controller.getClass().getCanonicalName() }));
      }
    } while (!(parentController instanceof HasControllerInjector));

    return (HasControllerInjector) parentController;
  }
}
