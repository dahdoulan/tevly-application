package org.group15.tveely.DTOs;

import java.sql.Blob;

public interface ThumbnailProjection  {


    byte[] getThumbnail();  // Directly map to the Blob

}
