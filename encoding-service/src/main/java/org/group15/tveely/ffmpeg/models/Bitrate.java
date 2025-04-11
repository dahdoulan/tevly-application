package org.group15.tveely.ffmpeg.models;

import lombok.RequiredArgsConstructor;

import java.util.Map;

public class Bitrate {
    public static Map<String, Bitrate> bitrates = Map.of(
            "1080p", new Bitrate("5000k", "5500k", "10000k"),
            "720p", new Bitrate("2800k", "3000k", "6000k"),
            "480p", new Bitrate("1400k", "1600k", "3000k")
    );

    private String bitrate;
    private String maxRate;
    private String bufferSize;

    public Bitrate(String bitrate, String maxRate, String bufferSize) {
        this.bitrate = bitrate;
        this.maxRate = maxRate;
        this.bufferSize = bufferSize;
    }

    public Bitrate getBitrate(String bitrate) {
        return bitrates.getOrDefault(bitrate, bitrates.get("720p"));
    }

    public void addBitrate(String resolution, Bitrate bitrate) {
        bitrates.put(resolution, bitrate);
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
    }

    public String getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(String bufferSize) {
        this.bufferSize = bufferSize;
    }
}
