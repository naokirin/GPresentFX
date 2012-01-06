package main.groovy.gpresentfx.image

import javafx.scene.image.ImageView
import javafx.scene.image.Image

import main.groovy.gpresentfx.AbstractNodeFactory

class ImageViewFactory extends AbstractNodeFactory{
  static final String imageUrlKeyword = 'url'
  static final String widthKeyword = 'width'
  static final String heightKeyword = 'height'
  static final String imagePreservedRatioKeyword = 'preservedratio'

 /** イメージビューの生成 */
  static ImageView newInstance(pdb, Map attribute){
    def obj = new ImageView()

    setImage(obj, attribute, pdb)
    setFitWidth(obj, attribute, pdb)
    setFitHeight(obj, attribute, pdb)
    setImagePreservedRatio(obj, attribute, pdb)
    setRotate(obj, attribute, pdb)
    setPositionX(obj, attribute, pdb)
    setPositionY(obj, attribute, pdb)

    return obj
  }

  private static void setImage(ImageView obj, Map attribute, pdb){
    obj.setImage(new Image(attribute[imageUrlKeyword].toString()))
  }

  private static void setFitWidth(ImageView obj, Map attribute, pdb){
    if(attribute[widthKeyword] != null)
      obj.setFitWidth((double)attribute[widthKeyword])
  }

  private static void setFitHeight(ImageView obj, Map attribute, pdb){
    if(attribute[heightKeyword] != null)
      obj.setFitHeight((double)attribute[heightKeyword])
  }

  private static void setImagePreservedRatio(ImageView obj, Map attribute, pdb){
    if(attribute[imagePreservedRatioKeyword] != null)
      obj.setPreserveRatio((boolean)attribute[imagePreservedRatioKeyword])
    else
      obj.setPreserveRatio(true)
  }
}
