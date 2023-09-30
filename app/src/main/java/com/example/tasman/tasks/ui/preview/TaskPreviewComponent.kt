package com.example.tasman.tasks.ui.preview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.tasman.tasks.model.Task

@Composable
@Preview(showBackground = true)
fun TaskPreview(
    @PreviewParameter(TaskProvider::class) task: Task,
    modifier: Modifier = Modifier,
    onCompletedChanged: (Long, Boolean) -> Unit = { _, _ -> }
) = Row(
    modifier = modifier
        .padding(vertical = 8.dp)
        .padding(end = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
) {
    Checkbox(checked = task.isCompleted, onCheckedChange = { onCompletedChanged(task.id, it) })
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = task.title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

class TaskProvider : PreviewParameterProvider<Task> {

    override val values: Sequence<Task> = sequenceOf(
        Task(id = 0, title = "Design Dribbble concept", isCompleted = true),
        Task(id = 0, title = lorem),
    )
}

const val lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at mattis ex. Sed imperdiet," +
        "sem vel cursus eleifend, libero urna rhoncus ipsum, in pharetra ex ex non diam. Aliquam quam elit," +
        "vulputate in felis a, laoreet tristique lectus. Nulla quis enim eu ante aliquam interdum non vel diam." +
        "Donec venenatis urna orci. Ut ante augue, rhoncus non mauris a, commodo congue nisi. Fusce fermentum lacus ac" +
        "mi dignissim, nec congue justo pharetra. Vestibulum non convallis orci. Aliquam porta cursus sem, eu tincidunt" +
        "elit aliquet eget." +
        "Donec id pretium nulla. Praesent vitae semper lorem. Sed condimentum sapien in bibendum tempus." +
        "Sed id fringilla est, in pharetra metus. Sed nec ligula nec nisi ornare hendrerit sit amet eu sapien." +
        "Aliquam venenatis tellus vel nulla consectetur, id commodo nisi ornare. Nulla nisl odio," +
        "sagittis ac ornare ac, luctus a erat. Maecenas ut vehicula neque. Vestibulum auctor ligula nec ultricies porta." +
        "Sed volutpat, sapien sit amet volutpat tempus, lacus odio cursus lectus, eget imperdiet leo enim sed tortor." +
        "Nullam ut lectus ut orci scelerisque laoreet eget a orci. Duis accumsan imperdiet lorem, et pretium turpis." +
        "Donec tempus porta leo at scelerisque." +
        "Quisque et metus ac leo tempor fringilla. Donec sed dolor lobortis, dictum libero at, rhoncus dui." +
        "Duis at augue magna. Quisque non turpis vel nunc viverra ullamcorper. Donec eleifend eu tellus ac venenatis." +
        "Sed nec maximus risus, iaculis viverra quam. Aenean consequat, nunc nec ultricies accumsan, massa nunc tempus" +
        "mauris, eu viverra libero diam consequat turpis. In elit enim, feugiat dignissim sollicitudin a," +
        "consectetur ut nulla. Sed dignissim, ante ac tristique posuere, sapien tortor scelerisque diam," +
        "eget venenatis eros dui quis lectus. Aliquam et mauris nibh. Donec diam lectus, vestibulum sed elit eget," +
        "faucibus vestibulum diam. Nulla vel orci id dolor dapibus maximus. Vestibulum bibendum erat in erat" +
        "imperdiet faucibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aenean pretium ultrices" +
        "elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
        "Maecenas in condimentum enim. Curabitur eleifend nunc velit, rhoncus congue justo porttitor ac." +
        "Ut non neque orci. Aenean laoreet dui lobortis, blandit mauris in, placerat metus. Cras interdum nunc eget" +
        "eros posuere, sed mollis ex facilisis. Mauris pellentesque est lacinia, ornare dui sit amet, pulvinar odio." +
        "Nulla turpis diam, vulputate in vehicula nec, accumsan nec diam. Maecenas et ex ac nisi pretium volutpat." +
        "Mauris vitae nulla id nunc facilisis cursus non at nisl. Praesent leo purus, dictum gravida augue sit amet," +
        "cursus vulputate dui. Praesent non justo quis ex efficitur porttitor in id erat. Praesent eu libero quis nisl" +
        "tempus malesuada fringilla aliquam turpis. In hac habitasse platea dictumst. Duis nisi justo, placerat a" +
        "eleifend ut, ultricies at mauris. Vivamus interdum mattis massa, sit amet volutpat ipsum porta ut." +
        "Nunc sollicitudin porta varius." +
        "Integer feugiat risus eu mi efficitur, sit amet consequat neque dignissim. Aliquam quam eros, aliquet id" +
        "leo nec, blandit posuere orci. Sed vulputate consectetur dui eget lacinia. Sed semper lorem sit amet dolor" +
        "luctus lacinia. In at quam lorem. Sed magna turpis, viverra vitae molestie id, congue eu diam. Pellentesque a" +
        "lorem convallis, mollis justo sit amet, rutrum felis. In imperdiet velit hendrerit, lobortis justo in," +
        "imperdiet neque. Sed felis ex, molestie vel hendrerit et, elementum eget sem. Proin posuere vestibulum tempus." +
        "Ut euismod tempus magna non laoreet. Aliquam a neque sagittis, sodales sem ac, condimentum mi. Aliquam finibus," +
        "magna sed aliquam aliquet, ante purus fermentum augue, sed tempus elit nisi id nisl. In congue, purus sed" +
        "vulputate hendrerit, lorem lacus dignissim libero, malesuada dictum tellus nunc eget dolor. Proin ac dolor" +
        "in erat lobortis porttitor quis eu ipsum. Vestibulum augue leo, tincidunt et mollis congue, auctor vitae enim."