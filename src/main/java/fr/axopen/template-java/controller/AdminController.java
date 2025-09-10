package fr.axopen.template-java.controller;

import fr.axopen.template-java.service.CacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.axopen.template-java.utils.constants.ApiPathConstants.ADMIN_PATH;

@RestController
@RequestMapping(ADMIN_PATH)
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin API")
public class AdminController {

    // Services
    private final CacheService cacheService;

    @PostMapping("/clear-cache")
    @Operation(summary = "Clear cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cache cleared successfully"),
            @ApiResponse(responseCode = "500", description = "An error occurred during cache clearing")
    })
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> clearCache() {
        cacheService.clearCache();
        return ResponseEntity.noContent().build();
    }

}
