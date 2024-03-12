package com.feature.live_matches.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b)\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0013J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u000bH\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0011H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u000bH\u00c6\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\t\u00103\u001a\u00020\u000bH\u00c6\u0003J\u0096\u0001\u00104\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00105J\u0013\u00106\u001a\u00020\u00052\b\u00107\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00108\u001a\u00020\u000bH\u00d6\u0001J\t\u00109\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0015\u00a8\u0006:"}, d2 = {"Lcom/feature/live_matches/model/Games;", "", "beginAt", "", "complete", "", "detailedStats", "endAt", "finished", "forfeit", "id", "", "length", "matchId", "position", "status", "winner", "Lcom/feature/live_matches/model/MatchWrapper$Match$Winner;", "winnerType", "(Ljava/lang/String;ZZLjava/lang/String;ZZILjava/lang/Integer;IILjava/lang/String;Lcom/feature/live_matches/model/MatchWrapper$Match$Winner;Ljava/lang/String;)V", "getBeginAt", "()Ljava/lang/String;", "getComplete", "()Z", "getDetailedStats", "getEndAt", "getFinished", "getForfeit", "getId", "()I", "getLength", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMatchId", "getPosition", "getStatus", "getWinner", "()Lcom/feature/live_matches/model/MatchWrapper$Match$Winner;", "getWinnerType", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;ZZLjava/lang/String;ZZILjava/lang/Integer;IILjava/lang/String;Lcom/feature/live_matches/model/MatchWrapper$Match$Winner;Ljava/lang/String;)Lcom/feature/live_matches/model/Games;", "equals", "other", "hashCode", "toString", "live_matches_debug"})
public final class Games {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String beginAt = null;
    private final boolean complete = false;
    private final boolean detailedStats = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String endAt = null;
    private final boolean finished = false;
    private final boolean forfeit = false;
    private final int id = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer length = null;
    private final int matchId = 0;
    private final int position = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    @org.jetbrains.annotations.NotNull()
    private final com.feature.live_matches.model.MatchWrapper.Match.Winner winner = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String winnerType = null;
    
    public Games(@org.jetbrains.annotations.Nullable()
    java.lang.String beginAt, boolean complete, boolean detailedStats, @org.jetbrains.annotations.Nullable()
    java.lang.String endAt, boolean finished, boolean forfeit, int id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer length, int matchId, int position, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    com.feature.live_matches.model.MatchWrapper.Match.Winner winner, @org.jetbrains.annotations.NotNull()
    java.lang.String winnerType) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBeginAt() {
        return null;
    }
    
    public final boolean getComplete() {
        return false;
    }
    
    public final boolean getDetailedStats() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEndAt() {
        return null;
    }
    
    public final boolean getFinished() {
        return false;
    }
    
    public final boolean getForfeit() {
        return false;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getLength() {
        return null;
    }
    
    public final int getMatchId() {
        return 0;
    }
    
    public final int getPosition() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.feature.live_matches.model.MatchWrapper.Match.Winner getWinner() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWinnerType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component10() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.feature.live_matches.model.MatchWrapper.Match.Winner component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component8() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.feature.live_matches.model.Games copy(@org.jetbrains.annotations.Nullable()
    java.lang.String beginAt, boolean complete, boolean detailedStats, @org.jetbrains.annotations.Nullable()
    java.lang.String endAt, boolean finished, boolean forfeit, int id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer length, int matchId, int position, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    com.feature.live_matches.model.MatchWrapper.Match.Winner winner, @org.jetbrains.annotations.NotNull()
    java.lang.String winnerType) {
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