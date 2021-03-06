package nsu.oop.marketplace.inet.users;

import nsu.oop.marketplace.inet.MarketplaceProto;
import nsu.oop.marketplace.inet.UsersControllerForInet;
import nsu.oop.marketplace.inet.messages.MessageBuilder;

import java.util.LinkedList;
import java.util.List;

public class UsersController implements UsersControllerForInet, Users {
    private final UsersControllerListener listener;
    private final InetForUsersController inetController;
    private final List<MarketplaceProto.User> userList;
    private int nodeId;

    public UsersController(UsersControllerListener listener, InetForUsersController inetController) {
        this.listener = listener;
        this.inetController = inetController;
        this.userList = new LinkedList<>();
        this.nodeId = 0;
    }

    @Override
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public void addUser(int userId, String username, String ip, int port, MarketplaceProto.UserType type, String firstName, String secondName) {
        removeUnAuthUserByIPAndPort(ip,port);
        MarketplaceProto.User newPlayer;
        MarketplaceProto.User.Builder playerBuilder = MarketplaceProto.User
                .newBuilder()
                .setUsername(username)
                .setId(userId)
                .setIpAddress(ip)
                .setPort(port)
                .setType(type)
                .setFirstName(firstName)
                .setSecondName(secondName);
        newPlayer = playerBuilder.build();
        userList.add(newPlayer);
    }

    @Override
    public void changeUserInList(int index, MarketplaceProto.User newUser) {
        userList.set(index, newUser);
    }

    @Override
    public void deleteUser(int userId) {
        int index = 0;
        for (MarketplaceProto.User user : userList) {
            if (user.getId() == userId) break;
            index++;
        }
        userList.remove(index);
        System.out.println("Users: delete dead node = " + userId);
    }

    @Override
    public void sendErrorMessage(String errorMessage, int receiverId) {
        inetController.sendMessage(getUserById(receiverId), MessageBuilder.errorMsgBuilder(errorMessage, nodeId, receiverId));
    }

    @Override
    public void sendPing(int receiverId) {
        inetController.sendMessage(getUserById(receiverId), MessageBuilder.pingMsgBuilder(nodeId, receiverId));
    }

    @Override
    public void disconnectUser(String errorMessage, int userId) {
        sendErrorMessage(errorMessage, userId);
        deleteUser(userId);
    }

    @Override
    public void sendJoinMessage(String login, String password) {
        inetController.sendMessage(getUserById(0), MessageBuilder.joinMsgBuilder(login, password, nodeId, 0));
    }

    @Override
    public void sendUserInfoMessage(int receiverId) {
        MarketplaceProto.User receiver = getUserById(receiverId);
        inetController.sendMessage(receiver, MessageBuilder.userInfoMsgBuilder(receiver.getType(), receiver.getFirstName(), receiver.getSecondName(), nodeId, receiverId));
    }

    @Override
    public void sendChatMessage(MarketplaceProto.Message.ChatMessage chatMessage, int receiverId) {
        MarketplaceProto.User receiver = getUserById(receiverId);
        inetController.sendMessage(receiver, MessageBuilder.chatMsgBuilder(chatMessage,nodeId,receiverId));
    }

    @Override
    public void sendDBRequestMessage(MarketplaceProto.Message.DBRequest request, int receiverId) {
        MarketplaceProto.User receiver = getUserById(receiverId);
        inetController.sendMessage(receiver, MessageBuilder.dbRequestMsgBuilder(request,nodeId,receiverId));
    }

    @Override
    public void sendDBResponseMessage(MarketplaceProto.Message.DBResponse response, int receiverId) {
        MarketplaceProto.User receiver = getUserById(receiverId);
        inetController.sendMessage(receiver, MessageBuilder.dbResponseMsgBuilder(response,nodeId,receiverId));
    }

    @Override
    public int getNumberOfUsers() {
        return userList.size();
    }

    @Override
    public List<MarketplaceProto.User> getUsersList() {
        return userList;
    }

    @Override
    public MarketplaceProto.User getUserById(int id) {
        for (MarketplaceProto.User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void removeUnAuthUserByIPAndPort(String ip, int port) {
        for (MarketplaceProto.User user : userList) {
            if (user.getIpAddress().equals(ip) && user.getPort() == port) {
                int id = user.getId();
                if(id > 0) continue;
                inetController.removeUserFromPing(id);
                deleteUser(id);
            }
        }
    }
}
