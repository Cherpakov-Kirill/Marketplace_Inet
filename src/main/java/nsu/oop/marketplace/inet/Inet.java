package nsu.oop.marketplace.inet;

public interface Inet {
    void attachUsers(UsersControllerForInet users);
    void startUnicast();
    void interruptUnicast();
    void startMulticastReceiver();
    void startMulticastPublisher(int nodeId, MarketplaceProto.SessionConfig config);
    void stopMulticastPublisher();
    void disconnectUser(String errorMessage, int userId);

}
