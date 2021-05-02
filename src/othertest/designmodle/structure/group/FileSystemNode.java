package src.othertest.designmodle.structure.group;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 组合模式
 *
 *
 * 动态地添加、删除某个目录下的子目录或文件；
 * 统计指定目录下的文件个数；
 * 统计指定目录下的文件总大小。
 *
 * 刚才讲的这种组合模式的设计思路，与其说是一种设计模式，
 * 倒不如说是对业务场景的一种数据结构和算法的抽象。
 * 其中，数据可以表示成树这种数据结构，业务需求可以通过在树上的递归遍历算法来实现。
 *
 */
public class FileSystemNode {
    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }


    public int countNumOfFiles() {
        if (isFile) {
            return 1;
        }
        int numOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.countNumOfFiles();
        }
        return numOfFiles;
    }

    public long countSizeOfFiles() {
        if (isFile) {
            File file = new File(path);
            if (!file.exists()) return 0;
            return file.length();
        }
        long sizeofFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            sizeofFiles += fileOrDir.countSizeOfFiles();
        }
        return sizeofFiles;
    }

    public String getPath() {
        return path;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public void removeSubNode(FileSystemNode fileOrDir) {
        int size = subNodes.size();
        int i = 0;
        for (; i < size; ++i) {
            if (subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }
        if (i < size) {
            subNodes.remove(i);
        }
    }
}