package main.groovy.gpresentfx.layout

import javafx.scene.layout.Pane

import main.groovy.gpresentfx.GPresentBuilder
import javafx.geometry.Pos

/**
 * @author naokirin
 * ボックスレイアウトを生成するファクトリクラス
 */
class BoxLayoutFactory extends AbstractLayoutFactory{

  static final String alignmentKeyword = 'alignment'

  /** ボックスレイアウトの生成 */
  static Pane newInstance(GPresentBuilder pdb, Pane pane ,Map attribute){
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
  private static void setAlignment(Pane pane, Map attribute, GPresentBuilder pdb){
    if(attribute[GPresentBuilder.alignmentKeyword] != null)
      pane.setAlignment(TreatingAlignment.stringToAlignment(attribute[alignmentKeyword].toString()))
    else
      pane.setAlignment(pdb.defaultAlignment)
  }


  // alignmentを指定するキーワード
  static final String alignmentCenter = 'center'
  static final String alignmentCenterRight = 'right'
  static final String alignmentCenterLeft = 'left'
  static final String alignmentBaseCenter = 'base'
  static final String alignmentBaseRight = 'baseright'
  static final String alignmentBaseLeft = 'baseleft'
  static final String alignmentTopCenter = 'top'
  static final String alignmentTopRight = 'topright'
  static final String alignmentTopLeft = 'topleft'
  static final String alignmentBottomCenter = 'bottom'
  static final String alignmentBottomRight = 'bottomright'
  static final String alignmentBottomLeft = 'bottomleft'

  /** 文字列からアラインメントを指定するPosを生成する */
  static Pos stringToAlignment(String align){
     switch(align){
       case alignmentCenter: return Pos.CENTER; break
       case alignmentCenterRight : return Pos.CENTER_RIGHT; break
       case alignmentCenterLeft : return Pos.CENTER_LEFT; break
       case alignmentBaseCenter: return Pos.BASELINE_CENTER; break;
       case alignmentBaseRight : return Pos.BASELINE_RIGHT; break
       case alignmentBaseLeft  : return Pos.BASELINE_LEFT; break
       case alignmentTopCenter : return Pos.TOP_CENTER; break
       case alignmentTopRight : return Pos.TOP_RIGHT; break
       case alignmentTopLeft  : return Pos.TOP_LEFT; break
       case alignmentBottomCenter : return Pos.BOTTOM_CENTER; break
       case alignmentBottomRight  : return Pos.BOTTOM_RIGHT; break
       case alignmentBottomLeft : return Pos.BOTTOM_LEFT; break
       default: return Pos.CENTER
     }
  }
}
