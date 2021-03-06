package kotlin.text;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.apache.cordova.globalization.Globalization;
import org.apache.cordova.networkinformation.NetworkManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000â\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b*\u00020\u0002\u001a\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\u0002\u001aE\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\b\u001a3\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00050\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001aM\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aN\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u00020\u00050\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b¢\u0006\u0002\u0010\u0018\u001ah\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0019\u001a`\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\b¢\u0006\u0002\u0010\u0018\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\b\u001a!\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0012\u0010\u001d\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0012\u0010 \u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a!\u0010!\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010!\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010\"\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0015\u0010#\u001a\u00020\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001cH\b\u001a)\u0010%\u001a\u00020\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001c2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a\u001c\u0010'\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001cH\b¢\u0006\u0002\u0010(\u001a!\u0010)\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010)\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a6\u0010*\u001a\u00020\u0002*\u00020\u00022'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010+H\b\u001a6\u0010*\u001a\u00020\u001f*\u00020\u001f2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010+H\b\u001aQ\u0010.\u001a\u0002H/\"\f\b\u0000\u0010/*\u000600j\u0002`1*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010+H\b¢\u0006\u0002\u00102\u001a!\u00103\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u00103\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a<\u00104\u001a\u0002H/\"\f\b\u0000\u0010/*\u000600j\u0002`1*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u00105\u001a<\u00106\u001a\u0002H/\"\f\b\u0000\u0010/*\u000600j\u0002`1*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u00105\u001a(\u00107\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u00108\u001a(\u00109\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u00108\u001a\n\u0010:\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010:\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0011\u0010;\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a(\u0010;\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u00108\u001a3\u0010=\u001a\b\u0012\u0004\u0012\u0002H?0>\"\u0004\b\u0000\u0010?*\u00020\u00022\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H?0\b0\u0004H\b\u001aL\u0010@\u001a\u0002H/\"\u0004\b\u0000\u0010?\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H?0\b0\u0004H\b¢\u0006\u0002\u0010B\u001aI\u0010C\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2'\u0010E\u001a#\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0+H\b¢\u0006\u0002\u0010G\u001a^\u0010H\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0IH\b¢\u0006\u0002\u0010J\u001aI\u0010K\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2'\u0010E\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002H?0+H\b¢\u0006\u0002\u0010G\u001a^\u0010L\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002H?0IH\b¢\u0006\u0002\u0010J\u001a!\u0010M\u001a\u00020N*\u00020\u00022\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020N0\u0004H\b\u001a6\u0010P\u001a\u00020N*\u00020\u00022'\u0010O\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020N0+H\b\u001a)\u0010Q\u001a\u00020\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001c2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a\u0019\u0010R\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001c¢\u0006\u0002\u0010(\u001a9\u0010S\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050>0\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001aS\u0010S\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0>0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aR\u0010T\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u001c\b\u0001\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050U0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b¢\u0006\u0002\u0010\u0018\u001al\u0010T\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u001c\b\u0002\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0U0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0019\u001a5\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0W\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0014\b\u0004\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001a!\u0010X\u001a\u00020\u001c*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010Y\u001a\u00020\u001c*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\n\u0010Z\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010Z\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0011\u0010[\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a(\u0010[\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u00108\u001a-\u0010\\\u001a\b\u0012\u0004\u0012\u0002H?0>\"\u0004\b\u0000\u0010?*\u00020\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\b\u001aB\u0010]\u001a\b\u0012\u0004\u0012\u0002H?0>\"\u0004\b\u0000\u0010?*\u00020\u00022'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0+H\b\u001aH\u0010^\u001a\b\u0012\u0004\u0012\u0002H?0>\"\b\b\u0000\u0010?*\u00020_*\u00020\u00022)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0+H\b\u001aa\u0010`\u001a\u0002H/\"\b\b\u0000\u0010?*\u00020_\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0+H\b¢\u0006\u0002\u0010a\u001a[\u0010b\u001a\u0002H/\"\u0004\b\u0000\u0010?\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0+H\b¢\u0006\u0002\u0010a\u001a3\u0010c\u001a\b\u0012\u0004\u0012\u0002H?0>\"\b\b\u0000\u0010?*\u00020_*\u00020\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0\u0004H\b\u001aL\u0010d\u001a\u0002H/\"\b\b\u0000\u0010?*\u00020_\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0\u0004H\b¢\u0006\u0002\u0010B\u001aF\u0010e\u001a\u0002H/\"\u0004\b\u0000\u0010?\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\b¢\u0006\u0002\u0010B\u001a\u0011\u0010f\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a8\u0010g\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010?*\b\u0012\u0004\u0012\u0002H?0h*\u00020\u00022\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\b¢\u0006\u0002\u00108\u001a-\u0010j\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010k\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050lj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`m¢\u0006\u0002\u0010n\u001a\u0011\u0010o\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a8\u0010p\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010?*\b\u0012\u0004\u0012\u0002H?0h*\u00020\u00022\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\b¢\u0006\u0002\u00108\u001a-\u0010q\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010k\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050lj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`m¢\u0006\u0002\u0010n\u001a\n\u0010r\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010r\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a0\u0010s\u001a\u0002Ht\"\b\b\u0000\u0010t*\u00020\u0002*\u0002Ht2\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020N0\u0004H\b¢\u0006\u0002\u0010u\u001a-\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a-\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f0\u0010*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a6\u0010w\u001a\u00020\u0005*\u00020\u00022'\u0010E\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050+H\b\u001aK\u0010x\u001a\u00020\u0005*\u00020\u00022<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050IH\b\u001a6\u0010y\u001a\u00020\u0005*\u00020\u00022'\u0010E\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00050+H\b\u001aK\u0010z\u001a\u00020\u0005*\u00020\u00022<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00050IH\b\u001a\n\u0010{\u001a\u00020\u0002*\u00020\u0002\u001a\r\u0010{\u001a\u00020\u001f*\u00020\u001fH\b\u001a\n\u0010|\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010|\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0011\u0010}\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a(\u0010}\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u00108\u001a\u0018\u0010~\u001a\u00020\u0002*\u00020\u00022\f\u0010\u001a\b\u0012\u0004\u0012\u00020\u001c0\b\u001a\u0013\u0010~\u001a\u00020\u0002*\u00020\u00022\u0007\u0010\u001a\u00030\u0001\u001a\u001b\u0010~\u001a\u00020\u001f*\u00020\u001f2\f\u0010\u001a\b\u0012\u0004\u0012\u00020\u001c0\bH\b\u001a\u0013\u0010~\u001a\u00020\u001f*\u00020\u001f2\u0007\u0010\u001a\u00030\u0001\u001a\"\u0010\u0001\u001a\u00020\u001c*\u00020\u00022\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001c0\u0004H\b\u001a$\u0010\u0001\u001a\u00030\u0001*\u00020\u00022\u0013\u0010i\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u00010\u0004H\b\u001a\u0013\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0013\u0010\u0001\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0013\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0013\u0010\u0001\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a\"\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a+\u0010\u0001\u001a\u0002H/\"\u0010\b\u0000\u0010/*\n\u0012\u0006\b\u0000\u0012\u00020\u00050A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/¢\u0006\u0003\u0010\u0001\u001a\u001d\u0010\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u0001*\u00020\u0002\u001a\u0011\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050>*\u00020\u0002\u001a\u0011\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050U*\u00020\u0002\u001a\u0012\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0002\u001a\u001f\u0010\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u0001*\u00020\u0002H\u0007\u001a\u0018\u0010\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00050\u00010\b*\u00020\u0002\u001a)\u0010\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100>*\u00020\u00022\u0007\u0010\u0001\u001a\u00020\u0002H\u0004\u001a]\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u000e0>\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0007\u0010\u0001\u001a\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b,\u0012\t\b-\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b,\u0012\t\b-\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u0002H\u000e0+H\b¨\u0006\u0001"}, d2 = {"all", "", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", FirebaseAnalytics.Param.DESTINATION, "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "count", "", "drop", "n", "", "dropLast", "dropLastWhile", "dropWhile", "elementAt", FirebaseAnalytics.Param.INDEX, "elementAtOrElse", "defaultValue", "elementAtOrNull", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;", "filter", "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "filterIndexedTo", "C", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function2;)Ljava/lang/Appendable;", "filterNot", "filterNotTo", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "filterTo", "find", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "findLast", "first", "firstOrNull", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "flatMap", "", "R", "flatMapTo", "", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fold", "initial", "operation", "acc", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "action", "forEachIndexed", "getOrElse", "getOrNull", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOfFirst", "indexOfLast", "last", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "", "mapIndexedNotNullTo", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "max", "maxBy", "", Globalization.SELECTOR, "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", NetworkManager.TYPE_NONE, "onEach", "S", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "partition", "reduce", "reduceIndexed", "reduceRight", "reduceRightIndexed", "reversed", "single", "singleOrNull", "slice", "indices", "Lkotlin/ranges/IntRange;", "sumBy", "sumByDouble", "", "take", "takeLast", "takeLastWhile", "takeWhile", "toCollection", "(Ljava/lang/CharSequence;Ljava/util/Collection;)Ljava/util/Collection;", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toSet", "", "toSortedSet", "Ljava/util/SortedSet;", "Lkotlin/collections/SortedSet;", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "kotlin-stdlib"}, k = 5, mv = {1, 1, 8}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: _Strings.kt */
class StringsKt___StringsKt extends StringsKt__StringsKt {
    @InlineOnly
    private static final char elementAt(@NotNull CharSequence charSequence, int i) {
        return charSequence.charAt(i);
    }

    @InlineOnly
    private static final char elementAtOrElse(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt.getLastIndex(charSequence)) ? function1.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @InlineOnly
    private static final Character elementAtOrNull(@NotNull CharSequence charSequence, int i) {
        return StringsKt.getOrNull(charSequence, i);
    }

    public static final char first(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (!(charSequence.length() == 0)) {
            return charSequence.charAt(0);
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static final char first(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return charAt;
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (charSequence.length() == 0) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(0));
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @InlineOnly
    private static final char getOrElse(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt.getLastIndex(charSequence)) ? function1.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @Nullable
    public static final Character getOrNull(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (i < 0 || i > StringsKt.getLastIndex(charSequence)) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(i));
    }

    public static final int indexOfFirst(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int indexOfLast(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        IntProgression reversed = RangesKt.reversed(StringsKt.getIndices(charSequence));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step > 0) {
            if (first > last) {
                return -1;
            }
        } else if (first < last) {
            return -1;
        }
        while (!function1.invoke(Character.valueOf(charSequence.charAt(first))).booleanValue()) {
            if (first == last) {
                return -1;
            }
            first += step;
        }
        return first;
    }

    public static final char last(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (!(charSequence.length() == 0)) {
            return charSequence.charAt(StringsKt.getLastIndex(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static final char last(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        IntProgression reversed = RangesKt.reversed(StringsKt.getIndices(charSequence));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step <= 0 ? first >= last : first <= last) {
            while (true) {
                char charAt = charSequence.charAt(first);
                if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                    if (first == last) {
                        break;
                    }
                    first += step;
                } else {
                    return charAt;
                }
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (charSequence.length() == 0) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(charSequence.length() - 1));
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        IntProgression reversed = RangesKt.reversed(StringsKt.getIndices(charSequence));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step > 0) {
            if (first > last) {
                return null;
            }
        } else if (first < last) {
            return null;
        }
        while (true) {
            char charAt = charSequence.charAt(first);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
            if (first == last) {
                return null;
            }
            first += step;
        }
    }

    public static final char single(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        switch (charSequence.length()) {
            case 0:
                throw new NoSuchElementException("Char sequence is empty.");
            case 1:
                return charSequence.charAt(0);
            default:
                throw new IllegalArgumentException("Char sequence has more than one element.");
        }
    }

    public static final char single(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Character ch = null;
        boolean z = false;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                if (!z) {
                    ch = Character.valueOf(charAt);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Char sequence contains more than one matching element.");
                }
            }
        }
        if (!z) {
            throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
        } else if (ch != null) {
            return ch.charValue();
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
        }
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (charSequence.length() == 1) {
            return Character.valueOf(charSequence.charAt(0));
        }
        return null;
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        Character ch = null;
        boolean z = false;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                if (z) {
                    return null;
                }
                ch = Character.valueOf(charAt);
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        return ch;
    }

    @NotNull
    public static final CharSequence drop(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (i >= 0) {
            return charSequence.subSequence(RangesKt.coerceAtMost(i, charSequence.length()), charSequence.length());
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String drop(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        if (i >= 0) {
            String substring = str.substring(RangesKt.coerceAtMost(i, str.length()));
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence dropLast(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (i >= 0) {
            return StringsKt.take(charSequence, RangesKt.coerceAtLeast(charSequence.length() - i, 0));
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String dropLast(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        if (i >= 0) {
            return StringsKt.take(str, RangesKt.coerceAtLeast(str.length() - i, 0));
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence dropLastWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        IntProgression reversed = RangesKt.reversed(StringsKt.getIndices(charSequence));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step <= 0 ? first >= last : first <= last) {
            while (function1.invoke(Character.valueOf(charSequence.charAt(first))).booleanValue()) {
                if (first != last) {
                    first += step;
                }
            }
            return charSequence.subSequence(0, first + 1);
        }
        return "";
    }

    @NotNull
    public static final String dropLastWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        IntProgression reversed = RangesKt.reversed(StringsKt.getIndices(str));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step > 0) {
            if (first > last) {
                return "";
            }
        } else if (first < last) {
            return "";
        }
        while (function1.invoke(Character.valueOf(str.charAt(first))).booleanValue()) {
            if (first == last) {
                return "";
            }
            first += step;
        }
        String substring = str.substring(0, first + 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static final CharSequence dropWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String dropWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
                String substring = str.substring(i);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence filter(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        StringBuilder sb = new StringBuilder();
        int length = charSequence.length() - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                char charAt = charSequence.charAt(i);
                if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                    sb.append(charAt);
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return sb;
    }

    @NotNull
    public static final String filter(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        String str2 = str;
        StringBuilder sb = new StringBuilder();
        int length = str2.length() - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                char charAt = str2.charAt(i);
                if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                    sb.append(charAt);
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    @NotNull
    public static final CharSequence filterIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return sb;
    }

    @NotNull
    public static final String filterIndexed(@NotNull String str, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        String str2 = str;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < str2.length()) {
            char charAt = str2.charAt(i);
            int i3 = i2 + 1;
            if (function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterIndexedTo(StringBu…(), predicate).toString()");
        return sb2;
    }

    @NotNull
    public static final CharSequence filterNot(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        return sb;
    }

    @NotNull
    public static final String filterNot(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        String str2 = str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str2.length(); i++) {
            char charAt = str2.charAt(i);
            if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "filterNotTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    @NotNull
    public static final <C extends Appendable> C filterNotTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                c.append(charAt);
            }
        }
        return c;
    }

    @NotNull
    public static final <C extends Appendable> C filterTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length() - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                char charAt = charSequence.charAt(i);
                if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                    c.append(charAt);
                }
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        return c;
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(intRange, "indices");
        if (intRange.isEmpty()) {
            return "";
        }
        return StringsKt.subSequence(charSequence, intRange);
    }

    @NotNull
    public static final String slice(@NotNull String str, @NotNull IntRange intRange) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(intRange, "indices");
        if (intRange.isEmpty()) {
            return "";
        }
        return StringsKt.substring(str, intRange);
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence charSequence, @NotNull Iterable<Integer> iterable) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(iterable, "indices");
        int collectionSizeOrDefault = CollectionsKt.collectionSizeOrDefault(iterable, 10);
        if (collectionSizeOrDefault == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(collectionSizeOrDefault);
        for (Integer num : iterable) {
            sb.append(charSequence.charAt(num.intValue()));
        }
        return sb;
    }

    @InlineOnly
    private static final String slice(@NotNull String str, Iterable<Integer> iterable) {
        if (str != null) {
            return StringsKt.slice((CharSequence) str, iterable).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence take(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (i >= 0) {
            return charSequence.subSequence(0, RangesKt.coerceAtMost(i, charSequence.length()));
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String take(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        if (i >= 0) {
            String substring = str.substring(0, RangesKt.coerceAtMost(i, str.length()));
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence takeLast(@NotNull CharSequence charSequence, int i) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (i >= 0) {
            int length = charSequence.length();
            return charSequence.subSequence(length - RangesKt.coerceAtMost(i, length), length);
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String takeLast(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        if (i >= 0) {
            int length = str.length();
            String substring = str.substring(length - RangesKt.coerceAtMost(i, length));
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence takeLastWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (lastIndex >= 0) {
            while (function1.invoke(Character.valueOf(charSequence.charAt(lastIndex))).booleanValue()) {
                if (lastIndex != 0) {
                    lastIndex--;
                }
            }
            return charSequence.subSequence(lastIndex + 1, charSequence.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String takeLastWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int lastIndex = StringsKt.getLastIndex(str);
        if (lastIndex >= 0) {
            while (function1.invoke(Character.valueOf(str.charAt(lastIndex))).booleanValue()) {
                if (lastIndex != 0) {
                    lastIndex--;
                }
            }
            String substring = str.substring(lastIndex + 1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        return str;
    }

    @NotNull
    public static final CharSequence takeWhile(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = charSequence.length() - 1;
        if (length >= 0) {
            int i = 0;
            while (function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                if (i != length) {
                    i++;
                }
            }
            return charSequence.subSequence(0, i);
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String takeWhile(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int length = str.length() - 1;
        if (length >= 0) {
            int i = 0;
            while (function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
                if (i != length) {
                    i++;
                }
            }
            String substring = str.substring(0, i);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        return str;
    }

    @NotNull
    public static final CharSequence reversed(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        StringBuilder reverse = new StringBuilder(charSequence).reverse();
        Intrinsics.checkExpressionValueIsNotNull(reverse, "StringBuilder(this).reverse()");
        return reverse;
    }

    @InlineOnly
    private static final String reversed(@NotNull String str) {
        if (str != null) {
            return StringsKt.reversed((CharSequence) str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.util.LinkedHashMap */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) function1.invoke(Character.valueOf(charSequence.charAt(i)));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.util.LinkedHashMap */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K> Map<K, Character> associateBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(function1.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.util.LinkedHashMap */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(function1.invoke(Character.valueOf(charAt)), function12.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: M extends java.util.Map<? super K, ? super java.lang.Character> */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(m, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(function1.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return m;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: M extends java.util.Map<? super K, ? super V> */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(m, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(function1.invoke(Character.valueOf(charAt)), function12.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: M extends java.util.Map<? super K, ? super V> */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(m, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) function1.invoke(Character.valueOf(charSequence.charAt(i)));
            m.put(pair.getFirst(), pair.getSecond());
        }
        return m;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C toCollection(@NotNull CharSequence charSequence, @NotNull C c) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        for (int i = 0; i < charSequence.length(); i++) {
            c.add(Character.valueOf(charSequence.charAt(i)));
        }
        return c;
    }

    @NotNull
    public static final HashSet<Character> toHashSet(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        return (HashSet) StringsKt.toCollection(charSequence, new HashSet(MapsKt.mapCapacity(charSequence.length())));
    }

    @NotNull
    public static final List<Character> toList(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        switch (charSequence.length()) {
            case 0:
                return CollectionsKt.emptyList();
            case 1:
                return CollectionsKt.listOf(Character.valueOf(charSequence.charAt(0)));
            default:
                return StringsKt.toMutableList(charSequence);
        }
    }

    @NotNull
    public static final List<Character> toMutableList(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        return (List) StringsKt.toCollection(charSequence, new ArrayList(charSequence.length()));
    }

    @NotNull
    public static final Set<Character> toSet(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        switch (charSequence.length()) {
            case 0:
                return SetsKt.emptySet();
            case 1:
                return SetsKt.setOf(Character.valueOf(charSequence.charAt(0)));
            default:
                return (Set) StringsKt.toCollection(charSequence, new LinkedHashSet(MapsKt.mapCapacity(charSequence.length())));
        }
    }

    @NotNull
    public static final SortedSet<Character> toSortedSet(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        return (SortedSet) StringsKt.toCollection(charSequence, new TreeSet());
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt.addAll(arrayList, (Iterable) function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt.addAll(c, (Iterable) function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return c;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: java.util.LinkedHashMap */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K> Map<K, List<Character>> groupBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: java.util.LinkedHashMap */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            Object obj = linkedHashMap.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(invoke, obj);
            }
            ((List) obj).add(function12.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: M extends java.util.Map<? super K, java.util.List<java.lang.Character>> */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(m, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            Object obj = m.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                m.put(invoke, obj);
            }
            ((List) obj).add(Character.valueOf(charAt));
        }
        return m;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: M extends java.util.Map<? super K, java.util.List<V>> */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(m, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        Intrinsics.checkParameterIsNotNull(function12, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            Object obj = m.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                m.put(invoke, obj);
            }
            ((List) obj).add(function12.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K> Grouping<Character, K> groupingBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "keySelector");
        return new StringsKt___StringsKt$groupingBy$1(charSequence, function1);
    }

    @NotNull
    public static final <R> List<R> map(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        ArrayList arrayList = new ArrayList(charSequence.length());
        for (int i = 0; i < charSequence.length(); i++) {
            arrayList.add(function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        ArrayList arrayList = new ArrayList(charSequence.length());
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            arrayList.add(function2.invoke(valueOf, Character.valueOf(charAt)));
        }
        return arrayList;
    }

    @NotNull
    public static final <R> List<R> mapIndexedNotNull(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            Object invoke = function2.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            c.add(function2.invoke(valueOf, Character.valueOf(charAt)));
        }
        return c;
    }

    @NotNull
    public static final <R> List<R> mapNotNull(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            Object invoke = function1.invoke(Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
        }
        return arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            c.add(function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return c;
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> withIndex(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        return new IndexingIterable(new StringsKt___StringsKt$withIndex$1(charSequence));
    }

    public static final boolean all(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        return !(charSequence.length() == 0);
    }

    public static final boolean any(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    private static final int count(@NotNull CharSequence charSequence) {
        return charSequence.length();
    }

    public static final int count(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i2))).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public static final <R> R fold(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super R, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        for (int i = 0; i < charSequence.length(); i++) {
            r = (R) function2.invoke(r, Character.valueOf(charSequence.charAt(i)));
        }
        return r;
    }

    public static final <R> R foldIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            r = (R) function3.invoke(valueOf, r, Character.valueOf(charAt));
        }
        return r;
    }

    public static final <R> R foldRight(@NotNull CharSequence charSequence, R r, @NotNull Function2<? super Character, ? super R, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        for (int lastIndex = StringsKt.getLastIndex(charSequence); lastIndex >= 0; lastIndex--) {
            r = (R) function2.invoke(Character.valueOf(charSequence.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final <R> R foldRightIndexed(@NotNull CharSequence charSequence, R r, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        for (int lastIndex = StringsKt.getLastIndex(charSequence); lastIndex >= 0; lastIndex--) {
            r = (R) function3.invoke(Integer.valueOf(lastIndex), Character.valueOf(charSequence.charAt(lastIndex)), r);
        }
        return r;
    }

    public static final void forEach(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        for (int i = 0; i < charSequence.length(); i++) {
            function1.invoke(Character.valueOf(charSequence.charAt(i)));
        }
    }

    public static final void forEachIndexed(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "action");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            function2.invoke(valueOf, Character.valueOf(charAt));
        }
    }

    @Nullable
    public static final Character max(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (charAt < charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character maxBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, Globalization.SELECTOR);
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        Comparable comparable = (Comparable) function1.invoke(Character.valueOf(charAt));
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                Comparable comparable2 = (Comparable) function1.invoke(Character.valueOf(charAt2));
                if (comparable.compareTo(comparable2) < 0) {
                    charAt = charAt2;
                    comparable = comparable2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character maxWith(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) < 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character min(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (charAt > charAt2) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character minBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, Globalization.SELECTOR);
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        Comparable comparable = (Comparable) function1.invoke(Character.valueOf(charAt));
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                Comparable comparable2 = (Comparable) function1.invoke(Character.valueOf(charAt2));
                if (comparable.compareTo(comparable2) > 0) {
                    charAt = charAt2;
                    comparable = comparable2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character minWith(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (1 <= lastIndex) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) > 0) {
                    charAt = charAt2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final boolean none(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        return charSequence.length() == 0;
    }

    public static final boolean none(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S extends CharSequence> S onEach(@NotNull S s, @NotNull Function1<? super Character, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(s, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        for (int i = 0; i < s.length(); i++) {
            function1.invoke(Character.valueOf(s.charAt(i)));
        }
        return s;
    }

    public static final char reduce(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        int i = 1;
        if (!(charSequence.length() == 0)) {
            char charAt = charSequence.charAt(0);
            int lastIndex = StringsKt.getLastIndex(charSequence);
            if (1 <= lastIndex) {
                while (true) {
                    charAt = function2.invoke(Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i))).charValue();
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char reduceIndexed(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        int i = 1;
        if (!(charSequence.length() == 0)) {
            char charAt = charSequence.charAt(0);
            int lastIndex = StringsKt.getLastIndex(charSequence);
            if (1 <= lastIndex) {
                while (true) {
                    charAt = function3.invoke(Integer.valueOf(i), Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i))).charValue();
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char reduceRight(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (lastIndex >= 0) {
            char charAt = charSequence.charAt(lastIndex);
            for (int i = lastIndex - 1; i >= 0; i--) {
                charAt = function2.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charAt)).charValue();
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char reduceRightIndexed(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> function3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function3, "operation");
        int lastIndex = StringsKt.getLastIndex(charSequence);
        if (lastIndex >= 0) {
            char charAt = charSequence.charAt(lastIndex);
            for (int i = lastIndex - 1; i >= 0; i--) {
                charAt = function3.invoke(Integer.valueOf(i), Character.valueOf(charSequence.charAt(i)), Character.valueOf(charAt)).charValue();
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final int sumBy(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Integer> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, Globalization.SELECTOR);
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i += function1.invoke(Character.valueOf(charSequence.charAt(i2))).intValue();
        }
        return i;
    }

    public static final double sumByDouble(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Double> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, Globalization.SELECTOR);
        double d = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            d += function1.invoke(Character.valueOf(charSequence.charAt(i))).doubleValue();
        }
        return d;
    }

    @NotNull
    public static final Pair<CharSequence, CharSequence> partition(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb, sb2);
    }

    @NotNull
    public static final Pair<String, String> partition(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb.toString(), sb2.toString());
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull Function2<? super Character, ? super Character, ? extends V> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        int min = Math.min(charSequence.length(), charSequence2.length());
        ArrayList arrayList = new ArrayList(min);
        int i = min - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                arrayList.add(function2.invoke(Character.valueOf(charSequence.charAt(i2)), Character.valueOf(charSequence2.charAt(i2))));
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }

    @NotNull
    public static final Iterable<Character> asIterable(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (charSequence instanceof String) {
            if (charSequence.length() == 0) {
                return CollectionsKt.emptyList();
            }
        }
        return new StringsKt___StringsKt$asIterable$$inlined$Iterable$1(charSequence);
    }

    @NotNull
    public static final Sequence<Character> asSequence(@NotNull CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        if (charSequence instanceof String) {
            if (charSequence.length() == 0) {
                return SequencesKt.emptySequence();
            }
        }
        return new StringsKt___StringsKt$asSequence$$inlined$Sequence$1(charSequence);
    }

    @InlineOnly
    private static final Character find(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Character findLast(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        IntProgression reversed = RangesKt.reversed(StringsKt.getIndices(charSequence));
        int first = reversed.getFirst();
        int last = reversed.getLast();
        int step = reversed.getStep();
        if (step <= 0 ? first >= last : first <= last) {
            while (true) {
                char charAt = charSequence.charAt(first);
                if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                    if (first == last) {
                        break;
                    }
                    first += step;
                } else {
                    return Character.valueOf(charAt);
                }
            }
        }
        return null;
    }

    @NotNull
    public static final <C extends Appendable> C filterIndexedTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function2, "predicate");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                c.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return c;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function2, "transform");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            Object invoke = function2.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                c.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return c;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapNotNullTo(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(c, FirebaseAnalytics.Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Object invoke = function1.invoke(Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                c.add(invoke);
            }
        }
        return c;
    }

    @NotNull
    public static final List<Pair<Character, Character>> zip(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.checkParameterIsNotNull(charSequence, "$receiver");
        Intrinsics.checkParameterIsNotNull(charSequence2, "other");
        int min = Math.min(charSequence.length(), charSequence2.length());
        ArrayList arrayList = new ArrayList(min);
        int i = min - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                arrayList.add(TuplesKt.to(Character.valueOf(charSequence.charAt(i2)), Character.valueOf(charSequence2.charAt(i2))));
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }
}
