package messages.mkdir;

import lombok.Getter;
import lombok.Setter;
import messages.AbstractMessage;

public class MkdirRequest extends AbstractMessage
{
    @Getter
    @Setter
    private String directoryPath;
}
