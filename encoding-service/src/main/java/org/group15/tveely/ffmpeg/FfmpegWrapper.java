package org.group15.tveely.ffmpeg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class FfmpegWrapper {
    public static String POWERSHELL = "powershell.exe";
    public static String BASH = "/bin/bash";
    public static String CMD = "cmd.exe";
    private String terminal;
    private String expected;

    private final ProcessBuilder pb = new ProcessBuilder();

    public FfmpegWrapper(String terminal) {
        pb.environment().put("PATH", "C:\\Program Files (x86)\\ffmpeg\\bin");

          if (terminal.equals(POWERSHELL)) {
              this.terminal = POWERSHELL;
              this.expected = "-Command";
          } else if (terminal.equals(BASH)) {
              this.terminal = BASH;
              this.expected = "-c";
          }else{
              this.terminal = CMD;
              this.expected = "/c";
          }
    }

    public int encode(String path, String extension, String resolution) throws IOException, InterruptedException {
        String outputFile = LocalDateTime.now()
                .toString()
                .replace(":", "-")
                .concat("." + extension);

        String scale;
        switch (resolution) {
            case "1440p":
                scale = "2560:1440";
                break;
            case "1080p":
                scale = "1920:1080";
                break;
            case "720p":
                scale = "1280:720";
                break;
            case "480p":
                scale = "854:480";
                break;
            default:
                throw new IllegalArgumentException("Unsupported resolution: " + resolution);
        }

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
                "-hls_time", "4",
                "-hls_playlist_type", "vod",
                "-movflags", "+faststart",
                "-threads", "0",
                outputFile
        );

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

}
