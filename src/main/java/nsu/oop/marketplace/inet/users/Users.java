package nsu.oop.marketplace.inet.users;

import nsu.oop.marketplace.inet.MarketplaceProto;

import java.util.List;

public interface Users {
    void addUser(int userId, String name, String ip, int port, MarketplaceProto.UserType type);
    void changeUserInList(int index, MarketplaceProto.User newUser);
    void sendErrorMessage(String errorMessage, int receiverId);
    void sendJoinMessage(String login, String password);
    void disconnectUser(String errorMessage, int userId);
    int getNumberOfUsers();
    List<MarketplaceProto.User> getUsersList();
    MarketplaceProto.User getUserById(int id);
    int getUserIdByIPAndPort(String ip, int port);
}
