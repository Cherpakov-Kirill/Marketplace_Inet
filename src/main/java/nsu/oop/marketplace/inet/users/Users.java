package nsu.oop.marketplace.inet.users;

import nsu.oop.marketplace.inet.MarketplaceProto;

import java.util.List;

public interface Users {
    void addUser(int userId, String username, String ip, int port, MarketplaceProto.UserType type, String firstName, String secondName);
    void changeUserInList(int index, MarketplaceProto.User newUser);
    void sendErrorMessage(String errorMessage, int receiverId);
    void sendJoinMessage(String login, String password);
    void sendChatMessage(MarketplaceProto.Message.ChatMessage chatMessage, int receiverId);
    void sendDBRequestMessage(MarketplaceProto.Message.DBRequest request, int receiverId);
    void sendDBResponseMessage(MarketplaceProto.Message.DBResponse response, int receiverId);
    void disconnectUser(String errorMessage, int userId);
    int getNumberOfUsers();
    List<MarketplaceProto.User> getUsersList();
    MarketplaceProto.User getUserById(int id);
}
