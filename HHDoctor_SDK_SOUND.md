## 和缓视频医生Android SDK对接文档 3.4.0.04191618（ SOUND 版本）

### 一、引入SDK

```
在project的build.gradle文件中加入如下配置，由于SDK是做成了私有库所以必须加入此配置

repositories {
    
    maven {
        credentials {
            username 'hh-public'
            password 'OFGB5wX0'
        }
        url 'http://develop.hh-medic.com/repository/maven-public'
    }
}

在app moudule的build.gradle文件中引用和缓视频医生SDK，如下：

implementation 'com.hhmedic.android.sdk:hh_trtc:3.4.0.04191618'
```

### 二、 初始化SDK

```
HHSDKOptions options = new HHSDKOptions(sdkProductId); //productId由和缓分配的产品Id
options.dev = true; //修改这个参数来切换测试环境和正式环境，当设置为true的时候是测试环境，设置为false为生产环境
options.mDeviceType = DeviceType.SOUND; //在TV上接入的时候mDeviceType必须设置为DeviceType.SOUND
HHDoctor.init(getApplicationContext(), options);
```

### 三、登录登出

```

//登录
String userToken = "这个参数是服务器和和缓服务器对接后得到的用户userToken";
HHDoctor.login(this, userToken, new HHLoginListener() {
            @Override
            public void onSuccess() {
               //这里处理登录后的逻辑
            }

            @Override
            public void onError(String s) {
               //处理登录失败后的逻辑，一般不会发生
            }
        });
        
//登出
HHDoctor.logOut(this); //this指的是上下文Context
```

### 四、 呼叫视频医生

```java
public static void call(Context context,  String userToken,HHCallListener listener)
```

### 五、SDK所需必要权限说明

由于`和缓视频医生SDK`主要提供的服务室视频医生服务，所以所需权限为音频采集和视频采集权限，权限列表如下：


| 权限 | 说明 | 
|---|---|
| android.permission.CAMERA | 摄像头采集 |
| android.permission.RECORD_AUDIO | 音频采集 |

> SDK内部做了权限检测以及申请，为了更好的体验，接入方可以在进入和缓视频医生SDK呼叫之前提前申请音频和视频权限。

### 六、视频方向适配说明

> 由于各种接入设备类型不同可能会遇到视频方向的不正确，可以通过以下说明进行适配调整

#### 1. 本地预览图像方向不正问题及解决方案

**本地预览图像在一些特殊设备上会出现方向不正的问题，遇到这类问题需要通过初始化参数来对图像做一些调整，调整方式如下。**

初始化配置类HHSDKOptions中设置localRenderRotation 配置的值，图像调整方向为顺时针调整，可设置值分别有如下参数，如表格列出。

调整方向参数整理如下表格：

|参数|调整方向及角度（原图像角度基础上调整）|
|---|---|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_0|顺时针调整0度（无效果）|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_90|顺时针调整90度|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_180|顺时针调整180度|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_270|顺时针调整270度|

在本地图像的方向调整好后有可能在医生工作台看客户图像方向不正的问题，那么就需要看第二大部分进行相关设置的介绍。

#### 2. 远端图像方向不正的问题及解决方案

**在对接的过程可能会遇到格式各样的设置（也可以简称硬件），有时候会遇到本地图像的方向调整好了，但在远端（医生端）看起来图像方向不正，这个时候就需要通过VideoSetting这个类针对远端图像方向做调整，具体调用方式可以参考如下代码。**

```java
//首先需要关闭设备重力感应。
VideoSetting.setEnableGSENSORMode(false); 

//设置远端图像的调整方向，调整方向为顺时针，调整方向参数是可以修改的，可调整
//参数请参考后面给出的表格中的参数。
VideoSetting.setRemoteRotation(TRTCCloudDef.TRTC_VIDEO_ROTATION_90);

//这个设置可以在调整的时候不添加，如果通过以上两条设置仍然不生效，再另行添加这个设置进行实验。
VideoSetting.setVideoResolutionMode(TRTCCloudDef.TRTC_VIDEO_RESOLUTION_MODE_LANDSCAPE); 
```

调整方向参数整理如下表格：

|参数|调整方向及角度（原图像角度基础上调整）|
|---|---|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_0|顺时针调整0度（无效果）|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_90|顺时针调整90度|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_180|顺时针调整180度|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_270|顺时针调整270度|
