package com.deciploy.backend.modules.api.tracking.dto;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public record ActivityFilter(
        @RequestParam Optional<String> team,

        @RequestParam Optional<String> user,

        @RequestParam Optional<String> application,

        @RequestParam Optional<String> type,

        @RequestParam Optional<String> from,

        @RequestParam Optional<String> to
) {
}
