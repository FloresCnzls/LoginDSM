package com.example.logindsm.screen

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.logindsm.R

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Validaciones
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    fun validate(): Boolean {
        var isValid = true

        // Validación de correo electrónico
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Correo electrónico inválido"
            isValid = false
        } else {
            emailError = null
        }

        // Validación de contraseña
        if (password.isEmpty()) {
            passwordError = "La contraseña no puede estar vacía"
            isValid = false
        } else {
            passwordError = null
        }

        return isValid
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.udb),
            contentDescription = "Login BANNER",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "¡Hola de nuevo!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Inicia sesión en tu cuenta",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 22.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Campo de correo electrónico
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Correo Electrónico") },
            modifier = modifier.fillMaxWidth(),
            isError = emailError != null
        )
        if (emailError != null) {
            Text(
                text = emailError!!,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Contraseña") },
            modifier = modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null
        )
        if (passwordError != null) {
            Text(
                text = passwordError!!,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón de inicio de sesión
        Button(
            onClick = {
                if (validate()) {
                    // Si la validación es exitosa, navega a la siguiente pantalla
                    navController.navigate("welcome/${email}")
                }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Text(text = "Iniciar Sesión", fontSize = 22.sp)
        }
    }
}