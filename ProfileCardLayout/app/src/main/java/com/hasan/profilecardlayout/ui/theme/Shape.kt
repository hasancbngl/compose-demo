package com.hasan.profilecardlayout.ui.theme

import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

//we can override shaped
val shapes = Shapes(
    extraSmall = AbsoluteCutCornerShape(2.dp),
    small = RoundedCornerShape(8.dp),
    medium = CutCornerShape(topEnd = 24.dp),
    large = RoundedCornerShape(0.dp)
)