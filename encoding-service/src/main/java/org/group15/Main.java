package org.group15;

import org.group15.tveely.ffmpeg.FfmpegWrapper;

import java.io.IOException;

import static org.group15.tveely.ffmpeg.FfmpegWrapper.CMD;
import static org.group15.tveely.ffmpeg.FfmpegWrapper.POWERSHELL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
         long startTime = System.nanoTime();  // Start timing
        FfmpegWrapper build = FfmpegWrapper.builder()
                .scale("1920:1080")
                .terminal(CMD)
                .expected("/c")
                .build();

        int exitCode = build.encode("\"C:\\Users\\otoum\\Videos\\NVIDIA\\Desktop\\desk.mp4\"");

        long endTime = System.nanoTime();    // End timing

        long durationInSecs = (endTime - startTime) / 1_000_000_000; // Convert to seconds
        System.out.println("Encoding took " + durationInSecs + " seconds");

        System.out.println(exitCode);
    }
}

