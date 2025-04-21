package org.group15.tveely.DTOs;

import java.sql.Blob;

public interface ThumbnailProjection  {


    Blob getThumbnail();  // Directly map to the Blob

}
