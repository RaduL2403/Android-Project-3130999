// Radu Lupu 3130999
package com.griffith.androidproject3130999.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.androidproject3130999.data.Screen

// Bottom navigation bar with 5 destinations and prominent central Camera button
@Composable
fun BottomNavBar(
    activeScreen: Screen,
    onScreenSelected: (Screen) -> Unit,
    modifier: Modifier = Modifier) {

    Box(modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF111827))
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {

            // Activity tab
            NavItem(
                title = "Activity",
                icon = "📈",
                isSelected = activeScreen == Screen.ACTIVITY,
                onClick = { onScreenSelected(Screen.ACTIVITY) })

            // Map tab
            NavItem(
                title = "Map",
                icon = "🗺️",
                isSelected = activeScreen == Screen.MAP,
                onClick = { onScreenSelected(Screen.MAP)}
            )

            // Central green Camera FAB
            Box(modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00E676))
                    .clickable { onScreenSelected(Screen.CAMERA) },
                contentAlignment = Alignment.Center){
                Text(text = "📷", fontSize = 24.sp)
            }

            // Album tab
            NavItem(
                title = "Album",
                icon = "🖼️",
                isSelected = activeScreen == Screen.ALBUM,
                onClick = { onScreenSelected(Screen.ALBUM) }
            )

            // Profile tab
            NavItem(
                title = "Profile",
                icon = "👤",
                isSelected = activeScreen == Screen.PROFILE,
                onClick = { onScreenSelected(Screen.PROFILE) }
            )
        }
    }
}

// Sub-component for individual bottom navigation tab items
@Composable
private fun NavItem(
    title: String,
    icon: String,
    isSelected: Boolean,
    onClick: () -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }.padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        // Active green line indicator above icon
        Box( modifier = Modifier.height(3.dp)
                .fillMaxWidth(0.6f)
                .clip(RoundedCornerShape(2.dp))
                .background(if (isSelected) Color(0xFF00E676) else Color.Transparent)
        )

        Text( text = icon,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text( text = title,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.White else Color(0xFF9CA3AF)
        )
    }


}
