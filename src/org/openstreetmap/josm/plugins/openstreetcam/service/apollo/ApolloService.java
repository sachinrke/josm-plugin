/*
 * Copyright 2019 Grabtaxi Holdings PTE LTE (GRAB), All rights reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be found in the LICENSE file.
 *
 */
package org.openstreetmap.josm.plugins.openstreetcam.service.apollo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openstreetmap.josm.data.coor.LatLon;
import org.openstreetmap.josm.plugins.openstreetcam.entity.Cluster;
import org.openstreetmap.josm.plugins.openstreetcam.entity.ClusterConfidenceLevel;
import org.openstreetmap.josm.plugins.openstreetcam.entity.Contribution;
import org.openstreetmap.josm.plugins.openstreetcam.entity.Detection;
import org.openstreetmap.josm.plugins.openstreetcam.entity.EditStatus;
import org.openstreetmap.josm.plugins.openstreetcam.entity.Photo;
import org.openstreetmap.josm.plugins.openstreetcam.entity.Sign;
import org.openstreetmap.josm.plugins.openstreetcam.service.BaseService;
import org.openstreetmap.josm.plugins.openstreetcam.service.ServiceException;
import org.openstreetmap.josm.plugins.openstreetcam.service.apollo.entity.Request;
import org.openstreetmap.josm.plugins.openstreetcam.service.apollo.entity.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grab.josm.common.argument.BoundingBox;

/**
 * Executes the operations of the ApolloService components.
 *
 * @author beataj
 * @version $Revision$
 */
public class ApolloService extends BaseService {

	private static final double AREA_EXTEND = 0.004;

	@Override
	public Gson createGson() {
		final GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EditStatus.class, new EditStatusTypeAdapter());
		builder.registerTypeAdapter(LatLon.class, new LatLonDeserializer());
		builder.registerTypeAdapter(ClusterConfidenceLevel.class, new ClusterConfidenceLevelDeserializer());
		return builder.create();
	}

	public List<Detection> searchDetections(final BoundingBox area, final Date date, final Long osmUserId,
			final DetectionFilter detectionFilter) throws ServiceException {
		final String url = new HttpQueryBuilder().buildSearchDetectionsQuery(area, date, osmUserId, detectionFilter);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getDetections() != null ? response.getDetections() : new ArrayList<>();
	}

	public List<Cluster> searchClusters(final BoundingBox area, final Date date, final DetectionFilter detectionFilter)
			throws ServiceException {
		final BoundingBox extendedArea = new BoundingBox(area.getNorth() + AREA_EXTEND, area.getSouth() - AREA_EXTEND,
				area.getEast() + AREA_EXTEND, area.getWest() - AREA_EXTEND);
		final String url = new HttpQueryBuilder().buildSearchClustersQuery(extendedArea, date, detectionFilter);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getClusters() != null ? response.getClusters() : new ArrayList<>();
	}

	public void updateDetection(final Detection detection, final Contribution contribution) throws ServiceException {
		final String url = new HttpQueryBuilder().buildCommentQuery();
		final Request request = new Request(detection, contribution);
		final String content = buildRequest(request, Request.class);
		final Response root = executePost(url, content, Response.class);
		verifyResponseStatus(root);
	}

	public List<Detection> retrieveSequenceDetections(final Long sequenceId) throws ServiceException {
		final String url = new HttpQueryBuilder().buildRetrieveSequenceDetectionsQuery(sequenceId);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getDetections();
	}

	public List<Detection> retrievePhotoDetections(final Long sequenceId, final Integer sequenceIndex)
			throws ServiceException {
		final String url = new HttpQueryBuilder().buildRetrievePhotoDetectionsQuery(sequenceId, sequenceIndex);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getDetections();
	}

	public Detection retrieveDetection(final Long id) throws ServiceException {
		final String url = new HttpQueryBuilder().buildRetrieveDetectionQuery(id);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getDetection();
	}

	public Cluster retrieveCluster(final Long id) throws ServiceException {
		final String url = new HttpQueryBuilder().buildRetrieveClusterQuery(id);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getCluster();
	}

	public List<Detection> retrieveClusterDetections(final Long id) throws ServiceException {
		final String url = new HttpQueryBuilder().buildRetrieveClusterDetectionsQuery(id);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getDetections();
	}

	public List<Photo> retrieveClusterPhotos(final Long id) throws ServiceException {
		final String url = new HttpQueryBuilder().buildRetrieveClusterPhotosQuery(id);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getPhotos();
	}

	public Photo retrievePhoto(final Long sequenceId, final Integer sequenceIndex) throws ServiceException {
		final String url = new HttpQueryBuilder().buildRetrievePhotoQuery(sequenceId, sequenceIndex);
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getPhoto();
	}

	public List<Sign> listSigns() throws ServiceException {
		final String url = new HttpQueryBuilder().buildListSignsQuery();
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getSigns();
	}

	public List<String> listRegions() throws ServiceException {
		final String url = new HttpQueryBuilder().buildListRegionsQuery();
		final Response response = executeGet(url, Response.class);
		verifyResponseStatus(response);
		return response.getRegions();
	}
}