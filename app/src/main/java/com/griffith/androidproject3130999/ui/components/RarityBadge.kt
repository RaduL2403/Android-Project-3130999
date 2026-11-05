// Radu Lupu 3130999

package com.griffith.androidproject3130999.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.androidproject3130999.data.CarRarity

//Pill tag badge displaying the car rarity
@Composable
fun RarityBadge(rarity: CarRarity, modifier: Modifier = Modifier){
    Text(
        text =rarity.label,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier.clip(RoundedCornerShape(12.dp)).background(rarity.color.copy(alpha = 0.85f)).padding(horizontal = 8.dp, vertical = 3.dp)
    )
}

// modifier for applying colored rarity borders to car cards
fun Modifier.rarityBorder(rarity: CarRarity): Modifier {
    return this.border(
        width = 2.dp,
        color = rarity.borderColor,
        shape = RoundedCornerShape(16.dp)
    )

}
