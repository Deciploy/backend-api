package com.deciploy.backend.modules.api.screenshot;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.screenshot.dto.ScreenshotRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/screenshot")
@Tag(name = "Screenshot", description = "Screenshot API for save and manage screenshot")
@SecurityRequirement(name = "Bearer Authentication")
public class ScreenshotController {
    @Autowired
    private ScreenshotService screenshotService;

    @PostMapping()
    public ResponseEntity sync(@Valid @RequestBody ScreenshotRequest screenshotRequest) {
        screenshotService.save(screenshotRequest);

        return ApiResponse.success("Screenshot save successfully");
    }

    @GetMapping()
    public ResponseEntity findByUser(
            @RequestParam String user,
            @RequestParam Optional<String> from,
            @RequestParam Optional<String> to
    ) {
        return ApiResponse.data(screenshotService.findByUser(user, from, to));
    }
}
