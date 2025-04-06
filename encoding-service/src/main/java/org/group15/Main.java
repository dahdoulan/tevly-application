package org.group15;

import org.group15.tveely.ffmpeg.FfmpegWrapper;

import java.io.IOException;

import static org.group15.tveely.ffmpeg.FfmpegWrapper.CMD;
import static org.group15.tveely.ffmpeg.FfmpegWrapper.POWERSHELL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FfmpegWrapper ffmpeg = new FfmpegWrapper(POWERSHELL);
        long startTime = System.nanoTime();  // Start timing

        int exitCode = ffmpeg.encode("\"C:\\Users\\otoum\\Downloads\\login.mp4\"", "mp4", "1080p");

        long endTime = System.nanoTime();    // End timing

        long durationInMillis = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Encoding took " + durationInMillis + " ms");

        System.out.println(exitCode);
    }
}
