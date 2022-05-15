package com.hasan.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasan.profilecardlayout.ui.theme.lightGreen
import com.hasan.profilecardlayout.ui.theme.ProfileCardLayoutTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme {
                MainScreen()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        ProfileCard()
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture()
            ProfileContent()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProfilePicture() {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.lightGreen),
        shape = CircleShape,
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "image",
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "John Doe",
            style = TextStyle(
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier.alpha(0.25f),
            text = "Active now",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileCardLayoutTheme {
        MainScreen()
    }
}