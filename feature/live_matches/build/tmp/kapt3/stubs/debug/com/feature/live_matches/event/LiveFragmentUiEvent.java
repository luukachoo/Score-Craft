package com.feature.live_matches.event;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0001\u0002\u0082\u0001\u0001\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/feature/live_matches/event/LiveFragmentUiEvent;", "", "NavigateToDetails", "Lcom/feature/live_matches/event/LiveFragmentUiEvent$NavigateToDetails;", "live_matches_debug"})
public abstract interface LiveFragmentUiEvent {

    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/feature/live_matches/event/LiveFragmentUiEvent$NavigateToDetails;", "Lcom/feature/live_matches/event/LiveFragmentUiEvent;", "id", "", "(I)V", "getId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "live_matches_debug"})
    public static final class NavigateToDetails implements com.feature.live_matches.event.LiveFragmentUiEvent {
        private final int id = 0;

        public NavigateToDetails(int id) {
            super();
        }

        public final int getId() {
            return 0;
        }

        public final int component1() {
            return 0;
        }

        @org.jetbrains.annotations.NotNull()
        public final com.feature.live_matches.event.LiveFragmentUiEvent.NavigateToDetails copy(int id) {
            return null;
        }

        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
                              java.lang.Object other) {
            return false;
        }

        @java.lang.Override()
        public int hashCode() {
            return 0;
        }

        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}