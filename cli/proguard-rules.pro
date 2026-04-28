# -dontobfuscate
-ignorewarnings
-keep class dev.keiji.license.maven.MainKt {
    public static void main(java.lang.String[]);
}
-keep class org.jetbrains.kotlinx.cli.** { *; }
-keep class kotlinx.serialization.** { *; }
-keep class dev.keiji.license.maven.entity.** { *; }

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn kotlin.reflect.**
