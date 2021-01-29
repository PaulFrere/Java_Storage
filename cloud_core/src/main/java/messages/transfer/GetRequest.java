package messages.transfer;

import lombok.Getter;
import lombok.Setter;
import messages.AbstractMessage;

public class GetRequest extends AbstractMessage
{
    @Getter
    @Setter
    private String fileName;
}
