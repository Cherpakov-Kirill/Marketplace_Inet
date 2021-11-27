package nsu.oop.marketplace.inet.unicast;


import nsu.oop.marketplace.inet.MarketplaceProto;

public interface UnicastReceiverListener {
    void receiveTypeChangeMsg(MarketplaceProto.Message.TypeChangeMsg roleChangeMsg, int receiverId);
    int receiveJoinMsg(String login, String password, String ip, int port);
    void receiveErrorMsg(String error, int senderId);
}
