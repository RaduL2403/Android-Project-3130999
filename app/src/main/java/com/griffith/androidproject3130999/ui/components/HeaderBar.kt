// Radu Lupu 3130999

package com.griffith.androidproject3130999.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.griffith.androidproject3130999.data.AppRepository
import com.griffith.androidproject3130999.data.Screen

//Header bar displaying app title, streak counter, auth toggle badge, and profile avatar
@Composable
fun HeaderBar( modifier: Modifier = Modifier){

    val profile = AppRepository.userProfile
    val isLoggedIn = AppRepository.isLoggedIn

    Row( modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        // App title
        Text(
            text = "Exotic Hunt",
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF111827))

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            // Auth toggle badge
            Box(modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isLoggedIn) Color(0xFFE8F5E9) else Color(0xFFFFEBEE))
                    .clickable { AppRepository.toggleAuth() }
                    .padding(horizontal = 8.dp, vertical = 4.dp)){
                Text (
                    text = if (isLoggedIn) "User" else "Guest",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isLoggedIn) Color(0xFF2E7D32) else Color(0xFFC62828))
            }

            // Streak counter badge
            Row( modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFFFF3E0))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "🔥", fontSize = 14.sp) //cool emoji
                Text(text = if (isLoggedIn) "${profile.streakCount}" else "0",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE65100),
                    modifier = Modifier.padding(start = 4.dp)
                )

            }

            // Profile avatar button (Navigates to Profile screen)
            Box(modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0))
                    .clickable { AppRepository.navigateTo(Screen.PROFILE) },
                contentAlignment = Alignment.Center) {

                Text(
                    text = if (isLoggedIn) "AC" else "?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF424242)
                )
            }
        }

    }

}
