## 视频医生带药SDK接入说明文档

### 一、SDK引入说明

#### 1. 接入环境说明同视频医生接入文档相同[《视频医生接入环境说明》](https://github.com/HHMedic/HHDoctorSDK_demo_Android#1-%E5%BB%BA%E8%AE%AE%E6%8E%A5%E5%85%A5%E7%8E%AF%E5%A2%83)

#### 2. 基本引入同视频医生接入文档相同[视频医生接入引入文档](https://github.com/HHMedic/HHDoctorSDK_demo_Android#2-%E8%A7%86%E9%A2%91%E5%8C%BB%E7%94%9Fandroid-sdk%E9%80%9A%E8%BF%87maven%E4%BB%93%E5%BA%93%E5%BC%95%E7%94%A8%E6%9D%A5%E5%AF%BC%E5%85%A5%E5%B7%A5%E7%A8%8B%E5%A6%82%E4%B8%8B)

> 注意用药SDK依赖于视频医生的引入，同时账户系统依赖于视频医生基础SDk

#### 3. 带药SDK引入

先在 build.gradle(Project:XXXX) 的 repositories 添加:
```
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```
注意以上这个配置是基于原视频SDK的新增项目，请务必添加，用药SDK中使用到了第三方库如下：
```
implementation 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.46'
```
如果这个库和接入项目中有冲突请及时告知。

然后添加用药库引用
```
implementation 'com.hhmedic.android.sdk:medicine:2.1.0'
```

#### 4. SDK引入特别说明
用药SDK用到data binding技术，需要接入方自行开启，需要开启databinding功能，需要在gradle配置文件android节点下开启如下：
```
android {
    ...
    dataBinding {
        enabled = true
    }
    ...
}
```
以上代码...代指其他配置不需要添加到gradle配置文件。


### 二、 SDK接入接口使用说明

> 注意用药SDK使用是基于视频医生SDK，所以是基于视频医生SDK的初始化，接口统一调用类为HHMedicine，调用方式为静态方法调用，通过HHMedicine.+接口名称方式调用

#### 1. 初始化用药SDK

```
HHMedicine.init();
```

> 注意初始化用药SDK依然和视频医生SDK相同需要将初始化代码放到Application的onCreate中去做。

#### 2.访问用药订单列表

```
public static void orderList(@NonNull Context context,@NonNull String userToken)
```

参数说明：

|参数定义|参数说明|
|---|---|
|Context context|上下文，指Activity|
|String userToken|通过与视频医生提供方服务器对接得到|

#### 3. 访问购药订单详情

```
public static void orderDetail(@NonNull Context context, @NonNull String orderId, @NonNull String userToken, @Nullable OnOrderListener listener)
```

参数说明：

|参数定义|参数说明|
|---|---|
|Context context|上下文，指Activity|
|String orderId|用药订单号|
|String userToken|通过与视频医生提供方服务器对接得到|
|String OnOrderListener|购药支付后回调|

#### 4. 支付明细

```
public static void payDetail(@NonNull Context context,@NonNull String userToken)
```

参数说明：

|参数定义|参数说明|
|---|---|
|Context context|上下文，指Activity|
|String userToken|通过与视频医生提供方服务器对接得到|

#### 5. 收货地址管理

```
public static void addressList(@NonNull Context context)
```

参数说明：

|参数定义|参数说明|
|---|---|
|Context context|上下文，指Activity|

### 三、 回调说明

#### 1. 查看订单详情支付结果回调

```
public interface OnOrderListener
{
    void onSuccess(String orderId);
}
```

在支付完后会有回调，onSuccess会收到支付完成orderId

### 四、版本更新说明

|版本号|说明|
|---|---|
|2.1.0| 发布用带SDK|
