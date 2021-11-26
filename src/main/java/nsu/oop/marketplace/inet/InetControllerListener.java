package nsu.oop.marketplace.inet;

public interface InetControllerListener {
    void launchClientCore(int playerId);

    void receiveRoleChangeMsg(MarketplaceProto.Message.TypeChangeMsg typeChangeMsg, int senderId);
    int receiveJoinMsg(String name, String password, String ip, int port);
    void receiveErrorMsg(String error, int senderId);
    void receiveAnnouncementMsg(MarketplaceProto.Message.AnnouncementMsg msg, String ip, int port);
}
