package com.hasan.mvvmcompose_mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hasan.mvvmcompose_mealzapp.model.response.MealResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    var profilePicState by remember { mutableStateOf(MealsDetailsProfilePicState.Normal) }
    val transition = updateTransition(targetState = profilePicState, label = "")
    val imageSizeDp: Dp by transition.animateDp(label = "") {
        it.size
    }
    val color: Color by transition.animateColor(label = "") {
        it.color
    }
    val borderWidth: Dp by transition.animateDp(label = "") {
        it.borderWidth
    }
    val buttonHeight: Dp by transition.animateDp(label = "") {
        it.btnHeight
    }

    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                colors =
                CardDefaults.cardColors(containerColor = Color.Transparent),
                border = BorderStroke(
                    width = borderWidth,
                    color = color
                )
            ) {
                AsyncImage(
                    model = meal?.imageUrl,
                    contentDescription = "image big",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(imageSizeDp)
                        .padding(8.dp)
                        .clip(CircleShape)
                )
            }
            Text(
                text = "${meal?.name}",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(buttonHeight),
            onClick = {
                profilePicState =
                    if (profilePicState == MealsDetailsProfilePicState.Normal) MealsDetailsProfilePicState.Expanded
                    else MealsDetailsProfilePicState.Normal
            }) {
            Text(text = "Change state of meal profile picture")
        }
    }
}

enum class MealsDetailsProfilePicState(
    val color: Color,
    val size: Dp,
    val borderWidth: Dp,
    val btnHeight: Dp
) {
    Normal(Color.Magenta, 120.dp, 2.dp, 50.dp),
    Expanded(Color.Green, 225.dp, 4.dp, 80.dp)
}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview() {
    MealDetailsScreen(meal = MealResponse("", "Meat", "Some dummy content", ""))
}