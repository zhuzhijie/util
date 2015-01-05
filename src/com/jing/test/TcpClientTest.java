package com.jing.test;


import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.jing.net.AbstractTcpClient;

public class TcpClientTest extends AbstractTcpClient {
	
	public static void main(String[] args){
		TcpClientTest t=new TcpClientTest();
		t.write("abc");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace(System.out);
	}

	@Override
	public String getServerIp() {
		return "116.255.149.143";
	}

	@Override
	public int getServerPort() {
		return 80;
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("messageReceived:"+message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");

	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
	}

}
