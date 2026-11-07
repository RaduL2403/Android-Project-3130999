// Radu Lupu 3130999

package com.griffith.androidproject3130999.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.griffith.androidproject3130999.data.AppRepository
import com.griffith.androidproject3130999.data.CarSpot
import com.griffith.androidproject3130999.data.Screen

//Modal detail popup dialog displaying full car spot metadata and editable specs
@Composable
fun CarDetailDialog( spot: CarSpot, onDismiss: () -> Unit) {

    val context = LocalContext.current

    // Local editable fields
    var engine by remember {mutableStateOf(spot.engineType) }
    var body by remember {mutableStateOf(spot.bodyType) }
    var horsepower by remember { mutableStateOf(spot.horsepower) }
    var note by remember { mutableStateOf(spot.note) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface( modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(24.dp) )
                .border(1.dp, Color(0xFF334155), RoundedCornerShape(24.dp)),
            color = Color(0xFF0F172A)) {

            Column(modifier = Modifier.padding(20.dp).verticalScroll(rememberScrollState())) {

                // Header row with close button
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    RarityBadge(rarity = spot.rarity)
                    Box( modifier = Modifier.clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF1E293B))
                            .clickable { onDismiss() }
                            .padding(horizontal = 10.dp, vertical = 4.dp)) {

                        Text(text = "✕", fontSize = 16.sp, color = Color.White)
                    }

                }

                Spacer( modifier = Modifier.height(14.dp) )

                // Car Image Header
                Box(modifier = Modifier.fillMaxWidth().height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1E293B)),
                    contentAlignment = Alignment.Center){

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(text = "🏎️", fontSize = 64.sp)
                        Text(text = "${spot.brand} ${spot.model}",
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.7f))

                    }


                }

                Spacer(modifier = Modifier.height(16.dp))

                // title and Model
                Text(text = spot.brand.uppercase(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF00E676))
                Text(text = "${spot.model} ${spot.trim}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)

                Spacer( modifier = Modifier.height(8.dp))

                // Location and Weather metadata
                Text(text = "📍 ${spot.location} ·${spot.timestamp}",
                    fontSize = 13.sp,
                    color = Color(0xFF94A3B8)
                )
                Text(text = "🌤️ Weather: ${spot.weatherDescription}",
                    fontSize = 13.sp,
                    color = Color(0xFF94A3B8)
                )
                Text(text = "👤 Spotted by: ${spot.spotterName}",
                    fontSize = 13.sp,
                    color = Color(0xFF94A3B8))

                Spacer(modifier = Modifier.height(16.dp))

                // Editable Specification Fields
                Text( text = "SPECIFICATIONS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField ( value = engine,
                    onValueChange = { engine = it },
                    label = { Text("Engine Type", color = Color(0xFF94A3B8))  },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF00E676),
                        unfocusedBorderColor = Color(0xFF334155) )

                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = body,
                        onValueChange = { body = it },
                        label = { Text("Body Type", color = Color(0xFF94A3B8)) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFF00E676),
                            unfocusedBorderColor = Color(0xFF334155)

                        )
                    )
                    OutlinedTextField(value = horsepower,
                        onValueChange = { horsepower = it },
                        label = { Text("Power", color = Color(0xFF94A3B8)) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFF00E676),
                            unfocusedBorderColor = Color(0xFF334155)
                        )

                    )

                }

                Spacer( modifier = Modifier.height( 8.dp ))

                OutlinedTextField( value = note,
                    onValueChange = { note = it },
                    label = { Text("Spotter Note", color = Color(0xFF94A3B8)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF00E676),
                        unfocusedBorderColor = Color(0xFF334155)
                    )

                )

                Spacer(modifier = Modifier.height(18.dp))

                //action Buttons
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Show on map button
                    Button( onClick = {
                            try {
                                val mapIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:${spot.latitude},${spot.longitude}?q=${Uri.encode(spot.location)}")
                                )
                                context.startActivity(mapIntent)

                            } catch (_: Exception) {
                                AppRepository.navigateTo(Screen.MAP)
                            }

                            onDismiss()

                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                        shape = RoundedCornerShape(12.dp)) {

                        Text("🗺️ Show on Map", fontSize = 12.sp, color = Color.White)
                    }

                    // Share button (System Share implicit intent)
                    Button( onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Check out this ${spot.brand} ${spot.model} spotted at ${spot.location} on Exotic Hunt!"
                                )
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Car Spot")) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("📤 Share", fontSize = 12.sp, color = Color.White)
                    }

                }

                Spacer(modifier = Modifier.height(12.dp))

                // Save Changes button
                Button( onClick = { AppRepository.updateSpotDetails(
                            spotId = spot.id,
                            newEngine = engine,
                            newBody = body,
                            newHp = horsepower,
                            newNote = note )
                        onDismiss()

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)),
                    shape = RoundedCornerShape(12.dp)){
                    Text(text = "Save Details",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A))

                }
            }

        }
    }

}

