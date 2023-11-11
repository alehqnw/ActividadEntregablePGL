package com.example.actividadentregablepgl.componentes

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.actividadentregablepgl.R

    @Composable
    fun DialogStatics(
        onDismissRequest: () -> Unit,
        painter:Int,
        texto:String
    ) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ){
                Column(
                    modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = painter),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.height(50.dp)

                    )
                    Text(
                        text = texto,
                        modifier = Modifier.padding(16.dp), fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp))
                    {
                        Text("Volver a jugar")
                    }
                }
            }
        }
    }

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewDialogEs(){
    DialogStatics({ }, R.drawable.telefonodialogo,"Texto de Ejemolo\nNumero 2")
}