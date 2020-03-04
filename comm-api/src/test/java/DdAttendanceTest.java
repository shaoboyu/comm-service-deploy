import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import java.util.Arrays;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-7-29
 **/
public class DdAttendanceTest {

    //7200s
    private final static String accessToken = "11c976f2a1853594a2d3d09894773131";

//    private final static String appKey = "dingh6patsukfaapgcg1";

//    private final static String appSecret = "xipN9KhPyI6yjumEvEjDTSsfGVqXhWeWaTn0mMF-1FuKAsYPDVLJO-hugkRsCoHz";

    //
    //山东阳光金服
    private final static String appKey = "ding8ghsfn8c2we87msx";
    private final static String appSecret = "9mV0V0G2tks5UJTRdkJ20sxdpGj6FBo4HPS_G-FR-1PnEqJ2MIpIgifXmA8Hc0-I";


    public static void main(String[] args) {
        String asToken = getASToken();
        System.out.println(asToken);
//        getListRecord();

//        getDepartList();
//        getDepartUserInfo();
//        getDepartInfo();
//        getUserInfo();

//        getUserWork();
//        getQuerydimission();
//        getEmployeeListdimission();
//        getHrmEmployeeList();
    }

    /**
     * 获取钉钉开放平台ACCESS_TOKEN
     *
     * @return
     */
    public static String getASToken(){
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(appKey);
            request.setAppsecret(appSecret);
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);

            System.out.println(JSON.toJSONString(response,true));
            String accessToken = response.getAccessToken();
            return accessToken;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取考勤详情
     * 1.必须指定useId
     *
     * @return
     */
    public static String getListRecord(){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/listRecord");
            OapiAttendanceListRecordRequest request = new OapiAttendanceListRecordRequest();
            request.setCheckDateFrom("2019-07-29 00:00:00");
            request.setCheckDateTo("2019-07-30 00:00:00");
            request.setUserIds(Arrays.asList("manager5723"));
            OapiAttendanceListRecordResponse execute = client.execute(request,accessToken);
            System.out.println(JSON.toJSONString(execute,true));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取部门列表
     * 1.部门ID为1时默认根部门，则获取所有部门信息
     *
     */
    public static void getDepartList(){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
            OapiDepartmentListRequest request = new OapiDepartmentListRequest();
            request.setId("112777165");
            request.setHttpMethod("GET");
            OapiDepartmentListResponse response = client.execute(request, accessToken);
            System.out.println(JSON.toJSONString(response,true));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getDepartInfo(){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/get");
            OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
            request.setId("112782193");
            request.setHttpMethod("GET");
            OapiDepartmentGetResponse response = client.execute(request, accessToken);
            System.out.println(JSON.toJSONString(response,true));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getDepartUserInfo(){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
            OapiUserListbypageRequest request = new OapiUserListbypageRequest();
            request.setDepartmentId(129949619L);
            request.setOffset(2L);
            request.setSize(10L);
            request.setOrder("entry_desc");
            request.setHttpMethod("GET");
            OapiUserListbypageResponse execute = client.execute(request,accessToken);
            System.out.println(JSON.toJSONString(execute,true));
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void getUserInfo(){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid("273762664126221050");
        request.setHttpMethod("GET");
        try {
            OapiUserGetResponse response = client.execute(request, accessToken);
            System.out.println(JSON.toJSONString(response,true));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void getUserWork(){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/queryonjob");
        OapiSmartworkHrmEmployeeQueryonjobRequest req = new OapiSmartworkHrmEmployeeQueryonjobRequest();
        req.setStatusList("2,3");
        req.setOffset(2l);
        req.setSize(20l);
        try {
            OapiSmartworkHrmEmployeeQueryonjobResponse response = client.execute(req , accessToken);
            System.out.println(JSON.toJSONString(response,true));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取离职员工列表
     */
    private static void getQuerydimission(){
        try {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/querydimission");
        OapiSmartworkHrmEmployeeQuerydimissionRequest req = new OapiSmartworkHrmEmployeeQuerydimissionRequest();
        req.setOffset(0l);
        req.setSize(50l);
        OapiSmartworkHrmEmployeeQuerydimissionResponse rsp = client.execute(req , accessToken);
            System.out.println(JSON.toJSONString(rsp,true));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 离职信息
     */
    private static void getEmployeeListdimission(){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/listdimission");
            OapiSmartworkHrmEmployeeListdimissionRequest req = new OapiSmartworkHrmEmployeeListdimissionRequest();
            req.setUseridList("130564131240090196");
            OapiSmartworkHrmEmployeeListdimissionResponse response = client.execute(req , accessToken);
            System.out.println(JSON.toJSONString(response,true));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void getHrmEmployeeList(){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/list");
            OapiSmartworkHrmEmployeeListRequest req = new OapiSmartworkHrmEmployeeListRequest();
            req.setUseridList("031259633639239340");
//            req.setFieldFilterList("sys00-name,sys00-email");
            OapiSmartworkHrmEmployeeListResponse rsp = client.execute(req, accessToken);
            System.out.println(JSON.toJSONString(rsp,true));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}

