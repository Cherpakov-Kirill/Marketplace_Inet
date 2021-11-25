package nsu.oop.marketplace.inet;


public interface InetForPlayers {
    void sendMessage(MarketplaceProto.User user, MarketplaceProto.Message message);
    void removeUserFromPing(int userId);
}
