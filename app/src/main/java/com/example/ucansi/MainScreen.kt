package com.example.ucansi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucansi.ui.theme.UcansiTheme

// Colors
val RainbowColors = listOf(
    Color(0xFFFF007F), Color(0xFF9D00FF), Color(0xFF007BFF), 
    Color(0xFF00FBFF), Color(0xFF00FF88), Color(0xFFFFFF00), Color(0xFFFF8800)
)
val RainbowBrush = Brush.sweepGradient(RainbowColors)
val GreenMoney = Color(0xFF2ECC71)

@Composable
fun MainScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // --- TOP BAR ---
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
            Text("Eledji com.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Surface(color = Color.Red, shape = RoundedCornerShape(4.dp)) {
                Text("Live", color = Color.White, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), fontSize = 12.sp)
            }
        }

        // --- RIGHT SIDEBAR ---
        Column(
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            MoneyIcon()
            MoneyIcon()
            Icon(Icons.Default.FavoriteBorder, null, tint = Color.White, modifier = Modifier.size(30.dp))
            Icon(Icons.Default.ChatBubbleOutline, null, tint = Color.White, modifier = Modifier.size(30.dp))
            Icon(Icons.Default.Share, null, tint = Color.White, modifier = Modifier.size(30.dp))
            SocialIcon(Color(0xFF25D366), Icons.Default.Call) // WhatsApp dummy
            SocialIcon(Color(0xFFE1306C), Icons.Default.CameraAlt) // Insta dummy
        }

        // --- FLOATING COMMENTS ---
        Column(
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CommentBubble("Wow! ❤️🔥")
            CommentBubble("Dere! ❤️🥰")
            Text("So cool.", color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(start = 8.dp))
        }

        // --- BOTTOM NAV & PLUS BUTTON ---
        Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(80.dp).background(Color.Black.copy(0.5f))) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Accueil", color = Color.White, fontSize = 12.sp)
                Text("Découvrir", color = Color.White.copy(0.6f), fontSize = 12.sp)
                Spacer(Modifier.width(60.dp)) // Space for center button
                Text("Messages", color = Color.White.copy(0.6f), fontSize = 12.sp)
                Text("Compte", color = Color.White.copy(0.6f), fontSize = 12.sp)
            }

            // The Glowing Plus Button
            Box(contentAlignment = Alignment.Center, modifier = Modifier.align(Alignment.Center).offset(y = (-10).dp)) {
                // Glow effect
                Box(modifier = Modifier.size(65.dp).blur(10.dp).background(RainbowBrush, CircleShape))
                // Button
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

@Preview
@Composable
fun Preview() { UcansiTheme { MainScreen() } }
