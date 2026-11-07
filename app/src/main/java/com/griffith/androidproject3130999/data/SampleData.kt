// Radu Lupu 3130999


package com.griffith.androidproject3130999.data

//initial demo
object SampleData {

    // Weather status header
    val sampleWeather = WeatherInfo(
        dateString = "Thursday, June 12",
        condition = "Light rain",
        tempC = 14,
        description = "Great spotting weather"
    )

    // Daily tasks for today
    val sampleTasks = listOf(
        DailyTask(
            id = "task_1",
            title = "Porsche 911",
            description = "Capture one with rain in frame",
            isCompleted = true,
            carMakeHint = "Porsche"
        ),
        DailyTask(
            id = "task_2",
            title = "Bright exotic",
            description = "Find one reflected on a wet street",
            isCompleted = true,
            carMakeHint = "Lamborghini"
        ),
        DailyTask(
            id = "task_3",
            title = "McLaren in motion",
            description = "Catch wheel spray or rain trails",
            isCompleted = false,
            carMakeHint = "McLaren"
        )
    )

    // Spot of the day
    val spotOfTheDay = CarSpot(
        id = "spot_day_1",
        brand = "Lamborghini",
        model = "Huracan EVO",
        trim = "V10 LP 640-4",
        rarity = CarRarity.SUPERCAR,
        location = "Grafton Street, Dublin",
        timestamp = "8:42 AM",
        weatherDescription = "Light rain · 14°C",
        spotterName = "Alex BimBIm",
        spotterInitials = "AC",
        note = "Arancio orange against the Dublin skyline. A perfect morning catch.",
        engineType = "5.2L Naturally Aspirated V10",
        bodyType = "Coupe",
        horsepower = "640 HP",
        topSpeed = "325 km/h",
        year = "2023",
        latitude = 53.3418,
        longitude = -6.2599
    )

    // Collection preview & album list
    val latestCaptures = listOf(
        CarSpot(
            id = "porsche_911",
            brand = "Porsche",
            model = "911 GT3",
            trim = "Touring Package",
            rarity = CarRarity.TRACK_EDITION,
            location = "Docklands, Dublin",
            timestamp = "Yesterday, 4:15 PM",
            weatherDescription = "Overcast · 16°C",
            spotterName = "Alex BimBIm",
            spotterInitials = "AC",
            note = "Chalk white finish parked in the Grand Canal Docklands. Impeccable sound.",
            engineType = "4.0L Flat-6",
            bodyType = "Coupe",
            horsepower = "502 HP",
            topSpeed = "318 km/h",
            year = "2022",
            latitude = 53.3468,
            longitude = -6.2415
        ),
        CarSpot(
            id = "lambo_huracan",
            brand = "Lamborghini",
            model = "Huracán EVO",
            trim = "Arancio X",
            rarity = CarRarity.SUPERCAR,
            location = "Grafton Street, Dublin",
            timestamp = "Today, 8:42 AM",
            weatherDescription = "Light rain · 14°C",
            spotterName = "Alex BimBIm",
            spotterInitials = "AC",
            note = "Bright orange paint glowing under wet Dublin street lights.",
            engineType = "5.2L V10",
            bodyType = "Coupe",
            horsepower = "640 HP",
            topSpeed = "325 km/h",
            year = "2023",
            latitude = 53.3418,
            longitude = -6.2599
        ),
        CarSpot(
            id = "mclaren_720s",
            brand = "McLaren",
            model = "720S Spider",
            trim = "Performance",
            rarity = CarRarity.HYPERCAR,
            location = "Temple Bar, Dublin",
            timestamp = "June 10, 9:20 PM",
            weatherDescription = "Clear · 18°C",
            spotterName = "Alex BimBIm",
            spotterInitials = "AC",
            note = "Midnight black with exposed carbon fiber aero components near Temple Bar.",
            engineType = "4.0L Twin-Turbo V8",
            bodyType = "Convertible",
            horsepower = "710 HP",
            topSpeed = "341 km/h",
            year = "2021",
            latitude = 53.3454,
            longitude = -6.2642
        ),
        CarSpot(
            id = "ferrari_f8",
            brand = "Ferrari",
            model = "F8 Tributo",
            trim = "Rosso Corsa",
            rarity = CarRarity.EXOTIC,
            location = "Ballsbridge, Dublin",
            timestamp = "June 8, 2:10 PM",
            weatherDescription = "Sunny · 22°C",
            spotterName = "Alex BimBIm",
            spotterInitials = "AC",
            note = "Classic Rosso Corsa with tan leather interior parked in Ballsbridge.",
            engineType = "3.9L Twin-Turbo V8",
            bodyType = "Coupe",
            horsepower = "710 HP",
            topSpeed = "340 km/h",
            year = "2022",
            latitude = 53.3283,
            longitude = -6.2285
        ),
        CarSpot(
            id = "bmw_m3",
            brand = "BMW",
            model = "M3 Competition",
            trim = "xDrive",
            rarity = CarRarity.COMMON,
            location = "O'Connell Street, Dublin",
            timestamp = "June 6, 11:30 AM",
            weatherDescription = "Partly Cloudy · 20°C",
            spotterName = "Alex BimBIm",
            spotterInitials = "AC",
            note = "Isle of Man Green metallic finish on O'Connell Street.",
            engineType = "3.0L Twin-Turbo Inline-6",
            bodyType = "Sedan",
            horsepower = "503 HP",
            topSpeed = "290 km/h",
            year = "2023",
            latitude = 53.3498,
            longitude = -6.2603
        )
    )

    // Initial user profile
    val defaultProfile = UserProfile(
        name = "Alex BimBIm",
        email = "alex.bimbim@exotichunt.com",
        age = 24,
        country = "Ireland",
        city = "Dublin",
        streakCount = 12,
        totalSpots = 42,
        tasksCompleted = 18,
        favoriteCarSpotId = "lambo_huracan"

    )


}
