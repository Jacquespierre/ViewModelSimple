package com.example.viewmodelsimple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random


//Para hacerlo con viewModel
class BotonYTexto : ViewModel() { //El viewModel no necesita el remember porque ya lo tiene su cliclo de vida
    private var _numero by mutableStateOf(0)
    val numero get() = _numero

    //Genera ese código el backingProperties  -> MutableState<Int>
    //El backingProperties ayuda a proteger la variable para que no se acceda desde fuera.
    fun changeNumber() {
        _numero = Random.nextInt(from = 1, until = 7)
    }
}


@Composable
fun MainScreen() {
    //Utilizo las variables del graddle que están en Properties
    val viewModel: BotonYTexto = viewModel()

    //var numero =0//Se pone número porque es mutable. Hay que hacer que la variable sea un estado
    //Hay que hacer que se recomponga la variable

    //var numero by remember { mutableStateOf(0) }

    //Paso 1-> TiradorDados(numero, { numero = it }) //Número es una variable a la que asigno. Se pasa con una lambda

    //El viewModel.numero surge de cambiar el = por un by en la sentencia -> private val _numero by mutableStateOf(0)

    //  TiradorDados(viewModel.numero, { numero = Random.nextInt(from = 1, until = 7) })

    //Debería quedar así para tener un módulo desacoplado

    //Después se cambia el viewModel del changeNumber
    TiradorDados(viewModel.numero, { viewModel.changeNumber() })


}

@Composable
fun TiradorDados(numero: Int, changeNumber: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(35.dp),//Ocupar toda la pantalla
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly //Distribuye el espacio
    ) {
        //Paso 1->  Button(onClick = { changeNumber(Random.nextInt(from = 1, until = 7) }) {
        Button(onClick =  changeNumber ) {
            Text(text = "Tirar dado")

        }
        Text(text = numero.toString(), fontSize = 80.sp)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}