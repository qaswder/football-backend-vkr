package com.example.footballbackend.core.staff;

import com.example.footballbackend.core.staff.dto.Staff;
import com.example.footballbackend.core.staff.dto.StaffRepo;
import com.example.footballbackend.error.ConflictResourceException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepo staffRepo;
    private final MessageUtil messageUtil;

    public StaffService(StaffRepo staffRepo,
                        MessageUtil messageUtil){
        this.staffRepo = staffRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Staff> getAllStaff(Pageable pageable) {
        return staffRepo.findAllStaff(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Staff> getStaffById(@NonNull Integer id) {
        return staffRepo.findById(id);
    }

    @Transactional
    public Staff saveStaff(@NonNull Staff staff) {
        try {
            return staffRepo.save(staff);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Staff getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Staff() : staffRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteStaffById(@NonNull Integer id) {
        staffRepo.deleteById(id);
    }
}
