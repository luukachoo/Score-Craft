package com.feature.upcoming_matches.screen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lcom/feature/upcoming_matches/screen/UpcomingMatchesViewModel;", "Landroidx/lifecycle/ViewModel;", "getUpcomingMatchesUseCase", "Lcom/core/domain/use_case/live_matches/GetUpcomingMatchesUseCase;", "(Lcom/core/domain/use_case/live_matches/GetUpcomingMatchesUseCase;)V", "_upcomingMatchesState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/feature/upcoming_matches/state/UpcomingMatchesState;", "upcomingMatchesState", "Lkotlinx/coroutines/flow/StateFlow;", "getUpcomingMatchesState", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchUpcomingMatches", "", "loading", "isLoading", "", "onEvent", "event", "Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent;", "updateErrorMessage", "message", "", "upcoming_matches_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UpcomingMatchesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.core.domain.use_case.live_matches.GetUpcomingMatchesUseCase getUpcomingMatchesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.feature.upcoming_matches.state.UpcomingMatchesState> _upcomingMatchesState = null;

    @javax.inject.Inject()
    public UpcomingMatchesViewModel(@org.jetbrains.annotations.NotNull()
                                    com.core.domain.use_case.live_matches.GetUpcomingMatchesUseCase getUpcomingMatchesUseCase) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.feature.upcoming_matches.state.UpcomingMatchesState> getUpcomingMatchesState() {
        return null;
    }

    public final void onEvent(@org.jetbrains.annotations.NotNull()
                              com.feature.upcoming_matches.event.UpcomingMatchesEvent event) {
    }

    private final void fetchUpcomingMatches() {
    }

    private final void loading(boolean isLoading) {
    }

    private final void updateErrorMessage(java.lang.String message) {
    }
}