package org.group15.tveely.dao;

import org.group15.tveely.models.VideoAdapter;

import java.util.List;

public interface VideoDao {

     List<VideoAdapter> findVideoByStatus(String status);

     void updateStatusById(String id, String status);
}
