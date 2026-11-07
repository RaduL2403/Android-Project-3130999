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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.androidproject3130999.data.AppRepository
import com.griffith.androidproject3130999.data.CarSpot
import com.griffith.androidproject3130999.data.Screen
import com.griffith.androidproject3130999.ui.components.CarDetailDialog
import com.griffith.androidproject3130999.ui.components.RarityBadge
import com.griffith.androidproject3130999.ui.components.rarityBorder

// Dark-themed Album Screen
@Composable
fun AlbumScreen( modifier: Modifier = Modifier) {

    val isLoggedIn = AppRepository.isLoggedIn
    val spots = AppRepository.spots
    val manufacturersCount = spots.map { it.brand.lowercase() }.distinct().size

    // Active detail spot for popup dialog
    var selectedSpot by remember { mutableStateOf<CarSpot?>(null) }

    //group spots by car make
    val groupedSpots = spots.groupBy { it.brand }

    Column(modifier = modifier.fillMaxSize().background(Color(0xFF0F172A))
            .verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        //Header section
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            Column {
                Text( text = "EVERY CATCH HAS A STORY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF00E676))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Your collection",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White )
                    Text( text = ".",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00E676) )
                }
                Text(text = "${if (isLoggedIn) spots.size else 0} captures · ${if (isLoggedIn) manufacturersCount else 0} manufacturers · one evolving album",
                    fontSize = 13.sp,
                    color = Color(0xFF94A3B8)
                )

            }

            // Camera shortcut button
            Box( modifier = Modifier.size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF00E676))
                    .clickable { AppRepository.navigateTo(Screen.CAMERA) },
                contentAlignment = Alignment.Center
            ) {

                Text(text = "📷", fontSize = 22.sp)
            }

        }

        // Guest Banner prompt when not logged in
        if (!isLoggedIn) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E293B))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Preview the community collection. Start your own story.",
                    fontSize = 13.sp,
                    color = Color(0xFF94A3B8),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sign in",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00E676),
                    modifier = Modifier.clickable { AppRepository.toggleAuth() }
                )
            }
        }

        // Filter Bar
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Box( modifier = Modifier.clip(RoundedCornerShape(12.dp))
                    .background(Color.White).padding(horizontal = 14.dp, vertical = 8.dp)) {
                Text(text = "By make", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
            }
            Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E293B))
                    .padding(horizontal = 14.dp, vertical = 8.dp)) {

                Text(text = "Newest first", fontSize = 13.sp, color = Color(0xFF94A3B8))
            }
            Box( modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E293B))
                    .padding(horizontal = 14.dp, vertical = 8.dp)) {

                Text(text = "All makes ▾", fontSize = 13.sp, color = Color(0xFF94A3B8))

            }


        }

        // Book / Field Notes Container
        Column( modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF161F30))
                .border(1.dp, Color(0xFF334155), RoundedCornerShape(24.dp))
                .padding(20.dp)) {

            // Book Header Tag
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text( text = "EXOTIC HUNT / FIELD NOTES",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64748B) )

                Text(text = "🖼️", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if ( spots.isEmpty() || !isLoggedIn){
                // Empty Album State Layout
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text( text = "🖼️", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Your first page is waiting.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Add your first car photo. Its manufacturer gets a chapter of its own, automatically.",
                        fontSize = 13.sp,
                        color = Color(0xFF94A3B8),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 20.dp))

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = { AppRepository.navigateTo(Screen.CAMERA) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)),
                        shape = RoundedCornerShape(12.dp)) {
                        Text(text = "Add your first catch",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A))

                    }

                }
            } else{
                //Sections List
                var makeIndex = 1
                groupedSpots.forEach {(makeName, makeSpots) ->
                    val formattedIndex = String.format("%02d", makeIndex++)

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row( verticalAlignment = Alignment.CenterVertically) {
                            Text( text = formattedIndex,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF00E676))
                            Spacer(modifier = Modifier.width(8.dp))

                            Text( text = makeName,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(text = "${makeSpots.size} catch${if (makeSpots.size > 1) "es" else ""}",
                                fontSize = 13.sp,
                                color = Color(0xFF64748B))

                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        //Grid Layout for Make Sectionn
                        val chunks = makeSpots.chunked(3)
                        chunks.forEach { rowSpots ->
                            Row( modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                rowSpots.forEach { spot ->
                                    AlbumGridCard( spot = spot,
                                        modifier = Modifier.weight(1f),
                                        onClick = { selectedSpot = spot })

                                }

                                //fill empty grid spaces if less than 3 in row
                                repeat(3 - rowSpots.size) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            // Book Footer Note
            Text( text = "COLLECTED ON THE STREET. KEPT FOR THE STORY.",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF475569),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)

            )
        }


    }

    // Car Detail Popup Modal
    selectedSpot?.let {spot ->
        CarDetailDialog(
            spot = spot,
            onDismiss ={ selectedSpot = null }
        )

    }

    // Active detail spot from AppRepository
    AppRepository.activeDetailSpot?.let { spot ->
        CarDetailDialog(
            spot = spot,
            onDismiss = {AppRepository.closeSpotDetail()}

        )
    }
}

// Individual 3-column album grid card for a spotted vehicle
@Composable
private fun AlbumGridCard(spot: CarSpot, modifier: Modifier = Modifier, onClick: () -> Unit) {

    Column( modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF0F172A))
            .rarityBorder(spot.rarity)
            .clickable { onClick() }
            .padding(8.dp)){

        Box( modifier = Modifier.fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF1E293B)),
            contentAlignment = Alignment.Center
        ){
            Text(text = "🏎️", fontSize = 28.sp)

        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(text = spot.brand.uppercase(),
            fontSize = 9.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF94A3B8))

        Text(text = spot.model,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White )


        Spacer(modifier = Modifier.height(6.dp)  )

        RarityBadge(rarity = spot.rarity)


    }



}
