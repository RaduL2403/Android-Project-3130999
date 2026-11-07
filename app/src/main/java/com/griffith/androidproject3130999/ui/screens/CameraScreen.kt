// Radu Lupu 3130999


package com.griffith.androidproject3130999.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.androidproject3130999.data.AppRepository
import com.griffith.androidproject3130999.data.CarRarity
import com.griffith.androidproject3130999.data.CarSpot
import com.griffith.androidproject3130999.data.Screen

// Camera Screen with live viewfinder and shutter controls
@Composable
fun CameraScreen(
    onTriggerCameraIntent: () -> Unit,
    onTriggerGalleryIntent: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Review mode state (false = live camera viewfinder, true = post capture review screen  )
    var isReviewingCapture by remember { mutableStateOf(false) }

    if (!isReviewingCapture) {
        // Live Camera Viewfinder
        Box(modifier = modifier.fillMaxSize().background(Color(0xFF0F172A) )) {
            // Viewfinder Preview Canvas
            Box(
                modifier = Modifier.fillMaxSize().padding(bottom = 90.dp).background(Color(0xFF1E293B)),
                contentAlignment = Alignment.Center

            ){

                //Car subject preview
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text( text = "🏎️", fontSize = 96.sp)
                    Text( text = "Align car inside reticle frame",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.7f))

                }

                // Viewfinder target
                Box(
                    modifier = Modifier.size(260.dp, 180.dp).border(2.dp, Color(0xFF00E676), RoundedCornerShape(16.dp))
                )

                // Top Tag: Camera preview · Demo
                Box(
                    modifier = Modifier.align(Alignment.TopStart)
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = 14.dp, vertical = 6.dp)

                ){
                    Text( text = "Camera preview · Demo",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )


                }

                //Close Button (✕)
                Box(
                    modifier = Modifier.align(Alignment.TopEnd)
                        .padding(20.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.6f))
                        .clickable { AppRepository.navigateTo(Screen.ACTIVITY) },
                    contentAlignment = Alignment.Center
                ) {

                    Text(text = "✕", fontSize = 16.sp, color = Color.White)

                }


            }

            // Bottom Shutter Controls Bar
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(90.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFF0B0F17))
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // System Camera Launcher button (button that will launch the phone camera instead of the app one )
                    Box(
                        modifier = Modifier.size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1E293B))
                            .clickable { onTriggerCameraIntent() },
                        contentAlignment = Alignment.Center

                    ) {

                        Text(text = "📱", fontSize = 20.sp)

                    }

                    // Large shutter button
                    Box(
                        modifier = Modifier.size(68.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(4.dp, Color(0xFF00E676), CircleShape)
                            .clickable { isReviewingCapture = true },
                        contentAlignment = Alignment.Center

                    ) {
                        Box(
                            modifier = Modifier.size(54.dp).clip(CircleShape).background(Color.White)
                        )

                    }

                    // Gallery Picker Button
                    Box(
                        modifier = Modifier.size(44.dp).clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF1E293B))
                            .clickable { onTriggerGalleryIntent() },
                        contentAlignment = Alignment.Center
                    ) {

                        Text(text = "🖼️", fontSize = 20.sp)


                    }
                }

            }
        }

    } else{
        // Post-Capture Review screen
        Column(
            modifier =modifier.fillMaxSize()
                .background(Color(0xFF0B0F17))
                .verticalScroll(rememberScrollState())
        ){
            // Captured Image Header Area
            Box(
                modifier = Modifier.fillMaxWidth().height(320.dp).background(Color(0xFF1E293B)),
                contentAlignment = Alignment.Center

            ) {
                Text(text ="🏎️", fontSize =100.sp)

                //Top Tag: review capture
                Box(
                    modifier = Modifier.align(Alignment.TopStart)
                        .padding(20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(text = "Review your capture",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    )
                }

                //Close Button
                Box(
                    modifier = Modifier.align(Alignment.TopEnd).padding(20.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.6f))
                        .clickable { isReviewingCapture = false },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "✕", fontSize = 16.sp, color = Color.White)

                }
            }

            // AI Identification summary
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp)

            ) {
                Text(text = "CAPTURE DETAILS · DEMO IDENTIFICATION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF94A3B8) )


                Spacer(modifier = Modifier.height(6.dp))

                Text( text = "Lamborghini Huracan",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(18.dp))

                // Metadata Grid
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Location", fontSize = 12.sp, color = Color(0xFF64748B))
                        Text(
                            text = "Grafton Street, Dublin",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(text ="Sample location", fontSize = 11.sp, color = Color(0xFF64748B))
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Date", fontSize = 12.sp, color = Color(0xFF64748B))
                        Text(text= "October 25, 2026",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White)

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Time", fontSize = 12.sp, color = Color(0xFF64748B))
                        Text( text ="11:09 PM",
                            fontSize =14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Weather", fontSize = 12.sp, color = Color(0xFF64748B))
                        Text(
                            text = "Light rain · 14°C",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Text(text = "Sample weather", fontSize = 11.sp, color = Color(0xFF64748B))
                    }
                }

                Spacer(modifier = Modifier.height(28.dp) )

                // Bottom Action Buttons: Delete & Retake vs Save with Details
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Delete & retake button
                    Button(
                        onClick = { isReviewingCapture = false },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        border = BorderStroke(1.dp, Color(0xFF334155)),
                        shape = RoundedCornerShape(12.dp)

                    ) {
                        Text(
                            text = "Delete & retake",
                            fontSize = 13.sp,
                            color = Color.White

                        )
                    }

                    //Save with details button
                    Button(
                        onClick ={
                            val newSpot =CarSpot(
                                id = "capture_${System.currentTimeMillis()}",
                                brand = "Lamborghini",
                                model = "Huracan EVO",
                                trim = "LP 640-4",
                                rarity = CarRarity.SUPERCAR,
                                location = "Grafton Street, Dublin",
                                timestamp = "Just now",
                                weatherDescription = "Light rain · 14°C",
                                spotterName = AppRepository.userProfile.name,
                                spotterInitials = "JO",
                                note = "Captured live on Grafton Street, Dublin."
                            )

                            AppRepository.addNewSpot(newSpot)
                            AppRepository.navigateTo(Screen.ALBUM)
                        },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2962FF)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Save with details",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                    }


                }
            }
        }

    }
}
