package org.group15;

import org.group15.tveely.ffmpeg.FfmpegWrapper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FfmpegWrapper ffmpeg = new FfmpegWrapper("fhjklsdkjf");
        int exitCode = ffmpeg.encode("C:\\Users\\DELL\\Videos\\2025-03-29 02-06-27.mkv", "mp4");
        System.out.println(exitCode);
    }
}
