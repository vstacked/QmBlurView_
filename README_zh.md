<div align="center">

## QmBlurView
**QmBlurView 是一个 `Android UI` 组件库，提供实时、动态的高斯模糊效果。它使用原生C++代码进行高效的模糊处理，并提供一组模糊UI组件来增强您的应用程序设计**

<br>

[![GitHub](https://img.shields.io/badge/GitHub-Repository-black?logo=github)](https://GitHub.com/QmDeve/QmBlurView/)

[![QQ](https://img.shields.io/badge/QQ%20群组-1046829337-blue.svg?logo=qq)](https://qm.qq.com/q/wIlrQPTMRO)
[![Telegram](https://img.shields.io/badge/Telegram%20群组-QmDeve-blue.svg?logo=telegram)](https://t.me/QmDeve)

[![License](https://img.shields.io/github/license/QmDeve/QmBlurView.svg?logo=github&color=blue&label=License)](https://github.com/QmDeve/QmBlurView/blob/master/LICENSE)
[![Android](https://img.shields.io/badge/Android-5.0%2B-brightgreen.svg)](https://developer.android.com)

[![Maven Central](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)
[![GitHub Releases](https://img.shields.io/github/release/QmDeve/QmBlurView?label=GitHub%20Releases)](https://github.com/QmDeve/QmBlurView/releases)

<br>

[English](./README.md) | 简体中文

</div>

---

## 特性

- **高性能**：使用 C/C++ 实现的原生模糊算法，确保最大的速度和流畅度。
- **实时模糊**：随着背景内容的变化自动更新模糊效果。
- **丰富的组件库**：
  - `BlurView`：通用模糊视图。
  - `BlurViewGroup`：适用于任何布局的可定制容器
  - `BlurButtonView`：具有毛玻璃效果的按钮。
  - `BlurBottomNavigationView`：时尚的模糊底部导航栏。
  - `BlurTitlebarView`、`BlurSwitchButtonView`、`BlurFloatingButtonView` 和 `ProgressiveBlurView`。
- **图片加载支持**：内置针对 **Glide** 和 **Picasso** 的变换支持。
- **易于集成**：简单的 XML 属性和 Java/Kotlin API。
- **广泛兼容**：支持 Android 5.0 (API 21) 及以上版本。

## 截图

|                                BlurView                                |                              BlurButtonView                              |                                ProgressiveBlurView                                |
|:----------------------------------------------------------------------:|:------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|
| <img src="https://cloud.qmdeve.com/f/aRFw/BlurView.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/wKi5/BlurButton.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/MluM/ProgressiveBlurView.jpeg" width="250"/> |

|                                BlurTitleBarView                                |                                BlurSwitchButtonView                                 |                              BlurBottomNavigationView                              |
|:------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------:|
| <img src="https://cloud.qmdeve.com/f/Posw/BlurTitlebarView.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/9wHl/BlurSwitchButton_true.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/KbcO/BlurBottomNavigation.jpeg" width="250"/> |

## 集成

[![最新版本](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central最新版本)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)

在模块的 `build.gradle` 文件中添加依赖项：

```gradle
dependencies {
    // 核心库（必需）
    implementation 'com.qmdeve.blurview:core:1.1.3'

    // 导航支持（可选）
    implementation 'com.qmdeve.blurview:navigation:1.1.3'

    // 图片加载变换（可选 - Glide/Picasso）
    implementation 'com.qmdeve.blurview:transform:1.1.3'
}
```

## 使用

请查看文档，以了解如何使用该库

**文档；[https://blurview.qmdeve.com](https://blurview.qmdeve.com/zh)**

## Star 历史

[![Star History](https://starchart.qmdeve.com/QmDeve/QmBlurView.svg?variant=adaptive)](https://starchart.qmdeve.com/QmDeve/QmBlurView)

---

## 我的其他项目

- **[AndroidLiquidGlassView](https://github.com/QmDeve/AndroidLiquidGlassView)**
- **[Qm Authenticator for Android](https://github.com/Rouneant/Qm-Authenticator-for-Android)**

## Used By
**以下是一些使用了 QmBlurView 库的开源项目或应用程序：**
- [react-native-blur](https://github.com/sbaiahmed1/react-native-blur)
- [react-native-qmblurview](https://github.com/hannojg/react-native-qmblurview)

> 如果您的项目或应用程序正在使用 `QmBlurView` 库，请通过 `Pull Request` 将其添加到此列表中或发送电子邮件至 `donny@qmdeve.com`

## 许可证

```
Copyright ©️ 2025-2026 DonnyYale (QmDeve)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```