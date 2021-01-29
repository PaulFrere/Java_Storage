package messages.transfer;

import lombok.Getter;
import lombok.Setter;
import messages.AbstractMessage;

public class PutRequest extends AbstractMessage
{
    @Getter
    @Setter
    private String fileName;
}
