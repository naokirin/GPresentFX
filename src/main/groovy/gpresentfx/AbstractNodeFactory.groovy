package main.groovy.gpresentfx

import javafx.scene.Node

/**
 * @author naokirin
 */
class AbstractNodeFactory {
  static final String positionXKeyword = 'posx'
  static final String positionYKeyword = 'posy'
  static final String rotateKeyword = 'rotate'

  protected static void setPositionX(Node node, Map attribute, GPresentBuilder pdb){
    if(attribute[positionXKeyword] != null)
      node.setTranslateX((double) attribute[positionXKeyword])
  }

  protected static void setPositionY(Node node, Map attribute, GPresentBuilder pdb){
    if(attribute[positionYKeyword] != null)
      node.setTranslateY((double) attribute[positionYKeyword])
  }

  protected static void setRotate(Node node, Map attribute, GPresentBuilder pdb){
    if(attribute[rotateKeyword] != null)
      node.setRotate((double)attribute[rotateKeyword])
  }
}
