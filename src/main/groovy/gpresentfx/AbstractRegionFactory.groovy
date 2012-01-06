package main.groovy.gpresentfx

import javafx.scene.layout.Region

abstract class AbstractRegionFactory extends AbstractNodeFactory{

  static final String widthKeyword = 'width'
  static final String heightKeyword = 'height'

  protected static void setWidth(Region node, Map attribute, GPresentBuilder pdb){
    if(attribute[widthKeyword] != null){
      node.setMaxWidth((double)attribute[widthKeyword])
      node.setMinWidth((double)attribute[widthKeyword])
    }
  }

  protected static void setHeight(Region node, Map attribute, GPresentBuilder pdb){
    if(attribute[heightKeyword] != null){
      node.setMaxHeight((double)attribute[heightKeyword])
      node.setMinHeight((double)attribute[heightKeyword])
    }
  }

}
