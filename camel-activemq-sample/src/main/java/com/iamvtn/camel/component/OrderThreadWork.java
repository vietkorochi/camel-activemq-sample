package com.iamvtn.camel.component;

/**
 * 
 * @author do.tran.tien
 *
 */
public class OrderThreadWork implements Runnable {

	public void run() {
		// TODO Auto-generated method stub

	}

	//	final static Logger logger = Logger.getLogger(OrderThreadWork.class);
	//
	//	@SuppressWarnings("unused")
	//	private long id = 0;
	//	private Message message;
	//	@SuppressWarnings("unused")
	//	private String threadName;
	//	ExecutionProcessor executionProcessor;
	////	GatewayProcessor gatewayProcessor;
	////	private int processorType;
	//
	//	public OrderThreadWork(long id, Message message, String threadName, ExecutionProcessor executionProcessor) {
	//		//this.id = Thread.currentThread().getId();
	//		this.id = id;
	//		this.message = message;
	//		this.threadName = threadName;
	//		this.executionProcessor = executionProcessor;
	////		this.processorType = 1;
	//	}
	//	
	////	public OrderThreadWork(long id, Message message, String threadName, GatewayProcessor gatewayProcessor) {
	////		//this.id = Thread.currentThread().getId();
	////		this.id = id;
	////		this.message = message;
	////		this.threadName = threadName;
	////		this.gatewayProcessor = gatewayProcessor;
	////		this.processorType = 2;
	////	}
	//
	//	private void doMyTask() throws Exception {
	////		if (processorType == 1) {
	//		executionProcessor.processTest(message);
	////		} else if (processorType == 2) {
	////			gatewayProcessor.processTest(message);
	////		}
	//		
	//		logger.info("OrderThreadWork============================");
	//	}
	//
	//	public void run() {
	//		try {
	//			doMyTask();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			logger.error(e.getStackTrace());
	//			System.exit(1);
	//		}
	//
	//	}

}
