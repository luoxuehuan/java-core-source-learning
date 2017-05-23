package com.java.guava;



import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static java.lang.System.*;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

/**
 * Created by hulb on 17/5/23.
 */
public class GuavaFileTest {

    public static void  main(String[] arg){
        GuavaFileTest guavaFileTest = new GuavaFileTest();
        guavaFileTest.demoFileWrite("./data/test2.txt","12345");


       // guavaFileTest.demoFileRead("");
        //guavaFileTest.demoBigFileRead("");

    }


    /**
     * 演示向文件中写入字节流
     *
     * @param fileName 要写入文件的文件名
     * @param contents 要写入的文件内容
     */
    public void demoFileWrite(final String fileName, final String contents)
    {
        checkNotNull(fileName, "Provided file name for writing must NOT be null.");
        checkNotNull(contents, "Unable to write null contents.");
        final File newFile = new File(fileName);
        try
        {
            Files.write(contents.getBytes(), newFile);
        } catch (IOException fileIoEx) {
            err.println(  "ERROR trying to write to file '" + fileName + "' - "
                    + fileIoEx.toString());
        }
    }



    /**
     * 演示读取（小）文件
     *
     * @param fileName 要写入文件的文件名
     */
    public void demoFileRead(final String fileName) {
        String testFilePath = "./data/test1.txt";
        File testFile = new File(testFilePath);
        List<String> lines = null;
        try {

            /**
             * 这里的readLines方法返回的是List<String>的接口，这对于大文件处理是会有问题的
             * 大文件处理可以使用readLines方法的另一个重载
             */
            lines = Files.readLines(testFile, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            System.out.println(line);
        }
    }
    /**
     * 演示读取（小）文件
     *
     * @param fileName 要写入文件的文件名
     */
    public void demoBigFileRead(final String fileName) {
        String testFilePath = "./data/test1.txt";
        File testFile = new File(testFilePath);
        CounterLine counter = new CounterLine();
        try {
            Files.readLines(testFile, Charsets.UTF_16, counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getResult());

    }


    /**
     * 演示如何使用guava的Files.copy方法复制文件
     *
     * @param sourceFileName 复制的源文件名
     * @param targetFileName 目标文件名
     */
    public void demoSimpleFileCopy(
            final String sourceFileName, final String targetFileName)
    {
        checkNotNull(sourceFileName, "Copy source file name must NOT be null.");
        checkNotNull(targetFileName, "Copy target file name must NOT be null.");
        final File sourceFile = new File(sourceFileName);
        final File targetFile = new File(targetFileName);
        try
        {
            Files.copy(sourceFile, targetFile);
        }
        catch (IOException fileIoEx)
        {
            err.println(
                    "ERROR trying to copy file '" + sourceFileName
                            + "' to file '" + targetFileName + "' - " + fileIoEx.toString());
        }
    }



    /**
     * 演示 Files.equal(File,File) 来比较两个文件的内容
     *
     * @param fileName1 比较的文件1文件名
     * @param fileName2 比较的文件2文件名
     */
    public void demoEqual(final String fileName1, final String fileName2)
    {
        checkNotNull(fileName1, "First file name for comparison must NOT be null.");
        checkNotNull(fileName2, "Second file name for comparison must NOT be null.");
        final File file1 = new File(fileName1);
        final File file2 = new File(fileName2);
        try
        {
            out.println(
                    "File '" + fileName1 + "' "
                            + (Files.equal(file1, file2) ? "IS" : "is NOT")
                            + " the same as file '" + fileName2 + "'.");
        }
        catch (IOException fileIoEx)
        {
            err.println(
                    "ERROR trying to compare two files '"
                            + fileName1 + "' and '" + fileName2 + "' - " + fileIoEx.toString());
        }
    }


    static class CounterLine implements LineProcessor<Integer> {
        private int rowNum = 0;
        @Override
        public boolean processLine(String line) throws IOException {
            rowNum ++;
            return true;
        }

        @Override
        public Integer getResult() {
            return rowNum;
        }
    }
}
