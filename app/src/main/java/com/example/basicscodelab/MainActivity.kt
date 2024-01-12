package com.example.basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BasicsCodelabTheme {
                        MyApp( modifier =  Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}


//<----------------Let's create the onboarding screen--------------
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Basics Codelab!"
        )
        Button(
            onClick = onContinueClicked,
            modifier.padding(vertical = 24.dp)
        ) {
            Text(text = "Continue")
        }
    }
}

@Preview(name = "Onboarding Screen", showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingScreenPreview() {
    BasicsCodelabTheme {
        OnboardingScreen( onContinueClicked = {} )
    }
}


//<-----------------My App composable---------------------------
@Composable
fun MyApp( modifier: Modifier = Modifier) {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnBoarding) {
            OnboardingScreen( onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, name = "Prev2")
@Composable
fun MyAppPreview() {
    MyApp(Modifier.fillMaxSize())
}


//<------------------The Greetings composable------------------
@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(50){"$it"}
) {
    LazyColumn (modifier.padding(vertical = 4.dp)){
        items(items = names) { username ->
            Greeting(name = username)
        }
    }
}

@Preview(
    name = "GreetingsPreviewDarkMode",
    widthDp = 320,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}


@Composable
fun CardContent(
    name: String
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row (
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column (
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ){
            Text(text = "Hello ")
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium
                    .copy( fontWeight = FontWeight.ExtraBold )
            )
            if (expanded) Text(
                text = (
                    "This is a compose app, " +
                    "my first app in compose!"
                ).repeat(4)
            )
        }

        IconButton(
            onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    stringResource(id = R.string.show_less)
                } else {
                    stringResource(id = R.string.show_more)
                }
            )
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card (
        modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors( containerColor = MaterialTheme.colorScheme.primary )
    ){
        CardContent(name = name)
    }
}
//@Preview(showBackground = true, name = "Prev1")
//@Composable
//fun GreetingPreview() {
//    BasicsCodelabTheme {
//        Greeting("world")
//    }
//}