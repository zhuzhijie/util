package com.jing.net;

import java.net.InetSocketAddress;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jing.test.TcpClientTest;

/**
 * tcp客户端。依赖于mina-core-2.0.7.jar,slf4j-api-1.6.6.jar.<br/>
 * 生成客户端时，实现这个抽象类即可。
 * @author 朱志杰 QQ：862990787
 * Sep 5, 2013 9:36:31 AM
 */
public abstract class AbstractTcpClient extends IoHandlerAdapter{
	 /** The connector */
    protected IoConnector connector;

    /** The session */
    protected static IoSession session;

    /**
     * Create the UdpClient's instance
     */
    public AbstractTcpClient() {
        connector = new NioSocketConnector();
        connector.setHandler(this);
        ConnectFuture connFuture = connector.connect(new InetSocketAddress(getServerIp(), getServerPort()));
        connFuture.awaitUninterruptibly();
        session = connFuture.getSession();
    }
    
    /**
     * 功能：向服务端传送数据。
     * @author 朱志杰 QQ：862990787
     * Sep 5, 2013 10:30:05 AM
     * @param str
     */
    public void write(String str){
    	TcpClientTest t=new TcpClientTest();
		IoBuffer buffer = IoBuffer.allocate(str.getBytes().length);
        buffer.put(str.getBytes());
        buffer.flip();
		t.write(buffer);
    }
    
    /**
     * 功能：向服务端传送数据。
     * @author 朱志杰 QQ：862990787
     * Sep 5, 2013 10:30:05 AM
     * @param buffer
     */
    public void write(IoBuffer buffer){
    	 session.write(buffer);
    }
    
    /**
     * 功能：传入服务端的IP。
     * @author 朱志杰 QQ：862990787
     * Sep 5, 2013 10:07:55 AM
     * @return String
     */
    public abstract String getServerIp() ;
    
    /**
     * 功能：传入服务端的端口。
     * @author 朱志杰 QQ：862990787
     * Sep 5, 2013 10:07:55 AM
     * @return int
     */
    public abstract int getServerPort() ;

   /**
    * 出现异常时。
    */
    @Override
    public abstract void exceptionCaught(IoSession session, Throwable cause) throws Exception ;

    /**
     * 收到报文返回时。
     */
    @Override
    public abstract void messageReceived(IoSession session, Object message) throws Exception ;

    /**
     * 发送报文后。
     */
    @Override
    public abstract void messageSent(IoSession session, Object message) throws Exception ;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void sessionClosed(IoSession session) throws Exception ;

    /**
     * session创建时。
     */
    @Override
    public abstract void sessionCreated(IoSession session) throws Exception ;

    /**
     * session超时时。
     */
    @Override
    public abstract void sessionIdle(IoSession session, IdleStatus status) throws Exception ;

    /**
     * session打开时。
     */
    @Override
    public abstract void sessionOpened(IoSession session) throws Exception ;

}
