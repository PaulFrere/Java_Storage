package messages.cd;

import lombok.Getter;
import lombok.Setter;
import messages.AbstractMessage;

public class CdRequest extends AbstractMessage
{
    @Getter
    @Setter
    private String targetDirectory;
}
