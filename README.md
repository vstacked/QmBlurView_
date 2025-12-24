<div align="center">

<img src="https://socialify.git.ci/QmDeve/QmBlurView/image?description=1&font=Inter&name=1&owner=1&pattern=Floating+Cogs&theme=Light" alt="QmBlurView" width="100%"/>

<br>
<br>

[![GitHub](https://img.shields.io/badge/GitHub-Repository-black?logo=github)](https://GitHub.com/QmDeve/QmBlurView/)
[![GitLab](https://img.shields.io/badge/GitLab-Repository-orange?logo=gitlab)](https://gitlab.com/QmDeve/QmBlurView)

[![Telegram](https://img.shields.io/badge/Telegram%20Group-QmDeves-blue.svg?logo=telegram)](https://t.me/QmDeves)

[![License](https://img.shields.io/github/license/QmDeve/QmBlurView.svg?logo=github&color=blue&label=License)](https://github.com/QmDeve/QmBlurView/blob/master/LICENSE)
[![Android](https://img.shields.io/badge/Android-5.0%2B-brightgreen.svg)](https://developer.android.com)

[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central%20Version)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)
[![JitPack Latest Version](https://jitpack.io/v/com.qmdeve/QmBlurView.svg?label=JitPack%20Latest%20Version)](https://jitpack.io/#com.qmdeve/QmBlurView)
[![GitHub Release Version](https://img.shields.io/github/release/QmDeve/QmBlurView?label=GitHub%20Release%20Version)](https://github.com/QmDeve/QmBlurView/releases)

<br>

English | [简体中文](./README_zh.md)

</div>

---

## Introduction

**QmBlurView** is a high-performance Android UI library that provides real-time, dynamic blur effects (frosted glass). It leverages native C++ code for efficient image processing and offers a comprehensive suite of blurred UI components to enhance your app's design.

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

## Integration

### Maven Central Integration method (recommended)

[![Maven Central Version](https://img.shields.io/maven-central/v/com.qmdeve.blurview/core?label=Maven%20Central%20Version)](https://central.sonatype.com/artifact/com.qmdeve.blurview/core)

Add the dependencies to your module's `build.gradle` file:

```gradle
dependencies {
    // Core Library (Required)
    implementation 'com.qmdeve.blurview:core:1.0.6'

    // Navigation Support (Optional)
    implementation 'com.qmdeve.blurview:navigation:1.0.6'

    // Image Loading Transformations (Optional - Glide/Picasso)
    implementation 'com.qmdeve.blurview:transform:1.0.6'
}
```

### JitPack Integration method

[![JitPack Latest Version](https://jitpack.io/v/com.qmdeve/QmBlurView.svg?label=JitPack%20Latest%20Version)](https://jitpack.io/#com.qmdeve/QmBlurView)

**1.Add the repository to the project's `settings.gradle` file:**

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

**2.Add the dependencies to your module's `build.gradle` file:**

```gradle
dependencies {
    // Core Library (Required)
    implementation 'com.qmdeve.QmBlurView:QmBlurView:v1.0.6'
	
    // Navigation Support (Optional)
    implementation 'com.qmdeve.QmBlurView:Navigation:v1.0.6'
	
    // Image Loading Transformations (Optional - Glide/Picasso)
    implementation 'com.qmdeve.QmBlurView:Transform:v1.0.6'
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

## Documentation
**Detailed Documentation；[https://blurview.qmdeve.com](https://blurview.qmdeve.com)**

## Screenshots

| BlurView | BlurButtonView | ProgressiveBlurView |
|:---:|:---:|:---:|
| <img src="./img/blurview.jpg" width="250"/> | <img src="./img/blurButton.jpg" width="250"/> | <img src="./img/progressiveBlurView.jpg" width="250"/> |

| BlurTitleBarView | BlurSwitchButtonView | BlurBottomNavigationView |
|:---:|:---:|:---:|
| <img src="./img/blurTitlebarView.jpg" width="250"/> | <img src="./img/blurSwitchButton_true.jpg" width="250"/> | <img src="./img/blurBottomNavigation.jpg" width="250"/> |

## Star History

[![Star History](https://starchart.qmdeve.com/QmDeve/QmBlurView.svg?variant=adaptive)](https://starchart.qmdeve.com/QmDeve/QmBlurView)

## Contributors

<a href="https://github.com/QmDeve/QmBlurView/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=QmDeve/QmBlurView" alt="Contributors"/>
</a>

## Other Projects

- **[AndroidLiquidGlassView](https://github.com/QmDeve/AndroidLiquidGlassView)**
- **[Qm Authenticator for Android](https://github.com/Rouneant/Qm-Authenticator-for-Android)**

## License

```text
Copyright ©️ 2025 QmDeve

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
