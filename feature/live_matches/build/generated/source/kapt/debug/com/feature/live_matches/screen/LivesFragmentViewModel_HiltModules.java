package com.feature.live_matches.screen;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap;
import dagger.hilt.codegen.OriginatingElement;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

import java.lang.String;

import javax.annotation.processing.Generated;

@Generated("dagger.hilt.android.processor.internal.viewmodel.ViewModelProcessor")
@OriginatingElement(
        topLevelClass = LivesFragmentViewModel.class
)
public final class LivesFragmentViewModel_HiltModules {
    private LivesFragmentViewModel_HiltModules() {
    }

    @Module
    @InstallIn(ViewModelComponent.class)
    public abstract static class BindsModule {
        private BindsModule() {
        }

        @Binds
        @IntoMap
        @StringKey("com.feature.live_matches.screen.LivesFragmentViewModel")
        @HiltViewModelMap
        public abstract ViewModel binds(LivesFragmentViewModel vm);
    }

    @Module
    @InstallIn(ActivityRetainedComponent.class)
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @IntoSet
        @HiltViewModelMap.KeySet
        public static String provide() {
            return "com.feature.live_matches.screen.LivesFragmentViewModel";
        }
    }
}
