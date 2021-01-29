package messages.rm;

import lombok.Getter;
import lombok.Setter;
import messages.AbstractMessage;

public class RmRequest extends AbstractMessage
{
    @Getter
    @Setter
    private String targetPath;
}
