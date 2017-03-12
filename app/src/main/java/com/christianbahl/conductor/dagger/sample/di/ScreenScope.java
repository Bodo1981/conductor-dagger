package com.christianbahl.conductor.dagger.sample.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by cbahl on 12.03.17.
 */
@Scope @Retention(RetentionPolicy.RUNTIME) public @interface ScreenScope {
}
