## TRTC版本接入图像旋转问题及解决方案整理

### 一、本地预览图像方向不正问题及解决方案

**本地预览图像在一些特殊设备上会出现方向不正的问题，遇到这类问题需要通过初始化参数来对图像做一些调整，调整方式如下。**

初始化配置类HHSDKOptions中设置localRenderRotation 配置的值，图像调整方向为顺时针调整，可设置值分别有如下参数，如表格列出。

调整方向参数整理如下表格：

|参数|调整方向及角度（原图像角度基础上调整）|
|---|---|
|RTCCloudDef.TRTC_VIDEO_ROTATION_0|顺时针调整0度（无效果）|
|RTCCloudDef.TRTC_VIDEO_ROTATION_90|顺时针调整90度|
|RTCCloudDef.TRTC_VIDEO_ROTATION_180|顺时针调整180度|
|RTCCloudDef.TRTC_VIDEO_ROTATION_270|顺时针调整270度|

在本地图像的方向调整好后有可能在医生工作台看客户图像方向不正的问题，那么就需要看第二大部分进行相关设置的介绍。

### 二、远端图像方向不正的问题及解决方案

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
|RTCCloudDef.TRTC_VIDEO_ROTATION_0|顺时针调整0度（无效果）|
|RTCCloudDef.TRTC_VIDEO_ROTATION_90|顺时针调整90度|
|RTCCloudDef.TRTC_VIDEO_ROTATION_180|顺时针调整180度|
|RTCCloudDef.TRTC_VIDEO_ROTATION_270|顺时针调整270度|
