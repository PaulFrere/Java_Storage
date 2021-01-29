package transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import conversations.ClientConversationManager;
import events.EMessageReceived;
import cloud_core.messages.IMessage;
import cloud_core.transport.ITransportChannel;
import cloud_core.transport.Netty;
import cloud_core.transport.NettyTransportChannel;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

public class NettyClientHandler extends ChannelInboundHandlerAdapter
{
    @Inject
    private ClientConversationManager conversationManager;

    @Inject
    @Netty
    private ITransportChannel transportChannel;

    @Inject
    private Event<EMessageReceived> messageReceivedBus;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        ((NettyTransportChannel) transportChannel).setChannelContext(ctx);
        conversationManager.setTransportChannel(transportChannel);
        conversationManager.authenticate();
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx,
                            final Object msg)
    {
        if (msg == null) return;
        if (!(msg instanceof IMessage)) return;
        messageReceivedBus.fireAsync(new EMessageReceived((IMessage) msg));
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx)
    {
        ctx.flush();
    }
}
