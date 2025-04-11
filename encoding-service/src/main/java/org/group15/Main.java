package org.group15;

import org.group15.tveely.ffmpeg.FfmpegWrapper;

import java.io.IOException;

import static org.group15.tveely.ffmpeg.FfmpegWrapper.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FfmpegWrapper build = FfmpegWrapper.builder()
                .scale("1920:1080")
                .terminal(BASH)
                .expected("-c")
                .outputDir("C:\\encoded")
                .build();

        try{
            int exitCode = build.encode("\"C:\\Users\\DELL\\Videos\\el-negro.mp4\"");
            System.out.println(exitCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.nanoTime();    // End timing
        long startTime = System.nanoTime();
        long durationInSecs = (endTime - startTime) / 1_000_000_000; // Convert to seconds
        System.out.println("Encoding took " + durationInSecs + " seconds");

    }
}

