# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
# Specifies the compression level of the code
-optimizationpasses 5
# Package names are case-insensitive
-dontusemixedcaseclassnames
 
# Don't ignore non-public library classes
-dontskipnonpubliclibraryclasses
 
 #Input class files are not optimized
-dontoptimize
 
 # The divverify
-dontpreverify
 
 # Whether to log when confused
-verbose
 
 # The algorithm used in confusion
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
 
# Protect the annotation
-keepattributes *Annotation*
 
# Keep those classes from being confused
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

# Ignore the warning
-ignorewarning
 
##Record the generated log data and output it in the project root directory when gradle build##
# The internal structure of all classes in the apk package
-dump proguard/class_files.txt
# Unconfused classes and members
-printseeds proguard/seeds.txt
# Lists the code deleted from the apk
-printusage proguard/unused.txt
# Before and after confusion mapping
-printmapping proguard/mapping.txt
########Record the generated log data and output it in the project root directory when gradle build-end######
 
 
####Confuse the code that protects part of your project with the reference to the third-party jar library-end####
 
 
 
# Keep the native method from being confused
-keepclasseswithmembernames class * {
    native <methods>;
}
 
# Keep custom control classes from being confused
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
 
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
 
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
 
# Keep Parcelable from being confused
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
 
# Keep Serializable from being confused
-keepnames class * implements java.io.Serializable
 
# Keep Serializable unobfuscated and the enum class unobfuscated
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
 
# Keep enumerated enum classes from being confused
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}
 
-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}
 
# Do not confuse resource classes
-keepclassmembers class **.R$* {
    public static <fields>;
}
 
 
#mob
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*
 
#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
 
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
 
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
 
######The other modules referenced can be configured directly in the obfuscation file of the app
 
# If a tool such as Gson is used, the JavaBean class that it parses is not confused with the entity class.
-keep class com.matrix.app.entity.json.** { *; }
-keep class com.matrix.appsdk.network.model.** { *; }
 


# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information,uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
