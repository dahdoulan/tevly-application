package org.group15.tveely.ffmpeg;

import lombok.Builder;
import org.group15.tveely.ffmpeg.models.Bitrate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Builder
public class FfmpegWrapper {

    public static String POWERSHELL = "powershell.exe";
    public static String BASH = "/bin/bash";
    public static String CMD = "cmd.exe";
    private String terminal;
    private String expected;
    private String scale;


    public int encode(String path) throws IOException, InterruptedException {
        String outputDir = "E:\\encoded";
        new File(outputDir).mkdirs();


        String baseName = new File(path).getName().replaceFirst("[.][^.]+$", "");
        String videoOutputDir = outputDir + File.separator + baseName;
        new File(videoOutputDir).mkdirs();

        List<String> resolutions = List.of("1080p", "720p", "480p");

        for (String resolution : resolutions) {
            ProcessBuilder pb = createCommand(resolution, videoOutputDir, path);

            Process process = pb.start();
            logProcess(process);
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Encoding failed for: " + resolution);
                return exitCode;
            }
        }

        createMasterPlaylist(videoOutputDir);
        return 0;
    }


    private void createMasterPlaylist(String videoOutputDir) throws IOException {
        File master = new File(videoOutputDir + File.separator + "master.m3u8");
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
    }

    private ProcessBuilder createCommand(String resolution, String directory, String inputPath) {
        String streamPath = directory + File.separator + resolution;
        new File(streamPath).mkdirs();

        Bitrate bitrate = Bitrate.bitrates.get(resolution);
        String width = "", height = "";

        switch (resolution) {
            case "1080p" -> { width = "1920"; height = "1080"; }
            case "720p" -> { width = "1280"; height = "720"; }
            case "480p" -> { width = "854"; height = "480"; }
        }

        List<String> command = new ArrayList<>(List.of(
                "ffmpeg",
                "-i", inputPath,
                "-vf", "scale=w=" + width + ":h=" + height + ":force_original_aspect_ratio=decrease",
                "-c:v", "h264_nvenc",
                "-b:v", bitrate.getBitrate(),
                "-maxrate", bitrate.getMaxRate(),
                "-bufsize", bitrate.getBufferSize(),
                "-c:a", "aac",
                "-b:a", "128k",
                "-f", "hls",
                "-hls_time", "20",
                "-hls_playlist_type", "vod",
                "-hls_flags", "independent_segments",
                "-hls_segment_filename", streamPath + File.separator + "segment_%03d.ts",

                streamPath + File.separator + "stream.m3u8"
        ));

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("logs/ffmpeg_output.txt")));
        pb.redirectErrorStream(true);
        return pb;
    }

    private void logProcess(Process process) throws IOException {
        Logger logger = Logger.getLogger(FfmpegWrapper.class.getName());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        }
    }
}
