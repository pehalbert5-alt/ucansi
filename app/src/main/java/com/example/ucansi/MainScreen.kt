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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucansi.ui.theme.UcansiTheme

// Brand Colors
val UcansiBlue = Color(0xFF4A90E2)
val UcansiPink = Color(0xFFFF4D6D)
val UcansiPurple = Color(0xFF9B51E0)
val UcansiOrange = Color(0xFFFF8C42)
val UcansiYellow = Color(0xFFFFD166)
val UcansiCyan = Color(0xFF06D6A0)

// Rainbow Gradients
val RainbowGradient = Brush.linearGradient(
    colors = listOf(
        UcansiPink,
        UcansiPurple,
        UcansiBlue,
        UcansiCyan,
        UcansiYellow,
        UcansiOrange,
        UcansiPink
    )
)

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
            // Placeholder for Video
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0A0A0A))
            ) {
                Text(
                    "Video Content Placeholder",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.DarkGray
                )
            }

            // Top Logo
            UcansiTopLogo(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 48.dp)
            )

            // Right side buttons
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 24.dp, end = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ProfileWithPlus()
                
                ActionIcon(
                    icon = Icons.Default.Favorite,
                    label = "Like",
                    count = "1.2M",
                    gradient = RainbowGradient
                )
                
                ActionIcon(
                    icon = Icons.AutoMirrored.Filled.Comment,
                    label = "Comment",
                    count = "12.5K",
                    gradient = RainbowGradient
                )
                
                ActionIcon(
                    icon = Icons.Default.Share,
                    label = "Share",
                    count = "102",
                    gradient = RainbowGradient
                )
            }

            // Bottom left info
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 24.dp, end = 80.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "@CreatorName",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = UcansiBlue,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Text(
                    text = "Check out my story! #Dating #Chat #Ucansi",
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.MusicNote, null, tint = Color.White, modifier = Modifier.size(14.dp))
                    Text(
                        text = "Original Audio - Ucansi Beats",
                        color = Color.White,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun UcansiTopLogo(modifier: Modifier = Modifier) {
    Text(
        text = "UCANSI",
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.ExtraBold,
            brush = RainbowGradient,
            letterSpacing = 2.sp
        )
    )
}

@Composable
fun ActionIcon(
    icon: ImageVector,
    label: String,
    count: String,
    gradient: Brush
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(gradient)
                .padding(2.dp)
                .background(Color.Black.copy(alpha = 0.2f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = count,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ProfileWithPlus() {
    Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center) {
        // Profile Image Placeholder with Rainbow border
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(RainbowGradient)
                .padding(2.dp)
                .background(Color.Gray, CircleShape)
        )
        // Plus Button
        Box(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 4.dp)
                .clip(CircleShape)
                .background(UcansiPink),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun UcansiBottomNavigation() {
    NavigationBar(
        containerColor = Color.Black.copy(alpha = 0.9f),
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            icon = { 
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            brush = RainbowGradient,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
                }
            },
            label = { Text("Feed", color = Color.White, fontWeight = FontWeight.ExtraBold) },
            selected = true,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                indicatorColor = Color.Transparent
            )
        )
        
        val itemModifier = Modifier.size(28.dp)
        
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.FavoriteBorder, "Likes", itemModifier, tint = UcansiPink) },
            label = { Text("Likes", color = Color.White) },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.MailOutline, "Messages", itemModifier, tint = UcansiPurple) },
            label = { Text("Chat", color = Color.White) },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.PersonOutline, "Profile", itemModifier, tint = UcansiCyan) },
            label = { Text("Profile", color = Color.White) },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    UcansiTheme {
        MainScreen()
    }
}
