package com.hasan.mvvmcompose_mealzapp.ui.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hasan.mvvmcompose_mealzapp.model.response.MealResponse
import java.lang.Float.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    val scrollState = rememberLazyListState()
    val offSet = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset / 500f + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, (200.dp * offSet)))
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        elevation = CardDefaults.cardElevation(2.dp),
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        colors =
                        CardDefaults.cardColors(containerColor = Color.Transparent),
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Black
                        )
                    ) {
                        AsyncImage(
                            model = meal?.imageUrl,
                            contentDescription = "image big",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(size)
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
            }
            val dummyList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState) {
                items(dummyList) { item ->
                    Text(text = item, fontSize = 18.sp, modifier = Modifier.padding(24.dp))
                }
            }
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