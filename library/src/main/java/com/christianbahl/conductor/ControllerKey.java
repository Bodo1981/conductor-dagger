package com.christianbahl.conductor;

import com.bluelinelabs.conductor.Controller;
import dagger.MapKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by cbahl on 12.03.17.
 */
@MapKey @Target({ ElementType.METHOD }) public @interface ControllerKey {
  Class<? extends Controller> value();
}

