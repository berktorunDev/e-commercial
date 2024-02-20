package com.app.ecommercial.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.app.ecommercial.model.dto.request.CreateAnnouncementRequestDTO;
import com.app.ecommercial.model.dto.request.UpdateAnnouncementRequestDTO;
import com.app.ecommercial.model.dto.response.AnnouncementDTO;
import com.app.ecommercial.model.entity.Announcement;
import com.app.ecommercial.repository.AnnouncementRepository;
import com.app.ecommercial.util.dictionary.ResponseDictionary;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnouncementService extends BaseService<Announcement, Long> {

    private final AnnouncementRepository announcementRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    protected JpaRepository<Announcement, Long> getRepository() {
        return announcementRepository;
    }

    public AnnouncementDTO getAnnouncement(Long id) {
        var announcement = getById(id);
        if (announcement == null) {
            return null;
        }
        return AnnouncementDTO.builder()
                .id(announcement.getId())
                .title(announcement.getTitle())
                .description(announcement.getDescription())
                .categoryId(announcement.getCategory() != null ? announcement.getCategory().getId() : null)
                .budget(announcement.getBudget())
                .phone(announcement.getPhone())
                .isEnable(announcement.isEnable())
                .createdAt(announcement.getCreatedAt())
                .updatedAt(announcement.getUpdatedAt())
                .userId(announcement.getUser() != null ? announcement.getUser().getId() : null)
                .build();
    }

    public List<AnnouncementDTO> getAllAnnouncements() {
        List<Announcement> announcements = super.getAll();
        return announcements.stream().map(announcement -> AnnouncementDTO.builder()
                .id(announcement.getId())
                .title(announcement.getTitle())
                .description(announcement.getDescription())
                .categoryId(announcement.getCategory() != null ? announcement.getCategory().getId() : null)
                .budget(announcement.getBudget())
                .phone(announcement.getPhone())
                .isEnable(announcement.isEnable())
                .createdAt(announcement.getCreatedAt())
                .updatedAt(announcement.getUpdatedAt())
                .userId(announcement.getUser() != null ? announcement.getUser().getId() : null)
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public String updateAnnouncement(Long id, UpdateAnnouncementRequestDTO updateDto) {
        try {
            var existingAnnouncement = getById(id);

            if (ObjectUtils.isNotEmpty(updateDto.getTitle())) {
                existingAnnouncement.setTitle(updateDto.getTitle());
            }
            if (ObjectUtils.isNotEmpty(updateDto.getDescription())) {
                existingAnnouncement.setDescription(updateDto.getDescription());
            }
            if (ObjectUtils.isNotEmpty(updateDto.getCategoryId())) {
                var category = categoryService.getById(updateDto.getCategoryId());
                if (!ObjectUtils.isEmpty(category)) {
                    existingAnnouncement.setCategory(category);
                }
            }
            if (updateDto.getBudget() != null) {
                existingAnnouncement.setBudget(updateDto.getBudget());
            }
            if (ObjectUtils.isNotEmpty(updateDto.getContactInfo())) {
                existingAnnouncement.setPhone(updateDto.getContactInfo());
            }
            if (updateDto.getIsEnable() != null) {
                existingAnnouncement.setEnable(updateDto.getIsEnable());
            }
            existingAnnouncement.setUpdatedAt(LocalDateTime.now());
            save(existingAnnouncement);

            return ResponseDictionary.AnnouncementUpdateSuccess;
        } catch (Exception e) {
            return ResponseDictionary.AnnouncementUpdateFail;
        }
    }

    @Transactional
    public String addAnnouncementToFavorites(UUID userId, Long announcementId) {
        var user = userService.getById(userId);
        var announcement = getById(announcementId);
        if (user != null && announcement != null) {
            var favorites = user.getFavorites();
            if (!favorites.contains(announcementId)) {
                favorites.add(announcementId);
                user.setUpdatedAt(LocalDateTime.now());
                userService.save(user);
                return ResponseDictionary.AnnouncementFavoriteAdded;
            }
        }
        return ResponseDictionary.AnnouncementFavoriteFail;
    }

    @Transactional
    public String removeAnnouncementFromFavorites(UUID userId, Long announcementId) {
        var user = userService.getById(userId);
        var announcement = getById(announcementId);
        if (user != null && announcement != null) {
            var favorites = user.getFavorites();
            if (favorites.contains(announcementId)) {
                favorites.remove(announcementId);
                user.setUpdatedAt(LocalDateTime.now());
                userService.save(user);
                return ResponseDictionary.AnnouncementFavoriteRemoved;
            }
        }
        return ResponseDictionary.AnnouncementFavoriteFail;
    }

    @Transactional
    public String createAnnouncement(UUID userId, CreateAnnouncementRequestDTO createDto) {
        try {
            var newAnnouncement = Announcement.builder()
                    .user(userService.getById(userId))
                    .title(createDto.getTitle())
                    .description(createDto.getDescription())
                    .category(categoryService.getById(createDto.getCategoryId()))
                    .budget(createDto.getBudget())
                    .phone(createDto.getPhone())
                    .isEnable(createDto.isEnable())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            var createdAnnouncement = save(newAnnouncement);

            var existingUser = userService.getById(userId);
            existingUser.getCreatedAnnouncements().add(createdAnnouncement);
            existingUser.setUpdatedAt(LocalDateTime.now());
            userService.save(existingUser);

            return ResponseDictionary.AnnouncementCreateSuccess;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDictionary.AnnouncementCreateFail;
        }
    }

    @Transactional
    public String deleteAnnouncement(UUID userId, Long announcementId) {
        try {
            var user = userService.getById(userId);
            var announcement = getById(announcementId);
            if (user != null && announcement != null) {
                var userAnnouncements = user.getCreatedAnnouncements();
                boolean isRemoved = userAnnouncements.remove(announcement);
                if (isRemoved) {
                    user.setUpdatedAt(LocalDateTime.now());
                    userService.save(user);
                    delete(announcementId);
                }
            }
            return ResponseDictionary.AnnouncementDeleteSuccess;
        } catch (Exception e) {
            return ResponseDictionary.AnnouncementDeleteFail;
        }
    }
}
