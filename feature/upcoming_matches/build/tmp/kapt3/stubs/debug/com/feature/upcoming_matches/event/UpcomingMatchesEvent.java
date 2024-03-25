package com.feature.upcoming_matches.event;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent;", "", "()V", "FetchUpcomingMatches", "ResetErrorMessage", "Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent$FetchUpcomingMatches;", "Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent$ResetErrorMessage;", "upcoming_matches_debug"})
public abstract class UpcomingMatchesEvent {

    private UpcomingMatchesEvent() {
        super();
    }

    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent$FetchUpcomingMatches;", "Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "upcoming_matches_debug"})
    public static final class FetchUpcomingMatches extends com.feature.upcoming_matches.event.UpcomingMatchesEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.feature.upcoming_matches.event.UpcomingMatchesEvent.FetchUpcomingMatches INSTANCE = null;

        private FetchUpcomingMatches() {
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

    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent$ResetErrorMessage;", "Lcom/feature/upcoming_matches/event/UpcomingMatchesEvent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "upcoming_matches_debug"})
    public static final class ResetErrorMessage extends com.feature.upcoming_matches.event.UpcomingMatchesEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.feature.upcoming_matches.event.UpcomingMatchesEvent.ResetErrorMessage INSTANCE = null;

        private ResetErrorMessage() {
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