package com.example.actividadentregablepgl.componentes

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.actividadentregablepgl.R

import java.security.SecureRandom


@Composable
fun Inicio() {
    //Clase con que haga la función de almacen preguntas, imagenes y la respuesta correcta
    var Preguntas:ArrayList<DatosJuego> = ArrayList()

    Preguntas.add(DatosJuego("Pregunta",
        "¿HollowKnight es el mejor metroidvania?",
        true,
        R.drawable.hollowknight,
        "Error, HollowKnight es el mejor metroidvania",
        "Has acertado, HollowKnight es el mejor"))

    Preguntas.add(DatosJuego("Pregunta",
        "¿Es el League of legends es goty del año of the year?",
        false,
        R.drawable.lol,
        "Error, el lol no es el goty del año",
        "Vaya, parece que sabes de lo que hablas"))

    Preguntas.add(DatosJuego("Pregunta",
        "¿Unity la ha liado recientemente mucho?",
        true,
        R.drawable.unity,
        "Si la ha liado, hay que informarse más",
        "Bien, estás al corriente del asunto"))
    Preguntas.add(DatosJuego("Pregunta",
        "¿Illojuan es LMDSHOW el amigo del novio de Massi?",
        true,
        R.drawable.illojuan,
        "No estás nada informado 🤡🤡🤡",
        "Correcto, de hecho illojuan es LMDSHOW y el novio de Massi"))
    Preguntas.add(DatosJuego("Pregunta",
        "¿ULTRAKILL es el mejor BOOMERSHOOTER?",
        true,
        R.drawable.ultrakill,
        "Me parece que no estás preparado para esta conversacion 🥱🥱",
        "SI, EL MEJOR BOOMERSHOOTER ES ULTRAKILL deberían darte un premio"))

    //Instanciar las variables
    var pulsa:Boolean= false //Ha pulsado true o false
    var pulsado:Boolean by remember{ mutableStateOf(false)} //Ha acertado o no
    var mostrar by remember{ mutableStateOf(false) }
    var mostrarEs by remember{ mutableStateOf(false) }
    var siError by remember{ mutableStateOf("")} // Muestra mensaje de error
    var esError:Int by remember{ mutableStateOf(0)} //Cuenta los errores
    var esRespuesta:Int by remember{ mutableStateOf(0)} //Cuenta los aciertos
    var indice by remember{ mutableStateOf(Randomizer(Preguntas.size)) } //INDICE
    var previewindice:ArrayList<Int> = ArrayList()
    var curr:Int by remember{ mutableStateOf(0)}
    if(mostrar) {
        Dialogo({ mostrar=false },R.drawable.telefonodialogo,siError,pulsado)
    }
    if(mostrarEs){
        DialogStatics(onDismissRequest = { mostrarEs = false },
            painter = R.drawable.telefonoestadistica,
            texto = "Tus estadisticas son:\n"+
                    "Aciertos: ${esRespuesta}\n"+
                    "Fallos: ${esError}")
    }

    if(Preguntas.size==indice){
        indice= Randomizer(Preguntas.size)
    }

  Column(
      Modifier
          .fillMaxSize(1f)
          .fillMaxWidth(1f)
          .fillMaxHeight(1f)) {

      Text(text = Preguntas.get(indice).Titulo+" número "+(indice+1),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 10.dp)
                .weight(0.5f)
                .fillMaxWidth()
      )

      Button(onClick = {
            mostrarEs=true }
          ,colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()) {
            Text(text = "Estadisticas")
      }
      //IMAGEN
      Box(modifier = Modifier.weight(5f)){
          Image(painter = painterResource(id = Preguntas.get(indice).Imagen), contentDescription = "Es una imagen de ejemplo",
              modifier = Modifier
                  .size(400.dp)
                  .fillMaxSize())
      }

    Text(text = Preguntas.get(indice).Contenido,
        Modifier
            .align(CenterHorizontally)
            .weight(0.5f))

      //BOTON TRUE O FALSE
      Row( horizontalArrangement = Arrangement.Center, modifier = Modifier
          .weight(2f)){
          var verde = ButtonDefaults.buttonColors(containerColor = Color.Green)
          Button(onClick = { pulsado = true
              pulsa = true;
          }, colors = ButtonDefaults.buttonColors(Color.Green),
              modifier = Modifier.weight(1f),
             ) {
              Text(text = "true")

          }
          Button(onClick = { pulsado = false
              pulsa = true;
              System.out.println("Se ha pulsado")
          }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
              modifier = Modifier.weight(1f)) {
              Text(text= "false")

          }
      }
      //BOTON NEXT Y PREV
      Row(horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.weight(2f).align(Alignment.End)) {
          Button(onClick = { indice = (curr) },
              modifier = Modifier.weight(1f)){
              Text(text = "PREV")
              Icon(painter = painterResource(
                  id = R.drawable.baseline_arrow_back_ios_new_24),
                  contentDescription ="FlechaAlaDerecha" )

          }

          Button(onClick = {
              if(!pulsa){
                  siError="Tienes que responder con true o false"
                  mostrar = true

              }else{
                  if(Preguntas.get(indice).acierto.equals(pulsado)){
                      siError=Preguntas.get(indice).Respuesta
                      pulsado=true
                      mostrar = true
                      indice = Randomizer(Preguntas.size)
                      previewindice.add(indice)
                      curr++
                      esRespuesta++
                  }
                  else{
                      pulsado = false
                      mostrar = true
                      siError=Preguntas.get(indice).Error
                      esError++
                      indice = Randomizer(Preguntas.size)
                      previewindice.add(indice)
                  }


              }
          },modifier = Modifier.weight(1f)
              ) {
              Text(text = "Next")
              Icon(painter = painterResource(
                  id = R.drawable.baseline_keyboard_arrow_right_24),
                  contentDescription ="FlechaAlaDerecha" )
          }

      }

  }
}



@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_4_xl")
@Composable
fun PreviewInicio(){
    Inicio()
}

fun Randomizer(max:Int):Int{
    val secureRandom = SecureRandom()
    val entero = secureRandom.nextInt(max)
    return entero
}
