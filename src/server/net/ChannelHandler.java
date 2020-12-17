package server.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import server.game.players.Client;
import server.game.players.saving.PlayerSave;

public class ChannelHandler extends SimpleChannelHandler {

	private Session session = null;

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		/*
		 * if (e.getCause() instanceof ReadTimeoutException) { if
		 * (session.getClient() != null) { System.out.println("Player " +
		 * session.getClient().playerName + " timed out!"); } } else
		 * if(!(e.getCause() instanceof java.io.IOException)){
		 * e.getCause().printStackTrace(); } ctx.getChannel().close();
		 */
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {

		if (e.getMessage() instanceof Client) {
			session.setClient((Client) e.getMessage());
		} else if (e.getMessage() instanceof Packet) {
			if (session.getClient() != null) {
				if (((Packet) e.getMessage()).getOpcode() == 41) {
					session.getClient().inStream.currentOffset = 0;
					session.getClient().inStream.buffer = ((Packet) e.getMessage()).getPayload().array();
					session.getClient().wearId = session.getClient().getInStream().readUnsignedWord();
					session.getClient().wearSlot = session.getClient().getInStream().readUnsignedWordA();
					session.getClient().getItems().wearItem(session.getClient().wearId, session.getClient().wearSlot);
					//session.getClient().sendMessage(	""+session.getClient().wearId);
				}
				session.getClient().queueMessage((Packet) e.getMessage());
			}
		}
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		if (session == null)
			session = new Session(ctx.getChannel());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		if (session != null) {
			Client client = session.getClient();
			if (client != null) {
				// ConnectionList.removeConnection(((InetSocketAddress)ctx.getChannel().getRemoteAddress()).getAddress().getHostAddress().toString());
				client.disconnected = true;
				PlayerSave.saveGame(client);

			}
			session = null;
		}
	}

}