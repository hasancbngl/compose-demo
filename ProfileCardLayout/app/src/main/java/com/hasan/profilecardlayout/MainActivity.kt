package com.hasan.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.hasan.profilecardlayout.ui.UserProfile
import com.hasan.profilecardlayout.ui.theme.ProfileCardLayoutTheme
import com.hasan.profilecardlayout.ui.theme.black
import com.hasan.profilecardlayout.ui.theme.lightGreen
import com.hasan.profilecardlayout.ui.userProfileList

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //convert hex code color and override default color
        //  LightGreen200 = Color("#000000".toColorInt())
        setContent {
            ProfileCardLayoutTheme {
                UsersApplication()
            }
        }
    }
}

//manage navigation
@ExperimentalMaterial3Api
@Composable
fun UsersApplication(usersList: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            UsersScreen(usersList, navController)
        }
        composable(
            "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) {
            ProfileDetailScreen(it.arguments!!.getInt("userId"), navController)

        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun UsersScreen(usersList: List<UserProfile>, navController: NavHostController?) {
    //scaffold composable helps to add app bar
    Scaffold(topBar = {
        AppBar(
            Icons.Default.Home,
            "Users List",
            false
        ) {}
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.primary,
        ) {
            LazyColumn(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(usersList) {
                    ProfileCard(userProfile = it) {
                        navController?.navigate("user_details/${it.id}")
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProfileDetailScreen(userId: Int, navController: NavHostController?) {
    //scaffold composable helps to add app bar
    val userProfile = userProfileList.find { it.id == userId }
    Scaffold(topBar = {
        AppBar(
            iconVector = Icons.Default.ArrowBack,
            title = "User Details",
            isClickable = true
        ) { navController?.navigateUp() }

    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.primary,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                userProfile?.apply {
                    ProfilePicture(imageUrl, status, 240.dp)
                    ProfileContent(name, status, Alignment.CenterHorizontally)
                }
            }
        }
    }
}

@Composable
fun AppBar(
    iconVector: ImageVector, title: String,
    isClickable: Boolean,
    onIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 22.sp,
                color = Color.White
            )
        },
        navigationIcon = {
            Icon(
                imageVector = iconVector,
                contentDescription = "description",
                modifier = Modifier
                    .size(48.dp)
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .clickable(enabled = isClickable, onClick = { onIconClick.invoke() }),
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
fun ProfileCard(userProfile: UserProfile, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 4.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = onCardClick),
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
@Preview(showBackground = true)
@Composable
fun ShowDetailPreview() {
    ProfileCardLayoutTheme {
        ProfileDetailScreen(0, null)
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun UsersMainScreenPreview() {
    ProfileCardLayoutTheme {
        UsersScreen(userProfileList, null)
    }
}