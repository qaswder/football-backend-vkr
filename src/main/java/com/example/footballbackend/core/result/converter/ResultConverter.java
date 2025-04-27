package com.example.footballbackend.core.result.converter;

import com.example.footballbackend.core.result.dto.Result;
import com.example.footballbackend.core.result.web.contract.ResultView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ResultConverter {
    private final ResultToResultView toResultView;

    public ResultConverter(ResultToResultView toResultView) {
        this.toResultView = toResultView;
    }

    public ResultView toView(@NonNull Result result) {
        return toResultView.convert(result);
    }
}
