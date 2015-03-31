package com.swayam.demo.rmi.api.shared;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpIOStreamProvider implements IOStreamProvider {

    private static final Logger LOG = LoggerFactory.getLogger(HttpIOStreamProvider.class);

    private final URLConnection urlConnection;

    public HttpIOStreamProvider(String host, int port) {
        urlConnection = getUrlConnection(host, port);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return urlConnection.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return urlConnection.getOutputStream();
    }

    private URLConnection getUrlConnection(String host, int port) {

        String url = "http://" + host + ":" + port + "/?count=-71";

        LOG.info("trying to connect to {}", url);

        URL httpUrl;
        try {
            httpUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        URLConnection urlConnection;
        try {
            urlConnection = httpUrl.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        try {
            urlConnection.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return urlConnection;
    }

}
