.class public LDefinition;
.super Ljava/lang/Object;
.source "Definition.java"


# instance fields
.field private author:Ljava/lang/String;

.field private currentVote:Ljava/lang/String;

.field private def:Ljava/lang/String;

.field private defid:J

.field private example:Ljava/lang/String;

.field private permaLink:Ljava/lang/String;

.field private thumbsDown:J

.field private thumbsUp:J

.field private word:Ljava/lang/String;


# direct methods
.method constructor <init>()V
    .registers 5

    .prologue
    const-wide/16 v2, 0x0

    .line 12
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    const-string v0, ""

    iput-object v0, p0, LDefinition;->def:Ljava/lang/String;

    .line 3
    const-string v0, ""

    iput-object v0, p0, LDefinition;->permaLink:Ljava/lang/String;

    .line 4
    iput-wide v2, p0, LDefinition;->thumbsUp:J

    .line 5
    const-string v0, ""

    iput-object v0, p0, LDefinition;->author:Ljava/lang/String;

    .line 6
    const-string v0, ""

    iput-object v0, p0, LDefinition;->word:Ljava/lang/String;

    .line 7
    iput-wide v2, p0, LDefinition;->defid:J

    .line 8
    const-string v0, ""

    iput-object v0, p0, LDefinition;->currentVote:Ljava/lang/String;

    .line 9
    const-string v0, ""

    iput-object v0, p0, LDefinition;->example:Ljava/lang/String;

    .line 10
    iput-wide v2, p0, LDefinition;->thumbsDown:J

    .line 13
    return-void
.end method

.method private strToL(Ljava/lang/String;)J
    .registers 4

    .prologue
    .line 17
    :try_start_0
    invoke-static {p1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_3} :catch_5

    move-result-wide v0

    .line 19
    :goto_4
    return-wide v0

    .line 18
    :catch_5
    move-exception v0

    .line 19
    const-wide/16 v0, 0x0

    goto :goto_4
.end method


# virtual methods
.method public getAuthor()Ljava/lang/String;
    .registers 2

    .prologue
    .line 36
    iget-object v0, p0, LDefinition;->author:Ljava/lang/String;

    return-object v0
.end method

.method public getCurrentVote()Ljava/lang/String;
    .registers 2

    .prologue
    .line 48
    iget-object v0, p0, LDefinition;->currentVote:Ljava/lang/String;

    return-object v0
.end method

.method public getDefid()J
    .registers 3

    .prologue
    .line 44
    iget-wide v0, p0, LDefinition;->defid:J

    return-wide v0
.end method

.method public getDefinition()Ljava/lang/String;
    .registers 2

    .prologue
    .line 24
    iget-object v0, p0, LDefinition;->def:Ljava/lang/String;

    return-object v0
.end method

.method public getExample()Ljava/lang/String;
    .registers 2

    .prologue
    .line 52
    iget-object v0, p0, LDefinition;->example:Ljava/lang/String;

    return-object v0
.end method

.method public getPermaLink()Ljava/lang/String;
    .registers 2

    .prologue
    .line 28
    iget-object v0, p0, LDefinition;->permaLink:Ljava/lang/String;

    return-object v0
.end method

.method public getThumbsDown()J
    .registers 3

    .prologue
    .line 56
    iget-wide v0, p0, LDefinition;->thumbsDown:J

    return-wide v0
.end method

.method public getThumbsUp()J
    .registers 3

    .prologue
    .line 32
    iget-wide v0, p0, LDefinition;->thumbsUp:J

    return-wide v0
.end method

.method public getWord()Ljava/lang/String;
    .registers 2

    .prologue
    .line 40
    iget-object v0, p0, LDefinition;->word:Ljava/lang/String;

    return-object v0
.end method

.method public toString()Ljava/lang/String;
    .registers 3

    .prologue
    .line 61
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, LDefinition;->word:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "\n\n"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, LDefinition;->def:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "\n\n"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, LDefinition;->example:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
