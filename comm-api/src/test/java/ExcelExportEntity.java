/**
 * @author yushaobo
 * @create 2019-08-26 18:30
 **/
public class ExcelExportEntity {

    /**
     * 如果是MAP导出,这个是map的key
     */
    private Object                  key;

    private double                  width           = 10;

    private double                  height          = 10;

    /**
     * 图片的类型,1是文件,2是数据库
     */
    private int                     exportImageType = 0;

    /**
     * 排序顺序
     */
    private int                     orderNum        = 0;

    /**
     * 是否支持换行
     */
    private boolean                 isWrap;

    /**
     * 是否需要合并
     */
    private boolean                 needMerge;
    /**
     * 单元格纵向合并
     */
    private boolean                 mergeVertical;
    /**
     * 合并依赖
     */
    private int[]                   mergeRely;
    /**
     * 后缀
     */
    private String                  suffix;
    /**
     * 统计
     */
    private boolean                 isStatistics;

    private String                   numFormat;
//
//    private List<ExcelExportEntity> list;
}
