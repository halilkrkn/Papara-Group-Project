package com.halilkrkn.chatchef.presentation.FavoriteScreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.halilkrkn.chatchef.R
import com.halilkrkn.chatchef.ui.theme.ColorButton
import com.halilkrkn.chatchef.ui.theme.ColorButton1

@Composable
fun FavoriteItemDeletionDialog(shouldShowItemDeletionDialog: (Boolean) -> Unit) {

    AlertDialog(
        onDismissRequest = {
            shouldShowItemDeletionDialog(false)
        },
        containerColor = ColorButton1,
        title = {
            Text(
                text = stringResource(R.string.favorite_item_deletion_dialog_text),
                fontSize = 20.sp,
                color = Color.Black
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    shouldShowItemDeletionDialog(false)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorButton,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.favorite_item_deletion_dialog_positive_button)
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    shouldShowItemDeletionDialog(false)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorButton,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.favorite_item_deletion_dialog_negative_button)
                )
            }
        }
    )
}