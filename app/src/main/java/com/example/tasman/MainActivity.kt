package com.example.tasman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasman.tasks.ui.create.CreateTaskScreen
import com.example.tasman.tasks.ui.details.TaskDetailsDestination
import com.example.tasman.tasks.ui.list.TasksListScreen
import com.example.tasman.ui.theme.TasmanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasmanTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val controller = rememberNavController()
                    NavHost(navController = controller, startDestination = "list") {
                        composable("list") {

                            TasksListScreen(
                                onTaskClicked = TaskDetailsDestination(controller)::navigate,
                                onNewTaskClicked = { controller.navigate("create") }
                            )
                        }

                        composable("create") {
                            CreateTaskScreen(onCompleted = controller::popBackStack)
                        }

                        TaskDetailsDestination(controller).register(this)
                    }
                }
            }
        }
    }
}