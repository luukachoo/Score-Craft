package com.feature.live_matches.screen;

import com.core.domain.use_case.live_matches.GetMatchesUseCase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class LivesFragmentViewModel_Factory implements Factory<LivesFragmentViewModel> {
    private final Provider<GetMatchesUseCase> getMatchesUseCaseProvider;

    public LivesFragmentViewModel_Factory(Provider<GetMatchesUseCase> getMatchesUseCaseProvider) {
        this.getMatchesUseCaseProvider = getMatchesUseCaseProvider;
    }

    public static LivesFragmentViewModel_Factory create(
            Provider<GetMatchesUseCase> getMatchesUseCaseProvider) {
        return new LivesFragmentViewModel_Factory(getMatchesUseCaseProvider);
    }

    public static LivesFragmentViewModel newInstance(GetMatchesUseCase getMatchesUseCase) {
        return new LivesFragmentViewModel(getMatchesUseCase);
    }

    @Override
    public LivesFragmentViewModel get() {
        return newInstance(getMatchesUseCaseProvider.get());
    }
}
