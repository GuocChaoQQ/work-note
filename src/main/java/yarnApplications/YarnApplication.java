package yarnApplications;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.misc.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * created by chao.guo on 2020/8/6
 **/
public class YarnApplication {
    // 访问yarn 的 web api 接口

    public static void main(String[] args) throws IOException {
        //  通过api 获取yarn 上的任务 ?states=accepted,running,finished&applicationTypes=SPARK
        URL url = new URL("http://node47:8088/ws/v1/cluster/apps");

        URLConnection urlConnection = url.openConnection();
        urlConnection.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream inputStream = urlConnection.getInputStream();
        byte[] getData = readInputStream(inputStream);
        inputStream.read(getData);
        String str = new String(getData);
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONObject apps = jsonObject.getJSONObject("apps");
        JSONArray appArray = apps.getJSONArray("app");
        //{"preemptedMemorySeconds":0,"reservedVCores":-1,"applicationType":"SPARK","finalStatus":"SUCCEEDED","trackingUrl":"http://node47:8088/proxy/application_1594626096609_1440/","runningContainers":-1,"preemptedVcoreSeconds":0,"timeouts":{"timeout":[{"remainingTimeInSeconds":-1,"expiryTime":"UNLIMITED","type":"LIFETIME"}]},"clusterUsagePercentage":0.0,"queueUsagePercentage":0.0,"clusterId":1594626096609,"vcoreSeconds":63,"amRPCAddress":"node123:35685","preemptedResourceVCores":0,"numAMContainerPreempted":0,"allocatedMB":-1,"reservedMB":-1,"id":"application_1594626096609_1440","state":"FINISHED","amHostHttpAddress":"node123:8042","memorySeconds":113593,"unmanagedApplication":false,"amNodeLabelExpression":"","preemptedResourceMB":0,"resourceSecondsMap":{"entry":{"value":"63","key":"vcores"}},"applicationTags":"","startedTime":1594848751028,"trackingUI":"History","preemptedResourceSecondsMap":{},"priority":0,"numNonAMContainerPreempted":0,"amContainerLogs":"http://node123:8042/node/containerlogs/container_1594626096609_1440_01_000001/hive","launchTime":1594848751562,"allocatedVCores":-1,"diagnostics":"","logAggregationStatus":"SUCCEEDED","name":"Hive on Spark (hiveSessionId = ef30cd80-50ed-4801-9d62-fc02ca0f80e3)","progress":100.0,"finishedTime":1594848767154,"user":"hive","queue":"root.users.hive","elapsedTime":16126}
        appArray.forEach(it->System.out.println(it.toString()));


//        apps.get()




        //System.out.println ("打印内容："+str);


    }
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }



}
