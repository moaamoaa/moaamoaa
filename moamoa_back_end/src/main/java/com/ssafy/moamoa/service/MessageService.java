package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.dto.MessageDto;
import com.ssafy.moamoa.domain.entity.Message;
import com.ssafy.moamoa.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    private final TimeService timeService;

    private final RedisTemplate<String, String> redisTemplate;


    public List<MessageDto> getMessages(Long roomId) {
        List<Message> messageList = messageRepository.getMessagesByChatroomIdTimeAsc(roomId);
        List<MessageDto> returnList = new ArrayList<>();

        for (Message m : messageList) {
            MessageDto messageDto = MessageDto.builder()
                    .sender(m.getSender().getNickname())
                    .receiver(m.getReceiver().getNickname())
                    .time(timeService.parseCurrentTime(m.getTime()))
                    .context(m.getText()).build();

            returnList.add(messageDto);
        }

        return returnList;
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);

        
    }
}
