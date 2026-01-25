<div align="center">

## QmBlurView
**QmBlurView is an `Android UI` component library that provides real-time, dynamic Gaussian blur effects. It uses native C++ code for efficient blur processing and provides a set of blur UI components to enhance your application design**

<br>

[![GitHub](https://img.shields.io/badge/GitHub-Repository-black?logo=github)](https://GitHub.com/QmDeve/QmBlurView/)

[![Telegram](https://img.shields.io/badge/Telegram%20Group-QmDeve-blue.svg?logo=telegram)](https://t.me/QmDeve)

[![License](https://img.shields.io/github/license/QmDeve/QmBlurView.svg?logo=github&color=blue&label=License)](https://github.com/QmDeve/QmBlurView/blob/master/LICENSE)
[![Android](https://img.shields.io/badge/Android-5.0%2B-brightgreen.svg)](https://developer.android.com)

[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)
[![GitHub Releases](https://img.shields.io/github/release/QmDeve/QmBlurView?label=GitHub%20Releases)](https://github.com/QmDeve/QmBlurView/releases)

</div>

---

## Features

- **High Performance**: Native blur algorithm implemented in C/C++ for maximum speed and smoothness.
- **Real-time Blurring**: Automatically updates the blur effect as the background content changes.
- **Rich Component Library**:
  - `BlurView`: Universal blur view
  - `BlurViewGroup`: Customizable container for any layout.
  - `BlurButtonView`: Buttons with frosted glass effects.
  - `BlurBottomNavigationView`: Stylish blurred bottom navigation.
  - `BlurTitlebarView`, `BlurSwitchButtonView`, `BlurFloatingButtonView`, and `ProgressiveBlurView`.
- **Image Loading Support**: Built-in transformations for **Glide** and **Picasso**.

## Screenshot

|                                BlurView                                |                              BlurButtonView                              |                                ProgressiveBlurView                                |
|:----------------------------------------------------------------------:|:------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|
| <img src="https://cloud.qmdeve.com/f/eqsn/BlurView.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/YyT3/BlurButton.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/xYug/ProgressiveBlurView.jpeg" width="250"/> |

|                                BlurTitleBarView                                |                                BlurSwitchButtonView                                 |                              BlurBottomNavigationView                              |
|:------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------:|
| <img src="https://cloud.qmdeve.com/f/Rbhn/BlurTitlebarView.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/3Nfl/BlurSwitchButton_true.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/gecr/BlurBottomNavigation.jpeg" width="250"/> |

## Integration

[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central%20Version)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)

Add the dependencies to your module's `build.gradle` file:

```gradle
dependencies {
    // Core Library (Required)
    implementation 'com.qmdeve.blurview:core:1.1.3'

    // Navigation Support (Optional)
    implementation 'com.qmdeve.blurview:navigation:1.1.3'

    // Image Loading Transformations (Optional - Glide/Picasso)
    implementation 'com.qmdeve.blurview:transform:1.1.3'
}
```

## Usage

Please refer to the documentation to learn how to use the library

**Documentation；[https://blurview.qmdeve.com](https://blurview.qmdeve.com)**

## Star History

[![Star History](https://starchart.qmdeve.com/QmDeve/QmBlurView.svg?variant=adaptive)](https://starchart.qmdeve.com/QmDeve/QmBlurView)

---

## My Other Projects

- **[AndroidLiquidGlassView](https://github.com/QmDeve/AndroidLiquidGlassView)**
- **[Qm Authenticator for Android](https://github.com/Rouneant/Qm-Authenticator-for-Android)**

## Used By
**The following are some open-source projects or applications that use the QmBlurView library:**
- [react-native-blur](https://github.com/sbaiahmed1/react-native-blur)
- [react-native-qmblurview](https://github.com/hannojg/react-native-qmblurview)

> If your project or application is using the `QmBlurView` library, please add it to this list by `Pull Request` or sending an Email to `donny@qmdeve.com`

## License

```
Copyright ©️ 2025-2026 Donny Yale

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