package messages.transfer;

import lombok.NoArgsConstructor;
import messages.ServerErrorResponse;

@NoArgsConstructor
public class FileTransferError extends ServerErrorResponse
{
    public FileTransferError(final String reason)
    {
        setReason(reason);
    }
}
