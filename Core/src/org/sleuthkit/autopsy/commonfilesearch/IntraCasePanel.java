/*
 * 
 * Autopsy Forensic Browser
 * 
 * Copyright 2018 Basis Technology Corp.
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
package org.sleuthkit.autopsy.commonfilesearch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.ComboBoxModel;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 *
 * @author bsweeney
 */
public class IntraCasePanel extends javax.swing.JPanel {
    
    private static final long serialVersionUID = 1L;
    static final long NO_DATA_SOURCE_SELECTED = -1;
        
    private static final Logger LOGGER = Logger.getLogger(CommonAttributePanel.class.getName());
        
    private boolean singleDataSource;
    private String selectedDataSource;
    private ComboBoxModel<String> dataSourcesList = new DataSourceComboBoxModel();
    private Map<Long, String> dataSourceMap;
    
    private String errorMessage;

    /**
     * Creates new form IntraCasePanel
     */
    public IntraCasePanel() {
        initComponents();
        this.errorMessage = "";
        this.dataSourceMap = new HashMap<>();
    }
    
    public boolean isSingleDataSource(){
        return this.singleDataSource;
    }
    
    public String getSelectedDataSource(){
        if(this.singleDataSource && this.selectedDataSource != null){
            return selectedDataSource;            
        } else {
            return "";
        }        
    }
    
    public Map<Long, String> getDataSourceMap(){
        return Collections.unmodifiableMap(this.dataSourceMap);
    }
    
    Long getSelectedDataSourceId(){
        for(Entry<Long, String> entry : this.dataSourceMap.entrySet()){
            if(entry.getValue().equals(this.selectDataSourceComboBox.getSelectedItem())){
                return entry.getKey();
            }
        }
        
        return IntraCasePanel.NO_DATA_SOURCE_SELECTED;
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        allDataSourcesRadioButton = new javax.swing.JRadioButton();
        withinDataSourceRadioButton = new javax.swing.JRadioButton();
        selectDataSourceComboBox = new javax.swing.JComboBox<>();

        buttonGroup.add(allDataSourcesRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(allDataSourcesRadioButton, org.openide.util.NbBundle.getMessage(IntraCasePanel.class, "IntraCasePanel.allDataSourcesRadioButton.text")); // NOI18N
        allDataSourcesRadioButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        allDataSourcesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allDataSourcesRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(withinDataSourceRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(withinDataSourceRadioButton, org.openide.util.NbBundle.getMessage(IntraCasePanel.class, "IntraCasePanel.withinDataSourceRadioButton.text")); // NOI18N
        withinDataSourceRadioButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        withinDataSourceRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withinDataSourceRadioButtonActionPerformed(evt);
            }
        });

        selectDataSourceComboBox.setModel(dataSourcesList);
        selectDataSourceComboBox.setEnabled(false);
        selectDataSourceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDataSourceComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(allDataSourcesRadioButton)
                            .addComponent(withinDataSourceRadioButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(selectDataSourceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(allDataSourcesRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(withinDataSourceRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectDataSourceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void allDataSourcesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allDataSourcesRadioButtonActionPerformed
        selectDataSourceComboBox.setEnabled(!allDataSourcesRadioButton.isSelected());
        singleDataSource = false;
    }//GEN-LAST:event_allDataSourcesRadioButtonActionPerformed

    private void withinDataSourceRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withinDataSourceRadioButtonActionPerformed
        withinDataSourceSelected(withinDataSourceRadioButton.isSelected());
    }//GEN-LAST:event_withinDataSourceRadioButtonActionPerformed

    private void selectDataSourceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDataSourceComboBoxActionPerformed
        final Object selectedItem = selectDataSourceComboBox.getSelectedItem();
        if (selectedItem != null) {
            selectedDataSource = selectedItem.toString();
        } else {
            selectedDataSource = "";
        }
    }//GEN-LAST:event_selectDataSourceComboBoxActionPerformed

    private void withinDataSourceSelected(boolean selected) {
        selectDataSourceComboBox.setEnabled(selected);
        if (selectDataSourceComboBox.isEnabled()) {
            selectDataSourceComboBox.setSelectedIndex(0);
            singleDataSource = true;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allDataSourcesRadioButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JComboBox<String> selectDataSourceComboBox;
    private javax.swing.JRadioButton withinDataSourceRadioButton;
    // End of variables declaration//GEN-END:variables

    void setDataModel(DataSourceComboBoxModel dataSourceComboBoxModel) {
        this.dataSourcesList = dataSourceComboBoxModel;
        this.selectDataSourceComboBox.setModel(dataSourcesList);
    }

    void rigForMultipleDataSources(boolean multipleDataSources) {
        this.withinDataSourceRadioButton.setEnabled(multipleDataSources);
        this.allDataSourcesRadioButton.setSelected(!multipleDataSources);
        this.withinDataSourceRadioButton.setSelected(multipleDataSources);
        this.withinDataSourceSelected(multipleDataSources);
        
    }

    void setDataSourceMap(Map<Long, String> dataSourceMap) {
        this.dataSourceMap.clear();
        this.dataSourceMap.putAll(dataSourceMap);
    }

    @NbBundle.Messages({
        "IntraCasePanel.areSearchCriteriaMet.message=Cannot run intra-case correlation search."
    })
    boolean areSearchCriteriaMet() {
        if(this.dataSourceMap.isEmpty()){
            this.errorMessage = Bundle.IntraCasePanel_areSearchCriteriaMet_message();
            return false;
        } else {
            return true;
        }            
    }

    String getErrorMessage() {
        return this.errorMessage;
    }
}
