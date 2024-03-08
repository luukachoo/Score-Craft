package com.feature.live_matches.screen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0011\u0012B\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u00020\u00072\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\u0005\u001a\u00020\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/feature/live_matches/screen/LivesRecyclerAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/feature/live_matches/model/MatchWrapper$Match;", "Lcom/feature/live_matches/screen/LivesRecyclerAdapter$LivesViewHolder;", "()V", "onClick", "Lkotlin/Function1;", "", "onBindViewHolder", "holder", "position", "", "click", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "LivesDiffCallback", "LivesViewHolder", "live_matches_debug"})
public final class LivesRecyclerAdapter extends androidx.recyclerview.widget.ListAdapter<com.feature.live_matches.model.MatchWrapper.Match, com.feature.live_matches.screen.LivesRecyclerAdapter.LivesViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.feature.live_matches.model.MatchWrapper.Match, kotlin.Unit> onClick;

    public LivesRecyclerAdapter() {
        super(null);
    }

    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.feature.live_matches.screen.LivesRecyclerAdapter.LivesViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
                                                                                                   android.view.ViewGroup parent, int viewType) {
        return null;
    }

    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
                                 com.feature.live_matches.screen.LivesRecyclerAdapter.LivesViewHolder holder, int position) {
    }

    public final void onClick(@org.jetbrains.annotations.NotNull()
                              kotlin.jvm.functions.Function1<? super com.feature.live_matches.model.MatchWrapper.Match, kotlin.Unit> click) {
    }

    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c2\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/feature/live_matches/screen/LivesRecyclerAdapter$LivesDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/feature/live_matches/model/MatchWrapper$Match;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "live_matches_debug"})
    static final class LivesDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.feature.live_matches.model.MatchWrapper.Match> {
        @org.jetbrains.annotations.NotNull()
        public static final com.feature.live_matches.screen.LivesRecyclerAdapter.LivesDiffCallback INSTANCE = null;

        private LivesDiffCallback() {
            super();
        }

        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
                                       com.feature.live_matches.model.MatchWrapper.Match oldItem, @org.jetbrains.annotations.NotNull()
                                       com.feature.live_matches.model.MatchWrapper.Match newItem) {
            return false;
        }

        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
                                          com.feature.live_matches.model.MatchWrapper.Match oldItem, @org.jetbrains.annotations.NotNull()
                                          com.feature.live_matches.model.MatchWrapper.Match newItem) {
            return false;
        }
    }

    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/feature/live_matches/screen/LivesRecyclerAdapter$LivesViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/feature/live_matches/databinding/ItemLiveBinding;", "(Lcom/feature/live_matches/screen/LivesRecyclerAdapter;Lcom/feature/live_matches/databinding/ItemLiveBinding;)V", "bind", "", "match", "Lcom/feature/live_matches/model/MatchWrapper$Match;", "live_matches_debug"})
    public final class LivesViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.feature.live_matches.databinding.ItemLiveBinding binding = null;

        public LivesViewHolder(@org.jetbrains.annotations.NotNull()
                               com.feature.live_matches.databinding.ItemLiveBinding binding) {
            super(null);
        }

        public final void bind(@org.jetbrains.annotations.NotNull()
                               com.feature.live_matches.model.MatchWrapper.Match match) {
        }
    }
}