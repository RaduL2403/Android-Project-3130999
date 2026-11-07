//Radu Lupu 3130999


package com.griffith.androidproject3130999

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.SurfaceView
import android.widget.Button
import android.widget.Space
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3. Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.griffith.androidproject3130999.data.Screen
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.griffith.androidproject3130999.data.AppRepository
import com.griffith.androidproject3130999.data.CarRarity
import com.griffith.androidproject3130999.data.CarSpot
import com.griffith.androidproject3130999.ui.components.BottomNavBar
import com.griffith.androidproject3130999.ui.components.HeaderBar
import com.griffith.androidproject3130999.ui.screens.ActivityScreen
import com.griffith.androidproject3130999.ui.screens.AlbumScreen
import com.griffith.androidproject3130999.ui.screens.CameraScreen
import com.griffith.androidproject3130999.ui.screens.MapScreen
import com.griffith.androidproject3130999.ui.screens.ProfileScreen
import com.griffith.androidproject3130999.ui.theme.AndroidProject3130999Theme

// Entry activity for the Exotic Hunt application
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidProject3130999Theme {
                MainScaffold()
            }
        }
    }
}

//Scaffold hosting header, bottom navigation bar, and active screen contentt
@Composable
fun MainScaffold() {

    val currentScreen = AppRepository.currentScreen

    // System Camera launcher
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract= ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if ( bitmap != null){
            val newSpot = CarSpot(
                id= "photo_${System.currentTimeMillis()}",
                brand= "Lamborghini",
                model = "Huracán EVO",
                trim = "LP 640-4",
                rarity = CarRarity.SUPERCAR,
                location= "Grafton Street, Dublin",
                timestamp ="Just now",
                weatherDescription ="Light rain · 14°C",
                spotterName =AppRepository.userProfile.name,
                spotterInitials = "JO",
                note = "Captured via device camera."
            )

            AppRepository.addNewSpot(newSpot)
            AppRepository.navigateTo(Screen.ALBUM)


        }
    }

    // Photo Gallery
    val pickGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null ) {
            val newSpot =CarSpot(
                id = "gallery_${System.currentTimeMillis()}",
                brand = "Porsche",
                model = "911 GT3",
                trim = "Touring",
                rarity = CarRarity.TRACK_EDITION,
                location = "Docklands, Dublin",
                timestamp = "Imported from Gallery",
                weatherDescription = "Sunny · 20°C",
                spotterName = AppRepository.userProfile.name,
                spotterInitials = "JO",
                note = "Imported from phone gallery."

            )

            AppRepository.addNewSpot(newSpot)
            AppRepository.navigateTo(Screen.ALBUM)

        }


    }

    Scaffold( topBar = {HeaderBar() },
        bottomBar ={
            BottomNavBar(
                activeScreen = currentScreen,
                onScreenSelected = { AppRepository.navigateTo(it) }
            )
        }) {innerPadding ->
        Box(modifier = Modifier.fillMaxSize()
                .background(Color(0xFFF5FCFF))
                .padding(innerPadding),
            contentAlignment = Alignment.Center){

            when ( currentScreen) {
                Screen.ACTIVITY -> ActivityScreen()
                Screen.MAP -> MapScreen()
                Screen.CAMERA -> CameraScreen(
                    onTriggerCameraIntent = { takePictureLauncher.launch(null) },
                    onTriggerGalleryIntent = { pickGalleryLauncher.launch("image/*") }
                )
                Screen.ALBUM -> AlbumScreen()
                Screen.PROFILE -> ProfileScreen()

            }
        }


    }


}
