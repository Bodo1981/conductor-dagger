# dagger-conductor
Extension to use conductor with the new dagger.android (com.google.dagger:dagger-android-support) module.

Description: https://google.github.io/dagger/android.html

# Install
[ ![Download](https://api.bintray.com/packages/bodo1981/maven/conductor-dagger/images/download.svg) ](https://bintray.com/bodo1981/maven/conductor-dagger/_latestVersion)

```gradle
dependencies {
    compile 'com.christianbahl.conductor:conductor-dagger-android:1.0.0'
    annotationProcessor 'com.google.dagger:dagger-compiler:2.10-rc4'
}
```

## Changelog
The changelog can be found in the [release section](https://github.com/Bodo1981/conductor-dagger/releases)

## Issues
Please fill any issues here [issue page](https://github.com/Bodo1981/conductor-dagger/issues)

# Usage
1. Install [ConductorInjectionModule](https://github.com/Bodo1981/conductor-dagger/blob/master/library/src/main/java/com/christianbahl/conductor/ConductorInjectionModule.java) in your application component to ensure that all bindings necessary for these base types are available.
    ```java
    @Singleton @Component(modules = { ConductorInjectionModule.class, ... }) public interface AppComponent {
      void inject(CustomApplication application);
    }
    ```
    
2. Next you hav to write a @Subcomponent that implements AndroidInjector<YourController>, with a @Subcomponent.Builder that extends AndroidInjector.Builder<YourController>
    ```java
    @ScreenScope @Subcomponent public interface YourControllerComponent extends AndroidInjector<YourController> {
      @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<YourController> {
      }
    }
    ```

    If your controller depends on controller specific stuff you can set this stuff by overriding the seedInstance method in the builder (e.g. YourControllerModule provides the simple class name of the controller).
    ```java
    @ScreenScope @Subcomponent(modules = { YourControllerModule.class }) public interface MainComponent extends AndroidInjector<YourController> {
      @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<YourController> {
        public abstract Builder yourControllerModule(YourControllerModule yourControllerModule);

        @Override public void seedInstance(YourController yourController) {
          yourControllerModule(new YourControllerModule(yourController.getClass().getSimpleName()));
        }
      }
    }
    ```

3. After defining the @Subcomponent, add it to your component hierarchy by defining a module that binds the subcomponent builder and adding it to the component that injects your Application
    ```java
    @Module(subcomponents = { YourControllerComponent.class }) public abstract class YourControllerModule {
      @Binds @IntoMap @ControllerKey(YourController.class)
      abstract AndroidInjector.Factory<? extends Controller> bindYourControllerInjectorFactory(YourControllerComponent.Builder builder);
    }
    ```
    
    ```java
    @Component(modules = {..., YourControllerModule.class})
    interface YourApplicationComponent {}
    ```
    
4. Next, make your Application implement [HasDispatchingControllerInjector](https://github.com/Bodo1981/conductor-dagger/blob/master/library/src/main/java/com/christianbahl/conductor/HasDispatchingControllerInjector.java) and @Inject a DispatchingAndroidInjector<Controller> to return from the controllerInjector() method:
    ```java
    public class YourApplication extends Application implements HasDispatchingControllerInjector {
       @Inject DispatchingAndroidInjector<Controller> dispatchingControllerInjector;

       @Override public void onCreate() {
         super.onCreate();
         
         DaggerYourApplicationComponent.create().inject(this);
       }

       @Override public DispatchingAndroidInjector<Controller> controllerInjector() {
         return dispatchingControllerInjector;
       }
    }
    ```

5. Finally, in your Controller.onCreateView() method, call **ConductorInjection.inject(this)** at the beginning
    ```java
    public class YourController extends Controller {
      @Inject @Named("controllerName") String controllerName;

      @NonNull @Override protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        ConductorInjection.inject(this);
        View view = layoutInflater.inflate(R.layout.controller_your, viewGroup, false);
        ((TextView) view.findViewById(R.id.textView)).setText("Injected value = " + controllerName);
        return view;
      }
    }
    ```

#License

    Copyright 2017 Christian Bahl

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License. 
