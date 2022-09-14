## TRTC版本接入图像旋转问题及解决方案整理

如果是硬件设备接入请注意以下提示：
> 由于硬件设备的摄像头采集方向不确定，在接入过程会遇到设备横屏情况下采集图像根据设备的默认方向（竖向）进行裁剪造成图像在和缓工作台显示为裁剪后的图像，如果遇到这种情况可以有限先不设置远程图像方向，优先考虑设置图像采集方向（设置为横屏采集）来尝试，具体设置如下：

```
//SDK初始化首先先声明一个如下配置
TRTCCloudDef.TRTCVideoEncParam encParam = new TRTCCloudDef.TRTCVideoEncParam();
// videoResolutionMode 设置为横屏
encParam.videoResolutionMode = TRTCCloudDef.TRTC_VIDEO_RESOLUTION_MODE_LANDSCAPE; 
//再使用VideoSetting中的setVideoEncParam这个方法设置进去就可以.
VideoSetting.setVideoEncParam(setVideoEncParam)
```

### 一、本地预览图像方向不正问题及解决方案

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
|TRTCCloudDef.TRTC_VIDEO_ROTATION_0|顺时针调整0度（无效果）|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_90|顺时针调整90度|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_180|顺时针调整180度|
|TRTCCloudDef.TRTC_VIDEO_ROTATION_270|顺时针调整270度|
