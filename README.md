<div align="center">

## QmBlurView
**QmBlurView is an `Android UI` component library that provides real-time, dynamic Gaussian blur effects. It uses native C++ code for efficient blur processing and provides a set of blur UI components to enhance your application design**

<br>

[![GitHub](https://img.shields.io/badge/GitHub-Repository-black?logo=github)](https://GitHub.com/QmDeve/QmBlurView/)

[![Telegram](https://img.shields.io/badge/Telegram%20Group-QmDeves-blue.svg?logo=telegram)](https://t.me/QmDeves)

[![License](https://img.shields.io/github/license/QmDeve/QmBlurView.svg?logo=github&color=blue&label=License)](https://github.com/QmDeve/QmBlurView/blob/master/LICENSE)
[![Android](https://img.shields.io/badge/Android-5.0%2B-brightgreen.svg)](https://developer.android.com)

[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)
[![JitPack Latest Version](https://jitpack.io/v/com.qmdeve/QmBlurView.svg?label=JitPack)](https://jitpack.io/#com.qmdeve/QmBlurView)
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
- **Easy Integration**: Simple XML attributes and Java/Kotlin APIs.
- **Broad Compatibility**: Supports Android 5.0 (API 21) and above.

## Screenshot

|                                BlurView                                |                              BlurButtonView                              |                                ProgressiveBlurView                                |
|:----------------------------------------------------------------------:|:------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|
| <img src="https://cloud.qmdeve.com/f/aRFw/BlurView.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/wKi5/BlurButton.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/MluM/ProgressiveBlurView.jpeg" width="250"/> |

|                                BlurTitleBarView                                |                                BlurSwitchButtonView                                 |                              BlurBottomNavigationView                              |
|:------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------:|
| <img src="https://cloud.qmdeve.com/f/Posw/BlurTitlebarView.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/9wHl/BlurSwitchButton_true.jpeg" width="250"/> | <img src="https://cloud.qmdeve.com/f/KbcO/BlurBottomNavigation.jpeg" width="250"/> |

## Integration

### Maven Central Integration Method

[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central%20Version)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)

Add the dependencies to your module's `build.gradle` file:

```gradle
dependencies {
    // Core Library (Required)
    implementation 'com.qmdeve.blurview:core:1.1.2'

    // Navigation Support (Optional)
    implementation 'com.qmdeve.blurview:navigation:1.1.2'

    // Image Loading Transformations (Optional - Glide/Picasso)
    implementation 'com.qmdeve.blurview:transform:1.1.2'
}
```

## Usage

### 1. Basic BlurView

Use `BlurView` to blur any part of your UI. It works best when placed on top of other content (e.g., in a `FrameLayout` or `RelativeLayout`).

**XML:**
```xml
<com.qmdeve.blurview.widget.BlurView
    android:id="@+id/blurView"
    android:layout_width="200dp"
    android:layout_height="200dp"
    app:blurRadius="25dp"
    app:cornerRadius="15dp"
    app:overlayColor="#80FFFFFF" />
```

**Java:**
```java
BlurView blurView = findViewById(R.id.blurView);
blurView.setBlurRadius(25);
blurView.setCornerRadius(15);
blurView.setOverlayColor(Color.parseColor("#80FFFFFF"));

// NEW: Control blur intensity (number of blur iterations)
blurView.setBlurRounds(5); // 1-15 iterations, higher = stronger blur
```

**Controlling Blur Intensity:**

The blur effect can be fine-tuned using two parameters:
- **Blur Radius** (2-100): Controls the size of the blur kernel (how far pixels blend)
- **Blur Iterations** (1-15): How many times the 2-pass blur is applied (higher = much stronger blur)

> **Note**: Each iteration performs both horizontal and vertical blur passes, so 5 iterations = 10 total blur passes.

```java
// Light blur - best performance
blurView.setBlurRadius(15);
blurView.setBlurRounds(2);

// Strong blur - balanced (recommended)
blurView.setBlurRadius(25);
blurView.setBlurRounds(3);

// Very strong blur - may impact performance
blurView.setBlurRadius(40);
blurView.setBlurRounds(5);

// Ultra intense blur - use sparingly (performance impact)
blurView.setBlurRadius(60);
blurView.setBlurRounds(8);
```

> **Performance Tips**:
> - Each iteration = 2 blur passes (horizontal + vertical)
> - High iterations (10+) can cause FPS drops on lower-end devices
> - **Recommended for real-time blur**: radius 20-30 with 2-4 iterations
> - For static/infrequent updates, you can use higher values
> - Combine with `setDownsampleFactor()` to blur smaller image for better performance

### 2. BlurViewGroup

Use the `BlurViewGroup` container

**XML:**
```xml
<com.qmdeve.blurview.widget.BlurViewGroup
    android:id="@+id/blurViewGroup"
    android:layout_width="200dp"
    android:layout_height="200dp"
    app:blurRadius="25dp"
    app:cornerRadius="15dp"
    app:overlayColor="#80FFFFFF" />
```

**Java:**
```java
BlurViewGroup blurViewGroup = findViewById(R.id.blurViewGroup);
blurViewGroup.setBlurRadius(25);
blurViewGroup.setCornerRadius(15);
blurViewGroup.setOverlayColor(Color.parseColor("#80FFFFFF"));
```

### 3. BlurButtonView

A customizable button with a blur background.

**XML:**
```xml
<com.qmdeve.blurview.widget.BlurButtonView
    android:id="@+id/blurButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Blur Button"
    app:blurRadius="12dp"
    app:buttonCornerRadius="12dp"
    app:overlayColor="#80FFFFFF"
    app:buttonIconSize="24dp"
    app:buttonTextBold="true" />
```

### 4. BlurBottomNavigationView

A bottom navigation bar with a built-in blur effect.

**XML:**
```xml
<com.qmdeve.blurview.widget.BlurBottomNavigationView
    android:id="@+id/bottomNav"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:menu="@menu/bottom_nav_menu"
    app:navOverlayColor="#AAFFFFFF"
    app:navSelectedColor="#0161F2"
    app:navUnselectedColor="#000000"
    android:layout_alignParentBottom="true" />
```

### 5. Image Loading Transformations

Apply blur directly to images loaded with Glide or Picasso.

**Glide:**
```java
Glide.with(context)
    .load(imageUrl)
    .transform(new com.qmdeve.blurview.transform.glide.BlurTransformation(25, 40)) // BlurRadius, CornerRadius
    .into(imageView);
```

**Picasso:**
```java
Picasso.get()
    .load(imageUrl)
    .transform(new com.qmdeve.blurview.transform.picasso.BlurTransformation(25, 40)) // BlurRadius, CornerRadius
    .into(imageView);
```

## Common Attributes

| Attribute | Description |
|---|---|
| `app:blurRadius` | Radius of the blur effect (higher = blurrier). |
| `app:overlayColor` | Color overlay drawn on top of the blurred image. |
| `app:cornerRadius` | Corner radius for the view background. |
| `app:downsampleFactor` | Downsampling factor for performance optimization. |

## Detailed Documentation
**Detailed Documentation；[https://blurview.qmdeve.com](https://blurview.qmdeve.com)**

## Star History

[![Star History](https://starchart.qmdeve.com/QmDeve/QmBlurView.svg?variant=adaptive)](https://starchart.qmdeve.com/QmDeve/QmBlurView)

---

## My Other Projects

- **[AndroidLiquidGlassView](https://github.com/QmDeve/AndroidLiquidGlassView)**
- **[Qm Authenticator for Android](https://github.com/Rouneant/Qm-Authenticator-for-Android)**

## Used By
**The following are some open-source projects and applications that use the QmBlurView library:**
- [react-native-blur](https://github.com/sbaiahmed1/react-native-blur)
- [react-native-qmblurview](https://github.com/hannojg/react-native-qmblurview)

> If your project or application is using the `QmBlurView` library, please add it to this list by `Pull Request` or sending an Email to `donny@qmdeve.com`

## License

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