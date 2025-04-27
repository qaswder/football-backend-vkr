package com.example.footballbackend.core.staff.web;

import com.example.footballbackend.core.staff.handler.StaffHandler;
import com.example.footballbackend.core.staff.web.contract.StaffReq;
import com.example.footballbackend.core.staff.web.contract.StaffView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
@Tag(name = "Персонал")
public class StaffController {
    private final StaffHandler handler;

    public StaffController(StaffHandler handler){
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение персонала по id")
    public StaffView getStaffById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetStaffById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всего персонала")
    public Page<StaffView> getAllStaff(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllStaff(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Создание персонала")
    public StaffView createStaff(@Valid @RequestBody @NotNull StaffReq req) {
        return handler.handlerCreateStaff(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение персонала")
    public StaffView updateStaffById(@PathVariable @NotNull Integer id,
                                     @Valid @RequestBody @NotNull StaffReq req) {
        return handler.handlerUpdateStaffById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление персонала")
    public void deleteStaffById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteStaffById(id);
    }
}
