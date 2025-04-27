package com.example.footballbackend.core.staff.converter;

import com.example.footballbackend.core.staff.dto.Staff;
import com.example.footballbackend.core.staff.web.contract.StaffView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StaffConverter {
    private final StaffToStaffView toCoachView;

    public StaffConverter(StaffToStaffView toCoachView){
        this.toCoachView = toCoachView;
    }

    public StaffView toView(@NonNull Staff staff){
        return toCoachView.convert(staff);
    }
}
