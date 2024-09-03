package com.challenge.enrollment.enrolleeservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.challenge.enrollment.enrolleeservice.entity.Dependent;
import com.challenge.enrollment.enrolleeservice.entity.Enrollee;
import com.challenge.enrollment.enrolleeservice.service.EnrolleeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/enrollees")
@Tag(name = "enrollees", description = "The Enrollee API documentation with annotations and operations.")
public class EnrolleeController {

    @Autowired
    EnrolleeService enrolleeService;
    @Operation(summary = "Get all enrollees.")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieval successful!", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Enrollee.class))) }),
        @ApiResponse(responseCode = "404", description = "No enrollees found!", content = @Content) })
    @GetMapping
    /***
     * Retrieve all enrollees in system or datasource.
     * 
     * @return A list of all avialiable enrollees.
     */
    public ResponseEntity<List<Enrollee>> getAllEnrollees() {
        return enrolleeService.getAll();
    }

    @Operation(summary = "Create a enrollee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enrollee created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Enrollee.class)) }),
            @ApiResponse(responseCode = "404", description = "Enrollee could not be created!", content = @Content) })
    @PostMapping
    /***
     * Add a new enrollee
     * 
     */
    public ResponseEntity<Enrollee> addEnrollee(
            @Parameter(description = "Enrollee object to be created") @RequestBody @Valid Enrollee enrollee) {
        return enrolleeService.add(enrollee);
    }

    @Operation(summary = "Get a enrollee by enrollee id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Enrollee found!", content = { 
                @Content(mediaType = "application/json", schema = @Schema(implementation = Enrollee.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied!", content = @Content), 
        @ApiResponse(responseCode = "404", description = "Enrollee could not be found by id!", content = @Content) })
    @GetMapping("/{enrolleeId}")
    public ResponseEntity<Enrollee> getEnrolleeById(@Parameter(description="id of Enrollee to be searched")@PathVariable ("enrolleeId") int enrolleeId) {
        return enrolleeService.getById(enrolleeId);
    }

    @Operation(summary = "Update a enrollee")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Enrollee updated successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Enrollee.class))}),
        @ApiResponse(responseCode = "404", description = "No Enrollee exists with given id", content = @Content) })
    @PutMapping("/{enrolleeId}")
    public ResponseEntity<Enrollee> updateEnrollee(@Parameter(description="id of Enrollee to be updated.")@PathVariable("enrolleeId") int enrolleeId, @RequestBody Enrollee enrollee) {
          return enrolleeService.update(enrolleeId,enrollee);
      }

    /***
     * Delete an enrollee by id.
     * 
     * Remove an enrollee entirelys, as well as dependents belonging to that
     * enrollee.
     *
     */
    @Operation(summary = "Delete a enrollee.")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Enrollee deleted"),
            @ApiResponse(responseCode = "404", description = "Bad request!", content = @Content) })
    @DeleteMapping("/{enrolleeId}")
    public ResponseEntity<Void> deleteEnrollee(
            @Parameter(description = "id of Enrollee to be deleted.") @PathVariable("enrolleeId") int enrolleeId) {
            return enrolleeService.delete(enrolleeId);
    }

    @Operation(summary = "Add a new dependent to a enrollee by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dependent created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Enrollee.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request!", content = @Content)
        })
    @PostMapping("/{enrolleeId}/dependents")
    public ResponseEntity<Dependent> addDependentByEnrollee(@Parameter(description="id of Enrollee to add dependent to.")@PathVariable("enrolleeId") int enrolleeId, @RequestBody Dependent dependent) {
     return enrolleeService.addDependent(enrolleeId, dependent);
    }

    @Operation(summary = "Delete a dependent by id under enrollee.")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Dependent deleted"),
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content) })
    @DeleteMapping("/{enrolleeId}/dependents/{dependentId}")
    public ResponseEntity<Void> deleteDependentByEnrolleeId(@Parameter(description="id of Enrollee to delete dependent from.")@PathVariable("enrolleeId") int enrolleeId, 
    @Parameter(description="id of Enrollee to delete dependent from.") @PathVariable("dependentId") int dependentId) 
    {
       return enrolleeService.deleteDependentByEnrolleeId(enrolleeId, dependentId);      
    }

    @Operation(summary = "Update a Depedent")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dependent updated successfully!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Dependent.class)) }),
            @ApiResponse(responseCode = "404", description = "Bad Request!", content = @Content) })
    @PutMapping("{enrolleeId}/dependents/{dependentId}")
    public ResponseEntity<Dependent> updateDependent(
            @Parameter(description = "id of dependent to be updated.") @PathVariable("enrolleeId") int enrolleeId, @Parameter(description = "id of dependent to be updated") @PathVariable("dependentId") int dependentId,
            @RequestBody Dependent dependent) {
                return enrolleeService.updateDependent(enrolleeId, dependentId, dependent);      
    }


}