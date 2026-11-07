// Radu Lupu 3130999


package com.griffith.androidproject3130999.ui.screens

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.androidproject3130999.data.AppRepository
import com.griffith.androidproject3130999.data.CarSpot

//interactive Map Screen
@Composable
fun MapScreen( modifier: Modifier = Modifier) {

    val spots = AppRepository.spots

    //Active selected spot on the map
    var selectedMapSpot by remember {mutableStateOf<CarSpot?>(spots.firstOrNull() )}

    Column(modifier = modifier.fillMaxSize()
            .background(Color(0xFFF0F6F8))
            .verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        // Header Title Section
        Column {
            Text(
                text = "THE STREETS ARE YOUR SHOWROOM",
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF64748B))
            Text(text = "Explore the map",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A))
            Text( text = "Demo sightings · New York",
                fontSize = 13.sp,
                color = Color(0xFF64748B))

        }

        // Filter Bar Controls
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically){

            Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)) {

                Text(text = "Show All supercars ▾", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))

            }

            Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)) {

                Text(text = "Within 2 kilometers ▾", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))


            }

        }

        //Catches in area status
        Row( verticalAlignment = Alignment.CenterVertically) {
            Box( modifier = Modifier.size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00E676)))

            Spacer(modifier = Modifier.width(6.dp))

            Text(text = "${spots.size} catches in this area",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF475569))


        }

        //Map View Container
        Box( modifier = Modifier.fillMaxWidth().height(440.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFE2E8F0))
                .border(2.dp, Color(0xFF2962FF), RoundedCornerShape(24.dp))) {

            //Map Grid Background
            Column( modifier = Modifier.fillMaxSize()) {repeat(8) {

                    Row(modifier = Modifier.weight(1f).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround){repeat(4) {

                            Box( modifier = Modifier.weight(1f)
                                    .fillMaxSize()
                                    .border(0.5.dp, Color(0xFFCBD5E1))

                            )
                        }

                    }
                }


            }

            //Top-left Radius Tag
            Box( modifier = Modifier.align(Alignment.TopStart)
                    .padding(14.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 6.dp)){

                Text(text = "SoHo, New York · 2 km radius",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A))


            }

            //Right Map Controls
            Column( modifier = Modifier.align(Alignment.TopEnd).padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)) {

                MapControlButton(symbol= "+")
                MapControlButton(symbol= "−")
                MapControlButton(symbol = "🎯")

            }

            //User s Current Location Blue Dot
            Box( modifier = Modifier.size(16.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(Color(0xFF2962FF))
                    .border(2.dp, Color.White, CircleShape)

            )

            //Water-Drop Pin Markers for Cars on Map
            spots.forEachIndexed {index,spot ->
                val alignment = when (index % 4){
                    0 -> Alignment.TopStart
                    1 ->Alignment.TopEnd
                    2 ->Alignment.BottomStart
                    else ->Alignment.CenterEnd

                }

                MapWaterDropPin(
                    spot = spot,
                    isSelected = selectedMapSpot?.id == spot.id,
                    onClick = { selectedMapSpot = spot },
                    modifier = Modifier.align(alignment).padding(

                            start = (index * 30 + 30).dp,
                            top = (index * 40 + 60).dp,
                            end = 30.dp,
                            bottom = 60.dp

                        )
                )

            }

            //bottom Scale Indicator and Instructions
            Row(modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Box( modifier = Modifier.clip(RoundedCornerShape(12.dp))
                        .background(Color.White.copy(alpha = 0.9f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)) {

                    Text( text = "Drag to explore · Use + / - to zoom",
                        fontSize = 10.sp,
                        color = Color(0xFF64748B))

                }

                Text(text = "235 m —",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF475569))

            }


        }

        //Pin "Community Catch" detail card
        selectedMapSpot?.let {spot ->
            Column( modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(24.dp))
                    .padding(18.dp) ){

                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){

                    Text( text = "COMMUNITY CATCH",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF64748B))

                    Box( modifier = Modifier.size(28.dp).clip(CircleShape)
                            .background(Color(0xFFF1F5F9))
                            .clickable { selectedMapSpot = null },
                        contentAlignment = Alignment.Center) {

                        Text(text = "✕", fontSize = 12.sp, color = Color(0xFF64748B))

                    }

                }

                Spacer(modifier = Modifier.height( 10.dp ))

                Box( modifier = Modifier.fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF1E293B)),
                    contentAlignment = Alignment.Center){

                    Text(text ="🏎️", fontSize= 56.sp)

                }

                Spacer(modifier = Modifier.height(12.dp))

                Text( text = "${spot.brand} ${spot.model}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A))

                Text( text = "${spot.location} · ${spot.timestamp}",
                    fontSize = 13.sp,
                    color = Color(0xFF64748B) )

                Spacer(modifier = Modifier.height(12.dp))

                Row( verticalAlignment =Alignment.CenterVertically){

                    Box( modifier = Modifier.size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00E676)),
                        contentAlignment = Alignment.Center){

                        Text(text = spot.spotterInitials, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))

                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Column{

                        Text(text = "Spotted by", fontSize = 11.sp, color = Color(0xFF94A3B8))
                        Text(text = spot.spotterName, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text( text = spot.note,
                    fontSize = 13.sp,
                    color = Color(0xFF475569)

                )
            }
        }

    }

}

//component for zoom control for maap
@Composable
private fun MapControlButton( symbol: String){
    Box( modifier = Modifier.size(36.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center){

        Text(text = symbol, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))

    }

}

// component for drop shape car pin on map
@Composable
private fun MapWaterDropPin(spot: CarSpot, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier){

    Box( modifier = modifier.size(52.dp)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFF00E676) else Color(0xFF2962FF))
            .border(3.dp, if (isSelected) Color(0xFF00E676) else Color.White, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center){

        Text(text = "🏎️", fontSize = 24.sp)

    }


}
