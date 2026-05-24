package com.example.ucansi

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioToolbarButton
import androidx.media3.ui.PlayerView
import com.example.ucansi.ui.theme.UcansiTheme

// Colors
val RainbowColors = listOf(
    Color(0xFFFF007F), Color(0xFF9D00FF), Color(0xFF007BFF), 
    Color(0xFF00FBFF), Color(0xFF00FF88), Color(0xFFFFFF00), Color(0xFFFF8800)
)
val RainbowBrush = Brush.sweepGradient(RainbowColors)
val GreenMoney = Color(0xFF2ECC71)

data class Reel(val id: Int, val videoUrl: String, val author: String, val description: String)

val SampleReels = listOf(
    Reel(1, "https://assets.mixkit.co/videos/preview/mixkit-girl-in-neon-lighting-in-the-night-city-2189-large.mp4", "Sarah_Neon", "Vivez en couleurs ! 🌈 #Rainbow #Ucansi"),
    Reel(2, "https://assets.mixkit.co/videos/preview/mixkit-winter-fashion-shoot-in-a-snowy-forest-34448-large.mp4", "Forest_Model", "Winter is coming... ❄️ #Fashion #Forest"),
    Reel(3, "https://assets.mixkit.co/videos/preview/mixkit-womans-feet-splashing-in-the-pool-1221-large.mp4", "Summer_Vibes", "Pool day! 💦 #Summer #Ucansi")
)

@Composable
fun MainScreen() {
    val pagerState = rememberPagerState(pageCount = { SampleReels.size })

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            ReelItem(reel = SampleReels[page], isVisible = pagerState.currentPage == page)
        }

        // --- TOP BAR (Overlay) ---
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
            Text("Eledji com.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Surface(color = Color.Red, shape = RoundedCornerShape(4.dp)) {
                Text("Live", color = Color.White, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), fontSize = 12.sp)
            }
        }

        // --- BOTTOM NAV & PLUS BUTTON ---
        Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(90.dp).background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Accueil", color = Color.White, fontSize = 12.sp)
                Text("Découvrir", color = Color.White.copy(0.6f), fontSize = 12.sp)
                Spacer(Modifier.width(60.dp))
                Text("Messages", color = Color.White.copy(0.6f), fontSize = 12.sp)
                Text("Compte", color = Color.White.copy(0.6f), fontSize = 12.sp)
            }

            // Glowing Plus Button
            Box(contentAlignment = Alignment.Center, modifier = Modifier.align(Alignment.Center).offset(y = (-15).dp)) {
                Box(modifier = Modifier.size(65.dp).blur(10.dp).background(RainbowBrush, CircleShape))
                Box(
                    modifier = Modifier.size(55.dp).border(3.dp, RainbowBrush, CircleShape).background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Add, null, tint = Color.Black, modifier = Modifier.size(35.dp))
                }
            }
        }
    }
}

@Composable
fun ReelItem(reel: Reel, isVisible: Boolean) {
    Box(modifier = Modifier.fillMaxSize()) {
        VideoPlayer(videoUrl = reel.videoUrl, playWhenReady = isVisible)

        // Right Sidebar
        Column(
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp, top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            MoneyIcon()
            MoneyIcon()
            Icon(Icons.Default.FavoriteBorder, null, tint = Color.White, modifier = Modifier.size(35.dp))
            Icon(Icons.Default.ChatBubbleOutline, null, tint = Color.White, modifier = Modifier.size(35.dp))
            Icon(Icons.Default.Share, null, tint = Color.White, modifier = Modifier.size(35.dp))
            SocialIcon(Color(0xFF25D366), Icons.Default.Call)
            SocialIcon(Color(0xFFE1306C), Icons.Default.CameraAlt)
        }

        // Info & Comments
        Column(
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("@${reel.author}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(reel.description, color = Color.White, fontSize = 14.sp)
            
            Spacer(Modifier.height(8.dp))
            CommentBubble("Wow! ❤️🔥")
            CommentBubble("Dere! ❤️🥰")
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(videoUrl: String, playWhenReady: Boolean) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ONE
            setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
            prepare()
        }
    }

    DisposableEffect(playWhenReady) {
        exoPlayer.playWhenReady = playWhenReady
        onDispose { exoPlayer.release() }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = false
                resizeMode = androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun MoneyIcon() {
    Box(modifier = Modifier.size(45.dp).background(GreenMoney, CircleShape), contentAlignment = Alignment.Center) {
        Text("$", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}

@Composable
fun SocialIcon(color: Color, icon: ImageVector) {
    Box(modifier = Modifier.size(40.dp).background(color, CircleShape), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = Color.White, modifier = Modifier.size(22.dp))
    }
}

@Composable
fun CommentBubble(text: String) {
    Surface(color = Color.Black.copy(0.4f), shape = RoundedCornerShape(12.dp)) {
        Text(text, color = Color.Yellow, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 13.sp)
    }
}
