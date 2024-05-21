package com.halilkrkn.chatchef.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun TextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}



@Composable
fun HeadingTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Loader(resId:Int, height:Dp = 200.dp){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.height(height)

    )
}

@Composable
fun TextFieldComponent(stateValue: String, label:String,onValueChange: (String) -> Unit, painterResource: Painter) {
    var textValue by remember { mutableStateOf(stateValue) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = label) },
        value = textValue,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.LightGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        onValueChange = {
            textValue = it
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )
}


@Composable
fun PasswordFieldComponent(stateValue:String,label: String,onValueChange: (String) -> Unit , painterResource : Painter){

    var password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = label) },
        value = password.value,
        colors =  OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.LightGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        onValueChange = {
            password.value = it
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisibility.value){
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if(passwordVisibility.value){
                "Hide Password"
            } else {
                "Show Password"
            }

            IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}



@Composable
fun ButtonComponent(value: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Red)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0xFFd70f4b),
                            Color(0xFFf50110),
                            //Color(0xFFf07167),
                        )
                    ), shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
fun DividerTextComponent() {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Black,
            thickness = 1.dp)

        Text(modifier = Modifier.padding(8.dp),text = "or",fontSize = 14.sp, color = Color.LightGray,)

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Black,
            thickness = 1.dp)



    }
}

@Composable
fun ClickableLoginTextComponent(tryToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText = if (tryToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if (tryToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        pushStringAnnotation(tag = "ACTION", annotation = loginText)
        withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Bold)) {
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,

        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "ACTION", start = offset, end = offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")
                    onTextSelected(span.item)
                }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnderLinedTextComponent(value:String,onClick: () -> Unit){
    Card (colors = CardDefaults.cardColors(
     containerColor = Color.Transparent
    ),onClick = {
        onClick()
    }){
        Text(
            text = value,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal),
            color = Color.Gray,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }

}


