package com.dev.ddaangn.chat.repository;

import com.dev.ddaangn.chat.ChatRoom;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String chatName, SessionUser user){
        ChatRoom chatRoom = ChatRoom.create(chatName,user.getName());
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

//    public ChatRoom createChatRoom(String chatName,String name){
//        ChatRoom chatRoom = ChatRoom.create(chatName,name);
//        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
//        return chatRoom;
//    }
}