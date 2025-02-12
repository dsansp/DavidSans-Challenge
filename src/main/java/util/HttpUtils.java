package util;



import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.file.Paths;



public class HttpUtils {



    public static String getClipboardContent() throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }


    public static String absolutePath(String data) {

        String absolutePath = Paths.get(data).toAbsolutePath().toString();
        System.out.println(absolutePath);
        return absolutePath;
    }
}


