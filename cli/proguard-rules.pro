-dontobfuscate
-ignorewarnings
-keep class dev.keiji.license.maven.MainKt {
    public static void main(java.lang.String[]);
}
-keep class org.jetbrains.kotlinx.cli.** { *; }
-keep class kotlinx.serialization.** { *; }
-keep class okhttp3.** { *; }
-keep class kotlin.reflect.** { *; }
-keep class okio.** { *; }
-keep @kotlinx.serialization.Serializable class * { *; }
-keep class dev.keiji.license.maven.entity.** { *; }
