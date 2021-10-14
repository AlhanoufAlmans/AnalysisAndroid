package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a8\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"closeFinally", "", "Ljava/lang/AutoCloseable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jre7"}, k = 2, mv = {1, 1, 8})
@JvmName(name = "AutoCloseableKt")
/* compiled from: AutoCloseable.kt */
public final class AutoCloseableKt {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        closeFinally(r2, r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T extends AutoCloseable, R> R use(T t, Function1<? super T, ? extends R> function1) {
        Throwable th = null;
        R r = (R) function1.invoke(t);
        InlineMarker.finallyStart(1);
        closeFinally(t, th);
        InlineMarker.finallyEnd(1);
        return r;
    }

    @SinceKotlin(version = "1.1")
    @PublishedApi
    public static final void closeFinally(@Nullable AutoCloseable autoCloseable, @Nullable Throwable th) {
        if (autoCloseable != null) {
            if (th == null) {
                autoCloseable.close();
                return;
            }
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
    }
}
