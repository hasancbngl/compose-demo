package com.hasan.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.hasan.profilecardlayout.ui.UserProfile
import com.hasan.profilecardlayout.ui.theme.ProfileCardLayoutTheme
import com.hasan.profilecardlayout.ui.theme.black
import com.hasan.profilecardlayout.ui.theme.lightGreen
import com.hasan.profilecardlayout.ui.userProfileList
import org.intellij.lang.annotations.JdkConstants

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //convert hex code color and override default color
        //  LightGreen200 = Color("#000000".toColorInt())
        setContent {
            ProfileCardLayoutTheme {
                UsersScreen()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun UsersScreen() {
    //scaffold composable helps to add app bar
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
        ) {
            LazyColumn(
                modifier = Modifier.padding(
                    top = 76.dp,
                    bottom = 8.dp
                ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(userProfileList) {
                    ProfileCard(userProfile = it)
                }
            }
        }
    }
}

@Composable
fun AppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Users",
                fontSize = 22.sp,
                color = Color.White
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "description",
                modifier = Modifier
                    .size(48.dp)
                    .padding(16.dp, 0.dp, 0.dp, 0.dp),
                tint = Color.White
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Red
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 4.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            with(userProfile) {
                ProfilePicture(imageUrl, status, 72.dp)
                ProfileContent(name, status, Alignment.Start)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProfilePicture(url: String, online: Boolean, size: Dp) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(
            2.dp,
            if (online) MaterialTheme.colorScheme.lightGreen
            else MaterialTheme.colorScheme.black
        ),
        shape = CircleShape,
        modifier = Modifier.padding(16.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = "image",
            modifier = Modifier.size(size),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun ProfileContent(name: String, status: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            modifier = Modifier.alpha(if (status) 1f else 0.4f),
            text = name,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            modifier = Modifier.alpha(0.25f),
            text = if (status) "Active now" else "Offline",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProfileDetailScreen(userProfile: UserProfile = userProfileList[0]) {
    //scaffold composable helps to add app bar
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 76.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                with(userProfile) {
                    ProfilePicture(imageUrl, status, 240.dp)
                    ProfileContent(name, status, Alignment.CenterHorizontally)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ShowDetailPreview() {
    ProfileCardLayoutTheme {
        ProfileDetailScreen()
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun UsersMainScreenPreview() {
    ProfileCardLayoutTheme {
        UsersScreen()
    }
}