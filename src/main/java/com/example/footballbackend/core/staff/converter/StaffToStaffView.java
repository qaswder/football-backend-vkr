package com.example.footballbackend.core.staff.converter;

import com.example.footballbackend.core.staff.dto.Staff;
import com.example.footballbackend.core.staff.web.contract.StaffView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StaffToStaffView implements Converter<Staff, StaffView> {
    @Override
    public StaffView convert(Staff source) {
        return new StaffView(
                source.getId(),
                source.getSurname(),
                source.getName(),
                source.getPatronymic(),
                source.getBirthdate(),
                source.getStaffRole().getCode(),
                source.getPhotoUrl(),
                source.getTeam().getTeamName()
        );
    }
}
