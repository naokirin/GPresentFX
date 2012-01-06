package main.groovy.gpresentfx.layout

import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

import main.groovy.gpresentfx.GPresentBuilder

/**
 * @author naokirin
 * スライドを生成するファクトリクラス
 * ただしSlideクラスは存在しないので、実際にはVBoxかStackPaneを生成している
 */
class SlideFactory extends AbstractLayoutFactory{

  static final String alignmentKeyword = 'alignment'
  static final String overlapKeyword = 'overlap'

  /** スライドの生成 */
  static Pane newInstance(GPresentBuilder pdb, Map attribute){
    def pane = createSlide(attribute)

    setAlignment(pane, attribute, pdb)
    setSlideBackground(pane, attribute, pdb)
    setSlideWidth(pane, attribute, pdb)
    setSlideHeight(pane, attribute, pdb)
    setPositionX(pane, attribute, pdb)
    setPositionY(pane, attribute, pdb)
    setPadding(pane, attribute, pdb, false)

    return pane
  }

  private static Pane createSlide(Map attribute){
    if(attribute[overlapKeyword] != null)
      return new StackPane()
    else
      return new VBox()
  }

  private static void setAlignment(Pane pane, Map attribute, GPresentBuilder pdb){
    if(attribute[alignmentKeyword] != null)
      pane.setAlignment(TreatingAlignment.stringToAlignment(attribute[alignmentKeyword].toString()))
    else
      pane.setAlignment(pdb.defaultAlignment)
  }

  private static void setSlideBackground(Pane pane, Map attribute, GPresentBuilder pdb){
    if(attribute[backgroundKeyword] != null)
      pane.setStyle("-fx-background-color: ${attribute[backgroundKeyword].toString()};")
    else
      pane.setStyle("-fx-background-color: ${pdb.defaultBackground};")
  }

  private static void setSlideWidth(Pane pane, Map attribute, GPresentBuilder pdb){
    if(attribute[widthKeyword] != null){
      pane.setMaxWidth((double)attribute[widthKeyword])
      pane.setMinWidth((double)attribute[widthKeyword])
    }
    else{
      pane.setMaxWidth(pdb.defaultWidth)
      pane.setMinWidth(pdb.defaultWidth)
    }
  }

  private static void setSlideHeight(Pane pane, Map attribute, GPresentBuilder pdb){
    if(attribute[heightKeyword] != null){
      pane.setMaxHeight((double)attribute[heightKeyword])
      pane.setMinHeight((double)attribute[heightKeyword])
    }
    else{
      pane.setMaxHeight(pdb.defaultHeight)
      pane.setMinHeight(pdb.defaultHeight)
    }
  }
}
