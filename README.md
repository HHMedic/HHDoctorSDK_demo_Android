## 和缓视频医生 Android SDK接入文档

<p align="right">
北京和缓医疗科技有限公司<br/>
网址：https://www.hh-medic.com <br/>
地址：北京市东城区东直门来福士7层
</p>

* [一、SDK接入引用说明](#一sdk接入引用说明)
   * [1. 建议接入环境](#1-建议接入环境)
      * [1.1 建议接入使用IDE版本](#11-建议接入使用ide版本)
      * [1.2 建议接入SDK版本](#12-建议接入sdk版本)
   * [2. 和缓视频医生Android SDK通过maven仓库引用来导入工程，如下](#2-和缓视频医生android-sdk通过maven仓库引用来导入工程如下)
      * [2.1 在build.gradle文件中配置远程库地址，在respositories中添加相应配置](#21-在buildgradle文件中配置远程库地址在respositories中添加相应配置)
      * [2.2 在build.gradle文件中dependencies中配置库的引用](#22-在buildgradle文件中dependencies中配置库的引用)
      * [2.3 配置NDK架构选择，必须进行对应配置](#23-配置ndk架构选择必须进行对应配置)
      * [2.4 java8支持的配置，必须配置](#24-java8支持的配置必须配置)
      * [2.5 packageingOptions配置，必须配置](#25-packageingoptions配置必须配置)
    * [3. 推送相关配置](#3-推送相关配置)
        * [3.1 申请华为和小米的推送](#31-申请华为和小米的推送)
        * [3.2 另外我们需要在配置文件AndroidManifest.xml文件配置push相关配置，如下：](#32-另外我们需要在配置文件androidmanifestxml文件配置push相关配置如下)
        * [3.3 我们提供push相关jar包](#33-我们提供push相关jar包)
    * [4. 我们用到的常用第三方库以及库的版本](4-我们用到的常用第三方库以及库的版本)
* [二、SDK接入引用说明](#二sdk接入引用说明)
   * [1. SDK初始化](#1-sdk初始化)
      * [1.1 SDK配置选项 HHSDKOptions](#11-sdk配置选项-hhsdkoptions)
      * [1.2 音箱接入快捷获取基本配置选项方式](#12-音箱接入快捷获取基本配置选项方式)
      * [1.3 SDK初始化](#13-sdk初始化)
   * [2. SDK功能介绍](#2-sdk功能介绍)
      * [2.1 获取SDK版本号](#21-获取sdk版本号)
      * [2.2 登录](#22-登录)
      * [2.3 登出](#23-登出)
      * [2.4 呼叫成人医生](#24-呼叫成人医生)
      * [2.5 呼叫儿童医生](#25-呼叫儿童医生)
      * [2.6 医生回拨的时候接受（主要应用于音箱）](#26-医生回拨的时候接受主要应用于音箱)
      * [2.7 医生回拨的时候拒绝（主要应用于音箱）](#27-医生回拨的时候拒绝主要应用于音箱)
      * [2.8 挂断（主要用于智能音箱）](#28-挂断主要用于智能音箱)
      * [2.9 获取用户登录状态](#29-获取用户登录状态)
      * [2.10 设置视频回拨处理结果回调](#210-设置视频回拨处理结果回调)
   * [3. 回调说明](#3-回调说明)
      * [3.1 登录回调（HHLoginListener）](#31-登录回调hhloginlistener)
      * [3.2 呼叫回调（HHCallListener）](#32-呼叫回调hhcalllistener)
      * [3.3 拒绝回调](#33-拒绝回调)
      * [3.4 视频回拨处理结果回调](#34-视频回拨处理结果回调)
* [三、常见问题](#三常见问题)
   * [1. AndroidManifest合并冲突问题](#1-androidmanifest合并冲突问题)
   * [2. error:style attribute '@android:attr/windowEnterAnimation' not found](#2-errorstyle-attribute-androidattrwindowenteranimation-not-found)
   * [3. SDK UTDID冲突解决方案](#3-sdk-utdid冲突解决方案)
   * [4. 使用阿里云Utils SDK造成的冲突即这个moudlealicloud-android-utils的冲突可以以如下方式解决](#4-使用阿里云Utils-SDK造成的冲突即这个moudlealicloud-android-utils的冲突可以以如下方式解决)
   * [5. 如果遇到库冲突也就是duplicate某个包这说明库冲突了，这种问题可以用如下方法解决](#5-如果遇到库冲突也就是duplicate某个包这说明库冲突了这种问题可以用如下方法解决)
* [四、Demo下载地址](#四demo下载地址)
* [五、版本更新说明](#五版本更新说明)


### 一、SDK接入引用说明

#### 1. 建议接入环境

##### 1.1 建议接入使用IDE版本

Android Studio 3.x.x版本以上版本

##### 1.2 建议接入SDK版本

|配置项|版本|
|---|---|
|compileSdkVersion| 27及以上|
|minSdkVersion| 17及以上|
|targetSdkVersion| 27及以上|

#### 2. 和缓视频医生Android SDK通过maven仓库引用来导入工程，如下

##### 2.1 在build.gradle文件中配置远程库地址，在respositories中添加相应配置

```
repositories {

    maven {url 'http://developer.huawei.com/repo/'} //这个是在用到华为推送的时候才需要配置
    
    maven {
        credentials {
            username 'hh-public'
            password 'OFGB5wX0'
        }
        url 'http://develop.hh-medic.com/repository/maven-public'
    }
    
    maven {
        url 'http://maven.aliyun.com/nexus/content/repositories/releases/'
    }
}
```

##### 2.2 在build.gradle文件中dependencies中配置库的引用

```
implementation 'com.hhmedic.android.sdk:hh:2.0.5'
```

<span style="color:red;">注：添加以上配置后需要进行gradle sync才能同步生效，配置maven库地址的时候不能省略用户名和密码，否则同步不下来。</span>

##### 2.3 配置NDK架构选择，必须进行对应配置

```
ndk {
    //设置支持的SO库架构
    abiFilters "armeabi-v7a"
}
```

##### 2.4 java8支持的配置，必须配置

```
compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
```

##### 2.5 packageingOptions配置，必须配置

```
packagingOptions {
   pickFirst 'lib/armeabi-v7a/libsecsdk.so'
}
```

#### 3. 推送相关配置

> 如果需要使用SDK的push功能，需要进行相应文件和配置的添加，主要是小米和华为推送SDK包添加，以及推送参数的申请，小米
推送需要接入方提供AppSecret和包名给我们，华为需要接入方提供AppID和AppSecret以及包名给我们，我们使用这些参数去生成对应SDK中需要配置的参数。具体说明如下：

##### 3.1 申请华为和小米的推送

- 需要将小米的AppSecret提供给和缓
- 需要将华为的AppId和AppSecret提供给和缓

    > 我们使用这些以上提供的数据来生成我们需要的参数,注意如下两个参数均需和缓来生成。另外需要提供唤醒的时候应该跳到哪个界面的配置，通知的图标配置，小米推送的AppId和小米推送的AppKey。如下：
    
    ```java
    public class HHPushConfig {
    
        /**
         * 通知栏提醒的响应intent的activity类型。<br>
         * 可以为null。如果未提供，将使用包的launcher的入口intent的activity。
         */
        public Class<? extends Activity> notificationEntrance;
    
        /**
         * 状态栏提醒的小图标的资源ID。<br>
         * 如果不提供，使用app的icon
         */
        public int notificationSmallIconId;
    
    
        /**
         * 小米推送 appId
         */
        public String xmAppId;
    
        /**
         * 小米推送 appKey
         */
        public String xmAppKey;
    
        public String xmCertificateName; //这个参数是由小米提供的参数生成的
        
        public String hwCertificateName; //这个是由华为提供的参数生成的
    }

    ```
    
    ##### 3.2 另外我们需要在配置文件AndroidManifest.xml文件配置push相关配置，如下：
    
    ```xml
    //小米push相关
    
    <!-- 小米push permission -->

        <permission android:name="{这里需要替换成你的包名}.permission.MIPUSH_RECEIVE"
        android:protectionLevel="signature" />
        <uses-permission android:name="{这里需要替换成你的包名}.permission.MIPUSH_RECEIVE" />

    <!-- end-->
    
    <!-- 小米 推送-->

        <!-- 小米推送配置 -->
        <!--配置的service和receiver-->
        <service
            android:name="com.xiaomi.push.service.XMPushService"
            android:process=":mixpush"
            android:enabled="true"
            />
        <service
            android:name="com.xiaomi.push.service.XMJobService"
            android:enabled="true"
            android:exported="false"
            android:permission="android.permission.BIND_JOB_SERVICE"
            android:process=":mixpush" />
        <!--注：此service必须在3.0.1版本以后（包括3.0.1版本）加入-->
        <service
            android:enabled="true"
            android:exported="true"
            android:name="com.xiaomi.mipush.sdk.PushMessageHandler" />

        <service android:enabled="true"
            android:name="com.xiaomi.mipush.sdk.MessageHandleService" />
        <!--注：此service必须在2.2.5版本以后（包括2.2.5版本）加入-->
        <receiver
            android:exported="true"
            android:name="com.xiaomi.push.service.receivers.NetworkStatusReceiver" >
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </receiver>
        <receiver
            android:exported="false"
            android:process=":mixpush"
            android:name="com.xiaomi.push.service.receivers.PingReceiver" >
            <intent-filter>
                <action android:name="com.xiaomi.push.PING_TIMER" />
            </intent-filter>
        </receiver>



        <receiver
            android:name="com.netease.nimlib.mixpush.mi.MiPushReceiver"
            android:exported="true">
            <intent-filter android:priority="0x7fffffff">
                <action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE"/>
                <action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED"/>
                <action android:name="com.xiaomi.mipush.ERROR"/>
            </intent-filter>
        </receiver>

        <!-- 小米 end -->
    
    
    
    
    //华为push相关
        <meta-data
            android:name="com.huawei.hms.client.appid"
            android:value="华为的AppID" />
        <provider
            android:name="com.huawei.hms.update.provider.UpdateProvider"
            android:authorities="{这里需要替换成自己的包名，不用带大括号}.hms.update.provider"
            android:exported="false"
            android:grantUriPermissions="true"/>

    ```

##### 3.3 我们提供push相关jar包

> jar包由我们发送给接入方

需要额外添加gradle引用

```
api "com.netease.nimlib:push:5.6.0"
api 'com.huawei.android.hms:push:2.6.0.301'
```

如果使用华为的push需要添加混淆配置

```
-keep class com.huawei.hms.**{*;}
```

#### 4. 我们用到的常用第三方库以及库的版本
```
implementation 'com.google.code.gson:gson:2.8.2'
implementation 'com.orhanobut:logger:2.2.0'
implementation 'com.github.bumptech.glide:glide:4.8.0'
implementation 'com.zhihu.android:matisse:0.5.1'
implementation 'com.squareup.okhttp3:okhttp:3.x.x' //这个版本号只是一个代写
```

> 如果由于这些包引用出现冲突例如是duplicate某个jar包或文件有可能是某些库引用的版本和我们不一致，直接force一个合适的版本就行。具体写法可以参考[这里](#5-如果遇到库冲突也就是duplicate某个包这说明库冲突了这种问题可以用如下方法解决)。


### 二、SDK接入引用说明

#### 1. SDK初始化

##### 1.1 SDK配置选项 HHSDKOptions

```java
HHSDKOptions options = new HHSDKOptions("productId");
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
| productId | 和缓分配的产品ID |
| sDebug    |是否开启调试（开启会打印log）|
|mDeviceType|接入设备类型(NORMAL, SOUND)，NORMAL表示手机、Pad SOUND 表示音箱|
|mImei|设备序列号（只有音箱接入的时候需要传入）|
|dev|是否开始测试服模式，开启后连接测试服|
|isOpenCamera|视频过程中是否开启拍照|
|mOrientation|屏幕方向 ActivityInfo.SCREEN_ORIENTATION_PORTRAIT 或 ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE|
|mCoopId|渠道编号|

##### 1.2 音箱接入快捷获取基本配置选项方式

```java
public static HHSDKOptions defaultSoundOption(String productId)
```

通过如下方式可以快速获取音箱接入配置

```java
HHSDKOptions.defaultSoundOption("productId")
```

>其他特殊设置需要按照1.1中表格列出的参数就行设置，这种获取默认参数的方式只是获取的基本参数。

<span style="color:red;">
注意：音箱接入一般都是横屏模式，如果使用默认获取方法获取配置后不需要再单独配置屏幕方向；音箱默认配置默认关闭拍照功能；音箱默认配置已经指定了mDeviceType为音箱类型（DeviceType.SOUND）这个也无需修改。
</span>

##### 1.3 SDK初始化

>SDK初始化最好是放到自定义的Application中去初始化。

```java
HHSDKOptions options = ...;//这里可以自行初始化，可以是音箱默认配置获取也可以直接初始化
options.isDebug = true;
options.mDeviceType = DeviceType.NORMAL;//如果是使用音箱默认配置不需要配置这个
options.mImei = "设备编号";
options.dev = true;
options.isOpenCamera = false;
options.mOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
HHDoctor.init(getApplicationContext(),options);
```

#### 2. SDK功能介绍

##### 2.1 获取SDK版本号

```java
public static String SDKVersion()
```

>通过这个接口在调试的过程可以获取到当前SDK的版本号

##### 2.2 登录

```java
public static void login(Context context, long uuid, HHLoginListener listener)
```

参数说明

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前操作Activity|
|long uuid|接入方和和缓服务端对接后返回和缓的uuid|
|HHLoginListener listener|登录回调|

##### 2.3 登出

```java
public static void loginOut(Context context)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前操作Activity|

##### 2.4 呼叫成人医生

```java
public static void callForAdult(Context context,HHCallListener listener)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|
|HHCallListener listener|呼叫回调|

##### 2.5 呼叫儿童医生

```java
public static void callForChild(Context context,HHCallListener listener)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|
|HHCallListener listener|呼叫回调|

##### 2.6 医生回拨的时候接受（主要应用于音箱）

```java
public static void onAccept(Context context,HHCallListener listener)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|
|HHCallListener listener|呼叫回调|

##### 2.7 医生回拨的时候拒绝（主要应用于音箱）

```java
public static void onRefuse(Context context,String message,Refuse.OnCallback callback)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|
|String message|服务端推送的数据|
|Refuse.OnCallback callback|呼叫回调|

##### 2.8 挂断（主要用于智能音箱）

```java
public static void hangUp()
```

>用于视频中挂断操作

##### 2.9 获取用户登录状态

```java
public static boolean isLogined(Context context)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|

##### 2.10 设置视频回拨处理结果回调

```
public static void setCallbackListener(HHCallbackListener listener)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|HHCallbackListener listener|回调代理|



#### 3. 回调说明

>主要说明在各个接口用到的回调代理

##### 3.1 登录回调（HHLoginListener）

```java
public interface HHLoginListener
{
    /**
     * 登录成功
     */
    void onSuccess();

    /**
     * 登录失败
     * @param tips
     */
    void onError(String tips);
}

```

##### 3.2 呼叫回调（HHCallListener）

```java
public interface HHCallListener
{

    /**
     * 启动呼叫
     */
    void onStart();

    /**
     * 呼叫中
     */
    void onCalling();

    /**
     * 通话中
     */
    void onInTheCall();

    /**
     * 通话结束
     */
    void onFinish();


    /**
     * 呼叫成功，等待医生接受
     */
    void onCallSuccess();


    /**
     * 呼叫失败
     * @param code 错误码
     */
    void onFail(int code);

    /**
     * 取消呼叫  含 取消排队
     */
    void onCancel();


    /**
     *
     * 排队超时 自动取消
     */
    void onLineUpTimeout();


    /**
     *
     * 需要排队等待
     */

    void onLineUp();
}
```

##### 3.3 拒绝回调

```java
public interface OnCallback
    {
        /**
         * 拒绝成功
         */
        void onSuccess();

        /**
         * 拒绝出现问题
         * @param 发生的错误
         */
        void onError(String tips);
    }
```

##### 3.4 视频回拨处理结果回调

```java
public interface HHCallbackListener {

    //拒绝接听
    void onRefuse();

    //接听
    void onAccept();

}
```


### 三、常见问题

#### 1. AndroidManifest合并冲突问题

```
 <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/provider_paths" />
</provider>
```

可以更替乘如下写法

```
<manifest package="cn.edu.fudan.rndrobot"
    xmlns:tools="http://schemas.android.com/tools"
          xmlns:android="http://schemas.android.com/apk/res/android">
    <provider
        tools:replace="android:authorities"
        android:name="android.support.v4.content.FileProvider"
        android:authorities="${applicationId}.fileprovider"
        android:exported="false"
        android:grantUriPermissions="true">
        <meta-data
            tools:replace="android:resource"
            android:name="android.support.FILE_PROVIDER_PATHS"
            android:resource="@xml/file_paths"/>
    </provider>
</manifest>
```

#### 2. error:style attribute '@android:attr/windowEnterAnimation' not found

在Project/gradle.properties中添加 android.enableAapt2=false


#### 3. SDK UTDID冲突解决方案

Android UTDID包命名形式为：utdid4all-x.x.x.jar，UTDID作为阿里集团移动端SDK通用组件，包括阿里云在内的许多平台产品移动端SDK对其有依赖，若同时集成多平台移动端SDK，可能发生UTDID冲突。解决重复的方案是手动删除重复的UTDID SDK，仅保留一个UTDID SDK，建议保留阿里云平台下载的UTDID SDK。如果是通过gradle引用通过关闭其他SDK包的utdid的引用即可如下：
```
compile ('com.xxx:xxx.xxx:1.0.1') {
  exclude (module: 'alicloud-android-utdid')
}
```

如果这种方式解决不了可以去阿里官网下载一个不带utdid的一个支付宝的包用就行，具体说明地址如下
https://help.aliyun.com/knowledge_detail/59152.html?spm=a2c4g.11186623.2.20.26d216ee1AMm0k

#### 4. 使用阿里云Utils SDK造成的冲突即这个moudlealicloud-android-utils的冲突可以以如下方式解决

造成冲突的原因有很多种，例如如果同时使用了阿里的 Utils库和友盟的库就会造成冲突，最好使用Utils库不要使用本地引用最好使用远程gradle引用。如果遇到了冲突可以先查看本地是否引用了阿里云的Utils SDK的包如果有可以删除即可或者使用gralde引用然后利用exclude排除Utils的module，即排除alicloud-android-utils。具体问题可以参照阿里的说明，地址如下
https://helpcdn.aliyun.com/knowledge_detail/66886.html?spm=a2c4g.11186631.2.1.8c0fb068qquUGZ

#### 5. 如果遇到库冲突也就是duplicate某个包这说明库冲突了，这种问题可以用如下方法解决
这种问题遇到的情况有可能是用到的库和我们SDK中用到的库冲突，如果是module的冲突类似于问题4中的那种可以用exclude来进行排序module就行，另外一种情况是库和我们SDK库引用的版本的版本不一样，只要对库的版本选一个最合适的版本force一下版本就可以解决了。force大概写法如下：
```
configurations.all {
    resolutionStrategy {
        force "com.android.support:recyclerview-v7:27.1.1"
    }
}
```

### 四、Demo下载地址

https://github.com/HHMedic/DoctorVideoDemo

### 五、版本更新说明

|版本号|说明|
|---|---|
|0.0.1| 发出初版|
|0.0.2| fix 直接呼叫crashbug|
|0.1.070915| 1、版本号切换尾号为时间 2、添加铃声可控配置 3、fix bugs|
|0.1.071810| 1、新增评价功能 2、fix bugs|
|0.1.071815| 更新配置可以去除maven { url "https://jitpack.io" }这个配置|
|2.0.0|HHSDKOptions添加默认摄像头选择配置videoDefaultFrontCamera，默认开启前置摄像头，特殊情况摄像头在设备上相反的情况下可以取反|
|2.0.1|1、添加获取用户登录状态接口HHDoctor.isLogined 2、添加设置回拨处理状态处理回调设置 HHDoctor.setCallbackListener|
|2.0.3|fix bugs|
|2.0.4|utdid回退添加，如果遇到冲突请按说明解决|


