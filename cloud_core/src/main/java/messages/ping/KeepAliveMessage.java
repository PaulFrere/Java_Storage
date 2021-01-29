package messages.ping;

import messages.AbstractMessage;

public class KeepAliveMessage extends AbstractMessage
{
    byte[] buffer = new byte[1024 * 1024];
}
