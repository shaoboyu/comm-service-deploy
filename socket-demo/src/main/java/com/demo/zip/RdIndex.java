package com.demo.zip;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * recordSN M RD记录序号
 * rdType M 留存数据内容 业务使用数据 0 用户数据 1 终端数据 2 递交网管信息 3 计费数据 4
 * rdSubType M 留存数据子类型 多媒体会话记录0 用户消息记录1 群消息记录2 电子邮件记录3 浏览交互记录4
 * 支付记录5 授权记录6 位置业务记录7 IP地址变更记录8 扩展业务记录9 账号操作记录10 联系人操作记录11 群操作12  邮箱记录13
 * rdRecordContent M 留存记录内容
 */
public class RdIndex {
    @JSONField(serialize = false)
    private String rdId;

    @JSONField(name = "recordSN")
    private int recordSN;    // M RD记录序号

    @JSONField(name = "rdType")
    private String rdType;      // M 留存数据内容 业务使用数据 0 用户数据 1 终端数据 2 递交网管信息 3 计费数据 4

    @JSONField(name = "rdSubType")
    private String rdSubType;   // M 留存数据子类型 多媒体会话记录0 用户消息记录1 群消息记录2 电子邮件记录3 浏览交互记录4
                                // 支付记录5 授权记录6 位置业务记录7 IP地址变更记录8 扩展业务记录9 账号操作记录10 联系人操作记录11 群操作12  邮箱记录13
//    @JSONField(name = "rdRecordContent")
//    private RdRecordContent rdRecordContent;// M 留存记录内容

    @JSONField(serialize=false)
    private String fileSize;// M 文件大小

    @JSONField(serialize=false)
    private String fileName;// M 文件名

    @JSONField(serialize=false)
    private String fileUrl;// M 文件地址nncfp

    @JSONField(serialize=false)
    private String fileMd5;// M 文件id

    @JSONField(serialize=false)
    private String fileType;// M 文件类型

    @JSONField(serialize=false)
    private String rdrelatedID;// M 文件关联ID

    @JSONField(serialize=false)
    private long time;// 按照时间进行排序

    public String getRdId() {
        return rdId;
    }

    public void setRdId(String rdId) {
        this.rdId = rdId;
    }

    public int getRecordSN() {
        return recordSN;
    }

    public void setRecordSN(int recordSN) {
        this.recordSN = recordSN;
    }

    public String getRdType() {
        return rdType;
    }

    public void setRdType(String rdType) {
        this.rdType = rdType;
    }

    public String getRdSubType() {
        return rdSubType;
    }

    public void setRdSubType(String rdSubType) {
        this.rdSubType = rdSubType;
    }

//    public RdRecordContent getRdRecordContent() {
//        return rdRecordContent;
//    }
//
//    public void setRdRecordContent(RdRecordContent rdRecordContent) {
//        this.rdRecordContent = rdRecordContent;
//    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRdrelatedID() {
        return rdrelatedID;
    }

    public void setRdrelatedID(String rdrelatedID) {
        this.rdrelatedID = rdrelatedID;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
