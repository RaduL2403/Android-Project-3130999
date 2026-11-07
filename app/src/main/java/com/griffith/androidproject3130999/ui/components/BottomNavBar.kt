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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF111827))
            .navigationBarsPadding()
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Activity tab
            NavItem(
                title = "Activity",
                icon = "📈",
                isSelected = activeScreen == Screen.ACTIVITY,
                onClick = { onScreenSelected(Screen.ACTIVITY) },
                modifier = Modifier.weight(1f)
            )

            // Map tab
            NavItem(
                title = "Map",
                icon = "🗺️",
                isSelected = activeScreen == Screen.MAP,
                onClick = { onScreenSelected(Screen.MAP) },
                modifier = Modifier.weight(1f)
            )

            // Central green Camera FAB
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF00E676))
                        .clickable { onScreenSelected(Screen.CAMERA) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "📷", fontSize = 22.sp)
                }
            }

            // Album tab
            NavItem(
                title = "Album",
                icon = "🖼️",
                isSelected = activeScreen == Screen.ALBUM,
                onClick = { onScreenSelected(Screen.ALBUM) },
                modifier = Modifier.weight(1f)
            )

            // Profile tab
            NavItem(
                title = "Profile",
                icon = "👤",
                isSelected = activeScreen == Screen.PROFILE,
                onClick = {onScreenSelected(Screen.PROFILE) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

//component for navigation tab items
@Composable
private fun NavItem( title: String, icon: String,
    isSelected: Boolean,
    onClick: () ->Unit,
    modifier: Modifier = Modifier ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }.padding(vertical = 4.dp)
    ){
        // Active green line indicator above icon
        Box(
            modifier = Modifier.height(3.dp)
                .width(28.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(if (isSelected) Color(0xFF00E676) else Color.Transparent))

        Text( text = icon,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
        Text( text = title,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.White else Color(0xFF9CA3AF))

    }


}
