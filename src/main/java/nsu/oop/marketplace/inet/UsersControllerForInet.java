package nsu.oop.marketplace.inet;

public interface UsersControllerForInet {
    MarketplaceProto.User getUserById(int id);
    void sendUserInfoMessage(int receiverId);
    void sendPing(int playerId);
    void disconnectUser(String errorMessage, int userId);
    void setNodeId(int nodeId);
    void deleteUser(int userId);
}
