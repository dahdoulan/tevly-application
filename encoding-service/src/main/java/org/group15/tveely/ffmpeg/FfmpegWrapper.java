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
    private final ProcessBuilder pb = new ProcessBuilder();

    public int encode(String path) throws IOException, InterruptedException {
        String outputDir = "C:\\encoded";
        new File(outputDir).mkdirs();
        pb.environment().put("PATH", "C:\\Users\\DELL\\ffmpeg\\bin");

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
        String baseName = new File(path).getName().replaceFirst("[.][^.]+$", "");
        String videoOutputDir = outputDir + File.separator + baseName;

        createCommand("1080p", videoOutputDir);
        createCommand("720p", videoOutputDir);
        createCommand("480p", videoOutputDir);

        Process process = startProcess();
        logProcess(process);
        int exitCode = process.waitFor();


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

        return exitCode;
    }

    private void logProcess(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Logger logger = Logger.getLogger(FfmpegWrapper.class.getName());
                logger.info(line);
            }
        }
    }

    private Process startProcess() throws IOException {
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("logs/ffmpeg_output.txt")));
        pb.redirectErrorStream(true);
        Process process = pb.start();
        return process;
    }

    private void createCommand(String resolution, String directory){
        String name = resolution;
        String streamPath = directory + File.separator + name;
        new File(streamPath).mkdirs();
        Bitrate bitrate = Bitrate.bitrates.get(resolution);

        String resolutionTag = name.replace("p", "");
        List<String> command = new ArrayList<>();
        command.addAll(List.of(
                "-map", "[v" + resolutionTag + "]",
                "-map", "0:a",
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
        pb.command(command);
    }
}
