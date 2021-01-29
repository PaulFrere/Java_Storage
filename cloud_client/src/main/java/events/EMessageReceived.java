package events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import cloud_core.messages.IMessage;

@Getter
@Setter
@AllArgsConstructor
public class EMessageReceived
{
    private IMessage message;
}
