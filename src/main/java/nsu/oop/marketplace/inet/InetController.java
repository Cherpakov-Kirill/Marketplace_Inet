package nsu.oop.marketplace.inet;


import nsu.oop.marketplace.inet.messages.MessageAcceptor;
import nsu.oop.marketplace.inet.ping.Ping;
import nsu.oop.marketplace.inet.ping.PingListener;
import nsu.oop.marketplace.inet.messages.MessageAcceptorListener;
import nsu.oop.marketplace.inet.messages.MessageBuilder;
import nsu.oop.marketplace.inet.multicast.MulticastPublisher;
import nsu.oop.marketplace.inet.multicast.MulticastPublisherListener;
import nsu.oop.marketplace.inet.multicast.MulticastReceiver;
import nsu.oop.marketplace.inet.multicast.MulticastReceiverListener;
import nsu.oop.marketplace.inet.unicast.*;

import java.net.DatagramSocket;
import java.net.SocketException;

public class InetController implements MulticastPublisherListener, MulticastReceiverListener, UnicastReceiverListener, MessageAcceptorListener, PingListener, InetForPlayers {
    private final InetControllerListener listener;
    private PlayersForInet players;
    private DatagramSocket socket;
    private final MessageAcceptor messageAcceptor;
    private final Ping ping;
    private final UnicastSender sender;
    private final UnicastReceiver receiver;
    private MulticastPublisher inviteSender;
    private MulticastReceiver inviteReceiver;
    private long messageSequence;

    public InetController(InetControllerListener listener, int port, int pingDelayMs, int nodeTimeOutMs) {
        this.listener = listener;
        try {
            socket = new DatagramSocket(port);
            System.out.println("Socket port " + port);
        } catch (SocketException e) {
            System.err.println("ERROR: Node did not started");
            e.printStackTrace();
        }
        this.messageAcceptor = new MessageAcceptor(this);
        this.ping = new Ping(this,pingDelayMs,nodeTimeOutMs);
        this.ping.start();
        this.sender = new UnicastSender(socket, messageAcceptor, ping, pingDelayMs);
        this.receiver = new UnicastReceiver(this, socket, messageAcceptor);
        this.receiver.start();
        this.messageSequence = 0;
    }

    public void interruptUnicast(){
        ping.interrupt();
        receiver.interrupt();
    }

    public void attachPlayers(PlayersForInet players){
        this.players = players;
    }

    public void startMulticastReceiver() {
        inviteReceiver = new MulticastReceiver(this);
        inviteReceiver.start();
    }

    public void startMulticastPublisher(int nodeId, MarketplaceProto.SessionConfig config) {
        inviteSender = new MulticastPublisher(this,
                nodeId,
                config);
        inviteSender.start();
    }

    public void stopMulticastPublisher() {
        if (inviteSender != null) inviteSender.interrupt();
    }

    public synchronized long getMessageSequence() {
        messageSequence++;
        return messageSequence;
    }


    @Override
    public void sendAckMessage(MarketplaceProto.User user, MarketplaceProto.Message message) {
        sender.sendMessage(user, message);
    }

    @Override
    public void setTimeOfReceivedMessage(int playerId) {
        ping.setTimeOfReceivedMessage(playerId);
    }

    @Override
    public void sendMessage(MarketplaceProto.User user, MarketplaceProto.Message message) {
        sender.sendMessage(user, MessageBuilder.setMessageSequence(message, getMessageSequence()));
    }

    @Override
    public void launchGameCore(int playerId) {
        listener.launchGameCore(playerId);
    }

    @Override
    public MarketplaceProto.User getGamePlayerById(int id) {
        return players.getGamePLayerById(id);
    }

    @Override
    public void receiveAnnouncementMsg(MarketplaceProto.Message.AnnouncementMsg msg, String masterIp) {
        //todo make action on receiving of the announcement message
        //listener.updateFindGameList(gamesList);
    }

    @Override
    public void receiveTypeChangeMsg(MarketplaceProto.Message.TypeChangeMsg typeChangeMsg, int senderId) {
        listener.receiveRoleChangeMsg(typeChangeMsg, senderId);
    }

    @Override
    public int receiveJoinMsg(String name, String password, String ip, int port) {
        return listener.receiveJoinMsg(name, password, ip, port);
    }

    @Override
    public void disconnectPlayer(int playerId) {
        players.disconnectPlayer(playerId);
    }

    @Override
    public void sendPing(int playerId) {
        players.sendPing(playerId);
    }

    @Override
    public void removeUserFromPing(int userId){
        ping.removeUser(userId);
    }
}
