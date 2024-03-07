package com.feature.live_matches.screen;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001b"}, d2 = {"Lcom/feature/live_matches/screen/LivesFragment;", "Lcom/core/common/base/BaseFragment;", "Lcom/feature/live_matches/databinding/FragmentLivesBinding;", "()V", "livesAdapter", "Lcom/feature/live_matches/screen/LivesRecyclerAdapter;", "getLivesAdapter", "()Lcom/feature/live_matches/screen/LivesRecyclerAdapter;", "livesAdapter$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/feature/live_matches/screen/LivesFragmentViewModel;", "getViewModel", "()Lcom/feature/live_matches/screen/LivesFragmentViewModel;", "viewModel$delegate", "bind", "", "bindObserves", "bindViewActionListeners", "handleLiveState", "state", "Lcom/feature/live_matches/state/LiveState;", "(Lcom/feature/live_matches/state/LiveState;)Lkotlin/Unit;", "handleNavigation", "matchId", "", "setUpRecycler", "live_matches_debug"})
public final class LivesFragment extends com.core.common.base.BaseFragment<com.feature.live_matches.databinding.FragmentLivesBinding> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy livesAdapter$delegate = null;
    
    public LivesFragment() {
        super(null);
    }
    
    private final com.feature.live_matches.screen.LivesFragmentViewModel getViewModel() {
        return null;
    }
    
    private final com.feature.live_matches.screen.LivesRecyclerAdapter getLivesAdapter() {
        return null;
    }
    
    @java.lang.Override()
    public void bind() {
    }
    
    @java.lang.Override()
    public void bindObserves() {
    }
    
    @java.lang.Override()
    public void bindViewActionListeners() {
    }
    
    private final kotlin.Unit handleLiveState(com.feature.live_matches.state.LiveState state) {
        return null;
    }
    
    private final void setUpRecycler() {
    }
    
    private final void handleNavigation(int matchId) {
    }
}