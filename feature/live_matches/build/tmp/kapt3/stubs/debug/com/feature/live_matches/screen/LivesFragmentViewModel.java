package com.feature.live_matches.screen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u0012\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0016\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006 "}, d2 = {"Lcom/feature/live_matches/screen/LivesFragmentViewModel;", "Landroidx/lifecycle/ViewModel;", "getMatchesUseCase", "Lcom/core/domain/use_case/live_matches/GetMatchesUseCase;", "(Lcom/core/domain/use_case/live_matches/GetMatchesUseCase;)V", "_liveState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/feature/live_matches/state/LiveState;", "_uiEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/feature/live_matches/event/LiveFragmentUiEvent;", "liveState", "Lkotlinx/coroutines/flow/StateFlow;", "getLiveState", "()Lkotlinx/coroutines/flow/StateFlow;", "uiEvent", "getUiEvent", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "fetchLiveMatches", "", "loading", "isLoading", "", "onEvent", "event", "Lcom/feature/live_matches/event/LivesFragmentEvent;", "updateErrorMessage", "message", "", "updateNavigationEvent", "events", "(Lcom/feature/live_matches/event/LiveFragmentUiEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "live_matches_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LivesFragmentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.core.domain.use_case.live_matches.GetMatchesUseCase getMatchesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.feature.live_matches.state.LiveState> _liveState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.feature.live_matches.event.LiveFragmentUiEvent> _uiEvent = null;

    @javax.inject.Inject()
    public LivesFragmentViewModel(@org.jetbrains.annotations.NotNull()
                                  com.core.domain.use_case.live_matches.GetMatchesUseCase getMatchesUseCase) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.feature.live_matches.state.LiveState> getLiveState() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableSharedFlow<com.feature.live_matches.event.LiveFragmentUiEvent> getUiEvent() {
        return null;
    }

    public final void onEvent(@org.jetbrains.annotations.NotNull()
                              com.feature.live_matches.event.LivesFragmentEvent event) {
    }

    private final void fetchLiveMatches() {
    }

    private final void loading(boolean isLoading) {
    }

    private final void updateErrorMessage(java.lang.String message) {
    }

    private final java.lang.Object updateNavigationEvent(com.feature.live_matches.event.LiveFragmentUiEvent events, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}