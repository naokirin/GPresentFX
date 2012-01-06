package main.groovy.gpresentfx.layout

import javafx.geometry.Insets

/**
 * @author naokirin
 * スライド全体の情報を保持するクラス
 */
class Slides{
  String name = ""
  double width = 0
  double height = 0
  boolean isPageCount = false
  List present = []

  Slides(){}

  Slides(String name, double width, double height, boolean isPageCount){
    this.name = name
    this.width = width
    this.height = height
    this.isPageCount = isPageCount
  }
}

/**
 * @author naokirin
 * Slidesを生成するファクトリクラス
 */
class SlidesFactory{

  static final String slidesNameKeyword = 'name'
  static final String windowWidthKeyword = 'width'
  static final String windowHeightKeyword = 'height'
  static final String alignmentKeyword = 'alignment'
  static final String paddingKeyword = 'padding'
  static final String backgroundKeyword = 'background'
  static final String textColorKeyword = 'textcolor'
  static final String fontSizeKeyword = 'fontsize'
  static final String fontFamilyKeyword = 'fontfamily'
  static final String pageCounterKeyword = 'pagecounter'

  static Slides newInstance(pdb, Map attribute){
    def obj = new Slides(
      attribute[slidesNameKeyword].toString(),
      (float)(attribute[windowWidthKeyword]?:pdb.defaultWidth),
      (float)(attribute[windowHeightKeyword]?:pdb.defaultHeight),
      (boolean)(attribute[pageCounterKeyword]))

    if(attribute[windowWidthKeyword] != null)
     pdb.defaultWidth = (float)attribute[windowWidthKeyword]

    if(attribute[windowHeightKeyword] != null)
      pdb.defaultHeight = (float)attribute[windowHeightKeyword]

    if(attribute[alignmentKeyword] != null)
      pdb.defaultAlignment = TreatingAlignment.stringToAlignment(attribute[alignmentKeyword].toString())

    if(attribute[paddingKeyword] != null){
      if(attribute[paddingKeyword] instanceof List){
        def padding = attribute[paddingKeyword] as List
        if(padding.size() == 4)
          pdb.defaultPadding = new Insets((double)padding[0], (double)padding[1], (double)padding[2], (double)padding[3])
        if(padding.size() == 2)
          pdb.defaultPadding = new Insets((double)padding[0], (double)padding[1], (double)padding[0], (double)padding[1])
      }
      else{
        def padding = (double)attribute[paddingKeyword]
        pdb.defaultPadding = new Insets(padding, padding, padding, padding)
      }
    }

    if(attribute[backgroundKeyword] != null)
      pdb.defaultBackground = attribute[backgroundKeyword].toString()

    if(attribute[textColorKeyword] != null)
      pdb.defaultTextColor = attribute[textColorKeyword].toString()

    if(attribute[fontSizeKeyword] != null)
      pdb.defaultFontSize = (int)attribute[fontSizeKeyword]

    if(attribute[fontFamilyKeyword] != null)
      pdb.defaultFontFamily = attribute[fontFamilyKeyword]

    return obj
  }
}
