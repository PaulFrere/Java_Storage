package transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import cloud_core.messages.IMessage;
import cloud_core.transport.ITransportChannel;
import cloud_core.transport.Netty;
import cloud_core.transport.NettyTransportChannel;
import conversations.ServerConversationManager;
import events.EClientConnected;
import events.EMessageReceived;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class NettyServerHandler extends ChannelInboundHandlerAdapter
{
    @Inject
    private ServerConversationManager conversationManager;

    @Inject
    @Netty
    private ITransportChannel transportChannel;

    @Inject
    private Event<EClientConnected> clientConntectedBus;

    @Inject
    private Event<EMessageReceived> messageReceivedBus;

    @Override
    public void channelActive(final ChannelHandlerContext ctx)
    {
        ((NettyTransportChannel) transportChannel).setChannelContext(ctx);
        conversationManager.setTransportChannel(transportChannel);
        clientConntectedBus.fireAsync(new EClientConnected(conversationManager));
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx,
                            final Object msg)
    {
        if (msg == null) return;
        if (!(msg instanceof IMessage)) return;
        messageReceivedBus.fireAsync(new EMessageReceived(conversationManager, (IMessage) msg));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
    {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        cause.printStackTrace();
        ctx.close();
    }
}
