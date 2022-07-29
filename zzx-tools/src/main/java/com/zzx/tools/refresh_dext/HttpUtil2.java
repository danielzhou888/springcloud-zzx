////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//package com.zzx.tools.refresh_dext;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.Map.Entry;
//
//import org.apache.commons.codec.Charsets;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.conn.ConnectionKeepAliveStrategy;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class HttpUtil2 {
//    private static final CloseableHttpClient httpClient;
//    public static final String CHARSET = "UTF-8";
//    private static final Logger logger = LoggerFactory.getLogger(HttpUtil2.class);
//    private static final String APPLICATION_JSON = "application/json";
//    private static final String CONTENT_TYPE_JSON = "text/json";
//    private static final String APPLICATION_FORM = "application/x-www-form-urlencoded";
//    private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";
//
//    public HttpUtil2() {
//    }
//
//    public static String doDelete(String url, Map<String, String> params) {
//        return doDelete(url, params, "UTF-8", false);
//    }
//
//    public static String doPut(String url, Map<String, String> params) {
//        return doPut(url, params, "UTF-8", false);
//    }
//
//    public static String doGet(String url, Map<String, String> params) {
//        return doGet(url, params, "UTF-8", false);
//    }
//
//    public static String doGet(String url, Map<String, String> params, List<HeaderVo> headers) {
//        return doGet(url, params, "UTF-8", false, headers);
//    }
//
//    public static String doPost(String url, Map<String, String> params) {
//        return doPost(url, params, "UTF-8", false);
//    }
//
//    public static String doGetObj(String url, Map<String, Object> params) {
//        return doGetObj(url, params, "UTF-8", false);
//    }
//
//    public static String doPostObj(String url, Map<String, Object> params) {
//        return doPostObj(url, params, "UTF-8", false);
//    }
//
//    public static String doPostObj(String url, Map<String, Object> params, int timeout) {
//        return doPostObj(url, params, "UTF-8", false, timeout);
//    }
//
//    public static String doPostJSON(String url, String josnStr) {
//        return doPostJSON(url, josnStr, false);
//    }
//
//    public static String doGet(String url, Map<String, String> params, boolean isExceptionThrow) {
//        return doGet(url, params, "UTF-8", isExceptionThrow);
//    }
//
//    public static String doPost(String url, Map<String, String> params, boolean isExceptionThrow) {
//        return doPost(url, params, "UTF-8", isExceptionThrow);
//    }
//
//    public static String doGetObj(String url, Map<String, Object> params, boolean isExceptionThrow) {
//        return doGetObj(url, params, "UTF-8", isExceptionThrow);
//    }
//
//    public static String doPostObj(String url, Map<String, Object> params, boolean isExceptionThrow) {
//        return doPostObj(url, params, "UTF-8", isExceptionThrow);
//    }
//
//    public static String doGet(String url, Map<String, String> params, String charset, boolean isExceptionThrow) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                if (params != null && !params.isEmpty()) {
//                    List<NameValuePair> pairs = new ArrayList(params.size());
//                    Iterator var5 = params.entrySet().iterator();
//
//                    while(var5.hasNext()) {
//                        Entry<String, String> entry = (Entry)var5.next();
//                        String value = (String)entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), value));
//                        }
//                    }
//
//                    url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
//                }
//
//                HttpGet httpGet = new HttpGet(url);
//                CloseableHttpResponse response = httpClient.execute(httpGet);
//
//                String var9;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpGet.abort();
//                        logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var9 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var9;
//            } catch (Exception var14) {
//                logger.error("HTTP连接异常  " + var14.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var14.getMessage(), var14);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doGet(String url, Map<String, String> params, String charset, boolean isExceptionThrow, List<HeaderVo> headers) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                String method = (String)params.get("method");
//                logger.info("msg method【" + method + "】URL：" + url);
//                Iterator var7;
//                if (params != null && !params.isEmpty()) {
//                    List<NameValuePair> pairs = new ArrayList(params.size());
//                    var7 = params.entrySet().iterator();
//
//                    while(var7.hasNext()) {
//                        Entry<String, String> entry = (Entry)var7.next();
//                        String value = (String)entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), value));
//                        }
//                    }
//
//                    url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
//                }
//
//                HttpGet httpGet = new HttpGet(url);
//                if (headers != null && headers.size() > 0) {
//                    var7 = headers.iterator();
//
//                    while(var7.hasNext()) {
//                        HeaderVo headerVo = (HeaderVo)var7.next();
//                        httpGet.setHeader(headerVo.getKey(), headerVo.getValue());
//                    }
//                }
//
//                CloseableHttpResponse response = httpClient.execute(httpGet);
//
//                String var11;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpGet.abort();
//                        logger.error("HTTP连接异常  msg,error status code :" + statusCode);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    logger.debug(method + "：" + entity);
//                    logger.info("【" + method + "】 end http status :" + statusCode);
//                    var11 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var11;
//            } catch (Exception var16) {
//                logger.error("HTTP连接异常  " + var16.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var16.getMessage(), var16);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doDelete(String url, Map<String, String> params, String charset, boolean isExceptionThrow) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                if (params != null && !params.isEmpty()) {
//                    List<NameValuePair> pairs = new ArrayList(params.size());
//                    Iterator var5 = params.entrySet().iterator();
//
//                    while(var5.hasNext()) {
//                        Entry<String, String> entry = (Entry)var5.next();
//                        String value = (String)entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), value));
//                        }
//                    }
//
//                    url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
//                }
//
//                HttpDelete httpDelete = new HttpDelete(url);
//                CloseableHttpResponse response = httpClient.execute(httpDelete);
//
//                String var9;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpDelete.abort();
//                        logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var9 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var9;
//            } catch (Exception var14) {
//                logger.error("HTTP连接异常  " + var14.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var14.getMessage(), var14);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doPost(String url, Map<String, String> params, String charset, boolean isExceptionThrow) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                List<NameValuePair> pairs = null;
//                if (params != null && !params.isEmpty()) {
//                    pairs = new ArrayList(params.size());
//                    Iterator var5 = params.entrySet().iterator();
//
//                    while(var5.hasNext()) {
//                        Entry<String, String> entry = (Entry)var5.next();
//                        String value = (String)entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), value));
//                        }
//                    }
//                }
//
//                HttpPost httpPost = new HttpPost(url);
//                if (pairs != null && pairs.size() > 0) {
//                    httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
//                }
//
//                CloseableHttpResponse response = httpClient.execute(httpPost);
//
//                String var10;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpPost.abort();
//                        logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode + ",url=" + url);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var10 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var10;
//            } catch (Exception var15) {
//                logger.error("HTTP连接异常  " + var15.getMessage() + "url=" + url, var15);
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var15.getMessage(), var15);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doPostJSON(String url, String josnStr, boolean isExceptionThrow) {
//        if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(josnStr)) {
//            try {
//                HttpPost httpPost = new HttpPost(url);
//                httpPost.addHeader("Content-Type", "application/json");
//                StringEntity se = new StringEntity(josnStr, "utf-8");
//                se.setContentType("text/json");
//                se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
//                httpPost.setEntity(se);
//                CloseableHttpResponse response = httpClient.execute(httpPost);
//
//                String var9;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpPost.abort();
//                        logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode + ",url=" + url);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var9 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var9;
//            } catch (Exception var14) {
//                logger.error("HTTP连接异常  " + var14.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var14.getMessage(), var14);
//                } else {
//                    return null;
//                }
//            }
//        } else {
//            return null;
//        }
//    }
//
//    public static String postFile(String url, Map<String, Object> params, String fileName, boolean isExceptionThrow) {
//        HttpPost post = new HttpPost(url);
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//        Set<String> pNames = params.keySet();
//        Iterator var7 = pNames.iterator();
//
//        while(var7.hasNext()) {
//            String key = (String)var7.next();
//            Object obj = params.get(key);
//            if (obj instanceof String) {
//                builder.addTextBody(key, obj.toString(), ContentType.TEXT_PLAIN);
//            }
//
//            if (obj instanceof Integer) {
//                builder.addTextBody(key, obj.toString(), ContentType.TEXT_PLAIN);
//            }
//
//            if (obj instanceof Long) {
//                builder.addTextBody(key, obj.toString(), ContentType.TEXT_PLAIN);
//            }
//
//            if (obj instanceof byte[]) {
//                builder.addBinaryBody(key, (byte[])((byte[])obj), ContentType.DEFAULT_BINARY, fileName);
//            }
//        }
//
//        HttpEntity entity = builder.build();
//        post.setEntity(entity);
//
//        try {
//            CloseableHttpResponse response = httpClient.execute(post);
//
//            String var12;
//            try {
//                int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode != 200) {
//                    post.abort();
//                    logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode);
//                }
//
//                HttpEntity entity2 = response.getEntity();
//                String result = null;
//                if (entity2 != null) {
//                    result = EntityUtils.toString(entity2, "utf-8");
//                }
//
//                EntityUtils.consume(entity2);
//                var12 = result;
//            } finally {
//                response.close();
//            }
//
//            return var12;
//        } catch (ClientProtocolException var18) {
//            var18.printStackTrace();
//            if (isExceptionThrow) {
//                throw new BusinessException(var18.getMessage(), var18);
//            }
//        } catch (IOException var19) {
//            var19.printStackTrace();
//            if (isExceptionThrow) {
//                throw new BusinessException(var19.getMessage(), var19);
//            }
//        }
//
//        return null;
//    }
//
//    public static String doPostFile(String url, byte[] content, boolean isExceptionThrow) {
//        HttpPost post = new HttpPost(url);
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.addBinaryBody("file", content, ContentType.create("image/png"), "1.png");
//        HttpEntity entity = builder.build();
//        post.setEntity(entity);
//
//        try {
//            CloseableHttpResponse response = httpClient.execute(post);
//
//            String var10;
//            try {
//                int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode != 200) {
//                    post.abort();
//                    logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode);
//                }
//
//                HttpEntity entity2 = response.getEntity();
//                String result = null;
//                if (entity2 != null) {
//                    result = EntityUtils.toString(entity2, "utf-8");
//                }
//
//                EntityUtils.consume(entity2);
//                var10 = result;
//            } finally {
//                response.close();
//            }
//
//            return var10;
//        } catch (ClientProtocolException var16) {
//            logger.error("上传文件异常: ", var16);
//            if (isExceptionThrow) {
//                throw new BusinessException(var16.getMessage(), var16);
//            }
//        } catch (IOException var17) {
//            logger.error("上传文件异常: ", var17);
//            if (isExceptionThrow) {
//                throw new BusinessException(var17.getMessage(), var17);
//            }
//        }
//
//        return null;
//    }
//
//    public static String doGetObj(String url, Map<String, Object> params, String charset, boolean isExceptionThrow) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                if (params != null && !params.isEmpty()) {
//                    List<NameValuePair> pairs = new ArrayList(params.size());
//                    Iterator var5 = params.entrySet().iterator();
//
//                    while(var5.hasNext()) {
//                        Entry<String, Object> entry = (Entry)var5.next();
//                        Object value = entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), String.valueOf(value)));
//                        }
//                    }
//
//                    url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
//                }
//
//                System.out.println(url);
//                HttpGet httpGet = new HttpGet(url);
//                CloseableHttpResponse response = httpClient.execute(httpGet);
//
//                String var9;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpGet.abort();
//                        logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var9 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var9;
//            } catch (Exception var14) {
//                logger.error("HTTP连接异常  " + var14.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var14.getMessage(), var14);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doPostObj(String url, Map<String, Object> params, String charset, boolean isExceptionThrow) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                List<NameValuePair> pairs = null;
//                if (params != null && !params.isEmpty()) {
//                    pairs = new ArrayList(params.size());
//                    Iterator var5 = params.entrySet().iterator();
//
//                    while(var5.hasNext()) {
//                        Entry<String, Object> entry = (Entry)var5.next();
//                        Object value = entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), String.valueOf(value)));
//                        }
//                    }
//                }
//
//                HttpPost httpPost = new HttpPost(url);
//                if (pairs != null && pairs.size() > 0) {
//                    httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
//                }
//
//                CloseableHttpResponse response = httpClient.execute(httpPost);
//
//                String var10;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpPost.abort();
//                        if (url.equals("http://101.68.83.91:9801/formal/erp/order/rest/")) {
//                            logger.info("HTTP连接异常    HttpClient,error status code :" + statusCode + ",url=" + url);
//                        } else {
//                            logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode + ",url=" + url);
//                        }
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var10 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var10;
//            } catch (Exception var15) {
//                logger.error("URL=" + url + "HTTP连接异常  " + var15.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var15.getMessage(), var15);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doPostObj(String url, Map<String, Object> params, String charset, boolean isExceptionThrow, int timeout) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                List<NameValuePair> pairs = null;
//                if (params != null && !params.isEmpty()) {
//                    pairs = new ArrayList(params.size());
//                    Iterator var6 = params.entrySet().iterator();
//
//                    while(var6.hasNext()) {
//                        Entry<String, Object> entry = (Entry)var6.next();
//                        Object value = entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), String.valueOf(value)));
//                        }
//                    }
//                }
//
//                HttpPost httpPost = new HttpPost(url);
//                if (pairs != null && pairs.size() > 0) {
//                    httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
//                }
//
//                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
//                httpPost.setConfig(requestConfig);
//                CloseableHttpResponse response = httpClient.execute(httpPost);
//
//                String var12;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpPost.abort();
//                        logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var12 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var12;
//            } catch (Exception var17) {
//                logger.error("HTTP连接异常:" + url + "\n" + var17.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var17.getMessage(), var17);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doPut(String url, Map<String, String> params, String charset, boolean isExceptionThrow) {
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        } else {
//            try {
//                List<NameValuePair> pairs = null;
//                if (params != null && !params.isEmpty()) {
//                    pairs = new ArrayList(params.size());
//                    Iterator var5 = params.entrySet().iterator();
//
//                    while(var5.hasNext()) {
//                        Entry<String, String> entry = (Entry)var5.next();
//                        String value = (String)entry.getValue();
//                        if (value != null) {
//                            pairs.add(new BasicNameValuePair((String)entry.getKey(), value));
//                        }
//                    }
//                }
//
//                HttpPut httpPut = new HttpPut(url);
//                httpPut.addHeader("Content-Type", "application/x-www-form-urlencoded");
//                if (pairs != null && pairs.size() > 0) {
//                    httpPut.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
//                }
//
//                CloseableHttpResponse response = httpClient.execute(httpPut);
//
//                String var10;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpPut.abort();
//                        logger.error("HTTP连接异常    HttpClient,error status code :" + statusCode);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var10 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var10;
//            } catch (Exception var15) {
//                logger.error("HTTP连接异常  " + var15.getMessage());
//                if (isExceptionThrow) {
//                    throw new BusinessException("HTTP连接异常  " + var15.getMessage(), var15);
//                } else {
//                    return null;
//                }
//            }
//        }
//    }
//
//    public static String doPost(String url, Map<String, String> apiMap, Map<String, String> headerMap) {
//        try {
//            List<NameValuePair> parameters = null;
//            if (null != apiMap && !apiMap.isEmpty()) {
//                parameters = new ArrayList(apiMap.size());
//                Iterator var4 = apiMap.entrySet().iterator();
//
//                while(var4.hasNext()) {
//                    Entry<String, String> entry = (Entry)var4.next();
//                    if (null != entry.getValue()) {
//                        parameters.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
//                    }
//                }
//            }
//
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//            if (null != headerMap && !headerMap.isEmpty()) {
//                Iterator var16 = headerMap.entrySet().iterator();
//
//                while(var16.hasNext()) {
//                    Entry<String, String> header = (Entry)var16.next();
//                    httpPost.setHeader((String)header.getKey(), (String)header.getValue());
//                }
//            }
//
//            if (null != parameters && !parameters.isEmpty()) {
//                httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
//            }
//
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//
//            String var9;
//            try {
//                int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode != 200) {
//                    httpPost.abort();
//                    logger.error("http请求失败，地址：{}，状态码：{}", url, statusCode);
//                }
//
//                HttpEntity httpEntity = response.getEntity();
//                String apiResult = null;
//                if (null != httpEntity) {
//                    apiResult = EntityUtils.toString(httpEntity, "UTF-8");
//                }
//
//                EntityUtils.consume(httpEntity);
//                var9 = apiResult;
//            } finally {
//                response.close();
//            }
//
//            return var9;
//        } catch (Exception var14) {
//            logger.error("http连接异常", var14);
//            return null;
//        }
//    }
//
//    public static String postJson(String url, String josnStr) {
//        if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(josnStr)) {
//            try {
//                HttpPost httpPost = new HttpPost(url);
//                httpPost.addHeader("Content-Type", "application/json");
//                httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
//                StringEntity se = new StringEntity(josnStr, "utf-8");
//                se.setContentType("text/json");
//                se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
//                httpPost.setEntity(se);
//                CloseableHttpResponse response = httpClient.execute(httpPost);
//
//                String var8;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpPost.abort();
//                        logger.error("HTTP连接异常   HttpClient,error status code :" + statusCode + ",url=" + url);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var8 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var8;
//            } catch (Exception var13) {
//                logger.error("HTTP连接异常  " + var13.getMessage());
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
//
//    public static String post(String url, Map<String, Object> params) throws BusinessException {
//        StringBuffer sb = new StringBuffer();
//        String strParam = "";
//        Iterator response;
//        String key;
//        if (params != null) {
//            response = params.keySet().iterator();
//
//            while(response.hasNext()) {
//                key = (String)response.next();
//                sb.append(String.format("%s=%s&", key, params.get(key)));
//            }
//
//            strParam = sb.substring(0, sb.length() - 1);
//        }
//
//        CloseableHttpResponse res = null;
//
//        String responses = "";
//        try {
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//            ContentType ctype = ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), Charsets.UTF_8.name());
//            StringEntity fsf = new StringEntity(strParam, ctype);
//            httpPost.setEntity(fsf);
//            logger.info(String.format("Request send params [%s], xxx-form-urlencord", strParam));
//            res = httpclient.execute(httpPost);
//            logger.info("doPost[" + url + "] status:" + res);
//            HttpEntity entity = res.getEntity();
//            responses = EntityUtils.toString(entity, Charsets.UTF_8.name());
//        } catch (Exception var18) {
//            String info = String.format("请求URL=%s失败", url);
//            logger.error(info, params, var18);
//            throw new BusinessException(info);
//        } finally {
//            try {
//                res.close();
//            } catch (IOException var17) {
//                var17.printStackTrace();
//            }
//
//        }
//
//        return responses;
//    }
//
//    public static String postJson(String url, String json, List<HeaderVo> headerList) {
//        if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(json)) {
//            try {
//                HttpPost httpPost = new HttpPost(url);
//                httpPost.addHeader("Content-Type", "application/json");
//                if (headerList != null && headerList.size() > 0) {
//                    headerList.forEach((h) -> {
//                        httpPost.addHeader(h.getKey(), h.getValue());
//                    });
//                }
//
//                StringEntity se = new StringEntity(json, "utf-8");
//                se.setContentType("text/json");
//                se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
//                httpPost.setEntity(se);
//                CloseableHttpResponse response = httpClient.execute(httpPost);
//
//                String var9;
//                try {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode != 200) {
//                        httpPost.abort();
//                        logger.error("HTTP连接异常   HttpClient,error status code :" + statusCode + ",url=" + url);
//                    }
//
//                    HttpEntity entity = response.getEntity();
//                    String result = null;
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity, "utf-8");
//                    }
//
//                    EntityUtils.consume(entity);
//                    var9 = result;
//                } finally {
//                    response.close();
//                }
//
//                return var9;
//            } catch (Exception var14) {
//                logger.error("HTTP连接异常  " + var14.getMessage());
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
//
//    public static String doPostToCds(String url, String jsonStr, Map<String, String> headerMap) {
//        try {
//            HttpPost httpPost = new HttpPost(url);
//            StringEntity se = new StringEntity(jsonStr, "utf-8");
//            se.setContentType("text/json");
//            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
//            httpPost.setEntity(se);
//            httpPost.addHeader("Content-Type", "application/json");
//            if (null != headerMap && !headerMap.isEmpty()) {
//                Iterator var5 = headerMap.entrySet().iterator();
//
//                while(var5.hasNext()) {
//                    Entry<String, String> header = (Entry)var5.next();
//                    httpPost.setHeader((String)header.getKey(), (String)header.getValue());
//                }
//            }
//
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//
//            String var9;
//            try {
//                int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode != 200) {
//                    httpPost.abort();
//                    logger.error("http请求失败，地址：{}，状态码：{}", url, statusCode);
//                }
//
//                HttpEntity httpEntity = response.getEntity();
//                String apiResult = null;
//                if (null != httpEntity) {
//                    apiResult = EntityUtils.toString(httpEntity, "UTF-8");
//                }
//
//                EntityUtils.consume(httpEntity);
//                var9 = apiResult;
//            } finally {
//                response.close();
//            }
//
//            return var9;
//        } catch (Exception var14) {
//            logger.error("http连接异常", var14);
//            return null;
//        }
//    }
//
//    static {
//        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(60000).setConnectTimeout(60000).setSocketTimeout(60000).build();
//        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();
//        pcm.setMaxTotal(200);
//        pcm.setDefaultMaxPerRoute(pcm.getMaxTotal());
//        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
//            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
//                return 180000L;
//            }
//        };
//        httpClient = HttpClientBuilder.create().setConnectionManager(pcm).setDefaultRequestConfig(config).setKeepAliveStrategy(myStrategy).build();
//    }
//}
