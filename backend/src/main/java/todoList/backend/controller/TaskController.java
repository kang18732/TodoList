package todoList.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoList.backend.data.dto.ResponseDto;
import todoList.backend.data.dto.TaskDto;
import todoList.backend.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags={"2.Task"})
@RestController
@RequestMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "할일 등록")
    @PostMapping(value = "/task")
    public ResponseEntity<ResponseDto<String>> createTask(
            HttpServletRequest servletRequest, @RequestBody TaskDto taskDto) {
        Long userId = Long.valueOf(String.valueOf(servletRequest.getAttribute("id")));
        taskService.createTask(userId, taskDto);

        ResponseDto<String> responseDto = new ResponseDto<>("할 일이 등록되었습니다.");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "할일 모두 조회")
    @GetMapping(value = "/task-list")
    public ResponseDto<List<TaskDto>> getAllTasks() {
        List<TaskDto> taskDtoList = taskService.getAllTasks();
        return new ResponseDto<>(taskDtoList);
    }

    @ApiOperation(value = "할일 삭제")
    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<ResponseDto<String>> deleteTask(
            HttpServletRequest servletRequest, @PathVariable Long taskId) {
        Long userId = Long.valueOf(String.valueOf(servletRequest.getAttribute("id")));
        taskService.deleteTask(userId, taskId);

        ResponseDto<String> responseDto = new ResponseDto<>("할 일이 삭제되었습니다.");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "할일 변경")
    @PatchMapping(value = "/{taskId}")
    public ResponseEntity<ResponseDto<String>> updateTask(
            HttpServletRequest servletRequest, @PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        Long userId = Long.valueOf(String.valueOf(servletRequest.getAttribute("id")));
        taskService.updateTask(userId, taskId, taskDto);

        ResponseDto<String> responseDto = new ResponseDto<>("할 일이 변경되었습니다.");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

//    @GetMapping(value = "/task-list/{text}")
//    public List<Task> getTaskListByText(@PathVariable String text) {
//        return taskService.findTasksByText(text);
//    }
//
//    @DeleteMapping(value = "/task/{id}")
//    public Boolean deleteTask(@PathVariable Long id) {
//        return taskService.removeTask(id);
//    }
}
