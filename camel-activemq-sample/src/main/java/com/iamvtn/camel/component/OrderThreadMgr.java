package com.iamvtn.camel.component;


public class OrderThreadMgr {

	//	final static Logger logger = Logger.getLogger(OrderThreadMgr.class);
	//
	//	private static volatile OrderThreadMgr threadMgr;
	//
	//	private ThreadPoolExecutor executor;
	//
	//	public static final int DEFAULT_QUEUE_SIZE = 100000;
	//
	//	public static void initThreadMgr(int size, long timeout, int queueSize) {
	//		threadMgr = new OrderThreadMgr(size, timeout, queueSize);
	//	}
	//
	//	public static OrderThreadMgr getThreadMgr() {
	//
	//		if (threadMgr == null) {
	//			threadMgr = new OrderThreadMgr(100, 100, DEFAULT_QUEUE_SIZE);
	//		} else {
	//		}
	//
	//		return threadMgr;
	//	}
	//
	//	public OrderThreadMgr(int size, long timeout, int queueSize) {
	//		BlockingQueue<Runnable> queue = (BlockingQueue<Runnable>) new ArrayBlockingQueue<Runnable>(queueSize);
	//		this.executor = new ThreadPoolExecutor(size, size, timeout, TimeUnit.MILLISECONDS, queue);
	//	}
	//
	//	public int fireEmptyThread(long id, Message message, String threadName, ExecutionProcessor executionProcessor) {
	//
	//		try {
	//			OrderThreadWork bWork = new OrderThreadWork(id, message, threadName, executionProcessor);
	//			this.executor.execute(bWork);
	//			return -1;
	//		} catch (RejectedExecutionException e) {
	//			logger.error("ERRORyyyy: " + e.getLocalizedMessage());
	//			return 0;
	//		}
	//	}
	//	
	//	public int fireEmptyThreadGateway(long id, Message message, String threadName, GatewayProcessor gatewayProcessor) {
	//
	//		try {
	////			OrderThreadWork bWork = new OrderThreadWork(id, message, threadName, gatewayProcessor);
	//			GatewayThreadWork bWork = new GatewayThreadWork(id, message, threadName, gatewayProcessor);
	//			this.executor.execute(bWork);
	//			return -1;
	//		} catch (RejectedExecutionException e) {
	//			logger.error("ERRORyyyy: " + e.getLocalizedMessage());
	//			return 0;
	//		}
	//	}

}
