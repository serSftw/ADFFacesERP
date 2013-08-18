package com.faceserp.common.view.backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.controller.binding.TaskFlowBindingAttributes;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.event.ItemEvent;

public class ErpTabs {
    
    private List<TaskFlowBindingAttributes> mTaskFlowBindingAttrs = new ArrayList<TaskFlowBindingAttributes>(5);    
    private RichPanelTabbed panelTabbed;
    
    public ErpTabs() {
        TaskFlowBindingAttributes tfAttr = new TaskFlowBindingAttributes();
        tfAttr.setId("login");
        tfAttr.setTaskFlowId(new TaskFlowId("/WEB-INF/flows/login-tf.xml", "login-tf"));
        mTaskFlowBindingAttrs.add(tfAttr);    
    }
    
    public List<TaskFlowBindingAttributes> getTaskFlowList() {
        return mTaskFlowBindingAttrs;
    }

    public void handleCloseTabItem(ItemEvent itemEvent) {
        if (itemEvent.getType().equals(ItemEvent.Type.remove)) {
            Object item = itemEvent.getSource();
            if (item instanceof RichShowDetailItem) {
                RichShowDetailItem tabItem = (RichShowDetailItem)item;
                String tfName = (String)tabItem.getAttributes().get("tfName");
                
                for (int i = 0; i < mTaskFlowBindingAttrs.size(); i++) {
                    TaskFlowBindingAttributes tf = mTaskFlowBindingAttrs.get(i);
                    if (tf.getId().equals(tfName)) {
                        mTaskFlowBindingAttrs.remove(tf);
                        break;
                    }
                }
            }
        }
    }

    public void regionOneLaunch(ActionEvent actionEvent) {
        int index = this.tfExist("login");
        if (index < 0) {
            TaskFlowBindingAttributes tfAttr = new TaskFlowBindingAttributes();
            tfAttr.setId("Login");
            tfAttr.setTaskFlowId(new TaskFlowId("/WEB-INF/flows/login-tf.xml", "login-tf"));
            mTaskFlowBindingAttrs.add(0, tfAttr);
            if (this.getPanelTabbed().getChildCount() > 0) {            
                this.makeSelected(0);   
            }
        } else {
            this.makeSelected(index);
        }
    }

//    public void regionTwoLaunch(ActionEvent actionEvent) {
//        int index = this.tfExist("Departments");
//        if (index < 0) {
//            TaskFlowBindingAttributes tfAttr = new TaskFlowBindingAttributes();
//            tfAttr.setId("Departments");
//            tfAttr.setTaskFlowId(new TaskFlowId("/WEB-INF/flows/deps-flow.xml", "deps-flow"));
//            mTaskFlowBindingAttrs.add(0, tfAttr);
//            if (this.getPanelTabbed().getChildCount() > 0) {            
//                this.makeSelected(0);   
//            }
//        } else {
//            this.makeSelected(index);
//        }
//    }
    
    private int tfExist(String tfName) {
        for (int i = 0; i < mTaskFlowBindingAttrs.size(); i++) {
            TaskFlowBindingAttributes tf = mTaskFlowBindingAttrs.get(i);
            if (tf.getId().equals(tfName)) {
                return i;
            }
        }     
        return -1;
    }
    
    private void makeSelected(int index) {
        List<UIComponent> tabs = this.getPanelTabbed().getChildren();
        for (int i = 0; i < tabs.size(); i++) {
            RichShowDetailItem tab = (RichShowDetailItem)tabs.get(i);
            tab.setDisclosed(false);
        }           
        RichShowDetailItem tab = (RichShowDetailItem)tabs.get(index);
        tab.setDisclosed(true);
    }

    public void setPanelTabbed(RichPanelTabbed panelTabbed) {
        this.panelTabbed = panelTabbed;
    }

    public RichPanelTabbed getPanelTabbed() {
        return panelTabbed;
    }    
}
