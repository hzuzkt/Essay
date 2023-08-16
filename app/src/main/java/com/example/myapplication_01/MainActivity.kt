package com.example.myapplication_01

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.TimerViewModel
import com.example.myapplication_01.ui.theme.MyApplication_01Theme
import java.lang.Math.cos
import java.lang.Math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication_01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication_01Theme {
        Greeting("Android")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val viewModel: TimerViewModel = TimerViewModel()
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                }
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeLeftText(viewModel)
            ProgressCircle(viewModel)
            EditText(viewModel)
            Row {
                StartButton(viewModel)
                StopButton(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditText(viewModel: TimerViewModel) {
    Box(
        modifier = Modifier
            .size(300.dp, 120.dp),
        contentAlignment = Alignment.Center
    ) {
        if (viewModel.status.showEditText()) {
            TextField(
                modifier = Modifier
                    .size(200.dp, 60.dp),
                value = if (viewModel.totalTime == 0L) "" else
                    viewModel.totalTime.toString(),
                onValueChange = viewModel::updateValue,
                label = { Text("Countdown Seconds") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
private fun TimeLeftText(viewModel: TimerViewModel) {
    Text(
        text = TimeFormatUtils.formatTime(viewModel.timeLeft), modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun StartButton(viewModel: TimerViewModel) {
    Button(
        modifier = Modifier
            .width(150.dp)
            .padding(16.dp),
        enabled = viewModel.totalTime > 0,
        onClick = viewModel.status::clickStartButton
    ) {
        Text(text = viewModel.status.startButtonDisplayString())
    }
}

@Composable
private fun StopButton(viewModel: TimerViewModel) {
    Button(
        modifier = Modifier
            .width(150.dp)
            .padding(16.dp),
        enabled = viewModel.status.stopButtonEnabled(),
        onClick = viewModel.status::clickStopButton
    ) {
        Text(text = "Stop")
    }
}

@Composable
fun ProgressCircle(viewModel: TimerViewModel) {
// Circle diameter
    val size = 160.dp
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(size)) {
            val sweepAngle = viewModel.status.progressSweepAngle()
// Circle radius
            val r = size.toPx() / 2
// The width of Ring
            val stokeWidth = 12.dp.toPx()
// Draw dial plate
            drawCircle(
                color = Color.LightGray,
                style = Stroke(
                    width = stokeWidth,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(1.dp.toPx(), 3.dp.toPx())
                    )
                )
            )
            drawArc(
                brush = Brush.sweepGradient(
                    0f to Color.Magenta,
                    0.5f to Color.Blue,
                    0.75f to Color.Green,
                    0.75f to Color.Transparent,
                    1f to Color.Magenta
                ),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = stokeWidth
                ),
                alpha = 0.5f
            )
            // Pointer
            val angle = (360 - sweepAngle) / 180 * Math.PI
            val pointTailLength = 8.dp.toPx()
            drawLine(
                color = Color.Red,
                start = Offset(
                    r + pointTailLength * sin(angle).toFloat(), r +
                            pointTailLength * cos(angle).toFloat()
                ),
                end = Offset(
                    (r - r * sin(angle) - sin(angle) * stokeWidth / 2).toFloat(),
                    (r - r * cos(angle) - cos(angle) * stokeWidth / 2).toFloat()
                ),
                strokeWidth = 2.dp.toPx()
            )
            drawCircle(
                color = Color.Red,
                radius = 5.dp.toPx()
            )
            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx()
            )
        }
    }


}
