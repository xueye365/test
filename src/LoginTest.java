package src;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginTest {
    private static RequestConfig requestConfig;

    public LoginTest() {
    }

    public static void main(String[] args) {
        test("sunxiaoli", "aaa111", "finance-test2.shanyishanmei.com");
    }

    public static String[] test(String username, String password, String env) {
        try {
            HttpClientContext context = getHttpContext();
            String url1 = "http://123.56.250.28:6080/cas/login?authn_method=captcha&service=http%3A%2F%2F" + env + "%3A8080%2F";
            String[] resFirst = sendHttpPostJson(url1, (List)null, context, (String)null, env);
            String resText = resFirst[1];
            List reqParams = new ArrayList();
            reqParams.add(new BasicNameValuePair("username", username));
            reqParams.add(new BasicNameValuePair("password", password));
            reqParams.add(new BasicNameValuePair("lt", regexLt(resText)));
            reqParams.add(new BasicNameValuePair("execution", regexExecution(resText)));
            reqParams.add(new BasicNameValuePair("_eventId", "submit"));
            String[] resSecond = sendHttpPostJson(url1, reqParams, context, (String)null, env);
            String resText2 = resSecond[1];
            List reqParams2 = new ArrayList();
            reqParams2.add(new BasicNameValuePair("captcha", "1"));
            reqParams2.add(new BasicNameValuePair("lt", ""));
            reqParams2.add(new BasicNameValuePair("execution", regexExecution(resText2)));
            reqParams2.add(new BasicNameValuePair("_eventId", "verifyCaptchaSubmit"));

            try {
                Thread.sleep(1000L);
            } catch (Exception var11) {
                ;
            }

            return sendHttpPostJson(url1, reqParams2, context, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36", env);
        } catch (Exception var12) {
            var12.printStackTrace();
            return new String[]{"test()error", ""};
        }
    }

    public static String[] sendHttpPostJson(String httpUrl, List<NameValuePair> reqParams, HttpClientContext context, String userAgent, String env) {
        HttpPost httpPost = new HttpPost(httpUrl);

        try {
            if (reqParams != null && reqParams.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(reqParams, Consts.UTF_8));
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return sendHttpPost(httpPost, context, userAgent, env);
    }

    private static String[] sendHttpPost(HttpPost httpPost, HttpClientContext context, String userAgent, String env) {
        String[] res = new String[2];
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String responseContent = null;

        label153: {
            String[] var12;
            try {
                httpClient = getHttpClient(context, userAgent);
                httpPost.setConfig(requestConfig);
                response = httpClient.execute(httpPost, context);
                HttpEntity entity = response.getEntity();
                Header header;
                if (response.getStatusLine().getStatusCode() < 300 || httpPost.getURI().toString().startsWith("http://" + env + ":8080/?ticket=")) {
                    if (200 == response.getStatusLine().getStatusCode() || response.getStatusLine().getStatusCode() == 302) {
                        responseContent = EntityUtils.toString(entity, "UTF-8");
                        EntityUtils.consume(entity);
                    }

                    header = response.getFirstHeader("Set-Cookie");
                    if (header != null) {
                        HeaderElement[] var25 = header.getElements();
                        int var26 = var25.length;

                        for(int var27 = 0; var27 < var26; ++var27) {
                            HeaderElement e = var25[var27];
                            if (e.getName().equals("SESSION")) {
                                res[0] = e.getValue();
                            }
                        }
                    }
                    break label153;
                }

                if (response.getStatusLine().getStatusCode() != 302) {
                    throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
                }

                header = response.getFirstHeader("location");
                String newuri = header.getValue();
                Header headerT = response.getFirstHeader("Set-Cookie");
                HttpPost httpPostTemp = new HttpPost(newuri);
                var12 = sendHttpPost(httpPostTemp, context, userAgent, env);
            } catch (Exception var23) {
                var23.printStackTrace();
                break label153;
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException var22) {
                    var22.printStackTrace();
                }

            }

            return var12;
        }

        res[1] = responseContent;
        return res;
    }

    public static CloseableHttpClient getHttpClient(HttpClientContext context, String userAgent) {
        CloseableHttpClient httpClient;
        if (StringUtils.isEmpty(userAgent)) {
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).setDefaultCookieStore(context.getCookieStore()).build();
            return httpClient;
        } else {
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).setUserAgent(userAgent).setDefaultCookieStore(context.getCookieStore()).build();
            return httpClient;
        }
    }

    private static HttpClientContext getHttpContext() {
        CookieStore cookieStore = new BasicCookieStore();
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
        return context;
    }

    private static String regexLt(String resText) {
        String regexLt = "lt\".*\"(LT.*)\"/>";
        Pattern plt = Pattern.compile(regexLt);
        Matcher mlt = plt.matcher(resText);

        String lt;
        for(lt = ""; mlt.find(); lt = lt.substring(0, lt.length() - 3)) {
            lt = mlt.group();
            lt = lt.substring(lt.indexOf("value=") + 7);
        }

        return lt;
    }

    private static String regexExecution(String resText) {
        String regexExecution = "execution.*=\"(.*)\"/>";
        Pattern pEx = Pattern.compile(regexExecution);
        Matcher mEx = pEx.matcher(resText);

        String ex;
        for(ex = ""; mEx.find(); ex = ex.substring(0, ex.length() - 3)) {
            ex = mEx.group();
            ex = ex.substring(ex.indexOf("value=") + 7);
        }

        return ex;
    }

    static {
        int socketTimeout = 10000;
        int connectTimeout = 10000;
        int connectionRequestTimeout = 10000;
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }


}

