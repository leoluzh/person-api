package com.lambdasys.person.api.controller;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.exception.PersonNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Person API" , description = "Manage person")
public interface PersonControllerDocs {

    @Operation(description = "Person creating operation")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Success person creation.",
                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "400", description = "Missing required fields or wrong field range value.", content = @Content)
    })
    ResponseEntity<PersonDto> create(PersonDto personDto);

    @Operation(description = "Return a list of all persons registered in the system.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200" , description = "List of all movies registered in the system."),
    })
    ResponseEntity<List<PersonDto>> listAll();

    @Operation(description = "Returns person found by a given id")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Success person found in the system.",
                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "404", description = "Person with given id not found.", content = @Content)
    })
    ResponseEntity<PersonDto> findById(Long id) throws PersonNotFoundException;

    @Operation(description="Delete an person found by a given valid id")
    @ApiResponses(value={
            @ApiResponse(responseCode = "204", description = "Success person deleted in the system." , content = @Content(mediaType = "application/json")) ,
            @ApiResponse(responseCode = "404", description = "Person with given id not found.", content = @Content)
    })
    void deleteById(Long id) throws PersonNotFoundException;

    @Operation(description = "Update person by a given id and with request body")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Success person found in the system and updated.",
                    content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "404", description = "Person with given id not found.", content = @Content)
    })
    ResponseEntity<PersonDto> update(Long id, PersonDto personDto) throws PersonNotFoundException;

}
