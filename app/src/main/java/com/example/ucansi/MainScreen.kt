package com.example.ucansi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucansi.ui.theme.UcansiTheme

// Couleurs ultra-vives
val UcansiPink = Color(0xFFFF007F)
val UcansiPurple = Color(0xFF9D00FF)
val UcansiBlue = Color(0xFF007BFF)
val UcansiCyan = Color(0xFF00FBFF)
val UcansiGreen = Color(0xFF00FF88)
val UcansiYellow = Color(0xFFFFFF00)
val UcansiOrange = Color(0xFFFF8800)

val RainbowColors = listOf(
    UcansiPink, UcansiPurple, UcansiBlue, UcansiCyan, UcansiGreen, UcansiYellow, UcansiOrange, UcansiPink
)

val FullRainbowBrush = Brush.sweepGradient(colors = RainbowColors)

@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = { UcansiBottomNavigation() },
        containerColor = Color.Black,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            // Fond sombre pour faire ressortir les couleurs
            Box(modifier = Modifier.fillMaxSize().background(Color(0xFF050505)))

            // Logo Top Arc-en-ciel
            Text(
                text = "UCANSI",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 48.dp),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Black,
                    brush = Brush.linearGradient(RainbowColors),
                    letterSpacing = 4.sp
                )
            )

            // Boutons Droite
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 24.dp, end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ProfileWithPlus()
                RainbowActionIcon(Icons.Default.Favorite, "1.2M")
                RainbowActionIcon(Icons.AutoMirrored.Filled.Comment, "12.5K")
                RainbowActionIcon(Icons.Default.Share, "102")
            }

            // Info Bas
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 24.dp, end = 80.dp)
            ) {
                Text("@Sarah_Neon", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Vivez en couleurs ! 🌈 #Rainbow #Ucansi", color = Color.White.copy(0.9f))
            }
        }
    }
}

@Composable
fun RainbowActionIcon(icon: ImageVector, count: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .background(FullRainbowBrush, CircleShape)
                .padding(3.dp)
                .background(Color.Black, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // L'icône elle-même est en dégradé !
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(FullRainbowBrush, blendMode = BlendMode.SrcAtop)
                        }
                    },
                tint = Color.Unspecified // Important pour le dégradé
            )
        }
        Text(count, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ProfileWithPlus() {
    Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .background(FullRainbowBrush, CircleShape)
                .padding(2.dp)
                .background(Color.DarkGray, CircleShape)
        )
        Box(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 4.dp)
                .background(UcansiPink, CircleShape)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Add, null, tint = Color.White, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun UcansiBottomNavigation() {
    NavigationBar(containerColor = Color.Black, tonalElevation = 0.dp) {
        NavigationBarItem(
            icon = { 
                Box(modifier = Modifier.size(38.dp).background(FullRainbowBrush, RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.PlayArrow, null, tint = Color.White)
                }
            },
            label = { Text("Feed", color = Color.White) },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FavoriteBorder, null, tint = UcansiPink) },
            label = { Text("Likes", color = Color.White) },
            selected = false, onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ChatBubbleOutline, null, tint = UcansiPurple) },
            label = { Text("Chat", color = Color.White) },
            selected = false, onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.PersonOutline, null, tint = UcansiCyan) },
            label = { Text("Profil", color = Color.White) },
            selected = false, onClick = {}
        )
    }
}

@Preview
@Composable
fun Preview() { UcansiTheme { MainScreen() } }
