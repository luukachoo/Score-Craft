package com.feature.past_matches.screen;

import com.core.domain.use_case.live_matches.GetPastMatchesUseCase;

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
public final class PastMatchesViewModel_Factory implements Factory<PastMatchesViewModel> {
    private final Provider<GetPastMatchesUseCase> getPastMatchesUseCaseProvider;

    public PastMatchesViewModel_Factory(
            Provider<GetPastMatchesUseCase> getPastMatchesUseCaseProvider) {
        this.getPastMatchesUseCaseProvider = getPastMatchesUseCaseProvider;
    }

    public static PastMatchesViewModel_Factory create(
            Provider<GetPastMatchesUseCase> getPastMatchesUseCaseProvider) {
        return new PastMatchesViewModel_Factory(getPastMatchesUseCaseProvider);
    }

    public static PastMatchesViewModel newInstance(GetPastMatchesUseCase getPastMatchesUseCase) {
        return new PastMatchesViewModel(getPastMatchesUseCase);
    }

    @Override
    public PastMatchesViewModel get() {
        return newInstance(getPastMatchesUseCaseProvider.get());
    }
}
