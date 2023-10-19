package com.codesquad.controlG.domain.group.service;

import com.codesquad.controlG.domain.group.dto.GroupCreateRequest;
import com.codesquad.controlG.domain.group.dto.GroupDetailResponse;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.group.repository.GroupRepository;
import com.codesquad.controlG.domain.image.ImageService;
import com.codesquad.controlG.domain.member_group.repository.MemberGroupRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.GroupException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberGroupRepository memberGroupRepository;
    private final ImageService imageService;

    @Transactional
    public void create(GroupCreateRequest groupCreateRequest) {
        checkGroup(groupCreateRequest.getName());
        String img = getImgUrl(groupCreateRequest.getImage());
        Group group = Group.of(groupCreateRequest.getName(), img);
        groupRepository.save(group);
    }

    private void checkGroup(String name) {
        if (groupRepository.existsByName(name)) {
            throw new CustomRuntimeException(GroupException.NAME_ALREADY_EXISTS);
        }
    }

    private String getImgUrl(MultipartFile image) {
        return image == null ? null : imageService.uploadImage(image);
    }

    public GroupDetailResponse retrieveGroupDetail(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomRuntimeException(GroupException.NOT_FOUND));
        Long headCount = memberGroupRepository.countByGroupId(groupId);
        return GroupDetailResponse.of(group, headCount);
    }
}
