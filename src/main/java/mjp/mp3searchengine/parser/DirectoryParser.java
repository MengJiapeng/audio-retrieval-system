package mjp.mp3searchengine.parser;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-11 13:16
 **/
@Component
public class DirectoryParser {

    /**
     * 解析指定目录下所有文件（包括子文件夹内的文件）
     *
     * @param path 目录路径
     * @return 若目录不存在或指定路径不是目录，返回null，否则返回文件数组
     */
    public List<File> parse(String path) {
        File directory = new File(path);
        if (!directory.exists() || !directory.isDirectory()) {
            return null;
        }
        List<File> fileList = new ArrayList<>();
        doParse(Paths.get(path), fileList);
        return fileList;
    }

    private void doParse(Path directoryPath, List<File> fileList) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
            for (Path path : directoryStream) {
                File file = path.toFile();
                if (file.isDirectory()) {
                    doParse(path, fileList);
                } else {
                    fileList.add(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
