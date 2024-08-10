package com.deciploy.backend.modules.api.decision.dto;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public record WorkTimeFilter(
        @RequestParam Optional<String> team,

        @RequestParam Optional<String> from,

        @RequestParam Optional<String> to
) {
}
