package nsu.oop.marketplace.inet;

public interface InetControllerListener {
    void showErrorAuthMessage();
    void launchClientCore(int userId, MarketplaceProto.UserType userType);
    int receiveJoinMsg(String name, String password, String ip, int port);
    void receiveErrorMsg(String error, int senderId);
    void receiveAnnouncementMsg(MarketplaceProto.Message.AnnouncementMsg msg, String ip, int port);
    void receiveChatMsg(MarketplaceProto.Message.ChatMessage chat, int messageSenderId);
    void notifyCoreAboutDisconnect(int userId);
}
