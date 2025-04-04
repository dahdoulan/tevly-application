package org.group15;

import org.group15.tveely.ffmpeg.FfmpegWrapper;

import java.io.IOException;

import static org.group15.tveely.ffmpeg.FfmpegWrapper.CMD;
import static org.group15.tveely.ffmpeg.FfmpegWrapper.POWERSHELL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FfmpegWrapper ffmpeg = new FfmpegWrapper(POWERSHELL);
        int exitCode = ffmpeg.encode("\"E:\\hi.mp4\"", "mp4");
        System.out.println(exitCode);
    }
}
