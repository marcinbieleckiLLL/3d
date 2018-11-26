package com.greencrane.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface SendingUtils {
    String post(Map<String, String> params, String url) throws IOException;
}

@Service
class SendingUtilsImpl implements SendingUtils {

    private CloseableHttpClient httpClient;

    public SendingUtilsImpl() {
        this.httpClient = HttpClients.createDefault();
    }

    @Override
    public String post(Map<String, String> params, String url) throws IOException {
        return new PostRequestHelper().send(params, url, httpClient);
    }

    private static class PostRequestHelper {
        public String send(Map<String, String> params, String url, CloseableHttpClient httpClient) throws IOException {
            HttpPost httpPost = createHttpPost(params, url);
            String response = executeAndGetStringResponse(httpClient, httpPost);
            closeConnection(httpClient);
            return response;
        }

        private HttpPost createHttpPost(Map<String, String> params, String url) throws UnsupportedEncodingException {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(createParams(params)));
            return httpPost;
        }

        private List<NameValuePair> createParams(Map<String, String> params) {
            return params.entrySet().stream().map(x -> new BasicNameValuePair(x.getKey(), x.getValue())).collect(Collectors.toList());
        }

        private String executeAndGetStringResponse(CloseableHttpClient httpClient, HttpPost httpPost) throws IOException {
            HttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        }

        private void closeConnection(CloseableHttpClient httpClient) throws IOException {
            httpClient.close();
        }
    }
}