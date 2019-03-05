/*
 * Autopsy Forensic Browser
 *
 * Copyright 2019-2019 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.experimental.autoingest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.Mode;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.casemodule.multiusercasesbrowser.MultiUserCasesBrowserPanel;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 * A top component that provides an adminstrative dashboard for multi-user
 * cases.
 */
@TopComponent.Description(
        preferredID = "CasesDashboardTopComponent",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "dashboard",
        openAtStartup = false
)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_CasesDashboardTopComponent",
        preferredID = "CasesDashboardTopComponent"
)
@Messages({
    "CTL_CasesDashboardAction=Multi-User Cases Dashboard",
    "CTL_CasesDashboardTopComponent=Cases",
    "HINT_CasesDashboardTopComponent=This is an adminstrative dashboard for multi-user cases"
})
@SuppressWarnings("PMD.SingularField") // Prevent warnings about generated code
public final class CasesDashboardTopComponent extends TopComponent implements ExplorerManager.Provider {

    private static final long serialVersionUID = 1L;
     private static final String TASK_THREAD_NAME = "Case-dashboard-task-%d";   
    private static final Logger logger = Logger.getLogger(CasesDashboardTopComponent.class.getName());
    private final ExplorerManager explorerManager;
    private final MultiUserCasesBrowserPanel caseBrowserPanel;
    private final Executor executor;

    /**
     * Opens a singleton top component that provides an adminstrative dashboard
     * for multi-user cases. The top component is docked into the "dashboard
     * mode" defined by the auto ingest jobs top component.
     */
    // RJCTODO: Consider moving all of the dashboard code into its own 
    // admindashboards or dashboards package.
    public static void openTopComponent() {
        CasesDashboardTopComponent topComponent = (CasesDashboardTopComponent) WindowManager.getDefault().findTopComponent("CasesDashboardTopComponent"); // NON-NLS
        if (topComponent == null) {
            logger.log(Level.SEVERE, "Failed to find CasesDashboardTopComponent"); // NON-NLS
            return;
        }

        Mode mode = WindowManager.getDefault().findMode("dashboard"); // NON-NLS
        if (mode == null) {
            logger.log(Level.SEVERE, "Failed to find dashboard for CasesDashboardTopComponent, will not display"); // NON-NLS
            return;
        }

        if (!topComponent.isOpened()) {
            mode.dockInto(topComponent);
            topComponent.open();
        }
        topComponent.toFront();
        topComponent.requestActive();
    }

    /**
     * Constructs a singleton top component that provides an adminstrative
     * dashboard for multi-user cases. The top component is docked into the
     * "dashboard mode" defined by the auto ingest jobs top component.
     */
    public CasesDashboardTopComponent() {
        initComponents();
        setName(Bundle.CTL_CasesDashboardTopComponent());
        setToolTipText(Bundle.HINT_CasesDashboardTopComponent());
        explorerManager = new ExplorerManager();
        associateLookup(ExplorerUtils.createLookup(explorerManager, getActionMap()));
        caseBrowserPanel = new MultiUserCasesBrowserPanel(explorerManager, new CasesDashboardCustomizer());
        caseBrowserScrollPane.add(caseBrowserPanel);
        caseBrowserScrollPane.setViewportView(caseBrowserPanel);
        executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat(TASK_THREAD_NAME).build()); // RJCTODO: Need shutdown
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    @Override
    protected void componentOpened() {
        caseBrowserPanel.displayCases();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        refreshButton = new javax.swing.JButton();
        caseBrowserScrollPane = new javax.swing.JScrollPane();

        org.openide.awt.Mnemonics.setLocalizedText(refreshButton, org.openide.util.NbBundle.getMessage(CasesDashboardTopComponent.class, "CasesDashboardTopComponent.refreshButton.text")); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addGap(0, 458, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(caseBrowserScrollPane)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(caseBrowserScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(refreshButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        caseBrowserPanel.displayCases();
    }//GEN-LAST:event_refreshButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane caseBrowserScrollPane;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables

}
