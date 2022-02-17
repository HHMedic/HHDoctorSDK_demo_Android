## 和缓视频医生Android SDK对接文档 3.4.0.02171826（快速接入版本）

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

implementation 'com.hhmedic.android.sdk:hh_trtc:3.4.0.02171826'
```

### 二、 初始化SDK

```
HHSDKOptions options = new HHSDKOptions(sdkProductId); //productId由和缓分配的产品Id
options.dev = true; //修改这个参数来切换测试环境和正式环境，当设置为true的时候是测试环境，设置为false为生产环境
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

### 四、跳转首页（必须登录后）

```
HHDoctor.message(this); //this指的是上下文Context
```

### 五、Demo及详细文档

Demo
https://github.com/HHMedic/HHDoctorSDK_demo_Android/tree/trtc

详细接入文档
https://github.com/HHMedic/HHDoctorSDK_demo_Android/blob/trtc/Document.md

TRTC版本接入图像旋转问题及解决方案整理
https://github.com/HHMedic/HHDoctorSDK_demo_Android/blob/trtc/Rotation.md

**注意如果需要对APP瘦身一定保留引用和缓视频SDK后出现的pic_error_78219.png这张图片，这张图片有特殊用途，删除会造成SDK不可用。**
