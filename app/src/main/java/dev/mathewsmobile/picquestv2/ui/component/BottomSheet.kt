package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(show: Boolean) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Text("This is the sheetContent")
        }
    ) {
        Text("Where am I?")
    }
}