package todoList.backend.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import todoList.backend.data.entity.Task;

@Getter
@NoArgsConstructor
public class TaskDto {

    private Long taskId;
    private String text;
    private Boolean done;

    public TaskDto(Task task) {
        this.taskId = task.getId();
        this.text = task.getText();
        this.done = task.getDone();
    }
}
