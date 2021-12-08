package nsu.oop.marketplace.inet.unicast;


import nsu.oop.marketplace.inet.MarketplaceProto;

public interface UnicastReceiverListener {
    void receiveUserInfoMsg(MarketplaceProto.Message.UserInfoMsg userInfoMsg, int receiverId);
    int receiveJoinMsg(String login, String password, String ip, int port);
    void receiveErrorMsg(String error, int senderId);
    void notifyNewUserAboutConnecting(int userId);

    void receiveChatMsg(MarketplaceProto.Message.ChatMessage chat, int messageSenderId);
}
