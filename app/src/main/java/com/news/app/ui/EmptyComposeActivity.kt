package com.news.app.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.news.app.ui.theme.NewsSampleAppTheme

class EmptyComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsSampleAppTheme {
                MyApp()
            }
        }
    }

    companion object {
        fun launch(context: Activity) {
            context.startActivity(Intent(context, EmptyComposeActivity::class.java))
        }
    }
}

@Composable
fun MyApp(
    names: List<String> = listOf(
        "World", "Compose", "Aman", "Vaibhav", "Jayesh", "JK"
    )
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        names.forEach { Greeting(name = it) }
    }
}

@Composable
private fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.weight(1F)) {
                Text(text = "Hello, ")
                Text(text = name)
            }

            OutlinedButton(
                onClick = { /* TODO */ }
            ) {
                Text("Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsSampleAppTheme {
        MyApp()
    }
}