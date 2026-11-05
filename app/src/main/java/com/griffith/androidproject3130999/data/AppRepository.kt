// Radu Lupu 3130999
package com.griffith.androidproject3130999.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//state manager for the app
//Uses Jetpack Compose state primitives ( mutableStateOf )

object AppRepository {

    // checkin if user is logged in
    var isLoggedIn by mutableStateOf(true)
        private set

    //current User Profile
    var userProfile by mutableStateOf(SampleData.defaultProfile)
        private set

    // Active Navigation Screen
    var currentScreen by mutableStateOf(Screen.ACTIVITY)
        private set

    //weather Info
    var weather by mutableStateOf(SampleData.sampleWeather)
        private set

    //Daily Tasks
    var tasks by mutableStateOf(SampleData.sampleTasks)
        private set

    // All spotted vehicles
    var spots by mutableStateOf(SampleData.latestCaptures)
        private set

    // Spot of the day
    var spotOfTheDay by mutableStateOf(SampleData.spotOfTheDay)
        private set

    // Currently selected car spot for the detail popup
    var activeDetailSpot by mutableStateOf<CarSpot?>(null)
        private set



    //  State Mutation Actions

    //Toggle authentication state
    fun toggleAuth() {
        isLoggedIn = !isLoggedIn
    }

    //Navigate to a specific tab
    fun navigateTo(screen: Screen){
        currentScreen = screen
    }

    // open the popup for a specific car spot
    fun openSpotDetail(spot: CarSpot){
        activeDetailSpot = spot
    }

    //close the active car detail popup
    fun closeSpotDetail(){
        activeDetailSpot = null
    }

    //toggle completion status of a daily task
    fun toggleTaskCompletion(taskId: String){
        if (  !isLoggedIn ) return
        tasks = tasks.map { task ->
            if (task.id == taskId){
                task.copy(isCompleted = !task.isCompleted)
            }
            else{
                task
            }
        }
    }

    // Update user profile information
    fun updateUserProfile(updated: UserProfile){
        if (!isLoggedIn) return
        userProfile = updated
    }

    //Update technical specs for a car spot
    fun updateSpotDetails( spotId: String, newEngine: String, newBody: String, newHp: String, newNote: String) {
        spots = spots.map {spot ->
            if(spot.id == spotId) {
                spot.copy(
                    engineType = newEngine,
                    bodyType = newBody,
                    horsepower = newHp,
                    note = newNote )
            }
            else{
                spot
            }
        }
        if (spotOfTheDay.id == spotId){
            spotOfTheDay = spotOfTheDay.copy(
                engineType = newEngine,
                bodyType = newBody,
                horsepower = newHp,
                note = newNote )
        }

        activeDetailSpot = activeDetailSpot?.copy(
            engineType = newEngine,
            bodyType = newBody,
            horsepower = newHp,
            note = newNote
        )
    }

    //adding a new captured car spot
    fun addNewSpot(spot: CarSpot){
        if (!isLoggedIn) return

        spots = listOf(spot) + spots
        userProfile = userProfile.copy(
            streakCount = userProfile.streakCount + 1,
            totalSpots = userProfile.totalSpots + 1
        )
    }

    //Update profile avatar image
    fun setProfileAvatar(uri: String){
        if (!isLoggedIn) return

        userProfile = userProfile.copy(avatarUri = uri)
    }


}
