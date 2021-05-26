package src.othertest.designmodle.behavior.visitor;

import java.util.ArrayList;
import java.util.List;



public abstract class ResourceFile2 {
    protected String filePath;
    public ResourceFile2(String filePath) {
        this.filePath = filePath;
    }
    // 根据多态特性，程序会调用实际类型的 accept 函数，
    // 代码中的 this 类型是 PdfFile 的，
    // 在编译的时候就确定了，
    // 所以会调用 extractor 的 extract2txt(PdfFile pdfFile) 这个重载函数。
    abstract public void accept(Visitor vistor);
}

class PdfFile2 extends ResourceFile2 {
    public PdfFile2(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    //...
}

class PPTFile2 extends ResourceFile2 {
    public PPTFile2(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    //...
}

class WordFile2 extends ResourceFile2 {
    public WordFile2(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    //...
}
//...PPTFile、WordFile跟PdfFile类似，这里就省略了...

interface Visitor {
    void visit(PdfFile2 pdfFile);
    void visit(PPTFile2 pdfFile);
    void visit(WordFile2 pdfFile);
}

class Extractor implements Visitor {
    @Override
    public void visit(PPTFile2 pptFile) {
        //...
        System.out.println("Extract PPT.");
    }

    @Override
    public void visit(PdfFile2 pdfFile) {
        //...
        System.out.println("Extract PDF.");
    }

    @Override
    public void visit(WordFile2 wordFile) {
        //...
        System.out.println("Extract WORD.");
    }
}

class Compressor implements Visitor {
    @Override
    public void visit(PPTFile2 pptFile) {
        //...
        System.out.println("Compress PPT.");
    }

    @Override
    public void visit(PdfFile2 pdfFile) {
        //...
        System.out.println("Compress PDF.");
    }

    @Override
    public void visit(WordFile2 wordFile) {
        //...
        System.out.println("Compress WORD.");
    }

}

class ToolApplication2 {
    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        List<ResourceFile2> resourceFiles = listAllResourceFiles(args[0]);
        for (ResourceFile2 resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
        }

        Compressor compressor = new Compressor();
        for(ResourceFile2 resourceFile : resourceFiles) {
            resourceFile.accept(compressor);
        }
    }

    private static List<ResourceFile2> listAllResourceFiles(String resourceDirectory) {
        List<ResourceFile2> resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile2("a.pdf"));
        resourceFiles.add(new WordFile2("b.word"));
        resourceFiles.add(new PPTFile2("c.ppt"));
        return resourceFiles;
    }
}


