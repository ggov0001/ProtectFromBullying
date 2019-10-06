# Security Policy

here is the scan result using Androbugs:
*************************************************************************
**   AndroBugs Framework - Android App Security Vulnerability Scanner  **
**                            version: 1.0.0                           **
**     author: Yu-Cheng Lin (@AndroBugs, http://www.AndroBugs.com)     **
**               contact: androbugs.framework@gmail.com                **
*************************************************************************
Platform: Android
Package Name: com.example.protectfrombullying
Package Version Name: 1.0
Package Version Code: 1
Min Sdk: 15
Target Sdk: 28
MD5   : e9d7b86e648b5d0161e00dbc5d40d1fd
SHA1  : 6e3a5a334c5fed88d636a465d352580badb3649f
SHA256: 0dca0251b14867d68f199be638d38b6f898f932f891db40ac3e20d8e37ea3c21
SHA512: eb93cb9d046fe8644b00c4f8f5b04c8b9231c3a5861a3e582eef0aa98bb747818494066d4dc5dcade2a7d1fae123107d087e0c18c50b2a0120b5410a91e85a3e
Analyze Signature: 8ec121c9df27382412ffa6289e0110f12b7fb0900c23524a71062ff04000867238e36e1ea5bf6fb7ee8ef435edac05a12fa863244f1abe9c1163aa8fc4fac85c
------------------------------------------------------------------------------------------------
[Critical] <Implicit_Intent> Implicit Service Checking:
           To ensure your app is secure, always use an explicit intent when starting a Service and DO NOT declare intent filters for your
           services. Using an implicit intent to start a service is a security hazard because you cannot be certain what service will
           respond to the intent, and the user cannot see which service starts.
           Reference: http://developer.android.com/guide/components/intents-filters.html#Types
               => com.google.firebase.iid.FirebaseInstanceIdService
[Critical] <SSL_Security> SSL Connection Checking:
           URLs that are NOT under SSL (Total:2):
               http://localhost
                   => Lcom/google/android/gms/internal/firebase_auth/zzx;-><init>(Ljava/lang/String;)V
                   => Lcom/google/android/gms/internal/firebase_auth/zzbg;-><init>(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;
                    Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
               http://play.google.com/store/apps/category/GAME
                   => Lcom/google/android/gms/common/internal/GmsIntents;->createPlayStoreGamesIntent(Landroid/content/Context;)Landroid/con
                    tent/Intent;
[Critical] <SSL_Security> SSL Certificate Verification Checking:
           This app DOES NOT check the validation of SSL Certificate. It allows self-signed, expired or mismatch CN certificates for SSL
           connection.
           This is a critical vulnerability and allows attackers to do MITM attacks without your knowledge.
           If you are transmitting users' username or password, these sensitive information may be leaking.
           Reference:
           (1)OWASP Mobile Top 10 doc: https://www.owasp.org/index.php/Mobile_Top_10_2014-M3
           (2)Android Security book: http://goo.gl/BFb65r
           (3)https://www.securecoding.cert.org/confluence/pages/viewpage.action?pageId=134807561
           This vulnerability is much more severe than Apple's "goto fail" vulnerability: http://goo.gl/eFlovw
           Please do not try to create a "X509Certificate" and override "checkClientTrusted", "checkServerTrusted", and "getAcceptedIssuers"
           functions with blank implementation.
           We strongly suggest you use the existing API instead of creating your own X509Certificate class.
           Please modify or remove these vulnerable code:
               [Confirm Vulnerable]
               => Lcom/google/android/gms/common/net/zza;
                     -> used by: Lcom/google/android/gms/common/net/SSLCertificateSocketFactory;-><clinit>()V
[Warning]  External Storage Accessing:
           External storage access found (Remember DO NOT write important files to external storages):
               => Lcom/github/mikephil/charting/utils/FileUtils;->loadEntriesFromFile(Ljava/lang/String;)Ljava/util/List; (0x0) --->
                    Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;
               => Lcom/github/mikephil/charting/utils/FileUtils;->saveToSdCard(Ljava/util/List; Ljava/lang/String;)V (0x0) --->
                    Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;
               => Lcom/github/mikephil/charting/charts/Chart;->saveToGallery(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;
                    Landroid/graphics/Bitmap$CompressFormat; I)Z (0x18) --->
                    Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;
               => Lcom/github/mikephil/charting/charts/Chart;->saveToPath(Ljava/lang/String; Ljava/lang/String;)Z (0x16) --->
                    Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;
               => Lcom/example/protectfrombullying/TreeHoleActivity;->onCreate(Landroid/os/Bundle;)V (0xba) --->
                    Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;
[Warning]  AndroidManifest Exported Components Checking:
           Found "exported" components(except for Launcher) for receiving outside applications' actions (AndroidManifest.xml).
           These components can be initilized by other apps. You should add or modify the attribute to [exported="false"] if you don't want
           to.
           You can also protect it with a customized permission with "signature" or higher protectionLevel and specify in
           "android:permission" attribute.
                  service => com.google.firebase.iid.FirebaseInstanceIdService
[Warning] <Sensitive_Information> Getting ANDROID_ID:
           This app has code getting the 64-bit number "Settings.Secure.ANDROID_ID".
           ANDROID_ID seems a good choice for a unique device identifier. There are downsides: First, it is not 100% reliable on releases of
           Android prior to 2.2 (Froyo).
           Also, there has been at least one widely-observed bug in a popular handset from a major manufacturer, where every instance has
           the same ANDROID_ID.
           If you want to get an unique id for the device, we suggest you use "Installation" framework in the following article.
           Please check the reference: http://android-developers.blogspot.tw/2011/03/identifying-app-installations.html
               => Lcom/google/android/gms/measurement/internal/zzfa;->zzd(Lcom/google/android/gms/measurement/internal/zzad;
                    Lcom/google/android/gms/measurement/internal/zzh;)V (0x81c) --->
                    Landroid/provider/Settings$Secure;->getString(Landroid/content/ContentResolver; Ljava/lang/String;)Ljava/lang/String;
[Notice]  AndroidManifest Adb Backup Checking:
           ADB Backup is ENABLED for this app (default: ENABLED). ADB Backup is a good tool for backing up all of your files. If it's open
           for this app, people who have your phone can copy all of the sensitive data for this app in your phone (Prerequisite: 1.Unlock
           phone's screen 2.Open the developer mode). The sensitive data may include lifetime access token, username or password, etc.
           Security case related to ADB Backup:
           1.http://www.securityfocus.com/archive/1/530288/30/0/threaded
           2.http://blog.c22.cc/advisories/cve-2013-5112-evernote-android-insecure-storage-of-pin-data-bypass-of-pin-protection/
           3.http://nelenkov.blogspot.co.uk/2012/06/unpacking-android-backups.html
           Reference: http://developer.android.com/guide/topics/manifest/application-element.html#allowbackup
[Notice] <Command> Executing "root" or System Privilege Checking:
           The app may has the code checking for "root" permission, mounting filesystem operations or monitoring system:
               Lcom/google/android/gms/common/util/CrashUtils;->zza(Landroid/content/Context; Ljava/lang/String; Ljava/lang/String;
                    I)Ljava/lang/String;  => '/system/bin/logcat'
[Notice] <Database><#CVE-2011-3901#> Android SQLite Databases Vulnerability Checking:
           This app is using Android SQLite databases but it's "NOT" suffering from SQLite Journal Information Disclosure Vulnerability.
[Notice]  File Unsafe Delete Checking:
           Everything you delete may be recovered by any user or attacker, especially rooted devices.
           Please make sure do not use "file.delete()" to delete essential files.
           Check this video: https://www.youtube.com/watch?v=tGw1fxUD-uY
               => Landroid/arch/persistence/db/SupportSQLiteOpenHelper$Callback;->deleteDatabaseFile(Ljava/lang/String;)V (0x7a) --->
                    Ljava/io/File;->delete()Z
               => Lcom/google/android/gms/measurement/internal/zzam;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase; (0x40)
                    ---> Ljava/io/File;->delete()Z
               => Lcom/google/android/gms/measurement/internal/zzt;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase; (0x70)
                    ---> Ljava/io/File;->delete()Z
               => Lcom/google/firebase/iid/zzs;->zza(Landroid/content/Context;)V (0x34) ---> Ljava/io/File;->delete()Z
               => Lcom/google/android/gms/common/data/BitmapTeleporter;->zzcj()Ljava/io/FileOutputStream; (0x32) --->
                    Ljava/io/File;->delete()Z
[Notice] <Debug><Hacker> Codes for Checking Android Debug Mode:
           Found codes for checking "ApplicationInfo.FLAG_DEBUGGABLE" in AndroidManifest.xml:
               => Lcom/google/android/gms/common/GoogleSignatureVerifier;->zza
                    (Landroid/content/pm/PackageInfo;)Lcom/google/android/gms/common/zzg;
[Notice] <Hacker> APK Installing Source Checking:
           This app has code checking APK installer sources(e.g. from Google Play, from Amazon, etc.). It might be used to check for whether
           the app is hacked by the attackers.
               => Lcom/google/android/gms/measurement/internal/zzfa;->zza(Landroid/content/Context; Ljava/lang/String; Ljava/lang/String; Z
                    Z Z J Ljava/lang/String;)Lcom/google/android/gms/measurement/internal/zzh; (0x42) --->
                    Landroid/content/pm/PackageManager;->getInstallerPackageName(Ljava/lang/String;)Ljava/lang/String;
               => Lcom/google/android/gms/measurement/internal/zzaj;->zzgu()V (0x5a) --->
                    Landroid/content/pm/PackageManager;->getInstallerPackageName(Ljava/lang/String;)Ljava/lang/String;
[Notice] <Signature><Hacker> Getting Signature Code Checking:
           This app has code checking the package signature in the code. It might be used to check for whether the app is hacked by the
           attackers.
               => Lcom/google/android/gms/common/GooglePlayServicesUtilLight;->zza(Landroid/content/Context; Z I)I (0x50) --->
                    Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String; I)Landroid/content/pm/PackageInfo;
               => Lcom/google/android/gms/common/util/UidVerifier;->isGooglePlayServicesUid(Landroid/content/Context; I)Z (0x24) --->
                    Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String; I)Landroid/content/pm/PackageInfo;
[Info] <Command> Runtime Command Checking:
           This app is not using critical function 'Runtime.getRuntime().exec("...")'.
[Info] <Database> SQLiteDatabase Transaction Deprecated Checking:
           Ignore checking "SQLiteDatabase:beginTransactionNonExclusive" because your set minSdk >= 11.
[Info] <Database> Android SQLite Databases Encryption (SQLite Encryption Extension (SEE)):
           This app is "NOT" using SQLite Encryption Extension (SEE) on Android (http://www.sqlite.org/android) to encrypt or decrpyt
           databases.
[Info] <Database> Android SQLite Databases Encryption (SQLCipher):
           This app is "NOT" using SQLCipher(http://sqlcipher.net/) to encrypt or decrpyt databases.
[Info] <Debug> Android Debug Mode Checking:
           DEBUG mode is OFF(android:debuggable="false") in AndroidManifest.xml.
[Info]  Dynamic Code Loading:
           No dynamic code loading(DexClassLoader) found.
[Info] <#BID 64208, CVE-2013-6271#> Fragment Vulnerability Checking:
           Did not detect the vulnerability of "Fragment" dynamically loading into "PreferenceActivity" or "SherlockPreferenceActivity"
[Info] <Framework> Framework - MonoDroid:
           This app is NOT using MonoDroid Framework (http://xamarin.com/android).
[Info] <Hacker> Base64 String Encryption:
           No encoded Base64 String or Urls found.
[Info] <Database><Hacker> Key for Android SQLite Databases Encryption:
           Did not find using the symmetric key(PRAGMA key) to encrypt the SQLite databases (It's still possible that it might use but we
           did not find out).
[Info] <KeyStore><Hacker> KeyStore File Location:
           Did not find any possible BKS keystores or certificate keystore file (Notice: It does not mean this app does not use keysotre):
[Info] <KeyStore><Hacker> KeyStore Protection Checking:
           Ignore checking KeyStore protected by password or not because you're not using KeyStore.
[Info] <Hacker> Code Setting Preventing Screenshot Capturing:
           Did not detect this app has code setting preventing screenshot capturing.
[Info]  HttpURLConnection Android Bug Checking:
           Ignore checking "http.keepAlive" because you're not using "HttpURLConnection" and min_Sdk > 8.
[Info] <KeyStore> KeyStore Type Checking:
           KeyStore 'BKS' type check OK
[Info]  Google Cloud Messaging Suggestion:
           Nothing to suggest.
[Info] <#CVE-2013-4787#> Master Key Type I Vulnerability:
           No Master Key Type I Vulnerability in this APK.
[Info]  App Sandbox Permission Checking:
           No security issues "MODE_WORLD_READABLE" or "MODE_WORLD_WRITEABLE" found on 'openOrCreateDatabase' or 'openOrCreateDatabase2' or
           'getDir' or 'getSharedPreferences' or 'openFileOutput'
[Info]  Native Library Loading Checking:
           No native library loaded.
[Info]  AndroidManifest Dangerous ProtectionLevel of Permission Checking:
           No "dangerous" protection level customized permission found (AndroidManifest.xml).
[Info]  AndroidManifest PermissionGroup Checking:
           PermissionGroup in permission tag of AndroidManifest sets correctly.
[Info]  AndroidManifest "intent-filter" Settings Checking:
           "intent-filter" of AndroidManifest.xml check OK.
[Info]  AndroidManifest Normal ProtectionLevel of Permission Checking:
           No default or "normal" protection level customized permission found (AndroidManifest.xml).
[Info] <#CVE-2013-6272#> AndroidManifest Exported Lost Prefix Checking:
           No exported components that forgot to add "android:" prefix.
[Info]  AndroidManifest ContentProvider Exported Checking:
           No exported "ContentProvider" found (AndroidManifest.xml).
[Info] <Sensitive_Information> Getting IMEI and Device ID:
           Did not detect this app is getting the "device id(IMEI)" by "TelephonyManager.getDeviceId()" approach.
[Info]  Codes for Sending SMS:
           Did not detect this app has code for sending SMS messages (sendDataMessage, sendMultipartTextMessage or sendTextMessage).
[Info] <System> AndroidManifest sharedUserId Checking:
           This app does not use "android.uid.system" sharedUserId.
[Info] <SSL_Security> SSL Implementation Checking (Verifying Host Name in Custom Classes):
           Self-defined HOSTNAME VERIFIER checking OK.
[Info] <SSL_Security> SSL Implementation Checking (Verifying Host Name in Fields):
           Critical vulnerability "ALLOW_ALL_HOSTNAME_VERIFIER" field setting or "AllowAllHostnameVerifier" class instance not found.
[Info] <SSL_Security> SSL Implementation Checking (Insecure component):
           Did not detect SSLSocketFactory by insecure method "getInsecure".
[Info] <SSL_Security> SSL Implementation Checking (HttpHost):
           DEFAULT_SCHEME_NAME for HttpHost check: OK
[Info] <SSL_Security> SSL Implementation Checking (WebViewClient for WebView):
           Did not detect critical usage of "WebViewClient"(MITM Vulnerability).
[Info]  Unnecessary Permission Checking:
           Permission 'android.permission.ACCESS_MOCK_LOCATION' sets correctly.
[Info]  Accessing the Internet Checking:
           This app is using the Internet via HTTP protocol.
[Info]  AndroidManifest System Use Permission Checking:
           No system-level critical use-permission found.
[Info] <WebView> WebView Local File Access Attacks Checking:
           Did not find potentially critical local file access settings.
[Info] <WebView> WebView Potential XSS Attacks Checking:
           Did not detect "setJavaScriptEnabled(true)" in WebView.
[Info] <WebView><Remote Code Executi
                       on><#CVE-2013-4710#> WebView RCE Vulnerability Checking:
           WebView addJavascriptInterface vulnerabilities not found.
------------------------------------------------------------
AndroBugs analyzing time: 11.328774 secs
Total elapsed time: 49.506177 secs
