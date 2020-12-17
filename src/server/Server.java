package server;

import java.io.IOException;
//import org.Vote.*;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;

import server.clip.region.ObjectDef;
import server.clip.region.Region;
import server.event.CycleEventHandler;
import server.event.Task;
import server.event.TaskScheduler;
import server.game.minigames.FightCaves;
import server.game.minigames.FightPits;
import server.game.minigames.PestControl;
import server.game.npcs.NPCHandler;

import server.game.players.PlayerHandler;
import server.net.PipelineFactory;

import server.world.ClanManager;
import server.world.ItemHandler;
import server.world.ObjectHandler;
import server.world.ObjectManager;
import server.world.PlayerManager;
import server.world.ShopHandler;
import server.world.StillGraphicsManager;

//import org.Vote.MainLoader;

/**
 * The main class needed to start the server.
 * 
 * @author Sanity
 * @author Graham
 * @author Blake
 * @author Ryan Lmctruck30 Revised by Shawn Notes by Shawn
 */
public class Server {

	public static boolean sleeping;
	public static final int cycleRate;
	public static boolean UpdateServer = false;
	private static long sleepTime;
	public static final int serverPort;
	public static final ClanManager clanManager = new ClanManager();
	public static final ItemHandler itemHandler = new ItemHandler();
	public static final PlayerHandler playerHandler = new PlayerHandler();
	public static  NPCHandler npcHandler = new NPCHandler();
	public static final ShopHandler shopHandler = new ShopHandler();
	public static final ObjectHandler objectHandler = new ObjectHandler();
	public static final ObjectManager objectManager = new ObjectManager();
	public static final FightPits fightPits = new FightPits();
	public static final PestControl pestControl = new PestControl();
	public static final FightCaves fightCaves = new FightCaves();
	private static final TaskScheduler scheduler = new TaskScheduler();
	public static PlayerManager playerManager = PlayerManager.getSingleton();
	private static StillGraphicsManager stillGraphicsManager = new StillGraphicsManager();
	private static final ScheduledExecutorService IO_EXECUTOR = Executors.newSingleThreadScheduledExecutor();
	private static DecimalFormat debugPercentFormat;



	static {
		serverPort = 43594;
		cycleRate = 600;
		sleepTime = 0;
		debugPercentFormat = new DecimalFormat("0.0#%");
	}

	public static void main(java.lang.String args[]) throws NullPointerException, IOException {
		System.currentTimeMillis();
		ObjectDef.loadConfig();
		Region.load();
		bind();

		playerManager.setupRegionPlayers();

		Connection.initialize();

		scheduler.schedule(new Task() {
			@Override
			protected void execute() {
				long loopStart = System.currentTimeMillis();
				CycleEventHandler.getSingleton().process();
				itemHandler.process();
				playerHandler.process();
				npcHandler.process();
				shopHandler.process();
				objectManager.process();
				fightPits.process();
				pestControl.process();
				if((System.currentTimeMillis() - loopStart) > 600) {
					System.out.println("-------------");
					System.out.println("Loop took: " + (System.currentTimeMillis() - loopStart) + " ms.");
					System.out.println("Players online: " + PlayerHandler.getPlayerCount() + ".");
					System.out.println("-------------");
				}
			}
		});
	}

	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}
	public static ScheduledExecutorService GetIOExecutor() {
		return IO_EXECUTOR;
	}


	/**
	 * Gets the Graphics manager.
	 */
	public static StillGraphicsManager getStillGraphicsManager() {
		return stillGraphicsManager;
	}

	/**
	 * Gets the Player manager.
	 */
	public static PlayerManager getPlayerManager() {
		return playerManager;
	}

	/**
	 * Gets the Object manager.
	 */
	public static ObjectManager getObjectManager() {
		return objectManager;
	}

	/**
	 * Java connection. Ports.
	 */
	private static void bind() {
		ServerBootstrap serverBootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		serverBootstrap.setPipelineFactory(new PipelineFactory(new HashedWheelTimer()));
		serverBootstrap.bind(new InetSocketAddress(serverPort));
	}

}
