package org.group15.tveely.ffmpeg;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
public class FfmpegWrapper {

    private final Map<String, String> resolutions = Map.of(
            "1440p",
            "2560:1440",
            "1080p",
            "1920:1080",
            "720p",
            "1280:720",
            "480p",
            "854:480"
    );

    public static String POWERSHELL = "powershell.exe";
    public static String BASH = "/bin/bash";
    public static String CMD = "cmd.exe";

    private String terminal = BASH;
    private String expected = "/c";

    private final ProcessBuilder pb;

    public int encode(String path, String extension, String resolution) throws IOException, InterruptedException {
        String scale = resolveScale(resolution);
        createCommand(path, extension, scale);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        return process.waitFor();
    }

    private void createCommand(String path, String extension, String scale) {
        pb.command(
                terminal,
                expected,
                "ffmpeg",
                "-i", path,
                "-c:v", "h264_nvenc",
                "-vf", "scale=" + scale,
                "-preset", "fast",
                "-g", "48",
                "-sc_threshold", "0",
                "-map", "0:v:0",
                "-map", "0:a:0",
                "-c:v", "libx264",
                "-b:v", "3000k",
                "-c:a", "aac",
                "-b:a", "128k",
                "-f", "hls",
                "-hls_time", "20",
                "-hls_playlist_type", "vod",
                "-movflags", "+faststart",
                "-threads", "0",
                "-vf", "yuv422p",
                generateFileName(extension)
        );
    }

    private String resolveScale(String resolution) {
        return resolutions.getOrDefault(resolution, resolutions.get("720"));
    }

    private String generateFileName(String extension) {
        return LocalDateTime.now()
                .toString()
                .replace(":", "-")
                .concat("." + extension);
    }
}
