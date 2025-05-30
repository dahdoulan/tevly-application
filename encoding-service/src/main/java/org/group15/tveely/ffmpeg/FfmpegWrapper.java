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
    private String outputDir;

    public int encode(String path) throws Exception {
        String outputDirectory = createOutputDirectory(path);
        List<String> resolutions = List.of("1080p", "720p" );
        for (String resolution : resolutions) {
            int exitCode = processCommand(resolution, outputDirectory, path);
            if (exitCode != 0) {
                throw new IllegalStateException("Error while processing video.");
            }
        }
        return 0;
    }

    private String createOutputDirectory(String path) {
        new File(outputDir).mkdirs();
        String baseName = new File(path).getName().replaceFirst("[.][^.]+$", "");
        String videoOutputDir = outputDir + File.separator + baseName;
        new File(videoOutputDir).mkdirs();
        return videoOutputDir;
    }

    private int processCommand(String resolution, String directory, String inputPath) throws InterruptedException, IOException {
        String streamPath = directory + File.separator + resolution;
        new File(streamPath).mkdirs();
        Bitrate bitrate = Bitrate.bitrates.get(resolution);
        List<String> command = createCommandTemplate(inputPath, bitrate, streamPath, resolution);
        Process process = startProcess(command);
        logProcess(process);
        return process.waitFor();
    }

    private static Process startProcess(List<String> command) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("logs/ffmpeg_output.txt")));
        pb.redirectErrorStream(true);
        Process process = pb.start();
        return process;
    }

    private static ArrayList<String> createCommandTemplate(String inputPath, Bitrate bitrate, String streamPath, String resolution) {
        String height = "", width = "";
        switch (resolution) {
            case "1080p" -> {
                width = "1920";
                height = "1080";
            }
            case "720p" -> {
                width = "1280";
                height = "720";
            }
        }
        return new ArrayList<>(List.of(
                "ffmpeg",
                "-i", inputPath,
                "-vf", "scale=w=" + width + ":h=" + height + ":force_original_aspect_ratio=decrease:force_divisible_by=2",
                "-c:v", "libx264",
                "-preset", "fast",
                "-b:v", bitrate.getBitrate(),
                "-maxrate", bitrate.getMaxRate(),
                "-bufsize", bitrate.getBufferSize(),
                "-c:a", "aac",
                "-b:a", "128k",
                "-movflags","+faststart",
                streamPath + File.separator + "stream.mp4"
        ));
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
