package nsu.oop.marketplace.inet.messages;


import nsu.oop.marketplace.inet.MarketplaceProto;

public class MessageBuilder {
    public static MarketplaceProto.Message pingMsgBuilder(int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setPing(MarketplaceProto.Message.PingMsg.newBuilder().build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message ackMsgBuilder(long messageSequence, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setAck(MarketplaceProto.Message.AckMsg.newBuilder().build())
                .setMsgSeq(messageSequence)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message announcementMsgBuilder(MarketplaceProto.SessionConfig config, int senderId) {
        return MarketplaceProto.Message.newBuilder()
                .setAnnouncement(MarketplaceProto.Message.AnnouncementMsg.newBuilder()
                        .setConfig(config)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .build();
    }

    public static MarketplaceProto.Message joinMsgBuilder(String login, String password, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setJoin(MarketplaceProto.Message.JoinMsg.newBuilder()
                        .setLogin(login)
                        .setPassword(password)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message errorMsgBuilder(String errorMessage, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setError(MarketplaceProto.Message.ErrorMsg.newBuilder()
                        .setErrorMessage(errorMessage)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message typeChangingMsgBuilder(MarketplaceProto.UserType receiverType, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setTypeChange(MarketplaceProto.Message.TypeChangeMsg.newBuilder()
                        .setReceiverType(receiverType)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message chatMsgBuilder(MarketplaceProto.Message.ChatMessage chatMessage, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setChat(chatMessage)
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatJoinMsgBuilder(String name) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setJoin(MarketplaceProto.Message.ChatMessage.JoinMsg.newBuilder()
                        .setName(name)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatEndMsgBuilder(String name) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setEnd(MarketplaceProto.Message.ChatMessage.EndMsg.newBuilder()
                        .setName(name)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatPublicMsgBuilder(String senderName, String message) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setPublic(MarketplaceProto.Message.ChatMessage.PublicMsg.newBuilder()
                        .setSenderName(senderName)
                        .setMessage(message)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatPrivateMsgBuilder(String senderName, String receiverName, String message) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setPrivate(MarketplaceProto.Message.ChatMessage.PrivateMsg.newBuilder()
                        .setSenderName(senderName)
                        .setReceiverName(receiverName)
                        .setMessage(message)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatUserListMsgBuilder(MarketplaceProto.ChatUsers chatUsers) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setList(MarketplaceProto.Message.ChatMessage.UserListMsg.newBuilder()
                        .setUserList(chatUsers)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatErrorMsgBuilder(String errorMessage) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setError(MarketplaceProto.Message.ChatMessage.ErrorMsg.newBuilder()
                        .setError(errorMessage)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message setMessageSequence(MarketplaceProto.Message gameMessage, long messageSeq) {
        return gameMessage.toBuilder().setMsgSeq(messageSeq).build();
    }
}

