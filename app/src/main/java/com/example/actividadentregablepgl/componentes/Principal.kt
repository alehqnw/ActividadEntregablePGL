package com.example.actividadentregablepgl.componentes

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
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


    //Instanciar las variables
    var pulsa:Boolean= false //Ha pulsado true o false
    var pulsado:Boolean by remember{ mutableStateOf(false)} //Ha acertado o no
    var mostrar by remember{ mutableStateOf(false) }
    var mostrarEs by remember{ mutableStateOf(false) }
    var siError by remember{ mutableStateOf("")} // Muestra mensaje de error
    var esError:Int by remember{ mutableStateOf(0)} //Cuenta los errores
    var esRespuesta:Int by remember{ mutableStateOf(0)} //Cuenta los aciertos
    if(mostrar) {
        Dialogo({ mostrar=false },R.drawable.telefonodialogo,siError,pulsado)
    }
    var indice by remember{ mutableStateOf(0) }

    if(Preguntas.size==indice){
        indice=0
        esRespuesta=0
        esError=0
    }
    if(mostrarEs){
        DialogStatics(onDismissRequest = { mostrarEs = false },
            painter = R.drawable.telefonodialogo,
            texto = "Tus estadisticas son:\n"+
                    "Aciertos: ${esRespuesta}\n"+
                    "Fallos: ${esError}")
    }

  Column(
      Modifier
          .fillMaxSize(1f)
          .fillMaxWidth(1f)
          .fillMaxHeight(1f)) {

      Text(text = Preguntas.get(indice).Titulo+" número "+(indice+1),
            modifier = Modifier.align(CenterHorizontally).padding(vertical = 10.dp)
      )

      Button(onClick = {
            mostrarEs=true }
          ,colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier.fillMaxWidth(1f)) {
            Text(text = "Estadisticas")
      }
    Image(painter = painterResource(id = Preguntas.get(indice).Imagen), contentDescription = "Es una imagen de ejemplo",
        modifier = Modifier
            .size(500.dp)
            .fillMaxWidth(1f))
    Text(text = Preguntas.get(indice).Contenido,
        Modifier
            .align(CenterHorizontally))
      Row(Modifier.padding(horizontal = 110.dp, vertical = 50.dp)){
          Button(onClick = { pulsado = true
              pulsa = true;

          }, colors = ButtonDefaults.buttonColors(containerColor= Color.Green)) {
              Text(text = "true")

          }
          Button(onClick = { pulsado = false
              pulsa = true;
              System.out.println("Se ha pulsado")
          }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
              Text(text= "false")

          }
      }

      Spacer(modifier = Modifier.height(10.dp))
      Button(onClick = {
          if(!pulsa){
              siError="Tienes que responder con true o false"
              mostrar = true

          }else{
                  if(Preguntas.get(indice).acierto.equals(pulsado)){
                      siError=Preguntas.get(indice).Respuesta
                      pulsado=true
                      mostrar = true
                      indice++
                      esRespuesta++
                  }
                  else{
                      pulsado = false
                      mostrar = true
                      siError=Preguntas.get(indice).Error
                      esError++
                      indice++
                  }


          }
      },Modifier.fillMaxWidth(1f)) {
          Text(text = "Next")
          Icon(painter = painterResource(
                id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription ="FlechaAlaDerecha" )
      }
  }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewInicio(){
    Inicio()
}
fun Randomizer():Int{
    val secureRandom = SecureRandom()
    val entero = secureRandom.nextInt(3)
    return entero
}
