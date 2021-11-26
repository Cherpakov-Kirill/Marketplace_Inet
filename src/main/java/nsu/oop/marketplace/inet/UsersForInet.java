package nsu.oop.marketplace.inet;

public interface UsersForInet {
    MarketplaceProto.User getUserById(int id);
    void sendPing(int playerId);
    void disconnectUser(String errorMessage, int userId);
}
