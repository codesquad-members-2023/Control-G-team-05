package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.domain.block.repository.BlockRepository;
import com.codesquad.controlG.domain.chat.dto.response.ChatRandomResult;
import com.codesquad.controlG.domain.chat.redis.RedisChatQueue;
import com.codesquad.controlG.domain.chat.repository.RedisChatQueueRepository;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RedisChatQueueService {

    private final RedisChatQueueRepository redisChatQueueRepository;
    private final ChatRoomService chatRoomService;
    private final BlockRepository blockRepository;

    @Transactional
    public String saveQueue(Long groupId, Long memberId) {
        return redisChatQueueRepository.save(RedisChatQueue.of(groupId, memberId)).getId();
    }

    @Transactional
    public void removeQueue(String key) {
        redisChatQueueRepository.deleteById(key);
    }

    @Transactional
    public synchronized ChatRandomResult matching(Long groupId, Long memberId) {
        String key = groupId + "_" + memberId;
        if (existsById(key)) {
            List<RedisChatQueue> redisChatQueues = redisChatQueueRepository.findByGroupId(groupId);

            // block 멤버를 리스트에서 삭제
            List<Long> blocks = blockRepository.findBlockedIds(memberId);
            redisChatQueues = redisChatQueues.stream().filter(member -> !blocks.contains(member.getMemberId()))
                    .toList();

            // 이미 자신과 채팅하고 있는 파트너 리스트에서 삭제
            List<Long> memberIds = chatRoomService.findMemberChatRoom(groupId, memberId);
            redisChatQueues = redisChatQueues.stream()
                    .filter(member -> !memberIds.contains(member.getMemberId()))
                    .toList();

            // 리스트에 혼자면 return
            if (redisChatQueues.size() == 1) {
                return ChatRandomResult.of(0L, 0L);
            }

            Random random = new Random();
            int randomIndex = random.nextInt(redisChatQueues.size());
            Long partnerId = redisChatQueues.get(randomIndex).getMemberId();
            while (partnerId.equals(memberId)) {
                randomIndex = random.nextInt(redisChatQueues.size());
                partnerId = redisChatQueues.get(randomIndex).getMemberId();
            }
            removeQueue(key);
            removeQueue(groupId + "_" + partnerId);

            Long chatRoomId = chatRoomService.createChatRoom(groupId, memberId, partnerId);
            return ChatRandomResult.of(chatRoomId, partnerId);
        } else {
            return ChatRandomResult.of(0L, 0L);
        }
    }

    public boolean existsById(String key) {
        return redisChatQueueRepository.existsById(key);
    }
}
