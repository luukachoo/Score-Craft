package com.feature.upcoming_matches.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u000eJ\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\fH\u00c6\u0003JU\u0010\u001f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\u0006H\u00d6\u0001J\t\u0010$\u001a\u00020\fH\u00d6\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006%"}, d2 = {"Lcom/feature/upcoming_matches/model/UpcomingMatch;", "", "results", "", "Lcom/feature/upcoming_matches/model/Result;", "id", "", "videogame", "Lcom/feature/upcoming_matches/model/VideoGame;", "opponents", "Lcom/core/domain/model/matches/live/GetOpponentWrapper;", "beginAt", "", "name", "(Ljava/util/List;ILcom/feature/upcoming_matches/model/VideoGame;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getBeginAt", "()Ljava/lang/String;", "getId", "()I", "getName", "getOpponents", "()Ljava/util/List;", "getResults", "getVideogame", "()Lcom/feature/upcoming_matches/model/VideoGame;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "upcoming_matches_debug"})
public final class UpcomingMatch {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.feature.upcoming_matches.model.Result> results = null;
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.feature.upcoming_matches.model.VideoGame videogame = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.core.domain.model.matches.live.GetOpponentWrapper> opponents = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String beginAt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String name = null;

    public UpcomingMatch(@org.jetbrains.annotations.NotNull()
                         java.util.List<com.feature.upcoming_matches.model.Result> results, int id, @org.jetbrains.annotations.NotNull()
                         com.feature.upcoming_matches.model.VideoGame videogame, @org.jetbrains.annotations.NotNull()
                         java.util.List<com.core.domain.model.matches.live.GetOpponentWrapper> opponents, @org.jetbrains.annotations.Nullable()
                         java.lang.String beginAt, @org.jetbrains.annotations.Nullable()
                         java.lang.String name) {
        super();
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.feature.upcoming_matches.model.Result> getResults() {
        return null;
    }

    public final int getId() {
        return 0;
    }

    @org.jetbrains.annotations.NotNull()
    public final com.feature.upcoming_matches.model.VideoGame getVideogame() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.core.domain.model.matches.live.GetOpponentWrapper> getOpponents() {
        return null;
    }

    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBeginAt() {
        return null;
    }

    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.feature.upcoming_matches.model.Result> component1() {
        return null;
    }

    public final int component2() {
        return 0;
    }

    @org.jetbrains.annotations.NotNull()
    public final com.feature.upcoming_matches.model.VideoGame component3() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.core.domain.model.matches.live.GetOpponentWrapper> component4() {
        return null;
    }

    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }

    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }

    @org.jetbrains.annotations.NotNull()
    public final com.feature.upcoming_matches.model.UpcomingMatch copy(@org.jetbrains.annotations.NotNull()
                                                                       java.util.List<com.feature.upcoming_matches.model.Result> results, int id, @org.jetbrains.annotations.NotNull()
                                                                       com.feature.upcoming_matches.model.VideoGame videogame, @org.jetbrains.annotations.NotNull()
                                                                       java.util.List<com.core.domain.model.matches.live.GetOpponentWrapper> opponents, @org.jetbrains.annotations.Nullable()
                                                                       java.lang.String beginAt, @org.jetbrains.annotations.Nullable()
                                                                       java.lang.String name) {
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