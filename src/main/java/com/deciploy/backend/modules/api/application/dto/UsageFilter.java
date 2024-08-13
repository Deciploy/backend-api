package com.deciploy.backend.modules.api.application.dto;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public record UsageFilter(
        @RequestParam Optional<String> from,

        @RequestParam Optional<String> to
) {
}
