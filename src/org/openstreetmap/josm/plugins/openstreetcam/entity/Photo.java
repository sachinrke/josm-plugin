/*
 * The code is licensed under the LGPL Version 3 license http://www.gnu.org/licenses/lgpl-3.0.en.html.
 *
 * The collected imagery is protected & available under the CC BY-SA version 4 International license.
 * https://creativecommons.org/licenses/by-sa/4.0/legalcode.
 *
 * Copyright (c)2017, Telenav, Inc. All Rights Reserved
 */
package org.openstreetmap.josm.plugins.openstreetcam.entity;

import java.util.List;
import org.openstreetmap.josm.data.coor.LatLon;
import com.telenav.josm.common.entity.EntityUtil;


/**
 * Defines the photo business entity. The field names originated from the OSC API and should be kept in order
 * to avoid transformation between notions.
 *
 * @author Beata
 * @version $Revision$
 */
public class Photo {

    private final Long id;
    private final Long sequenceId;
    private final Integer sequenceIndex;
    private final LatLon point;
    private final String name;
    private final String largeThumbnailName;
    private final String thumbnailName;
    private final String oriName;
    private final Long timestamp;
    private final Double heading;
    private Double gpsAccuracy;
    private String username;
    private Long wayId;
    private String shotDate;
    private List<Detection> detections;


    Photo(final PhotoBuilder builder) {
        this.id = builder.getId();
        this.sequenceId = builder.getSequenceId();
        this.sequenceIndex = builder.getSequenceIndex();
        this.point = builder.getPoint();
        this.name = builder.getName();
        this.largeThumbnailName = builder.getLargeThumbnailName();
        this.thumbnailName = builder.getThumbnailName();
        this.oriName = builder.getOriName();
        this.timestamp = builder.getTimestamp();
        this.heading = builder.getHeading();
        this.username = builder.getUsername();
        this.wayId = builder.getWayId();
        this.shotDate = builder.getShotDate();
        this.detections = builder.getDetections();
    }

    public Long getId() {
        return id;
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public Integer getSequenceIndex() {
        return sequenceIndex;
    }

    public LatLon getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }

    public String getLargeThumbnailName() {
        return largeThumbnailName;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getHeading() {
        return heading;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Long getWayId() {
        return wayId;
    }

    public void setWayId(final Long wayId) {
        this.wayId = wayId;
    }

    public String getShotDate() {
        return shotDate;
    }

    public void setShotDate(final String shotDate) {
        this.shotDate = shotDate;
    }

    public void setDetections(final List<Detection> detections) {
        this.detections = detections;
    }

    public List<Detection> getDetections() {
        return detections;
    }

    public String getOriName() {
        return oriName;
    }

    public Double getGpsAccuracy() {
        return gpsAccuracy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + EntityUtil.hashCode(id);
        result = prime * result + EntityUtil.hashCode(sequenceId);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj != null && obj.getClass() == this.getClass()) {
            final Photo other = (Photo) obj;
            result = EntityUtil.bothNullOrEqual(id, other.getId());
            result = result && EntityUtil.bothNullOrEqual(sequenceId, other.getSequenceId());
        }
        return result;
    }
}