package com.dev.ddaangn.chat.controller;

import com.dev.ddaangn.chat.ChatRoom;
import com.dev.ddaangn.chat.ChatRoomForm;
import com.dev.ddaangn.chat.repository.ChatRoomRepository;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/test/room")
    public String rooms(Model model,@LoginUser SessionUser user){
        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
        return "rooms";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable String id, Model model,@LoginUser SessionUser user){
        ChatRoom room = chatRoomRepository.findRoomById(id);
//        ChatRoom room = chatRoomRepository.findRoomById(user.getName());
        model.addAttribute("room",room);
        return "room";
    }

    @GetMapping("/new")
    public String make(Model model,@LoginUser SessionUser user){
        ChatRoomForm form = new ChatRoomForm();
        model.addAttribute("form",form);
        return "newRoom";
    }

    @PostMapping("/room/new")
    public String makeRoom(ChatRoomForm form,@LoginUser SessionUser user){
        chatRoomRepository.createChatRoom(form.getName(),user);
        return "redirect:/test/room";
    }

}