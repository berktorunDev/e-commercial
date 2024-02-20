package com.app.ecommercial.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommercial.model.dto.request.CreateAnnouncementRequestDTO;
import com.app.ecommercial.model.dto.request.UpdateAnnouncementRequestDTO;
import com.app.ecommercial.model.entity.User;
import com.app.ecommercial.service.AnnouncementService;
import com.app.ecommercial.util.handler.GlobalResponseHandler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement")
public class AnnouncementController {

        private final AnnouncementService announcementService;

        @GetMapping("/")
        public ResponseEntity<Object> getAllAnnouncements() {
                try {
                        var response = announcementService.getAllAnnouncements();
                        return GlobalResponseHandler.successResponse(HttpStatus.OK,
                                        "All announcements retrieved successfully", response);
                } catch (Exception e) {
                        return GlobalResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> getAnnouncementById(@PathVariable Long id) {
                try {
                        var response = announcementService.getAnnouncement(id);
                        return GlobalResponseHandler.successResponse(HttpStatus.OK,
                                        "Announcement retrieved successfully", response);
                } catch (Exception e) {
                        return GlobalResponseHandler.errorResponse(HttpStatus.NOT_FOUND, e.getMessage());
                }
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<Object> updateAnnouncement(@PathVariable Long id,
                        @Valid @RequestBody UpdateAnnouncementRequestDTO request) {
                try {
                        var response = announcementService.updateAnnouncement(id, request);
                        return GlobalResponseHandler.successResponse(HttpStatus.OK, "Announcement updated successfully",
                                        response);
                } catch (Exception e) {
                        return GlobalResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
                }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Object> deleteAnnouncement(@PathVariable Long id) {
                try {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        User userDetails = (User) authentication.getPrincipal();
                        UUID userId = userDetails.getId();
                        var response = announcementService.deleteAnnouncement(userId, id);
                        return GlobalResponseHandler.successResponse(HttpStatus.OK, "Announcement deleted successfully",
                                        response);
                } catch (Exception e) {
                        return GlobalResponseHandler.errorResponse(HttpStatus.NOT_FOUND, e.getMessage());
                }
        }

        @PutMapping("/add-favorites/{announcementId}")
        public ResponseEntity<Object> addAnnouncementToFavorites(@PathVariable Long announcementId) {
                try {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        User userDetails = (User) authentication.getPrincipal();
                        UUID userId = userDetails.getId();
                        var response = announcementService.addAnnouncementToFavorites(userId, announcementId);
                        return GlobalResponseHandler.successResponse(HttpStatus.OK,
                                        "Announcement added to favorites successfully", response);
                } catch (Exception e) {
                        return GlobalResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
                }
        }

        @PutMapping("/remove-favorites/{announcementId}")
        public ResponseEntity<Object> removeAnnouncementFromFavorites(@PathVariable Long announcementId) {
                try {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        User userDetails = (User) authentication.getPrincipal();
                        UUID userId = userDetails.getId();
                        var response = announcementService.removeAnnouncementFromFavorites(userId, announcementId);
                        return GlobalResponseHandler.successResponse(HttpStatus.OK,
                                        "Announcement removed from favorites successfully", response);
                } catch (Exception e) {
                        return GlobalResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
                }
        }

        @PostMapping("/create")
        public ResponseEntity<Object> createAnnouncement(@Valid @RequestBody CreateAnnouncementRequestDTO request) {
                try {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                        User userDetails = (User) authentication.getPrincipal();
                        UUID userId = userDetails.getId();
                        var response = announcementService.createAnnouncement(userId, request);
                        return GlobalResponseHandler.successResponse(HttpStatus.OK, "Announcement created successfully",
                                        response);
                } catch (Exception e) {
                        return GlobalResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
                }
        }
}
