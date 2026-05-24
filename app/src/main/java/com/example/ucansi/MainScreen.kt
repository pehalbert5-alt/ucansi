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
                    gradient = Brush.verticalGradient(listOf(UcansiBlue, UcansiPink))
                )
                
                ActionIcon(
                    icon = Icons.AutoMirrored.Filled.Comment,
                    label = "Comment",
                    count = "12.5K",
                    gradient = Brush.verticalGradient(listOf(UcansiPink, UcansiPurple))
                )
                
                ActionIcon(
                    icon = Icons.Default.Share,
                    label = "Share",
                    count = "102",
                    gradient = Brush.verticalGradient(listOf(UcansiPurple, UcansiBlue))
                )
            }

            // Bottom left info
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 24.dp, end = 80.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "@CreatorName",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Check out my story! #Dating #Chat #Ucansi",
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun UcansiTopLogo(modifier: Modifier = Modifier) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(UcansiBlue, UcansiPink)
    )
    Text(
        text = "UCANSI",
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.ExtraBold,
            brush = gradient,
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
                .size(50.dp)
                .clip(CircleShape)
                .background(gradient)
                .padding(1.dp) // Border effect
                .background(Color.Black.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = count,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ProfileWithPlus() {
    Box(modifier = Modifier.size(55.dp), contentAlignment = Alignment.Center) {
        // Profile Image Placeholder
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        // Plus Button
        Box(
            modifier = Modifier
                .size(22.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 2.dp)
                .clip(CircleShape)
                .background(UcansiPink),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun UcansiBottomNavigation() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { 
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            brush = Brush.linearGradient(colors = listOf(UcansiBlue, UcansiPink)),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
                }
            },
            label = { Text("Feed", fontWeight = FontWeight.Bold) },
            selected = true,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = UcansiPink,
                selectedTextColor = UcansiPink,
                indicatorColor = Color.Transparent
            )
        )
        
        val itemModifier = Modifier.size(26.dp)
        
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Likes", modifier = itemModifier, tint = UcansiBlue) },
            label = { Text("Likes") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.MailOutline, contentDescription = "Messages", modifier = itemModifier, tint = UcansiPurple) },
            label = { Text("Messages") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.PersonOutline, contentDescription = "Profile", modifier = itemModifier, tint = Color.Gray) },
            label = { Text("Profile") },
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
