package com.example.footballbackend.core.result;

import com.example.footballbackend.core.result.dto.Result;
import com.example.footballbackend.core.result.dto.ResultRepo;
import com.example.footballbackend.error.ConflictResourceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ResultService {
    private final ResultRepo resultRepo;

    public ResultService(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    @Transactional(readOnly = true)
    public Page<Result> getAllResult(Pageable pageable) {
        return resultRepo.findAllResult(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Result> getResultById(@NonNull Integer id) {
        return resultRepo.findById(id);
    }

    @Transactional
    public Result saveResult(@NonNull Result result) {
        try {
            return resultRepo.save(result);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Result getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Result() : resultRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteResultById(@NonNull Integer id) {
        resultRepo.deleteById(id);
    }
}
