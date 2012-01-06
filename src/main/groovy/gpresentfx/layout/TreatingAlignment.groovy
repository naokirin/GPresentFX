package main.groovy.gpresentfx.layout

import javafx.geometry.Pos

class TreatingAlignment {
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
