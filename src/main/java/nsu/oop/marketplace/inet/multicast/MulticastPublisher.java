package nsu.oop.marketplace.inet.multicast;

import nsu.oop.marketplace.inet.MarketplaceProto;
import nsu.oop.marketplace.inet.messages.MessageBuilder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastPublisher extends Thread {
    private DatagramSocket socket = null;

    private final MulticastPublisherListener listener;
    private final int nodeId;
    private final MarketplaceProto.SessionConfig config;


    public MulticastPublisher(MulticastPublisherListener listener, int nodeId, MarketplaceProto.SessionConfig config) {
        this.listener = listener;
        this.nodeId = nodeId;
        this.config = config;
    }

    private byte[] getMessageBytes() {
        MarketplaceProto.Message message;
        message = MessageBuilder.announcementMsgBuilder(config,nodeId);
        MessageBuilder.setMessageSequence(message, listener.getMessageSequence());
        return message.toByteArray();
    }

    public void run() {
        try {
            System.out.println("Multicast publisher started");
            InetAddress group = InetAddress.getByName("239.192.0.4");
            int port = 9192;
            this.socket = new DatagramSocket();
            while (!isInterrupted()) {
                byte[] messageBuffer = getMessageBytes();
                DatagramPacket packet = new DatagramPacket(messageBuffer, messageBuffer.length, group, port);
                socket.send(packet);
                Thread.sleep(1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Multicast publisher: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
            System.out.println("Multicast publisher finished");
        }
    }
}
