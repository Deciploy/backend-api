package com.deciploy.backend.modules.api.activity.dto;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public record ScoreFilter(
        @RequestParam Optional<String> from,

        @RequestParam Optional<String> to
) {
}
