package org.group15.tveely.ffmpeg;

import lombok.Builder;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public class FfmpegWrapper {
    public static String POWERSHELL = "powershell.exe";
    public static String BASH = "/bin/bash";
    public static String CMD = "cmd.exe";
    private String terminal;
    private String expected;
    private String scale;
    private final ProcessBuilder pb = new ProcessBuilder();

    public int encode(String path, String extension) throws IOException, InterruptedException {
        String outputDir = "E:\\encoded";
        new File(outputDir).mkdirs();
        pb.environment().put("PATH", "C:\\Program Files (x86)\\ffmpeg\\bin");


        String[] resolutions = {"1920x1080", "1280x720", "854x480"};
        String[] names = {"1080p", "720p", "480p"};
        String[] bitrates = {"5000k", "2800k", "1400k"};
        String[] maxrates = {"5500k", "3000k", "1600k"};
        String[] bufsizes = {"10000k", "6000k", "3000k"};


        List<String> command = new ArrayList<>();
        command.addAll(List.of(
                terminal,
                expected,
                "ffmpeg",
                "-i", path,
                "-filter_complex",
                "[0:v]split=3[v1][v2][v3];" +
                        "[v1]scale=w=1920:h=1080:force_original_aspect_ratio=decrease[v1080];" +
                        "[v2]scale=w=1280:h=720:force_original_aspect_ratio=decrease[v720];" +
                        "[v3]scale=w=854:h=480:force_original_aspect_ratio=decrease[v480]"

        ));

        for (int i = 0; i < 3; i++) {
            String name = names[i];
            String resolutionTag = name.replace("p", "");
            String streamPath = outputDir + "\\" + name;
            new File(streamPath).mkdirs();

            command.addAll(List.of(
                    "-map", "[v" + resolutionTag + "]",
                    "-map", "0:a",
                    "-c:v", "h264_nvenc",
                    "-b:v", bitrates[i],
                    "-maxrate", maxrates[i],
                    "-bufsize", bufsizes[i],
                    "-c:a", "aac",
                    "-b:a", "128k",
                    "-f", "hls",
                    "-hls_time", "20",
                    "-hls_playlist_type", "vod",
                    "-hls_flags", "independent_segments",
                    "-hls_segment_filename", streamPath + "\\segment_%03d.ts",
                    streamPath + "\\stream.m3u8"
            ));
        }

        pb.command(command);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("logs/ffmpeg_output.txt")));
        pb.redirectErrorStream(true);

        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();


        File master = new File(outputDir + "\\master.m3u8");
        try (PrintWriter writer = new PrintWriter(master)) {
            writer.println("#EXTM3U");
            writer.println("#EXT-X-VERSION:3");

            writer.println("#EXT-X-STREAM-INF:BANDWIDTH=5500000,RESOLUTION=1920x1080");
            writer.println("1080p/stream.m3u8");

            writer.println("#EXT-X-STREAM-INF:BANDWIDTH=3000000,RESOLUTION=1280x720");
            writer.println("720p/stream.m3u8");

            writer.println("#EXT-X-STREAM-INF:BANDWIDTH=1600000,RESOLUTION=854x480");
            writer.println("480p/stream.m3u8");
        }

        return exitCode;
    }

}
