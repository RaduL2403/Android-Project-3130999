// Radu Lupu 3130999

package com.griffith.androidproject3130999.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.griffith.androidproject3130999.data.CarSpot
import com.griffith.androidproject3130999.data.DailyTask
import com.griffith.androidproject3130999.data.Screen
import com.griffith.androidproject3130999.ui.components.NoAccountCard
import com.griffith.androidproject3130999.ui.components.RarityBadge
import com.griffith.androidproject3130999.ui.components.rarityBorder

// Main Activity feed screen showing weather, featured spot, daily tasks checklist, and latest collection
@Composable
fun ActivityScreen( modifier: Modifier = Modifier ){


    val weather = AppRepository.weather
    val spot = AppRepository.spotOfTheDay
    val tasks = AppRepository.tasks
    val captures = AppRepository.spots.take(3)
    val isLoggedIn = AppRepository.isLoggedIn

    val completedCount = tasks.count { it.isCompleted}
    val totalCount = tasks.size

    Column( modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        //Weather Banner Card
        Row(modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE0F2FE), RoundedCornerShape(20.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFE0F2FE)),
                contentAlignment = Alignment.Center) {

                Text(text = "🌧️", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = weather.dateString,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B))
                Text(
                    text = "${weather.condition} · ${weather.tempC}°C · ${weather.description}",
                    fontSize = 13.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        // Spot of the Day Hero Card
        Column( modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF1E293B))
                .rarityBorder(spot.rarity)
                .clickable { AppRepository.openSpotDetail(spot) }) {
            Box( modifier = Modifier.fillMaxWidth()
                    .height(220.dp)
                    .background(Color(0xFF0F172A)),
                contentAlignment = Alignment.Center) {

                Column( horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "🏎️", fontSize = 72.sp) //car
                    Text(
                        text = "${spot.brand} ${spot.model}",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.7f))
                }

                //Spot of the Day tag
                Box( modifier = Modifier.align(Alignment.TopStart)
                        .padding(14.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = 12.dp, vertical = 6.dp)) {

                    Text( text = "SPOT OF THE DAY",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)

                }

                RarityBadge( rarity = spot.rarity,
                    modifier = Modifier.align(Alignment.TopEnd).padding(14.dp)
                )

            }

            Column(modifier = Modifier.padding(18.dp)) {

                Text(text = spot.brand.uppercase(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF00E676))
                Text( text = spot.model,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text( text = "📍 ${spot.location} · ${spot.timestamp}",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.7f))

            }
        }

        //Today's Task Checklist Card
        Column(modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(20.dp)) {

            Text(text = "TODAY'S TASK",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF64748B)
            )

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text( text = "Find these cars in the rain",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A))

                // Dynamic progress wheel indicator
                Box(modifier = Modifier.size(44.dp),
                    contentAlignment = Alignment.Center) {

                    CircularProgressIndicator(
                        progress = { if (totalCount >0) completedCount.toFloat() /totalCount else 0f },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF2962FF),
                        trackColor = Color(0xFFE2E8F0),
                        strokeWidth = 4.dp
                    )
                    Text( text = "$completedCount/$totalCount",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!isLoggedIn ){
                NoAccountCard(featureName = "daily tasks")
            }
            else{
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    tasks.forEach {task ->
                        TaskItemRow(
                            task = task,
                            onToggle = { AppRepository.toggleTaskCompletion(task.id) })
                    }
                }
            }

        }

        // Latest Captures Preview Section
        Column(modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(20.dp)) {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Column {
                    Text( text = "MY COLLECTION",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF64748B))
                    Text(text = "Latest captures",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A))
                }

                Text( text = "View all >",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2962FF),
                    modifier = Modifier.clickable { AppRepository.navigateTo(Screen.ALBUM) })
            }

            Spacer(modifier = Modifier.height(16.dp))

            if ( !isLoggedIn) {
                NoAccountCard(featureName = "personal catches")
            }
            else{
                Column( verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    captures.forEach { catchItem -> CaptureRowItem(spot = catchItem, onClick = { AppRepository.openSpotDetail(catchItem) })
                    }
                }
            }
        }
    }

}

// Individual task row with interactive checkbox
@Composable
private fun TaskItemRow(task: DailyTask, onToggle: () -> Unit) {

    Row(modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF8FAFC))
            .clickable { onToggle() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(42.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE2E8F0)),
                contentAlignment = Alignment.Center) {
                Text(text = "🏎️", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column{
                Text(text = task.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Text(text = task.description,
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )
            }

        }

        Checkbox( checked = task.isCompleted,
            onCheckedChange = { onToggle() },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF2962FF),
                uncheckedColor = Color(0xFFCBD5E1)
            )
        )
    }
}


// Individual capture item row in Latest Captures list
@Composable
private fun CaptureRowItem( spot: CarSpot, onClick: () -> Unit) {

    Row(modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF8FAFC))
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E293B)),
                contentAlignment = Alignment.Center) {
                Text(text = "🏎️", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {

                Text( text = spot.brand,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A))
                Text(text = spot.model,
                    fontSize = 13.sp,
                    color = Color(0xFF64748B))

            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RarityBadge(rarity = spot.rarity)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "›", fontSize = 20.sp, color = Color(0xFF94A3B8))
        }
    }


}
