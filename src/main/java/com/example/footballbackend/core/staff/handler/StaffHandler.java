package com.example.footballbackend.core.staff.handler;

import com.example.footballbackend.core.staff.StaffService;
import com.example.footballbackend.core.staff.converter.StaffConverter;
import com.example.footballbackend.core.staff.dto.RoleStaffEnum;
import com.example.footballbackend.core.staff.dto.Staff;
import com.example.footballbackend.core.staff.web.contract.StaffReq;
import com.example.footballbackend.core.staff.web.contract.StaffView;
import com.example.footballbackend.core.team.TeamService;
import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StaffHandler {
    private final StaffConverter converter;
    private final StaffService service;
    private final TeamService teamService;
    private final MessageUtil messageUtil;

    public StaffHandler(StaffConverter converter,
                        StaffService service,
                        TeamService teamService,
                        MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.teamService = teamService;
        this.messageUtil = messageUtil;
    }

    public StaffView handlerGetStaffById(@NonNull Integer id) {
        return converter.toView(
                service.getStaffById(id)
                        .orElseThrow(() -> new NotFoundException(
                                messageUtil.getMessage("staff.id.not-found", id))
                        )
        );
    }

    public Page<StaffView> handlerGetAllStaff(@NonNull Pageable pageable) {
        Page<Staff> staff = service.getAllStaff(pageable);
        return staff.map(converter::toView);
    }

    public StaffView handlerCreateStaff(@NonNull StaffReq req) {
        final Staff staff = new Staff();
        final Team team = teamService.getTeamById(req.teamId())
                .orElseThrow(() -> new NotFoundException(
                        messageUtil.getMessage("team.id.not-found", req.teamId())
                ));

        staff.setSurname(req.surname());
        staff.setName(req.name());
        staff.setPatronymic(req.patronymic());
        staff.setBirthdate(req.birthdate());
        staff.setStaffRole(RoleStaffEnum.getStaffByCode(req.staffRole()));
        staff.setTeam(team);

        return converter.toView(
                service.saveStaff(staff)
        );
    }

    public StaffView handlerUpdateStaffById(@NonNull Integer id, @NonNull StaffReq req) {
        final Staff prototype = service.getReferenceOrNew(id);
        final Team team = teamService.getTeamById(req.teamId())
                .orElseThrow(() -> new NotFoundException(
                        messageUtil.getMessage("team.id.not-found", req.teamId())
                ));

        prototype.setSurname(req.surname());
        prototype.setName(req.name());
        prototype.setPatronymic(req.patronymic());
        prototype.setBirthdate(req.birthdate());
        prototype.setStaffRole(RoleStaffEnum.getStaffByCode(req.staffRole()));
        prototype.setTeam(team);

        return converter.toView(
                service.saveStaff(prototype)
        );
    }

    public void handlerDeleteStaffById(@NonNull Integer id) {
        service.deleteStaffById(id);
    }

}
