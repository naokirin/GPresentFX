package main.groovy.gpresentfx.layout

import javafx.scene.layout.BorderPane

import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.layout.VBox
/**
 * @author naokirin
 * BorderPaneのインスタンスを生成するファクトリクラス
 */
class BorderPaneFactory extends AbstractLayoutFactory{
  static  BorderPane newInstance(GPresentBuilder pdb, Map attribute){
    def pane = new BorderPane()

    setBackground(pane, attribute, pdb)
    setWidth(pane, attribute, pdb)
    setHeight(pane, attribute, pdb)
    setRotate(pane, attribute, pdb)
    setPositionX(pane, attribute, pdb)
    setPositionY(pane, attribute, pdb)
    setPadding(pane, attribute, pdb, true)

    return pane
  }
}

/**
 * @author naokirin
 * ボーダーペインのレイアウトオブジェクトを生成するファクトリクラス
 */
class BorderLayoutFactory extends AbstractLayoutFactory{

  static final String alignmentKeyword = 'alignment'

    /** ボーダーペインのレイアウトオブジェクトの生成 */
  static VBox newInstance(GPresentBuilder pdb, String pos, Map attribute){

    def pane = new VBox()
    pane.metaClass.borderPos = pos

    setAlignment(pane, attribute, pdb)
    setBackground(pane, attribute, pdb)
    setWidth(pane, attribute, pdb)
    setHeight(pane, attribute, pdb)
    setRotate(pane, attribute, pdb)
    setPositionX(pane, attribute, pdb)
    setPositionY(pane, attribute, pdb)
    setPadding(pane, attribute, pdb, true)

    return pane
  }

  /** アラインメントの設定をする */
  private static void setAlignment(VBox pane, Map attribute, GPresentBuilder pdb){
    if(attribute[alignmentKeyword] != null)
      pane.setAlignment(TreatingAlignment.stringToAlignment(attribute[alignmentKeyword].toString()))
    else
      pane.setAlignment(pdb.defaultAlignment)
  }
}
