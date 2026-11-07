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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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

//Profile Screen
@Composable
fun ProfileScreen( modifier: Modifier = Modifier ){

    val isLoggedIn =AppRepository.isLoggedIn
    val profile = AppRepository.userProfile
    val spots = AppRepository.spots

    // Local form state
    var name by remember {mutableStateOf(profile.name)}
    var email by remember {mutableStateOf(profile.email)}
    var age by remember { mutableStateOf(profile.age) }
    var country by remember {mutableStateOf(profile.country) }
    var city by remember { mutableStateOf(profile.city)  }

    Column( modifier = modifier.fillMaxSize()
            .background(Color(0xFF0F172A))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        if ( !isLoggedIn){

            // Guest Mode Profile layout
            Column( modifier = Modifier.fillMaxSize().padding(vertical = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Box( modifier = Modifier.size(80.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF00E676), CircleShape)
                        .background(Color(0xFF1E293B)),
                    contentAlignment = Alignment.Center) {

                    Text(text = "👤", fontSize = 36.sp)
                }

                Spacer(modifier = Modifier.height(20.dp) )
                Text( text = "Your hunter profile.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White  )

                Spacer(modifier = Modifier.height(8.dp))
                Text( text = "Sign in to make this space yours, save your catches, and build a collection worth sharing.",
                    fontSize = 14.sp,
                    color = Color(0xFF94A3B8),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp) )


                Spacer(modifier = Modifier.height(28.dp))
                Button( onClick = { AppRepository.toggleAuth() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.height(48.dp)
                ){
                    Text( text = "Sign in or create account",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A))

                }

            }
        }
        else{
            // Logged-In User Profile Layout
            Column {
                Text( text = "BEHIND THE LENS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF00E676) )

                Row( verticalAlignment = Alignment.CenterVertically) {
                    Text( text = "Your profile",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White )

                    Text( text = ".",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00E676) )


                }

            }


            //editable Profile Card
            Column( modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF161F30))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(24.dp))
                    .padding(20.dp)) {

                Row( verticalAlignment =Alignment.CenterVertically){

                    //Avatar circle
                    Box(modifier =Modifier.size(72.dp).clip(CircleShape)
                            .border(2.dp, Color(0xFF00E676), CircleShape)
                            .background(Color(0xFF1E293B)),
                        contentAlignment = Alignment.Center){

                        Text( text = if (name.length >= 2) name.substring(0, 2).uppercase() else "JO",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00E676) )

                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text( text = name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White )

                        Text( text = "Street collector",
                            fontSize = 13.sp,
                            color = Color(0xFF94A3B8) )

                        Spacer(modifier = Modifier.height(4.dp))


                        Box( modifier = Modifier.clip(RoundedCornerShape(12.dp))
                                .border(1.dp, Color(0xFF00E676), RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)) {

                            Text( text = "📷 ${spots.size} CATCHES COLLECTED",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00E676) )

                        }

                    }
                }

                Spacer(modifier = Modifier.height(20.dp) )

                //ditable Fields Form
                OutlinedTextField(

                    value = name,
                    onValueChange = {name = it},
                    label = {Text("Your name", color = Color(0xFF94A3B8)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF00E676),
                        unfocusedBorderColor = Color(0xFF334155)
                    )

                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it},
                    label = { Text("Email · only visible to you", color =Color(0xFF94A3B8))},
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF00E676),
                        unfocusedBorderColor = Color(0xFF334155)

                    )

                )

                Spacer(modifier = Modifier.height(12.dp))

                // Age Stepper Control
                Column(modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF0F172A))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(12.dp))
                        .padding(14.dp)) {

                    Text(text = "Age", fontSize = 12.sp, color = Color(0xFF94A3B8))
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Box(
                            modifier = Modifier.size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1E293B))
                                .clickable { if (age > 18) age-- },
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "−", fontSize = 20.sp, color = Color.White)

                        }

                        Text( text = "$age",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White )

                        Box(
                            modifier = Modifier.size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1E293B))
                                .clickable { age++ },
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "+", fontSize = 20.sp, color = Color.White)

                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = country,
                    onValueChange = { country = it },
                    label = { Text("Country", color = Color(0xFF94A3B8)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF00E676),
                        unfocusedBorderColor = Color(0xFF334155)
                    )

                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = city,
                    onValueChange = {city = it},
                    label = { Text("City", color = Color(0xFF94A3B8)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF00E676),
                        unfocusedBorderColor = Color(0xFF334155)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Action Buttons for Discard and Save
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text( text = "Discard",
                        fontSize = 14.sp,
                        color = Color(0xFF94A3B8),
                        modifier = Modifier
                            .clickable {
                                name = profile.name
                                email = profile.email
                                age = profile.age
                                country = profile.country
                                city = profile.city
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)  )


                    Spacer(modifier = Modifier.width(8.dp))

                    Button( onClick ={
                            AppRepository.updateUserProfile(
                                profile.copy(
                                    name = name,
                                    email = email,
                                    age = age,
                                    country = country,
                                    city = city
                                )

                            )
                        },

                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)),
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Text( text = "Save profile",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A) )

                    }
                }

            }

            // Stats Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){

                // Cars spotted card
                Column(
                    modifier = Modifier.weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF161F30))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {

                    Text(text = "📷", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text( text = "${spots.size}",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Cars spotted",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8) )

                }

                //Tasks completed card
                Column(
                    modifier = Modifier.weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF161F30))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    Text(text ="✓", fontSize = 20.sp,color = Color(0xFF00E676))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text( text = "${profile.tasksCompleted}",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White )

                    Text( text = "Tasks completed",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8) )

                }



            }


            // Dream Garage / Favorite Car Section
            Column(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF161F30))
                    .border(1.dp, Color(0xFF334155), RoundedCornerShape(24.dp))
                    .padding(20.dp)
            ){

                Text( text = "THE DREAM GARAGE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF00E676) )

                Text( text = "Favourite car",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White )


                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF0F172A))
                        .border(1.dp, Color(0xFF334155), RoundedCornerShape(12.dp))
                        .padding(14.dp)
                ){

                    Text( text = if (spots.isNotEmpty()) "${spots.first().brand} ${spots.first().model}" else "Choose your favourite catch ▾",
                        fontSize = 14.sp,
                        color = Color.White )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Text( text = "Every hunter has one. Pick a catch that makes you look twice.",
                    fontSize = 12.sp,
                    color = Color(0xFF94A3B8) )


            }

            // Sign out link
            Text( text = "Sign out of Exotic Hunt",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2962FF),
                modifier = Modifier.clickable { AppRepository.toggleAuth() }.padding(vertical = 8.dp) )

        }


    }
}
