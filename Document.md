## 视频医生 Android SDK接入文档 (3.6.0.01101738)
<!--![demo](demo.gif)-->

[接入过程遇到的问题及解决方案的汇总参考](QA.md)
[TRTC版本SDK接入遇到的图像方向问题及解决方案](Rotation.md)

## [查看版本更新说明](#六版本更新说明)

* [一、SDK接入引用说明](#一sdk接入引用说明)
   * [1. 建议接入环境](#1-建议接入环境)
      * [1.1 建议接入使用IDE版本](#11-建议接入使用ide版本)
      * [1.2 建议接入SDK版本](#12-建议接入sdk版本)
   * [2. 视频医生Android SDK通过maven仓库引用来导入工程，如下](#2-视频医生android-sdk通过maven仓库引用来导入工程如下)
      * [2.1 在build.gradle文件中配置远程库地址，在respositories中添加相应配置](#21-在buildgradle文件中配置远程库地址在respositories中添加相应配置)
      * [2.2 在build.gradle文件中dependencies中配置库的引用](#22-在buildgradle文件中dependencies中配置库的引用)
      * [2.3 配置NDK架构选择，必须进行对应配置](#23-配置ndk架构选择必须进行对应配置)
      * [2.4 java8支持的配置，必须配置](#24-java8支持的配置必须配置)
    * [3. 我们用到的常用第三方库以及库的版本](#3-我们用到的常用第三方库以及库的版本)
* [二、SDK接入引用说明](#二sdk接入引用说明)
   * [1. SDK初始化](#1-sdk初始化)
      * [1.1 SDK配置选项 HHSDKOptions](#11-sdk配置选项-hhsdkoptions)
      * [1.2 音箱接入快捷获取基本配置选项方式](#12-音箱接入快捷获取基本配置选项方式)
      * [1.3 SDK初始化](#13-sdk初始化)
      * [1.4 SDK使用到的主要权限说明](#14-sdk使用到的主要权限说明)
   * [2. SDK功能介绍](#2-sdk功能介绍)
      * [2.1 获取SDK版本号](#21-获取sdk版本号)
      * [2.2 登录](#22-登录)
      * [2.3 登出](#23-登出)
      * [2.4 选择成员呼叫（推荐使用）](#24-选择成员呼叫推荐使用)
      * [2.5 选定成员呼叫](#25-选定成员呼叫)
      * [2.6 获取用户登录状态](#26-获取用户登录状态)
      * [2.7 获取病历列表地址](#27-获取病历列表地址)
      * [2.8 获取病历详情地址](#28-获取病历详情地址)
      * [2.9 获取所有成员病历列表地址(推荐使用)](#29-获取所有成员病历列表地址推荐使用)
      * [2.10 陪诊](#210-陪诊)
      * [2.11 进入消息界面](#211-进入消息界面)
      * [2.12 设置呼叫附加参数](#212-设置呼叫附加参数)
   * [3. 回调说明](#3-回调说明)
      * [3.1 登录回调（HHLoginListener）](#31-登录回调hhloginlistener)
      * [3.2 呼叫回调（HHCallListener）](#32-呼叫回调hhcalllistener)
      * [3.3 呼叫回调扩展（HHCallExListener）继承自HHCallListener](#33-呼叫回调扩展HHCallExListener继承自HHCallListener)
* [三、常见问题](#三常见问题)
   * [1. AndroidManifest合并冲突问题](#1-androidmanifest合并冲突问题)
   * [2. error:style attribute '@android:attr/windowEnterAnimation' not found](#2-errorstyle-attribute-androidattrwindowenteranimation-not-found)
   * [3. 如果遇到库冲突也就是duplicate某个包这说明库冲突了，这种问题可以用如下方法解决](#3-如果遇到库冲突也就是duplicate某个包这说明库冲突了这种问题可以用如下方法解决)
   * [4. 推送相关配置](#4-推送相关配置)
      * [4.1 申请华为和小米的推送](#41-申请华为和小米的推送)
      * [4.2 另外我们需要在配置文件AndroidManifest.xml文件配置push相关配置，如下：](#42-另外我们需要在配置文件androidmanifestxml文件配置push相关配置如下)
      * [4.3 我们提供push相关jar包](#43-我们提供push相关jar包)
* [四、错误码整理](#四错误码整理)
* [五、Demo下载地址](#五demo下载地址)
* [六、版本更新说明](#六版本更新说明)


### 一、SDK接入引用说明

#### 1. 建议接入环境

##### 1.1 建议接入使用IDE版本

Android Studio 3.x.x版本以上版本

##### 1.2 建议接入SDK版本以及最低支持设备系统版本

|配置项|版本|
|---|---|
|compileSdkVersion| 28及以上|
|minSdkVersion| 17及以上|
|targetSdkVersion| 28及以上|
|最低支持设备系统| >= 4.2 |

#### 2. 视频医生Android SDK通过maven仓库引用来导入工程，如下

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
}
```

##### 2.2 在build.gradle文件中dependencies中配置库的引用

```
implementation "com.hhmedic.android.sdk:hh_trtc:3.6.0.01101738"
```

<span style="color:red;">注：添加以上配置后需要进行gradle sync才能同步生效，配置maven库地址的时候不能省略用户名和密码，否则同步不下来。</span>

##### 2.3 配置NDK架构选择，必须进行对应配置（这个配置在2.5.4.03011601这个版本后废弃）

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

#### 3. 我们用到的常用第三方库以及库的版本
```
implementation 'com.google.code.gson:gson:2.8.6'
implementation 'com.orhanobut:logger:2.2.0'
implementation 'com.github.bumptech.glide:glide:4.11.0'
implementation 'com.zhihu.android:matisse:0.5.3-beta3'
implementation 'com.squareup.okhttp3:okhttp:3.x.x' //这个版本号只是一个代写
```

> 如果由于这些包引用出现冲突例如是duplicate某个jar包或文件有可能是某些库引用的版本和我们不一致，直接force一个合适的版本就行。具体写法可以参考[这里](#5-如果遇到库冲突也就是duplicate某个包这说明库冲突了这种问题可以用如下方法解决)。


### 二、SDK接入引用说明

#### 1. SDK初始化

##### 1.1 SDK配置选项 HHSDKOptions

```java
HHSDKOptions options = new HHSDKOptions("sdkProductId");
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
| sdkProductId | 分配的产品ID |
| sDebug    |是否开启调试（开启会打印log）|
|mDeviceType|接入设备类型(NORMAL, SOUND)，NORMAL表示手机、Pad SOUND 表示音箱|
|mImei|设备序列号（只有音箱接入的时候需要传入）|
|dev|是否开始测试服模式，开启后连接测试服|
|isOpenCamera|视频过程中是否开启拍照|
|mOrientation|屏幕方向 ActivityInfo.SCREEN_ORIENTATION_PORTRAIT 或 ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE|
|enableMedical|是否开启个人中心的档案库显示|
|enableActivate|是否开启个人中心激活码激活功能|

##### 1.2 SDK初始化

>SDK初始化最好是放到自定义的Application中去初始化。

```java
HHSDKOptions options = ...;
options.isDebug = true;
options.mDeviceType = DeviceType.NORMAL;
options.dev = true;
options.isOpenCamera = false;
options.mOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
options.pushConfig = new HHPushConfig() //这里是需要实例化然后补充参数后赋值
HHDoctor.init(getApplicationContext(),options);
```

##### 1.3 SDK使用到的主要权限说明

| 权限 | 说明 |
| --- | --- |
| android.permission.READ_PHONE_STATE | 在视频通话过程中如果有电话进来我们会做挂断视频保证正常电话通话 |
|android.permission.CAMERA|保证正常使用设备的摄像设备|
|android.permission.RECORD_AUDIO|保证正常使用设备的音频设备|
|android.permission.WRITE_EXTERNAL_STORAGE android.permission.READ_EXTERNAL_STORAGE | 保证正常读取存储设备上的媒体文件 |

#### 2. SDK功能介绍

##### 2.1 获取SDK版本号

```java
public static String SDKVersion()
```

>通过这个接口在调试的过程可以获取到当前SDK的版本号

##### 2.2 登录

```java
public static void login(Context context,String userToken,HHLoginListener listener)
```

参数说明

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前操作Activity|
|String userToken|接入方和视频医生服务端对接后返回的userToken|
|HHLoginListener listener|登录回调|

##### 2.3 登出

```java
public static void logOut(Context context)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前操作Activity|

##### 2.4 选择成员呼叫（*推荐使用*）

```java
public static void call(Context context,HHCallListener listener)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|
|HHCallListener listener|呼叫回调|

##### 2.5 选定成员呼叫

```java
public static void call(Context context,  String userToken,HHCallListener listener)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|
|userToken|咨询人的userToken|
|HHCallListener listener|呼叫回调|

##### 2.6 获取用户登录状态

```java
public static boolean isLogined(Context context)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|上下文，当前呼叫发起Activity|

##### 2.7 获取病历列表地址
```
public static String getMedicListUrl(Context context,String userToken)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|当前上下文，一般为当前Activity|
|String userToken|由视频医生提供方分配给第三方的用户安全标志，userToken为与视频医生提供方对接得到的用户安全标志|

##### 2.8 获取病历详情地址
```
public static String getMedicDetailUrl(Context context,String userToken,String medicId)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|当前上下文，一般为当前Activity|
|String userToken|由视频医生提供方分配给第三方的用户安全标志，userToken为与视频医生提供方对接得到的用户安全标志|
|String medicId |病历存档ID,这个存档ID由视频医生提供方同步到接入方的存档ID|

##### 2.9 获取所有成员病历列表地址(*推荐使用*)

```
public static String getAllMedics(Context context,String userToken)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|当前上下文，一般为当前Activity|
|String userToken|由视频医生提供方分配给第三方的用户安全标志，userToken为与视频医生提供方对接得到的用户安全标志|

##### 2.10 陪诊

```
public static void multiCall(Context context, CallType type, HHInviteUser user)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|当前上下文，一般为当前Activity|
|CallType type|呼叫类型，其中包括儿科和全科，值分别为CallType.child和CallType.all |
|HHInviteUser user|被邀请的陪诊人，具体用法说明如本表下方|

```
String userToken = "与和缓对接得到的被邀请用户userToken";
String userName = "被邀请用户的昵称（名字），这个字段为选填";
String userPhoto = "被邀请用户的头像提示，这个字段为为选填";
HHInviteUser inviteUser = new HHInviteUser(userToken);
inviteUser.setNickName(userName);
inviteUser.setPhotoUrl(userPhoto);
```

##### 2.11 进入消息界面

```
public static void message(Context context)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|Context context|当前上下文，一般为当前Activity|

**如果需要支持O2O购药，跳转首页的同时上传经纬度，方法如下：**
```
 Location.sendLocation(context, longitude, latitude);
```
以上方法参数说明：context是上下文， longitude和latitude分别是经纬度

##### 2.12 设置呼叫附加参数

> 注意这个方法需要在呼叫前进行设置才会生效

```java
public static void setExtension(String ext)
```

参数说明：

| 参数定义 | 说明 |
| --- | --- |
|String ext|文本数据，不支持json格式，最长长度支持64位|

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
    void onStart(String orderId);

    /**
     * 呼叫中
     */
    void onCalling();

    /**
     * 通话中（该回调方法在3.0.0*版本以后废弃请使用HHCallExListener中的onDoctorAgree回调方法替代）
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

##### 3.3 呼叫回调扩展（HHCallExListener）继承自HHCallListener

```java
public interface HHCallExListener extends HHCallListener {

    /**
    * 返回呼叫医生信息
    **/
    void onLoadDoctor(HHCallInfo callInfo);

    /**
    * 医生接听进入通话
    **/
    void onDoctorAgree();

    
    void onCallError(String error);
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
        android:authorities="${applicationId}.provider"
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

#### 3. 如果遇到库冲突也就是duplicate某个包这说明库冲突了，这种问题可以用如下方法解决
这种问题遇到的情况有可能是用到的库和我们SDK中用到的库冲突，如果是module的冲突类似于问题4中的那种可以用exclude来进行排序module就行，另外一种情况是库和我们SDK库引用的版本的版本不一样，只要对库的版本选一个最合适的版本force一下版本就可以解决了。force大概写法如下：
```
configurations.all {
    resolutionStrategy {
        force "com.android.support:recyclerview-v7:27.1.1"
    }
}
```

#### 4. 推送相关配置

> 如果需要使用SDK的push功能，需要进行相应文件和配置的添加，主要是小米和华为推送SDK包添加，以及推送参数的申请，小米
推送需要接入方提供AppSecret和包名给我们，华为需要接入方提供AppID和AppSecret以及包名给我们，我们使用这些参数去生成对应SDK中需要配置的参数。

##### 4.1 申请华为和小米的推送

- 需要将小米的AppSecret提供
- 需要将华为的AppId和AppSecret提供

    > 我们使用这些以上提供的数据来生成我们需要的参数,注意如下两个参数均需视频医生提供方来生成。另外需要提供唤醒的时候应该跳到哪个界面的配置，通知的图标配置，小米推送的AppId和小米推送的AppKey。如下：
    
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
    
        public String xmCertificateName; //这个参数是由小米提供的参数生成
        
        public String hwCertificateName; //这个是由华为提供的参数生成
        
        public String vivoCertificateName; //这个是由vivo提供的参数生成
    }

    ```
    
    ##### 4.2 另外我们需要在配置文件AndroidManifest.xml文件配置push相关配置，如下：
    
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

##### 4.3 我们提供push相关jar包

> jar包由我们发送给接入方

需要额外添加gradle引用

```
api 'com.huawei.android.hms:push:2.6.0.301'
```

如果使用华为的push需要添加混淆配置

```
-keep class com.huawei.hms.**{*;}
```


### 四、错误码整理

|错误码|说明|
|---|---|
|-1||
|-5|未登录|
|-2||

### 五、Demo下载地址

https://github.com/HHMedic/HHDoctorSDK_demo_Android/tree/trtc

### 六、版本更新说明

版本更新说明

|版本号|说明|
|---|---|
|3.6.0.01101738|优化信息流|
|3.5.0.07281745|修正权限问题|
|3.5.0.07261105|1.兼容Android 12  2.优化视频中UI|
|3.1.6.10081541|兼容Android Q读取照片问题，如果targetSDKVersion设置的29 需要在AndroidManifest开启uestLegacyExternalStorage设置为true|
|3.1.6.09141533|解决一些layout相关问题|
|3.1.6.09131705|优化文案格式化|
|3.1.6.08191852|增加绿通、挂号、重疾、护理挂号服务|
|3.1.2.04011456|音视频优化|
|3.0.8.01061149|音视频优化|
|3.0.6.12022116|优化混淆规则|
|3.0.6.11161853|新版TRTC首发|
