package com.feature.upcoming_matches.screen;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.internal.lifecycle.HiltViewModelMap.KeySet")
@DaggerGenerated
@Generated(
        value = "dagger.internal.codegen.ComponentProcessor",
        comments = "https://dagger.dev"
)
@SuppressWarnings({
        "unchecked",
        "rawtypes",
        "KotlinInternal",
        "KotlinInternalInJava"
})
public final class UpcomingMatchesViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
    public static UpcomingMatchesViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String provide() {
        return Preconditions.checkNotNullFromProvides(UpcomingMatchesViewModel_HiltModules.KeyModule.provide());
    }

    @Override
    public String get() {
        return provide();
    }

    private static final class InstanceHolder {
        private static final UpcomingMatchesViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new UpcomingMatchesViewModel_HiltModules_KeyModule_ProvideFactory();
    }
}
