package com.demo.zip;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import java.io.*;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: yushaobo
 * @create: 19-2-15
 **/
public class ZipMergeDemo {


    private static ConcurrentHashMap<String,List<RdIndex>> concurrentHashMap =  new ConcurrentHashMap<>();

    //1.指定输出路径 /data/output/
    private static String basePath = "/data/output/";

    public static void main(String[] args) {
        String file1 = "/home/yushaobo/RdIndex/RD-30a115f24904476f836ca17d0e72e911-1-00000001.zip";
        String file2 = "/home/yushaobo/RdIndex/RD-30a115f24904476f836ca17d0e72e911-2-00000001.zip";
        try {
            megreFile(file1,file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String rdId = "30a11";

        //待合并文件A
        String aFile = file1;
        //待合并文件B
        String bFile = file2;

        //2.确定当前输出文件编号,此文件用于上传至LEMF
        String outPutUpload = "";
        //3.剩余的文件存入缓存并设置超时时间
        String outPutCache = "";

        //上传至LEMF中的txt文件命名
        String outPutUploadTxt = "";

        String rdFilePath = basePath + rdId;
        //创建文件夹
        File outPutFolder = new File(rdFilePath);
        if (!outPutFolder.exists()) {
            outPutFolder.mkdirs();
        }

        //处理A文件
        List<RdIndex> aRdIndices = unZip(aFile, rdId);

        //处理B文件
        List<RdIndex> bRdIndices = unZip(bFile, rdId);

        aRdIndices.addAll(bRdIndices);
        List<RdIndex> collect = aRdIndices.stream().sorted(Comparator.comparing(RdIndex::getTime)).collect(Collectors.toList());

        //recordSN进行重新编号
        int recordSN = 1;
        for (RdIndex rdIndex : collect) {
            rdIndex.setRecordSN(recordSN);
            recordSN++;
        }

        int max = 10;
        //截取,当前为需要上报的
        List<RdIndex> aColletc = collect.subList(0, max);

        //进行压缩上传
        doZip(aColletc,rdFilePath,rdId,1);

        //collect剩余的存入缓存
        concurrentHashMap.put(rdId,collect);
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
//                LOGGER.info("ZipEntry entry is:{}", entry.getName());
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
            e.printStackTrace();
//            LOGGER.error("modifyZip Exception", e);
        }
        return rdIndices;
    }

    /**
     * 进行压缩操作
     * 1.符合上传要求的进行压缩并上传LEMF
     * 2.
     * @param rdIndices
     */
    public static void doZip(List<RdIndex> rdIndices,String filePath ,String rdId , int rdSn){
        try{
            //某rdId文件夹下所有文件
            File sourceFilePath = new File(filePath);

            FileInputStream fis = null;
            BufferedInputStream bis = null;
            FileOutputStream fileOutputStream = null;
            ZipOutputStream zipOutputStream = null;

            if (sourceFilePath.exists()){
                File[] sourceFiles = sourceFilePath.listFiles();

                File zipFile = new File(filePath +"/"+ rdId + "-" + rdSn +".zip");

                fileOutputStream = new FileOutputStream(zipFile);
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
                byte[] bufs = new byte[1024*10];

                for(int i=0;i<sourceFiles.length;i++){
                    //创建ZIP实体，并添加进压缩包

                    boolean contain = isContain(sourceFiles[i].getName(), rdIndices);
                    if (contain){
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zipOutputStream.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024*10);
                        int read = 0;
                        while((read=bis.read(bufs, 0, 1024*10)) != -1){
                            zipOutputStream.write(bufs,0,read);
                        }
                    }
                }
                zipOutputStream.putNextEntry(new ZipEntry(rdId + "-" + rdSn+".txt"));
                zipOutputStream.write(JSON.toJSONString(rdIndices, true).getBytes("UTF-8"));

                zipOutputStream.flush();
                zipOutputStream.close();
                fileOutputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
//            LOGGER.error("doZip error :{}",e);
        }
    }

    /**
     * 判断当前文件是否在压缩
     *
     * @param fileName
     * @param rdIndices
     * @return
     */
    public static boolean isContain(String fileName,List<RdIndex> rdIndices){
        try {
            for (RdIndex rdIndex: rdIndices) {
                //rdrelatedID
                boolean contains = rdIndex.getFileName().contains(fileName);
                if (contains){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
//            LOGGER.error("isContain error :{}",e);
        }
        return false;
    }
}
