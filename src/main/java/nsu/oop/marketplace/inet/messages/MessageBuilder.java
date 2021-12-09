package nsu.oop.marketplace.inet.messages;


import nsu.oop.marketplace.inet.MarketplaceProto;

import java.util.List;

public class MessageBuilder {
    public static MarketplaceProto.Message pingMsgBuilder(int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setPing(MarketplaceProto.Message.PingMsg.newBuilder().build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message ackMsgBuilder(long messageSequence, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setAck(MarketplaceProto.Message.AckMsg.newBuilder().build())
                .setMsgSeq(messageSequence)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message announcementMsgBuilder(MarketplaceProto.SessionConfig config, int senderId) {
        return MarketplaceProto.Message.newBuilder()
                .setAnnouncement(MarketplaceProto.Message.AnnouncementMsg.newBuilder()
                        .setConfig(config)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .build();
    }

    public static MarketplaceProto.Message joinMsgBuilder(String login, String password, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setJoin(MarketplaceProto.Message.JoinMsg.newBuilder()
                        .setLogin(login)
                        .setPassword(password)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message errorMsgBuilder(String errorMessage, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setError(MarketplaceProto.Message.ErrorMsg.newBuilder()
                        .setErrorMessage(errorMessage)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message userInfoMsgBuilder(MarketplaceProto.UserType receiverType, String firstName, String secondName, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setUserInfo(MarketplaceProto.Message.UserInfoMsg.newBuilder()
                        .setType(receiverType)
                        .setFirstName(firstName)
                        .setSecondName(secondName)
                        .build())
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message dbRequestMsgBuilder(MarketplaceProto.Message.DBRequest request, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setDbRequest(request)
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbFullProductTableRequestMsgBuilder() {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setProductTable(MarketplaceProto.Message.DBRequest.ProductTable.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbFullLogTableRequestMsgBuilder() {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setLogTable(MarketplaceProto.Message.DBRequest.LogTable.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbFullTaskTableRequestMsgBuilder() {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setTaskTable(MarketplaceProto.Message.DBRequest.TaskTable.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbFullSalesTableRequestMsgBuilder() {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setSaleTable(MarketplaceProto.Message.DBRequest.SalesTable.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbFullGlobalChangesTableRequestMsgBuilder() {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setChangeTable(MarketplaceProto.Message.DBRequest.ChangeTable.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbCompleteTaskRequestMsgBuilder(int id) {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setCompleteTask(MarketplaceProto.Message.DBRequest.CompleteTask.newBuilder()
                        .setId(id)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbAcceptChangeRequestMsgBuilder(int id) {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setAcceptChange(MarketplaceProto.Message.DBRequest.AcceptChange.newBuilder()
                        .setId(id)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbAddNewUserRequestMsgBuilder(String firstName, String secondName, String role, String login, String password) {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setAddNewUser(MarketplaceProto.Message.DBRequest.AddNewUser.newBuilder()
                        .setFirstName(firstName)
                        .setSecondName(secondName)
                        .setRole(role)
                        .setLogin(login)
                        .setPassword(password)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbAddNewProductRequestMsgBuilder(String name, String price, String description) {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setAddNewProduct(MarketplaceProto.Message.DBRequest.AddNewProduct.newBuilder()
                        .setName(name)
                        .setPrice(price)
                        .setDescription(description)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbUserListRequestMsgBuilder() {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setUserList(MarketplaceProto.Message.DBRequest.UserList.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbSetTaskRequestMsgBuilder(int userId, String task) {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setSetNewTask(MarketplaceProto.Message.DBRequest.SetNewTask.newBuilder()
                        .setUserId(userId)
                        .setTask(task)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbProductListRequestMsgBuilder() {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setProductList(MarketplaceProto.Message.DBRequest.ProductList.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBRequest dbChangeProductInfoRequestMsgBuilder(int id, String name, String price, String description) {
        return MarketplaceProto.Message.DBRequest.newBuilder()
                .setChangeProductInfo(MarketplaceProto.Message.DBRequest.ChangeProductInfo.newBuilder()
                        .setProduct(MarketplaceProto.DBFullProduct.newBuilder()
                                .setId(id)
                                .setName(name)
                                .setPrice(price)
                                .setDescription(description)
                                .build())
                        .build())
                .build();
    }

    public static MarketplaceProto.Message dbResponseMsgBuilder(MarketplaceProto.Message.DBResponse response, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setDbResponse(response)
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    //Создание msg по таблицам
    public static MarketplaceProto.DBFullProduct dbFullProductBuilder(int id, String name, String price, String description) {
        return MarketplaceProto.DBFullProduct.newBuilder()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setDescription(description)
                .build();
    }

    public static MarketplaceProto.Message.DBResponse productTableResponseBuilder(List<MarketplaceProto.DBFullProduct> productList) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setProductTable(MarketplaceProto.Message.DBResponse.ProductTable.newBuilder()
                        .addAllFullProduct(productList)
                        .build())
                .build();
    }

    public static MarketplaceProto.DBFullTask dbFullTaskBuilder(int id, String name, String task, boolean done) {
        return MarketplaceProto.DBFullTask.newBuilder()
                .setId(id)
                .setName(name)
                .setTask(task)
                .setDone(done)
                .build();
    }

    public static MarketplaceProto.Message.DBResponse taskTableResponseBuilder(List<MarketplaceProto.DBFullTask> taskList) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setTaskTable(MarketplaceProto.Message.DBResponse.TaskTable.newBuilder()
                        .addAllFullTask(taskList)
                        .build())
                .build();
    }

    public static MarketplaceProto.DBFullSales dbFullSaleBuilder(int productId, String productName, String date, String quantity, String amount) {
        return MarketplaceProto.DBFullSales.newBuilder()
                .setProductId(productId)
                .setProductName(productName)
                .setDate(date)
                .setQuantity(quantity)
                .setAmount(amount)
                .build();
    }

    public static MarketplaceProto.Message.DBResponse salesTableResponseBuilder(List<MarketplaceProto.DBFullSales> salesList) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setSaleTable(MarketplaceProto.Message.DBResponse.SalesTable.newBuilder()
                        .addAllFullSales(salesList)
                        .build())
                .build();
    }

    public static MarketplaceProto.DBFullChanges dbFullChangeBuilder(int id, String productName, String changeType, String newValue, String userName) {
        return MarketplaceProto.DBFullChanges.newBuilder()
                .setId(id)
                .setProductName(productName)
                .setChangeType(changeType)
                .setNewValue(newValue)
                .setUserName(userName)
                .build();
    }

    public static MarketplaceProto.Message.DBResponse changeTableResponseBuilder(List<MarketplaceProto.DBFullChanges> changeList) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setChangeTable(MarketplaceProto.Message.DBResponse.ChangeTable.newBuilder()
                        .addAllFullChange(changeList)
                        .build())
                .build();
    }

    public static MarketplaceProto.DBFullLog dbFullLogBuilder(String userName, String description, String actionType) {
        return MarketplaceProto.DBFullLog.newBuilder()
                .setUserName(userName)
                .setDescription(description)
                .setActionType(actionType)
                .build();
    }

    public static MarketplaceProto.Message.DBResponse logTableResponseBuilder(List<MarketplaceProto.DBFullLog> logList) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setLogTable(MarketplaceProto.Message.DBResponse.LogTable.newBuilder()
                        .addAllFullLog(logList)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBResponse acceptChangeResponseBuilder(int id, boolean success, String errorMessage) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setAcceptChange(MarketplaceProto.Message.DBResponse.AcceptChange.newBuilder()
                        .setId(id)
                        .setSuccess(success)
                        .setErrorMessage(errorMessage)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBResponse completeTaskResponseBuilder(int id, boolean success, String errorMessage) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setCompleteTask(MarketplaceProto.Message.DBResponse.CompleteTask.newBuilder()
                        .setId(id)
                        .setSuccess(success)
                        .setErrorMessage(errorMessage)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.DBResponse successfullyBuilder() {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setSuccessfully(MarketplaceProto.Message.DBResponse.Successfully.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.Message.DBResponse unsuccessfullyBuilder() {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setUnsuccessfully(MarketplaceProto.Message.DBResponse.Unsuccessfully.newBuilder().build())
                .build();
    }

    public static MarketplaceProto.DBUser dbUserBuilder(int id, String fullName) {
        return MarketplaceProto.DBUser.newBuilder()
                .setId(id)
                .setFullUserName(fullName)
                .build();
    }

    public static MarketplaceProto.Message.DBResponse userListBuilder(List<MarketplaceProto.DBUser> userList) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setUserList(MarketplaceProto.Message.DBResponse.UserList.newBuilder()
                        .addAllUser(userList)
                        .build())
                .build();
    }

    public static MarketplaceProto.DBProduct dbProductBuilder(int id, String name) {
        return MarketplaceProto.DBProduct.newBuilder()
                .setId(id)
                .setName(name)
                .build();
    }

    public static MarketplaceProto.Message.DBResponse productListBuilder(List<MarketplaceProto.DBProduct> productList) {
        return MarketplaceProto.Message.DBResponse.newBuilder()
                .setProductList(MarketplaceProto.Message.DBResponse.ProductList.newBuilder()
                        .addAllProduct(productList)
                        .build())
                .build();
    }

    //Создание сообщений по чату

    public static MarketplaceProto.Message chatMsgBuilder(MarketplaceProto.Message.ChatMessage chatMessage, int senderId, int receiverId) {
        return MarketplaceProto.Message.newBuilder()
                .setChat(chatMessage)
                .setMsgSeq(0)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatJoinMsgBuilder(String name) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setJoin(MarketplaceProto.Message.ChatMessage.JoinMsg.newBuilder()
                        .setName(name)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatEndMsgBuilder(String name) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setEnd(MarketplaceProto.Message.ChatMessage.EndMsg.newBuilder()
                        .setName(name)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatPublicMsgBuilder(String senderName, String message) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setPublic(MarketplaceProto.Message.ChatMessage.PublicMsg.newBuilder()
                        .setSenderName(senderName)
                        .setMessage(message)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatPrivateMsgBuilder(String senderName, String receiverName, String message) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setPrivate(MarketplaceProto.Message.ChatMessage.PrivateMsg.newBuilder()
                        .setSenderName(senderName)
                        .setReceiverName(receiverName)
                        .setMessage(message)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatUserListMsgBuilder(MarketplaceProto.ChatUsers chatUsers) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setList(MarketplaceProto.Message.ChatMessage.UserListMsg.newBuilder()
                        .setUserList(chatUsers)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message.ChatMessage chatErrorMsgBuilder(String errorMessage) {
        return MarketplaceProto.Message.ChatMessage.newBuilder()
                .setError(MarketplaceProto.Message.ChatMessage.ErrorMsg.newBuilder()
                        .setError(errorMessage)
                        .build())
                .build();
    }

    public static MarketplaceProto.Message setMessageSequence(MarketplaceProto.Message gameMessage, long messageSeq) {
        return gameMessage.toBuilder().setMsgSeq(messageSeq).build();
    }
}

