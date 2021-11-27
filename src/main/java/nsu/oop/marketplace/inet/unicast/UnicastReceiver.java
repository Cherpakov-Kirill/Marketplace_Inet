package nsu.oop.marketplace.inet.unicast;

import nsu.oop.marketplace.inet.MarketplaceProto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnicastReceiver extends Thread {
    private final UnicastReceiverListener listener;
    private final DatagramSocket socket;
    private final AcceptorForReceiver messageAcceptor;


    public UnicastReceiver(UnicastReceiverListener listener, DatagramSocket socket, AcceptorForReceiver messageAcceptor) {
        this.listener = listener;
        this.socket = socket;
        this.messageAcceptor = messageAcceptor;
    }

    public void messageTypeHandler(MarketplaceProto.Message msg, InetAddress address, int port) {
        int messageSenderId = msg.getSenderId();
        long messageSequence = msg.getMsgSeq();
        switch (msg.getTypeCase()) {
            case PING -> {
                System.out.println("PING senderId:" + messageSenderId + " seq=" + messageSequence);
                messageAcceptor.acceptMessage(messageSenderId, messageSequence);
            }
            case ACK -> {
                System.out.println("ACK senderId:" + messageSenderId + " seq=" + messageSequence);
                messageAcceptor.receiveAckMsg(msg.getReceiverId(), messageSenderId, messageSequence);
            }
            case JOIN -> {
                System.out.println("JOIN senderId:" + messageSenderId + " seq=" + messageSequence);
                MarketplaceProto.Message.JoinMsg joinMsg = msg.getJoin();
                int newUserId = listener.receiveJoinMsg(
                        joinMsg.getLogin(),
                        joinMsg.getPassword(),
                        address.getHostAddress(),
                        port
                        );
                messageAcceptor.acceptMessage(newUserId, messageSequence);
                listener.notifyNewUserAboutConnecting(newUserId);
            }
            case ERROR -> {
                System.out.println("ERROR id:" + messageSenderId + " seq=" + messageSequence);
                messageAcceptor.acceptMessage(messageSenderId, messageSequence);
                listener.receiveErrorMsg(msg.getError().getErrorMessage(), messageSenderId);
            }
            case TYPE_CHANGE -> {
                System.out.println("TYPE_CHANGE from id:" + messageSenderId + " new user type = " + msg.getTypeChange().getReceiverType() + " seq =" + messageSequence);
                messageAcceptor.acceptMessage(messageSenderId, messageSequence);
                if (messageSenderId == 0) listener.receiveTypeChangeMsg(msg.getTypeChange(), msg.getReceiverId());
            }
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Unicast Receiver started");
            while (!isInterrupted()) {
                byte[] buffer = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                //System.out.println("Received "+packet.getLength()+" bytes");
                byte[] gotBytes = new byte[packet.getLength()];
                System.arraycopy(buffer, 0, gotBytes, 0, packet.getLength());
                MarketplaceProto.Message msg = MarketplaceProto.Message.parseFrom(gotBytes);
                messageTypeHandler(msg, packet.getAddress(), packet.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Unicast Receiver finished");
        }
    }
}
