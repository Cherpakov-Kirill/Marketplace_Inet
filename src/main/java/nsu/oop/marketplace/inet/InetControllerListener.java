package nsu.oop.marketplace.inet;

public interface InetControllerListener {
    boolean getOpportunityToJoin();

    void launchGameCore(int playerId);

    void receiveRoleChangeMsg(MarketplaceProto.Message.TypeChangeMsg typeChangeMsg, int senderId);
    int receiveJoinMsg(String name, String password, String ip, int port);
}
