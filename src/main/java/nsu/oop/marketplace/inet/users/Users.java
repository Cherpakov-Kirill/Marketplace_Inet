package nsu.oop.marketplace.inet.users;

import nsu.oop.marketplace.inet.MarketplaceProto;

import java.util.List;

public interface Users {
    void setNodeId(int nodeId);
    int addUser(String name, String ip, int port, MarketplaceProto.UserType type);
    void changeUserInList(int index, MarketplaceProto.User newUser);
    void sendErrorMessage(String errorMessage, int userId);
    void sendJoinMessage(String login, String password, int senderId, int receiverId);
    void disconnectUser(String errorMessage, int userId);
    void sendChangeTypeMessage(MarketplaceProto.User receiver, MarketplaceProto.UserType receiverType);
    int getNumberOfUsers();
    List<MarketplaceProto.User> getUsersList();
    MarketplaceProto.User getUserById(int id);
    int getUserIdByIPAndPort(String ip, int port);
}
