package com.zimji.auth.api;

import com.zimji.auth.dto.UserDTO;
import com.zimji.auth.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "UserAPI", description = "User API")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAPI {

    IUserService service;

    @PostMapping("/search")
    @Operation(summary = "User - Search", description = "User - Search", tags = {"UserAPI"})
    @Parameter(
            in = ParameterIn.HEADER,
            description = "Addition Key to bypass authen",
            name = "key",
            schema = @Schema(implementation = String.class)
    )
    public ResponseEntity<?> search(
            @Parameter(description = "Param Search") @RequestParam(required = false) String search,
            @Parameter(description = "Param Status") @RequestParam(defaultValue = "-1") Integer status,
            @Parameter(description = "Pageable") Pageable pageable
    ) {
        try {
            return new ResponseEntity<>(service.search(search, status, pageable), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    @Operation(summary = "User - Create", description = "User - Create", tags = {"UserAPI"})
    @Parameter(
            in = ParameterIn.HEADER,
            description = "Addition Key to bypass authen",
            name = "key",
            schema = @Schema(implementation = String.class)
    )
    public ResponseEntity<?> create(
            @Parameter(description = "Payload DTO") @RequestBody UserDTO dto
    ) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "User - Update", description = "User - Update", tags = {"UserAPI"})
    @Parameter(
            in = ParameterIn.HEADER,
            description = "Addition Key to bypass authen",
            name = "key",
            schema = @Schema(implementation = String.class)
    )
    public ResponseEntity<?> update(
            @Parameter(description = "Path Variable ID") @PathVariable Long id,
            @Parameter(description = "Payload DTO") @RequestBody UserDTO dto
    ) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete")
    @Operation(summary = "User - Delete", description = "User - Delete", tags = {"UserAPI"})
    @Parameter(
            in = ParameterIn.HEADER,
            description = "Addition Key to bypass authen",
            name = "key",
            schema = @Schema(implementation = String.class)
    )
    public ResponseEntity<?> delete(
            @Parameter(description = "Payload IDs") @RequestBody Set<Long> ids
    ) {
        try {
            service.deleteByIds(ids);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/get-detail/{id}")
    @Operation(summary = "User - Get Detail", description = "User - Get Detail", tags = {"UserAPI"})
    @Parameter(
            in = ParameterIn.HEADER,
            description = "Addition Key to bypass authen",
            name = "key",
            schema = @Schema(implementation = String.class)
    )
    public ResponseEntity<?> getDetail(
            @Parameter(description = "Path Variable ID") @PathVariable Long id
    ) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
