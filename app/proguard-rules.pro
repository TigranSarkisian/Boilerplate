-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-useuniqueclassmembernames
-keepattributes SourceFile,LineNumberTable
-allowaccessmodification

-dontwarn android.support.**
-dontwarn com.futurice.project.models.pojo.**
-dontwarn sun.misc.Unsafe
-dontwarn com.google.gson.**
-dontwarn com.google.**
-dontwarn com.facebook.**
-dontwarn com.facebook.**
-dontwarn com.squareup.**
-dontwarn com.nostra13.**
-dontwarn com.amulyakhare.**
-dontwarn de.hdodenhof.**
-dontwarn pl.droidsonroids.**
-dontwarn com.amulyakhar.**
-dontwarn com.theartofdev.**
-dontwarn com.github.paolorotolo.**
-dontwarn com.github.bumptech.glide.**
-dontwarn com.yalantis.**
-dontwarn com.viewpagerindicator.**
-dontwarn com.directions.**
-dontwarn com.google.guava.**
-dontwarn com.crittercism.**
-dontwarn org.joda.time.**
-dontwarn okio.**
-dontwarn net.hockeyapp.android.**
-dontwarn android.webkit.**
-dontwarn java.lang.ClassValue
-dontwarn com.google.j2objc.annotations.Weak
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepclassmembers class ** {
    @com.google.common.eventbus.Subscribe public *;
}

-keep class com.futurice.project.models.pojo.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.** { *; }
-keep class com.squareup.** { *; }
-keep class com.facebook.** { *; }
-keep class com.nostra13.** { *; }
-keep class com.amulyakhare.** { *; }
-keep class de.hdodenhof.** { *; }
-keep class pl.droidsonroids.** { *; }
-keep class com.amulyakhar.** { *; }
-keep class com.theartofdev.** { *; }
-keep class com.github.paolorotolo.** { *; }
-keep class com.github.bumptech.glide.** { *; }
-keep class com.yalantis.** { *; }
-keep class com.viewpagerindicator.** { *; }
-keep class com.directions.** { *; }
-keep class com.google.guava.** { *; }
-keep class android.support.** { *; }
-keep class android.support.v4.** { *; }
-keep class android.support.v7.** { *; }

-keep interface android.support.v7.** { *; }
-keep interface android.support.v4.** { *; }

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v4.app.DialogFragment
-keep public class * extends com.actionbarsherlock.app.SherlockListFragment
-keep public class * extends com.actionbarsherlock.app.SherlockFragment
-keep public class * extends com.actionbarsherlock.app.SherlockFragmentActivity
-keep public class * extends android.app.Fragment
-keep public class com.android.vending.licensing.ILicensingService

-keep public class com.crittercism.**
-keepclassmembers public class com.crittercism.* { *; }
-keepattributes SourceFile, LineNumberTable

-keep class com.squareup.picasso.** { *; }
-keepclasseswithmembers class * {
    @com.squareup.picasso.** *;
}
-keepclassmembers class * {
    @com.squareup.picasso.** *;
}

-keepclasseswithmembernames class * {
 native <methods>;
}

-keep public class * extends android.view.View {
 public <init>(android.content.Context);
 public <init>(android.content.Context, android.util.AttributeSet);
 public <init>(android.content.Context, android.util.AttributeSet, int);
 public void set*(...);
}

-keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
 public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
 public void *(android.view.View);
}

-keepclassmembers enum * {
 public static **[] values();
 public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
 public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
 public static <fields>;
}