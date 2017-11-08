/*
 * The code is licensed under the LGPL Version 3 license http://www.gnu.org/licenses/lgpl-3.0.en.html.
 * The collected imagery is protected & available under the CC BY-SA version 4 International license.
 * https://creativecommons.org/licenses/by-sa/4.0/legalcode.
 *
 * Copyright (c)2017, Telenav, Inc. All Rights Reserved
 */
package org.openstreetmap.josm.plugins.openstreetcam;

import java.io.IOException;
import org.openstreetmap.josm.gui.PleaseWaitRunnable;
import org.openstreetmap.josm.gui.progress.swing.PleaseWaitProgressMonitor;
import org.openstreetmap.josm.io.OsmTransferException;
import org.openstreetmap.josm.plugins.openstreetcam.entity.DataSet;
import org.openstreetmap.josm.plugins.openstreetcam.entity.PhotoDataSet;
import org.openstreetmap.josm.plugins.openstreetcam.util.cnf.GuiConfig;
import org.xml.sax.SAXException;


/**
 * Downloads the next or previous set of photo location results.
 *
 * @author beataj
 * @version $Revision$
 */
public class DownloadPhotosTask extends PleaseWaitRunnable {

    private static final int SLEEP = 1000;

    /** handles data update operations */
    private final DataUpdateHandler dataUpdateHandler;

    /** the currently running download thread */
    private Thread downloadThread;

    /** Flag indicated that user ask for cancel this task */
    private boolean canceled;

    /** flag indicating if the next or previous result set is loaded */
    private final boolean loadNextResults;

    /** the downloaded photo data set */
    private PhotoDataSet photoDataSet;


    /**
     * Builds a new task.
     *
     * @param taskTitle the title to be displayed while downloading the new set of photos
     * @param loadNextResults if true then the next result set is loaded; if false then the previous result set is
     * loaded
     */
    public DownloadPhotosTask(final String taskTitle, final boolean loadNextResults) {
        super(taskTitle, new PleaseWaitProgressMonitor(taskTitle), false);
        dataUpdateHandler = new DataUpdateHandler();
        this.loadNextResults = loadNextResults;
    }

    @Override
    protected void cancel() {
        synchronized (this) {
            if (downloadThread != null) {
                downloadThread.interrupt();
            }
            downloadThread = null;
            canceled = true;
            ((PleaseWaitProgressMonitor) progressMonitor).close();
        }
    }

    @Override
    protected void afterFinish() {
        synchronized (this) {
            if (!canceled && photoDataSet != null && !photoDataSet.getPhotos().isEmpty()) {
                // TODO: take into consideration also detections!
                dataUpdateHandler.updateUI(new DataSet(null, photoDataSet, null), true);
            }
        }
    }

    @Override
    protected void finish() {
        // nothing to add here
    }


    @Override
    protected void realRun() throws SAXException, IOException, OsmTransferException {
        if (!canceled && dataUpdateHandler.photoDataSetDownloadAllowed()) {
            try {
                final String taskTitle = loadNextResults ? GuiConfig.getInstance().getInfoDownloadNextPhotosTitle()
                        : GuiConfig.getInstance().getInfoDownloadPreviousPhotosTitle();
                this.progressMonitor.indeterminateSubTask(taskTitle);
                downloadThread = new Thread(() -> photoDataSet = dataUpdateHandler.downloadPhotos(loadNextResults));
                downloadThread.start();
                waitForCompletion();
            } finally {
                progressMonitor.finishTask();
            }
        }
    }

    private void waitForCompletion() {
        while (downloadThread != null && downloadThread.isAlive()) {
            try {
                Thread.sleep(SLEEP);
            } catch (final InterruptedException e) {
                // no need to handle this; if the user cancels the action, exception will occur
            }
        }
    }
}