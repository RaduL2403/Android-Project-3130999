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
import com.griffith.androidproject3130999.ui.components.BottomNavBar
import com.griffith.androidproject3130999.ui.components.HeaderBar
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

// Scaffold hosting header, bottom navigation bar, and active screen content
@Composable
fun MainScaffold() {
    val currentScreen = AppRepository.currentScreen

    Scaffold( topBar = {HeaderBar() },
        bottomBar = {
            BottomNavBar(
                activeScreen = currentScreen,
                onScreenSelected = { AppRepository.navigateTo(it) }
            )
        }) { innerPadding ->
        Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F6F8))
                .padding(innerPadding),
            contentAlignment = Alignment.Center){

            when (currentScreen) {
                Screen.ACTIVITY -> Text("Activity Screen", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Screen.MAP -> Text("Map Screen", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Screen.CAMERA -> Text("Camera Screen", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Screen.ALBUM -> Text("Album Screen", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Screen.PROFILE -> Text("Profile Screen", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
