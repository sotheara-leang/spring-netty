package com.example.netty.channelhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.netty.base.channelhandler.routing.RouteMapping;
import com.solab.iso8583.IsoMessage;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@RouteMapping(name = "Route1")
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<IsoMessage> {

	private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	
	@Override
	protected void channelRead0(final ChannelHandlerContext ctx, IsoMessage request) throws Exception {
		logger.debug("Server 1 get message : " + request.debugString());
		
		ctx.channel().writeAndFlush(request);	
	}
}