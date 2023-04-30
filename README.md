<body>
    <h1>SwipeUtils</h1>
    <p>SwipeUtils is a lightweight Android Jetpack Compose library that provides swipe-to-reveal functionality for Composable items, similar to the swipe-to-mute feature found in apps like WhatsApp and Telegram. It allows you to wrap any Composable cell and add multiple buttons on both horizontal sides.</p>
    <h2>Features</h2>
    <ul>
        <li>Wrap any Jetpack Compose cell to implement swipe-to-reveal</li>
        <li>Add multiple buttons on both horizontal sides</li>
        <li>Customizable Composable buttons</li>
    </ul>
    <h2>Installation</h2>
    <p>Add the JitPack repository to your project's build.gradle file:</p>
    <pre><code>allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
</code></pre>
    <p>Add the SwipeUtils dependency to your app's build.gradle file:</p>
    <pre><code>dependencies {
    implementation 'com.github.ohadsa:SwipeUtils:Tag'
}
</code></pre>
    <h2>Usage</h2>
    <p>Wrap your Composable cell with <code>SwappableBox</code>:</p>
    <pre><code>SwappableBox(
    modifier = Modifier.fillMaxWidth(),
    swipeDirection = SwipeDirection.EndToStart,
    endContentWidth = 60.dp,
    endContent = { state ->
        // Add any buttons here
        // The library also provides Icons as Buttons, but you can add any Composable you like
        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = "Delete",
            tint = Color.White,
            background = Color(0xff258750),
            weight = 1f,
            iconSize = 20.dp
        ) {
            // On button clicked
        }
        // Can add more buttons here
    }
) {
    // The content of your Composable cell
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(if (noBackground) Color.Transparent else Color(0xff02488F)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "this is a swipe cell",
            color = Color.Red,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
</code></pre>
    <h2>License</h2>
    <pre><code>Designed and developed by 2023 ohadsaada (Ohad Saada)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
</code></pre>
