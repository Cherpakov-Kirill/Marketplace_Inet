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
    private int newPlayerIdCounter;
    private int nodeId;

    public UsersController(UsersControllerListener listener, InetForUsersController inetController) {
        this.listener = listener;
        this.inetController = inetController;
        this.userList = new LinkedList<>();
        this.nodeId = 0;
        newPlayerIdCounter = 1;
    }

    @Override
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public int addUser(String name, String ip, int port, MarketplaceProto.UserType type) {
        int newUserId = getUserIdByIPAndPort(ip, port);
        if (newUserId == 0) {
            if(type != MarketplaceProto.UserType.UNAUTHENTICATED){
                newUserId = newPlayerIdCounter;
                newPlayerIdCounter++;
            }
            MarketplaceProto.User newPlayer;
            MarketplaceProto.User.Builder playerBuilder = MarketplaceProto.User
                    .newBuilder()
                    .setName(name)
                    .setId(newUserId)
                    .setIpAddress(ip)
                    .setPort(port)
                    .setType(type);
            newPlayer = playerBuilder.build();
            userList.add(newPlayer);
            if(type != MarketplaceProto.UserType.UNAUTHENTICATED){
                if (!listener.addUserInWorkspace(newUserId)) {
                    disconnectUser("We couldn't create a working session for you! Sorry.", newUserId);
                    return -1;
                }
            }
        }
        return newUserId;
    }

    @Override
    public void changeUserInList(int index, MarketplaceProto.User newUser) {
        userList.set(index, newUser);
    }

    private void deleteUser(int userId) {
        int index = 0;
        for (MarketplaceProto.User user : userList) {
            if (user.getId() == userId) break;
            index++;
        }
        userList.remove(index);
        System.out.println("Users: delete dead node = " + userId);
        inetController.removeUserFromPing(userId);
    }

    @Override
    public void sendErrorMessage(String errorMessage, int userId) {
        inetController.sendMessage(getUserById(userId), MessageBuilder.errorMsgBuilder(errorMessage, nodeId, userId));
    }

    @Override
    public void sendPing(int playerId) {
        inetController.sendMessage(getUserById(playerId), MessageBuilder.pingMsgBuilder(nodeId, playerId));
    }

    @Override
    public void disconnectUser(String errorMessage, int userId) {
        sendErrorMessage(errorMessage, userId);
        deleteUser(userId);
    }

    @Override
    public void sendJoinMessage(String login, String password, int senderId, int receiverId) {
        inetController.sendMessage(getUserById(0), MessageBuilder.joinMsgBuilder(login, password, nodeId, 0));
    }

    @Override
    public void sendChangeTypeMessage(MarketplaceProto.User receiver, MarketplaceProto.UserType receiverType) {
        inetController.sendMessage(receiver, MessageBuilder.typeChangingMsgBuilder(receiverType, nodeId, receiver.getId()));
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

    @Override
    public int getUserIdByIPAndPort(String ip, int port) {
        for (MarketplaceProto.User user : userList) {
            if (user.getIpAddress().equals(ip) && user.getPort() == port) return user.getId();
        }
        return 0;
    }
}