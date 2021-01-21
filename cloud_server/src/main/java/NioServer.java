import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class NioServer {

    private final ServerSocketChannel serverChannel = ServerSocketChannel.open();
    private final Selector selector = Selector.open();
    private final ByteBuffer buffer = ByteBuffer.allocate(5);
    private Path serverPath = Paths.get("serverDir");

    public NioServer() throws IOException {
        serverChannel.bind(new InetSocketAddress(8189));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (serverChannel.isOpen()) {
            selector.select(); // block
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    handleAccept(key);
                }
                if (key.isReadable()) {
                    handleRead(key);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NioServer();
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        int read = 0;
        StringBuilder msg = new StringBuilder();
        while ((read = channel.read(buffer)) > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                msg.append((char) buffer.get());
            }
            buffer.clear();
        }
        String command = msg.toString().replaceAll("[\n|\r]", "");
        if (command.equals("ls")) {
            String files = Files.list(serverPath)
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.joining(", "));
            files += "\n";
            channel.write(ByteBuffer.wrap(files.getBytes(StandardCharsets.UTF_8)));
        }

        if (command.startsWith("cd")) {
            String[] args = command.split(" ");
            if (args.length != 2) {
                channel.write(ByteBuffer.wrap("Wrong command\n".getBytes(StandardCharsets.UTF_8)));
            } else {
                String targetPath = args[1];
                Path serverDirBefore = serverPath;
                serverPath = serverPath.resolve(targetPath);
                if (!Files.isDirectory(serverPath) && !Files.exists(serverPath)) {
                    channel.write(ByteBuffer.wrap("Wrong arg for cd command\n".getBytes(StandardCharsets.UTF_8)));
                    serverPath = serverDirBefore;
                }
            }
        }

        if (command.startsWith("cat")) {
            try(FileReader reader = new FileReader("note.txt"))
            {
                int c;
                while((c=reader.read())!=-1){

                    System.out.print((char)c);
                }
            }
            catch(IOException e){

                System.out.println(e.getMessage());
            }

            if (command.equals("touch")){
                File dir = new File("C://SomeDir//NewDir");
                boolean created = dir.mkdir();
                if(created)
                    System.out.println("Folder has been created");
            }

        }
            try {
            Path path = Paths.get("logs/error.log");
            Files.createDirectories(path.getParent());

            Files.write(path, "Log log".getBytes());

            System.out.println(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
    }
}