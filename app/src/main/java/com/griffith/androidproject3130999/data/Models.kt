// Radu Lupu 3130999
package com.griffith.androidproject3130999.data

import androidx.compose.ui.graphics.Color


//badges and border colors for each car tier.
enum class CarRarity(
    val label: String,
    val color: Color,
    val borderColor: Color) {
    SUPERCAR("SUPERCAR", Color(0xFFFF9800), Color(0xFFFFB74D)),
    HYPERCAR("HYPERCAR", Color(0xFFF44336), Color(0xFFE57373)),
    TRACK_EDITION("TRACK EDITION", Color(0xFF9C27B0), Color(0xFFBA68C8)),
    EXOTIC("EXOTIC", Color(0xFF00E676), Color(0xFF69F0AE)),
    COMMON("COMMON", Color(0xFF6F7EDE), Color(0xFF03A9F4))
}


//represents ambient conditions at the time of a car spot.
data class WeatherInfo (
    val dateString: String = "Thursday, June 12",
    val condition: String = "Light rain",
    val tempC: Int = 14,
    val description: String = "Great spotting weather"
)


//checklist item for today's car hunt.
data class DailyTask(
    val id: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean =false,
    val carMakeHint: String = ""
)


//Holds full metadata for a spotted exotic vehicle.
data class CarSpot (
    val id: String,
    val brand: String,
    val model: String,
    val trim: String = "",
    val rarity: CarRarity = CarRarity.SUPERCAR,
    val location: String = "SoHo, New York",
    val timestamp: String = "8:42 AM",
    val weatherDescription: String = "Light rain · 14°C",
    val spotterName: String = "Alex BimBIm",
    val spotterInitials: String = "AC",
    //no idea if that color exists,
    val note: String = "Arancio orange against the downtown skyline. A perfect morning catch.",

    val engineType: String = "5.2L V10",
    val bodyType: String = "Coupe",
    val horsepower: String = "640 HP",
    val topSpeed: String = "325 km/h",
    val year: String = "2023",
    val latitude: Double = 40.7233,
    val longitude: Double = -74.0030,
    val imageResId: Int? = null,
    val imageUri: String? = null
)

//Holds user details, stats, streak counter, and favorite car reference.
data class UserProfile(
    val name: String = "Alex BimBIm",
    val email: String = "alex.bimbim@exotichunt.com",
    val age: Int = 24,
    val country: String = "United States",
    val city: String = "New York",
    val streakCount: Int = 12,
    val totalSpots: Int = 42,
    val tasksCompleted: Int = 18,
    val favoriteCarSpotId: String = "lambo_1",
    val avatarUri: String? = null
)


// the 5 main app pages for tab switching.
enum class Screen( val title: String ){
    ACTIVITY("Activity"),
    MAP("Map"),
    CAMERA("Camera"),
    ALBUM("Album"),
    PROFILE("Profile")
}
