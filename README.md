## 和缓视频医生 Android SDK接入文档

<p align="right">
北京和缓医疗科技有限公司<br/>
网址：https://www.hh-medic.com <br/>
地址：北京市东城区东直门来福士7层
</p>

* [一、SDK接入引用说明](#一sdk接入引用说明)
    * [1. 和缓视频医生Android SDK通过maven仓库引用来导入工程，如下](#1-和缓视频医生android-sdk通过maven仓库引用来导入工程如下)
        * [1.1 在build.gradle文件中配置远程库地址，在respositories中添加相应配置](#11-在buildgradle文件中配置远程库地址在respositories中添加相应配置)
        * [1.2 在build.gradle文件中dependencies中配置库的引用](#12-在buildgradle文件中dependencies中配置库的引用)
        * [1.3 配置NDK架构选择，必须进行对应配置](#13-配置ndk架构选择必须进行对应配置)
        * [1.4 java8支持的配置，必须配置](#14-java8支持的配置必须配置)
        * [1.5 packageingOptions配置，必须配置](#15-packageingoptions配置必须配置)
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
    * [3. 回调说明](#3-回调说明)
        * [3.1 登录回调（HHLoginListener）](#31-登录回调hhloginlistener)
        * [3.2 呼叫回调（HHCallListener）](#32-呼叫回调hhcalllistener)
        * [3.3 拒绝回调](#33-拒绝回调)
* [三、常见问题](#三常见问题)
    * [1. AndroidManifest合并冲突问题](#1-androidmanifest合并冲突问题)
* [四、Demo下载地址](#四demo下载地址)
* [五、版本更新说明](#五版本更新说明)

### 一、SDK接入引用说明

#### 1. 和缓视频医生Android SDK通过maven仓库引用来导入工程，如下

##### 1.1 在build.gradle文件中配置远程库地址，在respositories中添加相应配置

```
repositories {
    maven { url "https://jitpack.io" }
    maven {
        credentials {
            username 'hh-public'
            password 'OFGB5wX0'
        }
        url 'http://develop.hh-medic.com/repository/maven-public'
    }
}
```

##### 1.2 在build.gradle文件中dependencies中配置库的引用

```
implementation 'com.hhmedic.android.sdk:hh:0.0.1'
```

<span style="color:red;">注：添加以上配置后需要进行gradle sync才能同步生效，配置maven库地址的时候不能省略用户名和密码，否则同步不下来。</span>

##### 1.3 配置NDK架构选择，必须进行对应配置

```
ndk {
    //设置支持的SO库架构
    abiFilters "armeabi-v7a"
}
```

##### 1.4 java8支持的配置，必须配置

```
compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
```

##### 1.5 packageingOptions配置，必须配置

```
packagingOptions {
   pickFirst 'lib/armeabi-v7a/libsecsdk.so'
}
```

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
public static void handUp()
```

>用于视频中挂断操作

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

### 四、Demo下载地址

https://github.com/HHMedic/DoctorVideoDemo

### 五、版本更新说明

|版本号|说明|
|---|---|
|0.0.1| 发出初版|
|0.0.2| fix 直接呼叫crashbug|
|0.1.070915| 1、版本号切换尾号为时间 2、添加铃声可控配置 3、fix bugs|
