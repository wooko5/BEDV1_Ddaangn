package com.dev.ddaangn.chat;

import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomForm {
    private String name;
    private String sessionName;

//    public ChatRoomForm(SessionUser user) {
//        this.name = user.getName();
//        this.sessionName = sessionName;
//    }
}