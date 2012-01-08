package main.groovy.gpresentfx.layout

import javafx.geometry.Insets
import main.groovy.gpresentfx.GPresentBuilder

/**
 * @author naokirin
 * スライド全体の情報を保持するクラス
 */
class Slides{
  String name = ""
  boolean isPageCount = false
  List presents = []

  Slides(){}

  Slides(String name, boolean isPageCount){
    this.name = name
    this.isPageCount = isPageCount
  }
}

/**
 * @author naokirin
 * Slidesを生成するファクトリクラス
 */
class SlidesFactory{

  static final String slidesNameKeyword = 'name'
  static final String pageCounterKeyword = 'pagecounter'

  static Slides newInstance(GPresentBuilder pdb, Map attribute){
    def obj = new Slides(
      attribute[slidesNameKeyword]!=null?attribute[slidesNameKeyword].toString():"",
      (boolean)(attribute[pageCounterKeyword]))

    return obj
  }
}
