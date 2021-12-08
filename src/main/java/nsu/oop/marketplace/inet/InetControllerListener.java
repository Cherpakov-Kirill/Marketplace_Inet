package nsu.oop.marketplace.inet;

public interface InetControllerListener {
    void showErrorAuthMessage();

    void receiveUserInfoMsg(int receiverId, MarketplaceProto.UserType type, String firstName, String secondName);

    int receiveJoinMsg(String name, String password, String ip, int port);

    void receiveErrorMsg(String error, int senderId);

    void receiveAnnouncementMsg(MarketplaceProto.Message.AnnouncementMsg msg, String ip, int port);

    void receiveChatMsg(MarketplaceProto.Message.ChatMessage chat, int messageSenderId);

    void receiveDBRequestMsg(MarketplaceProto.Message.DBRequest dbRequest, int messageSenderId);

    void receiveDBResponseMsg(MarketplaceProto.Message.DBResponse dbResponse, int messageSenderId);

    void notifyCoreAboutDisconnect(int userId);


}
