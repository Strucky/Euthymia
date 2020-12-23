package server.game.players.packets;

import server.game.players.Client;

/**
 * Packet interface.
 *
 * @author Graham
 */
public interface Packet {

    public void handlePacket(Client client, int packetType, int packetSize);

}
