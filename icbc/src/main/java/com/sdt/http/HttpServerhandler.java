/**
 * 
 */
package com.sdt.http;



import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;
import org.apache.http.impl.io.ContentLengthInputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdt.logic.LogicModule;
import com.sdt.queue.LogicQueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.ReferenceCountUtil;


/**
 * @author liuqiang
 *
 */
public class HttpServerhandler extends ChannelInboundHandlerAdapter {

	private HttpHeaders headers;
	private HttpRequest request;
	private FullHttpResponse response;
	private FullHttpRequest fullRequest;
	private HttpPostRequestDecoder decoder;
	
    private static final String FAVICON_ICO = "/favicon.ico";
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String CONNECTION_KEEP_ALIVE = "keep-alive";
    private static final String CONNECTION_CLOSE = "close";
    private Queue<String> processQueue = null;
    public HttpServerhandler() {
		LogicQueue logicQueue = new LogicQueue();
    	processQueue = logicQueue.processQueue;
	}
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
	
    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		if (msg instanceof HttpRequest) {
			try{
				request = (HttpRequest) msg;
				headers = request.headers();
				
				String uri = request.uri();
				System.out.println("http uri: " + uri);
				if(uri.equals(FAVICON_ICO)){
					return;
				}
				
				HttpMethod method = request.method();
				if(method.equals(HttpMethod.GET)){
					QueryStringDecoder queryDecoder = new QueryStringDecoder(uri);
					Map<String, List<String>> uriAttributes = queryDecoder.parameters();

		            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
		                for (String attrVal : attr.getValue()) {
		                    System.out.println(attr.getKey() + "=" + attrVal);
		                }
		            }
				} else if(method.equals(HttpMethod.POST)){
					fullRequest = (FullHttpRequest) msg;
					dealWithContentType();
				} else {
					
				}
				writeResponse(ctx.channel(), HttpResponseStatus.OK, SUCCESS, false);
			} catch (Exception e) {
				writeResponse(ctx.channel(), HttpResponseStatus.INTERNAL_SERVER_ERROR, ERROR, true);
			} finally{
				ReferenceCountUtil.release(msg);
			} }
		else {
			//可以处理其他类型的请求
			ReferenceCountUtil.release(msg);
			}
	}
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    
    private void writeResponse(Channel channel, HttpResponseStatus status, String msg, boolean forceClose) {
    	ByteBuf byteBuf= Unpooled.wrappedBuffer(msg.getBytes());
    	response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, byteBuf);
    	boolean close = isClose();
    	if(!close && !forceClose){
    		response.headers().add(org.apache.http.HttpHeaders.CONTENT_LENGTH, String.valueOf(byteBuf.readableBytes()));
    	}
    	ChannelFuture future = channel.write(response);
    	if(close || forceClose){
    		future.addListener(ChannelFutureListener.CLOSE);
    	}
    }
    
    private boolean isClose(){
    	if(request.headers().contains(org.apache.http.HttpHeaders.CONNECTION, CONNECTION_CLOSE, true) ||
				(request.protocolVersion().equals(HttpVersion.HTTP_1_0) && 
				!request.headers().contains(org.apache.http.HttpHeaders.CONNECTION, CONNECTION_KEEP_ALIVE, true)))
			return true;
		return false;
    }
    
    private void dealWithContentType() throws UnsupportedEncodingException{
    	String contentType = getContentType();
    	if(contentType.equals("application/json")){
    		int len = fullRequest.content().readableBytes();  
            if(len > 0){  
                byte[] content = new byte[len];  
                fullRequest.content().readBytes(content);  
                String contentStr = new String(content, "UTF-8");  
                System.out.println(contentStr);
                
                processQueue.offer(contentStr);
                System.out.println("put into queue");
                //json解析到底在哪里合适
                JSONObject obj = JSON.parseObject(contentStr);
    			for(Entry<String, Object> item : obj.entrySet()){
    				System.out.println(item.getKey()+"="+item.getValue().toString());
    			}
            }
			
    	} else if (contentType.equals("application/x-www-form-urlencoded")){//用于
    		
    	} else if (contentType.equals("multipart/form-data")){//用于文件上传
    		
    	} else {
    		
    	}
    }
    
    private String getContentType(){
    	String typeStr = headers.get("Content-Type").toString();
    	String[] list = typeStr.split(";");
    	return list[0];
    }
}
