package messages.transfer;

import lombok.Getter;
import lombok.Setter;
import messages.AbstractMessage;

public class FileDataAcceptedResponse extends AbstractMessage
{
    @Getter
    @Setter
    private int cseq;
}
