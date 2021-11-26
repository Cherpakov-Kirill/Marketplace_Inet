package nsu.oop.marketplace.inet.unicast;

import nsu.oop.marketplace.inet.MarketplaceProto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnicastSender {
    private final DatagramSocket socket;
    private final AcceptorForSender messageAcceptor;
    private final PingForSender ping;

    private final int pingDelay;


    public UnicastSender(DatagramSocket socket, AcceptorForSender messageAcceptor, PingForSender ping, int pingDelay) {
        this.socket = socket;
        this.messageAcceptor = messageAcceptor;
        this.ping = ping;
        this.pingDelay = pingDelay;
    }


    public void sendMessage(MarketplaceProto.User user, MarketplaceProto.Message message) {
        (new SenderSchedule(socket, messageAcceptor, ping, pingDelay, user, message)).start();
    }

    static class SenderSchedule extends Thread {
        private final MarketplaceProto.User user;
        private final MarketplaceProto.Message message;
        private final DatagramSocket socket;

        private final AcceptorForSender messageAcceptor;
        private final PingForSender ping;

        private final int pingDelay;

        SenderSchedule(DatagramSocket socket, AcceptorForSender messageAcceptor, PingForSender ping, int pingDelay, MarketplaceProto.User user, MarketplaceProto.Message message) {
            this.socket = socket;
            this.messageAcceptor = messageAcceptor;
            this.ping = ping;
            this.message = message;
            this.user = user;
            this.pingDelay = pingDelay;
        }

        void send() throws IOException {
            System.out.println("Unicast Sender is sending a " + message.getTypeCase() + " message seq = " + message.getMsgSeq() + " to id=" + user.getId() + " ip:port=" + user.getIpAddress() + ":" + user.getPort());
            byte[] buffer = message.toByteArray();
            InetAddress ip = InetAddress.getByName(user.getIpAddress());
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, user.getPort());
            socket.send(packet);
        }

        @Override
        public void run() {
            ping.setTimeOfSentMessage(user.getId());
            if (message.getTypeCase() == MarketplaceProto.Message.TypeCase.ACK) {
                try {
                    send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                while (true) {
                    try {
                        send();
                        Thread.sleep(pingDelay);
                        if (messageAcceptor.checkAcceptedMessage(message.getMsgSeq())) {
                            //System.out.println("Sender found accepted message. Break.");
                            break;
                        }
                        if (!ping.isAlivePlayer(user.getId())) {
                            System.out.println("Sender out seq = " + message.getMsgSeq() + " because id=" + user.getId() + " is not available");
                            break;
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
