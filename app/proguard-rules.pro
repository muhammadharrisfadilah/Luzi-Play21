# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Penyaringan kelas dan metode yang tidak digunakan
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}

# Membuang debug logs dari kode
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

# Menghilangkan informasi debug yang tidak perlu
-dontnote
-dontwarn

# Mengoptimalkan penggunaan Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }

# Tetapkan kelas untuk antarmuka JavaScript dalam WebView
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# Menyimpan informasi nomor baris untuk debug stack traces
-keepattributes SourceFile,LineNumberTable

# Menyembunyikan nama file sumber asli
-renamesourcefileattribute SourceFile
