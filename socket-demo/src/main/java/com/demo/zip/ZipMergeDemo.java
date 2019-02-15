package com.demo.zip;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: yushaobo
 * @create: 19-2-15
 **/
public class ZipMergeDemo {

    private final static Logger LOGGER = LoggerFactory.getLogger(ZipMergeDemo.class);

    //1.指定输出路径 /data/output/
    private static String basePath = "/data/output/";

    public static void main(String[] args) {

    }

    /***
     * 1.会在指定输出路径下创建一个任务ID的文件夹
     * 2.将两者解压至当前文件夹
     * 3.
     *
     * @param file1
     * @param file2
     * @throws Exception
     */
    public static void megreFile(String file1, String file2) throws Exception {

        //当前留存任务ID
        String rdId = "";

        //待合并文件A
        String aFile = "";
        //待合并文件B
        String bFile = "";

        //2.确定当前输出文件编号,此文件用于上传至LEMF
        String outPutUpload = "";
        //3.剩余的文件存入缓存并设置超时时间
        String outPutCache = "";

        //上传至LEMF中的txt文件命名
        String outPutUploadTxt = "";

        //创建文件夹
        File outPutFolder = new File(basePath + rdId);
        if (!outPutFolder.exists()) {
            outPutFolder.createNewFile();
        }


        //处理A文件
        List<RdIndex> aRdIndices = unZip(aFile, rdId);

        //处理B文件
        List<RdIndex> bRdIndices = unZip(bFile, rdId);

        aRdIndices.addAll(bRdIndices);
        List<RdIndex> collect = aRdIndices.stream().sorted(Comparator.comparing(RdIndex::getTime)).collect(Collectors.toList());

        int i = 1;
        //recordSN进行重新编号
        int recordSN = 1;
        for (RdIndex rdIndex : collect) {
            rdIndex.setRecordSN(recordSN);
            recordSN++;
        }

        int max = 100;
        //截取,当前为需要上报的
        List<RdIndex> aColletc = collect.subList(0, max);


    }

    /**
     * 负责解压文件
     *
     * @param unZipfilePath
     * @param rdId
     * @return
     * @throws Exception
     */
    public static List<RdIndex> unZip(String unZipfilePath, String rdId) throws Exception {
        //
        List<RdIndex> rdIndices = null;

        //将重新排序后的文件输出进行压缩并上传至LEMF
        try {
            //解压缩A
            ZipFile sourceZipFile = new ZipFile(unZipfilePath, "UTF-8");

            File file;
            InputStream inputStream;
            FileOutputStream fileOut;

            //遍历整个压缩包文件
            for (Enumeration entries = sourceZipFile.getEntries(); entries.hasMoreElements(); ) {
                //添加压缩项目
                ZipEntry entry = (ZipEntry) entries.nextElement();
                LOGGER.info("ZipEntry entry is:{}", entry.getName());
                //
                //若此文件为留存数据索引RdIndex
                if (entry.getName().contains(rdId)) {
                    //索引文件重新压缩为文件
                    InputStream inputStreamTxt = sourceZipFile.getInputStream((ZipArchiveEntry) entry);
                    byte zipSourceByte[] = new byte[1024];
                    int zipSourceLength = 0;
                    ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
                    while ((zipSourceLength = inputStreamTxt.read(zipSourceByte)) > 0) {
                        infoStream.write(zipSourceByte, 0, zipSourceLength);
                    }
                    //
                    //对RdIndex进行反序列化处理
                    rdIndices = JSONObject.parseArray(infoStream.toString("UTF-8"), RdIndex.class);

                }
                file = new File(basePath + rdId + "/" + entry.getName());

                //如果指定文件的目录不存在,则创建之.
                File parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                inputStream = sourceZipFile.getInputStream((ZipArchiveEntry) entry);

                byte buf[] = new byte[1024];
                int readedBytes = 0;
                fileOut = new FileOutputStream(file);
                while ((readedBytes = inputStream.read(buf)) > 0) {
                    fileOut.write(buf, 0, readedBytes);
                }
                fileOut.flush();
                fileOut.close();
            }
        } catch (Exception e) {
            LOGGER.error("modifyZip Exception", e);
        }
        return rdIndices;
    }
}
